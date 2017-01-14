package xyz.hugoh.viw.entity;

import org.lwjgl.glfw.GLFW;
import xyz.hugoh.viw.Camera;
import xyz.hugoh.viw.math.VectorArray;

import java.awt.event.KeyEvent;

/**
 * Basic camera.
 */
public class BasicCamera extends Camera {
    private int deg = 0;
    private float radius = 5.f;

    /**
     * Creates a new {@link BasicCamera} instance.
     * @param fov field of view
     * @param aspectRatio aspect ratio
     * @param near camera near field
     * @param far camera far field
     */
    public BasicCamera(float fov, float aspectRatio, float near, float far) {
        super(fov, aspectRatio, near, far);
        float[] pos = {0f, 1f, 0f};
        float[] center = {0f, 0f, 0f};
        super.setPosition(pos);
    }

    @Override
    public void onScrollWheel(double scrollX, double scrollY) {
        radius += -(scrollX + scrollY) / 2.f;
    }

    @Override
    public void update() {
        float posX = (float) Math.cos(deg / 100.f) * radius;
        float posZ = (float) Math.sin(deg / 100.f) * radius;
        float[] pos = {posX, -3f, posZ};
        setPosition(pos);
        ++deg;
    }
}
