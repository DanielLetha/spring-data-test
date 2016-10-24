package com.simpletour.biz.test.impl;

import com.simpletour.biz.test.ITeamBiz;
import com.simpletour.commons.data.domain.Revisiable;
import com.simpletour.commons.data.domain.support.Diff;
import com.simpletour.commons.data.exception.BaseSystemException;
import com.simpletour.commons.data.repository.DependentRepository;
import com.simpletour.commons.data.repository.PseudoDeleteRepository;
import com.simpletour.commons.data.repository.RevisionRepository;
import com.simpletour.commons.data.util.ThreadLocalUtil;
import com.simpletour.domain.test.Team;
import com.simpletour.domain.test.Team_;
import com.simpletour.repository.test.mng.ITeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-13 上午11:50
 * @E-mail: wangcan@simpletour.com
 */
@Component
public class TeamBizImpl implements ITeamBiz {

    @Autowired
    private ITeamDao teamDao;

    @Autowired
    private DependentRepository<Team, Long> dependentRepository;

    @Autowired
    private RevisionRepository<Team, Long> revisionRepository;

    @Autowired
    @Qualifier("MultenantPseudoDeleteRepository")
    private PseudoDeleteRepository<Team, Long> deleteRepository;

    @Override
    public Team findOne(Long id) {
        return deleteRepository.findOneWithoutDeleted(Team.class, id);
    }

    @Override
    @Transactional
    public Team save(Team team) {
        Team db;
        if (team.getId() == null) {
            db = teamDao.save(team);
            dependentRepository.buildDomainDependencies(team);
        } else {
            Team dbTeam = teamDao.findOne(team.getId());
            Assert.notNull(dbTeam);
            Diff diff = team.diff(Revisiable.Operate.UPDATE, dbTeam);
            revisionRepository.addDiff(diff);
            dependentRepository.deleteDomainAllDependencies(team);
            db = teamDao.save(team);
            dependentRepository.buildDomainDependencies(team);
        }
        return db;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Team team = findOne(id);
        if (team == null) {
            throw new BaseSystemException("对象已经删除");
        }
        if (dependentRepository.isOtherDomainDependOnMe(team)) {
            throw new BaseSystemException("有对象依赖此对象");
        }
        team.setDel(Boolean.TRUE);
        Team dbTeam = teamDao.save(team);
        Diff diff = dbTeam.diff(Revisiable.Operate.DELETE, null);
        revisionRepository.addDiff(diff);
        dependentRepository.deleteDomainAllDependencies(dbTeam);
    }

    @Override
    public Team findOneWithoutDeleted(Long id) {
        return deleteRepository.findOneWithoutDeleted(Team.class, id);
    }

    @Override
    public List<Team> findAll() {
        return deleteRepository.findAllWithoutDeleted(Team.class);
    }

    @Override
    public Page<Team> findAll(Pageable pageable) {
        return deleteRepository.findAllWithoutDeleted(Team.class, pageable);
    }

    @Override
    public List<Team> findAll(Sort sort) {
        return deleteRepository.findAllWithoutDeleted(Team.class, sort);
    }

    @Override
    public List<Team> findAll(Specification<Team> spec, Sort sort) {
        Specification<Team> newSpec = new Specification<Team>() {
            @Override
            public Predicate toPredicate(Root<Team> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate prePre = spec.toPredicate(root, query, cb);
                Predicate delPre = cb.equal(root.get(Team_.del), Boolean.FALSE);
                Predicate tenantPre = cb.equal(root.get(Team_.companyId), ThreadLocalUtil.getTenantId());
                return cb.and(prePre, delPre, tenantPre);
            }
        };
        return teamDao.findAll(newSpec, sort);
    }

    @Override
    public Page<Team> findAll(Specification<Team> spec, Pageable pageable) {
        Specification<Team> newSpec = new Specification<Team>() {
            @Override
            public Predicate toPredicate(Root<Team> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate prePre = spec.toPredicate(root, query, cb);
                Predicate delPre = cb.equal(root.get(Team_.del), Boolean.FALSE);
                Predicate tenantPre = cb.equal(root.get(Team_.companyId), ThreadLocalUtil.getTenantId());
                return cb.and(prePre, delPre, tenantPre);
            }
        };
        return teamDao.findAll(newSpec, pageable);
    }

    @Override
    public List<Team> findByNameLike(String name) {
        return teamDao.findNameLike(name);
    }
}
