package ru.indivio.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.base.BaseScreen;
import ru.indivio.math.Rect;
import ru.indivio.sprite.Background;
import ru.indivio.sprite.Ship;
import ru.indivio.sprite.Star;


public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;
    private Vector2 GO_RIGHT = new Vector2(0.01f,0);
    private Vector2 GO_LEFT = new Vector2(-0.01f,0);
    private Texture bg;
    private Background background;

    private TextureAtlas atlas;
    private Ship mainShip;
    private Star [] stars;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        atlas = new TextureAtlas("textures/mainAtlas.tpack");

        mainShip = new Ship(atlas);
        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        mainShip.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return super.touchUp(touch, pointer, button);
    }

    private void update(float delta) {

        for (Star star : stars) {
            star.update(delta);
        }
        mainShip.update(delta);
    }

    private void draw() {
        Gdx.gl.glClearColor(0.56f, 0.81f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        mainShip.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
                mainShip.setVelocity(GO_RIGHT);
                break;
            case Input.Keys.LEFT:
                mainShip.setVelocity(GO_LEFT);
                break;
            default:
                break;
        }
        return false;
    }
}
