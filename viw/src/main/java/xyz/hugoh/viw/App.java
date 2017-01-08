package xyz.hugoh.viw;

import xyz.hugoh.viw.entity.BasicCamera;
import xyz.hugoh.viw.io.OBJProcessor;
import xyz.hugoh.viw.io.ShaderProcessor;
import xyz.hugoh.viw.renderer.Renderer;
import xyz.hugoh.viw.renderer.Window;

import static org.lwjgl.opengl.GL11.GL_VERSION;
import static org.lwjgl.opengl.GL11.glGetString;

public class App {
    /**
     * It should pretty clear what this method is, but checkstyle wants me to document this.
     * @param args it should also be pretty clear what this is.
     */
    public static void main(String[] args) {
        Window window = new Window();
        window.setCamera(new BasicCamera(60.f, 800.f / 600.f, 0.1f, 1000.f));
        window.create(800, 600, () -> {
            Renderer renderer = new Renderer();

            Scene scene = new Scene();
            OBJProcessor objProcessor = new OBJProcessor();
            Mesh m  = objProcessor.load3DObject("cube.obj");
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
}