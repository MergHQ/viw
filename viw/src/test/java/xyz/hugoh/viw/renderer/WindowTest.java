package xyz.hugoh.viw.renderer;

import org.junit.Before;
import org.junit.Test;
import xyz.hugoh.viw.renderer.Window;

import static org.junit.Assert.*;

/**
 * Created by hugoh on 27/12/2016.
 */

public class WindowTest {

    Window window;

    @Before
    public void setUp() {
        window = new Window();
    }

    @Test
    public void cameraIsNullOnInit() {
        assertTrue(window.getCamera() == null);
    }

}
