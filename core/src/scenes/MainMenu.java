package scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGameTest;

import clouds.Cloud;
import helpers.GameInfo;
import player.Player;

public class MainMenu implements Screen, ContactListener {

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
        this.world = new World(new Vector2(0, -9.8f), true);
        this.world.setContactListener(this);
        this.bg = new Texture("Game BG.png");
        this.player = new Player(this.world, "Player 1.png", GameInfo.WIDTH / 2,GameInfo.HEIGHT / 2);

        Cloud c = new Cloud(this.world);
    }

    // Same as create() in Main class
    @Override
    public void show() {
    }

    public void update(float dt) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            // this.player.getBody().applyLinearImpulse(new Vector2(-0.1f,0), this.player.getBody().getWorldCenter(), true);
            this.player.getBody().applyForce(new Vector2(-5f,0), this.player.getBody().getWorldCenter(), true);
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            // this.player.getBody().applyLinearImpulse(new Vector2(0.1f,0), this.player.getBody().getWorldCenter(), true);
            this.player.getBody().applyForce(new Vector2(5f,0), this.player.getBody().getWorldCenter(), true);
        }
    }

    @Override
    public void render(float delta) {
        this.update(delta);
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

    @Override
    public void beginContact(Contact contact) {
        Fixture firstBody, secondBody;

        if (contact.getFixtureA().getUserData().equals("Player")) {
            firstBody = contact.getFixtureA();
            secondBody = contact.getFixtureB();
        } else {
            firstBody = contact.getFixtureB();
            secondBody = contact.getFixtureA();
        }

        System.out.println("The name of the first body is " + firstBody.getUserData());
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
