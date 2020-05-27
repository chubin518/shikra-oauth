package com.github.shikra.server.model;

import java.io.Serializable;

public class OauthClientDetails implements Serializable {
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private static final long serialVersionUID = 1L;

    public String getClientId() {
        return clientId;
    }

    public OauthClientDetails withClientId(String clientId) {
        this.setClientId(clientId);
        return this;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public OauthClientDetails withResourceIds(String resourceIds) {
        this.setResourceIds(resourceIds);
        return this;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public OauthClientDetails withClientSecret(String clientSecret) {
        this.setClientSecret(clientSecret);
        return this;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret == null ? null : clientSecret.trim();
    }

    public String getScope() {
        return scope;
    }

    public OauthClientDetails withScope(String scope) {
        this.setScope(scope);
        return this;
    }

    public void setScope(String scope) {
        this.scope = scope == null ? null : scope.trim();
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public OauthClientDetails withAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.setAuthorizedGrantTypes(authorizedGrantTypes);
        return this;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes == null ? null : authorizedGrantTypes.trim();
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public OauthClientDetails withWebServerRedirectUri(String webServerRedirectUri) {
        this.setWebServerRedirectUri(webServerRedirectUri);
        return this;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri == null ? null : webServerRedirectUri.trim();
    }

    public String getAuthorities() {
        return authorities;
    }

    public OauthClientDetails withAuthorities(String authorities) {
        this.setAuthorities(authorities);
        return this;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities == null ? null : authorities.trim();
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public OauthClientDetails withAccessTokenValidity(Integer accessTokenValidity) {
        this.setAccessTokenValidity(accessTokenValidity);
        return this;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public OauthClientDetails withRefreshTokenValidity(Integer refreshTokenValidity) {
        this.setRefreshTokenValidity(refreshTokenValidity);
        return this;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clientId=").append(clientId);
        sb.append(", resourceIds=").append(resourceIds);
        sb.append(", clientSecret=").append(clientSecret);
        sb.append(", scope=").append(scope);
        sb.append(", authorizedGrantTypes=").append(authorizedGrantTypes);
        sb.append(", webServerRedirectUri=").append(webServerRedirectUri);
        sb.append(", authorities=").append(authorities);
        sb.append(", accessTokenValidity=").append(accessTokenValidity);
        sb.append(", refreshTokenValidity=").append(refreshTokenValidity);
        sb.append("]");
        return sb.toString();
    }
}