package com.simpletour.service.test.impl;

import com.simpletour.biz.test.ITeamBiz;
import com.simpletour.domain.test.Team;
import com.simpletour.service.test.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-18 上午10:14
 * @E-mail: wangcan@simpletour.com
 */
@Service
public class TeamServiceImpl implements ITeamService {

    @Autowired
    private ITeamBiz teamBiz;

    @Override
    public Optional<Team> add(Team team) {
        return Optional.ofNullable(teamBiz.save(team));
    }

    @Override
    public Optional<Team> update(Team team) {
        return Optional.ofNullable(teamBiz.save(team));
    }

    @Override
    public void delete(Long id) {
        teamBiz.delete(id);
    }

    @Override
    public Optional<Team> findOne(Long id) {
        return Optional.ofNullable(teamBiz.findOne(id));
    }

    @Override
    public List<Team> findAll(Specification<Team> spec, Sort sort) {
        return teamBiz.findAll(spec, sort);
    }

    @Override
    public Page<Team> findAll(Specification<Team> spec, Pageable pageable) {
        return teamBiz.findAll(spec, pageable);
    }

    @Override
    public List<Team> findLikeName(String name) {
        return teamBiz.findByNameLike(name);
    }
}
