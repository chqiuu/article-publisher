package com.chqiuu.publisher.article.controller;

import java.util.List;
import java.util.Map;

import com.chqiuu.publisher.common.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.chqiuu.publisher.common.base.BaseController;
import com.chqiuu.publisher.common.domain.Result;
import com.chqiuu.publisher.common.domain.ResultEnum;
import com.chqiuu.publisher.article.service.UserPlatformAccountsService;
import com.chqiuu.publisher.article.entity.UserPlatformAccountsEntity;



import com.chqiuu.publisher.article.vo.UserPlatformAccountsInputVO;
import com.chqiuu.publisher.article.dto.UserPlatformAccountsBriefDTO;
import com.chqiuu.publisher.article.dto.UserPlatformAccountsDetailDTO;
import com.chqiuu.publisher.article.dto.UserPlatformAccountsListDTO;
import com.chqiuu.publisher.article.query.UserPlatformAccountsListQuery;
import com.chqiuu.publisher.article.query.UserPlatformAccountsPageQuery;
import com.chqiuu.publisher.common.validator.group.Default;
import com.chqiuu.publisher.common.validator.group.Create;
import com.chqiuu.publisher.common.validator.group.Update;
import javax.validation.constraints.NotNull;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
/**
 * 用户绑定的平台账号信息请求处理层
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Validated
@RestController
@RequestMapping("/article/userPlatformAccounts")
@Tag(name = "用户绑定的平台账号信息")
@RequiredArgsConstructor
public class UserPlatformAccountsController extends BaseController {

    private final UserPlatformAccountsService userPlatformAccountsService;

    @Operation(summary = "根据唯一ID获取详细信息")
    @GetMapping("/detail/{id}")
    public Result<UserPlatformAccountsDetailDTO> detail(@PathVariable("id") @NotNull(message = "唯一ID不能为空") Long id) {
        return Result.ok(userPlatformAccountsService.getDetailById(id));
    }

    @Operation(summary = "用户绑定的平台账号信息列表查询")
    @GetMapping("/list")
    public Result<List<UserPlatformAccountsListDTO>> list(UserPlatformAccountsListQuery query) {
        return Result.ok(userPlatformAccountsService.getList(query));
    }

    @Operation(summary = "用户绑定的平台账号信息分页查询")
    @GetMapping("/page")
    public Result<IPage<UserPlatformAccountsListDTO>> page(UserPlatformAccountsPageQuery query) {
        return Result.ok(userPlatformAccountsService.getPage(query));
    }

    @Operation(summary = "新建用户绑定的平台账号信息，返回ID")
    @PostMapping("/add")
    public Result<Long> add(@Validated({Create.class}) @RequestBody UserPlatformAccountsInputVO vo) {
        UserPlatformAccountsEntity entity = vo.convertToEntity();
        userPlatformAccountsService.save(entity);
        return Result.ok(entity.getId());
    }

    @Operation(summary = "更新用户绑定的平台账号信息")
    @PostMapping("/update")
    public Result<String> update(@Validated({Update.class}) @RequestBody UserPlatformAccountsInputVO vo) {
        UserPlatformAccountsBriefDTO briefDTO = userPlatformAccountsService.getBriefById(vo.getId());
        if (null == briefDTO) {
            return Result.failed(ResultEnum.NOT_FOUND, "没有找到需要更新的记录");
        }
        userPlatformAccountsService.updateById(vo.convertToEntity());
        return Result.ok();
    }

    @Operation(summary = "根据唯一ID删除用户绑定的平台账号信息")
    @Parameters({
        @Parameter(name = "id",description = "关联记录ID",in = ParameterIn.PATH)
    })
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") @NotNull(message = "关联记录ID不能为空") Long id) {
        UserPlatformAccountsBriefDTO briefDTO = userPlatformAccountsService.getBriefById(id);
        if (null == briefDTO) {
            return Result.failed(ResultEnum.NOT_FOUND, "没有找到需要删除的记录");
        }
        //TODO 其他限制删除条件
        return Result.ok(userPlatformAccountsService.removeById(id));
    }
}
