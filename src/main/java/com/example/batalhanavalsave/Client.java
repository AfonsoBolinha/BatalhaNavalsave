package com.example.batalhanavalsave;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client(Socket socket) throws IOException {
        try{
            this.socket=socket;
            this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }catch (IOException e){
            e.printStackTrace();
            fechoGeral(socket,bufferedReader,bufferedWriter);
        }

    }


    public void teste(String mensagem) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    System.out.println("Aqui desespera, n se espera");
                    String teste= null;
                    try {
                        teste = bufferedReader.readLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);

                    }
                    System.out.println(teste);


                }
            }
        }).start();




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

    public void getJogada() throws IOException {
        int []posicaoI=new int[3];
        HelloController control = new HelloController();

        //min 34
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                //codigo
            }
        }).start();*/
        while (true){
            try{
                String jogada=bufferedReader.readLine();
                String []posicaoS=jogada.split(" ");

                posicaoI[0]=Integer.parseInt(posicaoS[0]);
                posicaoI[1]=Integer.parseInt(posicaoS[1]);

                control.recebeAtaque(posicaoI[0],posicaoI[1]);
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("Erro a receber coordenadas");
                fechoGeral(socket,bufferedReader,bufferedWriter);
            }
            break;
        }

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
