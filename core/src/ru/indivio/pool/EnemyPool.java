package ru.indivio.pool;

import com.badlogic.gdx.audio.Sound;

import ru.indivio.base.SpritesPool;
import ru.indivio.math.Rect;
import ru.indivio.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private final BulletPool bulletPool;
    private final ExplosionPool explosionPool;
    private final Rect worldBounds;
    private final Sound sound;

    public EnemyPool(BulletPool bulletPool,ExplosionPool explosionPool, Rect worldBounds, Sound sound) {
        this.explosionPool = explosionPool;
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.sound = sound;
    }

    @Override
    protected EnemyShip newSprite() {
        return new EnemyShip(bulletPool, explosionPool, worldBounds, sound);
    }
}
