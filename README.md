# SpaceGame

A 2D arcade-style space shooter game developed using the Java Swing library. The game features a user authentication system, a difficulty selection screen, and dynamic gameplay. Players control a spaceship at the bottom of the screen, with the objective of shooting a moving meteorite target at the top while dodging falling obstacles.

## Features

- **User Authentication:** A complete login and registration system. New users can register, and existing users can log in to play.
- **Difficulty Selection:** After logging in, players are presented with a screen to choose the game's difficulty.
- **Dynamic Gameplay:**
  - Control a spaceship with left and right movement.
  - Fire projectiles to destroy the target.
  - Evade randomly generated falling obstacles (stars).
- **Win/Loss Conditions:** The game ends when the player successfully destroys the target (Win) or collides with a falling obstacle (Loss).
- **Game Statistics:** Upon completion, the game displays performance metrics, including the total time played and the number of shots fired.

## Objective & How to Play

\*\*RUN the 'StartGame' class to launch the application. After logging in, select the desired difficulty level to start playing.

**Note:** Currently, only the **EASY** mode is fully implemented and playable.

- **Move Left:** `Left Arrow` key
- **Move Right:** `Right Arrow` key
- **Shoot:** `Spacebar` or `Ctrl` key

**The Objective:** The ultimate goal of the game is to successfully shoot and destroy the moving meteorite at the top of the screen in the shortest amount of time, using the fewest number of shots possible. You must achieve this without crashing into any of the falling stars!

## Upcoming Features (Future Versions)

The game is continuously being improved. Here is what is planned for future updates:

- **Database Integration:** Saving user credentials (username and password) securely to a database instead of temporary local memory.
- **Visual Enhancements:** A complete overhaul of the UI/UX with better background graphics and improved typography.
- **Health System:** Introducing a "Lives" mechanic so a single collision doesn't immediately end the game.
- **Difficulty Levels:** Full implementation of Medium, Hard, and Special modes with increasing challenges.
- **Progression & Abilities:** A new leveling system and special power-ups/abilities for the spaceship.
- _And much more!_

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

You will need a Java Development Kit (JDK) installed on your machine.

### Installation and Execution

1.  **Clone the repository:**

    ```sh
    git clone [https://github.com/dubomr/spacegame.git](https://github.com/dubomr/spacegame.git)
    cd spacegame
    ```

2.  **Ensure Assets are Present:**
    The game requires the following image files in the root directory of the project to run correctly:
    - `spacebck.jpg`
    - `Spaceship.png`
    - `stone.png`
    - `star.png`

3.  **Compile the Java files:**
    From the root directory, run the Java compiler:
    ```sh
    javac *.java
    ```
4.  **Run the application:**
    The main entry point is `StartGame`, which will launch the login screen.
    ```sh
    java StartGame
    ```
    You must first register an account and then log in to start the game.

## File Descriptions

- **`LoginScreen.java`**: The initial GUI screen for user login. It validates user credentials and provides access to the registration screen.
- **`RegisterScreen.java`**: Allows new users to create an account. User data is stored in memory for the duration of the session.
- **`DifficultyScreen.java`**: A screen that appears after a successful login, allowing the player to select a game difficulty.
- **`StartGame.java`**: The main `JFrame` that sets up the game window and contains the `Game` panel. This is the main executable class.
- **`Game.java`**: The core `JPanel` that handles all game logic, rendering, keyboard inputs, and the main game loop.
