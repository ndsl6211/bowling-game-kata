package tw.idv.mashu.bowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game;

    @Before
    public void Setup() {
        game = new Game(2);
    }

    @Test
    public void test_no_strike_and_spare() {
        givenRolledPin(3);
        givenRolledPin(4);
        givenRolledPin(1);
        givenRolledPin(5);

        int result = (3 + 4) + (1 + 5);
        assertEquals(result, game.score());
    }

    @Test
    public void test_spare() {
        givenRolledPin(8);
        givenRolledPin(2);
        givenRolledPin(2);
        givenRolledPin(6);

        int result = (8 + 2 + 2) + (2 + 6);
        assertEquals(result, game.score());
    }

    @Test
    public void test_spare_then_strike() {
        givenRolledPin(3);
        givenRolledPin(7);
        givenRolledPin(10);
        givenRolledPin(1);
        givenRolledPin(2);

        int result = (3 + 7 + 10) + (10 + 1 + 2);
        assertEquals(result, game.score());
    }

    @Test
    public void test_spare_in_last_frame() {
        givenRolledPin(3);
        givenRolledPin(1);
        givenRolledPin(7);
        givenRolledPin(3);
        givenRolledPin(9);

        int result = (3 + 1) + (7 + 3) + 9;
        assertEquals(result, game.score());
    }

    @Test
    public void test_strike_in_last_frame() {
        givenRolledPin(4);
        givenRolledPin(0);
        givenRolledPin(10);
        givenRolledPin(8);
        givenRolledPin(9);

        int result = (4 + 0) + (10 + 8 + 9);
        assertEquals(result, game.score());
    }

    @Test
    public void test_double_strike() {
        givenRolledPin(10);
        givenRolledPin(10);
        givenRolledPin(3);
        givenRolledPin(4);

        int result = (10 + 10 + 3) + (10 + 3 + 4);
        assertEquals(result, game.score());
    }

    @Test
    public void test_all_strike() {
        givenRolledPin(10);
        givenRolledPin(10);
        givenRolledPin(10);
        givenRolledPin(10);

        int result = (10 + 10 + 10) + (10 + 10 + 10);
        assertEquals(result, game.score());
    }

    private void givenRolledPin(int score) {
        game.roll(score);
    }
}
