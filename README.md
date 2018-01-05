# gamma-tic-tac-toe

## compilation

Just clone the repository any run `mvn clean compile package` in the gamma-engine directory to compile the project.

## running

In the gamm-engine directory just execute `java -jar target/gamma-engine-0.0.1-SNAPSHOT.jar`.

## playing

## configuration

The default configuration can be found from the application.properties in the resources-folder.

// Defines the amount of training runs executed before starting to play, default is 150.
training.runs = 150

// Configuration related to selfplay
selfplay.enabled = true
selfplay.matches = 50
selfplay.games = 10000

#
# Learning level
#

learning.stage = 1

#
# Playing against human
#

humanplay.enabled = true

