package xyz.hugoh.viw;

import xyz.hugoh.viw.renderer.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * Represents a Scene.
 */
public class Scene {
    private List<Mesh> meshList;
    private Shader shader;
    private Window window;

    /**
     * Creates a new {@link Scene} instance.
     * @param window active window
     */
    public Scene(Window window) {
        this.window = window;
        meshList = new ArrayList<>();
    }

    /**
     * Adds a new mesh to the scene.
     * @param m render-ready mesh.
     */
    public void addMesh(Mesh m) {
        meshList.add(m);
    }

    /**
     * Gets a mesh by name.
     * @param name name of the mesh
     * @return Mesh
     */
    public Mesh getMesh(String name) {
        return meshList.stream()
                .filter(m -> m.getName().equals(name))
                .limit(1)
                .collect(Collectors.toList())
                .get(0);
    }

    public List<Mesh> getMeshList() {
        return meshList;
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    public Window getWindow() {
        return window;
    }
}
