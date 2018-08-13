package qbert.model.characters;

/**
 * The enumeration that manages the different {@link Character} to be spawned in the map.
 */
public enum CharactersList {

    /**
     * 
     */
    COILY("Coily"),

    /**
     * 
     */
    RED_BALL("RedBall"),

    /**
     * 
     */
    GREEN_BALL("GreenBall"),

    /**
     * 
     */
    UGG("Ugg"),

    /**
     * 
     */
    WRONGWAY("Wrongway"),

    /**
     * 
     */
    SAM_AND_SLICK("SamAndSlick");

    private final String characterName;

    /**
     * @param characterName the name of the {@link Character}
     */
    CharactersList(final String characterName) {
        this.characterName = characterName;
    }

    /**
     * @return the name of the specific {@link Character}
     */
    public String getName() {
        return this.characterName;
    }

    /**
     * 
     * @param name the value of the enumeration constant to be returned
     * @return the enumeration constant corresponding to its value
     */
    public static CharactersList getEnumCostantByValue(final String name) {
        for (final CharactersList c : CharactersList.values()) {
            if (name.equals(c.characterName)) {
                return c;
            }
        }
        return null;
    }

}
