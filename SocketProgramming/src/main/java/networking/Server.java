package networking;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    static void main(String[] args) {
        try {
            int PORT = 3000;
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket server = serverSocket.accept();

            OutputStream out = server.getOutputStream();
            InputStream in = server.getInputStream();

            Scanner sc = new Scanner(System.in);

            while (true) {

                byte[] buffer = new byte[1024];
                int end = in.read(buffer);
                String res = new String(buffer, 0, end);
                System.out.println("Client - " + res);

                System.out.println("Enter Your Message or exit");
                String req = sc.nextLine();

                if (req.equals("exit")) break;

                out.write(req.getBytes());
                out.flush();
            }

            server.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
