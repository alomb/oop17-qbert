package qbert.model.models;

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
     * @return true if the GUI lines alignment is centered
     */
    boolean isCentered();

    /**
     * @param centered a boolean used to set the lines alignment centered
     */
    void setCentered(boolean centered);

    /**
     * @return the {@link TextSize} of the GUI
     */
    TextSize getSize();

    /**
     * @return the {@link TextPosition} of the GUI
     */
    TextPosition getPosition();
}
