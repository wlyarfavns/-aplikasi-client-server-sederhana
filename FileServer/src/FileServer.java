/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ronna
 */
import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server siap menerima file di port " + port + "...");

            Socket socket = serverSocket.accept();
            System.out.println("Client terhubung: " + socket.getInetAddress());

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String fileName = dis.readUTF();
            System.out.println("Menerima file: " + fileName);

            FileOutputStream fos = new FileOutputStream("received_" + fileName);
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = dis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            fos.close();
            dis.close();
            socket.close();

            System.out.println("File berhasil diterima dan disimpan sebagai 'received_" + fileName + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
