package com.tecsup.prj_pc02.modelo.dto;

public class CredencialesOAuthDTO {
    private String email;
    private String oauthProvider;
    private String oauthId;

    // Getters y setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOauthProvider() { return oauthProvider; }
    public void setOauthProvider(String oauthProvider) { this.oauthProvider = oauthProvider; }

    public String getOauthId() { return oauthId; }
    public void setOauthId(String oauthId) { this.oauthId = oauthId; }
}
