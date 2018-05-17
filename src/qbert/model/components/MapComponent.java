package qbert.model.components;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import qbert.model.LevelSettings;
import qbert.model.Tile;
import qbert.model.map.Mapper;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
import qbert.view.GraphicComponent;
import qbert.view.TileGraphicComponent;

public class MapComponent {
    
    private final int MAP_LEFT_TOP_EDGE = 2;
    private final int MAP_RIGHT_TOP_EDGE = 14;
    private final int MAP_BOTTOM_EDGE = 0;

    private Map<Integer, Map<Integer, Tile>> tiles;
    private LevelSettings settings;
    
    public MapComponent(LevelSettings settings) {
        this.settings = settings;
        
        tiles = new HashMap<>();
        Map<Integer, Tile> tmp = new HashMap<>();
        tmp.put(0, new Tile(0, 0));
        tiles.put(0, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(1, 1));
        tiles.put(1, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(2, 0));
        tmp.put(2, new Tile(2, 2));
        tiles.put(2, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(3, 1));
        tmp.put(3, new Tile(3, 3));
        tiles.put(3, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(4, 0));
        tmp.put(2, new Tile(4, 2));
        tmp.put(4, new Tile(4, 4));
        tiles.put(4, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(5, 1));
        tmp.put(3, new Tile(5, 3));
        tmp.put(5, new Tile(5, 5));
        tiles.put(5, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(6, 0));
        tmp.put(2, new Tile(6, 2));
        tmp.put(4, new Tile(6, 4));
        tmp.put(6, new Tile(6, 6));
        tiles.put(6, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(7, 1));
        tmp.put(3, new Tile(7, 3));
        tmp.put(5, new Tile(7, 5));
        tiles.put(7, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(8, 0));
        tmp.put(2, new Tile(8, 2));
        tmp.put(4, new Tile(8, 4));
        tiles.put(8, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(9, 1));
        tmp.put(3, new Tile(9, 3));
        tiles.put(9, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(10, 0));
        tmp.put(2, new Tile(10, 2));
        tiles.put(10, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(11, 1));
        tiles.put(11, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(12, 0));
        tiles.put(12, tmp);

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
   
   //TODO: Find out pattern to be used to use an algorithm based on who calls the method
   public void step(Position2D pos) {
       System.out.println("Stepping");
       Tile t = this.getTile(pos);
       if (t.getColor() < this.settings.getColorNumber()) {
           t.increment();
       } else {
           if (this.settings.getColorReversable()) {
               t.reset();
           }
       }
       t.setGraphicComponent(new TileGraphicComponent(this.settings.getColorMap().get(t.getColor())));
   }
  
   public boolean isOnVoid(Position2D logicPos) {
       return logicPos.getY() < this.MAP_BOTTOM_EDGE 
               || logicPos.getX() + logicPos.getY() == this.MAP_RIGHT_TOP_EDGE 
               || logicPos.getY() - logicPos.getX() == this.MAP_LEFT_TOP_EDGE;
   }

}
