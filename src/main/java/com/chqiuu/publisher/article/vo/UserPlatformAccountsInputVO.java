package com.chqiuu.publisher.article.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import com.chqiuu.publisher.article.entity.UserPlatformAccountsEntity;

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
 * 用户绑定的平台账号信息录入信息
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Data
@Schema(description = "用户绑定的平台账号信息录入信息")
public class UserPlatformAccountsInputVO implements Serializable{

    private static final long serialVersionUID = 1L;
    //TODO 当您看到这个后您应该自己修改模板增减规则
    /** 关联记录ID `id` bigint(20) unsigned NOT NULL auto_increment COMMENT 关联记录ID */
    @NotNull(message = "关联记录ID不能为空", groups = Update.class)
    // @Max(value = Long.MAX_VALUE, message = "关联记录ID不能超过{max}", groups = Default.class)
    @Schema(description = "关联记录ID")
    private Long id;
    /** 系统用户ID `user_id` bigint(20) unsigned NOT NULL  COMMENT 系统用户ID */
    @NotNull(message = "系统用户ID不能为空", groups = Default.class)
    // @Max(value = Long.MAX_VALUE, message = "系统用户ID不能超过{max}", groups = Default.class)
    @Schema(description = "系统用户ID")
    private Long userId;
    /** 平台ID `platform_id` int(10) unsigned NOT NULL  COMMENT 平台ID */
    @NotNull(message = "平台ID不能为空", groups = Default.class)
    @Max(value = Integer.MAX_VALUE, message = "平台ID不能超过{value}", groups = Default.class)
    @Schema(description = "平台ID")
    private Integer platformId;
    /** 用户为该账号设置的昵称 (方便区分) `account_nickname` varchar(100)  COMMENT 用户为该账号设置的昵称 (方便区分) */
    @NotNull(message = "用户为该账号设置的昵称 (方便区分)不能为空", groups = Default.class)
    @Length(max = 100, message = "用户为该账号设置的昵称 (方便区分)不能超过{max}个字符", groups = Default.class)
    @Schema(description = "用户为该账号设置的昵称 (方便区分)")
    private String accountNickname;
    /** 在第三方平台的用户名/ID (可选, 根据平台) `platform_username` varchar(100)  COMMENT 在第三方平台的用户名/ID (可选, 根据平台) */
    @NotNull(message = "在第三方平台的用户名/ID (可选, 根据平台)不能为空", groups = Default.class)
    @Length(max = 100, message = "在第三方平台的用户名/ID (可选, 根据平台)不能超过{max}个字符", groups = Default.class)
    @Schema(description = "在第三方平台的用户名/ID (可选, 根据平台)")
    private String platformUsername;
    /** 认证凭证 (JSON格式存储，如 cookies, tokens, app_id/secret等，需加密处理) `credentials` text  COMMENT 认证凭证 (JSON格式存储，如 cookies, tokens, app_id/secret等，需加密处理) */
    @NotNull(message = "认证凭证 (JSON格式存储，如 cookies, tokens, app_id/secret等，需加密处理)不能为空", groups = Default.class)
    @Schema(description = "认证凭证 (JSON格式存储，如 cookies, tokens, app_id/secret等，需加密处理)")
    private String credentials;
    /** 账号状态 `status` enum('active','inactive','expired','error') NOT NULL DEFAULT inactive  COMMENT 账号状态 */
    @NotNull(message = "账号状态不能为空", groups = Default.class)
    @Length(max = 8, message = "账号状态不能超过{max}个字符", groups = Default.class)
    @Schema(description = "账号状态")
    private String status;
    /** 上次验证成功时间 `last_verified_at` timestamp  COMMENT 上次验证成功时间 */
    @NotNull(message = "上次验证成功时间不能为空", groups = Default.class)
    @Schema(description = "上次验证成功时间")
    private LocalDateTime lastVerifiedAt;
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
    public UserPlatformAccountsEntity convertToEntity() {
        UserPlatformAccountsEntity entity = new UserPlatformAccountsEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}