package xyz.hugoh.viw;

/**
 * Represents a mesh.
 */

import org.joml.Matrix4f;
import xyz.hugoh.viw.math.Matrix;

public class Mesh {
    private String name;
    private float[][] transformationMatrix;
    private int indices;
    private int vertexArrayObject;

    /**
     * Instantiates a new {@link Mesh} instance.
     * @param name name of the mesh
     */
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

    /**
     * Sets the position of the mesh.
     * @param x x value
     * @param y y value
     * @param z z value
     */
    public void setPosition(float x, float y, float z) {
        float[] converted = {x, y, z, 1.f};
        //float[] res = new float[16];
        //new Matrix4f().identity().translate(x, y, z).get(res);
        transformationMatrix = Matrix.translate4x4(Matrix.identityMatrix44(), converted);
    }

    public float[] getPosition() {
        return transformationMatrix[3];
    }

    public float[] getTransformationMatrix() {
        return Matrix.to1Darray(transformationMatrix);
    }

    public int getVertexArrayObject() {
        return vertexArrayObject;
    }

    public void setVertexArrayObject(int vertexArrayObject) {
        this.vertexArrayObject = vertexArrayObject;
    }

    public int getIndices() {
        return indices;
    }

    public void setIndices(int indices) {
        this.indices = indices;
    }
}
