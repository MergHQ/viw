package xyz.hugoh.viw.math;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

/**
 * Utils for matrices.
 */
public class Matrix {
    /**
     * Creates a 4x4 identity matrix.
     * @return matrix [col][row]
     */
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

    /**
     * Does a matrix multiplication.
     * @param a matrix
     * @param b matrix
     * @return product
     */
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

    /**
     * Does a translation on a transformation matrix.
     * @param matrix transformation matrix
     * @param vector vector where to move
     * @return translated matrix
     */
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

    /**
     * Creates a view matrix.
     * @param pos position of camera
     * @param up up vector
     * @param center where the camera looks at
     * @return view matrix
     */
    public static float[][] lookAt(float[] pos, float[] up, float[] center) {
        float[] viewray = VectorArray.normalize(VectorArray.diff(center, pos));
        float[] right = VectorArray.normalize(VectorArray.cross3(viewray, up));
        up = VectorArray.cross3(right, viewray);
        float[][] res = {
                {right[0], up[0], viewray[0], 0.0f},
                {right[1], up[1], viewray[1], 0.0f},
                {right[2], up[2], viewray[2], 0.f},
                {-VectorArray.dot(right, pos), -VectorArray.dot(up, pos), VectorArray.dot(viewray, pos), 1f}
        };
        return res;
    }

    /**
     * Create a projection matrix.
     * @param fov field of view in degrees
     * @param aspect aspect ratio (width / height)
     * @param near near-field
     * @param far far-field
     * @return projection matrix
     */
    public static float[][] perspective(float fov, float aspect, float near, float far) {
        final float tanHalfFov = (float) Math.tan(fov / 2.f);
        float[][] res = {
            {1.f / (aspect * tanHalfFov), 0f, 0f, 0f},
            {0f, 1.f / tanHalfFov, 0f, 0f},
            {0f, 0f, (far + near) / (near - far), -1f},
            {0f, 0f, (2f * far) * near / (near - far), 0f}
        };
        return res;
    }

    /**
     * Rotate transformation matrix
     * @param vec rotation vector
     * @param deg degrees to rotate
     * @return matrix.
     */
    public static float[][] rotate(float[] vec, float deg) {
        // TODO: Fully implement.
        float[][] res = Matrix.identityMatrix44();
        deg = (float) Math.toRadians(deg);
        float cosTheta = (float) Math.cos(deg);
        float sinTheta = (float) Math.sin(deg);
        res[1][1] = cosTheta;
        res[2][1] = -sinTheta;
        res[1][2] = sinTheta;
        res[2][2] = cosTheta;
        float[][] vecTransformed = { {vec[0], vec[1], vec[2], 0.f} };
        res = Matrix.mul(res, vecTransformed);
        return res;
    }

    /**
     * Convert matrix to float buffer.
     * @param matrix matrix to be converted
     * @return float buffer of the matrix.
     */
    public static FloatBuffer toFloatBuffer(float[][] matrix) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(matrix.length * matrix[0].length);
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                buffer.put(matrix[j][i]);
            }
        }

        return buffer;
    }

    public static float[] to1Darray(float[][] matrix) {
        float[] res = new float[matrix.length * matrix[0].length];
        int ind = 0;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                res[ind] = matrix[i][j];
                ++ind;
            }
        }
        return res;
    }
}

