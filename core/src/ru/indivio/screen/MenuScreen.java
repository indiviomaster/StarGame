package ru.indivio.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Vector2 pos;
    private Vector2 velocity;
    private Vector2 touchPosition, stopPos;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        pos = new Vector2(0,0);
        touchPosition = new Vector2(0,0);
        stopPos = new Vector2(0,0);
        velocity = new Vector2(0,0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.56f, 0.81f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        if ((Math.abs(pos.x - stopPos.x))>= Math.abs(velocity.x)){
            pos.add(velocity);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touchPosition.set(screenX, Gdx.graphics.getHeight() - screenY);
        stopPos.set(touchPosition.x,touchPosition.y);
        velocity = touchPosition.sub(pos).nor();
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                pos.y += 10;
                break;
            case Input.Keys.DOWN:
                pos.y -= 10;
                break;
            case Input.Keys.RIGHT:
                pos.x += 10;
                break;
            case Input.Keys.LEFT:
                pos.x -= 10;
                break;
        }
        return false;
    }
}
