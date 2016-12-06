package Yammer;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by quent on 02/12/2016.
 */
public class AccesToken {
    private long user_id;
    private long network_id;
    private String network_permalink;
    private String network_name;
    private String token;
    private Boolean view_members;
    private Boolean view_groups;
    private Boolean view_messages;
    private Boolean view_subscriptions;
    private Boolean modify_subscriptions;
    private Boolean modify_messages;
    private Boolean view_tags;
    private Date created_at;
    private Date authorized_at;

    public String getToken() {
        return token;
    }

    public AccesToken(JSONObject obj) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        user_id = obj.getLong("user_id");
        network_id = obj.getLong("network_id");
        network_permalink = obj.getString("network_permalink");
        network_name = obj.getString("network_name");
        token = obj.getString("token");
        view_members = obj.getBoolean("view_members");
        view_groups = obj.getBoolean("view_groups");
        view_messages = obj.getBoolean("view_messages");
        view_subscriptions = obj.getBoolean("view_subscriptions");
        modify_subscriptions = obj.getBoolean("modify_subscriptions");
        modify_messages = obj.getBoolean("modify_messages");
        view_tags = obj.getBoolean("view_tags");
        created_at = formatter.parse(obj.getString("created_at"));
        authorized_at = formatter.parse(obj.getString("authorized_at"));
    }
}
