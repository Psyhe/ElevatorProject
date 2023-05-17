package elevatorsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    static void parseArguments(ElevatorSystem elevatorSystem) {
        try {
            File file = new File("data.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Process each line of the file here
                System.out.println(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        System.out.println("helloworld");
    }
}