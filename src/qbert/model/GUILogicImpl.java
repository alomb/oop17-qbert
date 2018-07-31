package qbert.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GUILogicImpl {

    private final List<String> data;
    private final Set<Integer> selected;

    private boolean centered;
    private final TextSize size;
    private final TextPosition position;

    public GUILogicImpl(final TextSize size, final TextPosition position) {
        this.data = new ArrayList<>();
        this.selected = new HashSet<>();
        this.size = size;
        this.position = position;
    }

    public List<String> getData() {
        return Collections.unmodifiableList(this.data);
    }

    public void addData(final String line) {
        this.data.add(line);
    }

    public void removeData(final String line) {
        this.data.remove(line);
    }

    public Set<Integer> getSelected() {
        return this.selected;
    }

    public boolean isCentered() {
        return this.centered;
    }

    public void setCentered(final boolean centered) {
        this.centered = centered;
    }

    public TextSize getSize() {
        return this.size;
    }

    public TextPosition getPosition() {
        return this.position;
    }
}
