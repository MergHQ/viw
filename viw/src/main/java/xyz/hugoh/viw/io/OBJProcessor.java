package xyz.hugoh.viw.io;

import xyz.hugoh.viw.Mesh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
            stream.forEach(line -> {
                processLine(line, mesh);
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return mesh;
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
}
