package ru.job4j.pseudo;

/**
 * Интерфейс, реазизация кот. означает возможность представления в псевдографике.
 */
public interface Shape {

    /**
     * возвращает фигуру, отрисованную символами
     * @return псевдофигура
     */
	String draw();
}