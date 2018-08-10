package qbert.model.components;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import qbert.model.characters.Player;
import qbert.model.characters.states.QbertOnDiskState;
import qbert.model.Disk;
import qbert.model.DiskImpl;
import qbert.model.LevelSettings;
import qbert.model.Tile;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
import qbert.view.DiskGC;
import qbert.view.DiskGCImpl;
import qbert.view.ReversibleTileGC;
import qbert.view.TileGC;
import qbert.view.BaseTileGC;

/**
 * Component managing information about the game map and its collections of {@link Tile} and {@link Disk}.
 */
public class MapComponent {

    private final Map<Integer, Map<Integer, Tile>> tiles;
    private final Map<Integer, List<Integer>> sideTiles;
    private final Map<Integer, Map<Integer, Optional<Disk>>> disks;

    /**
     * Constructor of MapComponent class.
     * @param settings Object containing the parameters of the current level
     */
    public MapComponent(final LevelSettings settings) {
        int disksToPlace = settings.getDisksNumber();
        final int diskVelocity = 40;
        final Random rand = new Random();

        final Map<Integer, BufferedImage> colors = settings.getColorMap();
        this.tiles = new HashMap<>();
        this.sideTiles = new HashMap<>();

        for (int i = 1; i < Dimensions.MAP_ROWS; i += 2) {
            for (int j = i; j < Dimensions.MAP_COLUMNS - i; j += 4) {

                TileGC gComponent;
                if (settings.isReversible()) {
                    gComponent = new ReversibleTileGC(colors);
                } else {
                    gComponent = new BaseTileGC(colors);
                }

                if (!this.tiles.containsKey(j)) {
                    final Map<Integer, Tile> column = new HashMap<>();
                    column.put(i, new Tile(new Position2D(j, i), gComponent));
                    tiles.put(j, column);
                } else {
                    tiles.get(j).put(i, new Tile(new Position2D(j, i), gComponent));
                }
            }
        }

        for (int i = 0; i < Dimensions.MAP_ROWS; i += 2) {
            for (int j = i; j < Dimensions.MAP_COLUMNS - i; j += 4) {

                if (!this.sideTiles.containsKey(j)) {
                    this.sideTiles.put(j, new ArrayList<Integer>());
                }
                this.sideTiles.get(j).add(i);
            }
        }

        for (int i = 0; i < Dimensions.MAP_ROWS; i += 2) {
            for (int j = i + 2; j < Dimensions.MAP_COLUMNS - i + 2; j += 4) {

                if (!this.sideTiles.containsKey(j)) {
                    this.sideTiles.put(j, new ArrayList<Integer>());
                }
                this.sideTiles.get(j).add(i);
            }
        }

        disks = new HashMap<>();

        for (int i = 3; i <= Dimensions.MAP_ROWS; i += 2) {
            final Map<Integer, Optional<Disk>> row = new HashMap<>();
            final Map<Integer, Optional<Disk>> row2 = new HashMap<>();
            row.put(i, Optional.empty());
            row2.put(i, Optional.empty());

            disks.put(i - 4, row);
            disks.put(Dimensions.MAP_COLUMNS - i + 3, row2);
        }

        while (disksToPlace > 0) {
            final List<Integer> yes = IntStream.iterate(3, i -> i + 2).limit((Dimensions.MAP_ROWS / 2) - 1).mapToObj(i -> i).collect(Collectors.toList()); 
            final int side = rand.nextInt(2);

            Collections.shuffle(yes);
            final int y = yes.stream().findFirst().get();
            int x;

            if (side > 0) {
                x = y - 4;
            } else {
                x = Dimensions.MAP_COLUMNS + 3 - y;
            }

            if (disks.containsKey(x) && disks.get(x).containsKey(y) && !disks.get(x).get(y).isPresent()) {
                final Map<Integer, BufferedImage> im = new HashMap<>();
                im.put(0, Sprites.disk1);
                im.put(1, Sprites.disk2);
                im.put(2, Sprites.disk3);
                im.put(3, Sprites.disk4);
                final DiskGC diskG = new DiskGCImpl(new Position2D(x, y), im, diskVelocity);
                final Disk disk = new DiskImpl(new Position2D(x, y), diskG);
                disks.get(x).put(y, Optional.of(disk));

                disksToPlace--;
            }
        }
        this.reset();
    }

