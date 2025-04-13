package com.chqiuu.publisher.article.dto;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 文章信息列表信息
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Data
@Schema(description = "文章信息列表信息")
public class ArticlesListDTO implements Serializable{

    private static final long serialVersionUID = 1L;
//TODO 当您看到这个后您应该自己修改模板增减字段
    /** 文章ID `id` bigint(20) unsigned NOT NULL auto_increment COMMENT 文章ID */
    @Schema(description = "文章ID")

    private Long id;
    /** 作者的用户ID `user_id` bigint(20) unsigned NOT NULL  COMMENT 作者的用户ID */
    @Schema(description = "作者的用户ID")

    private Long userId;
    /** 文章标题 `title` varchar(255) NOT NULL  COMMENT 文章标题 */
    @Schema(description = "文章标题")

    private String title;
    /** 文章的唯一标识符/别名 (用于URL，可选) `slug` varchar(255)  COMMENT 文章的唯一标识符/别名 (用于URL，可选) */
    @Schema(description = "文章的唯一标识符/别名 (用于URL，可选)")

    private String slug;
    /** Markdown 格式的文章内容 `content_md` mediumtext NOT NULL  COMMENT Markdown 格式的文章内容 */
    @Schema(description = "Markdown 格式的文章内容")

    private String contentMd;
    /** 封面图片URL `cover_image_url` varchar(255)  COMMENT 封面图片URL */
    @Schema(description = "封面图片URL")

    private String coverImageUrl;
    /** 文章标签 (逗号分隔或其他格式) `tags` varchar(255)  COMMENT 文章标签 (逗号分隔或其他格式) */
    @Schema(description = "文章标签 (逗号分隔或其他格式)")

    private String tags;
    /** 文章在系统内的状态 `status` enum('draft','published','archived') NOT NULL DEFAULT draft  COMMENT 文章在系统内的状态 */
    @Schema(description = "文章在系统内的状态")

    private String status;
    /** 是否原创 `is_original` tinyint(1) NOT NULL DEFAULT 1  COMMENT 是否原创 */
    @Schema(description = "是否原创")

    private Boolean isOriginal;
    /** 导入来源平台ID (如果文章是导入的) `source_platform_id` int(10) unsigned  COMMENT 导入来源平台ID (如果文章是导入的) */
    @Schema(description = "导入来源平台ID (如果文章是导入的)")

    private Integer sourcePlatformId;
    /** 导入来源的原始URL (如果文章是导入的) `source_url` varchar(512)  COMMENT 导入来源的原始URL (如果文章是导入的) */
    @Schema(description = "导入来源的原始URL (如果文章是导入的)")

    private String sourceUrl;
    /** 创建时间 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 创建时间 */
    @Schema(description = "创建时间")

    private LocalDateTime createdAt;
    /** 最后更新时间 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT 最后更新时间 */
    @Schema(description = "最后更新时间")

    private LocalDateTime updatedAt;
    /** 首次发布时间 (任意平台) `published_at` timestamp  COMMENT 首次发布时间 (任意平台) */
    @Schema(description = "首次发布时间 (任意平台)")

    private LocalDateTime publishedAt;
}