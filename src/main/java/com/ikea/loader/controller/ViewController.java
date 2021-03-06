package com.ikea.loader.controller;

import com.ikea.loader.model.Admin;
import com.ikea.loader.model.Competition;
import com.ikea.loader.model.Player;
import com.ikea.loader.service.main.MainService;
import com.ikea.loader.service.storage.DataStorage;
import com.ikea.loader.service.validation.ValidationFormService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class ViewController {

    private final MainService mainService;
    private final DataStorage dataStorage;
    private final ValidationFormService validationFormService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute Admin admin) {
        return "login";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String showPlayerForm(Player player) {
        return "form";
    }

    @RequestMapping(value = "/formCompetition", method = RequestMethod.GET)
    public String showCompetitionForm(Competition competition) {
        return "formCompetition";
    }

    @RequestMapping(value = "/formValidate", method = RequestMethod.POST)
    public String checkPlayerForm(Player player, Model model) {
        String page = validationFormService.validatePlayerAndReturnPage(player, model);
        if (page.equals("result")) mainService.processPlayerData(player);
        return page;
    }

    @RequestMapping(value = "/formValidateCompetition", method = RequestMethod.POST)
    public String checkCompetitionForm(Competition competition, Model model) {
        String page = validationFormService.validateCompetitionAndReturnPage(competition, model);
        if (page.equals("resultCompetition")) mainService.processCompetitionData(competition);
        return page;
    }

    @RequestMapping(value = "/formData", method = RequestMethod.GET)
    public String showPlayerDataForm(Model model) {
        List<Player> playerList = dataStorage.getAllDataPlayer();
        model.addAttribute("playerList", playerList);
        return "formData";
    }

    @RequestMapping(value = "/formDataCompetition", method = RequestMethod.GET)
    public String showCompetitionDataForm(Model model) {
        List<Competition> competitionList = dataStorage.getAllDataCompetition();
        model.addAttribute("competitionList", competitionList);
        return "formDataCompetition";
    }
}

