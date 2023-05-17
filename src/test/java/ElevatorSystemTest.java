import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import elevatorsystem.Elevator;
import elevatorsystem.ElevatorSystem;

public class ElevatorSystemTest {
    ElevatorSystem elevatorSystem;
    ElevatorSystem smallSystem;

    @Before
    public void setUp() {
        elevatorSystem = new ElevatorSystem(10, 10);
        smallSystem = new ElevatorSystem(2, 20);
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

    @Test
    public void testElevatorSystemPickup2() {
        elevatorSystem.pickup(2, Elevator.UP);
        elevatorSystem.pickup(3, Elevator.UP);
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.pickup(1, Elevator.UP);
        elevatorSystem.step();
        assertEquals(3, elevatorSystem.getElevator(0).getCurrentFloor());
        assertEquals(1, elevatorSystem.getElevator(1).getCurrentFloor());
    }

    // @Test
    // public void testElevatorSystemPickup3() {
    //     smallSystem.pickup(10, Elevator.UP);
    //     for (int i = 0; i < 10; i++)
    //         smallSystem.step();
    //     smallSystem.pickup(8, Elevator.UP);
    //     smallSystem.pickup(9, Elevator.DOWN);
    //     smallSystem.step();
    //     System.out.println(s);
    // }
}
