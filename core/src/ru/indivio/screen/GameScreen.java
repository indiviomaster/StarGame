package ru.indivio.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.util.List;

import ru.indivio.base.BaseScreen;
import ru.indivio.base.Font;
import ru.indivio.math.Rect;
import ru.indivio.pool.BulletPool;
import ru.indivio.pool.EnemyPool;
import ru.indivio.pool.ExplosionPool;
import ru.indivio.pool.MedcinePool;
import ru.indivio.sprite.Background;
import ru.indivio.sprite.Bullet;
import ru.indivio.sprite.EnemyShip;
import ru.indivio.sprite.GameOver;
import ru.indivio.sprite.MainShip;
import ru.indivio.sprite.Medcine;
import ru.indivio.sprite.NewGameButton;
import ru.indivio.sprite.Star;
import ru.indivio.utils.EnemyEmitter;
import ru.indivio.utils.MedcineEmitter;


public class GameScreen extends BaseScreen {

    private static final int STAR_COUNT = 64;
    private static final float FONT_SIZE = 0.02f;
    private static final float PADDING = 0.01f;

    private static final String FRAGS = "Frags: ";
    private static final String HP = "HP: ";
    private static final String LEVEL = "Level: ";

    private Texture bg;
    private Background background;

    private TextureAtlas atlas;
    private TextureAtlas medcineAtlas;

    private Star [] stars;
    private MainShip mainShip;

    private Sound laserSound;
    private Sound bulletSound;
    private Sound explosionSound;

    private EnemyPool enemyPool;
    private EnemyEmitter enemyEmitter;


    private Music music;

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private MedcinePool medcinePool;
    private MedcineEmitter medcineEmitter;
    private GameOver gameOver;
    private NewGameButton newGameButton;

    public GameScreen(){
    }

    private Font font;
    private StringBuilder sbFrags;
    private StringBuilder sbHp;
    private StringBuilder sbLevel;

    private int frags;


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        medcineAtlas = new TextureAtlas("textures/medical_atlas.atlas");
        laserSound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));

        stars = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++) {
            stars[i] = new Star(atlas);
        }
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, explosionSound);
        enemyPool = new EnemyPool(bulletPool,explosionPool, worldBounds, bulletSound);
        enemyEmitter = new EnemyEmitter(worldBounds, enemyPool, atlas);
        mainShip = new MainShip(atlas, bulletPool, explosionPool, laserSound);
        medcinePool = new MedcinePool(worldBounds);
        medcineEmitter = new MedcineEmitter(worldBounds,medcinePool,medcineAtlas);
        gameOver = new GameOver(atlas);
        newGameButton = new NewGameButton(atlas, this);

        font = new Font("font/font.fnt", "font/font.png");
        sbFrags = new StringBuilder();
        sbHp = new StringBuilder();
        sbLevel = new StringBuilder();

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/music.mp3"));
        music.setLooping(true);
        music.play();
    }

    public void startNewGame() {
        frags = 0;

        mainShip.startNewGame();

        bulletPool.freeAllActiveObjects();
        enemyPool.freeAllActiveObjects();
        medcinePool.freeAllActiveObjects();
    }

    @Override
    public void render(float delta) {
        update(delta);
        checkCollision();
        freeAllDestroyed();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
        mainShip.resize(worldBounds);
        gameOver.resize(worldBounds);
        newGameButton.resize(worldBounds);
        font.setSize(FONT_SIZE);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        bulletPool.dispose();
        enemyPool.dispose();
        medcinePool.dispose();
        explosionPool.dispose();
        font.dispose();
        laserSound.dispose();
        bulletSound.dispose();
        explosionSound.dispose();
        music.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (!mainShip.isDestroyed()) {
            mainShip.touchDown(touch, pointer, button);
        }else {
            newGameButton.touchDown(touch, pointer, button);
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        if (!mainShip.isDestroyed()) {
            mainShip.touchUp(touch, pointer, button);
        }else {
            newGameButton.touchUp(touch, pointer, button);
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        if (!mainShip.isDestroyed()) {
            mainShip.update(delta);
            bulletPool.updateActiveSprites(delta);
            enemyPool.updateActiveSprites(delta);
            medcinePool.updateActiveSprites(delta);
            enemyEmitter.generate(delta);
            medcineEmitter.generate(delta);
        }
        explosionPool.updateActiveSprites(delta);
    }

    private void checkCollision() {
        if (mainShip.isDestroyed()) {
            return;
        }
        List<Medcine> medcineList = medcinePool.getActiveObjects();
        for (Medcine med : medcineList) {
            if (med.isDestroyed()) {
                continue;
            }
            float minDist = med.getHalfWidth() + mainShip.getHalfWidth();
            if (med.pos.dst(mainShip.pos) < minDist) {
                med.destroy();
                mainShip.healing(med.getHealingPoint());
            }
        }


        List<EnemyShip> enemyShipList = enemyPool.getActiveObjects();
        for (EnemyShip enemyShip : enemyShipList) {
            if (enemyShip.isDestroyed()) {
                continue;
            }
            float minDist = enemyShip.getHalfWidth() + mainShip.getHalfWidth();
            if (enemyShip.pos.dst(mainShip.pos) < minDist) {
                enemyShip.destroy();
                mainShip.damage(enemyShip.getDamage() * 2);
            }
        }
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for (Bullet bullet : bulletList) {
            if (bullet.isDestroyed()) {
                continue;
            }
            for (EnemyShip enemyShip : enemyShipList) {
                if (enemyShip.isDestroyed() || bullet.getOwner() != mainShip) {
                    continue;
                }
                if (enemyShip.isBulletCollision(bullet)) {
                    enemyShip.damage(bullet.getDamage());
                    bullet.destroy();
                    if (enemyShip.isDestroyed()) {
                        frags++; //считаем убитые от пули
                    }
                }
            }
            if (bullet.getOwner() != mainShip) {
                if (mainShip.isBulletCollision(bullet)) {
                    mainShip.damage(bullet.getDamage());
                    bullet.destroy();
                }
            }
        }
    }

    private void freeAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        enemyPool.freeAllDestroyedActiveSprites();
        explosionPool.freeAllDestroyedActiveSprites();
        medcinePool.freeAllDestroyedActiveSprites();
    }

    private void draw() {
        Gdx.gl.glClearColor(0.56f, 0.81f, 0.26f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        if (mainShip.isDestroyed()) {
            gameOver.draw(batch);
            newGameButton.draw(batch);
        } else {
            mainShip.draw(batch);
            bulletPool.drawActiveSprites(batch);
            enemyPool.drawActiveSprites(batch);
            medcinePool.drawActiveSprites(batch);
        }
        explosionPool.drawActiveSprites(batch);
        printInfo();
        batch.end();
    }

    private void printInfo() {
        sbFrags.setLength(0);
        sbHp.setLength(0);
        sbLevel.setLength(0);
        font.draw(batch, sbFrags.append(FRAGS).append(frags), worldBounds.getLeft() + PADDING, worldBounds.getTop() - PADDING);
        font.draw(batch, sbHp.append(HP).append(mainShip.getHp()), worldBounds.pos.x, worldBounds.getTop() - PADDING, Align.center);
        font.draw(batch, sbLevel.append(LEVEL).append(enemyEmitter.getLevel()), worldBounds.getRight() - PADDING, worldBounds.getTop() - PADDING, Align.right);
    }
}
