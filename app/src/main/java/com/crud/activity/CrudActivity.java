package com.crud.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.crud.db.AlphaDbHelper;
import com.crud.table.TablePerson;

import alpha.com.newhelllo.R;

public class CrudActivity extends AppCompatActivity implements View.OnClickListener {

    private AlphaDbHelper dbHelper;
    EditText nameEditText;
    EditText genderEditText;
    EditText ageEditText;

    Button saveButton, editButton, deleteButton;
    LinearLayout buttonLayout;

    int personId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        personId = getIntent().getIntExtra(MainCrudActivity.KEY_EXTRA_CONTACT_ID, 0);
        nameEditText = (EditText) findViewById(R.id.editTextName);
        genderEditText = (EditText) findViewById(R.id.editTextGender);
        ageEditText = (EditText) findViewById(R.id.editTextAge);

        saveButton = (Button) findViewById(R.id.saveButton);
        editButton = (Button) findViewById(R.id.editButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        saveButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        buttonLayout = (LinearLayout) findViewById(R.id.buttonLayout);

        dbHelper = new AlphaDbHelper(this);

        if (personId > 0) {
            saveButton.setVisibility(View.GONE);
            buttonLayout.setVisibility(View.VISIBLE);

            Cursor person = dbHelper.getPerson(personId);
            person.moveToFirst();
            String personName = person.getString(person.getColumnIndex(TablePerson.COLUMN_NAME));
            String personGender = person.getString(person.getColumnIndex(TablePerson.COLUMN_GENDER));
            int personAge = person.getInt(person.getColumnIndex(TablePerson.COLUMN_AGE));
            if (!person.isClosed()) {
                person.close();
            }

            nameEditText.setText(personName);
            nameEditText.setFocusable(false);
            nameEditText.setClickable(false);

            genderEditText.setText((CharSequence) personGender);
            genderEditText.setFocusable(false);
            genderEditText.setClickable(false);

            ageEditText.setText((CharSequence) (personAge + ""));
            ageEditText.setFocusable(false);
            ageEditText.setClickable(false);

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.saveButton:
                persistPerson();
                return;
            case R.id.editButton:
                doEditAction();
                return;
            case R.id.deleteButton:
                doDeleteAction();
                return;
        }
    }

    public void persistPerson() {
        String personName = nameEditText.getText().toString();
        String personGender = genderEditText.getText().toString();
        String personAge = ageEditText.getText().toString();
        if (personId > 0) {
            if (dbHelper.updatePerson(personId, personName, personGender, Integer.parseInt(personAge))) {
                Toast.makeText(getApplicationContext(), "Person updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Could not update person", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (dbHelper.insertPerson(personName, personGender, Integer.parseInt(personAge))) {
                Toast.makeText(getApplicationContext(), "Person inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Could not insert person", Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent = new Intent(getApplicationContext(), MainCrudActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void doEditAction() {
        saveButton.setVisibility(View.VISIBLE);
        buttonLayout.setVisibility(View.GONE);
        nameEditText.setEnabled(true);
        nameEditText.setFocusableInTouchMode(true);
        nameEditText.setClickable(true);

        genderEditText.setEnabled(true);
        genderEditText.setFocusableInTouchMode(true);
        genderEditText.setClickable(true);

        ageEditText.setEnabled(true);
        ageEditText.setFocusableInTouchMode(true);
        ageEditText.setClickable(true);
    }

    private void doDeleteAction() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.deletePerson)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deletePerson(personId);
                        Toast.makeText(getApplicationContext(), "Delete successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainCrudActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle(R.string.delete_person);
        alertDialog.show();
    }
}
