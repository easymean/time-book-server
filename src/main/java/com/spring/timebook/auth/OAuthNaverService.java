package com.spring.timebook.auth;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.timebook.auth.exception.ParseException;
import com.spring.timebook.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

@Component
public class OAuthNaverService implements OAuthService{

    @Value("${login.naver.client-id}")
    private String CLIENT_ID;

    @Value("${login.naver.client-secret}")
    private String CLIENT_SECRET;

    @Value("${login.naver.redirect-uri}")
    private String REDIRECT_URI;

    // 로그인 인증 요청
    @Value("${login.naver.authorization-uri}")
    private String AUTHORIZATION_URI;

    // 토급 요청
    @Value("${login.naver.token-uri}")
    private String TOKEN_URI;

    // 프로필 정보 요청
    @Value("${login.naver.user-info-uri}")
    private String USER_INFO_URI;

    public OAuthNaverService() {
    }

    @Override
    public String redirect() {
       return getRedirectUrl();
    }

    @Override
    public User process() {

        Map<String, String> resMap = loginRequest();
        
        String code = resMap.get("code");
        String state = resMap.get("state");
        
        if(code.isEmpty() || state.isEmpty()){
            //에러 발생
            throw new ParseException("빈 값이 왔다!!!");
        }
        
        // access token 발급
        String accessToken = getAccessToken(code,state);

        // 유저 프로필 정보 가져오기
        return getUserInfo(accessToken);
    }


    /**
     * 로그인 요청 후 콜백 URL에 code와 state 값이 전달된다.
     * @param state
     * @return
     */
    public Map<String, String> loginRequest(String state){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("response_type", "code");
        body.add("client_id", CLIENT_ID);
        body.add("redirect_uri", REDIRECT_URI);
        body.add("state", state);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate(
                new SimpleClientHttpRequestFactory(){
                    @Override
                    protected void prepareConnection(HttpURLConnection connection, String httpMethod){
                        connection.setInstanceFollowRedirects(false);
                    }
                }
        );
        ResponseEntity<String> response = rt.exchange(
                getRedirectUrl(),
                HttpMethod.GET,
                request,
                String.class
        );

        String redirectURL =  response.getHeaders().getLocation() == null ? "" : response.getHeaders().getLocation().toString();

       return parseRequest(redirectURL);
    }

    public String getAccessToken(String code, String state) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", CLIENT_ID);
        body.add("client_secret", CLIENT_SECRET);
        body.add("code", code);
        body.add("state", state);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                TOKEN_URI,
                HttpMethod.POST,
                request,
                String.class
        );
        return parseToken(response.getBody());
    }

    public User getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기 - Post 방식
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                USER_INFO_URI,
                HttpMethod.POST,
                request,
                String.class
        );

         return parseUserInfo(response.getBody());
    }

    private String getRedirectUrl(){
        String state = new BigInteger(130, new SecureRandom()).toString();
        return AUTHORIZATION_URI + "?response_type=code&client_id=" + CLIENT_ID + "&state=" + state + "&redirect_uri=" + REDIRECT_URI;
    }

    private Map<String, String> parseRequest(String responseBody){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            String code = jsonNode.get("code").asText();
            String state = jsonNode.get("state").asText();

            return new HashMap<>(){{
                put("code", code);
                put("state", state);
            }};
        } catch (Exception e) {
            throw new ParseException(e.getMessage());
        }
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

    private User parseUserInfo(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            String resultCode = jsonNode.get("resultcode").asText();
            String message = jsonNode.get("message").asText();

            JsonNode response = jsonNode.get("response");

            String email = response.get("email").asText();
            String nickname = response.get("nickname").asText();

            return User.builder()
                    .email(email)
                    .username(nickname).build();

        } catch (Exception e) {
            throw new ParseException(e.getMessage());
        }
    }
}
