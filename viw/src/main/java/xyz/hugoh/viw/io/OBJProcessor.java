package xyz.hugoh.viw.io;

import org.lwjgl.BufferUtils;
import xyz.hugoh.viw.Mesh;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.Buffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.lwjgl.opengl.*;

/**
 * Loads a OBJ file, parses it and creates a render-ready Mesh object.
 */

public class OBJProcessor {
    private List<Float> vertices;
    private List<Float> normals;
    private List<Integer> indices;

    private float[] normalArray;

    /**
     * Creates a new {@link OBJProcessor} instance.
     */
    public OBJProcessor() {
        vertices = new ArrayList<>();
        normals = new ArrayList<>();
        indices = new ArrayList<>();
    }

    /**
     * Loads and parses a OBJ file.
     * @param file path to the OBJ file
     * @return Mesh render-ready mesh
     */
    public Mesh load3DObject(String file) {
        Mesh mesh = new Mesh("");
        try {
            Stream<String> stream = Files.lines(Paths.get(file));
            stream.forEach(line -> processLine(line, mesh));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        createGLHandles(mesh);
        return mesh;
    }

    private void createGLHandles(Mesh m) {
        if (normalArray == null) {
            return;
        }

        // I can't believe there isn't any good way to do this.
        double[] vertexArray = vertices.stream().mapToDouble(f -> f).toArray();
        int[] indexArray = indices.stream().mapToInt(i -> i).toArray();
        int vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        // Vertices
        GL20.glEnableVertexAttribArray(0);
        int vbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        DoubleBuffer vertexData = BufferUtils.createDoubleBuffer(vertexArray.length * 3);
        vertexData.put(vertexArray);
        vertexData.flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexData, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_DOUBLE, false, GL11.GL_FALSE, 0);

        // Normals
        GL20.glEnableVertexAttribArray(1);
        int nbo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, nbo);
        FloatBuffer normalData = BufferUtils.createFloatBuffer(normalArray.length * 3);
        normalData.put(normalArray);
        normalData.flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, normalData, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, GL11.GL_FALSE, 0);

        // Indices
        int ibo = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
        IntBuffer indexData = BufferUtils.createIntBuffer(indexArray.length);
        indexData.put(indexArray);
        indexData.flip();
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexData, GL15.GL_STATIC_DRAW);
        m.setIndices(indexArray.length);

        m.setVertexArrayObject(vao);
        GL30.glBindVertexArray(0);
    }

    private void processLine(String line, Mesh m) {
        String[] lineArgs = line.split(" ");
        if (lineArgs.length != 0 && !lineArgs[0].equals("#")) {
            switch (lineArgs[0]) {
                case "g":
                    m.setName(lineArgs[1]);
                    break;
                case "v":
                    try {
                        vertices.add(Float.parseFloat(lineArgs[1]));
                        vertices.add(Float.parseFloat(lineArgs[2]));
                        vertices.add(Float.parseFloat(lineArgs[3]));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "vn":
                    try {
                        normals.add(Float.parseFloat(lineArgs[1]));
                        normals.add(Float.parseFloat(lineArgs[2]));
                        normals.add(Float.parseFloat(lineArgs[3]));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "f":
                    if (normalArray == null) {
                        normalArray = new float[vertices.size()];
                    }

                    String[] vert1 = lineArgs[1].split("/");
                    String[] vert2 = lineArgs[2].split("/");
                    String[] vert3 = lineArgs[3].split("/");

                    processIndex(vert1);
                    processIndex(vert2);
                    processIndex(vert3);

                    break;
            }
        }
    }

    private void processIndex(String[] data) {
        int vertexIndex = Integer.parseInt(data[0]) - 1;
        int normalIndex = Integer.parseInt(data[2]) - 1;
        indices.add(vertexIndex);
        normalArray[3 * vertexIndex] = normals.get(normalIndex);
        normalArray[3 * vertexIndex + 1] = normals.get(normalIndex + 1);
        normalArray[3 * vertexIndex + 2] = normals.get(normalIndex + 2);
    }
}
