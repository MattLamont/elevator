package elevator; 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;


import elevator.Elevator;


public class ElevatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void failOnBadFloor() {
        String[] floors = {"5", "3"};
        Elevator elevator = new Elevator(floors, -1);
    }
      
      
    @Test
    public void sortFloors() {
        String[] floors = {"8", "6","1", "7","3", "4"};
        Elevator elevator = new Elevator(floors, 5);

        List<Integer> list = List.of(5 , 6, 7, 8 , 4, 3 , 1);
        assertTrue(list.equals(elevator.getFloorsToVisit()));

        assertEquals( Integer.valueOf(100) , elevator.calculateTravelTime());
    }
    
}
