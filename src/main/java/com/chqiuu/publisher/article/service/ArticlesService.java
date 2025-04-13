package com.chqiuu.publisher.article.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chqiuu.publisher.article.mapper.ArticlesMapper;
import com.chqiuu.publisher.article.dto.ArticlesBriefDTO;
import com.chqiuu.publisher.article.dto.ArticlesDetailDTO;
import com.chqiuu.publisher.article.dto.ArticlesListDTO;
import com.chqiuu.publisher.article.query.ArticlesListQuery;
import com.chqiuu.publisher.article.query.ArticlesPageQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.chqiuu.publisher.article.entity.ArticlesEntity;
import com.chqiuu.publisher.article.service.ArticlesService;

/**
 * 文章信息业务逻辑层实现
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticlesService extends ServiceImpl<ArticlesMapper, ArticlesEntity> implements IService<ArticlesEntity> {
    
    public ArticlesBriefDTO getBriefById(Long id) {
        return this.baseMapper.getBriefById(id);
    }

    
    public ArticlesDetailDTO getDetailById(Long id) {
        return this.baseMapper.getDetailById(id);
    }

    
    public List<ArticlesListDTO> getList(ArticlesListQuery query) {
        return this.baseMapper.getList(query);
    }

    
    public IPage<ArticlesListDTO> getPage(ArticlesPageQuery query) {
        Page<ArticlesListDTO> pageInfo = new Page<>(query.getCurrent(), query.getSize());
        return this.baseMapper.getPage(pageInfo, query);
    }
}