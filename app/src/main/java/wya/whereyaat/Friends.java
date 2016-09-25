package wya.whereyaat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Friends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        String request = "/me/friends";
        Intent intent = getIntent();
        String jsonFriends = intent.getStringExtra("jsondata");
        JSONArray jsonArrayFriends;
        ArrayList<String> friends = new ArrayList<String>();
        try {
            jsonArrayFriends = new JSONArray(jsonFriends);
            for (int x = 0; x < jsonArrayFriends.length(); x++) {
                friends.add(jsonArrayFriends.getJSONObject(x).getString("name"));
            }
        }
        catch(JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_friends, friends);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

}
