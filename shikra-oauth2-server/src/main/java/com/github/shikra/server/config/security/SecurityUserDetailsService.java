package com.github.shikra.server.config.security;


import com.github.shikra.server.mapper.dynamic.OauthUserMapper;
import com.github.shikra.server.model.OauthUser;
import com.github.shikra.server.model.OauthUserExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private OauthUserMapper oauthUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        OauthUserExample example = new OauthUserExample();
        example.createCriteria().andUsernameEqualTo(userName);
        OauthUser oauthUser = oauthUserMapper.selectByExample(example).stream().findFirst().orElseThrow(() -> new UsernameNotFoundException("no user with requested name:" + userName));
        log.info(oauthUser.toString());
        return new SecurityUser(oauthUser);
    }
}
