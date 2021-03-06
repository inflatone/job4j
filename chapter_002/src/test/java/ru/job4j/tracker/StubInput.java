package ru.job4j.tracker;

/**
 * Используется для эмуляции пользовательского ввода из консоли.
 *
 */
public class StubInput implements Input {

    private ConsoleInput pseudo = new ConsoleInput();

    /** содержит запрограммированную последовательность вводимых пользователем команд в консоль */
    private final String[] value;

    /** счётчик продвижения методом ask по последовательности команд */
    private int position;

    /**
     * Конструктор, передающий в параметрах массив с предполагаемым набором команд пользоватедя
     * @param value последовательность команд
     */
    public StubInput(final String[] value) {
        this.value = value;
    }

    /**
     * Возвращает очередную команду из запрограммированной последовательности
     * @param question запрос на ввод данных
     * @return ответ за запрос
     */
    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        if (this.pseudo.isNotCorrect(key, range)) {
            throw new MenuOutException("Out of menu range.");
        }
        return key;
    }
}