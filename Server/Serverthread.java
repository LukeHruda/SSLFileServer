import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Serverthread extends Thread {
    Socket socket;
    Serverthread(Socket socket)
    {
        this.socket = socket;
    }
    public void run(){
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("user: '"+bufferedReader.readLine()+"' is now connected.");
            String message;
            while (true) {
                message = bufferedReader.readLine();
                System.out.println(message);
                if(message.equals("file"))
                {
                    String file = bufferedReader.readLine();
                    InputStream in = socket.getInputStream();
                    OutputStream out = new FileOutputStream("files\\"+file);

                    byte[] bytes = new byte[16*1024];

                    int count;
                    while ((count = in.read(bytes)) > 0) {
                        out.write(bytes, 0, count);
                    }

                    out.close();
                    in.close();
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
            //fuck the exception
        }
        
    }
}