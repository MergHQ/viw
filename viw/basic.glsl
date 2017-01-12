#version 450

layout(location = 0) in vec3 vertexPosition;
layout(location = 1) in vec3 normal;

uniform mat4 u_transformationMatrix;
uniform mat4 u_projectionMatrix;
uniform mat4 u_viewMatrix;
uniform vec3 nigger;

out vec3 normalOut;
out vec4 debugColor;

void main () {
    mat4 mvp = u_projectionMatrix * u_viewMatrix * u_transformationMatrix;
    normalOut = normal;
    debugColor = -u_viewMatrix[2];
    gl_Position = vec4(vertexPosition, 1.0);
}

@

#version 450

in vec4 debugColor;

out vec4 finalColor;

void main() {
    finalColor = debugColor;
}  