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

    //protected boolean onScreen;
    protected boolean onMarsh;

    public boolean isOnScreen(){
        if (getTop() < worldBounds.getTop() && getBottom()>worldBounds.getBottom()) {
            return true;
        }
        return false;
    }

    public Ship() {
        bulletV = new Vector2();
        v = new Vector2();
        v0 = new Vector2(0, -0.5f);
        //onScreen = false;
        onMarsh = true;
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
        bulletV = new Vector2();
        v = new Vector2();
        v0 = new Vector2(0, -1f);
        //onScreen = false;
        onMarsh = true;
    }

    @Override
    public void update(float delta) {
        if(onMarsh){
        pos.mulAdd(v0, delta);
        }else{
            pos.mulAdd(v, delta);
        }

        if(isOnScreen()){
            //onScreen = true;
            onMarsh = false;
            reloadTimer += delta;
            if (reloadTimer > reloadInterval) {
                reloadTimer = 0f;

                shoot();
                }
        }else{
            //onScreen = false;
            onMarsh = true;}


    }

    private void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, this.pos, bulletV, worldBounds, damage, bulletHeight);
        sound.play();
    }


}
