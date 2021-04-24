package ru.indivio.pool;

import com.badlogic.gdx.audio.Sound;

import ru.indivio.base.SpritesPool;
import ru.indivio.math.Rect;
import ru.indivio.sprite.EnemyShip;
import ru.indivio.sprite.Medcine;

public class MedcinePool extends SpritesPool<Medcine> {
    private final Rect worldBounds;

    public MedcinePool(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }

    @Override
    protected Medcine newSprite() {
        return new Medcine(worldBounds);
    }
}
