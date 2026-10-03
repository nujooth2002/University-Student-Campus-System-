import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final StudentLinkedList students = new StudentLinkedList();
    private final ServiceRequestQueue requests = new ServiceRequestQueue();
    private final ActionHistoryStack history = new ActionHistoryStack();
    private final StudentBST studentTree = new StudentBST();
    private final StudentHashTable studentHashTable = new StudentHashTable();
    private final CampusGraph campus = new CampusGraph();

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        while (true) {
            displayMenu();
            Integer choice = readInteger("Choose an option: ");
            if (choice == null) {
                System.out.println("Input ended. Exiting.");
                return;
            }

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    students.displayStudents();
                    break;
                case 5:
                    addServiceRequest();
                    break;
                case 6:
                    processServiceRequest();
                    break;
                case 7:
                    history.displayRecentActions();
                    break;
                case 8:
                    studentTree.displayInOrder();
                    break;
                case 9:
                    searchStudentByHash();
                    break;
                case 10:
                    addCampusLocation();
                    break;
                case 11:
                    removeCampusLocation();
                    break;
                case 12:
                    addCampusConnection();
                    break;
                case 13:
                    removeCampusConnection();
                    break;
                case 14:
                    campus.displayConnections();
                    break;
                case 15:
                    traverseCampus();
                    break;
                case 16:
                    System.out.println("Goodbye.");
                    return;
                default:
                    System.out.println("Choose a number from 1 to 16.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n=== University Student and Campus System ===");
        System.out.println("1. Add student record");
        System.out.println("2. Update student record");
        System.out.println("3. Delete student record");
        System.out.println("4. Display all students (linked list)");
        System.out.println("5. Add service request (queue)");
        System.out.println("6. Process next service request");
        System.out.println("7. Display recent actions (stack)");
        System.out.println("8. Display students in ID order (BST)");
        System.out.println("9. Search student by ID (hash table)");
        System.out.println("10. Add campus location");
        System.out.println("11. Remove campus location");
        System.out.println("12. Add campus connection");
        System.out.println("13. Remove campus connection");
        System.out.println("14. Display campus connections");
        System.out.println("15. Traverse campus using BFS");
        System.out.println("16. Exit");
    }

    private void addStudent() {
        String studentId = readRequiredText("Student ID: ");
        String name = readRequiredText("Name: ");
        String programme = readRequiredText("Programme: ");
        Double marks = readMarks();
        if (studentId == null || name == null || programme == null || marks == null) {
            return;
        }

        Student student = new Student(studentId, name, programme, marks);
        if (!students.addStudent(student)) {
            System.out.println("Student was not added. Check for a duplicate or invalid ID.");
            return;
        }

        studentTree.addStudent(student);
        studentHashTable.addStudent(student);
        history.push("Added student " + studentId);
        System.out.println("Student added.");
    }

    private void updateStudent() {
        String studentId = readRequiredText("Student ID to update: ");
        if (studentId == null) {
            return;
        }
        if (studentHashTable.searchById(studentId) == null) {
            System.out.println("No student found with that ID.");
            return;
        }

        String name = readRequiredText("New name: ");
        String programme = readRequiredText("New programme: ");
        Double marks = readMarks();
        if (name == null || programme == null || marks == null) {
            return;
        }

        students.updateStudent(studentId, name, programme, marks);
        history.push("Updated student " + studentId);
        System.out.println("Student updated.");
    }

    private void deleteStudent() {
        String studentId = readRequiredText("Student ID to delete: ");
        if (studentId == null) {
            return;
        }

        if (!students.deleteStudent(studentId)) {
            System.out.println("No student found with that ID.");
            return;
        }

        studentTree.removeStudentById(studentId);
        studentHashTable.removeStudent(studentId);
        history.push("Deleted student " + studentId);
        System.out.println("Student deleted.");
    }

    private void addServiceRequest() {
        String request = readRequiredText("Service request: ");
        if (request != null && requests.addRequest(request)) {
            history.push("Added a service request");
            System.out.println("Request added to the queue.");
        }
    }

    private void processServiceRequest() {
        String request = requests.processNextRequest();
        if (request == null) {
            System.out.println("There are no service requests waiting.");
            return;
        }

        history.push("Processed a service request");
        System.out.println("Processed request: " + request);
    }

    private void searchStudentByHash() {
        String studentId = readRequiredText("Student ID to search: ");
        if (studentId == null) {
            return;
        }

        Student student = studentHashTable.searchById(studentId);
        if (student == null) {
            System.out.println("No student found with that ID.");
        } else {
            System.out.println(student);
        }
    }

    private void addCampusLocation() {
        String location = readRequiredText("Location name: ");
        if (location == null) {
            return;
        }

        if (campus.addLocation(location)) {
            history.push("Added campus location " + location);
            System.out.println("Campus location added.");
        } else {
            System.out.println("That location is invalid or already exists.");
        }
    }

    private void removeCampusLocation() {
        String location = readRequiredText("Location name to remove: ");
        if (location == null) {
            return;
        }

        if (campus.removeLocation(location)) {
            history.push("Removed campus location " + location);
            System.out.println("Campus location and its connections removed.");
        } else {
            System.out.println("That campus location does not exist.");
        }
    }

    private void addCampusConnection() {
        String firstLocation = readRequiredText("First location: ");
        String secondLocation = readRequiredText("Second location: ");
        if (firstLocation == null || secondLocation == null) {
            return;
        }

        if (campus.addConnection(firstLocation, secondLocation)) {
            history.push("Added campus connection " + firstLocation + " - " + secondLocation);
            System.out.println("Campus connection added.");
        } else {
            System.out.println("Connection unavailable: check both locations and whether the road already exists.");
        }
    }

    private void removeCampusConnection() {
        String firstLocation = readRequiredText("First location: ");
        String secondLocation = readRequiredText("Second location: ");
        if (firstLocation == null || secondLocation == null) {
            return;
        }

        if (campus.removeConnection(firstLocation, secondLocation)) {
            history.push("Removed campus connection " + firstLocation + " - " + secondLocation);
            System.out.println("Campus connection removed.");
        } else {
            System.out.println("That connection does not exist.");
        }
    }

    private void traverseCampus() {
        String startingLocation = readRequiredText("Starting location: ");
        if (startingLocation == null) {
            return;
        }

        List<String> visitOrder = campus.breadthFirstTraversal(startingLocation);
        if (visitOrder.isEmpty()) {
            System.out.println("Starting location does not exist.");
        } else {
            System.out.println("BFS order: " + String.join(" -> ", visitOrder));
        }
    }

    private String readRequiredText(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                return null;
            }

            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    private Integer readInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                return null;
            }

            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Enter a whole number.");
            }
        }
    }

    private Double readMarks() {
        while (true) {
            System.out.print("Marks (0-100): ");
            if (!scanner.hasNextLine()) {
                return null;
            }

            String input = scanner.nextLine().trim();
            try {
                double marks = Double.parseDouble(input);
                if (Double.isNaN(marks) || Double.isInfinite(marks) || marks < 0 || marks > 100) {
                    System.out.println("Marks must be a number from 0 to 100.");
                } else {
                    return marks;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Enter marks as a number from 0 to 100.");
            }
        }
    }
}