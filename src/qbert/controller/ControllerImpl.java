package qbert.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.jdom2.JDOMException;

import qbert.controller.input.Command;
import qbert.model.LevelSettings;
import qbert.model.models.GUILogic;
import qbert.model.components.graphics.Renderable;
import qbert.view.View;
import qbert.view.ViewImpl;

/**
 * The implementation of {@link Controller}.
 */
public class ControllerImpl implements Controller {

    private final String urlFile = "ranking.txt";
    private LevelConfigurationReader lcr;
    private final GameEngine gameEngine;
    private final GameStatusManager statusManager;

    private final BlockingQueue<Integer> gamePoint = new ArrayBlockingQueue<>(1);

    private final View view;

    /**
     * @param firstGameStatus the first application's {@link GameStatus}
     */
    public ControllerImpl(final GameStatus firstGameStatus) {
        this.lcr = new LevelConfigurationReaderImpl();
        this.statusManager = new GameStatusManagerImpl(firstGameStatus, this);

        this.view = new ViewImpl(this);
        this.gameEngine = new GameEngine(this.view);
    }

    @Override
    public final void setupGameEngine() {
        this.changeScene(this.statusManager.getCurrentStatus());
        this.gameEngine.mainLoop();
    }

    @Override
    public final LevelSettings getLevelSettings(final int level, final int round) {
        this.lcr = new LevelConfigurationReaderImpl();
        try {
            lcr.readLevelConfiguration(level, round);
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        return lcr.getLevelSettings();
    }

    @Override
    public final void notifyCommand(final Command command) {
        this.gameEngine.notifyCommand(command);
    }

    @Override
    public final void changeScene(final GameStatus newGameStatus) {
        this.statusManager.setCurrentStatus(newGameStatus);
        this.view.setScene(this.statusManager.getCurrentStatus());
        this.gameEngine.setup(this.statusManager.getModel());
    }

    @Override
    public final List<GUILogic> getGUI() {
        return this.statusManager.getModel().getGUI();
    }

    @Override
    public final List<Renderable> getRenderables() {
        return this.statusManager.getModel().getRenderables();
    }

    @Override
    public final void setScore(final Integer value) {
        this.gamePoint.clear();
        this.gamePoint.add(value);
    }

    @Override
    public final Integer getScore() {
        return this.gamePoint.poll();
    }
    
    public Map<String,Integer> getRank() {
        Map<String,Integer> rank = new TreeMap<String,Integer>();
        //Read file
        try (BufferedReader br = new BufferedReader(new FileReader(urlFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                rank.put(line.split("\\?")[0],Integer.parseInt(line.split("\\?")[1]));
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //convert map to a List
        List<Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(rank.entrySet());

        //sorting the list with a comparator
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return (o2.getValue()).compareTo(o1.getValue());
                }
        });

        //convert sortedMap back to Map
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list) {
                sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
    }
    
    
    public void addRank(String s, Integer i) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(urlFile, true));
            output.append("\r\n"+s+'#'+dateFormat.format(date)+'?'+i);
            output.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public final void terminate() {
        this.view.closeWindow();
        this.gameEngine.stop();
    }

    @Override
    public final Clip uploadClip(final SoundEffectFile soundEffect) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            final AudioInputStream inputStream = AudioSystem
                    .getAudioInputStream(new File(getClass().getResource("/sounds/").getFile() + soundEffect.getFile()));
            clip.open(inputStream);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return clip;
    }

    @Override
    public void emptyClipQueue(Queue<Clip> queue) {
        // TODO Auto-generated method stub
        
    }

}
