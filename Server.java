//
// created by SKundu2003
// 
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    public Server() {
        try {
            this.server = new ServerSocket(7777);
            System.out.println("server is ready for accepting connection");
            System.out.println("waiting for request......");
            this.socket = this.server.accept();
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream());
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        this.StartRead();
        this.StartWrite();
    }

    public void StartRead() {
        Runnable var1 = () -> {
            while(true) {
                String var1 = null;

                try {
                    var1 = this.br.readLine();
                    if (var1.equals("exit")) {
                        System.out.println("process terminated by user");
                        return;
                    }

                    System.out.println("client : " + var1);
                } catch (Exception var3) {
                    System.out.println(var3.getMessage());
                }
            }
        };
        (new Thread(var1)).start();
    }

    public void StartWrite() {
        System.out.println("writer started");
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
        new Server();
    }
}
