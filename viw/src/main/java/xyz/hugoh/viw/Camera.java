package xyz.hugoh.viw;

import xyz.hugoh.viw.math.Matrix;

/**
 * Created by Hugo on 26.12.2016.
 */

public abstract class Camera {
    private float[][] viewMatrix;
    private float[][] projectionMatrix;
    private float[] center = {0f, 0f, 0f};
    private float[] position = {0f, 0f, 0f};

    public Camera(float fov, float aspectRatio, float near, float far) {
        this.projectionMatrix = Matrix.perspective(fov, aspectRatio, near, far);
    }

    public void onInputEvent(long window, int key, int scancode, int action, int mods) {

    }

    public float[][] getViewMatrix() {
        return viewMatrix;
    }

    public float[][] getProjectionMatrix() {
        return projectionMatrix;
    }

    public void setPosition(float[] pos) {
        position = pos;
        recreateView();
    }

    public float[] getPosition() {
        return position;
    }

    public void setCenter(float[] center) {
        this.center = center;
        recreateView();
    }

    public float[] getCenter() {
        return center;
    }

    private void recreateView() {
        float[] up = {0f, 1f, 0f};
        viewMatrix = Matrix.lookAt(position, up, center);
    }
}
