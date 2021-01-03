import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        ArrayList<ArticleList> list = new ArrayList<>();
        ArrayList<Thread> threads = new ArrayList<>();
        String[] paths = {
                "C:\\Users\\Sungat Kaparov\\Desktop\\Lessons\\Java\\csv\\part1.csv",
                "C:\\Users\\Sungat Kaparov\\Desktop\\Lessons\\Java\\csv\\part2.csv",
                "C:\\Users\\Sungat Kaparov\\Desktop\\Lessons\\Java\\csv\\part3.csv",
                "C:\\Users\\Sungat Kaparov\\Desktop\\Lessons\\Java\\csv\\part4.csv"
        };
        for (int i = 0; i < paths.length; i++) {
            list.add(new ArticleList(paths[i]));
        }
        for (int i = 0; i < list.size(); i++) {
            threads.add(i, new Thread(list.get(i)));
            threads.get(i).start();
        }
        for (int i = 0; i < list.size(); i++) {
            threads.get(i).join();
        }
        ReportList.getAllInfo();
        PrintWriter writer = new PrintWriter("C:\\Users\\Sungat Kaparov\\Desktop\\Lessons\\Java\\csv\\report.csv");
        writer.write(ReportList.getAllInfo());
        writer.flush();
        writer.close();
    }
}
