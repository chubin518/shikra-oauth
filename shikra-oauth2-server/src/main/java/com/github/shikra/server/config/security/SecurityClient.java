package com.github.shikra.server.config.security;

import com.github.shikra.server.model.OauthClientDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import java.util.*;

public class SecurityClient implements ClientDetails {
    private OauthClientDetails clientDetails;

    public SecurityClient(OauthClientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    @Override
    public String getClientId() {
        return clientDetails.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        if (StringUtils.isEmpty(clientDetails.getResourceIds()))
            return Collections.EMPTY_SET;
        String[] split = clientDetails.getResourceIds().split(",");
        return new HashSet<>(Arrays.asList(split));
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientDetails.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        if (StringUtils.isEmpty(clientDetails.getScope()))
            return Collections.EMPTY_SET;
        String[] split = clientDetails.getScope().split(",");
        return new HashSet<>(Arrays.asList(split));
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if (StringUtils.isEmpty(clientDetails.getAuthorizedGrantTypes()))
            return Collections.EMPTY_SET;
        String[] split = clientDetails.getAuthorizedGrantTypes().split(",");
        return new HashSet<>(Arrays.asList(split));
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        if (StringUtils.isEmpty(clientDetails.getWebServerRedirectUri()))
            return Collections.EMPTY_SET;
        String[] split = clientDetails.getWebServerRedirectUri().split(",");
        return new HashSet<>(Arrays.asList(split));
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return clientDetails.getAccessTokenValidity();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return clientDetails.getRefreshTokenValidity();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.EMPTY_MAP;
    }
}
