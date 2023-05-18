ElevatorSystem coordinates elevators.

Each Elevator goes upwards and downwards. When it has certain direction, it follows it until there are no more
request that requieres going this direction, then it switches direction. This approach ensures that there will be no starvation and each person finally can leave at its floor.

We want to pickup an elevator that is closest to us and goes in the same direction as we want. If there is no such
elevator, we are going to choose the furthest one, as it may be the first that will come to us.

I interpreted update as pressing a button of chosen floor in one of the elevator, therefore I only need
elevatorId and floor.

mvn clean install
java -cp target/classes elevatorsystem.Main