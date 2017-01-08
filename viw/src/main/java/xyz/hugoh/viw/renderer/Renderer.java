package xyz.hugoh.viw.renderer;

import xyz.hugoh.viw.Scene;
import xyz.hugoh.viw.Shader;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 * Class that handles the rendering of a scene
 */
public class Renderer {
    private Scene currentScene;

    public Renderer() {
    }

    public void render() {
        glUseProgram(currentScene.getShader().getShaderProgramHandle());
        currentScene.getMeshList().forEach(mesh -> {
            // TODO: Implement rendering
        });
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

}
