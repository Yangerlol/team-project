package ru.netology;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStore {
    private List<Game> games = new ArrayList<>();
    private Map<String, Integer> playedTime = new HashMap<>();

    public Game publishGame(String title, String genre) {
        Game game = new Game(title, genre, this);
        games.add(game);
        return game;
    }

    public boolean containsGame(Game game) {
        for (Game g : games) {
            if (g.equals(game)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Добавление игры в каталог.
     * Если игра уже есть в каталоге, дубликат не добавляется.
     */
    public void addGame(Game game) {
        if (!games.contains(game)) {
            games.add(game);
        }
    }

    /**
     * Регистрирует количество игрового времени.
     * Если игрок уже играл, время суммируется.
     */
    public void addPlayTime(String playerName, int hours) {
        if (playedTime.containsKey(playerName)) {
            // ИСПРАВЛЕНО: Время теперь прибавляется к текущему, а не заменяется
            playedTime.put(playerName, playedTime.get(playerName) + hours);
        } else {
            playedTime.put(playerName, hours);
        }
    }

    /**
     * Ищет игрока, который проиграл больше всего времени.
     * Если игроков нет, возвращает null.
     */
    public String getMostPlayer() {
        String mostPlayer = null;
        int maxTime = 0; // ИСПРАВЛЕНО: Поиск начинается с 0, чтобы найти любого игрока
        for (String playerName : playedTime.keySet()) {
            int time = playedTime.get(playerName);
            if (time > maxTime) {
                maxTime = time;
                mostPlayer = playerName;
            }
        }
        return mostPlayer;
    }

    /**
     * Суммирует общее количество игрового времени всех игроков.
     */
    public int getSumPlayedTime() {
        int sum = 0;
        for (int time : playedTime.values()) {
            sum += time;
        }
        return sum;
    }
}