package xyz.hugoh.viw.renderer;

import org.junit.Before;
import org.junit.Test;
import xyz.hugoh.viw.Scene;

import static org.junit.Assert.*;

/**
 * Created by hugoh on 15/01/2017.
 */
public class RendererTest {
    Renderer renderer;

    @Before
    public void setUp() {
        renderer = new Renderer();
    }

    @Test
    public void sceneIsNull() {
        assertNull(renderer.getCurrentScene());
    }

    @Test
    public void sceneIsNotNullAfterSet() {
        renderer.setScene(new Scene(null));
        assertNotNull(renderer.getCurrentScene());
    }
}
