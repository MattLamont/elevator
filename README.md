# Elevator

Elevator app.

## Requirements

Provide code that simulates an elevator. You are free to use any language (Java recommended)
* Upload the completed project to GitHub for discussion during interview.
* Document all assumptions and any features that weren’t implemented.
* The result should be an executable, or script, that can be run with the following inputs and generate the following outputs.
                   ** Inputs: [list of floors to visit] (e.g. elevator start=12 floor=2,9,1,32)
                   ** Outputs: [total travel time, floors visited in order] (e.g. 560 12,2,9,1,32)
                   ** Program Constants: Single floor travel time: 10

## Assumptions

* Single elevator
* No below ground floors
* First floor is 1 ( not 0 )
* You cannot press the button of the floor you are already on
* All floors buttons are pressed in order at the start. New floors cannot be added while elevator is in transit
* There is no external up or down button that user presses. Therefore the elevator will visit all selected floors above the start floor. 
  After exhausting all selected floors above the start, the elevator will reverse course and exhaust all floors below the start.


## Executing program

* How to run the program
```
mvn clean test package
java -jar target/elevator-1.0-SNAPSHOT-jar-with-dependencies.jar --start=5 --floor=1,2,3
```

## Unimplemented Features

* Multiple elevators
* external button that allows user to select up or down
* new passengers can enter the elevator at intermediate floors and add a new floor to the sequence
