package ru.indivio;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	int count = 0;
	TextureRegion region;
	TextureRegion region2;

	@Override
	public void create () {

//		передает на отрисовку объекты
		batch = new SpriteBatch();
//		текстуры
		img = new Texture("badlogic.jpg");
		region = new TextureRegion(img); //TextureRegion не хранит текстуру
		//вырезаем из текстуры прямоугольник по координатам 50, 50 размером 150х150
		region2 = new TextureRegion(img,50,50, 150, 150);
	}

	@Override
	public void render () {
		count++;
		//60 раз в секунду перерисовывает экран
		Gdx.gl.glClearColor(1, 0, 0, 1); //очищаем цвет фона
		//Gdx.gl.glClearColor(0.56f, 0.11f, 0.26f, 1); //числа от 0 до 1
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin(); //команда к передаче объектов на отрисовку
		//родной цвет все 1 + непрозрачная картинка
		batch.setColor(1,1,1,1);
		//рисуем картинку
		batch.draw(img, 0, 0);
		//цвет текстуры и 50% прозрачная картинка
		batch.setColor(0.2f,0.2f,0.2f,0.5f);
		//добавляем регион в координаты x,y
		batch.draw(region2, 200, 200);
		batch.end(); //все готово рисуем
	}
	
	@Override
	public void dispose () {
		// все объекты с методом dispose
		batch.dispose();
		img.dispose();
	}
}
