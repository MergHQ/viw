package xyz.hugoh.viw;

/**
 * Created by hugoh on 27/12/2016.
 */

import org.junit.*;
import xyz.hugoh.viw.io.OBJProcessor;

import java.io.IOException;

import static org.junit.Assert.*;

public class OBJProcessorTest {
    OBJProcessor processor;

    @Before
    public void setUp() {
        processor = new OBJProcessor();
    }

    @Test
    public void noFileReturnsEmptyMesh() {
        assertTrue(processor.load3DObject("").getName().isEmpty());
    }

    @Test
    public void nameIsCorrect() {
        Mesh m = processor.load3DObject("simpletest.obj");
        assertEquals(m.getName(), "kappa");
    }

}
