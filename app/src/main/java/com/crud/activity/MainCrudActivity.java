package com.crud.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.crud.db.AlphaDbHelper;
import com.crud.table.TablePerson;

import alpha.com.newhelllo.R;

public class MainCrudActivity extends AppCompatActivity {

    public final static String KEY_EXTRA_CONTACT_ID = "KEY_EXTRA_CONTACT_ID";
    public final static String KEY_EXTRA_STUDENT_ID = "KEY_EXTRA_STUDENT_ID";

    private ListView listView;
    AlphaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_crud);

        Button button = (Button) findViewById(R.id.addNew);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCrudActivity.this, CrudActivity.class);
                intent.putExtra(KEY_EXTRA_CONTACT_ID, 0);
                startActivity(intent);
            }
        });

        dbHelper = new AlphaDbHelper(this);
        Cursor persons = dbHelper.getAllPersons();
        String[] columns = new String[] {TablePerson.COLUMN_ID, TablePerson.COLUMN_NAME};
        int[] widgets = new int[] {R.id.personId, R.id.personName};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.person_info, persons, columns, widgets, 0);
        listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Cursor item = (Cursor) listView.getItemAtPosition(position);
                int personId = item.getInt(item.getColumnIndex(TablePerson.COLUMN_ID));
                Intent intent = new Intent(getApplicationContext(), CrudActivity.class);
                intent.putExtra(KEY_EXTRA_CONTACT_ID, personId);
                startActivity(intent);
            }
        });
    }
}
