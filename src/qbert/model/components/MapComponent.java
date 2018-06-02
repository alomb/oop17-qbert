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
    
    private final int MAP_LEFT_TOP_EDGE = 2;
    private final int MAP_RIGHT_TOP_EDGE = 14;
    private final int MAP_BOTTOM_EDGE = 0;
    
    private final int MAP_COLUMNS = 12;

    private Map<Integer, Map<Integer, Tile>> tiles;
    private Map<Integer, Map<Integer, Optional<Disk>>> disks;
    private LevelSettings settings;
    
    public MapComponent(LevelSettings settings) {
        this.settings = settings;
        
        final Map<Integer, BufferedImage> colors = this.settings.getColorMap();
        tiles = new HashMap<>();
        
        for (int i = 0; i <= this.MAP_COLUMNS; i++) {
            Map<Integer, Tile> column = new HashMap<>();
            
            for (int j = 0; j <= i && j <= this.MAP_COLUMNS - i; j++) {
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
        

        //Disk Test Implementation
        disks = new HashMap<>();
        Map<Integer, Optional<Disk>> tmp2 = new HashMap<>();
        tmp2.put(1, Optional.empty());
        disks.put(-1, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(2, Optional.empty());
        disks.put(0, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(3, Optional.empty());
        disks.put(1, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(4, Optional.empty());
        disks.put(2, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(5, Optional.empty());
        disks.put(3, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(6, Optional.empty());
        disks.put(4, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(7, Optional.empty());
        disks.put(5, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(7, Optional.empty());
        disks.put(7, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(6, Optional.empty());
        disks.put(8, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(5, Optional.empty());
        disks.put(6, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(4, Optional.empty());
        disks.put(10, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(3, Optional.empty());
        disks.put(11, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(2, Optional.empty());
        disks.put(12, tmp2);

        tmp2 = new HashMap<>();
        tmp2.put(1, Optional.empty());
        disks.put(13, tmp2);
        
        Map<Integer, BufferedImage> im = new HashMap<>();
        im.put(0, Sprites.disk1);
        im.put(1, Sprites.disk2);
        im.put(2, Sprites.disk3);
        im.put(3, Sprites.disk4);
        DiskGC diskG = new DiskGCImpl(new Position2D(0, 2), im, 2);
        Disk disk = new DiskImpl(new Position2D(0, 2), diskG);
        Optional<Disk> optDisk = Optional.of(disk);
        tmp2 = new HashMap<>();
        tmp2.put(2, optDisk);
        disks.put(0, tmp2);
        

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

   
   public void increment(Position2D pos) {
       this.getTile(pos).increment();
   }

   
   public void decrement(Position2D pos) {
       this.getTile(pos).decrement();
   }

   public boolean isOnVoid(Position2D logicPos) {
       return logicPos.getY() < this.MAP_BOTTOM_EDGE 
               || logicPos.getX() + logicPos.getY() == this.MAP_RIGHT_TOP_EDGE 
               || logicPos.getY() - logicPos.getX() == this.MAP_LEFT_TOP_EDGE;
   }
   
   public boolean checkForDisk(Player qbert) {
       Position2D logicPos = qbert.getNextPosition();
       if (this.isOnVoid(logicPos)) {
           Optional<Disk> disk = disks.get(logicPos.getX()).get(logicPos.getY());
           if (disk.isPresent()) {
               disks.get(logicPos.getX()).clear();
               qbert.setCurrentState(new QbertOnDiskState(qbert));

               return true;
           }
       }
    return false;
   }
}
