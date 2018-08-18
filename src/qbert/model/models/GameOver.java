package qbert.model.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import qbert.controller.Controller;
import qbert.controller.GameStatus;
import qbert.model.components.graphics.Renderable;
import qbert.model.models.RankingBuilder.Builder;

/**
 * The implementation of {@link Model} for application {@link SceneGameOver} scene logic.
 */
public class GameOver implements Model {

    private Builder ranking = new RankingBuilder.Builder();
    private int index;
    private int indexC;
//    private static final int MAXVALUE = 28;
//    private static final int MINVALUE = 0;
//    private static final int EXIT = 27;
//    private static final int CONFIRM = 28;
    private static final int MAXVALUEFIRSTCOLUMN = 9;
    private static final int MAXVALUEOTHERCOLUMN = 6;
    private static final int MINVALUE = 0;
    private static final int EXIT = 7;
    private static final int CONFIRM = 8;
    private static final int COLUMN = 3;

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
        this.guiLeft.addData("EXIT");
        this.guiLeft.addData("CONFIRM");
        this.guiCenter = new GUILogicImpl(TextPosition.CENTER);
        this.guiCenter.addData("H");
        this.guiCenter.addData("I");
        this.guiCenter.addData("L");
        this.guiCenter.addData("M");
        this.guiCenter.addData("N");
        this.guiCenter.addData("O");
        this.guiCenter.addData("P");
        this.guiRight = new GUILogicImpl(TextPosition.RIGHTSIDE);
        this.guiRight.addData("Q");
        this.guiRight.addData("R");
        this.guiRight.addData("S");
        this.guiRight.addData("T");
        this.guiRight.addData("U");
        this.guiRight.addData("V");
        this.guiRight.addData("Z");

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
    private void deselectAll() {
        this.guiLeft.deselectAll();
        this.guiCenter.deselectAll();
        this.guiRight.deselectAll();
    }
    private void color() {
        if (this.indexC == 0) {
            this.guiLeft.selectSet(IntStream.range(this.index, this.index + 1).mapToObj(i -> i).collect(Collectors.toSet()));
        } else if (this.indexC == 1 && this.index < 7) { 
            this.guiCenter.selectSet(IntStream.range(this.index, this.index + 1).mapToObj(i -> i).collect(Collectors.toSet()));
        } else if (this.indexC == 2 && this.index < 7) { 
            this.guiRight.selectSet(IntStream.range(this.index, this.index + 1).mapToObj(i -> i).collect(Collectors.toSet()));
        }
    }

    @Override
    public final void initialize() {
        this.ranking.reset();
        this.index = 0;
        this.indexC = 0;
        this.deselectAll();
        this.color();
        this.ranking.addScore(this.controller.getScore());
    }

    @Override
    public final void moveDown() {
        if ((this.indexC >= 1 && this.index < GameOver.MAXVALUEOTHERCOLUMN) || (this.indexC == 0 && this.index < GameOver.MAXVALUEFIRSTCOLUMN)) {
          this.deselectAll();
          this.index++;
          this.color();
        }
    }

    @Override
    public void moveLeft() {
        if (this.indexC > 0) {
            this.deselectAll();
            this.indexC--;
            this.color();
        }
    }

    @Override
    public void moveRight() {
        if (this.indexC < this.COLUMN - 1) {
            this.deselectAll();
            this.indexC++;
            this.color();
        }
    }

    @Override
    public final void moveUp() {
        if (this.index > GameOver.MINVALUE) {
            this.deselectAll();
            this.index--;
            this.color();
        }
    }

    @Override
    public final void confirm() {

        if (this.index >= 0 && this.index < 7 && this.indexC <= 3) {
            this.ranking.addChar(this.indexC, this.index);
            this.guiFoot.removeAllData();
            this.guiFoot.addData("Your name is : " + this.ranking.getName());
        } else if (this.index == GameOver.EXIT) { 
            this.controller.changeScene(GameStatus.MENU);
        } else if (this.index == GameOver.CONFIRM) {
            this.controller.addRank(this.ranking.build().toString());
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
