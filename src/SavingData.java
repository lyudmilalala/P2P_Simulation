import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SavingData implements Runnable {
    private File file;
    private PrintWriter printWriter;
    private ArrayList<Message> messages;

    public SavingData(ArrayList<Message> messages) {
        file = new File("Data.txt");
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
        for (int i = 0; i < messages.size(); i++) {
            printWriter.println(messages.get(i).toString());

        }
        printWriter.close();


    }
}
