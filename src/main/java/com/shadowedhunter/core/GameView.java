package com.shadowedhunter.core;

/** What the engine needs from whatever is displaying the game. */
public interface GameView {

    /** Replaces the text in the output box. */
    void showMessage(String message);

    /** Redraws everything that reflects game state (map, player, health, inventory, stats). */
    void refresh();

    /** Shows the command cheat sheet for a while. */
    void showCheatSheet();
}
