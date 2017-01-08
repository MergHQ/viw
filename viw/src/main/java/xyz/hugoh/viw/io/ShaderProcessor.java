package xyz.hugoh.viw.io;

import xyz.hugoh.viw.Shader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Reads a shader file and create a Shader object.
 */
public class ShaderProcessor {

    /**
     * Loads and parses a shader file.
     *
     * @param file shader file to load
     * @return render-ready shader
     */
    public Shader loadShader(String file) {
        Shader shader = new Shader();
        StringBuilder source = new StringBuilder();
        try {
            Stream<String> stream = Files.lines(Paths.get(file));
            stream.forEach(line -> source.append(line).append('\n'));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        createShaderProgram(shader, source.toString().split("@"));
        return shader;
    }

    private void createShaderProgram(Shader s, String[] source) {
        String vsSource = source[0];
        String fsSource = source[1];

        int vsId = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vsId, vsSource);
        glCompileShader(vsId);
        int status = glGetShaderi(vsId, GL_COMPILE_STATUS);
        if (status == 0) {
            throw new RuntimeException("Error compiling vertex shader: \n" + glGetShaderInfoLog(vsId, 1024));
        }

        int fsId = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fsId, fsSource);
        glCompileShader(fsId);
        status = glGetShaderi(fsId, GL_COMPILE_STATUS);
        if (status == 0) {
            throw new RuntimeException("Error compiling fragment shader: \n" + glGetShaderInfoLog(fsId, 1024));
        }

        int shaderProgramHandle = glCreateProgram();
        glAttachShader(shaderProgramHandle, vsId);
        glAttachShader(shaderProgramHandle, fsId);
        glLinkProgram(shaderProgramHandle);
        if (glGetProgrami(shaderProgramHandle, GL_LINK_STATUS) == GL_FALSE) {
            System.err.println("Program failed to link:");
            System.err.println(
                    glGetProgramInfoLog(
                            shaderProgramHandle,
                            glGetProgrami(
                                    shaderProgramHandle, GL_INFO_LOG_LENGTH)
                    )
            );
        }

        s.setShaderProgramHandle(shaderProgramHandle);
        glDeleteProgram(fsId);
        glDeleteProgram(vsId);
    }
}
