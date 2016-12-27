package xyz.hugoh.viw;

/**
 * Created by Hugo on 26.12.2016.
 */

import xyz.hugoh.viw.math.Matrix;

public class Mesh {
    private String name;
    private float[][] transformationMatrix;

    public Mesh(String name) {
        this.name = name;
        this.transformationMatrix = Matrix.identityMatrix44();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(float x, float y, float z) {
        float[] converted = {x, y, z, 0.f};
        transformationMatrix = Matrix.translate4x4(transformationMatrix, converted);
    }

    public float[] getPosition() {
        return transformationMatrix[3];
    }

    public float[][] getTransformationMatrix() {
        return transformationMatrix;
    }

    public void prepare(float[] vertices, float[] normals, int[] indices) {

    }
}
