package Modele.Messages;
import Modele.User.User;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HistoryWriter {
    public static int writeFile(String filename, String message){
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(message);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return 77;
        }
        return 0;
    }
}
