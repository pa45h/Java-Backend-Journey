package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        int PORT = 3000;
        String IP = "localhost";

        try {
            for (int i = 0; i < 1000; i++) {
                Socket socket = new Socket(IP, PORT);
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);

                toServer.println("Hello Server...!👋");
                String res = fromServer.readLine();
                System.out.println("Server : " + res);

                fromServer.close();
                toServer.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
