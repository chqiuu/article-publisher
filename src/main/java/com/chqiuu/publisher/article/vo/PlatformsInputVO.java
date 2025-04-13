package com.chqiuu.publisher.article.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import com.chqiuu.publisher.article.entity.PlatformsEntity;

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
 * 支持的平台信息录入信息
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Data
@Schema(description = "支持的平台信息录入信息")
public class PlatformsInputVO implements Serializable{

    private static final long serialVersionUID = 1L;
    //TODO 当您看到这个后您应该自己修改模板增减规则
    /** 平台ID `id` int(10) unsigned NOT NULL auto_increment COMMENT 平台ID */
    @NotNull(message = "平台ID不能为空", groups = Update.class)
    @Max(value = Integer.MAX_VALUE, message = "平台ID不能超过{value}", groups = Default.class)
    @Schema(description = "平台ID")
    private Integer id;
    /** 平台唯一标识符 (e.g., wechat_mp, csdn, juejin, github) `platform_key` varchar(50) NOT NULL  COMMENT 平台唯一标识符 (e.g., wechat_mp, csdn, juejin, github) */
    @NotNull(message = "平台唯一标识符 (e.g., wechat_mp, csdn, juejin, github)不能为空", groups = Default.class)
    @Length(max = 50, message = "平台唯一标识符 (e.g., wechat_mp, csdn, juejin, github)不能超过{max}个字符", groups = Default.class)
    @Schema(description = "平台唯一标识符 (e.g., wechat_mp, csdn, juejin, github)")
    private String platformKey;
    /** 平台名称 (e.g., 微信公众号, CSDN, 稀土掘金, GitHub) `name` varchar(100) NOT NULL  COMMENT 平台名称 (e.g., 微信公众号, CSDN, 稀土掘金, GitHub) */
    @NotNull(message = "平台名称 (e.g., 微信公众号, CSDN, 稀土掘金, GitHub)不能为空", groups = Default.class)
    @Length(max = 100, message = "平台名称 (e.g., 微信公众号, CSDN, 稀土掘金, GitHub)不能超过{max}个字符", groups = Default.class)
    @Schema(description = "平台名称 (e.g., 微信公众号, CSDN, 稀土掘金, GitHub)")
    private String name;
    /** 平台类型: publish-仅发布, import-仅导入, both-发布和导入 `type` enum('publish','import','both') NOT NULL  COMMENT 平台类型: publish-仅发布, import-仅导入, both-发布和导入 */
    @NotNull(message = "平台类型: publish-仅发布, import-仅导入, both-发布和导入不能为空", groups = Default.class)
    @Length(max = 7, message = "平台类型: publish-仅发布, import-仅导入, both-发布和导入不能超过{max}个字符", groups = Default.class)
    @Schema(description = "平台类型: publish-仅发布, import-仅导入, both-发布和导入")
    private String type;
    /** 平台图标URL (可选) `icon_url` varchar(255)  COMMENT 平台图标URL (可选) */
    @NotNull(message = "平台图标URL (可选)不能为空", groups = Default.class)
    @Length(max = 255, message = "平台图标URL (可选)不能超过{max}个字符", groups = Default.class)
    @Schema(description = "平台图标URL (可选)")
    private String iconUrl;
    /** 平台描述 (可选) `description` text  COMMENT 平台描述 (可选) */
    @NotNull(message = "平台描述 (可选)不能为空", groups = Default.class)
    @Schema(description = "平台描述 (可选)")
    private String description;
    /** 创建时间 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 创建时间 */
    @NotNull(message = "创建时间不能为空", groups = Default.class)
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
    /** 更新时间 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT 更新时间 */
    @NotNull(message = "更新时间不能为空", groups = Default.class)
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;
    /**
     * 输入对象转为实体类
     *
     * @return 实体类
     */
    public PlatformsEntity convertToEntity() {
        PlatformsEntity entity = new PlatformsEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}