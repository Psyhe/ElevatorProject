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
            elevators[i] = new Elevator(i, numFloors);
        }
    }

    // private int distanceSameDirection(Elevator elevator, int floor) {
    //     if (elevator.getDirection() == Elevator.UP) {
    //         if (elevator.getCurrentFloor() <= floor) {
    //             return Math.abs(elevator.getCurrentFloor() - floor);
    //         }
    //         else {
    //             return numFloors - elevator.getCurrentFloor() + numFloors - floor - 1;
    //         }
    //     }
    //     else {
    //         if (elevator.getCurrentFloor() >= floor) {
    //             return Math.abs(elevator.getCurrentFloor() - floor);
    //         }
    //         else {
    //             return elevator.getCurrentFloor() + floor - 1;
    //         }
    //     }
    // }

    // private int distanceDifferentDirection(Elevator elevator, int floor) {
    //     if (elevator.getDirection() == Elevator.UP) {
    //         if ()
    //     }
    //     else {
    //         return numFloors - elevator.getCurrentFloor() + numFloors - floor - 1;
    //     }
    // }

    private Elevator getQuickestElevator(int floor) {
        Elevator optimalElevator = null;
        int distance = Integer.MAX_VALUE;
        for (Elevator elevator: elevators) {
            int shortestDistance;
            int shortestDistanceDifferentDirection = Integer.MAX_VALUE;
            if (elevator.getDownQueueSize() == 0) {
                shortestDistanceDifferentDirection = Math.abs(elevator.getCurrentFloor() - floor);
            }

            if (elevator.getUpQueueSize() == 0) {
                if (shortestDistanceDifferentDirection > Math.abs(elevator.getCurrentFloor() - floor)) {
                    shortestDistanceDifferentDirection = Math.abs(elevator.getCurrentFloor() - floor);
                }
            }

            if (elevator.getDirection() == Elevator.UP) {
                if (elevator.getCurrentFloor() <= floor) {
                    shortestDistance = floor - elevator.getCurrentFloor();
                }
                else {
                    shortestDistance = numFloors - elevator.getCurrentFloor() + numFloors - floor - 1;
                }
            }
            else {
                if (elevator.getCurrentFloor() >= floor) {
                    shortestDistance = elevator.getCurrentFloor() - floor;
                }
                else {
                    shortestDistance = elevator.getCurrentFloor() + floor - 1;
                }
            }

            shortestDistance = Math.min(shortestDistanceDifferentDirection, shortestDistance);

            if (shortestDistance < distance) {
                optimalElevator = elevator;
                distance = shortestDistance;
            }
        }

        return optimalElevator;
    }

    public void pickup(int floor, int direction) {
        if (floor > numFloors || floor < 0) {
            throw new IllegalArgumentException("Floor is out of range");
        }

        Elevator optimalElevator = getQuickestElevator(floor);

        // for (Elevator elevator: elevators) {
        //     if (direction == Elevator.UP) {
        //         if (elevator.getCurrentFloor() <= floor) {
        //             int distance = Math.abs(elevator.getCurrentFloor() - floor);
        //             if (distance < closestDistance) {
        //                 optimalElevator = elevator;
        //                 closestDistance = distance;
        //             }
        //         }
        //         else {
        //             int distance = numFloors - elevator.getCurrentFloor() + numFloors - floor - 1;
        //             if (distance < closestDistance) {
        //                 optimalElevator = elevator;
        //                 closestDistance = distance;
        //             }
        //         }
        //     }
        //     else {
        //         if (elevator.getCurrentFloor() >= floor) {
        //             int distance = Math.abs(elevator.getCurrentFloor() - floor);
        //             if (distance < closestDistance) {
        //                 optimalElevator = elevator;
        //                 closestDistance = distance;
        //             }
        //         }
        //         else {
        //             int distance = elevator.getCurrentFloor() + floor - 1;
        //             if (distance < closestDistance) {
        //                 optimalElevator = elevator;
        //                 closestDistance = distance;
        //             }
        //         }
        //     }
        // }

        if (optimalElevator != null) {
            optimalElevator.pickup(floor, direction);
        }
    }

    public void update(int elevatorId, int currentFloor, int destinationFloor) {
        if (currentFloor > numFloors || currentFloor < 0) {
            throw new IllegalArgumentException("Floor is out of range");
        }

        if (destinationFloor > numFloors || destinationFloor < 0) {
            throw new IllegalArgumentException("Floor is out of range");
        }

        if (elevatorId >= elevators.length || elevatorId < 0) {
            throw new IllegalArgumentException("Elevator ID is out of range");
        }

        elevators[elevatorId].update(currentFloor, destinationFloor);
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

    public Elevator getElevator(int elevatorId) {
        return elevators[elevatorId];
    }
}
