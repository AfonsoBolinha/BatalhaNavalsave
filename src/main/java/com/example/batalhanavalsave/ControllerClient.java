/*package com.example.batalhanavalsave;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ControllerClient implements Initializable {
    private Server server;
    //private Server2 server2;
    boolean clienteCheck=false,serverCheck=false;
    //ControllerClient controllerClient=new ControllerClient();

    //FXMLLoader loader=new FXMLLoader(getClass().getResource("batalhaNavalClient.fxml"));
    //ControllerClient controllerClient=new ControllerClient();

    public static String estilo;
    public Label numT1,numT2,numT3,numT4,numT5;
    public int qtdT1=3,qtdT2=2,qtdT3=1,qtdT4=1,qtdT5=1;
    private Client client;
    @FXML
    Pane tier1Pane, tier2Pane, tier3Pane, tier4Pane, tier5Pane;
    boolean barco_tier1 = false;
    boolean barco_tier2 = false;
    boolean barco_tier3 = false;
    boolean barco_tier4 = false;
    boolean barco_tier5 = false;
    boolean started = false;
    static boolean rodado=false;
    static int rotated=0;
    Timeline timeline;
    LocalTime time = LocalTime.parse("00:00");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm:ss");

    public GridPane campoPlayer;

    public Pane pane11,pane21,pane31,pane41,pane51,pane61,pane71,pane81,pane91,pane101,pane12,pane22,pane32,pane42,pane52,pane62,pane72,pane82,pane92,pane102;
    public Pane pane13,pane23,pane33,pane43,pane53,pane63,pane73,pane83,pane93,pane103,pane14,pane24,pane34,pane44,pane54,pane64,pane74,pane84,pane94,pane104;
    public Pane pane15,pane25,pane35,pane45,pane55,pane65,pane75,pane85,pane95,pane105,pane16,pane26,pane36,pane46,pane56,pane66,pane76,pane86,pane96,pane106;
    public Pane pane17,pane27,pane37,pane47,pane57,pane67,pane77,pane87,pane97,pane107,pane18,pane28,pane38,pane48,pane58,pane68,pane78,pane88,pane98,pane108;
    public Pane pane19,pane29,pane39,pane49,pane59,pane69,pane79,pane89,pane99,pane109,pane110,pane210,pane310,pane410,pane510,pane610,pane710,pane810,pane910,pane1010;

    public Pane[][] paneArr=new Pane[10][10];



    public Label timer;
    //Barcos
    public Button tier1, tier2, tier3, tier4, tier5;

    //Inimigos
    public Button e11, e21, e31, e41, e51, e61, e71, e81, e91, e101, e12, e22, e32, e42, e52, e62, e72, e82, e92, e102;
    public Button e13, e23, e33, e43, e53, e63, e73, e83, e93, e103, e14, e24, e34, e44, e54, e64, e74, e84, e94, e104;
    public Button e15, e25, e35, e45, e55, e65, e75, e85, e95, e105, e16, e26, e36, e46, e56, e66, e76, e86, e96, e106;
    public Button e17, e27, e37, e47, e57, e67, e77, e87, e97, e107, e18, e28, e38, e48, e58, e68, e78, e88, e98, e108;
    public Button e19, e29, e39, e49, e59, e69, e79, e89, e99, e109, e110, e210, e310, e410, e510, e610, e710, e810, e910, e1010;
    //Array Inimigos
    public Button[][] botoesEnimigo;
    //Player
    public Button y11, y21, y31, y41, y51, y61, y71, y81, y91, y101, y12, y22, y32, y42, y52, y62, y72, y82, y92, y102;
    public Button y13, y23, y33, y43, y53, y63, y73, y83, y93, y103, y14, y24, y34, y44, y54, y64, y74, y84, y94, y104;
    public Button y15, y25, y35, y45, y55, y65, y75, y85, y95, y105, y16, y26, y36, y46, y56, y66, y76, y86, y96, y106;
    public Button y17, y27, y37, y47, y57, y67, y77, y87, y97, y107, y18, y28, y38, y48, y58, y68, y78, y88, y98, y108;
    public Button y19, y29, y39, y49, y59, y69, y79, y89, y99, y109, y110, y210, y310, y410, y510, y610, y710, y810, y910, y1010;
    //Array player
    public Button[][] botoesPlayer;

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        timeline = new Timeline(new KeyFrame(Duration.millis(1000), ae -> incrementTime()));
        timeline.setCycleCount(Animation.INDEFINITE);

        botoesEnimigo = new Button[][]{{e11, e12, e13, e14, e15, e16, e17, e18, e19, e110},
                {e21, e22, e23, e24, e25, e26, e27, e28, e29, e210},
                {e31, e32, e33, e34, e35, e36, e37, e38, e39, e310},
                {e41, e42, e43, e44, e45, e46, e47, e48, e49, e410},
                {e51, e52, e53, e54, e55, e56, e57, e58, e59, e510},
                {e61, e62, e63, e64, e65, e66, e67, e68, e69, e610},
                {e71, e72, e73, e74, e75, e76, e77, e78, e79, e710},
                {e81, e82, e83, e84, e85, e86, e87, e88, e89, e810},
                {e91, e92, e93, e94, e95, e96, e97, e98, e99, e910},
                {e101, e102, e103, e104, e105, e106, e107, e108, e109, e1010}};

        botoesPlayer = new Button[][]{{y11, y12, y13, y14, y15, y16, y17, y18, y19, y110},
                {y21, y22, y23, y24, y25, y26, y27, y28, y29, y210},
                {y31, y32, y33, y34, y35, y36, y37, y38, y39, y310},
                {y41, y42, y43, y44, y45, y46, y47, y48, y49, y410},
                {y51, y52, y53, y54, y55, y56, y57, y58, y59, y510},
                {y61, y62, y63, y64, y65, y66, y67, y68, y69, y610},
                {y71, y72, y73, y74, y75, y76, y77, y78, y79, y710},
                {y81, y82, y83, y84, y85, y86, y87, y88, y89, y810},
                {y91, y92, y93, y94, y95, y96, y97, y98, y99, y910},
                {y101, y102, y103, y104, y105, y106, y107, y108, y109, y1010}};

        tier1.hoverProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object number, Object t1) {
                mouseOver(tier1);
            }
        });

        tier2.hoverProperty().addListener((observableValue, number, t1) -> mouseOver(tier2));
        tier3.hoverProperty().addListener((observableValue, number, t1) -> mouseOver(tier3));
        tier4.hoverProperty().addListener((observableValue, number, t1) -> mouseOver(tier4));
        tier5.hoverProperty().addListener((observableValue, number, t1) -> mouseOver(tier5));

        for (int a = 0; a < 10; a++) {
            for (int b = 0; b < 10; b++) {

                int finalA = a;
                int finalB = b;
                botoesEnimigo[a][b].hoverProperty().addListener((observableValue, number, t1) -> mouseOver(botoesEnimigo[finalA][finalB]));
            }
        }
        for (int c = 0; c < 10; c++) {
            for (int d = 0; d < 10; d++) {

                int finalC = c;
                int finalD = d;
                botoesPlayer[c][d].hoverProperty().addListener((observableValue, number, t1) -> mouseOverPlayer(botoesPlayer[finalC][finalD],finalC,finalD));
            }
        }

        for (int a=0; a<10;a++){
            for (int b=0;b<10;b++){
                int finalA=a;
                int finalB=b;

                botoesEnimigo[finalA][finalB].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("CLICADO ");
                        try {
                            atacar(finalA,finalB);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        }

        for (int e=0;e<10;e++){
            for (int f=0;f<10;f++){

                int finalE=e;
                int finalD=f;
                botoesPlayer[finalE][finalD].setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            colocar(botoesPlayer[finalE][finalD],finalE,finalD);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
            }
        }/*
        if (clienteCheck){
            try {
                client.getJogada();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/

//    }
//
//
//
//    //RELOGIO INICIO
//    private void incrementTime() {
//        time = time.plusSeconds(1);
//        timer.setText(time.format(dtf));
//    }
//
//    private void startTimer() {
//        timeline.play();
//    }
//
//    private void pauseTimer() {
//        timeline.pause();
//    }
//
//    public void start() {
//        if (started) {
//            pauseTimer();
//            started = false;
//        } else {
//            startTimer();
//            started = true;
//        }
//    }
////RELOGIO FIM
//
//
//
//    //Criar o server
//    public void criarServer() throws IOException {
//        serverCheck=true;
//        try{
//            server=new Server(new ServerSocket(2222));
//        }catch (IOException e){
//            e.printStackTrace();
//            System.out.println("Erro a criar server");
//        }
//        server.getJogada(paneArr);
//    }
//
//    public static void receiveGame(int posx,int posy,Pane [][]paneArr) throws IOException {
//        String recebido;
//        System.out.println("\n RECEIVED \n");
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    recebeAtaque(posx,posy,paneArr);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//
//    }
//
//    public void criarCliente() throws IOException {
//
//        clienteCheck=true;
//        try{
//            client = new Client(new Socket("localhost",2222));
//            System.out.println("Conectado ao server");
//        }catch (IOException e){
//            System.out.println("Erro a conectar ao servidor");
//            e.printStackTrace();
//        }
//
//        client.getJogada(paneArr);
//    }
//
//
//    //ROTACAO de barco
//    public static void rotate(){
        //O rodado significa que a peça está ou no eixo X ou no eixo Y
        //rodado= !rodado;
        //Rotated tem 4 posiçoes, podendo ser eixo X para direita(1) ou esquerda(2) e eixo Y cima(3) ou baixo(0)
        /*if (rotated<=3){
            rotated++;
        }else {
            rotated=0;
        }
    }*/

    //Previsualização Campo inimigo
   /* public void mouseOver(Button but) {
        if (but.isHover()) {
            but.setStyle("-fx-background-color: #ade3f0");

        } else {
            but.setStyle("-fx-background-color: rgba(0,0,0,0);");
        }
    }*/

    //BANDEIRA marcador de possivel inimigo
