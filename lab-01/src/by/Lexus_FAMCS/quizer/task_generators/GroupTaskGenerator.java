package by.Lexus_FAMCS.quizer.task_generators;

import by.Lexus_FAMCS.quizer.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GroupTaskGenerator implements Task.Generator {
    /**
     * Конструктор с переменным числом аргументов
     *
     * @param generators генераторы, которые в конструктор передаются через запятую
     */
    private List<Task.Generator> taskGens = new ArrayList<>();
    public GroupTaskGenerator(Task.Generator... generators) {
        taskGens.addAll(Arrays.asList(generators));
    }

    /**
     * Конструктор, который принимает коллекцию генераторов
     *
     * @param generators генераторы, которые передаются в конструктор в Collection (например, {@link ArrayList})
     */
    public GroupTaskGenerator(Collection<Task.Generator> generators) {
        taskGens.addAll(generators);
    }

    /**
     * @return результат метода generate() случайного генератора из списка.
     *         Если этот генератор выбросил исключение в методе generate(), выбирается другой.
     *         Если все генераторы выбрасывают исключение, то и тут выбрасывается исключение.
     */
    public Task generate() {
        int num = (int) (Math.random() * taskGens.size());
        Task.Generator taskGen;
        RuntimeException exception = new RuntimeException();
        Task res = null;
        for (int i = 0, size = taskGens.size(); i < size; ++i) {
            taskGen = taskGens.get((num + i) % size);
            try {
                res = taskGen.generate();
            } catch (Exception exc) {
                if (i == size - 1) throw exception;
                exception.addSuppressed(exc);
            }
        }
        return res;
    }
}
