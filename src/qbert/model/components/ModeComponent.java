package qbert.model.components;

import qbert.model.models.Game;

public interface ModeComponent {

    void checkStatus(TimerComponent timer);

    void changeRound(TimerComponent timer);

    void addObserver(final Game gameObserver);

    public void notifyEndLevel();

}
