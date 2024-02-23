import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StudentService extends Remote {
    void saveStudent(int id, String name, String department) throws RemoteException;
}
