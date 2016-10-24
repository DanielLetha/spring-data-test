package com.simpletour.service.test;

import com.simpletour.domain.test.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-18 上午10:13
 * @E-mail: wangcan@simpletour.com
 */
public interface ITeamService {

    Optional<Team> add(Team team);

    Optional<Team> update(Team team);

    void delete(Long id);

    Optional<Team> findOne(Long id);

    List<Team> findAll(Specification<Team> spec, Sort sort);

    Page<Team> findAll(Specification<Team> spec, Pageable pageable);

    List<Team> findLikeName(String name);
}
