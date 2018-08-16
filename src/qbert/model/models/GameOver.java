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
import qbert.model.components.graphics.Renderable;

/**
 * The implementation of {@link Model} for application menu scene logic.
 */
public class GameOver implements Model {

    private static int index;
//    private static final int MAXVALUE = 28;
//    private static final int MINVALUE = 0;
//    private static final int EXIT = 27;
//    private static final int CONFIRM = 28;
    private static final int MAXVALUE = 9;
    private static final int MINVALUE = 0;
    private static final int EXIT = 8;
    private static final int CONFIRM = 9;
    
    private final GUILogic guiBody;
    
    private final List<GUILogic> guiList;
    private final Controller controller;
    
    private Map<String,Integer> rank;

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
        this.guiBody.addData("A");
        this.guiBody.addData("B");
        this.guiBody.addData("C");
        this.guiBody.addData("D");
        this.guiBody.addData("E");
        this.guiBody.addData("F");
        this.guiBody.addData("G");
        this.guiBody.addData("H");
        this.guiBody.addData("EXIT");
        this.guiBody.addData("CONFIRM");
        
//        this.guiBody.addData("I");
//        this.guiBody.addData("J");
//        this.guiBody.addData("K");
//        this.guiBody.addData("L");
//        this.guiBody.addData("M");
//        this.guiBody.addData("N");
//        this.guiBody.addData("O");
//        this.guiBody.addData("P");
//        this.guiBody.addData("Q");
//        
//        this.guiBody.addData("R");
//        this.guiBody.addData("S");
//        this.guiBody.addData("T");
//        this.guiBody.addData("U");
//        this.guiBody.addData("V");
//        this.guiBody.addData("W");
//        this.guiBody.addData("X");
//        this.guiBody.addData("Y");
//        this.guiBody.addData("Z");

        
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
        this.guiBody.selectSet(IntStream.range(0, 1).mapToObj(i -> i).collect(Collectors.toSet()));
        this.controller.addScoreBuilder();
        
    }

    @Override
    public final void moveDown() {
        if (GameOver.index < GameOver.MAXVALUE) {
            this.guiBody.deselectAll();
            GameOver.index++;
            this.guiBody.selectSet(IntStream.range(GameOver.index, GameOver.index + 1).mapToObj(i -> i).collect(Collectors.toSet()));
        }
    }

    @Override
    public void moveLeft() {
        
    }

    @Override
    public void moveRight() {

    }

    @Override
    public final void moveUp() {
        if (GameOver.index > GameOver.MINVALUE) {
            this.guiBody.deselectAll();
            GameOver.index--;
            this.guiBody.selectSet(IntStream.range(GameOver.index, GameOver.index + 1).mapToObj(i -> i).collect(Collectors.toSet()));
        }
    }

    @Override
    public final void confirm() {
        if (GameOver.index >= 0 && GameOver.index < 8) {   
            this.controller.addCharacterNameBuilder(GameOver.index);
        }else if (GameOver.index == GameOver.EXIT) {
            this.controller.changeScene(GameStatus.MENU);
        }else if (GameOver.index == GameOver.CONFIRM) {
            this.controller.addRank();
            this.controller.changeScene(GameStatus.MENU);
        }
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
