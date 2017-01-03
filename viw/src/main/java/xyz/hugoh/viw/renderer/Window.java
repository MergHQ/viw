package xyz.hugoh.viw.renderer;

/**
 * Created by Hugo on 26.12.2016.
 */

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import xyz.hugoh.viw.Camera;

import java.util.concurrent.Callable;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {
    private long windowHandle;
    private Renderer renderer;
    private Camera camera;
    private int width, height;

    public Window() {}

    public void create(int width, int height, Callable onInitReady) {
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }


        this.width = width;
        this.height = height;

        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

        windowHandle = glfwCreateWindow(width, height, "Viw", NULL, NULL);
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        // Center our window
        glfwSetWindowPos(
            windowHandle,
            (vidmode.width() - width) / 2,
            (vidmode.height() - height) / 2
        );

        glfwSetKeyCallback(windowHandle, (window, key, scancode, action, mods) -> {
           if (camera != null) {
               camera.onInputEvent(window, key, scancode, action, mods);
           }
        });

        // Make the OpenGL context current
        glfwMakeContextCurrent(windowHandle);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(windowHandle);

        GL.createCapabilities();

        try {
            onInitReady.call();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        beginLoop();
    }

    private void beginLoop() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        while (!glfwWindowShouldClose(windowHandle)) {
            if (renderer != null) {
                renderer.render();
            }
            glfwSwapBuffers(windowHandle);
            glfwPollEvents();
        }
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public Renderer getRenderer() {
        return this.renderer;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
