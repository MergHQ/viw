package xyz.hugoh.viw.entity;

import xyz.hugoh.viw.Camera;

/**
 * Basic camera.
 */
public class BasicCamera extends Camera {
    public BasicCamera(float fov, float aspectRatio, float near, float far) {
        super(fov, aspectRatio, near, far);
        float[] pos = {0f, 1f, 1f};
        float[] center = {0f, 0f, 0f};
        super.setPosition(pos);
    }

    @Override
    public void onInputEvent(long window, int key, int scancode, int action, int mods) {
    }
}
