package ru.indivio.sprite;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.base.Sprite;
import ru.indivio.math.Rect;


public class Medcine extends Sprite {

    protected Rect worldBounds;
    protected Vector2 velocity;
    protected int healingPoint;

    public Medcine(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        velocity = new Vector2();
    }
    public Medcine(Rect worldBounds)
    {

        this.worldBounds = worldBounds;
        velocity = new Vector2();
    }

    @Override
    public void update(float delta) {
        this.pos.mulAdd(velocity, delta);
        if (getBottom() < worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(TextureRegion[] regions, Vector2 v, float height, int hp) {
        this.regions = regions;
        this.velocity.set(v);
        setHeightProportion(height);
        this.healingPoint = hp;

    }

    public int getHealingPoint(){
        return healingPoint;
    }

}
