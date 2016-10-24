package com.simpletour.repository.test;

import com.simpletour.commons.data.repository.AbstractRepository;
import com.simpletour.domain.test.Player;

import java.util.List;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-08 上午11:33
 * @E-mail: wangcan@simpletour.com
 */
public interface IPlayerDao extends AbstractRepository<Player, Long> {

    List<Player> findByAge(int age);
}
