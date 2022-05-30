package com.example.batalhanavalsave;

import javafx.scene.layout.Pane;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Server(ServerSocket serverSocket){
        try {
            this.serverSocket=serverSocket;
            System.out.println("Server Socket Criado");
            this.socket=serverSocket.accept();
            this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            System.out.println("Erro a criar o servidor");
            e.printStackTrace();
        }
    }

    public void teste(String mensagem) throws IOException {
        bufferedWriter.write(mensagem);
        bufferedWriter.newLine();
    }
    public void fechoGeral(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try{
            if (bufferedReader != null){
                bufferedReader.close();
            }if (bufferedWriter != null){
                bufferedWriter.close();
            }if (socket!=null){
                socket.close();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getJogada(Pane[][]paneArr) throws IOException {
        int []posicaoI=new int[3];

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(socket.isConnected()){
                    try{
                        String jogada=bufferedReader.readLine();
                        String []posicaoS=jogada.split(" ");

                        posicaoI[0]=Integer.parseInt(posicaoS[0]);
                        posicaoI[1]=Integer.parseInt(posicaoS[1]);

                        HelloController.receiveGame(posicaoI[0],posicaoI[1],paneArr);
                    }catch (IOException e){
                        e.printStackTrace();
                        System.out.println("Erro a receber coordenadas");
                        fechoGeral(socket,bufferedReader,bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }

    public void sendJogada(int posx, int posy){
        String mensagem= posx+" "+posy;
        try {
            bufferedWriter.write(mensagem);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Erro a enviar mensagem");
            fechoGeral(socket,bufferedReader,bufferedWriter);
        }
    }
}
