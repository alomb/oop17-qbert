package qbert.model.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import qbert.controller.Controller;
import qbert.controller.GameStatus;
import qbert.view.Renderable;

/**
 * The implementation of {@link Model} for application menu scene logic.
 */
public class Menu implements Model {

    private static int index;
    private static final int MAXVALUE = 1;
    private static final int MINVALUE = 0;
    private static final int PLAY = 0;
    private static final int EXIT = 1;
    private final GUILogic guiBody;

    private final List<GUILogic> guiList;
    private final Controller controller;

    /**
     * Initialize GUI data and logic.
     * @param controller the game controller.
     */
    public Menu(final Controller controller) {
        final GUILogic guiTitle;
        final GUILogic guiFoot;

        guiTitle = new GUILogicImpl(TextSize.LARGE, TextPosition.TITLE);
        guiTitle.addData("Menu'");
        guiTitle.setCentered(true);

        this.guiBody = new GUILogicImpl(TextSize.MEDIUM, TextPosition.CENTER);
        this.guiBody.addData("PLAY");
        this.guiBody.addData("EXIT");
        this.guiBody.setCentered(true);

        guiFoot = new GUILogicImpl(TextSize.SMALL, TextPosition.FOOT);
        guiFoot.addData("Move with arrow key");
        guiFoot.setCentered(true);

        this.guiList = new ArrayList<>();
        this.guiList.add(guiTitle);
        this.guiList.add(this.guiBody);
        this.guiList.add(guiFoot);

        this.controller = controller;
    }

    @Override
    public final void initialize() {
        this.guiBody.selectSet(IntStream.range(0, 1).mapToObj(i -> i).collect(Collectors.toSet()));
    }

    @Override
    public final void moveDown() {
        if (Menu.index < Menu.MAXVALUE) {
            this.guiBody.deselectAll();
            Menu.index++;
            this.guiBody.selectSet(IntStream.range(Menu.index, Menu.index + 1).mapToObj(i -> i).collect(Collectors.toSet()));
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
        if (Menu.index > Menu.MINVALUE) {
            this.guiBody.deselectAll();
            Menu.index--;
            this.guiBody.selectSet(IntStream.range(Menu.index, Menu.index + 1).mapToObj(i -> i).collect(Collectors.toSet()));
        }
    }

    @Override
    public final void confirm() {
        if (Menu.index == Menu.PLAY) {
            this.controller.changeScene(GameStatus.GAMEPLAY); 
        } else if (Menu.index == Menu.EXIT) {
            System.exit(0);
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
