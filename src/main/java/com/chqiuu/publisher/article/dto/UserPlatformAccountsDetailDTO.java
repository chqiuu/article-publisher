package com.chqiuu.publisher.article.dto;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;


import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 用户绑定的平台账号信息详细信息
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Data
@Schema(description = "用户绑定的平台账号信息详细信息")
public class UserPlatformAccountsDetailDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    //TODO 当您看到这个后您应该自己修改模板增减字段
    /** 关联记录ID `id` bigint(20) unsigned NOT NULL auto_increment COMMENT 关联记录ID */
    @Schema(description = "关联记录ID")

    private Long id;
    /** 系统用户ID `user_id` bigint(20) unsigned NOT NULL  COMMENT 系统用户ID */
    @Schema(description = "系统用户ID")

    private Long userId;
    /** 平台ID `platform_id` int(10) unsigned NOT NULL  COMMENT 平台ID */
    @Schema(description = "平台ID")

    private Integer platformId;
    /** 用户为该账号设置的昵称 (方便区分) `account_nickname` varchar(100)  COMMENT 用户为该账号设置的昵称 (方便区分) */
    @Schema(description = "用户为该账号设置的昵称 (方便区分)")

    private String accountNickname;
    /** 在第三方平台的用户名/ID (可选, 根据平台) `platform_username` varchar(100)  COMMENT 在第三方平台的用户名/ID (可选, 根据平台) */
    @Schema(description = "在第三方平台的用户名/ID (可选, 根据平台)")

    private String platformUsername;
    /** 认证凭证 (JSON格式存储，如 cookies, tokens, app_id/secret等，需加密处理) `credentials` text  COMMENT 认证凭证 (JSON格式存储，如 cookies, tokens, app_id/secret等，需加密处理) */
    @Schema(description = "认证凭证 (JSON格式存储，如 cookies, tokens, app_id/secret等，需加密处理)")

    private String credentials;
    /** 账号状态 `status` enum('active','inactive','expired','error') NOT NULL DEFAULT inactive  COMMENT 账号状态 */
    @Schema(description = "账号状态")

    private String status;
    /** 上次验证成功时间 `last_verified_at` timestamp  COMMENT 上次验证成功时间 */
    @Schema(description = "上次验证成功时间")

    private LocalDateTime lastVerifiedAt;
    /** 创建时间 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 创建时间 */
    @Schema(description = "创建时间")

    private LocalDateTime createdAt;
    /** 更新时间 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT 更新时间 */
    @Schema(description = "更新时间")

    private LocalDateTime updatedAt;
}