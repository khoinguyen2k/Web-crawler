import org.json.JSONArray;
import org.json.JSONObject;
import via_api.CrawlerViaAPI;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        int id = 21099999;
        String rawText = CrawlerViaAPI.getResponse(id);
        JSONObject jsonObject = new JSONObject(rawText);

        JSONArray array = jsonObject.getJSONArray("proxyapi2");
        while (array.length() == 0) {
            id--;
            System.out.println("id=" + id);
            rawText = CrawlerViaAPI.getResponse(id);
            jsonObject = new JSONObject(rawText);
            array = jsonObject.getJSONArray("proxyapi2");
        }

        JSONObject object = array.getJSONObject(0);
        System.out.println(object);
        System.out.println(object.get("LichSu"));
        System.out.println(object.get("HoaHoc"));
        System.out.println(object.get("ListGroup"));

    }
}
