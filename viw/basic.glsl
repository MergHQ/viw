#version 450

void main () {
    gl_Position = vec4(1,1,1,1);
};

@

#version 450

out vec4 finalColor;

void main() {
    finalColor = vec4(1,0,0,0);
}  