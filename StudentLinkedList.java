

public class StudentLinkedList {

    private StudentNode head;

    public boolean addStudent(Student student) {
        if (student == null || student.getStudentId() == null
                || student.getStudentId().trim().isEmpty()
                || findStudentById(student.getStudentId()) != null) {
            return false;
        }

        StudentNode newNode = new StudentNode(student);

        if (head == null) {
            head = newNode;
            return true;
        }

        StudentNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        return true;
    }

    public Student findStudentById(String studentId) {
        StudentNode current = head;

        while (current != null) {
            if (current.data.getStudentId().equals(studentId)) {
                return current.data;
            }
            current = current.next;
        }

        return null;
    }

    public boolean updateStudent(String studentId, String name, String programme, double marks) {
        Student student = findStudentById(studentId);

        if (student == null) {
            return false;
        }

        student.setName(name);
        student.setProgramme(programme);
        student.setMarks(marks);
        return true;
    }

    public boolean deleteStudent(String studentId) {
        if (head == null) {
            return false;
        }

        if (head.data.getStudentId().equals(studentId)) {
            head = head.next;
            return true;
        }

        StudentNode current = head;

        while (current.next != null) {
            if (current.next.data.getStudentId().equals(studentId)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public void displayStudents() {

        if (head == null) {
            System.out.println("No student records found.");
            return;
        }

        StudentNode current = head;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}