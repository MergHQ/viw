# Diagrams

## Class diagram
![Class diagram](/docs/img/classdiagram.png)

### yUML script
```
[Renderer]1-1[Scene], [Scene]*-1[\<\<interface\>\> | Camera], [Window], [OBJProcessor]-.->[Mesh], [ShaderProcessor]-.->[Shader], [Mesh]*-1[Shader], [Camera]1-1[Window], [Window]1-1[Renderer]
```