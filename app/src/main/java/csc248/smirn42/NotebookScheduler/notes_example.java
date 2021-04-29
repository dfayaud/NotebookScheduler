package csc248.smirn42.NotebookScheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;


public class notes_example extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText notebookName, noteText, dateText;
    CheckBox dueDate;
    int day = 0, month = 0, year = 0;
    int savedDay, savedMonth, savedYear = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_example);

        notebookName = findViewById(R.id.notebookName);
        noteText = findViewById(R.id.noteText);
        dateText = findViewById(R.id.dateText);
        dueDate = findViewById(R.id.dueDate);


        // get notebook name from another activity
        Intent intent = getIntent();
        String str = intent.getStringExtra("notebookName");
        notebookName.setText(str);

        // create a database helper
        DataBaseHelper dataBaseHelper = new DataBaseHelper(notes_example.this);

        // get notebook Id from notebook Name
        Notebook noteBook = dataBaseHelper.getNotebooks(str).get(0);
        int noteBookId = noteBook.getNotebookId();

        // get note text and note due date from notebookId
        Note note = dataBaseHelper.getNotes(noteBookId).get(0);
        String NoteText = note.getNoteText();
        String NoteDue = note.getDueDate();


        // set the notebook text and date from the database
        noteText.setText(NoteText);
        dateText.setText(NoteDue);

        if (! NoteDue.isEmpty()) {
            dueDate.setChecked(true);
        }

    }

    public void goToCalendar(View view) {
        Intent intent = new Intent(this, AddNotebook.class);
        startActivity(intent);
    }

    public void getDate() {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    }

    public void pickCurrentDate(View view) {
        String date = dateText.getText().toString();
        if (date.isEmpty()) {
            dateText.setClickable(false);
        } else {
            String[] arr = date.split("/", 3);
            month = Integer.parseInt(arr[0]);
            day = Integer.parseInt(arr[1]);
            year = Integer.parseInt(arr[2]);
            new DatePickerDialog(this, this, year, month - 1, day).show();
        }
    }

    public void pickTodayDate(View view) {
        getDate();
        new DatePickerDialog(this, this, year, month, day).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        savedDay = dayOfMonth;
        savedMonth  = month + 1;
        savedYear = year;

        String date = savedMonth + "/" +  savedDay + "/" + savedYear;
        dateText.setText(date);

    }

    public void checkbox(View view) {
        if (dueDate.isChecked()) {
            pickTodayDate(view);
        } else {
            dateText.setText("");
        }
    }

    public void save_notes() {

        DataBaseHelper dataBaseHelper = new DataBaseHelper(notes_example.this);
        Note note = new Note(-1,
                dataBaseHelper.notebookNameToNotebookId(notebookName.getText().toString()),
                noteText.getText().toString(),
                dateText.getText().toString(),
                false);

        boolean success = dataBaseHelper.addNote(note);

        Intent intent = new Intent(this, AddNotebook.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        save_notes();
        super.onBackPressed();
    }
}