package myapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static void writeToSocket(Socket socket, Float mean, Float standardDeviation, String name, String email) throws IOException {
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);
        try {
            oos.writeUTF(name);
            oos.writeUTF(email);
            oos.writeFloat(mean);
            oos.writeFloat(standardDeviation);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public static ArrayList<Float> readFromSocket(Socket socket) throws IOException {
        ArrayList<Float> data = new ArrayList<Float>();
        InputStream is = socket.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(is);

        // Print float values
        String thisFloat = ois.readUTF();
        System.out.println(thisFloat);

        String[] floats = thisFloat.split(",");
        for (String currFloat: floats) {
            data.add(Float.parseFloat(currFloat));
        }
        return data;
    }
    public static void readAndWriteToSocket(Socket socket) throws IOException {
        ArrayList<Float> data = Main.readFromSocket(socket);
        Float mean = Calculations.calculateMean(data);
        Float standardDeviation = Calculations.calculateStandardDeviation(data);
        String name = "Lim Yunhui Trixie";
        String email = "trixielyh@gmail.com";
        Main.writeToSocket(socket, mean, standardDeviation, name, email);
        return;
    }

    public static void main(String[] args) throws IOException {
        String serverHost = args[0];
        String serverPort = args[1];
        Integer castedServerPort = Integer.parseInt(serverPort);

        Socket socket = new Socket(serverHost, castedServerPort);
        socket.setSoTimeout(5000);
        Main.readAndWriteToSocket(socket);
        socket.close();
    }
}

            