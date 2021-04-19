package csc248.smirn42.NotebookScheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addNotebook(View view) {
        Intent intent = new Intent(this, AddNotebook.class);
        startActivity(intent);
    }

}