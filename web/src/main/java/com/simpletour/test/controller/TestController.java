package com.simpletour.test.controller;

import com.simpletour.commons.data.util.ThreadLocalUtil;
import com.simpletour.domain.test.Gender;
import com.simpletour.domain.test.Player;
import com.simpletour.domain.test.Team;
import com.simpletour.service.test.IPlayerService;
import com.simpletour.service.test.ITeamService;
import com.simpletour.test.query.TeamQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * @Brief :
 * @Author: hawk
 * @Create: 2016-10-08 上午10:28
 * @E-mail: wangcan@simpletour.com
 */
@Controller
public class TestController {

    @Autowired
    private IPlayerService playerService;

    @Autowired
    private ITeamService teamService;

    private Long teamId;

    @ResponseBody
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        ThreadLocalUtil.setTenantId(1L);

//        addTeam();

//        teamId = 1L;
//        addPlayer();

//        deleteTeam();
//        findTeamById();
//        findTeamBySpecNotPage();
//        findTeamBySpecPage();

//        findPlayerByAge();

        findTeamByQuery();

        return "OK";
    }

    void addTeam() {
        Team team = new Team();
        team.setName("Hawk-Init");
        Optional<Team> teamOpt = teamService.add(team);
        if (teamOpt.isPresent()) {
            System.out.println(teamOpt.get().toString());
        }
    }

    void addPlayer() {
        Player player = new Player();
        player.setName("Hawk");
        player.setGender(Gender.male);
        player.setAge(25);

        Optional<Team> teamOpt = teamService.findOne(teamId);
        player.setTeam(teamOpt.get());
        Optional<Player> playerOpt = playerService.add(player);
        if (playerOpt.isPresent()) {
            System.out.println(playerOpt.get().toString());
        }
    }

    void deleteTeam() {
        teamService.delete(teamId);
    }

    void findTeamById() {
        Optional<Team> teamOptional = teamService.findOne(teamId);
        System.out.println(teamOptional.get().toString());
    }

    void findTeamBySpecNotPage() {
        TeamQuery teamQuery = new TeamQuery();
        teamQuery.setName("awk-");
        List<Team> teams = teamService.findAll(teamQuery.toSpec(), new Sort(new Sort.Order(Sort.Direction.DESC, "id")));
        System.out.println("findTeamBySpecNotPage result size = " + teams.size());
    }

    void findTeamBySpecPage() {
        TeamQuery teamQuery = new TeamQuery();
        teamQuery.setName("awk-");
        Page<Team> teamPage = teamService.findAll(teamQuery.toSpec(), new PageRequest(0, 5, new Sort(new Sort.Order(Sort.Direction.DESC, "id"))));
        System.out.println("findTeamBySpecPage result size = " + teamPage.getNumberOfElements());
    }

    void findPlayerByAge() {
        List<Player> players = playerService.findByAge(25);
        if (!(players == null || players.isEmpty())) {
            System.out.println("findPlayerByAge result size = " + players.size());
        }
    }

    void findTeamByQuery() {
        List<Team> teams = teamService.findLikeName("awk-");
        if (!(teams == null || teams.isEmpty())) {
            System.out.println("findTeamByQuery result size = " + teams.size());
        }
    }
}
