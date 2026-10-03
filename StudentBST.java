public class StudentBST {

    private static class TreeNode {
        Student student;
        TreeNode left;
        TreeNode right;

        TreeNode(Student student) {
            this.student = student;
        }
    }

    private TreeNode root;

    public boolean addStudent(Student student) {
        if (student == null || student.getStudentId() == null
                || student.getStudentId().trim().isEmpty()
                || findStudentById(student.getStudentId()) != null) {
            return false;
        }

        root = insert(root, student);
        return true;
    }

    private TreeNode insert(TreeNode current, Student student) {
        if (current == null) {
            return new TreeNode(student);
        }

        if (student.getStudentId().compareTo(current.student.getStudentId()) < 0) {
            current.left = insert(current.left, student);
        } else {
            current.right = insert(current.right, student);
        }

        return current;
    }

    public Student findStudentById(String studentId) {
        if (studentId == null) {
            return null;
        }

        TreeNode current = root;
        while (current != null) {
            int comparison = studentId.compareTo(current.student.getStudentId());
            if (comparison == 0) {
                return current.student;
            }
            current = comparison < 0 ? current.left : current.right;
        }

        return null;
    }

    public boolean removeStudentById(String studentId) {
        if (findStudentById(studentId) == null) {
            return false;
        }

        root = remove(root, studentId);
        return true;
    }

    private TreeNode remove(TreeNode current, String studentId) {
        int comparison = studentId.compareTo(current.student.getStudentId());

        if (comparison < 0) {
            current.left = remove(current.left, studentId);
        } else if (comparison > 0) {
            current.right = remove(current.right, studentId);
        } else if (current.left == null) {
            return current.right;
        } else if (current.right == null) {
            return current.left;
        } else {
            TreeNode successor = findSmallest(current.right);
            current.student = successor.student;
            current.right = remove(current.right, successor.student.getStudentId());
        }

        return current;
    }

    private TreeNode findSmallest(TreeNode current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No student records found.");
            return;
        }

        displayInOrder(root);
    }

    private void displayInOrder(TreeNode current) {
        if (current == null) {
            return;
        }

        displayInOrder(current.left);
        System.out.println(current.student);
        displayInOrder(current.right);
    }
}