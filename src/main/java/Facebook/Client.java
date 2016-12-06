package Facebook;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

/**
 * Created by quent on 03/12/2016.
 */
public class Client {
    static private final String _ClientID = "1251794458175503";
    static private final String _SecretKey = "eaa2e8f372b4e4b4fd91772c83d8dc8b";
    static private final String _AccesTokenLink = "https://graph.facebook.com/v2.8/oauth/access_token";
    private OkHttpClient _client = new OkHttpClient();

    public static String getAuthLink() {
        return "https://www.facebook.com/v2.8/dialog/oauth?client_id=" + _ClientID + "&redirect_uri=" + "http%3A%2F%2F127.0.0.1%3A8080%2FFacebook%2FAuth";
    }

    public boolean AuthClient(String code) {
        Request request = new Request.Builder()
                .url(_AccesTokenLink
                        + "?client_id=" + _ClientID
                        + "&redirect_uri=http%3A%2F%2F127.0.0.1%3A8080%2FFacebook%2FAuth"
                        + "&client_secret=" + _SecretKey
                        + "&code=" + code)
                .get()
                .build();
        Response response = null;
        try {
            response = _client.newCall(request).execute();
            if (!response.isSuccessful())
                return false;
            String ret = response.body().string();
            JSONObject obj = new JSONObject(ret);
            System.out.println(obj.toString());
        } catch (IOException e) {
            return false;
        } catch (JSONException e) {
            return false;
        }
        return true;
    }
}
