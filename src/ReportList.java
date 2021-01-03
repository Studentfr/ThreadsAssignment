import java.util.ArrayList;

public class ReportList {
    private static final ArrayList<Report> reports = new ArrayList<>();
    public static void createReports(ArrayList<Article> articles){
        synchronized (reports) {
            boolean isFound;
            for (int i = 0; i < articles.size(); i++) {
                isFound = false;
                for (int j = 0; j < reports.size(); j++) {
                    if (reports.get(j).id.equals(articles.get(i).source_id)) {
                        isFound = true;
                        reports.get(j).numOfArticles++;
                        reports.get(j).all_content_length += articles.get(i).content.length();
                        if (articles.get(i).published_at.isBefore(reports.get(j).published_from)) {
                            reports.get(j).published_from = articles.get(i).published_at;
                        }
                        if (articles.get(i).published_at.isAfter(reports.get(j).published_to)) {
                            reports.get(j).published_to = articles.get(i).published_at;
                        }
                    }
                }
                if (!isFound) {
                    reports.add(new Report(articles.get(i).source_name, articles.get(i).source_id, articles.get(i).content.length(), articles.get(i).published_at, articles.get(i).published_at));
                }
            }
            for (int i = 0; i < reports.size(); i++) {
                reports.get(i).setAvgContentLength();
            }
        }
    }
    public static String getAllInfo(){
        String info = "";
        for (int i = 0; i < reports.size(); i++) {
            info += reports.get(i);
            info += "\n";
        }
        return info;
    }

}
