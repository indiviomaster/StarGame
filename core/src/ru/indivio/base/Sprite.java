package ru.indivio.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.indivio.math.Rect;


public class Sprite extends Rect {

    protected float angle; //угол поворота
    protected float scale = 1f; //масштаб
    protected TextureRegion[] regions; //регионы для анимации
    protected int frame; // текущая текстура

    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[frame].getRegionWidth() / (float) regions[frame].getRegionHeight();
        setWidth(height*aspect);
    }

    public void resize(Rect worldBounds) {

    }

    public void update(float delta) {

    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[frame], //текущая текстура
                getLeft(), getBottom(),
                halfWidth, halfHeight, //центруем позицию
                getWidth(), getHeight(), //устанавливаем новые размеры
                scale, scale, //масштабируем
                angle //поворачиваем
        );
    }

    public boolean touchDown(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        return false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
