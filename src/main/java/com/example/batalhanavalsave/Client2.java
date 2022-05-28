package com.example.batalhanavalsave;


import java.net.*;
import java.io.*;

public class Client2
{
    // We initialize our socket( tunnel )
    // and our input reader and output stream
    // we will take the input from the user
    // and send it to the socket using output stream
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream out;
    String line = "";
    // constructor that takes the IP Address and the Port
    public Client2(String address, int port)
    {
        // we try to establish a connection
        try
        {
            // creates a socket with the given information
            socket = new Socket(address, port);
            System.out.println("Connected");

            // we 'ready' the input reader
            input = new DataInputStream(socket.getInputStream());

            // and the output that is connected to the Socket
            out = new DataOutputStream(socket.getOutputStream());
        } catch(IOException u)
        {
            System.out.println(u);
        }
    }
    public void recebe() throws IOException {
        while (true){
            try {
                line=input.readLine();
                System.out.println(line);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    public static void main(String args[])
    {
        Client2 client = new Client2("localhost", 2222);
    }
}
