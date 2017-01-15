package xyz.hugoh.viw;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import xyz.hugoh.viw.math.Matrix;

import java.nio.FloatBuffer;

/**
 * Base camera class.
 */

public abstract class Camera {
    private float[][] viewMatrix;
    private float[][] projectionMatrix;
    private float[] center = {0f, 0f, 0f};
    private float[] position = {0f, 0f, 0f};

    /**
     * Creates a new {@link Camera} instance.
     * @param fov field of view
     * @param aspectRatio aspect ratio
     * @param near camera near field
     * @param far camera far field
     */
    public Camera(float fov, float aspectRatio, float near, float far) {
        this.projectionMatrix = Matrix.perspective(fov, aspectRatio, near, far);
    }

    /**
     * Called on a key event.
     * @param window window handle
     * @param key key id
     * @param scancode scancode handle
     * @param action action id
     * @param mods modifications
     */
    public void onInputEvent(long window, int key, int scancode, int action, int mods) {

    }

    /**
     * Called on mouse scroll.
     * @param scrollX The scroll offset along the x-axis
     * @param scrollY The scroll offset along the y-axis
     */
    public void onScrollWheel(double scrollX, double scrollY) {}

    /**
     * Called when the mouse position changes.
     * @param posX x position
     * @param posY y position
     */
    public void onMouseMoved(double posX, double posY) {}

    /**
     * Called every frame.
     */
    public void update() {}

    public float[] getViewMatrix() {
        return Matrix.to1Darray(viewMatrix);
    }

    public float[] getProjectionMatrix() {
        return Matrix.to1Darray(projectionMatrix);
    }

    /**
     * Sets the camera position and recreates the view matrix.
     * @param pos position
     */
    public void setPosition(float[] pos) {
        position = pos;
        recreateView();
    }

    public float[] getPosition() {
        return position;
    }

    /**
     * Sets the camera look at and recreates the view matrix.
     * @param center center
     */
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
