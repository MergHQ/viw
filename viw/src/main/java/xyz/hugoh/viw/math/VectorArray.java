package xyz.hugoh.viw.math;

/**
 * Utils for arrays treated as vectors.
 */
public class VectorArray {
    /**
     * Multiply vector elements with each other.
     * @param a matrix a
     * @param b matrix b
     * @return new array with the products
     */
    public static float[] mul(float[] a, float[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("VectorArray sizes do not match");
        }
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; ++i) {
            res[i] = a[i] * b[i];
        }
        return res;
    }

    /**
     * Multiply array with float.
     * @param a array of floats
     * @param b scalar value
     * @return product
     */
    public static float[] mulFloat(float[] a, float b) {
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; ++i) {
            res[i] = a[i] * b;
        }
        return res;
    }

    /**
     * Sum of vectos elements.
     * @param a vector a
     * @param b vector b
     * @return array with sums
     */
    public static float[] sum(float[] a, float[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("VectorArray sizes do not match");
        }
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; ++i) {
            res[i] = b[i] + a[i];
        }
        return res;
    }

    /**
     * Difference of vectos elements.
     * @param a vector a
     * @param b vector b
     * @return array with differences
     */
    public static float[] diff(float[] a, float[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("VectorArray sizes do not match");
        }
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; ++i) {
            res[i] = a[i] - b[i];
        }
        return res;
    }

    /**
     * Calculates a dot product.
     * @param a vector a
     * @param b vector b
     * @return dot product
     */
    public static float dot(float a[], float b[]) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("VectorArray sizes do not match");
        }
        float sum = 0f;
        for (int i = 0; i < a.length; ++i) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    /**
     * Cross product on three dimensional vectors.
     * @param a 3d vector
     * @param b 3d vector
     * @return cross product
     */
    public static float[] cross3(float[] a, float[] b) {
        float[] res = {
            a[1] * b[2] - b[1] * a[2],
            a[2] * b[0] - b[2] * a[0],
            a[0] * b[1] - b[0] * a[1]
        };
        return res;
    }

    /**
     * Calculates length of a vector.
     * @param a vector
     * @return length
     */
    public static float length(float[] a) {
        float sum = 0;
        for (int i = 0; i < a.length; ++i) {
            sum += Math.pow(a[i], 2);
        }
        return (float) Math.sqrt(sum);
    }

    /**
     * Turns a vector into a unit vector.
     * @param a vector
     * @return unit vector
     */
    public static float[] normalize(float[] a) {
        return mulFloat(a, 1.f / length(a));
    }
}
