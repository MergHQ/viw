package xyz.hugoh.viw.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import xyz.hugoh.viw.Mesh;
import xyz.hugoh.viw.Scene;
import xyz.hugoh.viw.math.Matrix;


import java.util.Arrays;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Class that handles the rendering of a scene.
 */
public class Renderer {
    private Scene currentScene;

    /**
     * Creates a new {@link Renderer} instance.
     */
    public Renderer() {
    }

    /**
     * Called from the window loop.
     */
    public void render() {
        glUseProgram(currentScene.getShader().getShaderProgramHandle());
        currentScene.getMeshList().forEach(mesh -> {
            glEnableVertexAttribArray(0);
            glEnableVertexAttribArray(1);
            GL30.glBindVertexArray(mesh.getVertexArrayObject());

            int tmUniformHandle = glGetUniformLocation(currentScene.getShader().getShaderProgramHandle(), "u_transformationMatrix");
            int pmUniformHandle = glGetUniformLocation(currentScene.getShader().getShaderProgramHandle(), "u_projectionMatrix");
            int vmUniformHandle = glGetUniformLocation(currentScene.getShader().getShaderProgramHandle(), "u_viewMatrix");
            glUniformMatrix4fv(tmUniformHandle, false, Matrix.to1Darray(mesh.getTransformationMatrix()));

            Window window = currentScene.getWindow();
            glUniformMatrix4fv(pmUniformHandle, false, Matrix.to1Darray(window.getCamera().getProjectionMatrix()));
            glUniformMatrix4fv(vmUniformHandle, false, Matrix.to1Darray(window.getCamera().getViewMatrix()));

            GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices(), GL11.GL_UNSIGNED_INT, 0);
            glDisableVertexAttribArray(0);
            glDisableVertexAttribArray(1);
        });
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

}
