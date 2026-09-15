package networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter toClient = new PrintWriter(socket.getOutputStream(),true);

            String res = fromClient.readLine();
            System.out.println("Client : " + res);
            toClient.println("Hello Client...!👋");

            fromClient.close();
            toClient.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Server {


    public static void main(String[] args) {
        try {
            int PORT = 3000;
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server Started..");
            ExecutorService pool = Executors.newFixedThreadPool(5);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected...");
                pool.execute(new ClientHandler(socket)); // 5 threads for all tasks with task queues

//                new Thread(new ClientHandler(socket)).start(); // for multi threaded app -- one thread per task
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
