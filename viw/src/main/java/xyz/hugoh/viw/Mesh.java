package xyz.hugoh.viw;

/**
 * Created by Hugo on 26.12.2016.
 */

import xyz.hugoh.viw.math.Matrices;

public class Mesh {
    private String name;
    private float[][] transformationMatrix;

    public Mesh(String name) {
        this.name = name;
        this.transformationMatrix = Matrices.identityMatrix44();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(float[] pos3d) {
    }

    public void prepare(float[] vertices, float[] normals, int[] indices) {

    }
}
