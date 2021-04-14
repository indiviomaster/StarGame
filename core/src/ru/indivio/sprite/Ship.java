package ru.indivio.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.base.Sprite;
import ru.indivio.math.Rect;
import ru.indivio.pool.BulletPool;


public class Ship extends Sprite {

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected float bulletHeight;
    protected int damage;

    protected final Vector2 v;
    protected final Vector2 v0;

    protected int hp;
    protected Sound sound;

    private float reloadTimer;
    protected float reloadInterval;

    public Ship() {
        bulletV = new Vector2();
        v = new Vector2();
        v0 = new Vector2();
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        bulletV = new Vector2();
        v = new Vector2();
        v0 = new Vector2();
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        if (reloadTimer > reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, this.pos, bulletV, worldBounds, damage, bulletHeight);
        sound.play();
    }


}
