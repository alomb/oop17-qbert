package qbert.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import qbert.model.utilities.Position2D;
import qbert.model.Sprites;

public class Game {

    private Toolkit t; 
    private Dimension d;
    
    private Level gameLevel;
   
    public Game() throws Exception {
        
        t = Toolkit.getDefaultToolkit();
        d = t.getScreenSize();
         
          Sprites.blueTile = loadImg("res/svg/TileBlue.svg","res/png/TileBlue.png");
          Sprites.pinkTile = loadImg("res/svg/TilePink.svg","res/png/TilePink.png");
          Sprites.yellowTile = loadImg("res/svg/TileYellow.svg","res/png/TileYellow.png");
          Sprites.life = loadImg("res/svg/GreenBallStand.svg","res/png/GreenBallStand.png");
          Sprites.blueBackground = loadImg("res/svg/BackgroundBlue.svg","res/png/BackgroundBlue.png"); 
          Sprites.brownBackground = loadImg("res/svg/BackgroundBrown.svg","res/png/BackgroundBrown.png"); 
          Sprites.qbertFrontMoving = loadImg("res/svg/QbertFrontMove.svg","res/png/QbertFrontMove.png"); 
          Sprites.qbertFrontStanding = loadImg("res/svg/QbertFrontStand.svg","res/png/QbertFrontStand.png"); 
          Sprites.RedBallMoving = loadImg("res/svg/RedBallMove.svg","res/png/RedBallMove.png"); 
          Sprites.RedBallStanding = loadImg("res/svg/RedBallStand.svg","res/png/RedBallStand.png"); 
//        
//        Sprites.blueTile = loadImg("/blueTile.png");
//        Sprites.pinkTile = loadImg("/pinkTile.png");
//        Sprites.yellowTile = loadImg("/yellowTile.png");
//        Sprites.life = loadImg("/life.png");
//        Sprites.blueBackground = loadImg("/blueBackground.png"); 
//        Sprites.brownBackground = loadImg("/brownBackground.png"); 
//        Sprites.qbertFrontMoving = loadImg("/QbertFrontMoving.png"); 
//        Sprites.qbertFrontStanding = loadImg("/QbertFrontStanding.png"); 
//        Sprites.RedBallMoving = loadImg("/redBallMoving.png"); 
//        Sprites.RedBallStanding = loadImg("/redBallStanding.png"); 
        
        Dimensions.screenHeight = d.height;
        Dimensions.screenWidth = d.width;
        Dimensions.windowHeight = Dimensions.screenHeight*3/4;
        Dimensions.windowWidth = Dimensions.screenWidth*3/4;
        Dimensions.deathHeight = Dimensions.windowHeight+200;
        Dimensions.spawningHeight = -100;
        Dimensions.spawningPointLeft = new Position2D((Dimensions.windowWidth/2)-Sprites.blueTile.getWidth(),-500);
        Dimensions.spawningPointRight = new Position2D((Dimensions.windowWidth/2),-500);
        Dimensions.spawningQBert = new Position2D((Dimensions.windowWidth/2)-Sprites.blueTile.getWidth()/2,-500);
        Dimensions.backgroundHeight = Sprites.blueBackground.getHeight();
        Dimensions.backgroundWidth = Sprites.blueBackground.getWidth();
        Dimensions.backgroundX = (Dimensions.windowWidth-Dimensions.backgroundWidth)/2;
        Dimensions.backgroundY = (Dimensions.windowHeight-Dimensions.backgroundHeight)/2;
        Dimensions.cubeHeight = Sprites.blueBackground.getHeight()/7;
        Dimensions.tileHeight = Sprites.blueTile.getHeight();
        Dimensions.tileWidth = Sprites.blueTile.getWidth();
        Dimensions.tileWidth = Sprites.blueTile.getWidth();
        
        gameLevel = new Level();
    }
    
    public Level getLevel() {
        return gameLevel;
    }
    
    public void update( float elapsed) {
        gameLevel.update(elapsed);
    }
    
    private BufferedImage loadImg(String pathIn,String pathOut) throws Exception{
        svg2png(pathIn,pathOut);
        BufferedImage res = null;
        //System.out.println("/png/"+pathOut.split("/")[2]);
        final URL spriteUrl = this.getClass().getResource("/png/"+pathOut.split("/")[2]);
        try {
            res = ImageIO.read(spriteUrl);
            System.out.println("new H " + res.getHeight() + " W " + res.getWidth());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    
//    private void svg2png(String pathIn, String pathOut) throws Exception {
//        //Step -1: We read the input SVG document into Transcoder Input
//        //We use Java NIO for this purpose
//        String svg_URI_input = Paths.get(pathIn).toUri().toURL().toString();
//        TranscoderInput input_svg_image = new TranscoderInput(svg_URI_input);        
//        //Step-2: Define OutputStream to PNG Image and attach to TranscoderOutput
//        OutputStream png_ostream = new FileOutputStream(pathOut);
//        TranscoderOutput output_png_image = new TranscoderOutput(png_ostream);              
//        // Step-3: Create PNGTranscoder and define hints if required
//        PNGTranscoder my_converter = new PNGTranscoder();        
//        // Step-4: Convert and Write output
//        my_converter.transcode(input_svg_image, output_png_image);
//        // Step 5- close / flush Output Stream
//        png_ostream.flush();
//        png_ostream.close();
//        
//    }
    
    private static void svg2png(String svg, String png) throws Exception {
        String svgURIInput = Paths.get(svg).toUri().toURL().toString();
        TranscoderInput inputSvgImage = new TranscoderInput(svgURIInput);
        OutputStream pngOstream = new FileOutputStream(png);
        TranscoderOutput outputPngImage = new TranscoderOutput(pngOstream);
        PNGTranscoder myConverter = new PNGTranscoder();
        
        
        final SAXBuilder builder = new SAXBuilder();
        final Document document = builder.build(svg);

        final Element root = document.getRootElement();
        float width = Float.valueOf(root.getAttributeValue("width"));
        float height = Float.valueOf(root.getAttributeValue("height"));
        System.out.println("old H " + height + " W " + width);
        
        
        myConverter.addTranscodingHint(PNGTranscoder.KEY_WIDTH, new Float(2*width));
        myConverter.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, new Float(2*height));
        myConverter.addTranscodingHint(PNGTranscoder.KEY_AOI, new Rectangle( 0, 0, Math.round(width), Math.round(height)));

        myConverter.transcode(inputSvgImage, outputPngImage);
        pngOstream.flush();
        pngOstream.close();
    }

     
}
