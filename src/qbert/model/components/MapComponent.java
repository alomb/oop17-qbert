package qbert.model.components;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import qbert.model.characters.Player;
import qbert.model.states.QbertOnDiskState;
import qbert.model.Disk;
import qbert.model.DiskImpl;
import qbert.model.LevelSettings;
import qbert.model.Tile;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
import qbert.view.DiskGC;
import qbert.view.DiskGCImpl;
import qbert.view.ReversibleTileGC;
import qbert.view.TileGC;
import qbert.view.BaseTileGC;

public class MapComponent {
    
    public static final int MAP_LEFT_TOP_EDGE = 2;
    public static final int MAP_RIGHT_TOP_EDGE = 14;
    public static final int MAP_BOTTOM_EDGE = 0;
    
    public static final int MAP_COLUMNS = 13;
    public static final int MAP_ROWS = 7;
    public static final int MAP_BEHIND_INDEX = -1;

    private Map<Integer, Map<Integer, Tile>> tiles;
    private Map<Integer, Map<Integer, Optional<Disk>>> disks;
    private LevelSettings settings;
    private int disksToPlace;
    
    public MapComponent(LevelSettings settings) {
        this.settings = settings;
        disksToPlace = settings.getDisksNumber();
        Random rand = new Random();
        
        final Map<Integer, BufferedImage> colors = this.settings.getColorMap();
        tiles = new HashMap<>();
        
        for (int i = 0; i < MapComponent.MAP_COLUMNS; i++) {
            Map<Integer, Tile> column = new HashMap<>();
            
            for (int j = 0; j <= i && j < MapComponent.MAP_COLUMNS - i; j++) {
                if (i % 2 == j % 2) {
                    TileGC gComponent;
                    if (settings.isReversible()) {
                        gComponent = new ReversibleTileGC(colors);
                    } else {
                        gComponent = new BaseTileGC(colors);
                    }
                    column.put(j, new Tile(i, j, gComponent));
                    tiles.put(i, column);
                }
            }
        }
        

        disks = new HashMap<>();
        
        for (int i = 1; i <= MapComponent.MAP_ROWS; i++) {
            Map<Integer, Optional<Disk>> row = new HashMap<>();
            Map<Integer, Optional<Disk>> row2 = new HashMap<>();
            row.put(i, Optional.empty());
            row2.put(i, Optional.empty());
            
            disks.put(i - 2, row);
            disks.put((MapComponent.MAP_ROWS - i) * 2 + i, row2);
        }
        
        while (disksToPlace > 0) {
            int n = rand.nextInt(6) + 1;
            int side = rand.nextInt(2);
            int y = n;
            int x;
            
            if (side > 0) {
                x = n - 2;
            } else {
                x = 14 - n;
            }
            
            if (!disks.get(x).get(y).isPresent()) {
                Map<Integer, BufferedImage> im = new HashMap<>();
                im.put(0, Sprites.disk1);
                im.put(1, Sprites.disk2);
                im.put(2, Sprites.disk3);
                im.put(3, Sprites.disk4);
                DiskGC diskG = new DiskGCImpl(new Position2D(x, y), im, 1);
                Disk disk = new DiskImpl(new Position2D(x, y), diskG);
                disks.get(x).put(y, Optional.of(disk));
                
                disksToPlace--;
            }
        }
        

        this.reset();
    }
    
   public Tile getTile(final Position2D pos) {
       return tiles.get(pos.getX()).get(pos.getY());
   }


   private void reset() {
       this.getTileList().forEach(t -> t.reset());
   }


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

   
   public int incrementColor(Position2D pos) {
       return this.getTile(pos).increment();
   }

   
   public void resetColor(Position2D pos) {
       this.getTile(pos).reset();
   }
   
   public boolean isBelowMap(Position2D logicPos) {
       return logicPos.getY() < MapComponent.MAP_BOTTOM_EDGE;
   }
   
   public boolean isOverMap(Position2D logicPos) {
       return logicPos.getX() + logicPos.getY() == MapComponent.MAP_RIGHT_TOP_EDGE 
               || logicPos.getY() - logicPos.getX() == MapComponent.MAP_LEFT_TOP_EDGE;
   }

   public boolean isOnVoid(Position2D logicPos) {
       return this.isBelowMap(logicPos) || this.isOverMap(logicPos);
   }
   
   public boolean checkForDisk(Player qbert) {
       Position2D logicPos = qbert.getNextPosition();
       if (this.isOverMap(logicPos)) {
           Optional<Disk> disk = disks.get(logicPos.getX()).get(logicPos.getY());
           if (disk.isPresent()) {
               disks.get(logicPos.getX()).put(logicPos.getY(), Optional.empty());
               qbert.setCurrentState(new QbertOnDiskState(qbert));

               return true;
           }
       }
       return false;
   }
}
