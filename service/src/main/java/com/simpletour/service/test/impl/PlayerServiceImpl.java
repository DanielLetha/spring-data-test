package com.simpletour.service.test.impl;

import com.simpletour.biz.test.IPlayerBiz;
import com.simpletour.domain.test.Player;
import com.simpletour.service.test.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PlayerServiceImpl implements IPlayerService {

    @Autowired
    private IPlayerBiz playerBiz;

    @Override
    public Optional<Player> add(Player player) {
        return Optional.ofNullable(playerBiz.save(player));
    }

    @Override
    public Optional<Player> update(Player player) {
        return Optional.ofNullable(playerBiz.save(player));
    }

    @Override
    public void delete(Long id) {
        playerBiz.delete(id);
    }

    @Override
    public List<Player> findByAge(Integer age) {
        return playerBiz.findByAge(age);
    }
}
