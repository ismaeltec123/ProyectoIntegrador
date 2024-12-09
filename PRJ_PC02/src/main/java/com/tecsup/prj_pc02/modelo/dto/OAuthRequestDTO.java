package com.tecsup.prj_pc02.modelo.dto;


public class OAuthRequestDTO {

    private String email; // Correo del usuario obtenido del proveedor OAuth
    private String oauthProvider; // Nombre del proveedor, por ejemplo: 'google'
    private String oauthId; // ID único proporcionado por el proveedor OAuth
    private String nombre;
    // Constructor vacío
    public OAuthRequestDTO() {
    }

    // Constructor con todos los campos
    public OAuthRequestDTO(String email, String oauthProvider, String oauthId,String nombre) {
        this.email = email;
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
        this.nombre=nombre;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOauthProvider() {
        return oauthProvider;
    }

    public void setOauthProvider(String oauthProvider) {
        this.oauthProvider = oauthProvider;
    }

    public String getOauthId() {
        return oauthId;
    }

    public void setOauthId(String oauthId) {
        this.oauthId = oauthId;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "OAuthRequest{" +
                "email='" + email + '\'' +
                ", oauthProvider='" + oauthProvider + '\'' +
                ", oauthId='" + oauthId + '\'' +
                '}';
    }
}

