package csc248.smirn42.NotebookScheduler;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNotebook extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton note, checklist;

    // My changes
    EditText notebookTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notebook_creation);

        radioGroup = findViewById(R.id.radio_group);
        note = findViewById(R.id.note_selection);
        checklist = findViewById(R.id.checklist_selection);

        // my changes
        notebookTitle = findViewById(R.id.notebookTitle);

    }

    public void checkInput(View view) {
        if(note.isChecked()) {
            System.out.println("Notes");
        } else {
            System.out.println("Checklist");
        }
    }

    // My changes
    public void AddNotebooks(View view) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(AddNotebook.this);

        String title = notebookTitle.getText().toString();


        Notebook notebook = new Notebook(-1,
                title,
                "Blue",
                "Dark",
                note.isChecked());

        boolean successNB = dataBaseHelper.addNotebook(notebook);



        Note note = new Note(-1,
                dataBaseHelper.notebookNameToNotebookId(title),
                "",
                "",
                false);

        boolean successN = dataBaseHelper.addNote(note);

        Intent intent = new Intent(this, notes_example.class);
        intent.putExtra("notebookName", title);
        startActivity(intent);
    }
}
