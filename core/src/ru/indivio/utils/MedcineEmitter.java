package ru.indivio.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.math.Rect;
import ru.indivio.math.Rnd;
import ru.indivio.pool.EnemyPool;
import ru.indivio.pool.MedcinePool;
import ru.indivio.sprite.EnemyShip;
import ru.indivio.sprite.Medcine;


public class MedcineEmitter {

    private static final float GENERATE_INTERVAL = 4f;

    private static final float SMALL_HEIGHT = 0.1f;
    private static final int SMALL_HP = 1;
    private static final float MEDIUM_HEIGHT = 0.1f;
    private static final int MEDIUM_HP = 50;
    private static final float BIG_HEIGHT = 0.1f;
    private static final int BIG_HP = 100;

    private final Rect worldBounds;
    private final MedcinePool medcinePool;
    private final TextureRegion [] medcineSmallRegions;
    private final TextureRegion [] medcineMediumRegions;
    private final TextureRegion [] medcineBigRegions;
    private Vector2 velocity;
    private float generateTimer;

    private int level = 1;

    public MedcineEmitter(Rect worldBounds, MedcinePool medcinePool, TextureAtlas atlas) {
        this.worldBounds = worldBounds;
        this.medcinePool = medcinePool;
        velocity = new Vector2(0, -0.2f);
        medcineSmallRegions = Regions.split(atlas.findRegion("small_medcine"),1,2,2) ;
        medcineMediumRegions = Regions.split(atlas.findRegion("medium_medcine"),1,2,2);
        medcineBigRegions = Regions.split(atlas.findRegion("big_medcine"),1,2,2);
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= GENERATE_INTERVAL) {
            generateTimer = 0;
            Medcine medcine = medcinePool.obtain();
            float type = (float) Math.random();
            if (type < 0.5f) {
                medcine.set(
                        medcineSmallRegions,
                        velocity,
                        SMALL_HEIGHT,
                        SMALL_HP
                );

            } else if (type < 0.8f) {
                medcine.set(
                        medcineSmallRegions,
                        velocity,
                        MEDIUM_HEIGHT,
                        MEDIUM_HP
                );
            } else {
                medcine.set(
                        medcineSmallRegions,
                        velocity,
                        BIG_HEIGHT,
                        BIG_HP
                );
            }
            medcine.pos.x = Rnd.nextFloat(
                    worldBounds.getLeft() + medcine.getHalfWidth(),
                    worldBounds.getRight() - medcine.getHalfWidth()
            );
            medcine.setBottom(worldBounds.getTop());
        }
    }

    public int getLevel() {
        return level;
    }
}
