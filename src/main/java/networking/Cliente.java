package networking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Cliente {

  public static void main(String[] args) throws Exception {
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    Socket s = null;
    String cadena;

    File f = new File("./numeros.txt");
    try (FileWriter fw = new FileWriter(f)) {
      for (int i = 0; i < 100000; i++) {
        if (i == 99999) {
          Random rand = new Random();
          int num = rand.nextInt(100);
          fw.write(num + "");
          break;
        }
        Random rand = new Random();
        int num = rand.nextInt(100);
        fw.write(num + ",");
      }
    } catch (Exception e) {
      System.out.println("Se ha producido un error");
    }

    try {
      s = new Socket("127.0.0.1", 5432);
      oos = new ObjectOutputStream(s.getOutputStream());
      ois = new ObjectInputStream(s.getInputStream());

      FileReader archivo = new FileReader("./numeros.txt");
      BufferedReader lector = new BufferedReader(archivo);

      cadena = lector.readLine();

      lector.close();

      oos.writeObject(cadena);

      int ret = (Integer) ois.readObject();

      System.out.println("El promedio de los numeros es " + ret);

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (oos != null)
        oos.close();
      if (ois != null)
        ois.close();
      if (s != null)
        s.close();
    }
  }
}
