package mindustry.ui;

import arc.graphics.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.io.*;
import mindustry.gen.*;
import mindustry.ui.*;
import mindustry.multiplayer.*;

public class MultiplayerCampaignScreen extends Table {
    private MultiplayerCampaign campaign;
    private Seq<Player> joinedPlayers;
    private TextButton startButton;
    private Table playerTable;

    public MultiplayerCampaignScreen(MultiplayerCampaign campaign) {
        this.campaign = campaign;
        this.joinedPlayers = campaign.joinedPlayers;

        startButton = new TextButton("Начать кампанию", () -> {
            if (joinedPlayers.size > 1) {
                campaign.startCampaign(campaign.campaignMap);
            }
        });
        startButton.disabled(joinedPlayers.size < 2);

        playerTable = new Table();
        playerTable.margin(10f);
        playerTable.defaults().size(100f, 50f).pad(5f);

        updatePlayers();
    }

    public void updatePlayers() {
        playerTable.clearChildren();

        for (Player player : joinedPlayers) {
            final Player finalPlayer = player;
            playerTable.add(new TextButton(player.name, () -> {
                // Код для удаления игрока из кампании здесь
            })).disabled(player == campaign.hostPlayer);
        }

        add(startButton).pad(10f);
        add(playerTable);
    }

    public void show() {
        stage.add(this).fill().get();
    }

    public void hide() {
        stage.remove(this);
    }
}