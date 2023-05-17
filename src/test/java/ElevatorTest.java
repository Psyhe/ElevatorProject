import elevatorsystem.Elevator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

public class ElevatorTest {
    private Elevator elevator1;

    @Before
    public void setUp() {
        elevator1 = new Elevator(10, 5);
    }

    @Test
    public void testElevatorId() {
        int actualId = elevator1.getID();
        int expectedId = 10;
        assertEquals(expectedId, actualId);
    }

    @Test
    public void testElevatorCurrentFloor() {
        int actualFloor = elevator1.getCurrentFloor();
        int expectedFloor = 0;
        assertEquals(expectedFloor, actualFloor);
    }

    @Test
    public void testElevatorDirection() {
        int actualDirection = elevator1.getDirection();
        int expectedDirection = Elevator.UP;
        assertEquals(expectedDirection, actualDirection);
    }

    @Test 
    public void testElevatorNoPassengers() {
        elevator1.step();
        elevator1.step();
        int floor = elevator1.getCurrentFloor();
        assertEquals(0, floor);
    }

    @Test
    public void testElevatorPickupDOWN1() {
        elevator1.pickup(2, Elevator.UP);
        elevator1.step();
        int floor = elevator1.getCurrentFloor();
        assertEquals(1, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(2, floor);
    }

    @Test
    public void testElevatorPickupUP1() {
        elevator1.pickup(3, Elevator.UP);
        elevator1.pickup(2, Elevator.UP);
        elevator1.pickup(1, Elevator.UP);
        elevator1.step();
        elevator1.step();
        int floor = elevator1.getCurrentFloor();
        assertEquals(2, floor);
    }

    @Test
    public void testDifferentDirections() {
        elevator1.pickup(3, Elevator.UP);
        elevator1.pickup(2, Elevator.UP);
        elevator1.pickup(1, Elevator.UP);
        elevator1.pickup(2, Elevator.DOWN);
        elevator1.pickup(1, Elevator.DOWN);
        elevator1.step();
        int floor = elevator1.getCurrentFloor();
        assertEquals(1, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(2, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(3, floor);
        elevator1.step();
        elevator1.pickup(2, Elevator.DOWN);
        elevator1.pickup(1, Elevator.DOWN);
        floor = elevator1.getCurrentFloor();
        assertEquals(2, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(1, floor);
        elevator1.step();
    }

    @Test
    public void testDifferentDirections2() {
        elevator1.pickup(3, Elevator.UP);
        elevator1.pickup(2, Elevator.UP);
        elevator1.pickup(1, Elevator.UP);
        elevator1.pickup(4, Elevator.DOWN);
        elevator1.pickup(5, Elevator.DOWN);
        elevator1.step();
        int floor = elevator1.getCurrentFloor();
        assertEquals(1, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(2, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(3, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(4, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(5, floor);
    }

    @Test
    public void testUpdate1() {
        elevator1.pickup(3, Elevator.UP);
        elevator1.pickup(2, Elevator.UP);
        elevator1.pickup(1, Elevator.UP);
        elevator1.pickup(4, Elevator.DOWN);
        elevator1.pickup(5, Elevator.DOWN);
        elevator1.update(3, 1);
        elevator1.update(2, 1);
        elevator1.update(1, 1);
        elevator1.update(4, 1);
        elevator1.update(5, 1);
        elevator1.step();
        int floor = elevator1.getCurrentFloor();
        assertEquals(1, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(2, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(3, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(4, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(5, floor);
    }

    @Test
    public void testPickup2() {
        elevator1.pickup(3, Elevator.DOWN);
        elevator1.step();
        elevator1.step();
        elevator1.step();
        elevator1.pickup(2, Elevator.DOWN);
        elevator1.step();
        int floor = elevator1.getCurrentFloor();
        assertEquals(2, floor);
    }

    @Test
    public void testPickup3() {
        elevator1.pickup(3, Elevator.DOWN);
        elevator1.step();
        elevator1.step();
        elevator1.step();
        elevator1.pickup(2, Elevator.UP);
        elevator1.step();
        int floor = elevator1.getCurrentFloor();
        assertEquals(2, floor);
    }

    @Test
    public void testPickup4() {
        elevator1.pickup(3, Elevator.DOWN);
        elevator1.step();
        elevator1.step();
        elevator1.step();
        elevator1.pickup(2, Elevator.UP);
        elevator1.step();
        elevator1.step();
        int floor = elevator1.getCurrentFloor();
        assertEquals(2, floor);
    }

    @Test
    public void testUpdate2() {
        elevator1.pickup(0, Elevator.UP);
        elevator1.update(0, 1);
        elevator1.step();
        int floor = elevator1.getCurrentFloor();
        assertEquals(1, floor);
    }

    @Test
    public void testUpdate3() {
        elevator1.pickup(1, Elevator.UP);
        elevator1.update(0, 2);
        elevator1.step();
        elevator1.step();
        elevator1.step();
        int floor = elevator1.getCurrentFloor();
        assertEquals(2, floor);
        elevator1.pickup(1, Elevator.UP);
        elevator1.update(2, 4);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(1, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(2, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(3, floor);
        elevator1.step();
        floor = elevator1.getCurrentFloor();
        assertEquals(4, floor);
    }

    @Test
    public void testStatus() {
        List<Integer> statusCorrect = new ArrayList<>();
        statusCorrect.add(10);
        statusCorrect.add(0);
        statusCorrect.add(0);
        List<Integer> status1 = elevator1.status();
        assertEquals(statusCorrect, status1);
    }

    @Test
    public void testStatus2() {
        List<Integer> statusCorrect = new ArrayList<>();
        statusCorrect.add(10);
        statusCorrect.add(2);
        statusCorrect.add(3);
        elevator1.pickup(3, Elevator.UP);
        elevator1.update(2, 1);
        elevator1.step();
        elevator1.step();
        List<Integer> status1 = elevator1.status();
        assertEquals(statusCorrect, status1);
        elevator1.step();
        statusCorrect.clear();
        statusCorrect.add(10);
        statusCorrect.add(3);
        statusCorrect.add(3);
        status1 = elevator1.status();
        assertEquals(statusCorrect, status1);
        elevator1.step();
        statusCorrect.set(1, 2);
        statusCorrect.set(2, 1);
        status1 = elevator1.status();
        assertEquals(statusCorrect, status1);
    }
}