package com.simpletour.service.test;

import com.simpletour.domain.test.Player;

import java.util.List;
import java.util.Optional;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-18 上午10:13
 * @E-mail: wangcan@simpletour.com
 */
public interface IPlayerService {

    Optional<Player> add(Player player);

    Optional<Player> update(Player player);

    void delete(Long id);

    List<Player> findByAge(Integer age);
}
