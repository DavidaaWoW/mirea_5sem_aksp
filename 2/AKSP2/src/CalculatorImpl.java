import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class CalculatorImpl implements Calculator{
    @Override
    public List<Double> getResult(double a, double b, double c) throws RemoteException {
        double D = b*b-4*a*c;
        List<Double> result = new ArrayList<Double>();
        if(D>0){
            result.add((-b+Math.sqrt(D))/(2*a));
            result.add((-b-Math.sqrt(D))/(2*a));
        } else if (D == 0) {
            result.add(-b/2*a);
        }
        return result;
    }
}
