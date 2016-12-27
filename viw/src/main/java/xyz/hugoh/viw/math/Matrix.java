package xyz.hugoh.viw.math;

/**
 * Created by Hugo on 27.12.2016.
 */
public class Matrix {

    public Matrix() {
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
        int aRows = a[0].length;
        int bRows = b[0].length;
        int aCols = a.length;
        int bCols = b.length;

        float[][] newMatrix = new float[bCols][aRows];

        if (aCols != bRows) {
            throw new IllegalArgumentException("Matrix a's column count doesn't match matrix b's row count");
        }

        for (int i = 0; i < aRows; ++i) {
            for (int j = 0; j < aCols; ++j) {
                for (int k = 0; k < bCols; ++k) {
                    newMatrix[k][i] += a[j][i] * b[k][j];
                }
            }
        }
        return newMatrix;
    }

    public static float[][] translate4x4(float[][] matrix, float[] vector) {
        float[][] newMatrix = new float[4][4];
        System.arraycopy(matrix, 0, newMatrix, 0, matrix.length);
        float[] a = Array.mulFloat(matrix[0], vector[0]);
        float[] b = Array.mulFloat(matrix[1], vector[1]);
        float[] c = Array.mulFloat(matrix[2], vector[2]);
        float[] sum = Array.sum(a, b);
        sum = Array.sum(sum, c);
        sum = Array.sum(sum, matrix[3]);
        newMatrix[3] = sum;
        return newMatrix;
    }
}

