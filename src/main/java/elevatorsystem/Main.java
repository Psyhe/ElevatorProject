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
        ElevatorSystem elevatorSystem = new ElevatorSystem(16, 16);
        System.out.println("helloworld");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ");
            // Process each line of the file here
            System.out.println(splitLine[0]);
            if (splitLine[0].equals("PICKUP")) {
                System.out.println("PICKUP");
            }
            else if (splitLine[0].equals("STEP")) {
                System.out.println("STEP");
            }
            else if (splitLine[0].equals("UPDATE")) {
                System.out.println("UPDATE");
            }
            else if (splitLine[0].equals("STATUS")) {
                System.out.println("STATUS");
            }
            else if(splitLine[0].equals("QUIT")) {
                System.out.println("QUIT");
                scanner.close();
                break;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
}