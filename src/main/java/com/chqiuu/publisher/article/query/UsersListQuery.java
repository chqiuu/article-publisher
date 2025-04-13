package com.chqiuu.publisher.article.query;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * 用户信息列表查询对象
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Data
@Schema(description = "用户信息列表查询对象")
public class UsersListQuery implements Serializable{

    private static final long serialVersionUID = 1L;
    /** 排序参数 */
    @Schema(description = "排序参数")
    private String sortParam;
    /** 排序方式：正序asc，倒序desc，默认为desc */
    @Schema(description = "排序方式：正序asc，倒序desc，默认为desc")
    private String sortord;

//TODO 当您看到这个后您应该自己修改模板增减规则
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