import java.time.LocalDateTime;
import java.util.ArrayList;

public class Report {
    String name, id;
    long all_content_length;
    int avg_content_length;
    int numOfArticles;
    LocalDateTime published_from, published_to;

    public Report(String name, String id, int all_content_length, LocalDateTime published_from, LocalDateTime published_to) {
        this.name = name;
        this.id = id;
        this.all_content_length = all_content_length;
        this.published_from = published_from;
        this.published_to = published_to;
        numOfArticles = 1;
    }
    public void setAvgContentLength(){
        avg_content_length = (int) (all_content_length/numOfArticles);
    }

    @Override
    public String toString() {
        return "Report{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", avg_content_length=" + avg_content_length +
                ", published_from=" + published_from +
                ", published_to=" + published_to +
                '}';
    }
}
