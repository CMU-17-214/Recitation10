
package edu.cmu.cs.cs214.rec10.plugin;

import edu.cmu.cs.cs214.rec10.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec10.framework.core.GamePlugin;

import java.util.Random;

public class SortWithSwapsPlugin implements GamePlugin {
    private int ONE = 1;
    private int TEN = 10;

    // Describes whether the current move is over
    public Boolean move = false;

    // An array of the numbers 1-10
    private int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    // The game framework
    private GameFramework framework = null;

    // The last number clicked, which is to be swapped with the next number clicked
    private int last = -1;

    //The number of swaps the user has made
    static Integer count = null;

    public String getGameName() {
        return new String("sort with swaps plugin");
    }

    public int getGridWidth() {
        return TEN;
    }

    public int getGridHeight() {
        return ONE;
    }

    public void onRegister(GameFramework framework) {
        this.framework = framework;
    }

    public void onNewGame() {
        // Reset count
        count = 0;

        // Make random permutation of the array
        Random random = new Random();

        int i;
        // For every position i, chooses a random position >= i and swaps it with the value at position i,
        // generating a random permutation of arr.
        for(i = 0; i < 10; i++) {
            int nextIndex;

            // Find an index at position >= i to swap to position i.
            while ((nextIndex = random.nextInt(10)) < i);  // Chooses a random number in the range [i, 10)

            // Swap positions i, nextIndex
            int temp = arr[i];
            arr[i] = arr[nextIndex];
            arr[nextIndex] = temp;
        }

        for(i = 0; i < 10; i++) {
            framework.setSquare(i, 0, Integer.toString(arr[i]));
        }
    }

    @Override
    public void onNewMove() {
        System.out.println("Status of game: at method onNewMove()");
    }

    public boolean isMoveValid(int x, int y) {
        return this.move || !this.move;
    }

    public boolean isMoveOver() {
        System.out.println(move);
        return !this.move;
    }

    public void onMovePlayed(int x, int y) {
        if (this.move) {
            String temp = framework.getSquare(last, 0);
            framework.setSquare(last, 0, framework.getSquare(x, 0));
            framework.setSquare(x, 0, temp);
            count = count + 1;
        } else {
            last = x;
        }

        this.move = !this.move;
    }

    public boolean isGameOver() {
//        System.out.println("here");

        // Checks that array is properly sorted
        for (int i = 0; i < 9; i++) {
            Integer j = Integer.parseInt(framework.getSquare(i+1, 0));
            if (j < Integer.parseInt(framework.getSquare(i, 0))) {
                return false;
            }
        }

        return true;
    }

    public String getGameOverMessage() {
        return "you did it in" + count + "moves!";
    }

    public void onGameClosed() {
        System.out.println(move);
    }
}
