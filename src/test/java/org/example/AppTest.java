package org.example;



import org.aspectj.lang.annotation.Before;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @BeforeTest
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
