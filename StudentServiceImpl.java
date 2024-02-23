import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentServiceImpl extends UnicastRemoteObject implements StudentService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    public StudentServiceImpl() throws RemoteException {
        super();
    }

    public void saveStudent(int id, String name, String department) throws RemoteException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO students VALUES (?, ?, ?)");
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, department);
            statement.executeUpdate();
            statement.close();
            connection.close();
            System.out.println("Student saved successfully.");
        } catch (SQLException e) {
            System.err.println("Error saving student: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            StudentService studentService = new StudentServiceImpl();
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            java.rmi.Naming.rebind("//localhost/StudentService", studentService);
            System.out.println("StudentService bound in registry");
        } catch (Exception e) {
            System.err.println("StudentService exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
