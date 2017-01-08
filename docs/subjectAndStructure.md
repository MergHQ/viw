# Subject

Viw is a OBJ viewer written in Java. Viw comes with a orbiting camera and simple Blinn-Phong lighting model. Currently Viw doesn't support textures.


**Subject:** Software to view OBJ files.
**Users:** Generic user.

**User actions**
- Specify file to load
    - Succesfull if file exists and data is valid.
- Parse file
- Render file

# Diagrams

## Class diagram

![Class diagram](img/classdiagram.png)

### yUML script
```
[Renderer]1-1[Scene], [Scene]*-1[\<\<interface\>\> | Camera], [Window], [OBJProcessor]creates-.->[Mesh], [ShaderProcessor]creates-.->[Shader], [Camera]1-1[Window], [Window]1-1[Renderer], [Shader]1-1[Scene], [Mesh]*-1[Scene], [Camera]-.->[\<\<interface\>\> | Camera], [ArrayVector], [Matrix]
[Mesh]uses-.->[Matrix]
[Matrix]uses-.->[ArrayVector]
```

## Sequence diagrams

### Creating 3d model

![Sequence diagram](img/seq1.png)

### Creating window

![Sequence diagram](img/seq2.png)
