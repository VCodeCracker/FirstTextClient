package com.example.demo;

import javafx.application.Platform;

import javax.swing.tree.DefaultTreeCellEditor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client implements Runnable {

    private Thread thread; // Thread-Objekt für die Client-Instanz
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean done;
    private Controller controller;
    private boolean isInterrupted = false;

    Client(Controller controller) {
        done = false;
        this.controller = controller;
        //controller.getMessageOutput().setText("hello");

        // Thread-Objekt für diese Instanz erstellen und starten
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            client = new Socket("localhost", 9999);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));


            String inMessage = "";
            while (!done && (inMessage = in.readLine()) != null) {
                if (isInterrupted) {
                    throw new InterruptedException();
                }
                System.out.println(decoder(inMessage));

                // Update UI in JavaFX Thread
                String decodedMessage = decoder(inMessage);
                controller.getMessageOutput().setText(controller.getMessageOutput().getText() + "\n" + insert(decodedMessage,"\n",64));
               // Platform.runLater(() -> controller.getMessageOutput().setText(decodedMessage));

            }
        } catch (InterruptedException e) {
            System.out.println("Program was interrupted");
        } catch (Exception e) {
            shutdown();
        }
    }

    public void shutdown() {
        done = true;
        isInterrupted = true;
        try {
            sendMessage("quit");
            if (in != null) in.close();
            if (out != null) out.close();
            if (client != null && !client.isClosed()) client.close();
        } catch (IOException e) {
            e.printStackTrace();  // handle the exception, e.g., log it
        }
    }



    public void sendMessage(String string) {
        if (out != null) {
            controller.getMessageOutput().setText(controller.getMessageOutput().getText() + "\n" + "me: " + string);
            String toSend = incoder(string);
            System.out.println("this will be send to the server: " + toSend +" that's the not crypted message:" + string);
            out.println(toSend);
        } else {
            System.err.println("PrintWriter 'out' is null. Cannot send message.");
        }
    }


    static String incoder(String message) {
        message = message.toLowerCase();
       // System.out.println("initial text " + message);
        Morse morse = new Morse();
        StringBuilder working = new StringBuilder();

        for (char a : message.toCharArray()) {
            working.append(Morse.letterToMorse(a));
            working.append("f");
        }
        //System.out.println("thats how the message looks after firs line incryption " + working.toString());

        String toReturn = Morse.secondLineCrypt(working.toString());
       // System.out.println("that will be returned"  + toReturn);
        return toReturn;
    }

    static String decoder(String message) {
        Morse morse = new Morse();
        String two = Morse.secondLineDecrypt(message);
        StringBuilder working = new StringBuilder();

        for (String a : two.split("f")) {
            working.append(Morse.morseToLetter(a));
        }

        return working.toString();
    }
    public static String insert(String text, String insert, int period) {
        Pattern p = Pattern.compile("(.{" + period + "})", Pattern.DOTALL);
        Matcher m = p.matcher(text);
        return m.replaceAll("$1" + insert);
    }
}
