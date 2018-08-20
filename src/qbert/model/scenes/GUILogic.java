package qbert.model.scenes;

import java.util.List;
import java.util.Set;

/**
 * The model abstraction of a GUI.
 */
public interface GUILogic {

    /**
     * @return the list of text lines of the GUI
     */
    List<String> getData();

    /**
     * @param line the new line to add in the GUI
     */
    void addData(String line);

    /**
     * @param line the text line to remove
     */
    void removeData(String line);

    /**
     * Clean the GUI text.
     */
    void removeAllData();

    /**
     * @return the set of lines' indexes that are selected 
     */
    Set<Integer> getSelected();

    /**
     * Clean current selected lines.
     */
    void deselectAll();

    /**
     * @param set the {@link Set} of lines (indexes) to be selected
     */
    void selectSet(Set<Integer> set);

    /**
     * @param set the {@link Set} of lines (indexes) to be deselected
     */
    void deselectSet(Set<Integer> set);

    /**
     * @return the {@link TextPosition} of the GUI
     */
    TextPosition getPosition();
}
