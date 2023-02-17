package prueba4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(12347);

        while (true) {
            Socket socket = serverSocket.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String comando;

            do {
                System.out.print("Comando: ");
                comando = new BufferedReader(new InputStreamReader(System.in)).readLine();
                out.println(comando);
                String linea;
                while ((linea = in.readLine()) != null) {
                    System.out.println(linea);
                }
            } while (!comando.equals("exit"));

            socket.close();
        }
    }
}