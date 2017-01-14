package xyz.hugoh.viw.io;

/**
 * Created by hugoh on 27/12/2016.
 */

import org.junit.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import xyz.hugoh.viw.Mesh;
import xyz.hugoh.viw.io.OBJProcessor;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.system.MemoryUtil.NULL;

public class OBJProcessorTest {
    OBJProcessor processor;

    @Before
    public void setUp() {
        GLFW.glfwInit();
        long windowHandle = glfwCreateWindow(100, 100, "", NULL, NULL);
        glfwMakeContextCurrent(windowHandle);
        GL.createCapabilities();
        processor = new OBJProcessor();
    }

    @Test
    public void noFileReturnsEmptyMesh() {
        assertTrue(processor.load3DObject("").getName().isEmpty());
    }

    @Test
    public void nameIsCorrect() {
        Mesh m = processor.load3DObject("models/cube.obj");
        assertEquals(m.getName(), "cube");
    }

}
