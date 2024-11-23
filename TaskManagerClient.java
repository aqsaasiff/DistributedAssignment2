import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class TaskManagerClient {
    public static void main(String[] args) {
        try {
            TaskManager taskManager = (TaskManager) Naming.lookup("rmi://localhost/TaskManager");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n1. Add Task");
                System.out.println("2. View Tasks");
                System.out.println("3. Mark Task Completed");
                System.out.println("4. Delete Task");
                System.out.println("5. Get Statistics");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                // Validate input to avoid InputMismatchException
                int choice = -1;
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 6.");
                    scanner.nextLine(); // Clear invalid input
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter task: ")
                        String task = scanner.nextLine();
                        System.out.println(taskManager.addTask(task));
                        break;
                    case 2:
                        List<String> tasks = taskManager.viewTasks();
                        System.out.println("Tasks:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(i + ". " + tasks.get(i));
                        }
                        break;
                    case 3:
                        System.out.print("Enter task ID to mark as completed: ");
                        int completeId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        System.out.println(taskManager.markTaskCompleted(completeId));
                        break;
                    case 4:
                        System.out.print("Enter task ID to delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        System.out.println(taskManager.deleteTask(deleteId));
                        break;
                    case 5:
                        System.out.println(taskManager.getStatistics());
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a number between 1 and 6.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
