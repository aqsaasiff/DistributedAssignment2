import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerServer extends UnicastRemoteObject implements TaskManager {
    private List<String> tasks;
    private int completedCount;

    public TaskManagerServer() throws RemoteException {
        tasks = new ArrayList<>();
        completedCount = 0;
    }

    @Override
    public synchronized String addTask(String task) throws RemoteException {
        tasks.add(task);
        return "Task added: " + task;
    }

    @Override
    public synchronized List<String> viewTasks() throws RemoteException {
        return tasks;
    }

    @Override
    public synchronized String markTaskCompleted(int taskId) throws RemoteException {
        if (taskId >= 0 && taskId < tasks.size()) {
            tasks.set(taskId, tasks.get(taskId) + " [Completed]");
            completedCount++;
            return "Task marked as completed.";
        }
        return "Invalid task ID.";
    }

    @Override
    public synchronized String deleteTask(int taskId) throws RemoteException {
        if (taskId >= 0 && taskId < tasks.size()) {
            tasks.remove(taskId);
            return "Task deleted.";
        }
        return "Invalid task ID.";
    }

    @Override
    public synchronized String getStatistics() throws RemoteException {
        return "Total tasks: " + tasks.size() + ", Completed: " + completedCount;
    }

    public static void main(String[] args) {
        try {
            TaskManagerServer server = new TaskManagerServer();
            Naming.rebind("rmi://localhost/TaskManager", server);
            System.out.println("Task Manager Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
