package com.chqiuu.publisher.article.mapper;
import com.chqiuu.publisher.article.entity.ArticlePublicationsEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chqiuu.publisher.article.dto.ArticlePublicationsBriefDTO;
import com.chqiuu.publisher.article.dto.ArticlePublicationsDetailDTO;
import com.chqiuu.publisher.article.dto.ArticlePublicationsListDTO;
import com.chqiuu.publisher.article.query.ArticlePublicationsListQuery;
import com.chqiuu.publisher.article.query.ArticlePublicationsPageQuery;
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
 * 文章在各平台的发布记录数据持久层
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Mapper
public interface ArticlePublicationsMapper extends BaseMapper<ArticlePublicationsEntity> {

    /**
    * 插入数据，如果中已经存在相同的记录，则忽略当前新数据
    *
    * @param entity 实体类对象
    * @return 影响条数
    */
    @Insert("<script>INSERT IGNORE INTO `article_publications` <trim prefix='(' suffix=')' suffixOverrides=','><if test='id != null'>`id`, </if><if test='articleId != null'>`article_id`, </if><if test='platformId != null'>`platform_id`, </if><if test='userPlatformAccountId != null'>`user_platform_account_id`, </if><if test='status != null'>`status`, </if><if test='platformArticleId != null'>`platform_article_id`, </if><if test='platformArticleUrl != null'>`platform_article_url`, </if><if test='errorMessage != null'>`error_message`, </if><if test='lastAttemptAt != null'>`last_attempt_at`, </if><if test='publishedAt != null'>`published_at`, </if><if test='lastSuccessSyncAt != null'>`last_success_sync_at`, </if><if test='createdAt != null'>`created_at`, </if><if test='updatedAt != null'>`updated_at`, </if></trim><trim prefix='values (' suffix=')' suffixOverrides=','><if test='id != null'>#{id}, </if><if test='articleId != null'>#{articleId}, </if><if test='platformId != null'>#{platformId}, </if><if test='userPlatformAccountId != null'>#{userPlatformAccountId}, </if><if test='status != null'>#{status}, </if><if test='platformArticleId != null'>#{platformArticleId}, </if><if test='platformArticleUrl != null'>#{platformArticleUrl}, </if><if test='errorMessage != null'>#{errorMessage}, </if><if test='lastAttemptAt != null'>#{lastAttemptAt}, </if><if test='publishedAt != null'>#{publishedAt}, </if><if test='lastSuccessSyncAt != null'>#{lastSuccessSyncAt}, </if><if test='createdAt != null'>#{createdAt}, </if><if test='updatedAt != null'>#{updatedAt}, </if></trim></script>")
    int insertIgnore(ArticlePublicationsEntity entity);

    /**
    * 替换数据，如果中已经存在相同的记录，则覆盖旧数据
    *
    * @param entity 实体类对象
    * @return 影响条数
    */
    @Insert("<script>REPLACE INTO `article_publications` <trim prefix='(' suffix=')' suffixOverrides=','><if test='id != null'>`id`, </if><if test='articleId != null'>`article_id`, </if><if test='platformId != null'>`platform_id`, </if><if test='userPlatformAccountId != null'>`user_platform_account_id`, </if><if test='status != null'>`status`, </if><if test='platformArticleId != null'>`platform_article_id`, </if><if test='platformArticleUrl != null'>`platform_article_url`, </if><if test='errorMessage != null'>`error_message`, </if><if test='lastAttemptAt != null'>`last_attempt_at`, </if><if test='publishedAt != null'>`published_at`, </if><if test='lastSuccessSyncAt != null'>`last_success_sync_at`, </if><if test='createdAt != null'>`created_at`, </if><if test='updatedAt != null'>`updated_at`, </if></trim><trim prefix='values (' suffix=')' suffixOverrides=','><if test='id != null'>#{id}, </if><if test='articleId != null'>#{articleId}, </if><if test='platformId != null'>#{platformId}, </if><if test='userPlatformAccountId != null'>#{userPlatformAccountId}, </if><if test='status != null'>#{status}, </if><if test='platformArticleId != null'>#{platformArticleId}, </if><if test='platformArticleUrl != null'>#{platformArticleUrl}, </if><if test='errorMessage != null'>#{errorMessage}, </if><if test='lastAttemptAt != null'>#{lastAttemptAt}, </if><if test='publishedAt != null'>#{publishedAt}, </if><if test='lastSuccessSyncAt != null'>#{lastSuccessSyncAt}, </if><if test='createdAt != null'>#{createdAt}, </if><if test='updatedAt != null'>#{updatedAt}, </if></trim></script>")
    int replace(ArticlePublicationsEntity entity);

    /**
    * 根据唯一ID获取简要信息
    *
    * @param id 发布记录ID
    * @return 简要信息
    */
    ArticlePublicationsBriefDTO getBriefById(@Param("id") Long id);

    /**
    * 根据唯一ID获取详细信息
    *
    * @param id 发布记录ID
    * @return 详细信息
    */
    ArticlePublicationsDetailDTO getDetailById(@Param("id") Long id);

    /**
    * 文章在各平台的发布记录列表查询
    * @param query       查询对象
    * @return 文章在各平台的发布记录列表
    */
    List<ArticlePublicationsListDTO> getList(@Param("query") ArticlePublicationsListQuery query);

    /**
    * 文章在各平台的发布记录分页查询
    * @param pageInfo      分页控件
    * @param query       分页查询对象
    * @return 文章在各平台的发布记录列表
    */
    IPage<ArticlePublicationsListDTO> getPage(@Param("pg") Page<ArticlePublicationsListDTO> pageInfo, @Param("query") ArticlePublicationsPageQuery query);
}
