package ru.indivio.base;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.math.Rect;

public class Smile extends Sprite {
    float x,y;
    public Smile(Texture texture) {
        super(new TextureRegion(texture));
        x = 0f;
        y = 0f;
    }


    public void draw(SpriteBatch batch, Vector2 pos) {
        batch.draw(regions[frame],  pos.x, pos.y,.2f,.2f);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        System.out.println("Smile touch"+touch.x + touch.y);
        return false;
    }

}
