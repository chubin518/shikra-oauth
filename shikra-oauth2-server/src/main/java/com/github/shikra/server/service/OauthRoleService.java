package com.github.shikra.server.service;

import com.github.shikra.server.mapper.dynamic.OauthRoleMapper;
import com.github.shikra.server.model.OauthRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OauthRoleService {
    @Autowired
    private OauthRoleMapper oauthRoleMapper;

    @Transactional()
    public void save(OauthRole oauthRole) {
        oauthRoleMapper.insert(oauthRole);
    }
}
