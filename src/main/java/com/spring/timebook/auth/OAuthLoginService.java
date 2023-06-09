package com.spring.timebook.auth;

import java.util.Map;

public interface OAuthLoginService {
    String redirect();
    OAuthUser process(Map<String, String> info);
}
