package com.github.shikra.server.mapper.dynamic;

import com.github.shikra.server.model.OauthUserRole;
import com.github.shikra.server.model.OauthUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OauthUserRoleMapper {
    long countByExample(OauthUserRoleExample example);

    int deleteByExample(OauthUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OauthUserRole record);

    int insertSelective(OauthUserRole record);

    List<OauthUserRole> selectByExampleWithRowbounds(OauthUserRoleExample example, RowBounds rowBounds);

    List<OauthUserRole> selectByExample(OauthUserRoleExample example);

    OauthUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OauthUserRole record, @Param("example") OauthUserRoleExample example);

    int updateByExample(@Param("record") OauthUserRole record, @Param("example") OauthUserRoleExample example);

    int updateByPrimaryKeySelective(OauthUserRole record);

    int updateByPrimaryKey(OauthUserRole record);
}