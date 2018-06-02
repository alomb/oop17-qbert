package qbert.model.components;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import qbert.model.characters.Character;
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
    
    public MapComponent(LevelSettings settings) {
        this.settings = settings;
        
        final Map<Integer, BufferedImage> colors = this.settings.getColorMap();
        tiles = new HashMap<>();
        
        for (int i = 0; i < this.MAP_COLUMNS; i++) {
            Map<Integer, Tile> column = new HashMap<>();
            
            for (int j = 0; j <= i && j < this.MAP_COLUMNS - i; j++) {
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
        
        for (int i = 1; i <= this.MAP_ROWS; i++) {
            Map<Integer, Optional<Disk>> row = new HashMap<>();
            Map<Integer, Optional<Disk>> row2 = new HashMap<>();
            row.put(i, Optional.empty());
            row2.put(i, Optional.empty());
            
            disks.put(i - 2, row);
            disks.put((this.MAP_ROWS - i) * 2 + i, row2);
        }
        
        Map<Integer, BufferedImage> im = new HashMap<>();
        im.put(0, Sprites.disk1);
        im.put(1, Sprites.disk2);
        im.put(2, Sprites.disk3);
        im.put(3, Sprites.disk4);
        DiskGC diskG = new DiskGCImpl(new Position2D(0, 2), im, 2);
        Disk disk = new DiskImpl(new Position2D(0, 2), diskG);
        Optional<Disk> optDisk = Optional.of(disk);
        Map<Integer, Optional<Disk>> tmp = new HashMap<>();
        tmp.put(2, optDisk);
        disks.put(0, tmp);
        

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

   
   public int increment(Position2D pos) {
       return this.getTile(pos).increment();
   }

   
   public void decrement(Position2D pos) {
       this.getTile(pos).decrement();
   }
   
   public boolean isBelowMap(Position2D logicPos) {
       return logicPos.getY() < this.MAP_BOTTOM_EDGE;
   }
   
   public boolean isOverMap(Position2D logicPos) {
       return logicPos.getX() + logicPos.getY() == this.MAP_RIGHT_TOP_EDGE 
               || logicPos.getY() - logicPos.getX() == this.MAP_LEFT_TOP_EDGE;
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
