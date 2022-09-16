package scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGameTest;

import helpers.GameInfo;
import player.Player;

public class MainMenu implements Screen {

    private MyGdxGameTest game;
    private Texture bg;
    private Player player;
    private World world;

    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    public MainMenu(MyGdxGameTest game) {
        this.game = game;

        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM, GameInfo.HEIGHT / GameInfo.PPM);

        box2DCamera.position.set(GameInfo.WIDTH / 2f,GameInfo.HEIGHT / 2f, 0);

        debugRenderer = new Box2DDebugRenderer();

        this.bg = new Texture("Game BG.png");
        this.world = new World(new Vector2(0, -9.8f), true);
        this.player = new Player(this.world, "Player 1.png", GameInfo.WIDTH / 2,GameInfo.HEIGHT / 2);
    }

    // Same as create() in Main class
    @Override
    public void show() {
        System.out.println("SHOW");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        this.player.updatePlayer();

        this.game.getBatch().begin();
        this.game.getBatch().draw(this.bg, 0, 0);
        this.game.getBatch().draw(this.player, this.player.getX(), this.player.getY() - this.player.getHeight() / 2f);
        this.game.getBatch().end();

        this.debugRenderer.render(this.world, this.box2DCamera.combined);

        this.world.step(Gdx.graphics.getDeltaTime(), 6, 2);
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
        this.bg.dispose();
        this.player.getTexture().dispose();
    }
}
