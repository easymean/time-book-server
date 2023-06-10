package com.spring.timebook.auth.oauth;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.timebook.auth.exception.ParseException;
import com.spring.timebook.config.OAuthNaverConfig;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;

@Getter
@Setter
public class OAuthNaverService implements OAuthProviderService {

    private String clientId;

    private String clientSecret;

    private String redirectUri;

    public OAuthNaverService(OAuthNaverConfig oAuthNaverConfig) {
        this.clientId = oAuthNaverConfig.getClientId();
        this.clientSecret = oAuthNaverConfig.getClientSecret();
        this.redirectUri = oAuthNaverConfig.getRedirectUri();
    }

    @Override
    public String redirect() {
       return getRedirectUrl();
    }

    @Override
    public OAuthUser process(Map<String, String> info) {
        
        // access token 발급

        String code = info.get("code");
        String state = info.get("state");
        String accessToken = getAccessToken(code, state);

        // 유저 프로필 정보 가져오기
        return getUserInfo(accessToken);
    }

    public String getAccessToken(String code, String state) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);
        body.add("state", state);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        // 토급 요청
        String TOKEN_URI = "https://nid.naver.com/oauth2.0/token";
        ResponseEntity<String> response = rt.exchange(
                TOKEN_URI,
                HttpMethod.POST,
                request,
                String.class
        );
        return parseToken(response.getBody());
    }

    public OAuthUser getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기 - Post 방식
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        // 프로필 정보 요청
        String USER_INFO_URI = "https://openapi.naver.com/v1/nid/me";
        ResponseEntity<String> response = rt.exchange(
                USER_INFO_URI,
                HttpMethod.GET,
                request,
                String.class
        );

         return parseUserInfo(response.getBody());
    }

    private String getRedirectUrl(){
        String state = new BigInteger(130, new SecureRandom()).toString();
        return "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" + clientId
                + "&state=" + state + "&redirect_uri=" + redirectUri;
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

    private OAuthUser parseUserInfo(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            String resultCode = jsonNode.get("resultcode").asText();
            String message = jsonNode.get("message").asText();

            JsonNode response = jsonNode.get("response");

            String id = response.get("id").asText();
            String email = response.get("email").asText();
            String nickname = response.get("name").asText();

            return OAuthUser.builder()
                    .email(email)
                    .nickname(nickname)
                    .snsType(OAuthProvider.NAVER)
                    .snsId(id)
                    .build();

        } catch (Exception e) {
            throw new ParseException(e.getMessage());
        }
    }
}
