package com.chqiuu.publisher.article.mapper;
import com.chqiuu.publisher.article.entity.ArticlesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chqiuu.publisher.article.dto.ArticlesBriefDTO;
import com.chqiuu.publisher.article.dto.ArticlesDetailDTO;
import com.chqiuu.publisher.article.dto.ArticlesListDTO;
import com.chqiuu.publisher.article.query.ArticlesListQuery;
import com.chqiuu.publisher.article.query.ArticlesPageQuery;
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
 * 文章信息数据持久层
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Mapper
public interface ArticlesMapper extends BaseMapper<ArticlesEntity> {

    /**
    * 插入数据，如果中已经存在相同的记录，则忽略当前新数据
    *
    * @param entity 实体类对象
    * @return 影响条数
    */
    @Insert("<script>INSERT IGNORE INTO `articles` <trim prefix='(' suffix=')' suffixOverrides=','><if test='id != null'>`id`, </if><if test='userId != null'>`user_id`, </if><if test='title != null'>`title`, </if><if test='slug != null'>`slug`, </if><if test='contentMd != null'>`content_md`, </if><if test='coverImageUrl != null'>`cover_image_url`, </if><if test='tags != null'>`tags`, </if><if test='status != null'>`status`, </if><if test='isOriginal != null'>`is_original`, </if><if test='sourcePlatformId != null'>`source_platform_id`, </if><if test='sourceUrl != null'>`source_url`, </if><if test='createdAt != null'>`created_at`, </if><if test='updatedAt != null'>`updated_at`, </if><if test='publishedAt != null'>`published_at`, </if></trim><trim prefix='values (' suffix=')' suffixOverrides=','><if test='id != null'>#{id}, </if><if test='userId != null'>#{userId}, </if><if test='title != null'>#{title}, </if><if test='slug != null'>#{slug}, </if><if test='contentMd != null'>#{contentMd}, </if><if test='coverImageUrl != null'>#{coverImageUrl}, </if><if test='tags != null'>#{tags}, </if><if test='status != null'>#{status}, </if><if test='isOriginal != null'>#{isOriginal}, </if><if test='sourcePlatformId != null'>#{sourcePlatformId}, </if><if test='sourceUrl != null'>#{sourceUrl}, </if><if test='createdAt != null'>#{createdAt}, </if><if test='updatedAt != null'>#{updatedAt}, </if><if test='publishedAt != null'>#{publishedAt}, </if></trim></script>")
    int insertIgnore(ArticlesEntity entity);

    /**
    * 替换数据，如果中已经存在相同的记录，则覆盖旧数据
    *
    * @param entity 实体类对象
    * @return 影响条数
    */
    @Insert("<script>REPLACE INTO `articles` <trim prefix='(' suffix=')' suffixOverrides=','><if test='id != null'>`id`, </if><if test='userId != null'>`user_id`, </if><if test='title != null'>`title`, </if><if test='slug != null'>`slug`, </if><if test='contentMd != null'>`content_md`, </if><if test='coverImageUrl != null'>`cover_image_url`, </if><if test='tags != null'>`tags`, </if><if test='status != null'>`status`, </if><if test='isOriginal != null'>`is_original`, </if><if test='sourcePlatformId != null'>`source_platform_id`, </if><if test='sourceUrl != null'>`source_url`, </if><if test='createdAt != null'>`created_at`, </if><if test='updatedAt != null'>`updated_at`, </if><if test='publishedAt != null'>`published_at`, </if></trim><trim prefix='values (' suffix=')' suffixOverrides=','><if test='id != null'>#{id}, </if><if test='userId != null'>#{userId}, </if><if test='title != null'>#{title}, </if><if test='slug != null'>#{slug}, </if><if test='contentMd != null'>#{contentMd}, </if><if test='coverImageUrl != null'>#{coverImageUrl}, </if><if test='tags != null'>#{tags}, </if><if test='status != null'>#{status}, </if><if test='isOriginal != null'>#{isOriginal}, </if><if test='sourcePlatformId != null'>#{sourcePlatformId}, </if><if test='sourceUrl != null'>#{sourceUrl}, </if><if test='createdAt != null'>#{createdAt}, </if><if test='updatedAt != null'>#{updatedAt}, </if><if test='publishedAt != null'>#{publishedAt}, </if></trim></script>")
    int replace(ArticlesEntity entity);

    /**
    * 根据唯一ID获取简要信息
    *
    * @param id 文章ID
    * @return 简要信息
    */
    ArticlesBriefDTO getBriefById(@Param("id") Long id);

    /**
    * 根据唯一ID获取详细信息
    *
    * @param id 文章ID
    * @return 详细信息
    */
    ArticlesDetailDTO getDetailById(@Param("id") Long id);

    /**
    * 文章信息列表查询
    * @param query       查询对象
    * @return 文章信息列表
    */
    List<ArticlesListDTO> getList(@Param("query") ArticlesListQuery query);

    /**
    * 文章信息分页查询
    * @param pageInfo      分页控件
    * @param query       分页查询对象
    * @return 文章信息列表
    */
    IPage<ArticlesListDTO> getPage(@Param("pg") Page<ArticlesListDTO> pageInfo, @Param("query") ArticlesPageQuery query);
}
