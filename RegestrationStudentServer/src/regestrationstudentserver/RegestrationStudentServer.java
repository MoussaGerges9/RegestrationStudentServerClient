/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regestrationstudentserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegestrationStudentServer {

    ServerSocket ss;
    Socket s;
    ObjectOutputStream out;
    ObjectInputStream in;

    public RegestrationStudentServer() throws IOException {

        ss = new ServerSocket(9999);
        s = ss.accept();
        out = new ObjectOutputStream(s.getOutputStream());
        in = new ObjectInputStream(s.getInputStream());

    }

    public void ReciveMessage() throws IOException, ClassNotFoundException {
        String message;

        while (true) {
            message = (String) in.readObject();
            if (!message.isEmpty()) {
                String[] newMessageArr = message.split(",");
                for (String w : newMessageArr) {
                    System.out.println(w);
                }
                FileWriter fw = new FileWriter("StudentData.txt", true);
                fw.write("Name: " + newMessageArr[0] + ", Number: " + newMessageArr[1] + ", Department: " + newMessageArr[2]+"\n");
                System.out.println("Successfully saved in file");
                fw.close();
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        RegestrationStudentServer reg = new RegestrationStudentServer();
        reg.ReciveMessage();
    }

}
