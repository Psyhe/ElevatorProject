import static org.junit.Assert.assertEquals;
import java.util.List;
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

    @Test
    public void testElevatorSystemPickup3() {
        smallSystem.pickup(10, Elevator.UP);
        for (int i = 0; i < 10; i++)
            smallSystem.step();
        smallSystem.pickup(8, Elevator.UP);
        smallSystem.pickup(9, Elevator.DOWN);
        smallSystem.step();
        smallSystem.step();
        assertEquals(8, smallSystem.getElevator(0).getCurrentFloor());
    }

    @Test
    public void testElevatorSystemPickup4() {
        smallSystem.pickup(10, Elevator.UP);
        for (int i = 0; i < 10; i++)
            smallSystem.step();
        smallSystem.pickup(15, Elevator.UP);
        smallSystem.pickup(8, Elevator.UP);

        smallSystem.step();
        smallSystem.step();

        assertEquals(8, smallSystem.getElevator(1).getCurrentOrder());
        assertEquals(15, smallSystem.getElevator(0).getCurrentOrder());
    }

    @Test
    public void statusTest() {
        elevatorSystem.pickup(8, Elevator.UP);
        elevatorSystem.pickup(9, Elevator.UP);
        for (int i = 0; i < 7; i++)
            elevatorSystem.step();
        elevatorSystem.pickup(5, Elevator.UP);
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.pickup(1,Elevator.DOWN);
        elevatorSystem.step();

        int tab[] = Parameters.statusTestParameters1();

        List<List<Integer>> mystatus = Parameters.customList(tab);
        List<List<Integer>> status = elevatorSystem.status();

        assertEquals(status, mystatus);
    }

    // This test is the same as the previous one, but with a different pickup
    // call. It is used to check two different situations, due to previous errors.
    @Test
    public void statusTest2() {
        elevatorSystem.pickup(8, Elevator.UP);
        elevatorSystem.pickup(9, Elevator.UP);
        for (int i = 0; i < 7; i++)
            elevatorSystem.step();
        elevatorSystem.pickup(5, Elevator.UP);
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.step();
        elevatorSystem.pickup(2,Elevator.DOWN);
        elevatorSystem.step();

        int[] tab = Parameters.statusTestParameters1();
        tab[8] = 2;

        List<List<Integer>> mystatus = Parameters.customList(tab);
        List<List<Integer>> status = elevatorSystem.status();

        assertEquals(status, mystatus);
    }

    @Test
    public void statusTest3() {
        smallSystem.pickup(10, Elevator.UP);
        smallSystem.pickup(18, Elevator.UP);
        smallSystem.pickup(17, Elevator.UP);
        for (int i = 0; i < 16; i++) {
            smallSystem.step();
        }
        smallSystem.pickup(15, Elevator.UP);
        smallSystem.step();

        int tab[] = Parameters.statusTestParameters2();
        tab[1] = 18;
        tab[2] = 18;
        List<List<Integer>> mystatus = Parameters.customList(tab);
        smallSystem.step();
        assertEquals(mystatus, smallSystem.status());

        smallSystem.step();
        smallSystem.step();

        tab[1] = 16;
        tab[2] = 15;
        mystatus = Parameters.customList(tab);
        assertEquals(mystatus, smallSystem.status());
    }

    @Test
    public void testUpdate1() {
        smallSystem.pickup(10, Elevator.UP);
        smallSystem.pickup(18, Elevator.UP);
        smallSystem.update(0, 6, 14);
        for (int i = 0; i < 11; i++)
            smallSystem.step();
        assertEquals(14, smallSystem.getElevator(0).getCurrentOrder());
        assertEquals(11, smallSystem.getElevator(0).getCurrentFloor());
    }

    @Test
    public void testDoubleUpdate1() {
        smallSystem.pickup(10, Elevator.UP);
        smallSystem.update(0, 10, 13);
        for (int i = 0; i < 10; i++)
            smallSystem.step();
        smallSystem.pickup(6, Elevator.UP);
        smallSystem.step();
        smallSystem.update(0, 10, 15);
        assertEquals(13, smallSystem.getElevator(0).getCurrentOrder());
        for (int i = 0; i < 3; i++)
            smallSystem.step();
        assertEquals(6, smallSystem.getElevator(0).getCurrentOrder());
        for (int i = 0; i < 15; i++) {
            smallSystem.step();
        }
        assertEquals(15, smallSystem.getElevator(0).getCurrentFloor());
        assertEquals(15, smallSystem.getElevator(0).getCurrentOrder());
    }
}
