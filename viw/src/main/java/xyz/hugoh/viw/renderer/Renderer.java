package xyz.hugoh.viw.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import xyz.hugoh.viw.Scene;


import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Class that handles the rendering of a scene.
 */
public class Renderer {
    private Scene currentScene;
    public int time = 0;

    /**
     * Creates a new {@link Renderer} instance.
     */
    public Renderer() {
    }

    /**
     * Called from the window loop.
     */
    public void render() {
        time++;
        glUseProgram(currentScene.getShader().getShaderProgramHandle());
        currentScene.getMeshList().forEach(mesh -> {
            Window window = currentScene.getWindow();
            window.getCamera().update();

            glEnableVertexAttribArray(0);
            glEnableVertexAttribArray(1);
            GL30.glBindVertexArray(mesh.getVertexArrayObject());

            int tmUniformHandle = glGetUniformLocation(currentScene.getShader().getShaderProgramHandle(), "u_transformationMatrix");
            int pmUniformHandle = glGetUniformLocation(currentScene.getShader().getShaderProgramHandle(), "u_projectionMatrix");
            int vmUniformHandle = glGetUniformLocation(currentScene.getShader().getShaderProgramHandle(), "u_viewMatrix");
            int camPosUniformHandle = glGetUniformLocation(currentScene.getShader().getShaderProgramHandle(), "u_cameraPos");

            glUniformMatrix4fv(tmUniformHandle, false, mesh.getTransformationMatrix());

            glUniformMatrix4fv(pmUniformHandle, false, window.getCamera().getProjectionMatrix());
            glUniformMatrix4fv(vmUniformHandle, false, window.getCamera().getViewMatrix());

            float[] pos = window.getCamera().getPosition();
            glUniform3f(camPosUniformHandle, pos[0], pos[1], pos[2]);

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
