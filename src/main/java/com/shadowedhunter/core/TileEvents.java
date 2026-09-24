package com.shadowedhunter.core;

import com.shadowedhunter.resources.LoreTexts;
import com.shadowedhunter.world.Floor;
import com.shadowedhunter.world.Tile;
import com.shadowedhunter.world.tiles.LoreTile;
import com.shadowedhunter.world.tiles.TrapTile;
import com.shadowedhunter.world.traps.Trap;

/** Resolves what happens when the player steps onto a tile. */
public final class TileEvents {

    private TileEvents() {}

    /**
     * Triggers the tile under the player, if it has anything to trigger.
     *
     * @return true if an event fired (its message is displayed), so movement should stop
     */
    public static boolean onEnter(GameEngine engine) {
        var player = engine.getGameState().getPlayer();
        Floor floor = engine.getWorld().getCurrentFloor();
        Tile tile = floor.getTile(player.getX(), player.getY());

        if (tile instanceof TrapTile trapTile && !trapTile.isTriggered()) {
            // Marked first: a triggered trap stays triggered even if it kills the player
            trapTile.trigger();
            engine.displayMessage(Trap.forType(trapTile.getTrapType()).trigger(engine));
            // Death or a hole may have moved the player
            engine.syncIconToPlayerPosition();
            return true;
        }

        if (tile instanceof LoreTile loreTile
                && !loreTile.getCode().isEmpty()
                && LoreTexts.hasLore(loreTile.getLoreId())) {
            engine.displayMessage(LoreTexts.getLoreText(loreTile.getLoreId()));
            markRoomAsRead(floor, loreTile.getLoreId());
            return true;
        }

        return false;
    }

    // A lore code covers every tile of its room, so read it once for the whole room
    private static void markRoomAsRead(Floor floor, String loreId) {
        for (Tile[] row : floor.getAllTiles()) {
            for (Tile tile : row) {
                if (tile instanceof LoreTile loreTile && loreTile.getLoreId().equals(loreId)) {
                    loreTile.markAsRead();
                }
            }
        }
    }
}
