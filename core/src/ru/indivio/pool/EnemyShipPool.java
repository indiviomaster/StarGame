package ru.indivio.pool;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.indivio.base.SpritesPool;
import ru.indivio.sprite.EnemyShip;

public class EnemyShipPool extends SpritesPool<EnemyShip> {
    private TextureAtlas atlas;
    public void setAtlas(TextureAtlas atlas){
        this.atlas = atlas;
    }

    @Override
    protected EnemyShip newSprite() {
        return new EnemyShip(atlas);
    }
}
