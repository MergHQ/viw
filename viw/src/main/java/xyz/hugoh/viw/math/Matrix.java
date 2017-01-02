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
        float[] a = VectorArray.mulFloat(matrix[0], vector[0]);
        float[] b = VectorArray.mulFloat(matrix[1], vector[1]);
        float[] c = VectorArray.mulFloat(matrix[2], vector[2]);
        float[] sum = VectorArray.sum(a, b);
        sum = VectorArray.sum(sum, c);
        sum = VectorArray.sum(sum, matrix[3]);
        newMatrix[3] = sum;
        return newMatrix;
    }

    public static float[][] lookAt(float[] pos, float[] up, float[] center) {
        float[] viewray = VectorArray.normalize(VectorArray.diff(center, pos));
        float[] right = VectorArray.normalize(VectorArray.cross3(viewray, up));
        up = VectorArray.cross3(right, viewray);
        float[][] res = {
            {right[0], up[0], -viewray[0], -VectorArray.dot(right, pos)},
            {right[1], up[1], -viewray[1], -VectorArray.dot(up, pos)},
            {right[2], up[2], -viewray[2], VectorArray.dot(viewray, pos)},
            {0f,0f,0f,1f}
        };
        return res;
    }

    public static float[][] perspective(float fov, float aspect, float near, float far) {
        float tanHalfFov = (float) Math.tan(fov / 2.f);
        float[][] res = {
            {1.f / (aspect * tanHalfFov), 0f, 0f, 0f},
            {0f, 1.f / tanHalfFov, 0f, 0f},
            {0f, 0f, -(far + near) / (far - near), -1f},
            {0f, 0f, - ((2f * far * near) / (far - near)), 0f}
        };
        return res;
    }
}

