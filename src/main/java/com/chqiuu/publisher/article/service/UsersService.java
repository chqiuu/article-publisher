package com.chqiuu.publisher.article.service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chqiuu.publisher.article.mapper.UsersMapper;
import com.chqiuu.publisher.article.dto.UsersBriefDTO;
import com.chqiuu.publisher.article.dto.UsersDetailDTO;
import com.chqiuu.publisher.article.dto.UsersListDTO;
import com.chqiuu.publisher.article.query.UsersListQuery;
import com.chqiuu.publisher.article.query.UsersPageQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.chqiuu.publisher.article.entity.UsersEntity;
import com.chqiuu.publisher.article.service.UsersService;

/**
 * 用户信息业务逻辑层实现
 *
 * @author chqiuu
 * @date 2025年4月12日
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UsersService extends ServiceImpl<UsersMapper, UsersEntity> implements IService<UsersEntity> {
    
    public UsersBriefDTO getBriefById(Long id) {
        return this.baseMapper.getBriefById(id);
    }

    
    public UsersDetailDTO getDetailById(Long id) {
        return this.baseMapper.getDetailById(id);
    }

    
    public List<UsersListDTO> getList(UsersListQuery query) {
        return this.baseMapper.getList(query);
    }

    
    public IPage<UsersListDTO> getPage(UsersPageQuery query) {
        Page<UsersListDTO> pageInfo = new Page<>(query.getCurrent(), query.getSize());
        return this.baseMapper.getPage(pageInfo, query);
    }
}