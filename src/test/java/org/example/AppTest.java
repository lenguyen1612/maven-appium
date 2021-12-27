package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Before
    public void setup() throws IOException {
        String path = System.getProperty("user.dir") + "/scripts/emulator_android2901_360_640.bat";
        Runtime.
                getRuntime().
                exec("cmd /c start " + path);
        Runtime.getRuntime().
                exec("cmd /c start adb devices");
    }
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
