package com.chqiuu.publisher.article.entity;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 用户信息
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Data
@Schema(description = "用户信息实体类")
@EqualsAndHashCode(callSuper = true)
@TableName("users")
public class UsersEntity extends Model<UsersEntity> {

    private static final long serialVersionUID = 1L;

    /** 用户ID `id` bigint(20) unsigned NOT NULL auto_increment COMMENT 用户ID */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Schema(description = "用户ID")

    private Long id;
    /** 用户名 `username` varchar(50) NOT NULL  COMMENT 用户名 */
    @Schema(description = "用户名")

    private String username;
    /** 加密后的密码 `password_hash` varchar(255) NOT NULL  COMMENT 加密后的密码 */
    @Schema(description = "加密后的密码")

    private String passwordHash;
    /** 邮箱 `email` varchar(100) NOT NULL  COMMENT 邮箱 */
    @Schema(description = "邮箱")

    private String email;
    /** 昵称 `nickname` varchar(50)  COMMENT 昵称 */
    @Schema(description = "昵称")

    private String nickname;
    /** 创建时间 `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT 创建时间 */
    @Schema(description = "创建时间")

    private LocalDateTime createdAt;
    /** 更新时间 `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT 更新时间 */
    @Schema(description = "更新时间")

    private LocalDateTime updatedAt;
}