package edu.cmu.cs.cs214.rec10.plugin;

import edu.cmu.cs.cs214.rec10.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec10.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec10.framework.core.Player;

import java.util.*;


public class MoneyDoorPlugin {
    private int[] arr = {-3, -2, -1, 0, 1, 2, 3, 4, 5, 6};
    private int ONE = 1;
    private int TEN = 10;

    // The game framework
    private GameFramework framework = null;

    // The amount of money each player has
    public HashMap<String, Integer> PlayerScores = new HashMap<String, Integer>();

    public String getGameName() {
        return new String("money door game");
    }

    public int getGridWidth() {
        return TEN;
    }

    public int getGridHeight() {
        return ONE;
    }

    public void onRegister(GameFramework fwk) {
        this.framework = fwk;
    }

    public void onNewGame() {
        // Make random permutation of the array
        Random random = new Random();

        int i;
        // For every position i, chooses a random position >= i and swaps it with the value at position i,
        // generating a random permutation of arr
        for(i = 0; i < 10; i++) {
            int nextIndex;

            // Find an index at position >= i to swap to position i
            while ((nextIndex = random.nextInt(10)) < i);  // choosing a random number in the range [i, 10).

            // Swap positions i, nextIndex
            int temp = arr[i];
            arr[i] = arr[nextIndex];
            arr[nextIndex] = temp;
        }

        // Set all doors to initially be closed
        for(i = 0; i < 10; i++) {
            framework.setSquare(i, 0, "Door closed!");
        }

        // Fill the scores with initial scores for each player.
        PlayerScores.clear();
        PlayerScores.put("O", 0);
        PlayerScores.put("X", 0);
    }

    public void onNewMove() {
        System.out.println("on new move");
    }

    public boolean isMoveValid(int x, int y) {
        return framework.getSquare(x, 0) == "Door closed!";
    }
}
