package mindustry.multiplayer;

import arc.struct.*;
import mindustry.multiplayer.*;

public class MultiplayerCampaignManager {
    private static MultiplayerCampaignManager instance;
    private ObjectMap<Player, MultiplayerCampaign> campaigns;

    public MultiplayerCampaignManager() {
        this.campaigns = new ObjectMap<>();
    }

    public static MultiplayerCampaignManager get() {
        if (instance == null) {
            instance = new MultiplayerCampaignManager();
        }
        return instance;
    }

    public void createCampaign(Player hostPlayer) {
        campaigns.put(hostPlayer, new MultiplayerCampaign(hostPlayer));
    }

    public void joinCampaign(Player joiningPlayer, Player hostPlayer) {
        if (campaigns.containsKey(hostPlayer)) {
            campaigns.get(hostPlayer).joinPlayer(joiningPlayer);
        }
    }

    public void leaveCampaign(Player leavingPlayer) {
        for (MultiplayerCampaign campaign : campaigns.values()) {
            campaign.leavePlayer(leavingPlayer);
        }
    }

    public void startCampaign(Player hostPlayer, Map map) {
        if (campaigns.containsKey(hostPlayer)) {
            campaigns.get(hostPlayer).startCampaign(map);
        }
    }

    public MultiplayerCampaign getCurrentCampaign() {
        return campaigns.get(hostPlayer);
    }

    // Другие методы для синхронизации и управления кампанией
}