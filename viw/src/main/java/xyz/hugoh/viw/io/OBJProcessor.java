package xyz.hugoh.viw.io;

import org.lwjgl.BufferUtils;
import xyz.hugoh.viw.Mesh;

import java.io.IOException;
import java.lang.reflect.Type;
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
 * Created by Hugo on 26.12.2016.
 */

public class OBJProcessor {
    private List<Float> vertices;
    private List<Float> normals;
    private List<Integer> indices;

    public OBJProcessor() {
        vertices = new ArrayList<>();
        normals = new ArrayList<>();
        indices = new ArrayList<>();
    }

    public Mesh load3DObject(String file) {
        Mesh mesh = new Mesh("");
        try {
            Stream<String> stream = Files.lines(Paths.get(file));
            stream.forEach(line -> processLine(line, mesh));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //createGLHandles(mesh);
        return mesh;
    }

    private void createGLHandles(Mesh m) {
        // I can't believe there isn't any good way to do this.
        double[] vertexArray = vertices.stream().mapToDouble(f -> f).toArray();
        double[] normalArray = normals.stream().mapToDouble(f -> f).toArray();
        int[] indexArray = indices.stream().mapToInt(i -> i).toArray();
        int vao = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vao);

        // Vertices
        GL20.glEnableVertexAttribArray(0);
        IntBuffer buffer = IntBuffer.allocate(1);
        GL15.glGenBuffers(buffer);
        int vbo = buffer.get(0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
        DoubleBuffer vertexData = BufferUtils.createDoubleBuffer(vertexArray.length * 3);
        vertexData.put(vertexArray);
        vertexData.flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexData, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3,GL11.GL_DOUBLE, false, GL11.GL_FALSE, 32);

        // Normals
        GL20.glEnableVertexAttribArray(1);
        buffer = IntBuffer.allocate(1);
        GL15.glGenBuffers(buffer);
        int nbo = buffer.get(0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, nbo);
        DoubleBuffer normalData = BufferUtils.createDoubleBuffer(normalArray.length * 3);
        vertexData.put(normalArray);
        vertexData.flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, normalData, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3,GL11.GL_DOUBLE, false, GL11.GL_FALSE, 32);

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
                        vertices.add(Float.parseFloat(lineArgs[2]));
                        vertices.add(Float.parseFloat(lineArgs[4]));
                        vertices.add(Float.parseFloat(lineArgs[6]));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "vn":
                    try {
                        normals.add(Float.parseFloat(lineArgs[2]));
                        normals.add(Float.parseFloat(lineArgs[4]));
                        normals.add(Float.parseFloat(lineArgs[lineArgs.length == 7 ? 6 : 5]));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "f":
                    // index
                    break;
            }
        }
    }

    private static <T> void fromListToArray(ArrayList<?> input, T[] out) {
        if (out.length != input.size()) {
            throw new IllegalArgumentException("Array sizes do no match");
        }
        for (int i = 0; i < input.size(); ++i) {
            out[i] = (T) input.get(i);
        }
    }
}
