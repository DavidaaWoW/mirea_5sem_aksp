import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Server {

    public static final int PORT = 8085;
    public static LinkedList<ServerImpl> serverList = new LinkedList<>();
    public static MessageBuf messageBuf;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        messageBuf = new MessageBuf();
        System.out.println("Server Started");
        try {
            Thread one = new Thread(()->{
                while (true){
                    if(!messageBuf.isBufEmpty()){
                        for (ServerImpl vr : Server.serverList) {
                            try {
                                for (ServerImpl _server : serverList ){
                                    messageBuf.printBuf(new BufferedWriter(new OutputStreamWriter(_server.getSocket().getOutputStream())));
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            finally {
                                messageBuf.clearBuf();
                            }
                        }
                    }
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            one.start();
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerImpl(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
class ServerImpl extends Thread {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    public ServerImpl(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Server.messageBuf.printBuf(out);
        start();
    }
    @Override
    public void run() {
        String word;
        try {
                while (true) {
                    word = in.readLine();
                    Server.messageBuf.addBufEl(word);
                }
        } catch (IOException e) {
        }
    }

    public Socket getSocket() {
        return socket;
    }
}
class MessageBuf {
    private LinkedList<String> serverBuf = new LinkedList<>();
    public void addBufEl(String el) {
        serverBuf.add(el);
    }

    public void printBuf(BufferedWriter writer) {
        if(serverBuf.size() > 0) {
            try {
                for (String vr : serverBuf) {
                    writer.write(vr + "\n");
                }
                writer.flush();
            } catch (IOException ignored) {}
        }
    }

    public void clearBuf(){
        serverBuf.clear();
    }

    public boolean isBufEmpty(){
        return serverBuf.isEmpty();
    }
}