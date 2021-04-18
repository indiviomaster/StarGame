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

    private final Game game;

    public NewGameButton(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("button_new_game"));
        System.out.println("NewGame Construct");
        this.game = game;
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
        game.setScreen(new GameScreen());
    }
}
