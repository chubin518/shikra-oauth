package com.github.shikra.server.mapper.dynamic;

import com.github.shikra.server.model.OauthRole;
import com.github.shikra.server.model.OauthRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OauthRoleMapper {
    long countByExample(OauthRoleExample example);

    int deleteByExample(OauthRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OauthRole record);

    int insertSelective(OauthRole record);

    List<OauthRole> selectByExampleWithRowbounds(OauthRoleExample example, RowBounds rowBounds);

    List<OauthRole> selectByExample(OauthRoleExample example);

    OauthRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OauthRole record, @Param("example") OauthRoleExample example);

    int updateByExample(@Param("record") OauthRole record, @Param("example") OauthRoleExample example);

    int updateByPrimaryKeySelective(OauthRole record);

    int updateByPrimaryKey(OauthRole record);
}