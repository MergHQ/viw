# Diagrams

## Class diagram
![Class diagram](img/classdiagram.png)

### yUML script
```
[Renderer]1-1[Scene], [Scene]*-1[\<\<interface\>\> | Camera], [Window], [OBJProcessor]-.->[Mesh], [ShaderProcessor]-.->[Shader], [Camera]1-1[Window], [Window]1-1[Renderer]
[Shader]1-1[Scene]
[Mesh]*-1[Scene]
[Camera]-.->[\<\<interface\>\> | Camera]
[ArrayVector]
[Matrix]
```
