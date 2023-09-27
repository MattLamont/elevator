package elevator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import lombok.Data;

@Data
public class Elevator {

    private Integer startFloor = 0;
    private List<Integer> floorsToVisit;

    private static final Integer FLOOR_TRAVEL_TIME = 10;
    
    public Elevator( String[] floors , Integer startFloor ) {

        // setup the starting floor and sequence of floors provided
        if( startFloor < 1 ) {
            throw new IllegalArgumentException("Start floor number must be 1 or greater");
        }
        this.startFloor = startFloor;

        this.floorsToVisit = new ArrayList<>();

        Set<Integer> floorsSet = new HashSet<>();
        for( String floor: floors) {
            if( Integer.parseInt(floor) < 1) {
                throw new IllegalArgumentException("Floors numbers must be 1 or greater");
            }
            floorsSet.add(new Integer(floor));
        }

        // sort the floors out into two sets
        this.sortFloors(floorsSet);
    }

    private void sortFloors( Set<Integer> floorsSet ) {

        HashSet<Integer> floorsUp = new HashSet<>();
        TreeSet<Integer> floorsDown = new TreeSet<>();

        floorsUp.add(this.startFloor);

        // divide the floors into two sets
        // 1. floors above the start floor
        // 2. floors below the start floor
        // The sets are automatically sorted from small to large, so the floorsDown will need to be reversed
        floorsSet.forEach(floor -> {
            if( floor >= this.startFloor) {
                floorsUp.add(floor);
            } else {
                floorsDown.add(floor);
            }
        });

        this.floorsToVisit.clear();

        floorsUp.forEach(floor -> this.floorsToVisit.add(floor));
        floorsDown.descendingSet().forEach(floor -> this.floorsToVisit.add(floor));

    }

    public void travel() {

        // print out the floors visited in order
        StringBuilder builder = new StringBuilder();
        builder.append("floors visited in order: [");

        this.floorsToVisit.forEach(floor -> {
            builder.append( String.format( "%s , ", floor));
        });

        builder.append("]");

        System.out.println( builder.toString());

        System.out.println( String.format( "total travel time: %s" , this.calculateTravelTime()));
    }

    public Integer calculateTravelTime() {
        Integer travelTime = Integer.valueOf(0);

        // calculate the travel time going to the up floors first,
        // then calculate the travel time going down the floors
        Integer lastFloor = this.startFloor;
        for( Integer currentFloor: this.floorsToVisit ){
            travelTime += Math.abs( currentFloor - lastFloor);
            lastFloor = currentFloor;
        }

        return travelTime * FLOOR_TRAVEL_TIME;
    }



    
}
