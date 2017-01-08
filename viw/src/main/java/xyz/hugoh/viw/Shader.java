package xyz.hugoh.viw;

/**
 * Wraps a OpenGL shader program
 */
public class Shader {
    private int shaderProgramHandle;

    public Shader() {
    }
    public int getShaderProgramHandle() {
        return shaderProgramHandle;
    }

    public void setShaderProgramHandle(int shaderProgramHandle) {
        this.shaderProgramHandle = shaderProgramHandle;
    }
}
