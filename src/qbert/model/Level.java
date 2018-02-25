package qbert.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import qbert.model.utilities.Position2D;
import qbert.view.CharacterGraphicComponentImpl;

public class Level {

    private Map<Integer, Map<Integer, Tile>> tiles;
    private List<Character> gameCharacters;
    private int mapHeight = 7;
    private BufferedImage background;
    private List<Tile> tempTileList;

    public Level() {
        this.createLevelTiles();
        this.importBackground();

        this.gameCharacters = new ArrayList<>();
    }

    public Tile getTile(final int x, final int y) {
        return tiles.get(x).get(y);
    }
    
    public BufferedImage getBackground() {
        return this.background;
    }

    private void createLevelTiles() {
        tiles = new HashMap<>();
        
        //TODO: Utilizzare un metodo di creazione dei tile migliore
        this.tempTileList = new ArrayList<>();
        for (int i = 1; i <= this.mapHeight; i++) {
            Map<Integer, Tile> tmpMap = new HashMap<>();
            for (int j = 1; j <= this.mapHeight; j++) {
                if (j <= i) {
                    //Creazione di tile con colore "casuale"
                    Tile tmpTile = new Tile(i * 100, j * 70, j % 3);
                    tmpMap.put(j, tmpTile);
                    this.tempTileList.add(tmpTile);
                }
                tiles.put(i, tmpMap);
            }
        }
    }
    
    private void importBackground() {
        final URL mapSpriteUrl = this.getClass().getResource("/background.png");
        try {
            this.background = ImageIO.read(mapSpriteUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Character> getEntities() {
        return this.gameCharacters;
    }
    
    public List<Tile> getTiles() {
        //TODO: Ottenere lista da struttura dati "this.tiles"
        return this.tempTileList;
    }
}
