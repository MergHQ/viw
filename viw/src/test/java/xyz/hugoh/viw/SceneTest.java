package xyz.hugoh.viw;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by hugoh on 04/01/2017.
 */
public class SceneTest {
    Scene scene;

    @Before
    public void setUp() {
        scene = new Scene(null);
    }

    @Test
    public void addingMeshWorks() {
        String name = UUID.randomUUID().toString();
        Mesh m = new Mesh(name);
        scene.addMesh(m);
        assertEquals(1, scene.getMeshList().size());
    }

    @Test
    public void gettingMeshByNameWorks() {
        String name = UUID.randomUUID().toString();
        Mesh m = new Mesh(name);
        scene.addMesh(m);
        assertEquals(m.getName(), scene.getMesh(name).getName());
    }

    @Test
    public void shaderIsNull() {
        assertNull(scene.getShader());
    }
}
