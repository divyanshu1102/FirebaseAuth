package info.androidhive.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
    private ArrayList<String> userIDs= new ArrayList<String>();
    private ArrayList<String> phoneNumbers= new ArrayList<String>();

    //final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    //DatabaseReference currentUserDatabase;
    String userType_listing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        //Intent intent = getIntent();
        //userType_listing = intent.getStringExtra("UserType");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ///
        //userID=user.getUid();
        //currentUserDatabase= FirebaseDatabase.getInstance().getReference().child("data").child("Users").child(userID);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("data");
        mDatabase.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                       User currentUser= dataSnapshot.getValue(User.class);
                    //Toast.makeText(Listings.this,"User name: "+currentUser.getName()+" "+currentUser.getUser_type(),Toast.LENGTH_SHORT).show();
                    userType_listing=currentUser.getUser_type().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                userType_listing="";
                Toast.makeText(Listings.this,"Firebase Error Occured",Toast.LENGTH_SHORT).show();
            }
        });


        ///


        usersRef.addChildEventListener(new com.google.firebase.database.ChildEventListener() {
               @Override
               public void onChildAdded(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                   User downloadingUser =  dataSnapshot.getValue(User.class);

                   if(userType_listing.equals("Looking for Apartment")) {

                       if(!downloadingUser.getUser_type().equals("Looking for Apartment")) {
                           titles.add(downloadingUser.getUser_type());
                           Log.i("DownLoad", "" + downloadingUser.getUser_type());

                           details.add(downloadingUser.getMajor());
                           Log.i("DownLoad", "" + downloadingUser.getMajor());

                           images.add(downloadingUser.getName());
                           Log.i("DownLoad", "" + downloadingUser.getName());

                           phoneNumbers.add(downloadingUser.getPhone_number());
                           Log.i("DownLoad", "" + downloadingUser.getPhone_number());

                           userIDs.add(downloadingUser.getUserId());
                           Log.i("DownLoad", "" + downloadingUser.getUserId());

                       }
                   }
                   else if(!userType_listing.equals("Looking for Apartment") && !userType_listing.equals("") )
                   {
                       if(downloadingUser.getUser_type().equals("Looking for Apartment")) {
                           titles.add(downloadingUser.getUser_type());
                           Log.i("DownLoad", "" + downloadingUser.getUser_type());

                           details.add(downloadingUser.getMajor());
                           Log.i("DownLoad", "" + downloadingUser.getMajor());

                           images.add(downloadingUser.getName());
                           Log.i("DownLoad", "" + downloadingUser.getName());

                           phoneNumbers.add(downloadingUser.getPhone_number());
                           Log.i("DownLoad", "" + downloadingUser.getPhone_number());

                           userIDs.add(downloadingUser.getUserId());
                           Log.i("DownLoad", "" + downloadingUser.getUserId());
                       }
                   }
                   else
                   {
                       titles.add(downloadingUser.getUser_type());
                       Log.i("DownLoad", "" + downloadingUser.getUser_type());

                       details.add(downloadingUser.getMajor());
                       Log.i("DownLoad", "" + downloadingUser.getMajor());

                       images.add(downloadingUser.getName());
                       Log.i("DownLoad", "" + downloadingUser.getName());

                       phoneNumbers.add(downloadingUser.getPhone_number());
                       Log.i("DownLoad", "" + downloadingUser.getPhone_number());

                       userIDs.add(downloadingUser.getUserId());
                       Log.i("DownLoad", "" + downloadingUser.getUserId());

                   }

                   adapter.notifyDataSetChanged();
               }

               @Override
               public void onChildChanged(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                   User downloadingUser =  dataSnapshot.getValue(User.class);

                   if(userType_listing.equals("Looking for Apartment")) {

                       if(!downloadingUser.getUser_type().equals("Looking for Apartment")) {
                           titles.add(downloadingUser.getUser_type());
                           Log.i("DownLoad", "" + downloadingUser.getUser_type());

                           details.add(downloadingUser.getMajor());
                           Log.i("DownLoad", "" + downloadingUser.getMajor());

                           images.add(downloadingUser.getName());
                           Log.i("DownLoad", "" + downloadingUser.getName());

                           phoneNumbers.add(downloadingUser.getPhone_number());
                           Log.i("DownLoad", "" + downloadingUser.getPhone_number());

                           userIDs.add(downloadingUser.getUserId());
                           Log.i("DownLoad", "" + downloadingUser.getUserId());

                       }
                   }
                   else  if(!userType_listing.equals("Looking for Apartment") && !userType_listing.equals("") )
                   {
                       if(downloadingUser.getUser_type().equals("Looking for Apartment")) {
                           titles.add(downloadingUser.getUser_type());
                           Log.i("DownLoad", "" + downloadingUser.getUser_type());

                           details.add(downloadingUser.getMajor());
                           Log.i("DownLoad", "" + downloadingUser.getMajor());

                           images.add(downloadingUser.getName());
                           Log.i("DownLoad", "" + downloadingUser.getName());

                           phoneNumbers.add(downloadingUser.getPhone_number());
                           Log.i("DownLoad", "" + downloadingUser.getPhone_number());

                           userIDs.add(downloadingUser.getUserId());
                           Log.i("DownLoad", "" + downloadingUser.getUserId());
                       }
                   }
                   else
                   {
                       titles.add(downloadingUser.getUser_type());
                       Log.i("DownLoad", "" + downloadingUser.getUser_type());

                       details.add(downloadingUser.getMajor());
                       Log.i("DownLoad", "" + downloadingUser.getMajor());

                       images.add(downloadingUser.getName());
                       Log.i("DownLoad", "" + downloadingUser.getName());

                       phoneNumbers.add(downloadingUser.getPhone_number());
                       Log.i("DownLoad", "" + downloadingUser.getPhone_number());

                       userIDs.add(downloadingUser.getUserId());
                       Log.i("DownLoad", "" + downloadingUser.getUserId());

                   }

                   adapter.notifyDataSetChanged();

               }

               @Override
               public void onChildRemoved(com.google.firebase.database.DataSnapshot dataSnapshot) {

               }

               @Override
               public void onChildMoved(com.google.firebase.database.DataSnapshot dataSnapshot, String s) {

                   User downloadingUser =  dataSnapshot.getValue(User.class);

                   if(userType_listing.equals("Looking for Apartment")) {

                       if(!downloadingUser.getUser_type().equals("Looking for Apartment")) {
                           titles.add(downloadingUser.getUser_type());
                           Log.i("DownLoad", "" + downloadingUser.getUser_type());

                           details.add(downloadingUser.getMajor());
                           Log.i("DownLoad", "" + downloadingUser.getMajor());

                           images.add(downloadingUser.getName());
                           Log.i("DownLoad", "" + downloadingUser.getName());

                           phoneNumbers.add(downloadingUser.getPhone_number());
                           Log.i("DownLoad", "" + downloadingUser.getPhone_number());

                           userIDs.add(downloadingUser.getUserId());
                           Log.i("DownLoad", "" + downloadingUser.getUserId());

                       }
                   }
                   else  if(!userType_listing.equals("Looking for Apartment") && !userType_listing.equals("") )
                   {
                       if(downloadingUser.getUser_type().equals("Looking for Apartment")) {
                           titles.add(downloadingUser.getUser_type());
                           Log.i("DownLoad", "" + downloadingUser.getUser_type());

                           details.add(downloadingUser.getMajor());
                           Log.i("DownLoad", "" + downloadingUser.getMajor());

                           images.add(downloadingUser.getName());
                           Log.i("DownLoad", "" + downloadingUser.getName());

                           phoneNumbers.add(downloadingUser.getPhone_number());
                           Log.i("DownLoad", "" + downloadingUser.getPhone_number());

                           userIDs.add(downloadingUser.getUserId());
                           Log.i("DownLoad", "" + downloadingUser.getUserId());
                       }
                   }
                   else
                   {
                       titles.add(downloadingUser.getUser_type());
                       Log.i("DownLoad", "" + downloadingUser.getUser_type());

                       details.add(downloadingUser.getMajor());
                       Log.i("DownLoad", "" + downloadingUser.getMajor());

                       images.add(downloadingUser.getName());
                       Log.i("DownLoad", "" + downloadingUser.getName());

                       phoneNumbers.add(downloadingUser.getPhone_number());
                       Log.i("DownLoad", "" + downloadingUser.getPhone_number());

                       userIDs.add(downloadingUser.getUserId());
                       Log.i("DownLoad", "" + downloadingUser.getUserId());

                   }

                   adapter.notifyDataSetChanged();

               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });


        adapter = new RecyclerAdapter(titles, details, images, userIDs, phoneNumbers, Listings.this);
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
            //Toast.makeText(this,""+id,Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Created by Divyanshu Sharma on 11/29/2017.
     */

    public static class GlobalVariables {
    }
}
