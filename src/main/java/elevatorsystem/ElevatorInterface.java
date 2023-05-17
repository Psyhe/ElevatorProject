package elevatorsystem;

import java.util.List;

public interface ElevatorInterface {
    public void pickup(int floor, int direction);
    public void update(int floor);
    public void step();
    public List<Integer> status();
}
