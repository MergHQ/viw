package xyz.hugoh.viw.renderer;

/**
 * Created by Hugo on 26.12.2016.
 */

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
public class Window {
    private long windowHandle;

    public void Create(int width, int height) {
        windowHandle = glfwCreateWindow(width, height, "Viw", NULL, NULL);
    }
}
