package com.chqiuu.publisher.article.mapper;
import com.chqiuu.publisher.article.entity.PlatformsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chqiuu.publisher.article.dto.PlatformsBriefDTO;
import com.chqiuu.publisher.article.dto.PlatformsDetailDTO;
import com.chqiuu.publisher.article.dto.PlatformsListDTO;
import com.chqiuu.publisher.article.query.PlatformsListQuery;
import com.chqiuu.publisher.article.query.PlatformsPageQuery;
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
 * 支持的平台信息数据持久层
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Mapper
public interface PlatformsMapper extends BaseMapper<PlatformsEntity> {

    /**
    * 插入数据，如果中已经存在相同的记录，则忽略当前新数据
    *
    * @param entity 实体类对象
    * @return 影响条数
    */
    @Insert("<script>INSERT IGNORE INTO `platforms` <trim prefix='(' suffix=')' suffixOverrides=','><if test='id != null'>`id`, </if><if test='platformKey != null'>`platform_key`, </if><if test='name != null'>`name`, </if><if test='type != null'>`type`, </if><if test='iconUrl != null'>`icon_url`, </if><if test='description != null'>`description`, </if><if test='createdAt != null'>`created_at`, </if><if test='updatedAt != null'>`updated_at`, </if></trim><trim prefix='values (' suffix=')' suffixOverrides=','><if test='id != null'>#{id}, </if><if test='platformKey != null'>#{platformKey}, </if><if test='name != null'>#{name}, </if><if test='type != null'>#{type}, </if><if test='iconUrl != null'>#{iconUrl}, </if><if test='description != null'>#{description}, </if><if test='createdAt != null'>#{createdAt}, </if><if test='updatedAt != null'>#{updatedAt}, </if></trim></script>")
    int insertIgnore(PlatformsEntity entity);

    /**
    * 替换数据，如果中已经存在相同的记录，则覆盖旧数据
    *
    * @param entity 实体类对象
    * @return 影响条数
    */
    @Insert("<script>REPLACE INTO `platforms` <trim prefix='(' suffix=')' suffixOverrides=','><if test='id != null'>`id`, </if><if test='platformKey != null'>`platform_key`, </if><if test='name != null'>`name`, </if><if test='type != null'>`type`, </if><if test='iconUrl != null'>`icon_url`, </if><if test='description != null'>`description`, </if><if test='createdAt != null'>`created_at`, </if><if test='updatedAt != null'>`updated_at`, </if></trim><trim prefix='values (' suffix=')' suffixOverrides=','><if test='id != null'>#{id}, </if><if test='platformKey != null'>#{platformKey}, </if><if test='name != null'>#{name}, </if><if test='type != null'>#{type}, </if><if test='iconUrl != null'>#{iconUrl}, </if><if test='description != null'>#{description}, </if><if test='createdAt != null'>#{createdAt}, </if><if test='updatedAt != null'>#{updatedAt}, </if></trim></script>")
    int replace(PlatformsEntity entity);

    /**
    * 根据唯一ID获取简要信息
    *
    * @param id 平台ID
    * @return 简要信息
    */
    PlatformsBriefDTO getBriefById(@Param("id") Integer id);

    /**
    * 根据唯一ID获取详细信息
    *
    * @param id 平台ID
    * @return 详细信息
    */
    PlatformsDetailDTO getDetailById(@Param("id") Integer id);

    /**
    * 支持的平台信息列表查询
    * @param query       查询对象
    * @return 支持的平台信息列表
    */
    List<PlatformsListDTO> getList(@Param("query") PlatformsListQuery query);

    /**
    * 支持的平台信息分页查询
    * @param pageInfo      分页控件
    * @param query       分页查询对象
    * @return 支持的平台信息列表
    */
    IPage<PlatformsListDTO> getPage(@Param("pg") Page<PlatformsListDTO> pageInfo, @Param("query") PlatformsPageQuery query);
}