/*
    public void bandeira(MouseEvent butao) {
        e11.setStyle("-fx-background-color: rgba(0,0,0,0)");

        if (e11.getText().equals("") && butao.getButton() == MouseButton.SECONDARY) {
            e11.setText("?");
        } else {
            e11.setText("");
        }
    }*/

    //Tratamento da seleção e coloraçao dos barcos
/*
    public boolean setFalse(Pane pane) {
        tier1Pane.setStyle("-fx-background-color: #ffffff");
        tier2Pane.setStyle("-fx-background-color: #ffffff");
        tier3Pane.setStyle("-fx-background-color: #ffffff");
        tier4Pane.setStyle("-fx-background-color: #ffffff");
        tier5Pane.setStyle("-fx-background-color: #ffffff");
        barco_tier1 = false;
        barco_tier2 = false;
        barco_tier3 = false;
        barco_tier4 = false;
        barco_tier5 = false;


        pane.setStyle("-fx-background-color: #ade3f0");
        return true;
    }

    public void setTier1() {
        barco_tier1 = setFalse(tier1Pane);
    }

    public void setTier2() {
        barco_tier2 = setFalse(tier2Pane);
    }

    public void setTier3() {
        barco_tier3 = setFalse(tier3Pane);
    }

    public void setTier4() {
        barco_tier4 = setFalse(tier4Pane);
    }

    public void setTier5() {
        barco_tier5 = setFalse(tier5Pane);
    }*/
