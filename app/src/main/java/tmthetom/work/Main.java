package tmthetom.work;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set layout
        setContentView(R.layout.activity_main);

        // Elements from activity
        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        // Get current date
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Date picker init
        datePicker.init(year, month, day, dateSetListener);

        // Get if working init
        GetIfWorking();
    }

    private DatePicker.OnDateChangedListener dateSetListener = new DatePicker.OnDateChangedListener() {
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        GetIfWorking();
        }
    };

    private void GetIfWorking(){

        /// <summary>
        /// OddWeek
        /// True = In work
        /// False = Not working
        /// </summary>
        Boolean[] oddWeek = {
            true,  // Sunday
            true,  // Monday
            true,  // Tuesday
            false,  // Wednesday
            false,  // Thursday
            true,  // Friday
            true   // Saturday
        };

        /// <summary>
        /// EvenWeek
        /// True = In work
        /// False = Not working
        /// </summary>
        Boolean[] evenWeek = {
            false,  // Sunday
            false,  // Monday
            false,  // Tuesday
            true,  // Wednesday
            true,  // Thursday
            false,  // Friday
            false   // Saturday
        };

        // Elements from activity
        final TextView textView = (TextView) findViewById(R.id.textView);
        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        // Gets the Calendar instance
        Calendar calendar = Calendar.getInstance();

        // Set selected date
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int realMonth = month + 1;
        int year = datePicker.getYear();
        calendar.set(year, month, day);

        // Calculate odd or even week of year
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // Show result

        // Odd week
        if (weekOfYear % 2 != 0) {
            textView.setText(day + ". " + realMonth + ". " + year + System.getProperty("line.separator") + (oddWeek[dayOfWeek] ? "Jsem v práci" : "Nejsem v práci"));
        }

        // Even week
        else {
            textView.setText(day + ". " + realMonth + ". " + year + System.getProperty("line.separator") + (evenWeek[dayOfWeek] ? "Jsem v práci" : "Nejsem v práci"));
        }
    }
}
