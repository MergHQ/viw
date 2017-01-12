#version 450

layout(location = 0) in vec3 vertexPosition;
layout(location = 1) in vec3 normal;

uniform mat4 u_transformationMatrix;
uniform mat4 u_projectionMatrix;
uniform mat4 u_viewMatrix;

void main () {
    mat4 mvp = u_projectionMatrix * u_viewMatrix * u_transformationMatrix;
    gl_Position = mvp * vec4(vertexPosition, 1.0);
}

@

#version 450

out vec4 finalColor;

void main() {
    finalColor = vec4(1,0,0,0);
}  