//Fim do tratamento da seleção e coloraçao dos barcos


    //Previsualização de colocação de barcos
/*
    public void mouseOverPlayer(Button but,int cord1,int cord2) {
        try{
            if (!but.isHover()) {
                for (int c = 0; c < 10; c++) {
                    for (int d = 0; d < 10; d++) {
                        botoesPlayer[c][d].setStyle("-fx-background-color: rgba(0,0,0,0)");
                    }
                }
            } else {
                if (barco_tier1){
                    but.setStyle("-fx-background-color: #56A5EE");
                } else if (barco_tier2) {
                    but.setStyle("-fx-background-color: #008000");
                    if (rodado){
                        botoesPlayer[cord1+1][cord2].setStyle("-fx-background-color: #008000");
                    }else {
                        botoesPlayer[cord1][cord2+1].setStyle("-fx-background-color: #008000");
                    }
                } else if (barco_tier3) {
                    but.setStyle("-fx-background-color: #ffd700");
                    if (rodado){
                        botoesPlayer[cord1+1][cord2].setStyle("-fx-background-color: #ffd700");
                        botoesPlayer[cord1-1][cord2].setStyle("-fx-background-color: #ffd700");
                    }else{
                        botoesPlayer[cord1][cord2+1].setStyle("-fx-background-color: #ffd700");
                        botoesPlayer[cord1][cord2-1].setStyle("-fx-background-color: #ffd700");
                    }
                } else if (barco_tier4) {
                    but.setStyle("-fx-background-color: #050f42");
                    if (rodado){
                        botoesPlayer[cord1+1][cord2].setStyle("-fx-background-color: #050f42");
                        botoesPlayer[cord1+2][cord2].setStyle("-fx-background-color: #050f42");
                        botoesPlayer[cord1+3][cord2].setStyle("-fx-background-color: #050f42");
                    }else{
                        botoesPlayer[cord1][cord2+1].setStyle("-fx-background-color: #050f42");
                        botoesPlayer[cord1][cord2+2].setStyle("-fx-background-color: #050f42");
                        botoesPlayer[cord1][cord2+3].setStyle("-fx-background-color: #050f42");
                    }
                } else if (barco_tier5) {
                    but.setStyle("-fx-background-color: #87888a");
                    if (rotated==0){
                        botoesPlayer[cord1][cord2+1].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1][cord2-1].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1+1][cord2].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1+2][cord2].setStyle("-fx-background-color: #87888a");
                    } else if (rotated==1) {
                        botoesPlayer[cord1-1][cord2].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1+1][cord2].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1][cord2+1].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1][cord2+2].setStyle("-fx-background-color: #87888a");
                    } else if (rotated==2) {
                        botoesPlayer[cord1][cord2+1].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1][cord2-1].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1-1][cord2].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1-2][cord2].setStyle("-fx-background-color: #87888a");
                    }else {
                        botoesPlayer[cord1-1][cord2].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1+1][cord2].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1][cord2-1].setStyle("-fx-background-color: #87888a");
                        botoesPlayer[cord1][cord2-2].setStyle("-fx-background-color: #87888a");
                    }

                } else{
                    but.setStyle("-fx-background-color: #ade3f0");
                }
            }
        }catch (Exception ignored){

        }
    }*/
