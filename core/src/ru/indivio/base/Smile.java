package ru.indivio.base;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.math.Rect;

public class Smile extends Sprite {

    private Vector2 touchPos;
    private Vector2 velocity;
    private Vector2 tmp;


    public Smile(Texture texture) {
        super(new TextureRegion(texture));
        touchPos = new Vector2();
        velocity = new Vector2();
        tmp = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.2f); //устанавливаем пропорции относительно экрана для логотипа
    }

    @Override
    public void update(float delta) {
        tmp.set(touchPos);
        if(tmp.sub(pos).len()>0.01f){
            pos.add(velocity);
        }else{
            pos.set(touchPos);
        }
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        touchPos.set(touch);
        //velocity.set(touch.sub(pos)).nor(); //не корректно работает в координатах 2f2f
        velocity.set(touch.sub(pos)).setLength(0.01f);
//        System.out.println("velo="+velocity);
//        System.out.println("position Smile pos"+pos);
//        System.out.println("position Smile touch"+touch);
        return false;
    }

}

