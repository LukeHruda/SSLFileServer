import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.InputStreamReader;

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
            while (true) printWriter.println(bufferedReader.readLine()+"echo");
        } catch (Exception e) {
            //TODO: handle exception
            //fuck the exception
        }
        
    }
}
