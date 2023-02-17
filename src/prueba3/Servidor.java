package prueba3;
import java.net.*;
import java.io.*;

public class Servidor implements Runnable {

    private ServerSocket server;
    private Socket cliente;
    private Thread hilo;

    public Servidor() throws IOException {
        server = new ServerSocket(5000);
        hilo = new Thread(this);
        hilo.start();
    }

    public void run() {
        try {
            cliente = server.accept();
            enviarComando("ls", cliente.getOutputStream());
            recibirResultado(cliente.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviarComando(String comando, OutputStream out) throws IOException {
        PrintWriter pw = new PrintWriter(out, true);
        pw.println(comando);
    }

    public void recibirResultado(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String linea;
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }
    }

    public static void main(String[] args) throws IOException {
        new Servidor();
    }
}