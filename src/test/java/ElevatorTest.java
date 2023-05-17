import elevatorsystem.Elevator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ElevatorTest {
    @Test
    public void testElevatorId() {
        // Arrange
        Elevator elevator = new Elevator(10);

        // Act
        int actualId = elevator.getID();

        // Assert
        int expectedId = 10;
        assertEquals(expectedId, actualId);
    }
}