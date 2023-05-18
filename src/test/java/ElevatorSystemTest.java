import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private Set<List<Integer>> customSet(int tab[]) {
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < tab.length; i+=3) {
            List<Integer> list = new ArrayList<>();
            list.add(tab[i]);
            list.add(tab[i+1]);
            list.add(tab[i+2]);
            set.add(list);
        }
        
        return set;
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

    private int[] statusTestParameters1() {
        int tab[] = new int[30];

        for (int i = 0; i < 10; i++) {
            tab[i*3] = i;
            tab[i*3+1] = 0;
            tab[i*3+2] = 0;
        }
        tab[1] = 9;
        tab[2] = 9;
        tab[4] = 4;
        tab[5] = 5;
        tab[7] = 1;
        tab[8] = 1;

        return tab;
    }

    private int[] statusTestParameters2() {
        int tab[] = new int[6];

        for (int i = 0; i < 2; i++) {
            tab[i*3] = i;
            tab[i*3+1] = 0;
            tab[i*3+2] = 0;
        }

        return tab;
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

        int tab[] = statusTestParameters1();

        Set<List<Integer>> mystatus = customSet(tab);
        Set<List<Integer>> status = elevatorSystem.status();
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

        int[] tab = statusTestParameters1();
        tab[8] = 2;

        Set<List<Integer>> mystatus = customSet(tab);
        Set<List<Integer>> status = elevatorSystem.status();
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

        int tab[] = statusTestParameters2();
        tab[1] = 18;
        tab[2] = 18;
        Set<List<Integer>> mystatus = customSet(tab);
        smallSystem.step();
        assertEquals(mystatus, smallSystem.status());

        smallSystem.step();
        smallSystem.step();

        tab[1] = 16;
        tab[2] = 15;
        mystatus = customSet(tab);
        assertEquals(mystatus, smallSystem.status());
    }
}
