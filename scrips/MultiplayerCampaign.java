package mindustry.multiplayer;

import arc.struct.*;
import arc.util.io.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.maps.Map;
import mindustry.net.*;

public class MultiplayerCampaign {
    private Player hostPlayer;
    private Seq<Player> joinedPlayers;
    private Map campaignMap;
    private CampaignState campaignState;

    public MultiplayerCampaign(Player hostPlayer) {
        this.hostPlayer = hostPlayer;
        this.joinedPlayers = new Seq<>();
        this.campaignMap = null;
        this.campaignState = new CampaignState();
    }

    public void joinPlayer(Player player) {
        joinedPlayers.add(player);
        syncCampaignMap(player);
        syncCampaignState(player);
    }

    public void leavePlayer(Player player) {
        joinedPlayers.remove(player);
    }

    public void startCampaign(Map map) {
        this.campaignMap = map;
        for (Player player : joinedPlayers) {
            player.campaignMap = campaignMap;
            syncCampaignState(player); // Синхронизировать начальное состояние кампании
        }
    }

    public void syncCampaignMap(Player player) {
        if (campaignMap != null) {
            player.sendCampaignMap(campaignMap);
        }
    }

    public void syncCampaignState(Player player) {
        player.sendCampaignState(campaignState);
    }

    public CampaignState getCampaignState() {
        return campaignState;
    }

    // Класс для хранения состояния кампании
    public static class CampaignState {
        public int progress;
        public int resources;
        public Seq<Research> unlockedResearch;

        // Конструктор и геттеры/сеттеры
    }
}