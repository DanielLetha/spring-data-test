package com.simpletour.test.query;

import com.simpletour.commons.data.query.BaseQuery;
import com.simpletour.domain.test.Team;
import com.simpletour.domain.test.Team_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-18 上午11:37
 * @E-mail: wangcan@simpletour.com
 */
public class TeamQuery extends BaseQuery<Team> {

    private String name;

    @Override
    public Specification<Team> toSpec() {
        Specification<Team> spec = new Specification<Team>() {
            @Override
            public Predicate toPredicate(Root<Team> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get(Team_.name), "%" + name + "%");
            }
        };
        return spec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
