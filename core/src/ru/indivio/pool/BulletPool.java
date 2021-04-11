package ru.indivio.pool;


import ru.indivio.base.SpritesPool;
import ru.indivio.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {

    @Override
    protected Bullet newSprite() {
        return new Bullet();
    }

}
