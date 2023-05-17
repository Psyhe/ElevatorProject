package elevatorsystem;

import java.util.Scanner;

class Main {
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
        ElevatorSystem elevatorSystem = new ElevatorSystem(16, 16);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            String[] splitLine = line.split(" ");
            // Process each line of the file here.
            if (splitLine[0].equals("PICKUP")) {
                pickup(elevatorSystem, splitLine);
            }
            else if (splitLine[0].equals("STEP")) {
                elevatorSystem.step();
            }
            else if (splitLine[0].equals("UPDATE")) {
                update(elevatorSystem, splitLine);
            }
            else if (splitLine[0].equals("STATUS")) {
                System.out.println(elevatorSystem.status());
            }
            else if(splitLine[0].equals("QUIT")) {
                scanner.close();
                break;
            }
            else {
                System.out.println("Invalid command");
            }
        }
    }
}