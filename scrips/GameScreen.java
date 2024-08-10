package mindustry.ui;

import arc.graphics.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import mindustry.gen.*;
import mindustry.ui.*;
import mindustry.multiplayer.*;

public class GameScreen implements Screen {
    private MultiplayerCampaignScreen campaignScreen;

    public void show() {
        // ... (другой код для отображения экрана игры) ...

        campaignScreen = new MultiplayerCampaignScreen(multiplayerCampaignManager.get().getCurrentCampaign());
        campaignScreen.show();
    }

    public void hide() {
        // ... (другой код для скрытия экрана игры) ...

        campaignScreen.hide();
    }

    // Другие методы для управления экраном игры
}