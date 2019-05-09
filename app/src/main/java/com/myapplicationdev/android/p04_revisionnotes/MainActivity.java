package com.myapplicationdev.android.p04_revisionnotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnShowList;
    EditText etTextNote;
    RadioGroup rg;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Note> notes;
    int stars = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTextNote = findViewById(R.id.editTextNote);
        btnInsert = findViewById(R.id.buttonInsertNote);
        btnShowList = findViewById(R.id.buttonShowList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textNote = etTextNote.getText().toString();



                rg = findViewById(R.id.radioGroupStars);
                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = findViewById(selectedButtonId);
                if (rb.getText().toString().equals("1")) {
                    stars = 1;
                } else if (rb.getText().toString().equals("2")) {
                    stars = 2;
                } else if (rb.getText().toString().equals("3")) {
                    stars = 3;
                } else if (rb.getText().toString().equals("4")) {
                    stars = 4;
                } else {
                    stars = 5;
                }

                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a Note
                db.insertNote(textNote, stars);
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                Log.d("db content ", textNote + ", " + stars);
                db.close();
            }
        });

/*        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(MainActivity.this);

                ArrayList<String> data = db.getNoteContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);
            }
        });*/

    }
}
