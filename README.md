This project is an attempt at the "Mobile Device Keyboard" coding challenge. It is implemented in Java, and all of the source code is contained in the package directory "autocomplete"

For the purpose of this challenge, openJDK was used to build and run the code in a linux environment. OpenJDK can be installed using the following command as root:

apt-get install openjdk-8-jdk

The following steps are geared toward this environment and version of Java, although it will work similarly on many different systems. In general, you must compile all of the .java files and run the generated Driver.class file. 

STEPS TO BUILD THE CODE

1. Navigate to the directory where you will clone the repository

2. Clone the git repository using the following commands in the terminal (make sure git is installed):

git init

git clone https://github.com/daniel-sachs/AsymmetrikCodingChallenge


3. Navigate to the root repository directory you just created using the command

cd AsymmetrikCodingChallenge

4. From this directory, compile the .java files by running the command

javac autocomplete/*.java

5. From the same directory, run the code by executing the command

java autocomplete.Driver

STEPS TO TEST THE CODE

1. Once the code is running, it will display a main menu. You have the option to train the algorithm with a passage of text, test the algorithm by entering a word fragment, or exit the program.

2. If you select to train the code, you can enter any number of words you want the algorithm to learn. The program stores these words in a Prefix Tree data structure to optimize searching.

3. If you select testing from the menu, you will be prompted to enter a fragment of a word. The program will search through the data structure to find all previously trained words that contain the fragment as a prefix, and will display this list of words in order of their frequency they have appeared.

4. If you choose to exit, the program stops execution. Otherwise, the user continues to be prompted with the main menu. After exiting, the data structure will be reset for the next run of the program.