    /**
     * Gets the {@link Tile} located in the said {@link Position2D}.
     * @param pos Position of the required {@link Tile}
     * @return an {@link Optional} containing the {@link Tile} if there is one 
     * in the requested {@link Position2D}, otherwise an {@link Optional} empty
     */
   public Optional<Tile> getTile(final Position2D pos) {
       if (this.tiles.containsKey(pos.getX()) && this.tiles.get(pos.getX()).containsKey(pos.getY())) {
           return Optional.of(tiles.get(pos.getX()).get(pos.getY()));
       }
       return Optional.empty();
   }

   private void reset() {
       this.getTileList().forEach(t -> t.reset());
   }

   /**
    * Gets the list of Tiles.
    * @return List of {@link Tile} currently in the map
    */
   public List<Tile> getTileList() {
       return this.tiles
               .entrySet()
               .stream()
               .flatMap((Map.Entry<Integer, Map<Integer, Tile>> me) -> me.getValue()
                       .entrySet()
                       .stream()
                       .map(Map.Entry::getValue))
               .collect(Collectors.toList());
   }

   /**
    * Gets the list of Disks.
    * @return List of {@link Disk} currently in the map
    */
   public List<Disk> getDiskList() {
       return this.disks
               .entrySet()
               .stream()
               .flatMap((Map.Entry<Integer, Map<Integer, Optional<Disk>>> me) -> me.getValue()
                       .entrySet()
                       .stream()
                       .map((Map.Entry<Integer, Optional<Disk>> opt) -> opt)
                       .filter(opt -> opt.getValue().isPresent())
                       .map(opt -> opt.getValue().get()))
               .collect(Collectors.toList());
   }

   /**
    * Increments the color value for the {@link Tile} in a given {@link Position2D}.
    * @param pos Position of the {@link Tile}
    * @return Number of points given by the action
    */
   public int incrementColor(final Position2D pos) {
       if (this.getTile(pos).isPresent()) {
           return this.getTile(pos).get().increment();
       } else {
           return 0;
       }
   }

   /**
    * Sets a {@link Tile} to its original color.
    * @param pos Position of the {@link Tile}
    */
   public void resetColor(final Position2D pos) {
       if (this.getTile(pos).isPresent()) {
           this.getTile(pos).get().reset();
       }
   }

   /**
    * Checks if the given position is placed outside the map.
    * @param logicPos Position to check
    * @return True if the position is outside the map
    */
   public boolean isOnVoid(final Position2D logicPos) {
       return !(this.getTile(logicPos).isPresent() 
               || (this.sideTiles.containsKey(logicPos.getX()) && this.sideTiles.get(logicPos.getX()).contains(logicPos.getY())));
   }

   /**
    * Checks if player is standing on a {@link Disk}.
    * @param qbert {@link Player} object
    * @return True if the player is on a disk
    */
   public boolean checkForDisk(final Player qbert) {
       final Position2D logicPos = qbert.getNextPosition();
       if (this.isOnVoid(logicPos) && disks.containsKey(logicPos.getX()) && disks.get(logicPos.getX()).containsKey(logicPos.getY())) {
           final Optional<Disk> disk = disks.get(logicPos.getX()).get(logicPos.getY());
           if (disk.isPresent()) {
               disks.get(logicPos.getX()).put(logicPos.getY(), Optional.empty());
               qbert.setCurrentState(new QbertOnDiskState(qbert));

               return true;
           }
       }
       return false;
   }
}
