package ru.netology;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private String name;

    /** информация о том, в какую игру сколько часов было сыграно
     ключ - игра
     значение - суммарное количество часов игры в эту игру */
    private Map<Game, Integer> playedTime = new HashMap<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** добавление игры игроку
     если игра уже была, никаких изменений происходить не должно */
    public void installGame(Game game) {
        // ИСПРАВЛЕНО: Добавлена проверка. Если игра уже есть, не перезаписываем (не обнуляем часы).
        if (!playedTime.containsKey(game)) {
            playedTime.put(game, 0);
        }
    }

    /** игрок играет в игру game на протяжении hours часов
     об этом нужно сообщить объекту-каталогу игр, откуда была установлена игра
     также надо обновить значения в мапе игрока, добавив проигранное количество часов
     возвращает суммарное количество часов, проигранное в эту игру.
     если игра не была установлена, то надо выкидывать RuntimeException */
    public int play(Game game, int hours) {
        // ИСПРАВЛЕНО: Если игры нет в мапе (не установлена), выкидываем исключение по ТЗ.
        if (!playedTime.containsKey(game)) {
            throw new RuntimeException("Игра " + game.getTitle() + " не установлена");
        }

        game.getStore().addPlayTime(name, hours);

        // ИСПРАВЛЕНО: Часы теперь суммируются с уже имеющимися, а не перезаписываются.
        int currentTime = playedTime.get(game);
        playedTime.put(game, currentTime + hours);

        return playedTime.get(game);
    }

    /** Метод принимает жанр игры (строку) и суммирует все часы,
     * проигранные в игры этого жанра этим игроком */
    public int sumGenre(String genre) {
        int sum = 0;
        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                sum += playedTime.get(game);
            }
        }
        return sum;
    }

    /** Метод принимает жанр и возвращает игру этого жанра, в которую играли больше всего.
     * Если в игры этого жанра не играли, возвращает null */
    public Game mostPlayerByGenre(String genre) {
        int maxTime = 0;
        Game mostPlayed = null;
        for (Game game : playedTime.keySet()) {
            if (game.getGenre().equals(genre)) {
                int time = playedTime.get(game);
                if (time > maxTime) {
                    maxTime = time;
                    mostPlayed = game;
                }
            }
        }
        return mostPlayed;
    }
}