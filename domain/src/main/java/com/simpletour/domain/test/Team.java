package com.simpletour.domain.test;

import com.simpletour.commons.data.domain.DependKey;
import com.simpletour.commons.data.domain.support.BaseMultenantPseudoDeletableRevisionDomain;
import com.simpletour.commons.data.domain.support.Diff;
import com.simpletour.commons.data.domain.support.DiffEntity;
import com.simpletour.commons.data.domain.support.EntityKey;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Brief : com.simpletour.test.domain.Team
 * Author: Hawk
 * Date  : 2016/1/13
 */

@Entity
@Table(name = "team")
@DependKey("public.team")
@EntityListeners(AuditingEntityListener.class)
public class Team extends BaseMultenantPseudoDeletableRevisionDomain<Team, Long> {

    @Column
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.DETACH)
    private List<Player> players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }


    @Override
    public Diff diff(Operate operate, Team before) {
        Diff diff = new Diff();
        List<DiffEntity> diffEntities = new ArrayList<>();
        if (Operate.UPDATE.equals(operate)) {
            if (name != null && !name.equals(before.getName())) {
                DiffEntity nameRev = new DiffEntity();
                nameRev.setProperty("name");
                nameRev.setOrigin(before.getName());
                nameRev.setCurrent(name);
                diffEntities.add(nameRev);
            }
        }
        diff.setEntity(EntityKey.getTableName(this.getClass()));
        diff.setEntityId(getId());
        diff.setDiffEntities(diffEntities);
        diff.setOperate(operate);
        return diff;
    }
}
