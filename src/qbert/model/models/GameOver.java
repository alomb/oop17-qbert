package qbert.model.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import qbert.controller.Controller;
import qbert.controller.GameStatus;
import qbert.model.components.graphics.Renderable;

/**
 * The implementation of {@link Model} for application {@link SceneGameOver} scene logic.
 */
public class GameOver implements Model {

    private int index;
//    private static final int MAXVALUE = 28;
//    private static final int MINVALUE = 0;
//    private static final int EXIT = 27;
//    private static final int CONFIRM = 28;
    private static final int MAXVALUE = 9;
    private static final int MINVALUE = 0;
    private static final int EXIT = 8;
    private static final int CONFIRM = 9;

    private final GUILogic guiLeft;
    private final GUILogic guiCenter;
    private final GUILogic guiRight;
    private final GUILogic guiFoot;
    private final List<GUILogic> guiList;

    private final Controller controller;

    private final String footBaseString;

    /**
     * Initialize GUI data and logic.
     * @param controller the game controller.
     */
    public GameOver(final Controller controller) {
        final GUILogic guiTitle;
        guiTitle = new GUILogicImpl(TextPosition.TITLE);
        guiTitle.addData("GAMEOVER");

        this.guiLeft = new GUILogicImpl(TextPosition.LEFTSIDE);
        this.guiLeft.addData("A");
        this.guiLeft.addData("B");
        this.guiLeft.addData("C");
        this.guiLeft.addData("D");
        this.guiLeft.addData("E");
        this.guiLeft.addData("F");
        this.guiLeft.addData("G");
        this.guiLeft.addData("H");
        this.guiLeft.addData("EXIT");
        this.guiLeft.addData("CONFIRM");
        //this.guiLeft.addData("I");
        //this.guiLeft.addData("J");
        this.guiCenter = new GUILogicImpl(TextPosition.CENTER);
        this.guiCenter.addData("K");
        this.guiCenter.addData("L");
        this.guiCenter.addData("M");
        this.guiCenter.addData("N");
        this.guiCenter.addData("O");
        this.guiCenter.addData("P");
        this.guiCenter.addData("Q");
        this.guiCenter.addData("R");
        this.guiCenter.addData("S");
        this.guiCenter.addData("T");
        this.guiRight = new GUILogicImpl(TextPosition.RIGHTSIDE);
        this.guiRight.addData("U");
        this.guiRight.addData("V");
        this.guiRight.addData("W");
        this.guiRight.addData("X");
        this.guiRight.addData("Y");
        this.guiRight.addData("Z");
        //this.guiRight.addData("_");
        //this.guiRight.addData("@");
        //this.guiRight.addData("EXIT");
        //this.guiRight.addData("CONFIRM");

        this.footBaseString = "Your name is :";

        this.guiFoot = new GUILogicImpl(TextPosition.FOOT);
        this.guiFoot.addData(this.footBaseString);

        this.guiList = new ArrayList<>();
        this.guiList.add(guiTitle);
        this.guiList.add(this.guiLeft);
        this.guiList.add(this.guiCenter);
        this.guiList.add(this.guiRight);
        this.guiList.add(this.guiFoot);

        this.controller = controller;
    }

    @Override
    public final void initialize() {
        this.controller.resetNameBuilder();
        this.index = 0;
        this.guiLeft.deselectAll();
        this.guiLeft.selectSet(IntStream.range(0, 1).mapToObj(i -> i).collect(Collectors.toSet()));
        this.controller.addScoreBuilder();
    }

    @Override
    public final void moveDown() {
        if (this.index < GameOver.MAXVALUE) {
            this.guiLeft.deselectAll();
            this.index++;
            this.guiLeft.selectSet(IntStream.range(this.index, this.index + 1).mapToObj(i -> i).collect(Collectors.toSet()));
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
        if (this.index > GameOver.MINVALUE) {
            this.guiLeft.deselectAll();
            this.index--;
            this.guiLeft.selectSet(IntStream.range(this.index, this.index + 1).mapToObj(i -> i).collect(Collectors.toSet()));
        }
    }

    @Override
    public final void confirm() {
        if (this.index >= 0 && this.index < 8) {
            this.controller.addCharacterNameBuilder(this.index);
        } else if (this.index == GameOver.EXIT) { 
            this.controller.changeScene(GameStatus.MENU);
        } else if (this.index == GameOver.CONFIRM) {
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
