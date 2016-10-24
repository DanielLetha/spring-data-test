package com.simpletour.biz.test;

import com.simpletour.domain.test.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-13 上午11:50
 * @E-mail: wangcan@simpletour.com
 */
public interface ITeamBiz {

    Team findOne(Long id);

    Team save(Team team);

    void delete(Long id);

    Team findOneWithoutDeleted(Long id);

    List<Team> findAll();

    Page<Team> findAll(Pageable pageable);

    List<Team> findAll(Sort sort);

    List<Team> findAll(Specification<Team> spec, Sort sort);

    Page<Team> findAll(Specification<Team> spec, Pageable pageable);

    List<Team> findByNameLike(String name);
}
