package xyz.hugoh.viw.renderer;

/**
 * Created by Hugo on 26.12.2016.
 */

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import xyz.hugoh.viw.Camera;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {
    private long windowHandle;
    private Renderer renderer;
    private Camera camera;

    public Window() {}

    public void create(int width, int height) {
        windowHandle = glfwCreateWindow(width, height, "Viw", NULL, NULL);
        glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
        });

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            windowHandle,
            (vidmode.width() - width) / 2,
            (vidmode.height() - height) / 2
        );

        // Make the OpenGL context current
        glfwMakeContextCurrent(windowHandle);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(windowHandle);

        beginLoop();
    }

    private void beginLoop() {
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        while (!glfwWindowShouldClose(windowHandle)) {
            renderer.render();
            glfwSwapBuffers(windowHandle);
            glfwPollEvents();
        }
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public Renderer GetRenderer() { return this.renderer; }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
