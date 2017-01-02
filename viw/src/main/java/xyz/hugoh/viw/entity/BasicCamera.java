package xyz.hugoh.viw.entity;

import xyz.hugoh.viw.Camera;

/**
 * Created by Hugo on 2.1.2017.
 */
public class BasicCamera extends Camera {
    public BasicCamera(float fov, float aspectRatio, float near, float far) {
        super(fov, aspectRatio, near, far);
        float[] pos = {0f, 1f, 1f};
        float[] center = {0f, 0f, 0f};
        super.setCenter(center);
        super.setPosition(pos);
    }

    @Override
    public void onInputEvent() {

    }
}
