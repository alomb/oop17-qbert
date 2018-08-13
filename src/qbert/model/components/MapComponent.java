package qbert.model.components;

import java.util.List;
import java.util.Optional;

import qbert.model.characters.Player;
import qbert.model.Disk;
import qbert.model.Tile;
import qbert.model.utilities.Position2D;

/**
 * Component managing information about the game map and its collections of {@link Tile} and {@link Disk}.
 */
public interface MapComponent {

    /**
     * Gets the {@link Tile} located in the said {@link Position2D}.
     * @param pos Position of the required {@link Tile}
     * @return an {@link Optional} containing the {@link Tile} if there is one 
     * in the requested {@link Position2D}, otherwise an {@link Optional} empty
     */
   Optional<Tile> getTile(Position2D pos);

   /**
    * Gets the list of Tiles.
    * @return List of {@link Tile} currently in the map
    */
   List<Tile> getTileList();

   /**
    * Gets the list of Disks.
    * @return List of {@link Disk} currently in the map
    */
   List<Disk> getDiskList();

   /**
    * Increments the color value for the {@link Tile} in a given {@link Position2D}.
    * @param pos Position of the {@link Tile}
    * @return Number of points given by the action
    */
   int incrementColor(Position2D pos);

   /**
    * Sets a {@link Tile} to its original color.
    * @param pos Position of the {@link Tile}
    */
   void resetColor(Position2D pos);

   /**
    * Checks if the given position is placed outside the map.
    * @param logicPos Position to check
    * @return True if the position is outside the map
    */
   boolean isOnVoid(Position2D logicPos);

   /**
    * Checks if player is standing on a {@link Disk}.
    * @param qbert {@link Player} object
    * @return True if the player is on a disk
    */
   boolean checkForDisk(Player qbert);
}
