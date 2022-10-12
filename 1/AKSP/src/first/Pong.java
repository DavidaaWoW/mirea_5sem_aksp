package first;

public class Pong implements Runnable{
    Controller controller;

    public Pong(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (true) {
            controller.doPong();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
