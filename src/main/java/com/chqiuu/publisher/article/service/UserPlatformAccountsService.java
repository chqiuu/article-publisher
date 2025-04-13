package com.chqiuu.publisher.article.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chqiuu.publisher.article.mapper.UserPlatformAccountsMapper;
import com.chqiuu.publisher.article.dto.UserPlatformAccountsBriefDTO;
import com.chqiuu.publisher.article.dto.UserPlatformAccountsDetailDTO;
import com.chqiuu.publisher.article.dto.UserPlatformAccountsListDTO;
import com.chqiuu.publisher.article.query.UserPlatformAccountsListQuery;
import com.chqiuu.publisher.article.query.UserPlatformAccountsPageQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.chqiuu.publisher.article.entity.UserPlatformAccountsEntity;
import com.chqiuu.publisher.article.service.UserPlatformAccountsService;

/**
 * 用户绑定的平台账号信息业务逻辑层实现
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserPlatformAccountsService extends ServiceImpl<UserPlatformAccountsMapper, UserPlatformAccountsEntity> implements IService<UserPlatformAccountsEntity> {
    
    public UserPlatformAccountsBriefDTO getBriefById(Long id) {
        return this.baseMapper.getBriefById(id);
    }

    
    public UserPlatformAccountsDetailDTO getDetailById(Long id) {
        return this.baseMapper.getDetailById(id);
    }

    
    public List<UserPlatformAccountsListDTO> getList(UserPlatformAccountsListQuery query) {
        return this.baseMapper.getList(query);
    }

    
    public IPage<UserPlatformAccountsListDTO> getPage(UserPlatformAccountsPageQuery query) {
        Page<UserPlatformAccountsListDTO> pageInfo = new Page<>(query.getCurrent(), query.getSize());
        return this.baseMapper.getPage(pageInfo, query);
    }
}