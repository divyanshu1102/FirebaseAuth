package info.androidhive.firebase;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.MultiAutoCompleteTextView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class BuildProfile extends AppCompatActivity {

    private  RadioGroup user_type, Question1_group, Question2_group, Question3_group, Question4_group;
    private String[] answers={"-99","-99","-99","-99","-99","-99"};
    private MultiAutoCompleteTextView bio;

    int day, month, year;
    TextView dateOfBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_profile);

        user_type= (RadioGroup) findViewById(R.id.radioGroup);
        Question1_group= (RadioGroup) findViewById(R.id.Question1_group);
        Question2_group = (RadioGroup) findViewById(R.id.Question2_group);
        Question3_group = (RadioGroup) findViewById(R.id.Question3_group);
        Question4_group = (RadioGroup) findViewById(R.id.Question4_group);

        bio = (MultiAutoCompleteTextView) findViewById(R.id.bio);

        user_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if (checkedId== R.id.roommate)
                {
                    Toast.makeText(getApplicationContext(), "Apartment", Toast.LENGTH_SHORT).show();
                    PopupMenu popupMenu = new PopupMenu(getApplicationContext(),user_type);
                    popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(getApplicationContext(), ""+item.getTitle(), Toast.LENGTH_SHORT).show();

                            RadioButton apartment = (RadioButton) findViewById(R.id.roommate);
                            apartment.setText("Looking for Apartment: "+item.getTitle());

                            if(item.getTitle().equals("Arbor Oaks"))
                                answers[0]="1.1";
                            else if(item.getTitle().equals("Meadow Run"))
                                answers[0]="1.2";
                            else if(item.getTitle().equals("University Village"))
                                answers[0]="1.3";
                            else if(item.getTitle().equals("Center Point"))
                                answers[0]="1.4";
                            else if(item.getTitle().equals("Garden Club"))
                                answers[0]="1.5";

                            return true;
                        }
                    });

                    popupMenu.show();
                }
                else if (checkedId== R.id.apartment)
                {
                    answers[0]="2";
                }

            }
        });

        Question1_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId== R.id.stem)
                    answers[1]= "1";

                else  if (checkedId== R.id.arts)
                    answers[1]="2";

                else if (checkedId== R.id.business)
                    answers[1]="3";

                else  if (checkedId== R.id.others)
                    answers[1]="4";
            }
        });

        Question2_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId== R.id.early_bird)
                    answers[2]= "1";

                else  if (checkedId== R.id.night_owl)
                    answers[2]= "2";

            }
        });

        Question3_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId== R.id.rarely)
                    answers[3]= "1";

                else  if (checkedId== R.id.onceAWeek)
                    answers[3]="2";

                else if (checkedId== R.id.once2Weeks)
                    answers[3]="3";

                else  if (checkedId== R.id.onceAMonth)
                    answers[3]="4";
            }
        });

        Question4_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId== R.id.RarelyGuests)
                    answers[4]= "1";

                else  if (checkedId== R.id.onceAWhile)
                    answers[4]="2";

                else if (checkedId== R.id.SeveralTimesWeek)
                    answers[4]="3";

            }
        });



        Button seeAnswers = (Button) findViewById(R.id.seeAnswers);
        seeAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answers[5]= bio.getText().toString();

                for(int i=0;i<=5;i++)
                Log.i("answer",answers[i]);
            }
        });


        dateOfBirth =(TextView) findViewById(R.id.dateOfBirth);

        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });

    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            day = selectedDay;
            month = selectedMonth;
            year = selectedYear;
            dateOfBirth.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };
}
