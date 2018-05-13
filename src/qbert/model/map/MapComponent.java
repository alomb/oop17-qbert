package qbert.model.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import qbert.model.LevelSettings;
import qbert.model.Tile;
import qbert.model.utilities.Position2D;

public class MapComponent {

    private Map<Integer, Map<Integer, Tile>> tiles;
    
    public MapComponent(LevelSettings settings) {
        tiles = new HashMap<>();
        Map<Integer, Tile> tmp = new HashMap<>();
        tmp.put(0, new Tile(0, 0, settings));
        tiles.put(0, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(1, 1, settings));
        tiles.put(1, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(2, 0, settings));
        tmp.put(2, new Tile(2, 2, settings));
        tiles.put(2, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(3, 1, settings));
        tmp.put(3, new Tile(3, 3, settings));
        tiles.put(3, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(4, 0, settings));
        tmp.put(2, new Tile(4, 2, settings));
        tmp.put(4, new Tile(4, 4, settings));
        tiles.put(4, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(5, 1, settings));
        tmp.put(3, new Tile(5, 3, settings));
        tmp.put(5, new Tile(5, 5, settings));
        tiles.put(5, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(6, 0, settings));
        tmp.put(2, new Tile(6, 2, settings));
        tmp.put(4, new Tile(6, 4, settings));
        tmp.put(6, new Tile(6, 6, settings));
        tiles.put(6, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(7, 1, settings));
        tmp.put(3, new Tile(7, 3, settings));
        tmp.put(5, new Tile(7, 5, settings));
        tiles.put(7, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(8, 0, settings));
        tmp.put(2, new Tile(8, 2, settings));
        tmp.put(4, new Tile(8, 4, settings));
        tiles.put(8, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(9, 1, settings));
        tmp.put(3, new Tile(9, 3, settings));
        tiles.put(9, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(10, 0, settings));
        tmp.put(2, new Tile(10, 2, settings));
        tiles.put(10, tmp);

        tmp = new HashMap<>();
        tmp.put(1, new Tile(11, 1, settings));
        tiles.put(11, tmp);

        tmp = new HashMap<>();
        tmp.put(0, new Tile(12, 0, settings));
        tiles.put(12, tmp);

        this.resetLevelTiles();
    }
    
   public Tile getTile(final Position2D pos) {
       return tiles.get(pos.getX()).get(pos.getY());
   }


   private void resetLevelTiles() {
       this.getTileList().stream().forEach(t -> {
           t.resetColor();
       });
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

}
