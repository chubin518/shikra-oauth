package com.github.shikra.server.mapper.dynamic;

import com.github.shikra.server.model.OauthUser;
import com.github.shikra.server.model.OauthUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OauthUserMapper {
    long countByExample(OauthUserExample example);

    int deleteByExample(OauthUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OauthUser record);

    int insertSelective(OauthUser record);

    List<OauthUser> selectByExampleWithRowbounds(OauthUserExample example, RowBounds rowBounds);

    List<OauthUser> selectByExample(OauthUserExample example);

    OauthUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OauthUser record, @Param("example") OauthUserExample example);

    int updateByExample(@Param("record") OauthUser record, @Param("example") OauthUserExample example);

    int updateByPrimaryKeySelective(OauthUser record);

    int updateByPrimaryKey(OauthUser record);
}