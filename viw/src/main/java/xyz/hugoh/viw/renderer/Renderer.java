package xyz.hugoh.viw.renderer;

import xyz.hugoh.viw.Scene;
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
