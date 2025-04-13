package com.chqiuu.publisher.article.query;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 文章在各平台的发布记录分页查询对象
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Data
@Schema(description = "文章在各平台的发布记录分页查询对象")
public class ArticlePublicationsPageQuery implements Serializable{

    private static final long serialVersionUID = 1L;
    /** 排序参数 */
    @Schema(description = "排序参数")
    private String sortParam;
    /** 排序方式：正序asc，倒序desc，默认为desc */
    @Schema(description = "排序方式：正序asc，倒序desc，默认为desc")
    private String sortord;
    /** 当前页 */
    @Schema(description = "当前页")
    private Integer current = 1;
    /** 每页显示条数 */
    @Schema(description = "每页显示条数")
    private Integer size = 10;

//TODO 当您看到这个后您应该自己修改模板增减规则
    /** 文章ID `article_id` bigint(20) unsigned NOT NULL  COMMENT 文章ID */
    @Schema(description = "文章ID")
    private Long articleId;
    /** 目标发布平台ID `platform_id` int(10) unsigned NOT NULL  COMMENT 目标发布平台ID */
    @Schema(description = "目标发布平台ID")
    private Integer platformId;
    /** 用于发布的平台账号ID `user_platform_account_id` bigint(20) unsigned NOT NULL  COMMENT 用于发布的平台账号ID */
    @Schema(description = "用于发布的平台账号ID")
    private Long userPlatformAccountId;
    /** 发布状态 `status` enum('pending','publishing','published','failed','updating','update_failed','deleted') NOT NULL DEFAULT pending  COMMENT 发布状态 */
    @Schema(description = "发布状态")
    private String status;
    /** 文章在目标平台的唯一ID (如果发布成功) `platform_article_id` varchar(100)  COMMENT 文章在目标平台的唯一ID (如果发布成功) */
    @Schema(description = "文章在目标平台的唯一ID (如果发布成功)")
    private String platformArticleId;
    /** 文章在目标平台的访问URL (如果发布成功) `platform_article_url` varchar(512)  COMMENT 文章在目标平台的访问URL (如果发布成功) */
    @Schema(description = "文章在目标平台的访问URL (如果发布成功)")
    private String platformArticleUrl;
    /** 上次尝试发布/更新的时间 `last_attempt_at` timestamp  COMMENT 上次尝试发布/更新的时间 */
    @Schema(description = "上次尝试发布/更新的时间")
    private LocalDateTime lastAttemptAt;
    /** 成功发布到此平台的时间 `published_at` timestamp  COMMENT 成功发布到此平台的时间 */
    @Schema(description = "成功发布到此平台的时间")
    private LocalDateTime publishedAt;
    /** 最后一次成功同步/发布/更新到此平台的时间 `last_success_sync_at` timestamp  COMMENT 最后一次成功同步/发布/更新到此平台的时间 */
    @Schema(description = "最后一次成功同步/发布/更新到此平台的时间")
    private LocalDateTime lastSuccessSyncAt;
    /** 记录创建时间 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 记录创建时间 */
    @Schema(description = "记录创建时间")
    private LocalDateTime createdAt;
    /** 记录更新时间 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT 记录更新时间 */
    @Schema(description = "记录更新时间")
    private LocalDateTime updatedAt;
}