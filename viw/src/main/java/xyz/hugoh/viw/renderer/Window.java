package xyz.hugoh.viw.renderer;

/**
 * Window.
 */

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import xyz.hugoh.viw.Camera;

import java.util.concurrent.Callable;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL20.GL_SHADING_LANGUAGE_VERSION;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {
    private long windowHandle;
    private Renderer renderer;
    private Camera camera;
    private int width, height;

    /**
     * Creates a new {@link Window} instance.
     */
    public Window() {
    }

    /**
     * Creates an OpenGL context and begins render loop.
     * @param width width of window
     * @param height height of window
     * @param onInitReady callback function that is executed when the OpenGL context is fully created
     */
    public void create(int width, int height, Callable onInitReady) {

        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }


        this.width = width;
        this.height = height;

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        windowHandle = glfwCreateWindow(width, height, "Viw", NULL, NULL);
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
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

        glfwSetScrollCallback(windowHandle, (window, scrollX, scrollY) -> {
           if (camera != null) {
               camera.onScrollWheel(scrollX, scrollY);
           }
        });

        glfwSetCursorPosCallback(windowHandle, (window, posX, posY) -> {
            if (camera != null) {
                camera.onMouseMoved(posX, posY);
            }
        });

        glfwMakeContextCurrent(windowHandle);
        glfwSwapInterval(1);

        glfwShowWindow(windowHandle);

        GL.createCapabilities();

        System.out.println(glGetString(GL_VERSION));
        System.out.println(glGetString(GL_SHADING_LANGUAGE_VERSION));

        try {
            onInitReady.call();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        beginLoop();
    }

    private void beginLoop() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LESS);
        //glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );
        while (!glfwWindowShouldClose(windowHandle)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glClearColor(1f, 1f, 1f, 1f);
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
