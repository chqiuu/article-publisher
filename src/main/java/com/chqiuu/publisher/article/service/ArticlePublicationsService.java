package com.chqiuu.publisher.article.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chqiuu.publisher.article.mapper.ArticlePublicationsMapper;
import com.chqiuu.publisher.article.dto.ArticlePublicationsBriefDTO;
import com.chqiuu.publisher.article.dto.ArticlePublicationsDetailDTO;
import com.chqiuu.publisher.article.dto.ArticlePublicationsListDTO;
import com.chqiuu.publisher.article.query.ArticlePublicationsListQuery;
import com.chqiuu.publisher.article.query.ArticlePublicationsPageQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.chqiuu.publisher.article.entity.ArticlePublicationsEntity;
import com.chqiuu.publisher.article.service.ArticlePublicationsService;

/**
 * 文章在各平台的发布记录业务逻辑层实现
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ArticlePublicationsService extends ServiceImpl<ArticlePublicationsMapper, ArticlePublicationsEntity> implements IService<ArticlePublicationsEntity> {
    
    public ArticlePublicationsBriefDTO getBriefById(Long id) {
        return this.baseMapper.getBriefById(id);
    }

    
    public ArticlePublicationsDetailDTO getDetailById(Long id) {
        return this.baseMapper.getDetailById(id);
    }

    
    public List<ArticlePublicationsListDTO> getList(ArticlePublicationsListQuery query) {
        return this.baseMapper.getList(query);
    }

    
    public IPage<ArticlePublicationsListDTO> getPage(ArticlePublicationsPageQuery query) {
        Page<ArticlePublicationsListDTO> pageInfo = new Page<>(query.getCurrent(), query.getSize());
        return this.baseMapper.getPage(pageInfo, query);
    }
}