package elevatorsystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElevatorSystem {
    private Elevator[] elevators;
    private int numFloors;

    public ElevatorSystem(int numElevators, int numFloors) {
        this.numFloors = numFloors;
        elevators = new Elevator[numElevators];

       for (int i = 0; i < numElevators; i++) {
            elevators[i] = new Elevator(i);
        }
    }

    public void pickup(int floor, int direction) {
        if (floor > numFloors || floor < 0) {
            throw new IllegalArgumentException("Floor is out of range");
        }

        Elevator optimaltElevator = null;
        int closestDistance = Integer.MAX_VALUE;
        Elevator secodElevator = null;
        int secondClosestDistance = -1;

        for (Elevator elevator : elevators) {
            int elevatorDirection = elevator.getDirection();
            int distance = Math.abs(elevator.getCurrentFloor() - floor);

            if (elevatorDirection == direction && distance < closestDistance) {
                optimaltElevator = elevator;
                closestDistance = distance;
            } else if (elevatorDirection == -direction && distance > secondClosestDistance) {
                secodElevator = elevator;
                secondClosestDistance = distance;
            }
        }

        if (optimaltElevator != null) {
            optimaltElevator.pickup(floor, direction);
        } else if (secodElevator != null) {
            secodElevator.pickup(floor, direction);
        }
    }

    public void update(int elevatorId, int floor) {
        if (floor > numFloors || floor < 0) {
            throw new IllegalArgumentException("Floor is out of range");
        }

        if (elevatorId >= elevators.length || elevatorId < 0) {
            throw new IllegalArgumentException("Elevator ID is out of range");
        }

        elevators[elevatorId].update(floor);
    }

    public class MyElevator implements Runnable {
        Elevator elevator;

        public MyElevator(Elevator elevator) {
            this.elevator = elevator;
        }
     
        public void run() {
            elevator.step();
        }
    }

    public void step() {
        Thread[] threads = new Thread[elevators.length];
        for (Elevator elevator : elevators) {
            threads[elevator.getID()] = new Thread(new MyElevator(elevator));
            threads[elevator.getID()].start();
        }

        for (Elevator elevator : elevators) {
            try {
                threads[elevator.getID()].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Set<List<Integer>> status() {
        Set<List<Integer>> status = new HashSet<>();
        for (Elevator elevator : elevators) {
            List<Integer> elevatorStatus = elevator.status();
            status.add(elevatorStatus);
        }
        
        return status;
    }   
}
