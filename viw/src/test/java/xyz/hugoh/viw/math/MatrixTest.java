package xyz.hugoh.viw.math;

/**
 * Created by Hugo on 27.12.2016.
 */

import org.junit.Test;
import static org.junit.Assert.*;

import xyz.hugoh.viw.math.*;

import java.util.Arrays;

public class MatrixTest {

    @Test
    public void equallySizedMatricesReturnCorrectProduct() {
        float[][] a = {{3,3}, {1,3}};
        float[][] b = {{2,2}, {1,2}};
        float[][] res = Matrix.mul(a, b);
        assertTrue(res[0][0] == 8f && res[0][1] == 12f && res[1][0] == 5f && res[1][1] == 9f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidOperationThrowsException() {
        float[][] a = {{2,2}, {1,2}};
        float[][] b = {{3,3,1}, {1,3,2}, {2,5,3}};
        Matrix.mul(a, b);
    }

    @Test
    public void nonEqualMatricesStillWorks() {
        float[][] a = {{2,2}, {1,2}};
        float[][] b = {{3,3}};
        float[][] res = Matrix.mul(a, b);
        assertTrue(res[0][0] == 9f && res[0][1] == 12f);
    }

    @Test
    public void testTranslate() {
        float[] a = {1.2f, 3.33f, 2.3f, 0};
        float[][] mat = Matrix.translate4x4(Matrix.identityMatrix44(), a);
        float[][] res = Matrix.identityMatrix44();
        res[3] = a;
        res[3][3] = 1.0f;
        assertTrue(Arrays.deepEquals(mat, res));
    }

    @Test
    public void lookAtWorks() {
        float[] pos = {0f, 1f, 1f};
        float[] up = {0f, 1f, 0f};
        float[] center = {0f,0f,0f};
        float[][] matrix = Matrix.lookAt(pos, up, center);
        assertTrue(matrix[0][0] == 0.99999994f);
    }

    @Test
    public void perspectiveWorks() {
        float[][] a = Matrix.perspective(60.f, 1280.f / 720.f, 0.0f, 1000.0f);
        assertTrue(a[1][1] == -0.15611996f);
    }

    @Test
    public void toFloatBufferWorks() {
        float[] res = {1f, 1f, 1f, 1f};
        float[][] mat =  {{1f, 1f}, {1f, 1f}};
        assertTrue(Arrays.equals(Matrix.toFloatBuffer(mat).array(), res));
    }

}
