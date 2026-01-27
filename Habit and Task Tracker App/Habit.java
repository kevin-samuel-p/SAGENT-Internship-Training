import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


class Habit {
    private enum HabitFrequency {
        DAILY,
        WEEKLY
    }

    class Task {
        String taskName;
        int taskDay;        // Does not apply for daily habits
        String description;
        boolean taskCompletionStatus = false;

        Task(String taskName, int taskDay, String description) {
            this.taskName = taskName;
            this.taskDay = taskDay;
            this.description = description;
        }

        void resetTask() {
            taskCompletionStatus = false;
        }
    }

    String habitName;
    HabitFrequency habitFrequency;
    boolean habitCompletionStatus = false;

    private Map<String, Task> tasks = new HashMap<>();
    private int tasksCompleted = 0;

    Habit(String habitName, HabitFrequency habitFrequency) {
        this.habitName = habitName;
        this.habitFrequency = habitFrequency;
    }

    void createTask(String taskName, String taskDescription, int taskDay) {
        if (tasks.containsKey(taskName)) {
            System.out.println("That task already exists!");
            return;
        }
        
        Task task = new Task(taskName, taskDay, taskDescription);
        tasks.put(taskName, task);
    }

    void modifyTask(String taskName, String taskDescription, int taskDay) {
        if (!tasks.containsKey(taskName)) {
            System.out.println("That task does not exist!");
            return;
        }

        Task task = new Task(taskName, taskDay, taskDescription);
        tasks.put(taskName, task);
    }

    void deleteTask(String taskName) {
        tasks.remove(taskName);
        System.out.println("Task removed!");
    }

    void showTasks() {
        StringBuilder sb = new StringBuilder();
        for (String taskName : tasks.keySet()) {
            Task task = tasks.get(taskName);

            
        }
    }

    void completeTask(String taskName) {
        if (!tasks.containsKey(taskName)) {
            System.out.println("That task does not exist!");
            return;
        }

        tasks.get(taskName).taskCompletionStatus = true;
    }

    void resetHabit() {
        for (String task : tasks.keySet()) {
            tasks.get(task).resetTask();
        }

        habitCompletionStatus = false;
        tasksCompleted = 0;
    }
}