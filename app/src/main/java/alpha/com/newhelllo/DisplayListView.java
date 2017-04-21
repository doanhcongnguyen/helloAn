package alpha.com.newhelllo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.adapter.Contact;
import com.adapter.ContactAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String JSON_STRING;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactAdapter contactAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        contactAdapter = new ContactAdapter(this, R.layout.row_layout);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(contactAdapter);

        JSON_STRING = getIntent().getExtras().getString(MainActivity.JSON_DATA);
        String name, email, contact, password;

        try {
            jsonObject = new JSONObject(JSON_STRING);
            int count = 0;
            jsonArray = jsonObject.getJSONArray("server_response");
            while (count < jsonArray.length()) {

                JSONObject obj = jsonArray.getJSONObject(count);
                name = obj.getString("name");
                email = obj.getString("email");
                contact = obj.getString("contact");
                password = obj.getString("password");

                Contact contacts = new Contact(name, email, contact, password);
                contactAdapter.add(contacts);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
