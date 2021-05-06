package csc248.smirn42.NotebookScheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class CalendarActivity extends AppCompatActivity {

    Button linkButton;
    CompactCalendarView calendarView;
    TextView textView;
    String currentMonth, currentYear;
    ImageButton left, right;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());
    boolean scrollFromButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);
        linkButton = (Button) findViewById(R.id.linkButton);
        calendarView = (CompactCalendarView) findViewById(R.id.simpleCalendarView);
        calendarView.setUseThreeLetterAbbreviation(true);
        textView = findViewById(R.id.monthandyear);
        left = findViewById(R.id.leftImage);
        right = findViewById(R.id.rightImage);
        //set current date as init of the text view
        getCurrentDate();
        //calendarView.shouldScrollMonth(false);
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Intent intent = new Intent(getBaseContext(), DayView.class);
                SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
                String formattedDate = df.format(dateClicked);
                intent.putExtra("date", formattedDate);
                intent.putExtra("dateIndateFormat", dateClicked);
                startActivity(intent);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                if (scrollFromButton) {
                    scrollFromButton = false;
                    return;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
                currentMonth = sdf.format(firstDayOfNewMonth);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
                currentYear = sdf2.format(firstDayOfNewMonth);
                textView.setText(dateFormat.format(firstDayOfNewMonth));
            }
        });
        //scroll with left button
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollFromButton = true;
                clickOnLeft();
            }
        });
        //scroll with right button
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollFromButton = true;
                clickOnRight();
            }
        });

        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNotebook();
            }
        });
    }

    public void goToNotebook() {
        Intent intent = new Intent(this, NotebookHome.class);
        startActivity(intent);
    }

    private void clickOnRight() {
        calendarView.showNextMonth();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        Calendar c = Calendar.getInstance();
        String date = currentMonth + "-" + currentYear;
        try {
            Date d = sdf.parse(date);
            c.setTime(Objects.requireNonNull(d));
            if (currentMonth.equals("December")) {
                int x = Integer.parseInt(currentYear) + 1;
                currentYear = String.valueOf(x);
            }
            c.add(Calendar.MONTH, 1);
            date = sdf.format(c.getTime());
            currentMonth = date;
            textView.setText(date + "-" + currentYear);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void clickOnLeft() {
        calendarView.showPreviousMonth();
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        Calendar c = Calendar.getInstance();
        String date = currentMonth + "-" + currentYear;
        try {
            Date d = sdf.parse(date);
            c.setTime(Objects.requireNonNull(d));
            if (currentMonth.equals("January")) {
                int x = Integer.parseInt(currentYear) - 1;
                currentYear = String.valueOf(x);
            }
            c.add(Calendar.MONTH, -1);
            date = sdf.format(c.getTime());
            currentMonth = date;
            textView.setText(date + "-" + currentYear);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.getDefault());
        SimpleDateFormat YearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
        //    String formattedDate = df.format(c);
        currentMonth = monthFormat.format(c);
        currentYear = YearFormat.format(c);
        textView.setText(currentMonth + "-" + currentYear);
    }
}


