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
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) {
        String serverIP = "127.0.0.1"; // IP server (localhost)
        int port = 5000;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan path file yang akan dikirim: ");
        String filePath = scanner.nextLine();
        scanner.close();

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("File tidak ditemukan!");
            return;
        }

        try (Socket socket = new Socket(serverIP, port)) {
            System.out.println("Terhubung ke server!");

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            FileInputStream fis = new FileInputStream(file);

            dos.writeUTF(file.getName());

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                dos.write(buffer, 0, bytesRead);
            }

            fis.close();
            dos.close();
            socket.close();

            System.out.println("File berhasil dikirim!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
