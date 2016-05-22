package com.threebeardsmobile.fitquest.oauth2;

public class AccessToken {
    private String accessToken;
    private String tokenType;
    private String expires_in;
    private String refresh_token;
    private String user_id;

    public String getExpires_in() {
        return expires_in;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        // OAuth requires uppercase Authorization HTTP header value for token type
        if (!Character.isUpperCase(tokenType.charAt(0))) {
            tokenType =
                    Character
                            .toString(tokenType.charAt(0))
                            .toUpperCase() + tokenType.substring(1);
        }

        return tokenType;
    }

    @Override
    public String toString() {
        return "Token Type: " + tokenType +
                " Access Token: " + accessToken +
                " Expires In: " + expires_in +
                " User Id: " + user_id +
                " Refresh Token: " + refresh_token;
    }
}
