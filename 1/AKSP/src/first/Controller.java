package first;

public class Controller{
    String value;

    public Controller(String value) {
        this.value = value;
    }

    void switchSwitch(){
        value = value.equals("Ping") ? "Pong" : "Ping";
    }

    synchronized void doPing(){
        while (!this.isPing()){
            try{
                wait();
            }
            catch (InterruptedException ignored){}
        }
        System.out.println("Ping");
        this.switchSwitch();
        notify();
    }

    synchronized void doPong(){
        while (this.isPing()){
            try{
                wait();
            }
            catch (InterruptedException ignored){}
        }
        System.out.println("Pong");
        this.switchSwitch();
        notify();
    }

    boolean isPing(){
        return value.equals("Ping");
    }

}
