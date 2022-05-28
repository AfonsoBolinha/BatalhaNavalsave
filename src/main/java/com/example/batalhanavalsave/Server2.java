package com.example.batalhanavalsave;


import java.net.*;
import java.io.*;

public class Server2
{
    //initialize socket and input stream
    private Socket socket;
    private ServerSocket server;
    private DataInputStream in;
    private DataOutputStream out;
    String line = "";

    // creates a server and connects it to the given port
    public Server2(int port)
    {
        // starts server and waits for a connection
        try
        {
            // we start our server
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            // we accept the client in the given port
            // and create a socket
            // we now have an established connection between our client and our server on the
            // given socket
            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public void recebe(){
        while (!line.equals("Stop"))
        {
            try
            {
                line = in.readUTF();
                System.out.println(line);

            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
    }
    public void envia(int posx,int posy) throws IOException {
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(posx+""+posy);
    }
    public static void main(String args[])
    {
        Server2 server = new Server2(2222);
    }
}