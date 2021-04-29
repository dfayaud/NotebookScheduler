package csc248.smirn42.NotebookScheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToAddNotebookPage = findViewById(R.id.addNotebookBtn);
        goToAddNotebookPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotebook();
            }
        });

    }

    public void addNotebook() {
        Intent intent = new Intent(this, AddNotebook.class);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}