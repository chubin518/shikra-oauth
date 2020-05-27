package com.github.shikra.server.config.security;

import com.github.shikra.server.mapper.dynamic.OauthClientDetailsMapper;
import com.github.shikra.server.model.OauthClientDetails;
import com.github.shikra.server.model.OauthClientDetailsExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecurityClientDetailsService implements ClientDetailsService {
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OauthClientDetailsExample example = new OauthClientDetailsExample();
        example.createCriteria().andClientIdEqualTo(clientId);
        OauthClientDetails oauthClientDetails = oauthClientDetailsMapper.selectByExample(example).stream().findFirst().orElseThrow(() -> new NoSuchClientException("no client with requested id: " + clientId));
        log.info(oauthClientDetails.toString());
        return new SecurityClient(oauthClientDetails);
    }
}
