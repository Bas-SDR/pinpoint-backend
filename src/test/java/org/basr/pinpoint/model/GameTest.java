package org.basr.pinpoint.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    //Arrange
    private Game game1;

    @BeforeEach
    void setUp() {
        //https://stackoverflow.com/questions/57851708/how-to-set-jpa-attribute-with-accesslevel-none-for-unit-test
        Player player = new Player();
        ReflectionTestUtils.setField(player, "id", 10L);

        Team team = new Team();
        ReflectionTestUtils.setField(team, "id", 20L);

        League league = new League();
        ReflectionTestUtils.setField(league, "id", 30L);

        game1 = new Game();
        game1.setId(1L);
        game1.setDatePlayed(LocalDate.of(2025, 12, 1));
        game1.setPinfall(234);
        game1.setGameNumber(1);
        game1.setCreatedDate(LocalDate.of(2025, 12, 2));
        game1.setPlayer(player);
        game1.setTeam(team);
        game1.setLeague(league);
    }

    @Test
    void shouldReturnCorrectId(){
        // Act
        long result = game1.getId();
        // Assert
        assertEquals(1L, result);
    }

    @Test
    void shouldReturnCorrectGameDate() {
        //Act
        LocalDate result = game1.getDatePlayed();
        //Assert
        assertEquals(LocalDate.of(2025, 12, 1), result);

    }

    @Test
    void shouldReturnCorrectPinfall() {
        // Act
        int result = game1.getPinfall();
        // Assert
        assertEquals(234, result);
    }

    @Test
    void shouldReturnCorrectGameNumber() {
        // Act
        int result = game1.getGameNumber();
        // Assert
        assertEquals(1, result);
    }

    @Test
    void shouldReturnCorrectCreationDate() {
        //Act
        LocalDate result = game1.getCreatedDate();
        //Assert
        assertEquals(LocalDate.of(2025, 12, 2), result);
    }

    @Test
    void shouldReturnCorrectPlayer() {
        //Act
        long result = game1.getPlayer().getId();
        //Assert
        assertEquals(10L, result);
    }

    @Test
    void shouldReturnCorrectTeam() {
        //Act
        long result = game1.getTeam().getId();
        //Assert
        assertEquals(20L, result);
    }

    @Test
    void shouldReturnCorrectLeague() {
        //Act
        long result = game1.getLeague().getId();
        //Assert
        assertEquals(30L, result);
    }
}