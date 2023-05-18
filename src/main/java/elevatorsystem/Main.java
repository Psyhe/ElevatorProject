package elevatorsystem;

import java.util.Scanner;

public class Main {
    private static void pickup(ElevatorSystem elevatorSystem, String[] splitLine) {
        if (splitLine.length != 3) {
            System.out.println("Invalid command");
            return;
        }
        else {
            try {
                int floor = Integer.parseInt(splitLine[1]);
                int direction = Integer.parseInt(splitLine[2]);
                if (direction >= 0) {
                    elevatorSystem.pickup(floor, Elevator.UP);
                }
                else {
                    elevatorSystem.pickup(floor, Elevator.DOWN);
                }
            }
            catch (Exception e) {
                System.out.println("Invalid floor");
            }
        }
    }

    private static void update(ElevatorSystem elevatorSystem, String[] splitLine) {
        if (splitLine.length != 4) {
            System.out.println("Invalid command");
            return;
        }
        else {
            try {
                int id = Integer.parseInt(splitLine[1]);
                int currentFloor = Integer.parseInt(splitLine[2]);
                int requestedFloor = Integer.parseInt(splitLine[3]);
                elevatorSystem.update(id, currentFloor, requestedFloor);
            }
            catch (Exception e) {
                System.out.println("Invalid floor or id");
            }
        }
    }

    public static void main (String[] args) {
        ElevatorSystem elevatorSystem = null;
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ");
            if (splitLine[0].equals("SET") && splitLine.length == 3) {
                int numElevators = Integer.parseInt(splitLine[1]);
                int numFloors = Integer.parseInt(splitLine[2]);
                elevatorSystem = new ElevatorSystem(numElevators, numFloors);
                break;
            }
            else {
                System.out.println("Invalid command");
            }
        }
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ");

            switch(splitLine[0]) {
                case "PICKUP":
                    pickup(elevatorSystem, splitLine);
                    break;
                case "STEP":
                    elevatorSystem.step();
                    break;
                case "UPDATE":
                    update(elevatorSystem, splitLine);
                    break;
                case "STATUS":
                    System.out.println(elevatorSystem.status());
                    break;
                case "QUIT":
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command");
            }
        }
        scanner.close();
    }
}