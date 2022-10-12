import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Client {
    public static final String UNIQUE_BINDING_NAME = "server.calculator";

    public static void main(String[] args) throws RemoteException, NotBoundException {

        final Registry registry = LocateRegistry.getRegistry(2732);

        Calculator calculator = (Calculator) registry.lookup(UNIQUE_BINDING_NAME);

        List <Double> getResult = calculator.getResult(3, 7, -6);

        System.out.println(getResult);
    }
}
