package elevatorsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator implements ElevatorInterface{

    public static int UP = 1;
    public static int DOWN = -1;    
    public static int NO_ORDER = -1;
    private int numFloors;
    private int ID;
    private int currentFloor = 0;
    private int direction = UP;
    private int currentOrder = 0;
    private PriorityQueue<Integer> upQueue = new PriorityQueue<Integer>();
    private PriorityQueue<Integer> downQueue = new PriorityQueue<Integer>();
    private List<Integer>[] pressedButtons;

    public Elevator(int ID, int numFloors) {
        this.ID = ID;
        this.numFloors = numFloors;
        
        List<Integer>[] newArrayOfLists = new ArrayList[numFloors + 1];
        for (int i = 0; i <= numFloors; i++) {
            newArrayOfLists[i] = new ArrayList<Integer>();
        }
        pressedButtons = newArrayOfLists;
    }

    @Override
    public void pickup(int floor, int mydirection) {
        if (mydirection == UP) {
            upQueue.add(floor);
        } else if (mydirection == DOWN) {
            downQueue.add(-floor);
        }

        // if (floor >= currentFloor) {
        //     upQueue.add(floor);
        // } else {
        //     downQueue.add(-floor);
        // }
    }

    @Override
    public void update(int currentFloor, int destinationFloor) {
        pressedButtons[currentFloor].add(destinationFloor);
    }

    private void quantStep() {
        if (currentFloor == numFloors) {
            direction = DOWN;
        }

        if (currentFloor == 0) {
            direction = UP;
        }

        if (direction == UP) {
            currentFloor++;
        }
        else {
            currentFloor--;
        }
    }

    private void chooseNextFloor() {
        // We need to know where to go.
        // Kiedy musimy wybrać następne pole?
        if (direction == UP) {
            if (upQueue.size() > 0) {
                currentOrder = upQueue.poll();
                if (currentOrder <  currentFloor) {
                    direction = DOWN;
                }
            } else if (downQueue.size() > 0) {
                currentOrder = -downQueue.poll();
                // exception
                if (currentOrder <  currentFloor) {
                    direction = DOWN;
                }
            }
        }
        else if (direction == DOWN) {
            if (downQueue.size() > 0) {
                currentOrder = -downQueue.poll();
                if (currentOrder >  currentFloor) {
                    direction = UP;
                }
            } else if (upQueue.size() > 0) {
                currentOrder = upQueue.poll();
                if (currentOrder >  currentFloor) {
                    direction = UP;
                }
            }
        }
    }

    private void addUpdated(int x) {
        for (int i = 0; i < pressedButtons[x].size(); i++) {
            int destinationFloor = pressedButtons[currentFloor].get(i);
            if (destinationFloor > currentFloor) {
                upQueue.add(destinationFloor);
            } else if (destinationFloor < currentFloor) {
                downQueue.add(-destinationFloor);
            }
        }

        pressedButtons[x].clear();
    }

    @Override
    public void step() {
        addUpdated(currentFloor);
        // We stay where we are.
        if (upQueue.size() == 0 && downQueue.size() == 0 && currentOrder == currentFloor) {
            return;
        }

        if (currentOrder == currentFloor) {
            chooseNextFloor();
        }

        while (upQueue.size() > 0 && upQueue.peek() == currentFloor) {
            upQueue.poll();
        }

        while (downQueue.size() > 0 && downQueue.peek() == -currentFloor) {
            downQueue.poll();
        }
        
        quantStep();
    }

    // @Override
    // public void step() {
    //     if (currentOrder == NO_ORDER) {
    //         if (direction == UP) {
    //             if (upQueue.size() > 0) {
    //                 currentOrder = upQueue.poll();
    //             } else if (downQueue.size() > 0) {
    //                 currentOrder = -downQueue.poll();
    //                 // exception
    //                 if (currentOrder <=  currentFloor) {
    //                     direction = DOWN;
    //                 }
    //             }
    //             else {
    //                 return;
    //             }
    //         } else if (direction == DOWN) {
    //             if (downQueue.size() > 0) {
    //                 currentOrder = -downQueue.poll();
    //             } else if (upQueue.size() > 0) {
    //                 currentOrder = upQueue.poll();
    //                 if (currentOrder >=  currentFloor) {
    //                     direction = UP;
    //                 }
    //             }
    //             else {
    //                 return;
    //             }
    //         }
    //     }

    //     quantStep();

    //     for (int i = 0; i < pressedButtons[currentFloor].size(); i++) {
    //         int destinationFloor = pressedButtons[currentFloor].get(i);
    //         if (destinationFloor > currentFloor) {
    //             upQueue.add(destinationFloor);
    //         } else if (destinationFloor < currentFloor) {
    //             downQueue.add(-destinationFloor);
    //         }
    //     }

    //     if (currentFloor == currentOrder) {
    //         if (direction == UP) {
    //             while (upQueue.size() > 0 && upQueue.peek() == currentFloor) {
    //                 upQueue.poll();
    //             }
    //         }

    //         if (direction == DOWN) {
    //             while (downQueue.size() > 0 && downQueue.peek() == currentFloor) {
    //                 downQueue.poll();
    //             }
    //         }

    //         currentOrder = NO_ORDER;
    //     }
    // }

    @Override
    public List<Integer> status() {
        List<Integer> status = new ArrayList<Integer>();
        status.add(ID);
        status.add(currentFloor);
        status.add(currentOrder);
        return status;
    }

    public int getID() {
        return ID;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getDirection() {
        return direction;
    }
}
