import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Calculator extends Remote {
    List<Double> getResult(double a, double b, double c) throws RemoteException;
}
