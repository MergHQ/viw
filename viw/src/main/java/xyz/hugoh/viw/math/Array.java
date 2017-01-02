package xyz.hugoh.viw.math;

/**
 * Created by hugoh on 27/12/2016.
 */
public class Array {
    public Array() {}

    public static float[] mul(float[] a, float[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Array sizes do not match");
        }
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; ++i) {
            res[i] = a[i] * b[i];
        }
        return res;
    }

    public static float[] mulFloat(float[] a, float b) {
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; ++i) {
            res[i] = a[i] * b;
        }
        return res;
    }

    public static float[] sum(float[] a, float[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Array sizes do not match");
        }
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; ++i) {
            res[i] = b[i] + a[i];
        }
        return res;
    }

    public static float[] diff(float[] a, float[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Array sizes do not match");
        }
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; ++i) {
            res[i] = b[i] - a[i];
        }
        return res;
    }

    public static float dot(float a[], float b[]) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Array sizes do not match");
        }
        float sum = 0f;
        for (int i = 0; i < a.length; ++i) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    public static float[] cross3(float[] a, float[] b) {
        float[] res = {
            (a[1] * b[2]) - (b[1] * a[2]),
            (a[2] * b[0]) - (b[2] * a[0]),
            (a[0] * b[1]) - (b[0] * a[1])
        };
        return res;
    }

    public static float length(float[] a) {
        float sum = 0;
        for (int i = 0; i < a.length; ++i) {
            sum += Math.pow(a[i], 2);
        }
        return (float) Math.sqrt(sum);
    }

    public static float[] normalize(float[] a) {
        return mulFloat(a, 1 / length(a));
    }
}
