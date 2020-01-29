import java.io.*;
import java.net.*;

public class TCPServer{
    public static void main(String[] args) {
        String clientSentence;
        String capitalizedSentence;
        ServerSoket welcomeSocket = new ServerSocket(44014);

        while(true){
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.geetInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            System.out.println("Recived: " + clientSentence);
            capitalizedSentence = clientSentence.toUpperCase() + "\n";
            outToClient.writeBytes(capitalizedSentence);
        }
    }
}