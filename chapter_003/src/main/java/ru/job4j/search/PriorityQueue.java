package ru.job4j.search;

import java.util.LinkedList;

/**
 * Менеджер задач.
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Вставляем в очередь текущую задачу.
     * При наличии задач с совпадающим приоритетом, вставка осуществляется после них.
     *
     * @param task текущая задача
     */
    public void put(Task task) {
        var iterator = this.tasks.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().getPriority() > task.getPriority()) {
                iterator.previous();
                break;
            }
        }
        iterator.add(task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}