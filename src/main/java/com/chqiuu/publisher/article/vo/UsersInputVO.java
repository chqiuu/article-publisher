package com.chqiuu.publisher.article.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import com.chqiuu.publisher.article.entity.UsersEntity;

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
 * 用户信息录入信息
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Data
@Schema(description = "用户信息录入信息")
public class UsersInputVO implements Serializable{

    private static final long serialVersionUID = 1L;
    //TODO 当您看到这个后您应该自己修改模板增减规则
    /** 用户ID `id` bigint(20) unsigned NOT NULL auto_increment COMMENT 用户ID */
    @NotNull(message = "用户ID不能为空", groups = Update.class)
    // @Max(value = Long.MAX_VALUE, message = "用户ID不能超过{max}", groups = Default.class)
    @Schema(description = "用户ID")
    private Long id;
    /** 用户名 `username` varchar(50) NOT NULL  COMMENT 用户名 */
    @NotNull(message = "用户名不能为空", groups = Default.class)
    @Length(max = 50, message = "用户名不能超过{max}个字符", groups = Default.class)
    @Schema(description = "用户名")
    private String username;
    /** 加密后的密码 `password_hash` varchar(255) NOT NULL  COMMENT 加密后的密码 */
    @NotNull(message = "加密后的密码不能为空", groups = Default.class)
    @Length(max = 255, message = "加密后的密码不能超过{max}个字符", groups = Default.class)
    @Schema(description = "加密后的密码")
    private String passwordHash;
    /** 邮箱 `email` varchar(100) NOT NULL  COMMENT 邮箱 */
    @NotNull(message = "邮箱不能为空", groups = Default.class)
    @Length(max = 100, message = "邮箱不能超过{max}个字符", groups = Default.class)
    @Schema(description = "邮箱")
    private String email;
    /** 昵称 `nickname` varchar(50)  COMMENT 昵称 */
    @NotNull(message = "昵称不能为空", groups = Default.class)
    @Length(max = 50, message = "昵称不能超过{max}个字符", groups = Default.class)
    @Schema(description = "昵称")
    private String nickname;
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
    public UsersEntity convertToEntity() {
        UsersEntity entity = new UsersEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}