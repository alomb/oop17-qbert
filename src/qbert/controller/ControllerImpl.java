package qbert.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.jdom2.JDOMException;

import qbert.controller.input.Command;
import qbert.model.LevelSettings;
import qbert.model.models.GUILogic;
import qbert.model.components.graphics.Renderable;
import qbert.view.View;

/**
 * The implementation of {@link Controller}.
 */
public class ControllerImpl implements Controller {

    private static final String USER_MESSAGE = "Application aborted. Please look at log file for more information.";

    private final String urlFile = System.getProperty("user.home") + "/qbert/ranking.txt";
    private LevelConfigurationReader lcr;
    private GameEngine gameEngine;
    private GameStatusManager statusManager;

    private final BlockingQueue<Integer> gamePoint = new ArrayBlockingQueue<>(1);

    private final View view;
    private boolean aborted;

    /**
     * @param firstGameStatus the first application's {@link GameStatus}
     * @param view the application {@link View}
     */
    public ControllerImpl(final GameStatus firstGameStatus, final View view) {
        this.aborted = false;
        this.view = view;

        try {
            this.lcr = new LevelConfigurationReaderImpl();
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
            this.forceQuit(USER_MESSAGE);
        }

        if (!aborted) {
            this.view.initialize(this);
            this.statusManager = new GameStatusManagerImpl(firstGameStatus, this);
            this.gameEngine = new GameEngine(this.view);
        }
    }

    @Override
    public final void setupGameEngine() {
        if (!this.aborted) {
            this.changeScene(this.statusManager.getCurrentStatus());
            this.gameEngine.mainLoop();
        }
    }

    @Override
    public final LevelSettings getLevelSettings(final int level, final int round) {
        try {
            this.lcr = new LevelConfigurationReaderImpl();
            lcr.readLevelConfiguration(level, round);
        } catch (JDOMException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            this.forceQuit(USER_MESSAGE);
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            this.forceQuit(USER_MESSAGE);
        }
        return lcr.getLevelSettings();
    }

    @Override
    public final void notifyCommand(final Command command) {
        this.gameEngine.notifyCommand(command);
    }

    @Override
    public final void changeScene(final GameStatus newGameStatus) {
        if (!this.aborted) {
            this.statusManager.setCurrentStatus(newGameStatus);
            this.view.setScene(this.statusManager.getCurrentStatus());
            this.gameEngine.setup(this.statusManager.getModel());
        }
    }

    @Override
    public final List<GUILogic> getGUI() {
        return this.statusManager != null ? this.statusManager.getModel().getGUI() : new ArrayList<>();
    }

    @Override
    public final List<Renderable> getRenderables() {
        return this.statusManager != null ? this.statusManager.getModel().getRenderables() : new ArrayList<>();
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
    public final Map<String, Integer> getRank() {
        final Map<String, Integer> rank = new TreeMap<String, Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(urlFile))) {
            String line;
            if (!isEmptyFile()) {
                line = br.readLine();
                while (line != null) {
                    rank.put(line.split("\\?")[0], Integer.parseInt(line.split("\\?")[1]));
                    line = br.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            final File file = new File(urlFile);
            //Create the file
            try {
                file.createNewFile();
            } catch (IOException e1) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
                this.forceQuit(USER_MESSAGE);
            }
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            this.forceQuit(USER_MESSAGE);
        }
        //Convert map to a List
        final List<Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(rank.entrySet());

        //Sorting the list with a comparator
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
                public int compare(final Map.Entry<String, Integer> o1, final Map.Entry<String, Integer> o2) {
                        return (o2.getValue()).compareTo(o1.getValue());
                }
        });

        //Convert sortedMap back to Map
        final Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (final Entry<String, Integer> entry : list) {
                sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    @Override
    public final void addRank(final String s) {
        Writer output;
        try {
            output = new BufferedWriter(new FileWriter(urlFile, true));
            if (!isEmptyFile()) {
                output.append("\r\n" + s);
            } else {
                output.append(s);
            }
            output.close();
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            this.forceQuit(USER_MESSAGE);
        }
    }

    @Override
    public final void terminate() {
        if (view != null) {
            this.view.closeWindow();
        }
        if (this.gameEngine != null) {
            this.gameEngine.stop();
        }
    }

    @Override
    public final void forceQuit(final String errorMessage) {
        this.view.showErrorMessageBox(errorMessage);
        this.terminate();
        this.aborted = true;
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
            Logger.getGlobal().log(Level.WARNING, e.getMessage(), e);
        }
        return clip;
    }

    @Override
    public final void emptyClipQueue(final Queue<Clip> queue) {
        this.view.play(queue);
    }

    /**
     * Check if ranking file is empty or not.
     */
    private boolean isEmptyFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(urlFile))) {
            return br.readLine() == null;
        } catch (FileNotFoundException e) {
            final File file = new File(urlFile);
            //Create the file
            try {
                file.createNewFile();
                return true;
            } catch (IOException e1) {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
                this.forceQuit(USER_MESSAGE);
            }
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            this.forceQuit(USER_MESSAGE);
        }
        return false;
    }

}
