ElevatorSystem coordinates elevators.

Each elevator travels both upwards and downwards. When an elevator has a specific direction, it continues moving in that direction until there are no more requests that require movement in that direction. As request, I understand pressing a button outside of elevator to get into it or inside the elevator (pickup and update functions). Then, if there is no request in the opposite direction, it switches its direction. This approach ensures that no passengers are left stranded - there will be no starvation, and each person can eventually exit at their intended floor. Furthermore, this method is highly efficient as it allows us to stop at every floor where there is a request.

My goal is to choose the elevator that will require the least number of steps to reach us. However, it is difficult to determine the exact number of steps needed in scenarios where the elevator is moving upwards and the requested floor is below it, or when the elevator is moving downwards and the requested floor is above it. In these situations, there may be additional requests that require the elevator to visit multiple floors between its current position and the top or bottom, respectively.
To handle this worst-case scenario, I assume that the elevator needs to travel to the top floor and then from the top floor to the desired floor, or vice versa, unless there are no requests for higher floors while going upwards or no requests for lower floors while going downwards. By considering this worst-case scenario, I can ensure that I make a safe decision regarding the elevator selection.

I interpret "update" as pressing the button for the desired floor inside one of the elevators while being on a certain floor.

I interpret "step()" as moving to the next floor by either going one floor upwards, one floor downwards, or staying if there are no requests.

To run the program, please follow these commands in the project folder within the terminal:
mvn clean install
java -cp target/classes elevatorsystem.Main

1. The first command must be "SET <number of elevators> <number of floors>".
2. Other available commands are:
a) "PICKUP <floor's number> <flag (int)>": If the flag is negative, it means we want to go downwards; otherwise, it indicates going upwards. This command triggers the pickup function.
b) "UPDATE <ID> <number of current floor> <number of destination floor>": This command triggers the update function,
it adds a request to get to a certain floor and is pressed while elevator with id = ID is on <number of current floor>'th floor.
c) "STATUS": This command triggers the status function and displays a collection of triples: <ID, current floor number, desired floor number>.
d) "STEP": This command triggers the step function, causing each elevator to change its floor by one.
If an incorrect command is entered, it will be ignored, and the message "Invalid command" will be displayed. The user can continue entering commands.
3. The last command is "QUIT," which terminates the program.

I wasn't certain about the interpretation of "step" - it could also refer to the exchange between the desired floor and the current floor. However, in this case, we need to disregard the time it takes for the elevator to change floors.

In future iterations of the project, it would be beneficial to incorporate the concept of staying time. Currently, there is no distinction between exchanges between floors with no requests and exchanges with multiple requests, as they are considered the same. Additionally, adding elevator capacity would be valuable. However, to implement these features, we would need specific information regarding passengers' desired destinations and origins. In this version, I have omitted capacity considerations and interpreted "step" as changing the floor by one.