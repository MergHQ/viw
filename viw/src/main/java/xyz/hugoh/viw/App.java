package xyz.hugoh.viw;

import xyz.hugoh.viw.entity.BasicCamera;
import xyz.hugoh.viw.io.OBJProcessor;
import xyz.hugoh.viw.io.ShaderProcessor;
import xyz.hugoh.viw.renderer.Renderer;
import xyz.hugoh.viw.renderer.Window;

import javax.swing.*;
import java.awt.*;

public class App {
    /**
     * It should pretty clear what this method is.
     * @param args it should also be pretty clear what this is.
     */
    public static void main(String[] args) {
        String file = getFile();
        Window window = new Window();
        window.setCamera(new BasicCamera((float) Math.toRadians(60.0), 1280.f / 720.f, 0.1f, 1000.f));
        window.create(1280, 720, () -> {
            Renderer renderer = new Renderer();

            Scene scene = new Scene(window);
            OBJProcessor objProcessor = new OBJProcessor();
            Mesh m  = objProcessor.load3DObject(file != null ? file : "models/sphere.obj");
            m.setPosition(1f,1f,1f);
            System.out.println(m.getName());
            scene.addMesh(m);

            ShaderProcessor shaderProcessor = new ShaderProcessor();
            Shader s = shaderProcessor.loadShader("basic.glsl");
            System.out.println(s.getShaderProgramHandle());
            scene.setShader(s);

            renderer.setScene(scene);
            window.setRenderer(renderer);
            return 0;
        });

    }

    private static String getFile() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        FileDialog fileDialog = new FileDialog(frame, "Choose a obj file", FileDialog.LOAD);
        fileDialog.setFile("*.obj");
        fileDialog.setVisible(true);
        frame.dispose();
        return fileDialog.getDirectory() + fileDialog.getFile();
    }
}