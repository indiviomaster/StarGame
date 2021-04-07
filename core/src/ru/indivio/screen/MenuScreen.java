package ru.indivio.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.base.BaseScreen;
import ru.indivio.base.Smile;
import ru.indivio.math.Rect;
import ru.indivio.sprite.Background;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private Texture smileTexture;
    private Smile smile;


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        smileTexture = new Texture("badlogic.jpg");
        smile = new Smile(smileTexture);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        smile.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.56f, 0.81f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        smile.update(delta);
        //отрисовка
        batch.begin();
        background.draw(batch);
        smile.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        smileTexture.dispose();
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        System.out.println("Menu touchDown screenX = " + touch.x + " screenY = " + touch.y);
        smile.touchUp(touch, pointer, button); //пробрасываем координаты в лого
        return false;
    }

//    @Override
//    public boolean keyDown(int keycode) {
//        switch (keycode) {
//            case Input.Keys.UP:
//                pos.y += 10;
//                break;
//            case Input.Keys.DOWN:
//                pos.y -= 10;
//                break;
//            case Input.Keys.RIGHT:
//                pos.x += 10;
//                break;
//            case Input.Keys.LEFT:
//                pos.x -= 10;
//                break;
//        }
//        return false;
//    }
}
