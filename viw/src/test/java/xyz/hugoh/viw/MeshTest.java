package xyz.hugoh.viw;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import xyz.hugoh.viw.math.Matrix;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by hugoh on 27/12/2016.
 */
public class MeshTest {

    Mesh mesh;

    @Before
    public void setUp() {
        mesh = new Mesh("");
    }

    @Test
    public void transformationMatrixIsIdentitymatrix() {
        assertTrue(Arrays.deepEquals(Matrix.identityMatrix44(), mesh.getTransformationMatrix()));
    }

    @Test
    public void changingPositionWorks() {
        mesh.setPosition(1.2f, 3.33f, 2.3f);
        float[] array = {1.2f, 3.33f, 2.3f, 0.f};
        assertTrue(Arrays.deepEquals(mesh.getTransformationMatrix(), Matrix.translate4x4(Matrix.identityMatrix44(), array)));
    }

    @Test
    public void changingPositionWorks2() {
        float[] array = {1.2f, 3.33f, 2.3f, 0.f};
        mesh.setPosition(array[0], array[1], array[2]);
        mesh.setPosition(array[0], array[1], array[2]);
        float[][] mat = mesh.getTransformationMatrix();
        assertTrue(mat[3][0] == 2.4f && mat[3][1] == 6.66f && mat[3][2] == 4.6f);
    }

}
