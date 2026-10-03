public class StudentHashTable {

    private static final int CAPACITY = 101;

    private static class Entry {
        Student student;
        Entry next;

        Entry(Student student, Entry next) {
            this.student = student;
            this.next = next;
        }
    }

    private final Entry[] buckets = new Entry[CAPACITY];

    public boolean addStudent(Student student) {
        if (student == null || student.getStudentId() == null
                || student.getStudentId().trim().isEmpty()) {
            return false;
        }

        int index = getIndex(student.getStudentId());
        Entry current = buckets[index];

        while (current != null) {
            if (current.student.getStudentId().equals(student.getStudentId())) {
                return false;
            }
            current = current.next;
        }

        buckets[index] = new Entry(student, buckets[index]);
        return true;
    }

    public Student searchById(String studentId) {
        if (studentId == null) {
            return null;
        }

        Entry current = buckets[getIndex(studentId)];
        while (current != null) {
            if (current.student.getStudentId().equals(studentId)) {
                return current.student;
            }
            current = current.next;
        }

        return null;
    }

    public boolean removeStudent(String studentId) {
        if (studentId == null) {
            return false;
        }

        int index = getIndex(studentId);
        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {
            if (current.student.getStudentId().equals(studentId)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                return true;
            }
            previous = current;
            current = current.next;
        }

        return false;
    }

    private int getIndex(String studentId) {
        return Math.floorMod(studentId.hashCode(), CAPACITY);
    }
}