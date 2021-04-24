package ru.indivio.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.indivio.base.BaseButton;
import ru.indivio.math.Rect;
import ru.indivio.screen.GameScreen;


public class NewGameButton extends BaseButton {

    private static final float HEIGHT = 0.08f;
    private static final float PADDING = 0.3f;
    private static final float PADDING_LEFT = 0.05f;

    private final GameScreen gameScreen;

    public NewGameButton(TextureAtlas atlas, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameScreen = gameScreen;
    }

    @Override
    public void resize(Rect worldBounds) {
        System.out.println("ButNewGAne Resize");
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom()+PADDING);
        setLeft(worldBounds.getLeft()+PADDING_LEFT);
    }


    @Override
    public void action() {
        gameScreen.startNewGame();
    }
}
