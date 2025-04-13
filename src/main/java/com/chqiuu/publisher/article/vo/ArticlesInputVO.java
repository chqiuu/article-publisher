package com.chqiuu.publisher.article.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import com.chqiuu.publisher.article.entity.ArticlesEntity;

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
 * 文章信息录入信息
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Data
@Schema(description = "文章信息录入信息")
public class ArticlesInputVO implements Serializable{

    private static final long serialVersionUID = 1L;
    //TODO 当您看到这个后您应该自己修改模板增减规则
    /** 文章ID `id` bigint(20) unsigned NOT NULL auto_increment COMMENT 文章ID */
    @NotNull(message = "文章ID不能为空", groups = Update.class)
    // @Max(value = Long.MAX_VALUE, message = "文章ID不能超过{max}", groups = Default.class)
    @Schema(description = "文章ID")
    private Long id;
    /** 作者的用户ID `user_id` bigint(20) unsigned NOT NULL  COMMENT 作者的用户ID */
    @NotNull(message = "作者的用户ID不能为空", groups = Default.class)
    // @Max(value = Long.MAX_VALUE, message = "作者的用户ID不能超过{max}", groups = Default.class)
    @Schema(description = "作者的用户ID")
    private Long userId;
    /** 文章标题 `title` varchar(255) NOT NULL  COMMENT 文章标题 */
    @NotNull(message = "文章标题不能为空", groups = Default.class)
    @Length(max = 255, message = "文章标题不能超过{max}个字符", groups = Default.class)
    @Schema(description = "文章标题")
    private String title;
    /** 文章的唯一标识符/别名 (用于URL，可选) `slug` varchar(255)  COMMENT 文章的唯一标识符/别名 (用于URL，可选) */
    @NotNull(message = "文章的唯一标识符/别名 (用于URL，可选)不能为空", groups = Default.class)
    @Length(max = 255, message = "文章的唯一标识符/别名 (用于URL，可选)不能超过{max}个字符", groups = Default.class)
    @Schema(description = "文章的唯一标识符/别名 (用于URL，可选)")
    private String slug;
    /** Markdown 格式的文章内容 `content_md` mediumtext NOT NULL  COMMENT Markdown 格式的文章内容 */
    @NotNull(message = "Markdown 格式的文章内容不能为空", groups = Default.class)
    @Schema(description = "Markdown 格式的文章内容")
    private String contentMd;
    /** 封面图片URL `cover_image_url` varchar(255)  COMMENT 封面图片URL */
    @NotNull(message = "封面图片URL不能为空", groups = Default.class)
    @Length(max = 255, message = "封面图片URL不能超过{max}个字符", groups = Default.class)
    @Schema(description = "封面图片URL")
    private String coverImageUrl;
    /** 文章标签 (逗号分隔或其他格式) `tags` varchar(255)  COMMENT 文章标签 (逗号分隔或其他格式) */
    @NotNull(message = "文章标签 (逗号分隔或其他格式)不能为空", groups = Default.class)
    @Length(max = 255, message = "文章标签 (逗号分隔或其他格式)不能超过{max}个字符", groups = Default.class)
    @Schema(description = "文章标签 (逗号分隔或其他格式)")
    private String tags;
    /** 文章在系统内的状态 `status` enum('draft','published','archived') NOT NULL DEFAULT draft  COMMENT 文章在系统内的状态 */
    @NotNull(message = "文章在系统内的状态不能为空", groups = Default.class)
    @Length(max = 9, message = "文章在系统内的状态不能超过{max}个字符", groups = Default.class)
    @Schema(description = "文章在系统内的状态")
    private String status;
    /** 是否原创 `is_original` tinyint(1) NOT NULL DEFAULT 1  COMMENT 是否原创 */
    @NotNull(message = "是否原创不能为空", groups = Default.class)
    @Schema(description = "是否原创")
    private Boolean isOriginal;
    /** 导入来源平台ID (如果文章是导入的) `source_platform_id` int(10) unsigned  COMMENT 导入来源平台ID (如果文章是导入的) */
    @NotNull(message = "导入来源平台ID (如果文章是导入的)不能为空", groups = Default.class)
    @Max(value = Integer.MAX_VALUE, message = "导入来源平台ID (如果文章是导入的)不能超过{value}", groups = Default.class)
    @Schema(description = "导入来源平台ID (如果文章是导入的)")
    private Integer sourcePlatformId;
    /** 导入来源的原始URL (如果文章是导入的) `source_url` varchar(512)  COMMENT 导入来源的原始URL (如果文章是导入的) */
    @NotNull(message = "导入来源的原始URL (如果文章是导入的)不能为空", groups = Default.class)
    @Length(max = 512, message = "导入来源的原始URL (如果文章是导入的)不能超过{max}个字符", groups = Default.class)
    @Schema(description = "导入来源的原始URL (如果文章是导入的)")
    private String sourceUrl;
    /** 创建时间 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 创建时间 */
    @NotNull(message = "创建时间不能为空", groups = Default.class)
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
    /** 最后更新时间 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT 最后更新时间 */
    @NotNull(message = "最后更新时间不能为空", groups = Default.class)
    @Schema(description = "最后更新时间")
    private LocalDateTime updatedAt;
    /** 首次发布时间 (任意平台) `published_at` timestamp  COMMENT 首次发布时间 (任意平台) */
    @NotNull(message = "首次发布时间 (任意平台)不能为空", groups = Default.class)
    @Schema(description = "首次发布时间 (任意平台)")
    private LocalDateTime publishedAt;
    /**
     * 输入对象转为实体类
     *
     * @return 实体类
     */
    public ArticlesEntity convertToEntity() {
        ArticlesEntity entity = new ArticlesEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}