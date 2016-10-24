package com.simpletour.domain.test;

import com.simpletour.commons.data.domain.DependKey;
import com.simpletour.commons.data.domain.support.BaseDomain;
import com.simpletour.commons.data.domain.support.Dependency;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Brief : com.simpletour.test.domain.Player
 * Author: Hawk
 * Date  : 2016/1/13
 */
@Entity
@Table(name = "player")
@DependKey("public.player")
@EntityListeners(AuditingEntityListener.class)
public class Player extends BaseDomain<Long> {

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public List<Dependency> getDependencies() {
        if (team == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(new Dependency(team.getEntityKey()));
    }
}
