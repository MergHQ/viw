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

    public void setRotation(float[] vec, float theta) {
        float[] res = new float[16];
        new Matrix4f().identity().translate(transformationMatrix[3][0], transformationMatrix[3][1], transformationMatrix[3][2]).rotate(theta, vec[0], vec[1], vec[2]).get(res);
        float[][] twoDimensional = {
                {res[0], res[1], res[2], res[3]},
                {res[4], res[5], res[6], res[7]},
                {res[8], res[9], res[10], res[11]},
                {res[12], res[13], res[14], res[15]}
        };
        transformationMatrix = twoDimensional;
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
