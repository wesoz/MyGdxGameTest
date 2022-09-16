package scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGameTest;

import helpers.GameInfo;
import player.Player;

public class MainMenu implements Screen {

    MyGdxGameTest game;
    Texture bg;
    Player player;

    public MainMenu(MyGdxGameTest game) {
        this.game = game;

        this.bg = new Texture("Game BG.png");
        this.player = new Player("Player 1.png", GameInfo.WIDTH / 2,GameInfo.HEIGHT / 2);
    }

    // Same as create() in Main class
    @Override
    public void show() {
        System.out.println("SHOW");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        game.getBatch().begin();
        game.getBatch().draw(bg, 0, 0);
        game.getBatch().draw(player, player.getX(), player.getY());
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        System.out.println("HIDE");
    }

    @Override
    public void dispose() {

    }
}
