package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {

  public static void main(String[] args) throws IOException {
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;

    ServerSocket ss = new ServerSocket(5432);

    Socket s = null;

    while (true) {
      try {
        String[] res;
        s = ss.accept();
        int suma = 0;
        System.out.println("Se conecta desde la IP " + s.getInetAddress());

        ois = new ObjectInputStream(s.getInputStream());
        oos = new ObjectOutputStream(s.getOutputStream());

        String cadena = (String) ois.readObject();

        res = cadena.split(",");

        for (String num : res) {
          suma += Integer.parseInt(num);
        }

        int prom = suma / res.length;

        oos.writeObject(prom);
        System.out.println("El promedio calculado es: " + prom);
        System.out.println("Promedio enviado...");
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (oos != null)
          oos.close();
        if (ois != null)
          ois.close();
        if (s != null)
          s.close();
        System.out.println("Conexion cerrada");
      }
    }

  }

}
