package player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;

public class Player extends Sprite {

    private World world;
    private Body body;

    public Player(World world, String name, float x, float y) {
        super(new Texture(name));
        this.world = world;
        setPosition(x - super.getWidth() / 2f, y - super.getHeight() / 2f);
        this.createBody();
    }

    void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(super.getX() / GameInfo.PPM, super.getY() / GameInfo.PPM);

        this.body = this.world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((super.getWidth() / 2) / GameInfo.PPM, (super.getHeight() / 2) / GameInfo.PPM);

        // Define shape and mass of the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = this.body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void updatePlayer() {
        this.setPosition(this.body.getPosition().x * GameInfo.PPM, this.body.getPosition().y * GameInfo.PPM);
    }
}
