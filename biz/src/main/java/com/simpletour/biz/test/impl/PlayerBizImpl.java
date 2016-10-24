package com.simpletour.biz.test.impl;

import com.simpletour.biz.test.IPlayerBiz;
import com.simpletour.commons.data.exception.BaseSystemException;
import com.simpletour.commons.data.repository.DependentRepository;
import com.simpletour.domain.test.Player;
import com.simpletour.repository.test.IPlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-13 下午4:55
 * @E-mail: wangcan@simpletour.com
 */
@Component
public class PlayerBizImpl implements IPlayerBiz {

    @Autowired
    private DependentRepository<Player, Long> dependentRepository;

    @Autowired
    private IPlayerDao playerDao;

    @Override
    @Transactional
    public Player save(Player player) {
        Player dbPlayer = playerDao.save(player);
        dependentRepository.deleteDomainAllDependencies(dbPlayer);
        dependentRepository.buildDomainDependencies(dbPlayer);
        return player;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Player player = findOne(id);
        if (player == null) {
            throw new BaseSystemException("对象已经删除");
        }
        if (dependentRepository.isOtherDomainDependOnMe(player)) {
            throw new BaseSystemException("有对象依赖此对象");
        }
        dependentRepository.deleteDomainAllDependencies(player);
        playerDao.delete(player);
    }

    @Override
    public Player findOne(Long id) {
        return playerDao.findOne(id);
    }

    @Override
    public List<Player> findByAge(Integer age) {
        return playerDao.findByAge(age);
    }
}
