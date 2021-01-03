import com.sun.xml.internal.ws.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ArticleList implements Runnable {
    private ArrayList<Article> articles;
    private String fileName;

    public ArticleList(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        int countLine = 0;
        articles = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.UTF_8)) {
            String line = br.readLine();
            while (line != null){
                countLine++;
                if (countLine == 1){
                    line = br.readLine();
                }
                String[] attributes = line.split(",");
                if (attributes.length != 6){
                    line = br.readLine();
                    continue;
                }
                Article article = createArticle(attributes);
                articles.add(article);
                line = br.readLine();
            }
//            getAll();
//            System.out.println(articles.size());
            ReportList.createReports(articles);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    private Article createArticle(String[] attributes) {
        int id = 0;
        String source_id = "";
        String source_name = "";
        String title, content = "";
        LocalDateTime published_at;
        id = Integer.parseInt(attributes[0]);
        source_id = attributes[1];
        source_name = attributes[2];
        title = attributes[3];
        content = attributes[4];
        if (attributes[5].contains(".")){
            attributes[5] = attributes[5].split("\\.")[0];
        }else if (attributes[5].contains("+")){
            attributes[5] = attributes[5].split("\\+")[0];
        }
        else {
            attributes[5] = attributes[5].split("Z")[0];
        }
        attributes[5] = attributes[5].replaceAll("T", " ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.S");
        //DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
//        if (attributes[5].length() > 24){
//            published_at = LocalDateTime.parse(attributes[5], formatter3);
//        }
//        else
//        if(attributes[5].length() > 20){
//            published_at = LocalDateTime.parse(attributes[5], formatter2);
//        }else
        published_at = LocalDateTime.parse(attributes[5], formatter);
//        System.out.println(published_at);
        return new Article(id,source_id,source_name,title,content,published_at);
    }
    public void getAll(){
        for (Article a:
             articles) {
            System.out.println(a);
        }
    }
    public ArrayList<Article> getArticles() {
        return articles;
    }
}
