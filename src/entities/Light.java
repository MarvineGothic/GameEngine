package entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;

public class Light extends Entity {
    private Vector3f lightPosition;
    private Vector3f color;
    private Vector3f attenuation = new Vector3f(1, 0, 0);

    private int lightOffsetDY = 0;

    public Light(Vector3f lightPosition, Vector3f color) {
        super(lightPosition, 0, 0, 0, 1);
        this.lightPosition = lightPosition;
        this.color = color;
    }

    public Light(Vector3f lightPosition, Vector3f color, Vector3f attenuation) {
        super(lightPosition, 0, 0, 0, 1);
        this.lightPosition = lightPosition;
        this.color = color;
        this.attenuation = attenuation;
    }

    public Light setTexturedModel(TexturedModel texturedModel) {
        super.setModel(texturedModel);
        return this;
    }

    public Vector3f getAttenuation() {
        return attenuation;
    }

    public Vector3f getLightPosition() {
        return lightPosition;
    }

    public void setLightPosition(Vector3f lightPosition) {
        this.lightPosition.x = lightPosition.x;
        this.lightPosition.y = lightPosition.y + lightOffsetDY;
        this.lightPosition.z = lightPosition.z;
        super.setPosition(lightPosition);
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public int getLightOffsetDY() {
        return lightOffsetDY;
    }

    public Light setLightOffsetDY(int lightOffsetDY) {
        this.lightOffsetDY = lightOffsetDY;
        lightPosition = new Vector3f(lightPosition.x, lightPosition.y + lightOffsetDY, lightPosition.z);
        return this;
    }
}
