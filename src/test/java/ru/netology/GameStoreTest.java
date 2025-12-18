package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл", "РПГ");

        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldNotAddDuplicateGame() {
        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл", "РПГ");
        // Пытаемся добавить ту же самую игру через метод addGame
        store.addGame(game1);

        // В списке должна быть только одна игра.
        // Если тест упадет (будет 2), значит есть баг с дубликатами.
        // Для этого теста нужно добавить метод в GameStore для получения размера списка или проверки уникальности.
    }

    @Test
    public void shouldSumPlayedTime() {
        GameStore store = new GameStore();
        store.addPlayTime("Ivan", 3);
        store.addPlayTime("Ivan", 5); // Суммируем время того же игрока

        // Ожидаем 8, но если в коде баг (перезапись), будет 5.
        assertEquals(8, store.getSumPlayedTime());
    }

    @Test
    public void shouldFindMostPlayer() {
        GameStore store = new GameStore();
        store.addPlayTime("Ivan", 2);
        store.addPlayTime("Olya", 5);
        store.addPlayTime("Dima", 1);

        assertEquals("Olya", store.getMostPlayer());
    }

    @Test
    public void shouldReturnNullIfNoPlayers() {
        GameStore store = new GameStore();
        assertNull(store.getMostPlayer());
    }
}