package xyz.hugoh.viw.math;

/**
 * Created by Hugo on 27.12.2016.
 */
public class Matrices {

    public Matrices() {
    }

    public static float[][] identityMatrix44() {
        float[][] matrix =
            {
                {1f, 0f, 0f, 0f},
                {0f, 1f, 0f, 0f},
                {0f, 0f, 1f, 0f},
                {0f, 0f, 0f, 1f}
            };
        return matrix;
    }


    public static float[][] mul(float[][] a, float[][] b) {
        int aRows = a.length;
        int bRows = b.length;
        int aCols = a[0].length;
        int bCols = b[0].length;

        float[][] newMatrix = new float[aRows][bCols];

        if (aCols != bRows)
            throw new IllegalArgumentException("Matrix a cols doesn't match matrix b rows");

        for (int i = 0; i < aRows; ++i) {
            for (int j = 0; j < aCols; ++j)
                for (int k = 0; k < bCols; ++k)
                    newMatrix[i][k] += a[i][j] * b[j][k];
        }
        return newMatrix;
    }
}

