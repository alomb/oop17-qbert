package qbert.model.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import qbert.controller.Controller;
import qbert.controller.GameStatus;
import qbert.model.characters.Player;
import qbert.model.characters.Qbert;
import qbert.model.characters.states.LandState;
import qbert.model.characters.states.MoveState;
import qbert.model.utilities.Dimensions;
import qbert.model.utilities.Position2D;
import qbert.model.utilities.Sprites;
import qbert.view.Renderable;
import qbert.view.characters.PlayerGC;
import qbert.view.characters.PlayerGCImpl;

/**
 * The implementation of {@link Model} for application introductive scene logic.
 */
public class Introduction implements Model {

    private int instructionsIndex;
    private static final int INSTRUCTIONSTEP = 4;
    private int steps;
    private static final int MAXSTEP = 4;

    private final Player qbert;
    private static final float SPEED = 0.35f;
    private static final Position2D QBERTPOSITION = 
            new Position2D(Math.round(Dimensions.getWindowWidth() / 3f), Math.round(Dimensions.getWindowHeight() / 2.75f));

    private final GUILogic guiBody;

    private final List<GUILogic> guiList;
    private final Controller controller;

    /**
     * Initialize GUI data and logic.
     * @param controller the game controller.
     */
    public Introduction(final Controller controller) {
        final PlayerGC graphics = new PlayerGCImpl(Sprites.qbertFrontStanding, Sprites.qbertFrontMoving, Sprites.qbertBackStanding, Sprites.qbertBackMoving, 
                Sprites.qbertDead, Sprites.qbertOnDisk, new Position2D(new Position2D(Introduction.QBERTPOSITION)));

        this.qbert = new Qbert(Dimensions.getSpawningLogQBert(), SPEED, graphics);

        final GUILogic guiTitle;
        final GUILogic guiFoot;

        guiTitle = new GUILogicImpl(TextPosition.TITLE);
        guiTitle.addData("Q*bert");

        this.guiBody = new GUILogicImpl(TextPosition.RIGHTSIDE);
        this.guiBody.addData("JUMP ON SQUARES TO");
        this.guiBody.addData("CHANGE THEM TO");
        this.guiBody.addData("THE TARGET COLOR");
        this.guiBody.addData("");
        this.guiBody.addData("STAY ON PLAYFIELD!");

        this.guiBody.addData("JUMPING OFF RESULTS");
        this.guiBody.addData("IN A FATAL PLUMMET");
        this.guiBody.addData("");
        this.guiBody.addData("AVOID ALL OBJECTS");
        this.guiBody.addData("AND CREATURES THAT");
        this.guiBody.addData("ARE NOT GREEN");
        this.guiBody.addData("");
        this.guiBody.addData("JUMP ON SPINNING DISKS");
        this.guiBody.addData("TO LURE SNAKE");
        this.guiBody.addData("TO HIS DEATH");
        this.guiBody.addData("");

        guiFoot = new GUILogicImpl(TextPosition.FOOT);
        guiFoot.addData("Press Enter to continue...");

        this.guiList = new ArrayList<>();
        this.guiList.add(guiTitle);
        this.guiList.add(this.guiBody);
        this.guiList.add(guiFoot);

        this.controller = controller;
    }

    @Override
    public final void initialize() {
        this.instructionsIndex = Introduction.INSTRUCTIONSTEP - 1;
        this.steps = 1;

        this.guiBody.selectSet(IntStream.range(this.instructionsIndex, this.guiBody.getData().size()).mapToObj(i -> i).collect(Collectors.toSet()));

        this.qbert.setCurrentPosition(new Position2D(new Position2D(Dimensions.getSpawningLogQBert())));
        this.qbert.getGraphicComponent().setPosition(new Position2D(Introduction.QBERTPOSITION));
        this.qbert.setCurrentState(this.qbert.getStandingState());
    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void moveUp() {

    }

    @Override
    public final void confirm() {
        if (!this.qbert.isMoving()) {
            if (this.steps < Introduction.MAXSTEP) {
                this.instructionsIndex += Introduction.INSTRUCTIONSTEP;
                this.guiBody.deselectSet(IntStream.rangeClosed(0, instructionsIndex).mapToObj(i -> i).collect(Collectors.toSet()));
            }
            this.steps++;

            this.qbert.setCurrentState(new MoveState.DownRight(this.qbert));
        }
    }

    @Override
    public final void update(final float elapsed) {
        this.qbert.update(elapsed);

        if (this.qbert.getCurrentState() instanceof LandState) {
            this.qbert.setCurrentState(this.qbert.getStandingState());
        }

        if (this.hasFinished()) {
            this.controller.changeScene(GameStatus.MENU); 
        }
    }

    @Override
    public final List<GUILogic> getGUI() {
        return Collections.unmodifiableList(guiList);
    }

    @Override
    public final List<Renderable> getRenderables() {
        return Stream.of(this.qbert).collect(Collectors.toList());
    }

    @Override
    public final boolean hasFinished() {
        return this.steps > Introduction.MAXSTEP;
    }
}
