package com.chqiuu.publisher.article.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chqiuu.publisher.article.mapper.PlatformsMapper;
import com.chqiuu.publisher.article.dto.PlatformsBriefDTO;
import com.chqiuu.publisher.article.dto.PlatformsDetailDTO;
import com.chqiuu.publisher.article.dto.PlatformsListDTO;
import com.chqiuu.publisher.article.query.PlatformsListQuery;
import com.chqiuu.publisher.article.query.PlatformsPageQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.chqiuu.publisher.article.entity.PlatformsEntity;
import com.chqiuu.publisher.article.service.PlatformsService;

/**
 * 支持的平台信息业务逻辑层实现
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PlatformsService extends ServiceImpl<PlatformsMapper, PlatformsEntity> implements IService<PlatformsEntity> {
    
    public PlatformsBriefDTO getBriefById(Integer id) {
        return this.baseMapper.getBriefById(id);
    }

    
    public PlatformsDetailDTO getDetailById(Integer id) {
        return this.baseMapper.getDetailById(id);
    }

    
    public List<PlatformsListDTO> getList(PlatformsListQuery query) {
        return this.baseMapper.getList(query);
    }

    
    public IPage<PlatformsListDTO> getPage(PlatformsPageQuery query) {
        Page<PlatformsListDTO> pageInfo = new Page<>(query.getCurrent(), query.getSize());
        return this.baseMapper.getPage(pageInfo, query);
    }
}