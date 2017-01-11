package xyz.hugoh.viw.entity;

import xyz.hugoh.viw.Camera;

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
        float[] pos = {0f, 2f, 2f};
        float[] center = {0f, 0f, 0f};
        super.setPosition(pos);
    }

    @Override
    public void onInputEvent(long window, int key, int scancode, int action, int mods) {
    }
}
