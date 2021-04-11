package ru.indivio.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.base.Sprite;
import ru.indivio.math.Rect;


public class EnemyShip extends Sprite {

    private static final float HEIGHT = 0.15f;
    private static final float PADDING = 0.05f;

    private Rect worldBounds;

    private final Vector2 v;

    public EnemyShip(TextureAtlas atlas) {
        super(atlas.findRegion("enemy0"), 1, 2, 2);
        System.out.println("Создан враг");
        pos.set(0.2f,0.2f);
        v = new Vector2(-0.1f,-0.2f);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        setTop(worldBounds.getTop() - PADDING);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
    }

}
