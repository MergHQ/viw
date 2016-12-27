package xyz.hugoh.viw.math;

/**
 * Created by hugoh on 27/12/2016.
 */
public class Array {
    public Array() {}

    public static float[] mul(float[] a, float[] b) {
        if (a.length != b.length)
            throw new IllegalArgumentException("Array sizes do not match");
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; ++i)
            res[i] = a[i] * b[i];
        return res;
    }

    public static float[] mulFloat(float[] a, float b) {
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; ++i)
            res[i] = a[i] * b;
        return res;
    }

    public static float[] sum(float[] a, float[] b) {
        if (a.length != b.length)
            throw new IllegalArgumentException("Array sizes do not match");
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; ++i)
            res[i] = b[i] + a[i];
        return res;
    }
}
