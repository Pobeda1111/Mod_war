package mindustry.net;

import arc.struct.*;
import arc.util.io.*;
import mindustry.multiplayer.*;
import mindustry.net.*;

public class Net {
    // ... (другой код сети) ...

    public static void sendCampaignJoin(Player player, Player hostPlayer) {
        OutStream stream = new OutStream();
        MultiplayerCampaignNet.writeCampaignJoin(stream, hostPlayer);
        player.con.sendTCP(new Packet(NetCodes.campaign, stream));
    }

    public static void sendCampaignLeave(Player player, Player leavingPlayer) {
        OutStream stream = new OutStream();
        MultiplayerCampaignNet.writeCampaignLeave(stream, leavingPlayer);
        player.con.sendToAllTCP(new Packet(NetCodes.campaign, stream));
    }

    public static void sendCampaignStart(Player player, Map map) {
        OutStream stream = new OutStream();
        MultiplayerCampaignNet.writeCampaignStart(stream, map);
        player.con.sendToAllTCP(new Packet(NetCodes.campaign, stream));
    }

    public static void sendCampaignSyncMap(Player player, Map map) {
        OutStream stream = new OutStream();
        MultiplayerCampaignNet.writeCampaignSyncMap(stream, map);
        player.con.sendToAllTCP(new Packet(NetCodes.campaign, stream));
    }

    public static void sendCampaignSyncState(Player player, CampaignState state) {
        OutStream stream = new OutStream();
        MultiplayerCampaignNet.writeCampaignSyncState(stream, state);
        player.con.sendToAllTCP(new Packet(NetCodes.campaign, stream));
    }

    // Другие методы для отправки сообщений кампании
}