package first;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller("Ping");
        Ping ping = new Ping(controller);
        Pong pong = new Pong(controller);
        new Thread(ping).start();
        new Thread(pong).start();
    }
}
