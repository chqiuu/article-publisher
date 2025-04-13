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
import com.chqiuu.publisher.article.service.ArticlesService;
import com.chqiuu.publisher.article.entity.ArticlesEntity;



import com.chqiuu.publisher.article.vo.ArticlesInputVO;
import com.chqiuu.publisher.article.dto.ArticlesBriefDTO;
import com.chqiuu.publisher.article.dto.ArticlesDetailDTO;
import com.chqiuu.publisher.article.dto.ArticlesListDTO;
import com.chqiuu.publisher.article.query.ArticlesListQuery;
import com.chqiuu.publisher.article.query.ArticlesPageQuery;
import com.chqiuu.publisher.common.validator.group.Default;
import com.chqiuu.publisher.common.validator.group.Create;
import com.chqiuu.publisher.common.validator.group.Update;
import javax.validation.constraints.NotNull;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
/**
 * 文章信息请求处理层
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Validated
@RestController
@RequestMapping("/article/articles")
@Tag(name = "文章信息")
@RequiredArgsConstructor
public class ArticlesController extends BaseController {

    private final ArticlesService articlesService;

    @Operation(summary = "根据唯一ID获取详细信息")
    @GetMapping("/detail/{id}")
    public Result<ArticlesDetailDTO> detail(@PathVariable("id") @NotNull(message = "唯一ID不能为空") Long id) {
        return Result.ok(articlesService.getDetailById(id));
    }

    @Operation(summary = "文章信息列表查询")
    @GetMapping("/list")
    public Result<List<ArticlesListDTO>> list(ArticlesListQuery query) {
        return Result.ok(articlesService.getList(query));
    }

    @Operation(summary = "文章信息分页查询")
    @GetMapping("/page")
    public Result<IPage<ArticlesListDTO>> page(ArticlesPageQuery query) {
        return Result.ok(articlesService.getPage(query));
    }

    @Operation(summary = "新建文章信息，返回ID")
    @PostMapping("/add")
    public Result<Long> add(@Validated({Create.class}) @RequestBody ArticlesInputVO vo) {
        ArticlesEntity entity = vo.convertToEntity();
        articlesService.save(entity);
        return Result.ok(entity.getId());
    }

    @Operation(summary = "更新文章信息")
    @PostMapping("/update")
    public Result<String> update(@Validated({Update.class}) @RequestBody ArticlesInputVO vo) {
        ArticlesBriefDTO briefDTO = articlesService.getBriefById(vo.getId());
        if (null == briefDTO) {
            return Result.failed(ResultEnum.NOT_FOUND, "没有找到需要更新的记录");
        }
        articlesService.updateById(vo.convertToEntity());
        return Result.ok();
    }

    @Operation(summary = "根据唯一ID删除文章信息")
    @Parameters({
        @Parameter(name = "id",description = "文章ID",in = ParameterIn.PATH)
    })
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") @NotNull(message = "文章ID不能为空") Long id) {
        ArticlesBriefDTO briefDTO = articlesService.getBriefById(id);
        if (null == briefDTO) {
            return Result.failed(ResultEnum.NOT_FOUND, "没有找到需要删除的记录");
        }
        //TODO 其他限制删除条件
        return Result.ok(articlesService.removeById(id));
    }
}
