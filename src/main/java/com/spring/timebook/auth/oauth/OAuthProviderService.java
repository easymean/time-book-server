package com.spring.timebook.auth.oauth;

import java.util.Map;

public interface OAuthProviderService {
    String redirect();
    OAuthUser process(Map<String, String> info);
}
