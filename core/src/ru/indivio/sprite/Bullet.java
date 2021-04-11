package ru.indivio.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.base.Sprite;
import ru.indivio.math.Rect;


public class Bullet extends Sprite {

    private Rect worldBounds;
    private Vector2 v;
    private int damage;
    private Sprite owner;
    private Sound buletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
    public Bullet() {
        regions = new TextureRegion[1];
        v = new Vector2();

    }

    public void set(
            Sprite owner,
            TextureRegion region,
            Vector2 pos,
            Vector2 v,
            Rect worldBounds,
            int damage,
            float height
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos);
        this.v.set(v);
        this.worldBounds = worldBounds;
        this.damage = damage;
        setHeightProportion(height);




    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public void shotSound(){

        buletSound.play(0.3f);
    }

    public void shotSoundDispose(){
        buletSound.stop();
        buletSound.dispose();
    }


    public int getDamage() {
        return damage;
    }

    public Sprite getOwner() {
        return owner;
    }
}
