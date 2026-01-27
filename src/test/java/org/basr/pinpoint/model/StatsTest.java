package org.basr.pinpoint.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatsTest {

    //Arrange
    private Stats stats1;
    private Stats stats2;

    @BeforeEach
    void setUp() {
        Player player = new Player();
        ReflectionTestUtils.setField(player, "id", 10L);

        stats1 = new Stats();
        stats1.setId(1L);
        stats1.setGamesPlayed(20);
        stats1.setHighestGame(280);
        stats1.setHighestSeries(633);
        stats1.setTotalPinfall(12345);
        stats1.setPerfectGames(0);
        stats1.setPlayer(player);

        stats2 = new Stats();
        stats2.setId(1L);
        stats2.setGamesPlayed(0);
        stats2.setHighestGame(300);
        stats2.setHighestSeries(900);
        stats2.setTotalPinfall(12345);
        stats2.setPerfectGames(3);
        stats2.setPlayer(player);

    }

    @Test
    void shouldReturnCorrectId() {
        //Act
        long result = stats1.getId();
        //Assert
        assertEquals(1L, result);
    }

    @Test
    void shouldReturnCorrectGamesPlayed() {
        //Act
        int result = stats1.getGamesPlayed();
        //Assert
        assertEquals(20, result);
    }

    @Test
    void shouldReturnCorrectHighestGame() {
        //Act
        int result = stats1.getHighestGame();
        //Assert
        assertEquals(280, result);
    }

    @Test
    void shouldReturnCorrectHighestSeries() {
        //Act
        int result = stats1.getHighestSeries();
        //Assert
        assertEquals(633, result);
    }

    @Test
    void shouldReturnCorrectTotalPinfall() {
        //Act
        int result = stats1.getTotalPinfall();
        //Assert
        assertEquals(12345, result);
    }

    @Test
    void shouldReturnCorrectPerfectGames() {
        //Act
        int result = stats1.getPerfectGames();
        //Assert
        assertEquals(0, result);
    }

    @Test
    void shouldReturnCorrectPlayer() {
        //Act
        Player player = stats1.getPlayer();
        //Assert
        assertEquals(10L, player.getId());
    }

    @Test
    void shouldReturnCorrectAverageScore() {
        //Act
        double result = stats1.getAverageScore();
        //Assert
        assertEquals(617.25, result);
    }

    @Test
    void shouldReturnNoAverageScore() {
        //Act
        double result = stats2.getAverageScore();
        //Assert
        assertEquals(0, result);
    }
}