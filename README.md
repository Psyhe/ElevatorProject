ElevatorSystem coordinates elevators.

Each Elevator goes upwards and downwards. When it has certain direction, it follows it until there are no more
requests that requieres going this direction, then it switches direction. This approach ensures that there will be no starvation and each person finally can leave at its floor, moreover it is quite efficient - we can stop at
each floor where there is a request.

We want to pickup an elevator that requires the least amount of steps to get to us.

I interpreted update as pressing a button of chosen floor in one of the elevator while being on certain floor.

I interpreted step() as changing floor by 1 (going one floor upwards, downwards or staying if there are no requests).

In order to run program, write following commands in project folder in terminal:

mvn clean install
java -cp target/classes elevatorsystem.Main

In order to interact with a program, you can write commands in simple language. Here is the instruction:
1. First command must be "SET <number of elevators> <number of floors>"
2. Other commands are:
a) "PICKUP <number of floor> <number - if it is negative, it means that we want to go downwards, otherwise upwards>"
    - it runs pickup function.
b) "UPDATE <ID> <number of current floor> <number of destination floor>
    - it runs update function.
c) "STATUS" - it runs status function and displays collection of triples<ID, current floor number, desired floor number>
d) "STEP" - it runs step function and changes floor of each elevator in move by one.
After inserting incorrect command, such command will be ignored and the messange "Invalid command" will be displayed
and user can continue.
3. Last command is "QUIT" and it finishes the program.

I wasn't sure about interpretation of step - it may also mean exchange between desired floor and current floor, 
but in this case we need to ignore the fact that elevator needs time to change floors. 

In the future, project should be expanded by adding time of staying (right now exchange between floors with no requests 
is the same as with multiple requests) and by adding elevator capacity, but in order to do so, we need to know
exactly what person wants to leave and wants to get (update may mean that one person wants to leave, but it may
also means that all the people inside the elevator should leave), therefore in this version I ignored capacity
and I interpreted step as changing floor by one.