package elevatorsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator implements ElevatorInterface{

    public static int UP = 1;
    public static int DOWN = -1;
    public static int NO_ORDER = -1;
    private int ID;
    private int currentFloor = 0;
    private int direction = UP;
    private int currentOrder = NO_ORDER;
    private PriorityQueue<Integer> upQueue = new PriorityQueue<Integer>();
    private PriorityQueue<Integer> downQueue = new PriorityQueue<Integer>();

    public Elevator(int ID) {
        this.ID = ID;
    }

    @Override
    public void pickup(int floor, int direction) {
        if (direction == UP) {
            upQueue.add(floor);
        } else if (direction == DOWN) {
            downQueue.add(-floor);
        }
    }

    @Override
    public void update(int floor) {
        if (floor < currentFloor) {
            downQueue.add(-floor);
        }
        else {
            upQueue.add(floor);
        }
    }

    @Override
    public void step() {
        if (currentOrder == NO_ORDER) {
            if (direction == UP) {
                if (upQueue.size() > 0) {
                    currentOrder = upQueue.poll();
                } else if (downQueue.size() > 0) {
                    currentOrder = -downQueue.poll();
                    direction = DOWN;
                }
                else {
                    return;
                }
            } else if (direction == DOWN) {
                if (downQueue.size() > 0) {
                    currentOrder = -downQueue.poll();
                } else if (upQueue.size() > 0) {
                    direction = UP;
                    currentOrder = upQueue.poll();
                }
                else {
                    return;
                }
            }
        }

        if (direction == UP) {
            currentFloor++;
        }
        else {
            currentFloor--;
        }

        if (currentFloor == currentOrder) {
            if (direction == UP) {
                while (upQueue.size() > 0 && upQueue.peek() == currentFloor) {
                    upQueue.poll();
                }
            }

            if (direction == DOWN) {
                while (downQueue.size() > 0 && downQueue.peek() == currentFloor) {
                    downQueue.poll();
                }
            }

            currentOrder = NO_ORDER;
        }
    }

    @Override
    public List<Integer> status() {
        List<Integer> status = new ArrayList<Integer>();
        status.add(ID);
        status.add(currentFloor);
        status.add(direction);
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
