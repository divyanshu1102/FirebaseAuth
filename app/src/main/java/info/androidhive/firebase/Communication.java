package info.androidhive.firebase;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Communication extends AppCompatActivity {

    private ImageButton messagingButton;
    private String phone;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        messagingButton= (ImageButton)findViewById(R.id.messagingButton);

        Intent intent = getIntent();
        phone = intent.getStringExtra("Phone");

        //Toast.makeText(Communication.this,""+phone,Toast.LENGTH_SHORT).show();

        messagingButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageButton view = (ImageButton) v;
                        //overlay is black with transparency of 0x77 (119)
                        v.startAnimation(buttonClick);
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        //clear the overlay
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }

                return false;
            }
        });


        messagingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Communication.this,"Redirecting to Messaging...",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phone));
                intent.putExtra("sms_body", "Hey!\n I saw your listing on URoom\n");

                try {
                    startActivity(intent);
                    Log.i("Finished sending SMS...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Communication.this,"SMS failed, please try again later.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
