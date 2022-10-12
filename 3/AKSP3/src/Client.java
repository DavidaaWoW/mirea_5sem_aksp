import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {

    public static String ipAddr = "localhost";
    public static int port = 8085;

    public static void main(String[] args) {
        new ClientImpl(ipAddr, port);
    }
}

class ClientImpl {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private String addr;
    private int port;
    private Date time;
    private String dtime;
    private SimpleDateFormat dt1;

    public ClientImpl(String addr, int port) {
        this.addr = addr;
        this.port = port;
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            new ReadMsg().start();
            new WriteMsg().start();
        } catch (IOException e) {

        }
    }

    private class ReadMsg extends Thread {
        @Override
        public void run() {

            String str;
            try {
                while (true) {
                    str = in.readLine();
                    System.out.println(str);
                }
            } catch (IOException e) {

            }
        }
    }

    public class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                String userWord;
                try {
                    time = new Date();
                    dt1 = new SimpleDateFormat("HH:mm:ss");
                    dtime = dt1.format(time);
                    userWord = inputUser.readLine();
                        out.write("(" + dtime + ") " + ": " + userWord + "\n");
                    out.flush();
                } catch (IOException e) {
                }

            }
        }
    }
}
