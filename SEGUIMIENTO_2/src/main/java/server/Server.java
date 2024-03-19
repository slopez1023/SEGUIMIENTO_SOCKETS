package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Server {

    private ServerSocket serverSocket;
    private String[] list;

    public Server() {
        list = new String[]{"Santiago","Monica","Juan Camilo","Maria Angélica","Diana","Sebastian","Jaime","Miguel","Gloria","Argelia"};
        try {
            serverSocket = new ServerSocket(5000);
            Logger.getGlobal().info("SERVER: I'm running on port 5000...");
            transferData();
        } catch (IOException e) {
            Logger.getGlobal().info("SERVER: Server failure");
        }
    }

    private void transferData() throws IOException {
        Socket connection = serverSocket.accept();
        Logger.getGlobal().info("SERVER: New Conection");
        DataInputStream connectionInput = new DataInputStream(connection.getInputStream());
        DataOutputStream connectionOutput = new DataOutputStream(connection.getOutputStream());

        while (true){
            connectionOutput.writeUTF(searchPosition(connectionInput.readInt()));
        }
    }

    private String searchPosition(int position)  {
        for (int i = 0; i < list.length; i++) {
            if (list[position] != null) {
                return list[position];
            }
        }
        return "No exist";
    }

    public static void main(String[] args) {
        new Server();
    }
}
