import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.ssl.SSLSocketFactory;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;

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

            else if(message.equals("file")){
                printWriter.println(message);
                String fileName = userInput.readLine();
                printWriter.println(fileName);
                File file = new File(fileName);
                // Get the size of the file
                long length = file.length();
                byte[] bytes = new byte[16 * 1024];
                InputStream in = new FileInputStream(file);
                OutputStream out = socket.getOutputStream();
                
                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }
                out.close();
                in.close();
            }
            else{
                printWriter.println(message);
                System.out.println("Reply from server:");
                System.out.println(socketBufferedReader.readLine());
            }
            
        }
    }
}