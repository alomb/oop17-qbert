package qbert.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

    private final String urlFile = "res/ranking.txt";
    private LevelConfigurationReader lcr;
    private final GameEngine gameEngine;
    private final GameStatusManager statusManager;

    private final BlockingQueue<Integer> gamePoint = new ArrayBlockingQueue<>(1);

    private final View view;
    private boolean abort;

    /**
     * @param firstGameStatus the first application's {@link GameStatus}
     */
    public ControllerImpl(final GameStatus firstGameStatus) {
        this.abort = false;
        this.lcr = new LevelConfigurationReaderImpl();

        this.statusManager = new GameStatusManagerImpl(firstGameStatus, this);
        this.view = new ViewImpl(this);
        this.gameEngine = new GameEngine(this.view);

        if (this.abort) {
            this.terminate();
        }
    }

    @Override
    public final void setupGameEngine() {
        if (!this.abort) {
            this.changeScene(this.statusManager.getCurrentStatus());
            this.gameEngine.mainLoop();
        }
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

    @Override
    public final List<Map<String, Integer>> getRank() {
        List<Map<String, Integer>> rank = new ArrayList<Map<String, Integer>>();
        //Read file
        try (BufferedReader br = new BufferedReader(new FileReader(urlFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                Map<String, Integer> mapTmp = new TreeMap<String, Integer>();
                mapTmp.put(line.split(":")[0], Integer.parseInt(line.split(":")[1]));
                rank.add(mapTmp);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Order list 
        Collections.sort(rank, new Comparator<Map<String, Integer>>() {
            @Override
            public int compare(Map<String, Integer> map1, Map<String, Integer> map2) {
                Integer val1 = 0;
                Integer val2 = 0;
                for (Map.Entry<String, Integer> entry : map1.entrySet()) {
                    val1 = entry.getValue();
                }
                for (Map.Entry<String, Integer> entry : map2.entrySet()) {
                    val2 = entry.getValue();
                }
                return val2.compareTo(val1);
            }
        });

        return rank;
    }

    @Override
    public final void addRank(final String s, final Integer i) {
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(urlFile, true));
            output.append("\r\n" + s + ":" + i);
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
    public final void abort() {
        this.abort = true;
    }

    @Override
    public final Clip uploadClip(final SoundEffectFile soundEffect) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();

            final AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    new BufferedInputStream(ControllerImpl.class.getResourceAsStream("/sounds/" + soundEffect.getFile().toString())));
            clip.open(inputStream);
            inputStream.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return clip;
    }

    @Override
    public final void emptyClipQueue(final Queue<Clip> queue) {
        this.view.play(queue);
    }
}
