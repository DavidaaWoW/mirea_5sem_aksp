package first;

public class Ping implements Runnable{
    Controller controller;

    public Ping(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            controller.doPing();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
