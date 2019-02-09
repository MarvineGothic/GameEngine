package entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

    private Matrix4f viewMatrix;

    private float distanceFromPlayer = 50;
    private float angleAroundPlayer = 0;

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch = 10;
    private float yaw = 0;
    private float roll;

    private Player player;

    public Camera(Player player) {
        this.player = player;
    }

    public void move() {
        calculateZoom();
        calculatePitch();
        calculateAngleAroundPlayer();
        float horizontalDistance = calculateHorizontalDistance();
        float verticalDistance = calculateVerticalDistance();
        calculateCameraPosition(horizontalDistance, verticalDistance);
        this.yaw = 180 - (player.getRotY() /*+ angleAroundPlayer*/);
        //System.out.println("Yaw" + yaw);
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

    private void calculateCameraPosition(float horizDistance, float verticDistance) {
        //float theta = player.getRotY() + angleAroundPlayer;
        float theta = player.getRotY()/* + angleAroundPlayer*/;
        //System.out.println("pLayer Y" + player.getRotY());

        float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
        position.x = player.getPosition().x - offsetX;
        position.z = player.getPosition().z - offsetZ;
        position.y = player.getPosition().y + verticDistance + 10;
    }

    private float calculateHorizontalDistance() {
        return (float) (distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
    }

    private float calculateVerticalDistance() {
        return (float) (distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
    }

    private void calculateZoom() {
        float zoomLevel = Mouse.getDWheel() * 0.1f;
        distanceFromPlayer -= zoomLevel;
        if (distanceFromPlayer > 200) distanceFromPlayer = 200;
        if (distanceFromPlayer < 0) distanceFromPlayer = 0;
    }

    private void calculatePitch() {
        //if (Mouse.isButtonDown(1)) {
        float pitchChange = Mouse.getDY() * 0.1f;
        pitch -= pitchChange;
        // }
    }

    private void calculateAngleAroundPlayer() {
        // if (Mouse.isButtonDown(0)) {
        float angleChange = Mouse.getDX() * 0.3f;
        angleAroundPlayer -= angleChange;
       // System.out.println(angleAroundPlayer);
        player.setRotY(angleAroundPlayer);
        // }
    }

    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }

    public void setViewMatrix(Matrix4f viewMatrix) {
        this.viewMatrix = viewMatrix;
    }
}




/*if (Keyboard.isKeyDown(Keyboard.KEY_W)) position.z -= 1f;
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) position.z += 1f;
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) position.x += 1f;
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) position.x -= 1f;
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) position.y += 0.2f;
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) position.y -= 0.2f;*/

         /*   float arg_yaw = Mouse.getDX() ;
            System.out.println(arg_yaw) ;
            yaw += arg_yaw/10 ;
            float arg_roll = Mouse.getDY() ;
            pitch += -(arg_roll/10) ;
            Mouse.setGrabbed(true);

            if (Keyboard.isKeyDown(Keyboard.KEY_W))
            {
                float toZ = ((float)Math.sin( Math.toRadians(yaw+90))) ;
                float toX = ((float)Math.cos( Math.toRadians(yaw+90))) ;
                position.x -= toX*10;
                position.z -= toZ*10;

            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S))
            {
                float toZ = ((float)Math.sin( Math.toRadians(yaw+90))) ;
                float toX = ((float)Math.cos( Math.toRadians(yaw+90))) ;
                position.x += toX;
                position.z += toZ;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_D))
            {
                float toZ = ((float)Math.sin( Math.toRadians(yaw))) ;
                float toX = ((float)Math.cos( Math.toRadians(yaw))) ;
                position.x += toX;
                position.z += toZ;
            }

            if (Keyboard.isKeyDown(Keyboard.KEY_A))
            {
                float toZ = ((float)Math.sin( Math.toRadians(yaw))) ;
                float toX = ((float)Math.cos( Math.toRadians(yaw))) ;
                position.x -= toX;
                position.z -= toZ;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            {
                position.y += 0.2f;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            {
                position.y -= 0.2f;
            }*/