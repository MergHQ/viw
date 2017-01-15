package xyz.hugoh.viw.entity;

import xyz.hugoh.viw.Camera;

/**
 * Basic camera.
 */
public class OrbitingCamera extends Camera {
    private int deg = 0;
    private float radius = 5.f;
    private float mouseX = 0;

    /**
     * Creates a new {@link OrbitingCamera} instance.
     * @param fov field of view
     * @param aspectRatio aspect ratio
     * @param near camera near field
     * @param far camera far field
     */
    public OrbitingCamera(float fov, float aspectRatio, float near, float far) {
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
    public void onMouseMoved(double posX, double posY) {
        float dx = (float) posX - mouseX;
        deg += -dx / 2.f;
        mouseX = (float) posX;
    }

    @Override
    public void update() {
        float posX = (float) Math.cos(deg / 100.f) * radius;
        float posZ = (float) Math.sin(deg / 100.f) * radius;
        float[] pos = {posX, -3f, posZ};
        setPosition(pos);
    }
}
