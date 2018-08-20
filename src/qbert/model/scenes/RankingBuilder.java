package qbert.model.scenes;

import java.text.DateFormat;
import java.util.Date;
 
/**
 * The implementation of {@link Model} for build ranking data.
 */
public class RankingBuilder {
 
    private final Integer score;
    private final String name;
    private final Date date;
    /**
     * Construct.
     * @param builder for initialize attribute of object
     */
    public RankingBuilder(final Builder builder) {
        score = builder.scoreB;
        name = builder.nameB;
        date = builder.dateB;
    }

    @Override
    public final String toString() {
        final DateFormat dateFormat = DateFormat.getDateTimeInstance();
        return this.name + '#' + dateFormat.format(this.date
                ) + '?' + this.score;
    }

    /**
     * Get only name of object.
     * @return the name object
     */
    public String getName() {
        return this.name;
    }
    /**
     * The implementation of builder pattern.
     */
    public static class Builder {
        private static final Integer NCHARACTER = 3;
        private Integer scoreB;
        private String nameB = "";
        private Date dateB = new Date();
        private final String[][] alphabet = {{"A", "B", "C", "D", "E", "F", "G"}, 
                {"H", "I", "L", "M", "N", "O", "P"}, 
                {"Q", "R", "S", "T", "U", "V", "Z"}};

        /**
         * Add score to the object.
         * @param i is score
         * @return the entire object
         */
        public Builder addScore(final Integer i) {
            scoreB = i;
            return this;
        }

        /**
         * Add char to name of player.
         * @param row is index in alphabetic bidimensional array(row)
         * @param column is index in alphabetic bidimensional array(column)
         * @return the entire object
         */
        public Builder addChar(final Integer row, final Integer column) {
            if (nameB.equals(" ")) {
                nameB = alphabet[row][column];
            } else if (nameB.length() <= NCHARACTER) {
                nameB += alphabet[row][column];
            }
            return this;
        }
        /**
         * Get name during build.
         * @return nameB 
         */
        public String getName() {
            return nameB;
        }

        /**
         * Reset all data.
         * @return the entire object
         */
        public Builder reset() {
            nameB = " ";
            dateB = new Date();
            return this;
        }
        /**
         * Build the object.
         * @return the entire object
         */
        public RankingBuilder build() {
            return new RankingBuilder(this);
        }
    }
}
