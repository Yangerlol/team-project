package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл", "РПГ");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre("РПГ");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionIfGameNotInstalled() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл", "РПГ");
        Player player = new Player("Petya");

        // Проверяем, что метод выкидывает исключение, если играть в неустановленную игру
        assertThrows(RuntimeException.class, () -> {
            player.play(game, 3);
        });
    }

    @Test
    public void shouldNotResetTimeAfterReinstall() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл", "РПГ");
        Player player = new Player("Petya");

        player.installGame(game);
        player.play(game, 5);
        player.installGame(game); // Повторная установка

        // Если время сбросилось до 0, значит есть баг
        assertEquals(5, player.sumGenre("РПГ"));
    }

    @Test
    public void shouldReturnMostPlayedGameByGenre() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Баттл 1", "РПГ");
        Game game2 = store.publishGame("Баттл 2", "РПГ");
        Game game3 = store.publishGame("Гонки", "Аркада");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 3);
        player.play(game2, 10);
        player.play(game3, 5);

        assertEquals(game2, player.mostPlayerByGenre("РПГ"));
    }
}