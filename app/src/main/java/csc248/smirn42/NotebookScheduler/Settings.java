package csc248.smirn42.NotebookScheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Settings extends AppCompatActivity {
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        }
        public void goBLUE(View view)
        {
            view.setBackgroundResource(R.color.blue);
        }
        public void goGreen(View view)
        {
            view.setBackgroundResource(R.color.green);
        }
        public void goYellow(View v)
        {
            view.setBackgroundResource(R.color.yellow);
        }
        public void goHotPink(View v)
    {
            view.setBackgroundResource(R.color.hot_pink);
    }
        public void goOrange(View v)
        {
            view.setBackgroundResource(R.color.orange);
        }
        public void goCyan(View v)
        {
            view.setBackgroundResource(R.color.cayan);
        }
    public void goRed(View v)
    {
        view.setBackgroundResource(R.color.red);
    }
        public void goPurple(View v)
        {
            view.setBackgroundResource(R.color.purple);
        }
        public void goWhite(View view)
        {
            view.setBackgroundResource(R.color.white);
        }
        public void goBlack(View v)
        {
            view.setBackgroundResource(R.color.black);
        }
        public void goGray(View v)
        {
            view.setBackgroundResource(R.color.gray);
        }
        public void goBeige(View v)
        {
            view.setBackgroundResource(R.color.beige);
        }
    }