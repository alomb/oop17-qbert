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
import qbert.view.TileGCImpl;

public class MapComponent {
    
    private final int MAP_LEFT_TOP_EDGE = 2;
    private final int MAP_RIGHT_TOP_EDGE = 14;
    private final int MAP_BOTTOM_EDGE = 0;

    private Map<Integer, Map<Integer, Tile>> tiles;
    private Map<Integer, Map<Integer, Optional<Disk>>> disks;
    private LevelSettings settings;
    
    public MapComponent(LevelSettings settings) {
        this.settings = settings;
        
        final Map<Integer, BufferedImage> colors = this.settings.getColorMap();
        
        tiles = new HashMap<>();
        Map<Integer, Tile> tmp = new HashMap<>();
        tmp.put(0, new Tile(0, 0, new TileGCImpl(colors)));
        tiles.put(0, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(1, 1, new TileGCImpl(colors)));
        tiles.put(1, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(2, 0, new TileGCImpl(colors)));
        tmp.put(2, new Tile(2, 2, new TileGCImpl(colors)));
        tiles.put(2, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(3, 1, new TileGCImpl(colors)));
        tmp.put(3, new Tile(3, 3, new TileGCImpl(colors)));
        tiles.put(3, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(4, 0, new TileGCImpl(colors)));
        tmp.put(2, new Tile(4, 2, new TileGCImpl(colors)));
        tmp.put(4, new Tile(4, 4, new TileGCImpl(colors)));
        tiles.put(4, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(5, 1, new TileGCImpl(colors)));
        tmp.put(3, new Tile(5, 3, new TileGCImpl(colors)));
        tmp.put(5, new Tile(5, 5, new TileGCImpl(colors)));
        tiles.put(5, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(6, 0, new TileGCImpl(colors)));
        tmp.put(2, new Tile(6, 2, new TileGCImpl(colors)));
        tmp.put(4, new Tile(6, 4, new TileGCImpl(colors)));
        tmp.put(6, new Tile(6, 6, new TileGCImpl(colors)));
        tiles.put(6, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(7, 1, new TileGCImpl(colors)));
        tmp.put(3, new Tile(7, 3, new TileGCImpl(colors)));
        tmp.put(5, new Tile(7, 5, new TileGCImpl(colors)));
        tiles.put(7, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(8, 0, new TileGCImpl(colors)));
        tmp.put(2, new Tile(8, 2, new TileGCImpl(colors)));
        tmp.put(4, new Tile(8, 4, new TileGCImpl(colors)));
        tiles.put(8, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(9, 1, new TileGCImpl(colors)));
        tmp.put(3, new Tile(9, 3, new TileGCImpl(colors)));
        tiles.put(9, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(10, 0, new TileGCImpl(colors)));
        tmp.put(2, new Tile(10, 2, new TileGCImpl(colors)));
        tiles.put(10, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(11, 1, new TileGCImpl(colors)));
        tiles.put(11, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(12, 0, new TileGCImpl(colors)));
        tiles.put(12, tmp);
        

        //Disk Test Implementation
        disks = new HashMap<>();
        
        Map<Integer, BufferedImage> im = new HashMap<>();
        im.put(0, Sprites.disk1);
        im.put(1, Sprites.disk2);
        im.put(2, Sprites.disk3);
        im.put(3, Sprites.disk4);
        DiskGC diskG = new DiskGCImpl(new Position2D(0, 2), im, 2);
        Disk disk = new DiskImpl(new Position2D(0, 2), diskG);
        Optional<Disk> optDisk = Optional.of(disk);
        Map<Integer, Optional<Disk>> tmp2 = new HashMap<>();
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
                       .map((Map.Entry<Integer, Optional<Disk>> opt) -> opt.getValue().get()))
               .collect(Collectors.toList());
   }

   
   public void increment(Position2D pos) {
       Tile t = this.getTile(pos);
       if (t.getColor() < this.settings.getColorsNumber()) {
           t.increment();
       } else {
           if (this.settings.isReversible()) {
               t.reset();
           }
       }
   }

   
   public void decrement(Position2D pos) {
       Tile t = this.getTile(pos);
       if (t.getColor() > 0) {
           t.decrement();
       }
   }

   public boolean isOnVoid(Position2D logicPos) {
       return logicPos.getY() < this.MAP_BOTTOM_EDGE 
               || logicPos.getX() + logicPos.getY() == this.MAP_RIGHT_TOP_EDGE 
               || logicPos.getY() - logicPos.getX() == this.MAP_LEFT_TOP_EDGE;
   }
   
   public void checkForDisk(Player qbert) {
       Position2D logicPos = qbert.getNextPosition();
       if (this.isOnVoid(logicPos)) {
           Optional<Disk> disk = disks.get(logicPos.getX()).get(logicPos.getY());
           if (disk.isPresent()) {
               disks.get(logicPos.getX()).clear();
               qbert.setCurrentState(new QbertOnDiskState(qbert));
           }
       }
   }
}
