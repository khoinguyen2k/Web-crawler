package via_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CrawlerViaAPI {
    public static String getResponse(int studentId) throws IOException {
        URL url = new URL("https://hoctap.coccoc.com/composer/proxyapi2/graduation_grades/score_search?code="
                + Integer.toString(studentId)
                + "&nam=2023");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        BufferedReader reader;
        String line;
        StringBuilder response = new StringBuilder();

        int status = connection.getResponseCode();
        if (status > 299)
            System.out.print("exit");
        else {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null)
                response.append(line);
        }

        return response.toString();
    }

    public static void main(String[] args) throws IOException {

//      https://hoctap.coccoc.com/composer/proxyapi2/graduation_grades/score_search?code=21005681&nam=2023

        for (int studentId = 21005681; studentId < 21005681 + 100; studentId++) {
           String rawText = getResponse(studentId);
           Student student = new Student(rawText);
           System.out.println(student);
        }

    }
}
