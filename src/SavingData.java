import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SavingData implements Runnable {
    private File file;
    private PrintWriter printWriter;
    private ArrayList<Message> messages;

    SavingData(int id , ArrayList<Message> messages) {
        file = new File(id+".txt");
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.messages = messages;

    }

    @Override
    public void run() {
        System.out.println("Start saving...");
        for (Message message:messages) {
            printWriter.println(message.toString());

        }
        printWriter.close();


    }
}
