package com.simpletour.biz.test;


import com.simpletour.domain.test.Player;

import java.util.List;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-13 下午4:54
 * @E-mail: wangcan@simpletour.com
 */
public interface IPlayerBiz {

    Player save(Player player);

    void delete(Long id);

    Player findOne(Long id);

    List<Player> findByAge(Integer age);
}
