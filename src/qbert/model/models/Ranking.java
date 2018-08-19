package qbert.model.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import qbert.controller.Controller;
import qbert.controller.GameStatus;
import qbert.model.components.graphics.Renderable;

/**
 * The implementation of {@link Model} for application menu scene logic.
 */
public class Ranking implements Model {

    private final GUILogic guiBody;
    private final List<GUILogic> guiList;
    private final Controller controller;
    private static final int ROWS = 10;

    /**
     * Initialize GUI data and logic.
     * @param controller the game controller.
     */
    public Ranking(final Controller controller) {
        final GUILogic guiTitle;
        final GUILogic guiFoot;
        guiTitle = new GUILogicImpl(TextPosition.TITLE);
        guiTitle.addData("TOP SCORES:");
        this.guiBody = new GUILogicImpl(TextPosition.CENTER);

        guiFoot = new GUILogicImpl(TextPosition.FOOT);
        guiFoot.addData("Press enter to exit");

        this.guiList = new ArrayList<>();
        this.guiList.add(guiTitle);
        this.guiList.add(this.guiBody);
        this.guiList.add(guiFoot);

        this.controller = controller;
    }

    @Override
    public final void initialize() {
        final Map<String, Integer> rank;
        this.guiBody.removeAllData();
        rank = controller.getRank();
        rank.entrySet().stream().limit(ROWS).forEach(k -> {
            this.guiBody.addData("Player: " + k.getKey() + " Point: " + k.getValue());
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
