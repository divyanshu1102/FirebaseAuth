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

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class BuildProfile extends AppCompatActivity {

    private  RadioGroup user_type, Question0_group, Question1_group, Question2_group, Question3_group, Question4_group;
    private String[] answers={"-99","-99","-99","-99","-99","-99","-99","-99","-99"};
    private MultiAutoCompleteTextView bio;
    private TextView name, dateofBirth;

    int day, month, year;
    TextView dateOfBirth;

    private FirebaseAuth auth1= FirebaseAuth.getInstance();
    Firebase reference= new Firebase("https://testingfirebase2-baa5c.firebaseio.com/");
    //auth1 = FirebaseAuth.getInstance();
    Firebase databaseReference = reference.child("data").child("Users").child(auth1.getCurrentUser().getUid());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_profile);
        //Firebase.setAndroidContext(this);



        name= (TextView) findViewById(R.id.name) ; // data 0 done
        dateofBirth= (TextView) findViewById(R.id.dateOfBirth) ; // data 1 done

        user_type= (RadioGroup) findViewById(R.id.radioGroup);
        Question0_group= (RadioGroup) findViewById(R.id.Question0_group);
        Question1_group= (RadioGroup) findViewById(R.id.Question1_group);
        Question2_group = (RadioGroup) findViewById(R.id.Question2_group);
        Question3_group = (RadioGroup) findViewById(R.id.Question3_group);
        Question4_group = (RadioGroup) findViewById(R.id.Question4_group);

        bio = (MultiAutoCompleteTextView) findViewById(R.id.bio);


        Question0_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {    // data 2

                if (checkedId== R.id.male)
                    answers[2]= "male";

                else  if (checkedId== R.id.female)
                    answers[2]="female";

                else if (checkedId== R.id.otherGender)
                    answers[2]="Other";
            }
        });


        user_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {     // data 3
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
                            apartment.setText("Looking for Roommate for: "+item.getTitle());

                            if(item.getTitle().equals("Arbor Oaks"))
                                answers[3]="Looking for Roommate for: Arbor Oaks";
                            else if(item.getTitle().equals("Meadow Run"))
                                answers[3]="Looking for Roommate for: Meadow Run";
                            else if(item.getTitle().equals("University Village"))
                                answers[3]="Looking for Roommate for: University Village";
                            else if(item.getTitle().equals("Center Point"))
                                answers[3]="Looking for Roommate for: Center Point";
                            else if(item.getTitle().equals("Garden Club"))
                                answers[3]="Looking for Roommate for: Garden Club";

                            return true;
                        }
                    });

                    popupMenu.show();
                }
                else if (checkedId== R.id.apartment)
                {
                    answers[3]="Looking for Apartment";
                }

            }
        });


        Question1_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {       // data 4

                if (checkedId== R.id.stem)
                    answers[4]= "stem";

                else  if (checkedId== R.id.arts)
                    answers[4]="arts";

                else if (checkedId== R.id.business)
                    answers[4]="business";

                else  if (checkedId== R.id.others)
                    answers[4]="others";
            }
        });

        Question2_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {        // data 5

                if (checkedId== R.id.early_bird)
                    answers[5]= "Early Bird";

                else  if (checkedId== R.id.night_owl)
                    answers[5]= "Night Owl";

            }
        });

        Question3_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {        // data 6

                if (checkedId== R.id.rarely)
                    answers[6]= "Rarely";

                else  if (checkedId== R.id.onceAWeek)
                    answers[6]="Once A Week";

                else if (checkedId== R.id.once2Weeks)
                    answers[6]="Once every 2 weeks";

                else  if (checkedId== R.id.onceAMonth)
                    answers[6]="Once A Month";
            }
        });

        Question4_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {     // data 7

                if (checkedId== R.id.RarelyGuests)
                    answers[7]= "Rarely";

                else  if (checkedId== R.id.onceAWhile)
                    answers[7]="Once or Twice a Week";

                else if (checkedId== R.id.SeveralTimesWeek)
                    answers[7]="Several Times A Week";

            }
        });



        Button seeAnswers = (Button) findViewById(R.id.seeAnswers);

        seeAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Button clicked",Toast.LENGTH_SHORT).show();
                answers[8]= bio.getText().toString();

                answers[0]= name.getText().toString();  // name done
                answers[1]= ""+day+"/"+(month+1)+"/"+year; // date of birth done


                for(int i=0;i<=8;i++)
                Log.i("answer",answers[i]);

                databaseReference.child("Name").setValue(answers[0]);
                databaseReference.child("Date Of Birth").setValue(answers[1]);
                databaseReference.child("Gender").setValue(answers[2]);
                databaseReference.child("User Type").setValue(answers[3]);
                databaseReference.child("Major").setValue(answers[4]);
                databaseReference.child("Sleep Preferences").setValue(answers[5]);
                databaseReference.child("Cleaning Frequency").setValue(answers[6]);
                databaseReference.child("Guests").setValue(answers[7]);
                databaseReference.child("Bio").setValue(answers[8]);


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
