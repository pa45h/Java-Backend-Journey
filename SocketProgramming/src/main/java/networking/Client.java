package networking;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static void main(String[] args) {
        try {
            int PORT = 3000;
            String IP = "localhost";
            Socket client = new Socket(IP, PORT);

            OutputStream out = client.getOutputStream();
            InputStream in = client.getInputStream();

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("Enter Your Message or exit");
                String req = sc.nextLine();

                if (req.equals("exit")) break;

                out.write(req.getBytes());
                out.flush();

                byte[] buffer = new byte[1024];
                int end = in.read(buffer);
                String res = new String(buffer, 0, end);
                System.out.println("Server - " + res);
            }
            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
