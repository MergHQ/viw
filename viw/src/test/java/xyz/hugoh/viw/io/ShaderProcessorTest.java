package xyz.hugoh.viw.io;

import org.junit.*;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import xyz.hugoh.viw.Shader;
import xyz.hugoh.viw.io.ShaderProcessor;

import static org.junit.Assert.*;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by hugoh on 08/01/2017.
 */
public class ShaderProcessorTest {
    ShaderProcessor processor;

    @Before
    public void setUp() {
        GLFW.glfwInit();
        long windowHandle = glfwCreateWindow(100, 100, "", NULL, NULL);
        glfwMakeContextCurrent(windowHandle);
        GL.createCapabilities();
        processor = new ShaderProcessor();
    }

    @Test
    public void processorCreatesProgram() {
        Shader s = processor.loadShader("basic.glsl");
        assertTrue(s.getShaderProgramHandle() != 0);
    }

    @Test(expected = RuntimeException.class)
    public void faultyShaderIsNotLoaded() {
        processor.loadShader("errorshader.glsl");
    }

}
