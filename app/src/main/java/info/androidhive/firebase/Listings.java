package info.androidhive.firebase;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Listings extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

   //private Firebase reference= new Firebase("https://testingfirebase2-baa5c.firebaseio.com/data/Users");

    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("data").child("Users");
    Query q= usersRef.orderByKey().equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

    private ArrayList<String> titles=new ArrayList<String>();
    private ArrayList<String> details=new ArrayList<String>();
    private ArrayList<String> images=new ArrayList<String>();



    public void goToCommunication(View view)
    {
        Intent intent = new Intent(this, Communication.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        recyclerView =
                (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        /*
        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);
         */

        ////////////////////////////////////////////////////////////

        /*q.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {

                User downloadingUser =  dataSnapshot.getValue(User.class);

                titles.add(downloadingUser.getUser_type());
                Log.i("DownLoad",""+downloadingUser.getUser_type());
                details.add(downloadingUser.getMajor());
                Log.i("DownLoad",""+downloadingUser.getMajor());
                images.add(downloadingUser.getName());
                Log.i("DownLoad",""+downloadingUser.getName());


                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/ // sorta works


        // following works

        /*q.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
            @Override
            public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                User downloadingUser=  dataSnapshot.getValue(User.class);

                    titles.add(downloadingUser.getUser_type());
                    Log.i("DownLoad", "" + downloadingUser.getUser_type());
                    details.add(downloadingUser.getMajor());
                    Log.i("DownLoad", "" + downloadingUser.getMajor());
                    images.add(downloadingUser.getName());
                    Log.i("DownLoad", "" + downloadingUser.getName());

                    adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                User downloadingUser =  dataSnapshot.getValue(User.class);

                titles.add(downloadingUser.getUser_type());
                Log.i("DownLoad",""+downloadingUser.getUser_type());
                details.add(downloadingUser.getMajor());
                Log.i("DownLoad",""+downloadingUser.getMajor());
                images.add(downloadingUser.getName());
                Log.i("DownLoad",""+downloadingUser.getName());


                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



        usersRef.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
                                           @Override
                                           public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                                               User downloadingUser =  dataSnapshot.getValue(User.class);

                                               titles.add(downloadingUser.getUser_type());
                                               Log.i("DownLoad",""+downloadingUser.getUser_type());
                                               details.add(downloadingUser.getMajor());
                                               Log.i("DownLoad",""+downloadingUser.getMajor());
                                               images.add(downloadingUser.getName());
                                               Log.i("DownLoad",""+downloadingUser.getName());


                                               adapter.notifyDataSetChanged();

                                           }

                                           @Override
                                           public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                                               User downloadingUser =  dataSnapshot.getValue(User.class);

                                               titles.add(downloadingUser.getUser_type());
                                               Log.i("DownLoad",""+downloadingUser.getUser_type());
                                               details.add(downloadingUser.getMajor());
                                               Log.i("DownLoad",""+downloadingUser.getMajor());
                                               images.add(downloadingUser.getName());
                                               Log.i("DownLoad",""+downloadingUser.getName());


                                               adapter.notifyDataSetChanged();

                                           }

                                           @Override
                                           public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {

                                           }

                                           @Override
                                           public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                                               User downloadingUser =  dataSnapshot.getValue(User.class);

                                               titles.add(downloadingUser.getUser_type());
                                               Log.i("DownLoad",""+downloadingUser.getUser_type());
                                               details.add(downloadingUser.getMajor());
                                               Log.i("DownLoad",""+downloadingUser.getMajor());
                                               images.add(downloadingUser.getName());
                                               Log.i("DownLoad",""+downloadingUser.getName());


                                               adapter.notifyDataSetChanged();
                                           }

                                           @Override
                                           public void onCancelled(DatabaseError databaseError) {

                                           }
                                       });


        adapter = new RecyclerAdapter(titles, details, images);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_card_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
