package xyz.hugoh.viw;

import xyz.hugoh.viw.entity.BasicCamera;
import xyz.hugoh.viw.renderer.Window;

public class App {
    public static void main(String[] args) {
        Window window = new Window();
        window.setCamera(new BasicCamera(60.f, 800.f / 600.f, 0.1f, 1000.f));
        window.create(800, 600);
    }
}