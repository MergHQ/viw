package xyz.hugoh.viw.renderer;

import xyz.hugoh.viw.Scene;

/**
 * Created by Hugo on 26.12.2016.
 */
public class Renderer {
    private Scene currentScene;
    private Window activeWindow;

    public Renderer(Window w) {
        this.activeWindow = w;
    }

    public void render() {
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public Window getActiveWindow() {
        return activeWindow;
    }

    public void setWindow(Window activeWindow) {
        this.activeWindow = activeWindow;
    }
}
