package com.chqiuu.publisher.article.mapper;
import com.chqiuu.publisher.article.entity.UserPlatformAccountsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chqiuu.publisher.article.dto.UserPlatformAccountsBriefDTO;
import com.chqiuu.publisher.article.dto.UserPlatformAccountsDetailDTO;
import com.chqiuu.publisher.article.dto.UserPlatformAccountsListDTO;
import com.chqiuu.publisher.article.query.UserPlatformAccountsListQuery;
import com.chqiuu.publisher.article.query.UserPlatformAccountsPageQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
/**
 * 用户绑定的平台账号信息数据持久层
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Mapper
public interface UserPlatformAccountsMapper extends BaseMapper<UserPlatformAccountsEntity> {

    /**
    * 插入数据，如果中已经存在相同的记录，则忽略当前新数据
    *
    * @param entity 实体类对象
    * @return 影响条数
    */
    @Insert("<script>INSERT IGNORE INTO `user_platform_accounts` <trim prefix='(' suffix=')' suffixOverrides=','><if test='id != null'>`id`, </if><if test='userId != null'>`user_id`, </if><if test='platformId != null'>`platform_id`, </if><if test='accountNickname != null'>`account_nickname`, </if><if test='platformUsername != null'>`platform_username`, </if><if test='credentials != null'>`credentials`, </if><if test='status != null'>`status`, </if><if test='lastVerifiedAt != null'>`last_verified_at`, </if><if test='createdAt != null'>`created_at`, </if><if test='updatedAt != null'>`updated_at`, </if></trim><trim prefix='values (' suffix=')' suffixOverrides=','><if test='id != null'>#{id}, </if><if test='userId != null'>#{userId}, </if><if test='platformId != null'>#{platformId}, </if><if test='accountNickname != null'>#{accountNickname}, </if><if test='platformUsername != null'>#{platformUsername}, </if><if test='credentials != null'>#{credentials}, </if><if test='status != null'>#{status}, </if><if test='lastVerifiedAt != null'>#{lastVerifiedAt}, </if><if test='createdAt != null'>#{createdAt}, </if><if test='updatedAt != null'>#{updatedAt}, </if></trim></script>")
    int insertIgnore(UserPlatformAccountsEntity entity);

    /**
    * 替换数据，如果中已经存在相同的记录，则覆盖旧数据
    *
    * @param entity 实体类对象
    * @return 影响条数
    */
    @Insert("<script>REPLACE INTO `user_platform_accounts` <trim prefix='(' suffix=')' suffixOverrides=','><if test='id != null'>`id`, </if><if test='userId != null'>`user_id`, </if><if test='platformId != null'>`platform_id`, </if><if test='accountNickname != null'>`account_nickname`, </if><if test='platformUsername != null'>`platform_username`, </if><if test='credentials != null'>`credentials`, </if><if test='status != null'>`status`, </if><if test='lastVerifiedAt != null'>`last_verified_at`, </if><if test='createdAt != null'>`created_at`, </if><if test='updatedAt != null'>`updated_at`, </if></trim><trim prefix='values (' suffix=')' suffixOverrides=','><if test='id != null'>#{id}, </if><if test='userId != null'>#{userId}, </if><if test='platformId != null'>#{platformId}, </if><if test='accountNickname != null'>#{accountNickname}, </if><if test='platformUsername != null'>#{platformUsername}, </if><if test='credentials != null'>#{credentials}, </if><if test='status != null'>#{status}, </if><if test='lastVerifiedAt != null'>#{lastVerifiedAt}, </if><if test='createdAt != null'>#{createdAt}, </if><if test='updatedAt != null'>#{updatedAt}, </if></trim></script>")
    int replace(UserPlatformAccountsEntity entity);

    /**
    * 根据唯一ID获取简要信息
    *
    * @param id 关联记录ID
    * @return 简要信息
    */
    UserPlatformAccountsBriefDTO getBriefById(@Param("id") Long id);

    /**
    * 根据唯一ID获取详细信息
    *
    * @param id 关联记录ID
    * @return 详细信息
    */
    UserPlatformAccountsDetailDTO getDetailById(@Param("id") Long id);

    /**
    * 用户绑定的平台账号信息列表查询
    * @param query       查询对象
    * @return 用户绑定的平台账号信息列表
    */
    List<UserPlatformAccountsListDTO> getList(@Param("query") UserPlatformAccountsListQuery query);

    /**
    * 用户绑定的平台账号信息分页查询
    * @param pageInfo      分页控件
    * @param query       分页查询对象
    * @return 用户绑定的平台账号信息列表
    */
    IPage<UserPlatformAccountsListDTO> getPage(@Param("pg") Page<UserPlatformAccountsListDTO> pageInfo, @Param("query") UserPlatformAccountsPageQuery query);
}
