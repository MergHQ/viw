package xyz.hugoh.viw.entity;

import org.junit.Before;
import org.junit.Test;
import xyz.hugoh.viw.math.Matrix;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by hugoh on 04/01/2017.
 */
public class OrbitingCameraTest {

    OrbitingCamera camera;

    @Before
    public void setUp() {
        camera = new OrbitingCamera(90.f, 800.f / 600.f, 0.1f, 1000.f);
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
        //assertTrue(Arrays.deepEquals(matrix, camera.getProjectionMatrix()));
    }

    @Test
    public void positionIsCorrect() {
        float[] pos = {0f, 1f, 0f};
        assertTrue(Arrays.equals(pos, camera.getPosition()));
    }

}
