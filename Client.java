import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    Client() {
        try {
            System.out.println("connecting to the Souvik wait for it bro....");
            this.socket = new Socket("127.0.0.1", 7777); //change the ip address
          //replace the ip address with server ip
            System.out.println("connection done...\n abar bol vai....");
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream());
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        this.ReadData();
        this.WriterData();
    }

    public void ReadData() {
        Runnable var1 = () -> {
            while(true) {
                try {
                    String var1 = this.br.readLine();
                    if (var1.equals("exit")) {
                        System.out.println("process terminated");
                        return;
                    }

                    System.out.println("Souvik : " + var1);
                } catch (Exception var2) {
                    var2.printStackTrace();
                }
            }
        };
        (new Thread(var1)).start();
    }

    public void WriterData() {
        System.out.println("Writing process started");
        Runnable var1 = () -> {
            while(true) {
                try {
                    BufferedReader var1 = new BufferedReader(new InputStreamReader(System.in));
                    String var2 = var1.readLine();
                    this.out.println(var2);
                    this.out.flush();
                } catch (Exception var3) {
                    var3.printStackTrace();
                }
            }
        };
        (new Thread(var1)).start();
    }

    public static void main(String[] var0) {
        new Client();
    }
}
