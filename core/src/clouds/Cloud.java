package clouds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;

public class Cloud extends Sprite {
    private World world;
    private Body body;

    public Cloud(World world) {
        super(new Texture("Cloud 1.png"));
        super.setPosition(GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2 - 130);
        this.world = world;
        createBody();
    }

    void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(super.getX() / GameInfo.PPM, super.getY() / GameInfo.PPM);

        this.body = this.world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((super.getWidth()) / GameInfo.PPM, (super.getHeight() / 2) / GameInfo.PPM);

        // Define shape and mass of the body
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1;

        Fixture fixture = this.body.createFixture(fixtureDef);
        fixture.setUserData("Cloud");
        // Makes the bodies detect collision but get through
        fixture.setSensor(true);

        shape.dispose();
    }
}
