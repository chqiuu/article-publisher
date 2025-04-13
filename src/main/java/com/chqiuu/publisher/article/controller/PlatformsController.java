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
import com.chqiuu.publisher.article.service.PlatformsService;
import com.chqiuu.publisher.article.entity.PlatformsEntity;



import com.chqiuu.publisher.article.vo.PlatformsInputVO;
import com.chqiuu.publisher.article.dto.PlatformsBriefDTO;
import com.chqiuu.publisher.article.dto.PlatformsDetailDTO;
import com.chqiuu.publisher.article.dto.PlatformsListDTO;
import com.chqiuu.publisher.article.query.PlatformsListQuery;
import com.chqiuu.publisher.article.query.PlatformsPageQuery;
import com.chqiuu.publisher.common.validator.group.Default;
import com.chqiuu.publisher.common.validator.group.Create;
import com.chqiuu.publisher.common.validator.group.Update;
import javax.validation.constraints.NotNull;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
/**
 * 支持的平台信息请求处理层
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Validated
@RestController
@RequestMapping("/article/platforms")
@Tag(name = "支持的平台信息")
@RequiredArgsConstructor
public class PlatformsController extends BaseController {

    private final PlatformsService platformsService;

    @Operation(summary = "根据唯一ID获取详细信息")
    @GetMapping("/detail/{id}")
    public Result<PlatformsDetailDTO> detail(@PathVariable("id") @NotNull(message = "唯一ID不能为空") Integer id) {
        return Result.ok(platformsService.getDetailById(id));
    }

    @Operation(summary = "支持的平台信息列表查询")
    @GetMapping("/list")
    public Result<List<PlatformsListDTO>> list(PlatformsListQuery query) {
        return Result.ok(platformsService.getList(query));
    }

    @Operation(summary = "支持的平台信息分页查询")
    @GetMapping("/page")
    public Result<IPage<PlatformsListDTO>> page(PlatformsPageQuery query) {
        return Result.ok(platformsService.getPage(query));
    }

    @Operation(summary = "新建支持的平台信息，返回ID")
    @PostMapping("/add")
    public Result<Integer> add(@Validated({Create.class}) @RequestBody PlatformsInputVO vo) {
        PlatformsEntity entity = vo.convertToEntity();
        platformsService.save(entity);
        return Result.ok(entity.getId());
    }

    @Operation(summary = "更新支持的平台信息")
    @PostMapping("/update")
    public Result<String> update(@Validated({Update.class}) @RequestBody PlatformsInputVO vo) {
        PlatformsBriefDTO briefDTO = platformsService.getBriefById(vo.getId());
        if (null == briefDTO) {
            return Result.failed(ResultEnum.NOT_FOUND, "没有找到需要更新的记录");
        }
        platformsService.updateById(vo.convertToEntity());
        return Result.ok();
    }

    @Operation(summary = "根据唯一ID删除支持的平台信息")
    @Parameters({
        @Parameter(name = "id",description = "平台ID",in = ParameterIn.PATH)
    })
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") @NotNull(message = "平台ID不能为空") Integer id) {
        PlatformsBriefDTO briefDTO = platformsService.getBriefById(id);
        if (null == briefDTO) {
            return Result.failed(ResultEnum.NOT_FOUND, "没有找到需要删除的记录");
        }
        //TODO 其他限制删除条件
        return Result.ok(platformsService.removeById(id));
    }
}
