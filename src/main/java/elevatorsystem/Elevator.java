package elevatorsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator implements ElevatorInterface{

    public static int UP = 1;
    public static int DOWN = -1;    
    private int numFloors;
    private int ID;
    private int currentFloor = 0;
    private int direction = UP;
    private int currentOrder = 0;
    private PriorityQueue<Integer> upQueue = new PriorityQueue<Integer>();
    private PriorityQueue<Integer> downQueue = new PriorityQueue<Integer>();
    private ArrayList<ArrayList<Integer>> pressedButtons;

    public Elevator(int ID, int numFloors) {
        this.ID = ID;
        this.numFloors = numFloors;
        
        ArrayList<ArrayList<Integer>> newArrayOfLists = new ArrayList<ArrayList<Integer>>(numFloors + 1);
        for (int i = 0; i <= numFloors; i++) {
            ArrayList<Integer> sublist = new ArrayList<Integer>();
            newArrayOfLists.add(sublist);
        }
        pressedButtons = newArrayOfLists;
    }

    @Override
    public void pickup(int floor, int mydirection) {
        if (floor >= currentFloor) {
            upQueue.add(floor);
        } else {
            downQueue.add(-floor);
        }
    }

    @Override
    public void update(int currentFloor, int destinationFloor) {
        pressedButtons.get(currentFloor).add(destinationFloor);
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
        if (direction == UP) {
            if (upQueue.size() > 0) {
                currentOrder = upQueue.poll();
                if (currentOrder <  currentFloor) {
                    direction = DOWN;
                }
            } else if (downQueue.size() > 0) {
                currentOrder = -downQueue.poll();
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
        ArrayList <Integer> list = pressedButtons.get(x);

        for (int i = 0; i < list.size(); i++) {
            int destinationFloor = list.get(i);
            if (destinationFloor > currentFloor) {
                upQueue.add(destinationFloor);
            } else if (destinationFloor < currentFloor) {
                downQueue.add(-destinationFloor);
            }
        }

        pressedButtons.get(x).clear();
    }

    @Override
    public void step() {
        addUpdated(currentFloor);
        // We stay where we are.
        if (upQueue.size() == 0 && downQueue.size() == 0 && currentOrder == currentFloor) {
            return;
        }

        while (upQueue.size() > 0 && upQueue.peek() == currentFloor) {
            upQueue.poll();
        }

        while (downQueue.size() > 0 && downQueue.peek() == -currentFloor) {
            downQueue.poll();
        }

        if (currentOrder == currentFloor) {
            chooseNextFloor();
        }
        
        if (currentOrder != currentFloor) {
            quantStep();
        }
    }

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

    public int getCurrentOrder() {
        return currentOrder;
    }

    public int getUpQueueSize() {
        return upQueue.size();
    }

    public int getDownQueueSize() {
        return downQueue.size();
    }

    public PriorityQueue<Integer> getUpQueue() {
        return upQueue;
    }

    public PriorityQueue<Integer> getDownQueue() {
        return downQueue;
    }
}
