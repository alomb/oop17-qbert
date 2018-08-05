package qbert.model.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JTextField;

import qbert.controller.Controller;
import qbert.controller.GameStatus;
import qbert.view.Renderable;

/**
 * The implementation of {@link Model} for application menu scene logic.
 */
public class GameOver implements Model {

    private final GUILogic guiBody;
    
    private final List<GUILogic> guiList;
    private final Controller controller;
    
    private Map<String,Integer> rank;
    private String fit="AA";

    /**
     * Initialize GUI data and logic.
     * @param controller the game controller.
     */
    public GameOver(final Controller controller) {
        final GUILogic guiTitle;
        final GUILogic guiFoot;
        
        guiTitle = new GUILogicImpl(TextPosition.TITLE);
        guiTitle.addData("GAMEOVER");

        this.guiBody = new GUILogicImpl(TextPosition.CENTER);
        
        guiFoot = new GUILogicImpl(TextPosition.FOOT);
        guiFoot.addData("Set your name!");

        this.guiList = new ArrayList<>();
        this.guiList.add(guiTitle);
        this.guiList.add(this.guiBody);
        this.guiList.add(guiFoot);

        this.controller = controller;
    }

    @Override
    public final void initialize() {
        this.guiBody.removeAllData();
        this.guiBody.selectSet(IntStream.range(0, 1).mapToObj(i -> i).collect(Collectors.toSet()));
        
        rank = controller.getRank();
        
        rank.forEach((k,v)->{
            this.guiBody.addData("Player: " + k + " Point: " + v);
        });
        
        rank.clear();
    }

    @Override
    public final void moveDown() {
        
    }

    @Override
    public void moveLeft() {
        
    }

    @Override
    public void moveRight() {

    }

    @Override
    public final void moveUp() {
        
    }

    @Override
    public final void confirm() {
        this.controller.addRank(this.fit, this.controller.getScore());
        this.controller.changeScene(GameStatus.MENU);
    }

    @Override
    public final void update(final float elapsed) {
    }

    @Override
    public final List<GUILogic> getGUI() {
        return Collections.unmodifiableList(guiList);
    }

    @Override
    public final List<Renderable> getRenderables() {
        return new ArrayList<Renderable>();
    }

    @Override
    public final boolean hasFinished() {
        return false;
    }
}
