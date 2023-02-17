package prueba2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



    public class Servidor {
        public static void main(String[] args) throws IOException {
            int puerto = 12346;

            try (ServerSocket serverSocket = new ServerSocket(puerto)) {
                System.out.println("Servidor escuchando en el puerto " + puerto);

                while (true) {
                    try (Socket socket = serverSocket.accept();
                         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    ) {
                        // Enviar comando al cliente
                        out.println("dir");

                        // Leer resultado del cliente
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String respuesta;
                        while ((respuesta = in.readLine()) != null) {
                            System.out.println("Resultado del cliente: " + respuesta);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error al escuchar en el puerto " + puerto);
                System.exit(1);
            }
        }
    }

