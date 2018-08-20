package test;

import org.junit.Test;

import qbert.controller.ControllerImpl;
import qbert.controller.GameStatus;
import qbert.model.scenes.RankingBuilder;
import qbert.model.scenes.RankingBuilder.Builder;
import qbert.view.ViewImpl;

import static org.junit.Assert.assertEquals;

import java.util.Map;

/**
 * A class with some jUnit tests for {@link Ranking}.
 */
public class TestRankingReadWrite {
    private final Builder rank;
    private final ViewImpl testvi = new ViewImpl();
    private final ControllerImpl controller = new ControllerImpl(GameStatus.MENU, testvi);
    /**
     * The constructor create a {@link RankingBuilder}.
     */
    public TestRankingReadWrite() {
        rank = new RankingBuilder.Builder();
    }

    /**
     * A test method for {@link RankingBuilder} and {@link Ranking}.
     */
    @Test
    public void testBuilder() {
        rank.addChar(0, 0);
        rank.addChar(0, 1);
        rank.addChar(0, 2);
        assertEquals("ABC", rank.build().getName());
        rank.reset();
        rank.build();
        assertEquals(" ", rank.build().getName());
        rank.addChar(0, 1);
        rank.addChar(0, 2);
        rank.addChar(0, 3);
        assertEquals("BCD", rank.build().getName());
    }
    /**
     * A test method for {@link RankingBuilder} and {@link Ranking}.
     * Test work only if file "ranking.txt"  is empty
     */
    @Test
    public void testRanking() {
        Map<String, Integer> mapRank;
        rank.addChar(0, 1);
        rank.addChar(0, 2);
        rank.addChar(0, 3);
        rank.addScore(10);
        controller.addRank(rank.build());
        mapRank = controller.getRank();
        mapRank.entrySet().stream().limit(1).forEach(k -> {
            assertEquals("BCD", k.getKey().split("#")[0]);
            assertEquals(10, (int) k.getValue());
        });
        rank.reset();
        rank.addChar(0, 0);
        rank.addChar(0, 1);
        rank.addChar(0, 2);
        rank.addScore(100);
        controller.addRank(rank.build());
        mapRank = controller.getRank();
        mapRank.entrySet().stream().limit(1).forEach(k -> {
            assertEquals("ABC", k.getKey().split("#")[0]);
            assertEquals(100, (int) k.getValue());
        });
    }
}
