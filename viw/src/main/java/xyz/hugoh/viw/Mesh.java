package xyz.hugoh.viw;

/**
 * Represents a mesh.
 */

import xyz.hugoh.viw.math.Matrix;

public class Mesh {
    private String name;
    private float[][] transformationMatrix;
    private int vertexArrayObject;

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

    public int getVertexArrayObject() {
        return vertexArrayObject;
    }

    public void setVertexArrayObject(int vertexArrayObject) {
        this.vertexArrayObject = vertexArrayObject;
    }
}
