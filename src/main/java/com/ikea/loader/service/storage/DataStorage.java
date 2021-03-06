package com.ikea.loader.service.storage;

import com.ikea.loader.model.Competition;
import com.ikea.loader.model.Player;

import java.util.List;

public interface DataStorage {

    Player getData(String id);

    List<Player> getAllDataPlayer();

    List<Competition> getAllDataCompetition();
}
