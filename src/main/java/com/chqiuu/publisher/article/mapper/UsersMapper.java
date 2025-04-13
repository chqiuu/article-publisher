package com.chqiuu.publisher.article.mapper;
import com.chqiuu.publisher.article.entity.UsersEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chqiuu.publisher.article.dto.UsersBriefDTO;
import com.chqiuu.publisher.article.dto.UsersDetailDTO;
import com.chqiuu.publisher.article.dto.UsersListDTO;
import com.chqiuu.publisher.article.query.UsersListQuery;
import com.chqiuu.publisher.article.query.UsersPageQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
/**
 * 用户信息数据持久层
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Mapper
public interface UsersMapper extends BaseMapper<UsersEntity> {

    /**
    * 插入数据，如果中已经存在相同的记录，则忽略当前新数据
    *
    * @param entity 实体类对象
    * @return 影响条数
    */
    @Insert("<script>INSERT IGNORE INTO `users` <trim prefix='(' suffix=')' suffixOverrides=','><if test='id != null'>`id`, </if><if test='username != null'>`username`, </if><if test='passwordHash != null'>`password_hash`, </if><if test='email != null'>`email`, </if><if test='nickname != null'>`nickname`, </if><if test='createdAt != null'>`created_at`, </if><if test='updatedAt != null'>`updated_at`, </if></trim><trim prefix='values (' suffix=')' suffixOverrides=','><if test='id != null'>#{id}, </if><if test='username != null'>#{username}, </if><if test='passwordHash != null'>#{passwordHash}, </if><if test='email != null'>#{email}, </if><if test='nickname != null'>#{nickname}, </if><if test='createdAt != null'>#{createdAt}, </if><if test='updatedAt != null'>#{updatedAt}, </if></trim></script>")
    int insertIgnore(UsersEntity entity);

    /**
    * 替换数据，如果中已经存在相同的记录，则覆盖旧数据
    *
    * @param entity 实体类对象
    * @return 影响条数
    */
    @Insert("<script>REPLACE INTO `users` <trim prefix='(' suffix=')' suffixOverrides=','><if test='id != null'>`id`, </if><if test='username != null'>`username`, </if><if test='passwordHash != null'>`password_hash`, </if><if test='email != null'>`email`, </if><if test='nickname != null'>`nickname`, </if><if test='createdAt != null'>`created_at`, </if><if test='updatedAt != null'>`updated_at`, </if></trim><trim prefix='values (' suffix=')' suffixOverrides=','><if test='id != null'>#{id}, </if><if test='username != null'>#{username}, </if><if test='passwordHash != null'>#{passwordHash}, </if><if test='email != null'>#{email}, </if><if test='nickname != null'>#{nickname}, </if><if test='createdAt != null'>#{createdAt}, </if><if test='updatedAt != null'>#{updatedAt}, </if></trim></script>")
    int replace(UsersEntity entity);

    /**
    * 根据唯一ID获取简要信息
    *
    * @param id 用户ID
    * @return 简要信息
    */
    UsersBriefDTO getBriefById(@Param("id") Long id);

    /**
    * 根据唯一ID获取详细信息
    *
    * @param id 用户ID
    * @return 详细信息
    */
    UsersDetailDTO getDetailById(@Param("id") Long id);

    /**
    * 用户信息列表查询
    * @param query       查询对象
    * @return 用户信息列表
    */
    List<UsersListDTO> getList(@Param("query") UsersListQuery query);

    /**
    * 用户信息分页查询
    * @param pageInfo      分页控件
    * @param query       分页查询对象
    * @return 用户信息列表
    */
    IPage<UsersListDTO> getPage(@Param("pg") Page<UsersListDTO> pageInfo, @Param("query") UsersPageQuery query);
}
