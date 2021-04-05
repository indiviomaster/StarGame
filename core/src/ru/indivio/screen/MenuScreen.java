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
    private Vector2 smilePos;
    private Vector2 smileToPos;
    private Vector2 smileStopPos;
    private Vector2 velocity;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        smileTexture = new Texture("badlogic.jpg");
        smile = new Smile(smileTexture);
        smilePos = new Vector2(0,0);
        smileToPos = new Vector2(0,0);
        smileStopPos = new Vector2(0,0);
        velocity = new Vector2(0,0);
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
        batch.begin();
        background.draw(batch);
        System.out.println("X="+smilePos.x+"v="+ velocity.x + "D= "+(smilePos.x - smileStopPos.x));
        smile.draw(batch, smilePos);
        if ((Math.abs(smilePos.x - smileStopPos.x))>= Math.abs(velocity.x)){
            smilePos.add(velocity);
            System.out.println("Добавляем вектор"+velocity.x);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        smileTexture.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("Menu touchDown screenX = " + screenX + " screenY = " + screenY);
        smileToPos.set(screenX, screenBounds.getHeight()- screenY).mul(screenToWorld);
        smileStopPos.set(smileToPos.x,smileToPos.y);
        velocity = smileToPos.sub(smilePos).nor();
        return false;
    }


//    @Override
//    public boolean touchDown(Vector2 touch, int pointer, int button) {
//
//        return false;
//    }

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
