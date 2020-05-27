package com.github.shikra.server.mapper.dynamic;

import com.github.shikra.server.model.OauthResource;
import com.github.shikra.server.model.OauthResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OauthResourceMapper {
    long countByExample(OauthResourceExample example);

    int deleteByExample(OauthResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OauthResource record);

    int insertSelective(OauthResource record);

    List<OauthResource> selectByExampleWithRowbounds(OauthResourceExample example, RowBounds rowBounds);

    List<OauthResource> selectByExample(OauthResourceExample example);

    OauthResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OauthResource record, @Param("example") OauthResourceExample example);

    int updateByExample(@Param("record") OauthResource record, @Param("example") OauthResourceExample example);

    int updateByPrimaryKeySelective(OauthResource record);

    int updateByPrimaryKey(OauthResource record);
}