package com.spring.timebook.auth.oauth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.timebook.auth.exception.ParseException;
import com.spring.timebook.config.OAuthKakaoConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Getter
@Setter
public class OAuthKakaoService implements OAuthProviderService {

    private String clientId;
    private String redirectUri;

    public OAuthKakaoService(OAuthKakaoConfig oAuthKakaoConfig){
        this.clientId = oAuthKakaoConfig.getApiKey();
        this.redirectUri = oAuthKakaoConfig.getRedirectUri();
    }

    @Override
    public String redirect() {
        return getRedirectUrl();
    }

    @Override
    public OAuthUser process(Map<String, String> info) {

        String code = info.get("code");
        String accessToken = getAccessToken(code);

        return getUserInfo(accessToken);
    }

    public void logout(){

    }

    private String getAccessToken(String code){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("redirect_uri", redirectUri);
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        // 토급 요청
        String TOKEN_URI = "https://kauth.kakao.com/oauth/token";
        ResponseEntity<String> response = rt.exchange(
                TOKEN_URI,
                HttpMethod.POST,
                request,
                String.class
        );
        return parseToken(response.getBody());
    }

    private OAuthUser getUserInfo(String accessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded");

        // HTTP 요청 보내기 - Post 방식
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        // 프로필 정보 요청
        String userInfoUri = "https://kapi.kakao.com/v2/user/me";
        ResponseEntity<String> response = rt.exchange(
                userInfoUri,
                HttpMethod.POST,
                request,
                String.class
        );

        return parseUserInfo(response.getBody());
    }

    private String parseToken(String responseBody){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            return jsonNode.get("access_token").asText();
        } catch (Exception e) {
            throw new ParseException(e.getMessage());
        }
    }

    private OAuthUser parseUserInfo(String responseBody){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode response = objectMapper.readTree(responseBody);

            String id = response.get("id").asText();

            JsonNode account = response.get("kakao_account");
            String email = account.get("email").asText();

            JsonNode profile = account.get("profile");
            String nickname = profile.get("nickname").asText();


            return OAuthUser.builder()
                    .email(email)
                    .nickname(nickname)
                    .snsId(id)
                    .snsType(OAuthProvider.KAKAO)
                    .build();

        } catch (Exception e) {
            throw new ParseException(e.getMessage());
        }
    }

    private String getRedirectUrl(){
        return "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id="+ clientId +
                "&redirect_uri=" + redirectUri;
    }
}
