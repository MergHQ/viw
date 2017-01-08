package xyz.hugoh.viw.entity;

import org.junit.Before;
import org.junit.Test;
import xyz.hugoh.viw.entity.BasicCamera;
import xyz.hugoh.viw.math.Matrix;
import xyz.hugoh.viw.renderer.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.concurrent.Callable;

import static org.junit.Assert.*;

/**
 * Created by hugoh on 04/01/2017.
 */
public class BasicCameraTest {

    BasicCamera camera;

    @Before
    public void setUp() {
        camera = new BasicCamera(90.f, 800.f / 600.f, 0.1f, 1000.f);
    }

    @Test
    public void positionIsNotNull() {
        assertNotNull(camera.getPosition());
    }

    @Test
    public void centerIsNotNull() {
        assertNotNull(camera.getCenter());
    }

    @Test
    public void projectionMatrixIsCorrectlyInitialized() {
        float[][] matrix = Matrix.perspective(90.f, 800.f / 600.f, 0.1f, 1000.f);
        assertTrue(Arrays.deepEquals(matrix, camera.getProjectionMatrix()));
    }

    @Test
    public void positionIsCorrect() {
        float[] pos = {0f, 1f, 1f};
        assertTrue(Arrays.equals(pos, camera.getPosition()));
    }

}
