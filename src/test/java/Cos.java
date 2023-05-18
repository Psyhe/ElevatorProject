
// // import elevatorsystem.Main;
// // import elevatorsystem.RealMain;

// // import java.io.InputStream;

// // import org.junit.Before;
// // import org.junit.Test;
// // import java.io.ByteArrayInputStream;


// // public class RealMainTest {
// //     RealMain realMain;

// //     static String input = "SET 2 20\n" +
// //     "UPDATE 0 0 10\n" +
// //     "UPDATE 1 0 5\n" +
// //     "STEP\n" +
// //     "STEP\n" +
// //     "STEP\n" +
// //     "STATUS";
    
// //     @Before
// //     public void setUpStreams() {
// //         // Create a ByteArrayOutputStream to capture stdout
// //         outputStream = new ByteArrayOutputStream();

// //         // Create a PrintStream to replace the default stdout
// //         customPrintStream = new PrintStream(outputStream);

// //         // Redirect System.out to the custom PrintStream
// //         System.setOut(customPrintStream);
// //     }

// //     @After
// //     public void restoreStreams() {
// //         // Restore System.in and System.out to their original states
// //         System.setIn(originalSystemIn);
// //         System.setOut(originalSystemOut);
// //     }

// //     @Test
// //     public void testRealMain() {
// //         String input = "your_input_here";
// //         InputStream inputStream = new ByteArrayInputStream(input.getBytes());
// //         System.setIn(inputStream);

// //         // Call the method or code that produces output to stdout
// //         realMain.parse();

// //         // Retrieve the captured output from the ByteArrayOutputStream
// //         String capturedOutput = outputStream.toString();

// //         // Assert the expected output
// //         String expectedOutput = "expected_output_here";
// //         assertEquals(expectedOutput, capturedOutput);
// //     }
// // }


// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.io.InputStream;
// import java.io.PrintStream;

// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;
// import static org.junit.Assert.assertEquals;

// import elevatorsystem.Main;
// import elevatorsystem.RealMain;
// import junit.framework.Assert;

// public class RealMainTest {
//     private final InputStream originalSystemIn = System.in;
//     private final PrintStream originalSystemOut = System.out;
//     private ByteArrayOutputStream outputStream;
//     private PrintStream customPrintStream;
//     private RealMain realMain;

//     @Before
//     public void setUpStreams() {
//         realMain = new RealMain();
//         // Create a ByteArrayOutputStream to capture stdout
//         outputStream = new ByteArrayOutputStream();

//         // Create a PrintStream to replace the default stdout
//         customPrintStream = new PrintStream(outputStream);

//         // Redirect System.out to the custom PrintStream
//         System.setOut(customPrintStream);
//     }

//     @After
//     public void restoreStreams() {
//         // Restore System.in and System.out to their original states
//         System.setIn(originalSystemIn);
//         System.setOut(originalSystemOut);
//     }

//     // @Test
//     // public void testRealMain() {
//     //     String input = "your_input_here \n gjgjghg \n SET 10 5 \n UPDATE 0 0 10 \n UPDATE 1 0 5 \n STEP \n STEP \n STEP \n STATUS";
//     //     InputStream inputStream = new ByteArrayInputStream(input.getBytes());
//     //     System.setIn(inputStream);



//     //     // Call the method or code that produces output to stdout
//     //     realMain.parse();

//     //     // Retrieve the captured output from the ByteArrayOutputStream
//     //     String capturedOutput = outputStream.toString();

//     //     // Assert the expected output
//     //     String expectedOutput = "expected_output_here";
//     //     assertEquals(expectedOutput, capturedOutput);
//     // }

//     @Test
//     public void validUserInput_ShouldResultInExpectedOutput() {
//         String userInput = String.format("Dan%sVega%sdanvega@gmail.com",
//                 System.lineSeparator(),
//                 System.lineSeparator());
//         ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
//         System.setIn(bais);
    
//         String expected = "Dan,Vega,danvega@gmail.com";
//         ByteArrayOutputStream baos = new ByteArrayOutputStream();
//         PrintStream printStream = new PrintStream(baos);
//         System.setOut(printStream);
    
//         Main.main(null); // call the main method
    
//         String[] lines = baos.toString().split(System.lineSeparator());
//         String actual = lines[lines.length-1];
    
//         // checkout output
//         assertEquals(expected, actual);
//     }
    
// }

