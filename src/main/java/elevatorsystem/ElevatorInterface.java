package elevatorsystem;

import java.util.List;

public interface ElevatorInterface {
    public void pickup(int floor, int direction);
    public void update(int currentFloor, int destinationFloor);
    public void step();
    public List<Integer> status();
}
