import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;;

public class Client {
    public static void main(String[] args) throws IOException{
        System.setProperty("javax.net.ssl.trustStore","group.18");
        Socket socket = ((SSLSocketFactory) SSLSocketFactory.getDefault()).createSocket("localhost",4444);
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter a username");
        printWriter.println(userInput.readLine());
        String message = null;

        while(true){
            System.out.println("Please send a message.");
            message = userInput.readLine();
            if(message.equals("quit")){
                socket.close();
                break;
            }
            printWriter.println(message);
            System.out.println("Reply from server:");
            System.out.println(socketBufferedReader.readLine());
        }
    }
}
