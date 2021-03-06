#version 330

layout(location = 0) in vec3 vertexPosition;
layout(location = 1) in vec3 normal;

uniform mat4 u_transformationMatrix;
uniform mat4 u_projectionMatrix;
uniform mat4 u_viewMatrix;

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

#version 330

in vec3 v_normal;
in vec3 v_position;

out vec4 finalColor;

uniform vec3 u_cameraPos;

void main() {
    vec3 normal = normalize(v_normal);
    vec3 lightPos = vec3(1000, 1000, 1000);
    vec4 lightColor = vec4(0.7, 0.6, 0.2, 1);
    vec3 lightRay = normalize(lightPos - v_position);
    float dist = dot(v_normal, lightRay);
    dist = clamp(dist, 0.0, 1.0);
    vec4 diffuse = lightColor * dist;
    finalColor = diffuse + vec4(0.1, 0.1, 0.12, 1);
}  