//Fim de previsualização de colocação de barcos
/*
    public void colocar(Button but,int posx,int posy) throws IOException {


        if (barco_tier1){
            if (qtdT1>0){
                System.out.println(paneArr2(posx,posy));
                paneArr2(posx,posy).setStyle("-fx-background-color: #56a5ee");
                qtdT1--;
                numT1.setText(""+qtdT1);

            }else{
                tier1.setDisable(true);
            }
        }
        if (barco_tier2){
            if (qtdT2>0){
                if (rodado){
                    paneArr2(posx,posy).setStyle("-fx-background-color: #008000");
                    paneArr2(posx+1,posy).setStyle("-fx-background-color: #008000");
                }else {
                    paneArr2(posx,posy).setStyle("-fx-background-color: #008000");
                    paneArr2(posx,posy+1).setStyle("-fx-background-color: #008000");
                }
                qtdT2--;
                numT2.setText(""+qtdT2);
            }else {
                tier2.setDisable(true);
            }
        }
        if (barco_tier3){
            if (qtdT3>0){
                if (rodado){
                    paneArr2(posx,posy).setStyle("-fx-background-color: #ffd700");
                    paneArr2(posx+1,posy).setStyle("-fx-background-color: #ffd700");
                    paneArr2(posx-1,posy).setStyle("-fx-background-color: #ffd700");
                }else{
                    paneArr2(posx,posy).setStyle("-fx-background-color: #ffd700");
                    paneArr2(posx,posy+1).setStyle("-fx-background-color: #ffd700");
                    paneArr2(posx,posy-1).setStyle("-fx-background-color: #ffd700");
                }
                qtdT3--;
                numT3.setText(""+qtdT3);
            }else{
                tier3.setDisable(true);
            }
        }
        if (barco_tier4){
            if (qtdT4>0){
                if (rodado){
                    paneArr2(posx,posy).setStyle("-fx-background-color: #050f42");
                    paneArr2(posx+1,posy).setStyle("-fx-background-color: #050f42");
                    paneArr2(posx+2,posy).setStyle("-fx-background-color: #050f42");
                    paneArr2(posx+3,posy).setStyle("-fx-background-color: #050f42");
                }else{
                    paneArr2(posx,posy).setStyle("-fx-background-color: #050f42");
                    paneArr2(posx,posy+1).setStyle("-fx-background-color: #050f42");
                    paneArr2(posx,posy+2).setStyle("-fx-background-color: #050f42");
                    paneArr2(posx,posy+3).setStyle("-fx-background-color: #050f42");
                }
                qtdT4--;
                numT4.setText(""+qtdT4);
            }else{
                tier4.setDisable(true);
            }
        }
        if (barco_tier5){
            if (qtdT5>0){
                if (rotated==0){
                    paneArr2(posx,posy).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx,posy+1).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx,posy-1).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx+1,posy).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx+2,posy).setStyle("-fx-background-color: #87888a");
                } else if (rotated==1) {
                    paneArr2(posx,posy).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx-1,posy).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx+1,posy).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx,posy+1).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx,posy+2).setStyle("-fx-background-color: #87888a");
                } else if (rotated==2) {
                    paneArr2(posx,posy).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx,posy+1).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx,posy-1).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx-1,posy).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx-2,posy).setStyle("-fx-background-color: #87888a");
                }else {
                    paneArr2(posx,posy).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx-1,posy).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx+1,posy).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx,posy-1).setStyle("-fx-background-color: #87888a");
                    paneArr2(posx,posy-2).setStyle("-fx-background-color: #87888a");
                }
                qtdT5--;
                numT5.setText(""+qtdT5);
            }else {
                tier5.setDisable(true);
            }
        }
    }
    *//*
    public void servidor(){
        Server2 server2= new Server2(2222);
    }
    public void cliente() throws IOException {
        Client2 client2= new Client2("localhost",2222);
        client2.recebe();
    }*/
