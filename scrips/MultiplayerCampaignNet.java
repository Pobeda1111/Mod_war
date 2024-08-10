package mindustry.multiplayer;

import arc.struct.*;
import arc.util.io.*;
import mindustry.net.Packets.*;
import mindustry.maps.Map;

public class MultiplayerCampaignNet {
    public static final int CAMPAIGN_JOIN = 100;
    public static final int CAMPAIGN_LEAVE = 101;
    public static final int CAMPAIGN_START = 102;
    public static final int CAMPAIGN_SYNC_MAP = 103;
    public static final int CAMPAIGN_SYNC_STATE = 104;

    public static void writeCampaignJoin(OutStream stream, Player hostPlayer) {
        stream.writeInt(CAMPAIGN_JOIN);
        stream.writeInt(hostPlayer.id);
    }

    public static void readCampaignJoin(Player player, InStream stream) {
        int hostPlayerId = stream.readInt();
        Player host = Player.ByID(hostPlayerId);
        if (host != null) {
            multiplayerCampaignManager.joinCampaign(player, host);
        }
    }

    public static void writeCampaignLeave(OutStream stream, Player leavingPlayer) {
        stream.writeInt(CAMPAIGN_LEAVE);
        stream.writeInt(leavingPlayer.id);
    }

    public static void readCampaignLeave(Player player, InStream stream) {
        int leavingPlayerId = stream.readInt();
        Player leaving = Player.ByID(leavingPlayerId);
        if (leaving != null) {
            multiplayerCampaignManager.leaveCampaign(leaving);
        }
    }

    public static void writeCampaignStart(OutStream stream, Map map) {
        stream.writeInt(CAMPAIGN_START);
        stream.writeBytes(map.writeData());
    }

    public static void readCampaignStart(Player player, InStream stream) {
        Map map = Map.read(stream);
        multiplayerCampaignManager.startCampaign(player, map);
    }

    public static void writeCampaignSyncMap(OutStream stream, Map map) {
        stream.writeInt(CAMPAIGN_SYNC_MAP);
        stream.writeBytes(map.writeData());
    }

    public static void readCampaignSyncMap(Player player, InStream stream) {
        Map map = Map.read(stream);
        multiplayerCampaignManager.syncCampaignMap(player, map);
    }

    public static void writeCampaignSyncState(OutStream stream, CampaignState state) {
        stream.writeInt(CAMPAIGN_SYNC_STATE);
        stream.writeBytes(state.writeData());
    }

    public static void readCampaignSyncState(Player player, InStream stream) {
        CampaignState state = CampaignState.read(stream);
        multiplayerCampaignManager.syncCampaignState(player, state);
    }

    // Класс для сериализации/десериализации состояния кампании
    public static class CampaignState {
        public int progress;
        public int resources;
        public Seq<Research> unlockedResearch;

        public void write(OutStream stream) {
            stream.writeInt(progress);
            stream.writeInt(resources);
            stream.writeSeq(unlockedResearch, (out, research) -> research.write(out));
        }

        public static CampaignState read(InStream stream) {
            CampaignState state = new CampaignState();
            state.progress = stream.readInt();
            state.resources = stream.readInt();
            state.unlockedResearch = stream.readSeq(Research[]::new, in -> Research.read(in));
            return state;
        }
    }
}