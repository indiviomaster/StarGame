package ru.indivio.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.base.Sprite;
import ru.indivio.math.Rect;


public class Ship extends Sprite {

    private Vector2 velocity;
    private Rect worldBounds;

    private float height;

    public Ship(TextureAtlas atlas) {
        super(new TextureAtlas.AtlasRegion(atlas.findRegion("main_ship").getTexture(),atlas.findRegion("main_ship").getRegionX(),atlas.findRegion("main_ship").getRegionY(),atlas.findRegion("main_ship").getRegionWidth()/2,atlas.findRegion("main_ship").getRegionHeight()));//Задвоенный корабль
        velocity = new Vector2();//скорость перемещения корабля
    }

    public void setVelocity(Vector2 velocity){
        this.velocity.set(velocity);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.3f);
        float x = 0;
        float y = worldBounds.getBottom()+getHalfHeight();
        pos.set(x, y);
    }

    @Override
    public void update(float delta) {
        //System.out.println("ship X =" + pos.x +" world " + worldBounds.getLeft() + worldBounds.getRight());

        if(getRight()>worldBounds.getRight()){
            pos.x = worldBounds.getRight()-getHalfWidth();
            velocity.set(0,0);
        }else if(getLeft()<worldBounds.getLeft()){
            pos.x = worldBounds.getLeft()+getHalfWidth();
            velocity.set(0,0);
        }else{
            pos.x += velocity.x;
        }
    }
}