/*
    public void atacar(int posx,int posy) throws IOException {
        //controllerClient.paneArr[posx][posy].setStyle("-fx-background-color: #87888a");
        if (serverCheck){
            server.sendJogada(posx,posy);
        }else {
            client.sendJogada(posx,posy);
        }



    }
    public Pane panela(int posx,int posy){

        return paneArr[posx][posy];
    }
    public static void  recebeAtaque(int posx, int posy,Pane [][]paneArr) throws IOException{

        enviaEstilo(posx,posy,paneArr).setStyle("-fx-background-color: #ff0000");
    }

    private Pane  paneArr2(int posx,int posy){
        paneArr = new Pane[][]{{pane11,pane12,pane13,pane14,pane15,pane16,pane17,pane18,pane19,pane110},
                {pane21,pane22,pane23,pane24,pane25,pane26,pane27,pane28,pane29,pane210},
                {pane31,pane32,pane33,pane34,pane35,pane36,pane37,pane38,pane39,pane310},
                {pane41,pane42,pane43,pane44,pane45,pane46,pane47,pane48,pane49,pane410},
                {pane51,pane52,pane53,pane54,pane55,pane56,pane57,pane58,pane59,pane510},
                {pane61,pane62,pane63,pane64,pane65,pane66,pane67,pane68,pane69,pane610},
                {pane71,pane72,pane73,pane74,pane75,pane76,pane77,pane78,pane79,pane710},
                {pane81,pane82,pane83,pane84,pane85,pane86,pane87,pane88,pane89,pane810},
                {pane91,pane92,pane93,pane94,pane95,pane96,pane97,pane98,pane99,pane910},
                {pane101,pane102,pane103,pane104,pane105,pane106,pane107,pane108,pane109,pane1010}};
        return paneArr[posx][posy];
    }

    public static Pane enviaEstilo(int x,int y,Pane [][]paneArr) throws IOException {

        Pane pane= paneArr[x][y];


        return pane;
    }



}
*/