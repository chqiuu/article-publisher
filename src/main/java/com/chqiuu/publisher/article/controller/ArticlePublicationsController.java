package com.chqiuu.publisher.article.controller;

import java.util.List;
import java.util.Map;

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
import com.chqiuu.publisher.article.service.ArticlePublicationsService;
import com.chqiuu.publisher.article.entity.ArticlePublicationsEntity;



import com.chqiuu.publisher.article.vo.ArticlePublicationsInputVO;
import com.chqiuu.publisher.article.dto.ArticlePublicationsBriefDTO;
import com.chqiuu.publisher.article.dto.ArticlePublicationsDetailDTO;
import com.chqiuu.publisher.article.dto.ArticlePublicationsListDTO;
import com.chqiuu.publisher.article.query.ArticlePublicationsListQuery;
import com.chqiuu.publisher.article.query.ArticlePublicationsPageQuery;
import com.chqiuu.publisher.common.validator.group.Default;
import com.chqiuu.publisher.common.validator.group.Create;
import com.chqiuu.publisher.common.validator.group.Update;
import javax.validation.constraints.NotNull;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
/**
 * 文章在各平台的发布记录请求处理层
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Validated
@RestController
@RequestMapping("/article/articlePublications")
@Tag(name = "文章在各平台的发布记录")
@RequiredArgsConstructor
public class ArticlePublicationsController extends BaseController {

    private final ArticlePublicationsService articlePublicationsService;

    @Operation(summary = "根据唯一ID获取详细信息")
    @GetMapping("/detail/{id}")
    public Result<ArticlePublicationsDetailDTO> detail(@PathVariable("id") @NotNull(message = "唯一ID不能为空") Long id) {
        return Result.ok(articlePublicationsService.getDetailById(id));
    }

    @Operation(summary = "文章在各平台的发布记录列表查询")
    @GetMapping("/list")
    public Result<List<ArticlePublicationsListDTO>> list(ArticlePublicationsListQuery query) {
        return Result.ok(articlePublicationsService.getList(query));
    }

    @Operation(summary = "文章在各平台的发布记录分页查询")
    @GetMapping("/page")
    public Result<IPage<ArticlePublicationsListDTO>> page(ArticlePublicationsPageQuery query) {
        return Result.ok(articlePublicationsService.getPage(query));
    }

    @Operation(summary = "新建文章在各平台的发布记录，返回ID")
    @PostMapping("/add")
    public Result<Long> add(@Validated({Create.class}) @RequestBody ArticlePublicationsInputVO vo) {
        ArticlePublicationsEntity entity = vo.convertToEntity();
        articlePublicationsService.save(entity);
        return Result.ok(entity.getId());
    }

    @Operation(summary = "更新文章在各平台的发布记录")
    @PostMapping("/update")
    public Result<String> update(@Validated({Update.class}) @RequestBody ArticlePublicationsInputVO vo) {
        ArticlePublicationsBriefDTO briefDTO = articlePublicationsService.getBriefById(vo.getId());
        if (null == briefDTO) {
            return Result.failed(ResultEnum.NOT_FOUND, "没有找到需要更新的记录");
        }
        articlePublicationsService.updateById(vo.convertToEntity());
        return Result.ok();
    }

    @Operation(summary = "根据唯一ID删除文章在各平台的发布记录")
    @Parameters({
        @Parameter(name = "id",description = "发布记录ID",in = ParameterIn.PATH)
    })
    @PostMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable("id") @NotNull(message = "发布记录ID不能为空") Long id) {
        ArticlePublicationsBriefDTO briefDTO = articlePublicationsService.getBriefById(id);
        if (null == briefDTO) {
            return Result.failed(ResultEnum.NOT_FOUND, "没有找到需要删除的记录");
        }
        //TODO 其他限制删除条件
        return Result.ok(articlePublicationsService.removeById(id));
    }
}
