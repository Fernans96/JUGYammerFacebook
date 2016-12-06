package Yammer;

import com.sun.istack.internal.Nullable;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by quent on 02/12/2016.
 */
public class Client {
    private String _Token;
    static private final String _ClientID = "OlqGsVBBuJfcVlzR1H1w";
    static private final String _ClientSecret = "f55Yu0cxZUXwMkvn2Ao4J3Y7hOTJWoRpmLGR99ob58";
    static private final String _AuthTokenLink = "https://www.yammer.com/oauth2/access_token";
    private OkHttpClient _client = new OkHttpClient();
    public Message message = new Message();
    private AccesToken token = null;

    public static String getAuthLink() {
        return "https://www.yammer.com/epitech.eu/oauth2/authorize?client_id=" + _ClientID + "&response_type=code";
    }

    private Request createGetRequest(String URL) {
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("Authorization", "Bearer " + token.getToken())
                .get()
                .build();
        return request;
    }

    private Request createPostRequest(String URL, RequestBody form) {
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("Authorization", "Bearer " + token.getToken())
                .post(form)
                .build();
        return request;
    }

    public class Message {
        static private final String _MessageLink = "https://www.yammer.com/api/v1/messages.json";
        static private final String _My_feedLink = "https://www.yammer.com/api/v1/messages/my_feed.json";
        static private final String _AlgoLink = "https://www.yammer.com/api/v1/messages/algo.json";
        static private final String _FollowingLink = "https://www.yammer.com/api/v1/messages/following.json";
        static private final String _SentLink = "https://www.yammer.com/api/v1/messages/sent.json";
        static private final String _PrivateLink = "https://www.yammer.com/api/v1/messages/private.json";
        static private final String _ReceivedLink = "https://www.yammer.com/api/v1/messages/received.json";

        private String getMessages(@Nullable Integer older_than, @Nullable Integer newer_than, @Nullable Boolean threaded, @Nullable Integer limit, String link) throws IOException, JSONException {
            link += (older_than != null) ? "?older_than=" + older_than : "";
            link += (newer_than != null) ? ((older_than != null) ? "&" : "?") + "newer_than=" + older_than : "";
            link += (threaded != null) ? ((older_than != null || newer_than != null) ? "&" : "?") + "threaded=" + threaded : "";
            link += (limit != null) ? ((older_than != null || newer_than != null || threaded != null) ? "&" : "?") + "limit=" + limit : "";

            Request request = createGetRequest(link);
            Response response = _client.newCall(request).execute();
            JSONObject obj = new JSONObject(response.body().string());
            return obj.getJSONArray("messages").toString();
        }

        public String get(@Nullable Integer older_than, @Nullable Integer newer_than, @Nullable Boolean threaded, @Nullable Integer limit) {
            try {
                return getMessages(older_than, newer_than, threaded, limit, _MessageLink);
            } catch (IOException e) {
                return "[]";
            } catch (JSONException e) {
                return "[]";
            }
        }

        public String getMyFeed(@Nullable Integer older_than, @Nullable Integer newer_than, @Nullable Boolean threaded, @Nullable Integer limit) {
            try {
                return getMessages(older_than, newer_than, threaded, limit, _My_feedLink);
            } catch (IOException e) {
                return "[]";
            } catch (JSONException e) {
                return "[]";
            }
        }

        public String getAlgo(@Nullable Integer older_than, @Nullable Integer newer_than, @Nullable Boolean threaded, @Nullable Integer limit) {
            try {
                return getMessages(older_than, newer_than, threaded, limit, _AlgoLink);
            } catch (IOException e) {
                return "[]";
            } catch (JSONException e) {
                return "[]";
            }
        }

        public String getFollowing(@Nullable Integer older_than, @Nullable Integer newer_than, @Nullable Boolean threaded, @Nullable Integer limit) {
            try {
                return getMessages(older_than, newer_than, threaded, limit, _FollowingLink);
            } catch (IOException e) {
                return "[]";
            } catch (JSONException e) {
                return "[]";
            }
        }

        public String getSent(@Nullable Integer older_than, @Nullable Integer newer_than, @Nullable Boolean threaded, @Nullable Integer limit) {
            try {
                return getMessages(older_than, newer_than, threaded, limit, _SentLink);
            } catch (IOException e) {
                return "[]";
            } catch (JSONException e) {
                return "[]";
            }
        }

        public String getPrivate(@Nullable Integer older_than, @Nullable Integer newer_than, @Nullable Boolean threaded, @Nullable Integer limit) {
            try {
                return getMessages(older_than, newer_than, threaded, limit, _PrivateLink);
            } catch (IOException e) {
                return "[]";
            } catch (JSONException e) {
                return "[]";
            }
        }

        public String getReceived(@Nullable Integer older_than, @Nullable Integer newer_than, @Nullable Boolean threaded, @Nullable Integer limit) {
            try {
                return getMessages(older_than, newer_than, threaded, limit, _ReceivedLink);
            } catch (IOException e) {
                return "[]";
            } catch (JSONException e) {
                return "[]";
            }
        }

        public String Post(String body, @Nullable Integer replied_to_id, @Nullable JSONArray direct_to_user_ids, @Nullable Integer group_id, @Nullable Integer network_id) {
            RequestBody form = null;
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("body", body);
            if (replied_to_id != null)
                builder.add("replied_to_id", replied_to_id.toString());
            if (direct_to_user_ids != null)
                builder.add("direct_to_user_ids", direct_to_user_ids.toString());
            if (group_id != null)
                builder.add("group_id", group_id.toString());
            if (network_id != null)
                builder.add("network_id", network_id.toString());
            form = builder.build();
            Request request = createPostRequest(_MessageLink, form);
            try {
                Response response = _client.newCall(request).execute();
                String ret = response.body().string();
                JSONObject obj = new JSONObject(ret);
                return obj.toString();
            } catch (IOException e) {
                return "{}";
            } catch (JSONException e) {
                return "{}";
            }
        }
    }

    public boolean AuthClient(String code) {
        RequestBody formBody = new FormBody.Builder()
                .add("client_id", _ClientID)
                .add("client_secret", _ClientSecret)
                .add("code", code)
                .add("grant_type", "authorization_code")
                .build();

        Request request = new Request.Builder()
                .url(_AuthTokenLink)
                .post(formBody)
                .build();

        Response response = null;
        try {
            response = _client.newCall(request).execute();
            if (!response.isSuccessful())
                return false;
            String ret = response.body().string();
            JSONObject obj = new JSONObject(ret);
            token = new AccesToken(obj.getJSONObject("access_token"));
        } catch (IOException e) {
            token = null;
            return false;
        } catch (ParseException e) {
            token = null;
            return false;
        } catch (JSONException e) {
            token = null;
            return false;
        }
        System.out.println("Token : " + token.getToken());
        return true;
    }

}
