package ru.indivio;

import com.badlogic.gdx.Game;

import ru.indivio.screen.MenuScreen;

public class StarGame extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
