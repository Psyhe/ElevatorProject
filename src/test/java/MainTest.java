import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Test;

import elevatorsystem.Main;

public class MainTest {
    @Test
    public void testInvalidUserInput() {
        String userInput = "PICKUP 16";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
    
        String expected = "Invalid command";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    
        Main.main(null);
    
        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected, actual);
    }

    @Test 
    public void testInvalidUserInput2() {
        // This test is to check if the program will continue to run after an invalid command.
        // UPDATE 0 0 4 6 is invalid because it has 5 arguments instead of 4, so it
        // should be ignored and the program should continue to run.
        String userInput = "SET 2 5 \n" + 
        "UPDATE 0 0 4 6\n" +
        "PICKUP 1 6 \n" +
        "STEP \n" +
        "STEP \n" +
        "STATUS \n";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
    
        int tab[] = Parameters.statusTestParameters2();
        tab[1] = 1;
        tab[2] = 1;
        List<List<Integer>> expected = Parameters.customList(tab);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    
        Main.main(null);
    
        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected.toString(), actual);
    }

    @Test
    public void testValidUserInput1() {
        String userInput = "SET 2 5 \n" + 
        "UPDATE 0 0 4 \n" +
        "PICKUP 1 6 \n" +
        "STEP \n" +
        "STATUS \n";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
    
        int tab[] = Parameters.statusTestParameters2();
        tab[1] = 1;
        tab[2] = 1;
        List<List<Integer>> expected = Parameters.customList(tab);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    
        Main.main(null);
    
        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected.toString(), actual);
    }

    @Test
    public void testValidUserInput2() {
        String userInput = "SET 2 5 \n" + 
        "UPDATE 0 0 4 \n" +
        "PICKUP 1 6 \n" +
        "STEP \n" +
        "STEP \n" +
        "STATUS \n" +
        "QUIT \n";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);
    
        int tab[] = Parameters.statusTestParameters2();
        tab[1] = 2;
        tab[2] = 4;
        List<List<Integer>> expected = Parameters.customList(tab);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);
    
        Main.main(null);
    
        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length-1];

        assertEquals(expected.toString(), actual);
    }
}