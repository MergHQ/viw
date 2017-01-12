package xyz.hugoh.viw.entity;

import org.lwjgl.glfw.GLFW;
import xyz.hugoh.viw.Camera;
import xyz.hugoh.viw.math.VectorArray;

import java.awt.event.KeyEvent;

/**
 * Basic camera.
 */
public class BasicCamera extends Camera {
    /**
     * Creates a new {@link BasicCamera} instance.
     * @param fov field of view
     * @param aspectRatio aspect ratio
     * @param near camera near field
     * @param far camera far field
     */
    public BasicCamera(float fov, float aspectRatio, float near, float far) {
        super(fov, aspectRatio, near, far);
        float[] pos = {4f, 2f, 0f};
        float[] center = {0f, 0f, 0f};
        super.setPosition(pos);
    }

    @Override
    public void onInputEvent(long window, int key, int scancode, int action, int mods) {
        if (key == GLFW.GLFW_KEY_W && action == GLFW.GLFW_RELEASE) {
            float[] a = {1f,0f,0f};
            setPosition(VectorArray.sum(getPosition(), a));
        } else if (key == GLFW.GLFW_KEY_S && action == GLFW.GLFW_RELEASE) {
            float[] a = {-1f,0f,0f};
            setPosition(VectorArray.sum(getPosition(), a));
        }
    }
}
