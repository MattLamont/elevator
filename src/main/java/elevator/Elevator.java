package elevator;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Elevator {

    private Integer startFloor = 0;
    private Set<Integer> floorsToVisit;

    private Set<Integer> floorsUp;
    private TreeSet<Integer> floorsDown;

    private static final Integer FLOOR_TRAVEL_TIME = 10;
    
    public Elevator( String[] floors , Integer startFloor ) {

        // setup the starting floor and sequence of floors provided
        if( startFloor < 1 ) {
            throw new IllegalArgumentException("Start floor number must be 1 or greater");
        }
        this.startFloor = startFloor;

        this.floorsToVisit = new HashSet<>();
        for( String floor: floors) {
            if( Integer.parseInt(floor) < 1) {
                throw new IllegalArgumentException("Floors numbers must be 1 or greater");
            }
            this.floorsToVisit.add(new Integer(floor));
        }

        // sort the floors out into two sets
        this.sortFloors();
    }

    private void sortFloors() {

        this.floorsUp = new HashSet<>();
        this.floorsDown = new TreeSet<>();

        this.floorsUp.add(this.startFloor);

        // divide the floors into two sets
        // 1. floors above the start floor
        // 2. floors below the start floor
        // The sets are automatically sorted from small to large, so the floorsDown will need to be reversed
        this.floorsToVisit.forEach(floor -> {
            if( floor >= this.startFloor) {
                this.floorsUp.add(floor);
            } else {
                this.floorsDown.add(floor);
            }
        });


    }

    public void travel() {

        // print out the floors visited in order
        StringBuilder builder = new StringBuilder();
        builder.append("floors visited in order: [");

        this.floorsUp.forEach(floor -> {
            builder.append( String.format( "%s , ", floor));
        });

        this.floorsDown.descendingSet().forEach(floor -> {
            builder.append( String.format( "%s , ", floor));
        });

        builder.append("]");

        System.out.println( builder.toString());

        System.out.println( String.format( "total travel time: %s" , this.calculateTravelTime()));
    }

    private Integer calculateTravelTime() {
        Integer travelTime = Integer.valueOf(0);

        // calculate the travel time going to the up floors first,
        // then calculate the travel time going down the floors
        Integer lastFloor = this.startFloor;
        for( Integer currentFloor: this.floorsUp ){
            travelTime += (currentFloor - lastFloor);
            lastFloor = currentFloor;
        }

        for( Integer currentFloor: this.floorsDown.descendingSet() ){
            travelTime += (lastFloor - currentFloor);
            lastFloor = currentFloor;
        }

        return travelTime * FLOOR_TRAVEL_TIME;
    }



    
}
