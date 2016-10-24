package com.simpletour.repository.test.mng;

import com.simpletour.commons.data.repository.BaseMultenantableRepository;
import com.simpletour.domain.test.Team;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-08 下午2:16
 * @E-mail: wangcan@simpletour.com
 */
public interface ITeamDao extends BaseMultenantableRepository<Team, Long> {

    @Query("select u from #{#entityName} u where u.name like %?1%")
    List<Team> findNameLike(String name);
}
