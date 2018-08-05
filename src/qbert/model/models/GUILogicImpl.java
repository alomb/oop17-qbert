package qbert.model.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The implementation of {@link GUILogic}.
 */
public class GUILogicImpl implements GUILogic {

    private final List<String> data;
    private final Set<Integer> selected;

    private final TextPosition position;

    /**
     * @param position the GUI {@link TextPosition}
     */
    public GUILogicImpl(final TextPosition position) {
        this.data = new ArrayList<>();
        this.selected = new HashSet<>();
        this.position = position;
    }

    @Override
    public final List<String> getData() {
        return Collections.unmodifiableList(this.data);
    }

    @Override
    public final void addData(final String line) {
        this.data.add(line);
    }

    @Override
    public final void removeData(final String line) {
        this.data.remove(line);
    }

    @Override
    public final void removeAllData() {
        this.data.clear();
    }

    @Override
    public final Set<Integer> getSelected() {
        return Collections.unmodifiableSet(this.selected);
    }

    @Override
    public final void deselectAll() {
        this.selected.clear();
    }

    @Override
    public final void selectSet(final Set<Integer> set) {
        this.selected.addAll(set);
    }

    @Override
    public final void deselectSet(final Set<Integer> set) {
        this.selected.removeAll(set);
    }

    @Override
    public final TextPosition getPosition() {
        return this.position;
    }
}
