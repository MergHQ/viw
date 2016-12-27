package xyz.hugoh.viw;

/**
 * Created by Hugo on 27.12.2016.
 */

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Test;
import static org.junit.Assert.*;

import xyz.hugoh.viw.math.*;

public class MatricesTest {

    @Test
    public void equallySizedMatricesReturnCorrectProduct() {
        float[][] a = {{2,2}, {1,2}};
        float[][] b = {{3,3}, {1,3}};
        float[][] res = Matrices.mul(a, b);
        assertTrue(res[0][0] == 8f && res[0][1] == 12f && res[1][0] == 5f && res[1][1] == 9f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidOperationThrowsException() {
        float[][] a = {{2,2}, {1,2}};
        float[][] b = {{3,3}, {1,3}, {2,5}};
        Matrices.mul(a, b);
    }

    @Test
    public void nonEqualMatricesStillWorks() {
        float[][] a = {{2,2}, {1,2}};
        float[][] b = {{3,3}};
        float[][] res = Matrices.mul(b, a);
        assertTrue(res[0][0] == 9f && res[0][1] == 12f);
    }

}
