package com.chqiuu.publisher.article.query;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 支持的平台信息分页查询对象
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Data
@Schema(description = "支持的平台信息分页查询对象")
public class PlatformsPageQuery implements Serializable{

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
    /** 平台唯一标识符 (e.g., wechat_mp, csdn, juejin, github) `platform_key` varchar(50) NOT NULL  COMMENT 平台唯一标识符 (e.g., wechat_mp, csdn, juejin, github) */
    @Schema(description = "平台唯一标识符 (e.g., wechat_mp, csdn, juejin, github)")
    private String platformKey;
    /** 平台名称 (e.g., 微信公众号, CSDN, 稀土掘金, GitHub) `name` varchar(100) NOT NULL  COMMENT 平台名称 (e.g., 微信公众号, CSDN, 稀土掘金, GitHub) */
    @Schema(description = "平台名称 (e.g., 微信公众号, CSDN, 稀土掘金, GitHub)")
    private String name;
    /** 平台类型: publish-仅发布, import-仅导入, both-发布和导入 `type` enum('publish','import','both') NOT NULL  COMMENT 平台类型: publish-仅发布, import-仅导入, both-发布和导入 */
    @Schema(description = "平台类型: publish-仅发布, import-仅导入, both-发布和导入")
    private String type;
    /** 平台图标URL (可选) `icon_url` varchar(255)  COMMENT 平台图标URL (可选) */
    @Schema(description = "平台图标URL (可选)")
    private String iconUrl;
    /** 创建时间 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 创建时间 */
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
    /** 更新时间 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT 更新时间 */
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;
}