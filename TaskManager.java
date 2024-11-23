import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TaskManager extends Remote {
    String addTask(String task) throws RemoteException;
    List<String> viewTasks() throws RemoteException;
    String markTaskCompleted(int taskId) throws RemoteException;
    String deleteTask(int taskId) throws RemoteException;
    String getStatistics() throws RemoteException;
}
