# Test documentation

### Why are there a lot of untested classes?

Because many almost every singe OpenGL function can't be tested in a decent way. They return handles, but most of the data is stored on the GPU. 
PIT reports will complain about mutants surviving when calls are removed to OpenGL, so I completely left those out from unit testing.

I also removed callback function from tests.

### How can I test these manually?

Testing OpenGL is usually all about running the program and seeing what is drawn, if anything, on the screen.
