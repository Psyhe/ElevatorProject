import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import elevatorsystem.Elevator;
import elevatorsystem.ElevatorSystem;

public class ElevatorSystemTest {
    ElevatorSystem elevatorSystem;

    @Before
    public void setUp() {
        elevatorSystem = new ElevatorSystem(10, 10);
    }

    @Test
    public void testElevatorSystemPickup() {
        elevatorSystem.pickup(2, Elevator.UP);
        elevatorSystem.pickup(3, Elevator.UP);
        elevatorSystem.step();
        elevatorSystem.step();
        int floor = elevatorSystem.getElevator(0).getCurrentFloor();
        assertEquals(2, floor);
        elevatorSystem.step();
        floor = elevatorSystem.getElevator(0).getCurrentFloor();
        assertEquals(3, floor);
    }
}
