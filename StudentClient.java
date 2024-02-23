import java.rmi.Naming;

public class StudentClient {
    public static void main(String[] args) {
        try {
            StudentService studentService = (StudentService) Naming.lookup("//localhost/StudentService");
            studentService.saveStudent(21430, "Dawit Petros", "Computer Science and Engineering");
	    studentService.saveStudent(21380, "Fesum Adane", "Computer Science and Engineering");
	    studentService.saveStudent(21000, "Kalid Kasim", "Computer Science and Engineering");
            System.out.println("Student information sent to server.");
        } catch (Exception e) {
            System.err.println("StudentClient exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

