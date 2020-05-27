package com.github.shikra.server.mapper.dynamic;

import com.github.shikra.server.model.OauthRoleResource;
import com.github.shikra.server.model.OauthRoleResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OauthRoleResourceMapper {
    long countByExample(OauthRoleResourceExample example);

    int deleteByExample(OauthRoleResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OauthRoleResource record);

    int insertSelective(OauthRoleResource record);

    List<OauthRoleResource> selectByExampleWithRowbounds(OauthRoleResourceExample example, RowBounds rowBounds);

    List<OauthRoleResource> selectByExample(OauthRoleResourceExample example);

    OauthRoleResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OauthRoleResource record, @Param("example") OauthRoleResourceExample example);

    int updateByExample(@Param("record") OauthRoleResource record, @Param("example") OauthRoleResourceExample example);

    int updateByPrimaryKeySelective(OauthRoleResource record);

    int updateByPrimaryKey(OauthRoleResource record);
}