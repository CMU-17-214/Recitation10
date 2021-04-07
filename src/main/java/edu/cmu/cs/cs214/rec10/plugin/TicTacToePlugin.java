package edu.cmu.cs.cs214.rec10.plugin;

import edu.cmu.cs.cs214.rec10.framework.core.GameFramework;
import edu.cmu.cs.cs214.rec10.framework.core.GamePlugin;
import edu.cmu.cs.cs214.rec10.framework.core.Player;

/**
 * An example Tic-Tac-Toe game plug-in.
 */
public class TicTacToePlugin implements GamePlugin {

    private static final String GAME_NAME = "Tic-Tac-Toe - Best-Game-Ever";

    private static final int GRID_SIZE = 3;

    private static final String PLAYER_WON_MSG = "%s has won the game!";
    private static final String GAME_TIED_MSG = "The game has ended in a tie.";

    private GameFramework framework;
    private int numOccupiedSquares = 0;
    private boolean stalemate;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return GRID_SIZE;
    }

    @Override
    public int getGridHeight() {
        return GRID_SIZE;
    }

    @Override
    public void onRegister(GameFramework f) {
        framework = f;
    }

    @Override
    public void onNewGame() {
        numOccupiedSquares = 0;
        stalemate = true;
    }

    @Override
    public boolean isMoveValid(int x, int y) {
        return framework.getSquare(x, y) == null;
    }

    @Override
    public void onNewMove() {
        // Nothing to do here.
    }

    // Side effect: sets stalemate to false if current player has won;
    @Override
    public boolean isGameOver() {
        if (hasWon(framework.getCurrentPlayer())) {
            stalemate = false;
            return true;
        }
        return isFull();
    }

    @Override
    public String getGameOverMessage() {
        return stalemate ? GAME_TIED_MSG
                : String.format(PLAYER_WON_MSG, framework.getCurrentPlayer().getName());
    }

    private boolean isFull() {
        return numOccupiedSquares == GRID_SIZE * GRID_SIZE;
    }

    /**
     * Checks if the designated player has won the game.
     */
    private boolean hasWon(Player player) {
        final String symbol = player.getSymbol();

        // Check for a horizontal win.
        for (int row = 0; row < GRID_SIZE; row++) {
            if (isWin(row, 0, 0, 1, symbol))
                return true;
        }

        // Check for a vertical win.
        for (int col = 0; col < GRID_SIZE; col++) {
            if (isWin(0, col, 1, 0, symbol))
                return true;
        }

        // Check for a diagonal win.
        return isWin(0, 0, 1, 1, symbol) || isWin(0, GRID_SIZE - 1, 1, -1, symbol);
    }

    /**
     * Checks for a win on the line consisting  of (startRow, startCol),
     * (startRow + rowInc, startCol + colInc), and
     * (startRow + 2 * rowInc, startCol + 2 * colInc)
     */
    private boolean isWin(int startRow, int startCol, int rowInc, int colInc, String symbol) {
        for(int i = 0; i < GRID_SIZE; i++) {
            if (!symbol.equals(framework.getSquare(startCol, startRow)))
                return false;
            startRow += rowInc;
            startCol += colInc;
        }
        return true;
    }

    @Override
    public boolean isMoveOver() {
        // A move is always over after every valid action.
        return true;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        framework.setSquare(x, y, framework.getCurrentPlayer().getSymbol());
        numOccupiedSquares++;
    }

	@Override
	public void onGameClosed() {
	}
}
