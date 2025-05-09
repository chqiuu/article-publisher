package com.chqiuu.publisher.article.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import com.chqiuu.publisher.article.entity.ArticlePublicationsEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.chqiuu.publisher.common.validator.group.Default;
import com.chqiuu.publisher.common.validator.group.Update;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 文章在各平台的发布记录录入信息
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Data
@Schema(description = "文章在各平台的发布记录录入信息")
public class ArticlePublicationsInputVO implements Serializable{

    private static final long serialVersionUID = 1L;
    //TODO 当您看到这个后您应该自己修改模板增减规则
    /** 发布记录ID `id` bigint(20) unsigned NOT NULL auto_increment COMMENT 发布记录ID */
    @NotNull(message = "发布记录ID不能为空", groups = Update.class)
    // @Max(value = Long.MAX_VALUE, message = "发布记录ID不能超过{max}", groups = Default.class)
    @Schema(description = "发布记录ID")
    private Long id;
    /** 文章ID `article_id` bigint(20) unsigned NOT NULL  COMMENT 文章ID */
    @NotNull(message = "文章ID不能为空", groups = Default.class)
    // @Max(value = Long.MAX_VALUE, message = "文章ID不能超过{max}", groups = Default.class)
    @Schema(description = "文章ID")
    private Long articleId;
    /** 目标发布平台ID `platform_id` int(10) unsigned NOT NULL  COMMENT 目标发布平台ID */
    @NotNull(message = "目标发布平台ID不能为空", groups = Default.class)
    @Max(value = Integer.MAX_VALUE, message = "目标发布平台ID不能超过{value}", groups = Default.class)
    @Schema(description = "目标发布平台ID")
    private Integer platformId;
    /** 用于发布的平台账号ID `user_platform_account_id` bigint(20) unsigned NOT NULL  COMMENT 用于发布的平台账号ID */
    @NotNull(message = "用于发布的平台账号ID不能为空", groups = Default.class)
    // @Max(value = Long.MAX_VALUE, message = "用于发布的平台账号ID不能超过{max}", groups = Default.class)
    @Schema(description = "用于发布的平台账号ID")
    private Long userPlatformAccountId;
    /** 发布状态 `status` enum('pending','publishing','published','failed','updating','update_failed','deleted') NOT NULL DEFAULT pending  COMMENT 发布状态 */
    @NotNull(message = "发布状态不能为空", groups = Default.class)
    @Length(max = 13, message = "发布状态不能超过{max}个字符", groups = Default.class)
    @Schema(description = "发布状态")
    private String status;
    /** 文章在目标平台的唯一ID (如果发布成功) `platform_article_id` varchar(100)  COMMENT 文章在目标平台的唯一ID (如果发布成功) */
    @NotNull(message = "文章在目标平台的唯一ID (如果发布成功)不能为空", groups = Default.class)
    @Length(max = 100, message = "文章在目标平台的唯一ID (如果发布成功)不能超过{max}个字符", groups = Default.class)
    @Schema(description = "文章在目标平台的唯一ID (如果发布成功)")
    private String platformArticleId;
    /** 文章在目标平台的访问URL (如果发布成功) `platform_article_url` varchar(512)  COMMENT 文章在目标平台的访问URL (如果发布成功) */
    @NotNull(message = "文章在目标平台的访问URL (如果发布成功)不能为空", groups = Default.class)
    @Length(max = 512, message = "文章在目标平台的访问URL (如果发布成功)不能超过{max}个字符", groups = Default.class)
    @Schema(description = "文章在目标平台的访问URL (如果发布成功)")
    private String platformArticleUrl;
    /** 发布/更新失败时的错误信息 `error_message` text  COMMENT 发布/更新失败时的错误信息 */
    @NotNull(message = "发布/更新失败时的错误信息不能为空", groups = Default.class)
    @Schema(description = "发布/更新失败时的错误信息")
    private String errorMessage;
    /** 上次尝试发布/更新的时间 `last_attempt_at` timestamp  COMMENT 上次尝试发布/更新的时间 */
    @NotNull(message = "上次尝试发布/更新的时间不能为空", groups = Default.class)
    @Schema(description = "上次尝试发布/更新的时间")
    private LocalDateTime lastAttemptAt;
    /** 成功发布到此平台的时间 `published_at` timestamp  COMMENT 成功发布到此平台的时间 */
    @NotNull(message = "成功发布到此平台的时间不能为空", groups = Default.class)
    @Schema(description = "成功发布到此平台的时间")
    private LocalDateTime publishedAt;
    /** 最后一次成功同步/发布/更新到此平台的时间 `last_success_sync_at` timestamp  COMMENT 最后一次成功同步/发布/更新到此平台的时间 */
    @NotNull(message = "最后一次成功同步/发布/更新到此平台的时间不能为空", groups = Default.class)
    @Schema(description = "最后一次成功同步/发布/更新到此平台的时间")
    private LocalDateTime lastSuccessSyncAt;
    /** 记录创建时间 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 记录创建时间 */
    @NotNull(message = "记录创建时间不能为空", groups = Default.class)
    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;
    /** 记录更新时间 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT 记录更新时间 */
    @NotNull(message = "记录更新时间不能为空", groups = Default.class)
    @Schema(description = "记录更新时间")
    private LocalDateTime updatedAt;
    /**
     * 输入对象转为实体类
     *
     * @return 实体类
     */
    public ArticlePublicationsEntity convertToEntity() {
        ArticlePublicationsEntity entity = new ArticlePublicationsEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}