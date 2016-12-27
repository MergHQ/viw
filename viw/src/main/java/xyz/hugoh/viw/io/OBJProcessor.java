package xyz.hugoh.viw.io;

import xyz.hugoh.viw.Mesh;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Hugo on 26.12.2016.
 */
public class OBJProcessor {
    public OBJProcessor() {}

    public Mesh load3DObject(String file) {
        Mesh mesh = new Mesh("");
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            stream.forEach(line -> {
                String[] lineArgs = line.split(" ");
                if (lineArgs[0] != "#") {
                    switch (lineArgs[0]) {
                        case "g": {
                            // Set mesh name
                            break;
                        }
                        case "v": {
                            // vertex
                            break;
                        }
                        case "vn": {
                            // normal
                            break;
                        }
                        case "f": {
                            // index
                            break;
                        }
                    }
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return mesh;
    }
}
