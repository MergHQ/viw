#version 450

layout(location = 0) in vec3 vertexPosition;
layout(location = 1) in vec3 normal;

uniform mat4 u_transformationMatrix;
uniform mat4 u_projectionMatrix;
uniform mat4 u_viewMatrix;
uniform vec3 nigger;

out vec3 v_normal;
out vec3 v_position;
out vec4 debugColor;

void main () {
    mat4 mvp = u_projectionMatrix * u_viewMatrix * u_transformationMatrix;
    v_normal = (u_transformationMatrix * vec4(normal, 0.0)).xyz;
    v_position = (u_transformationMatrix * vec4(vertexPosition, 1.0)).xyz;
    gl_Position = mvp * vec4(vertexPosition, 1.0);
}

@

#version 450

in vec3 v_normal;
in vec3 v_position;

out vec4 finalColor;

void main() {
    vec3 lightPos = vec3(10,10,10);
    vec3 lightRay = normalize(lightPos - v_position);
    float dist = dot(v_normal, lightRay);
    finalColor = vec4(v_normal, 1);
}  