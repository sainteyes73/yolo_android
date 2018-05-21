package com.example.gim_useong.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gim_useong.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView imageView1, imageView2, imageView3;
    private ImageView today_photo;
    private DatabaseReference mDatabase;
    private String head_id;
    private String head_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setting();
        initView();
    }
    public void setting(){

        mDatabase= FirebaseDatabase.getInstance().getReference("users");
    }
    public void setnav(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView=navigationView.getHeaderView(0);
        final TextView navUsername = (TextView)headerView.findViewById(R.id.nickname);
        final TextView navEmail= (TextView)headerView.findViewById(R.id.head_id);
        navigationView.setNavigationItemSelectedListener(this);
        mDatabase.child(getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(snapshot.getKey()=="username"){
                        //nick_name.setText((String)snapshot.getValue());
                        Log.d("aaaa",(String)snapshot.getValue());
                        //navUsername.setText((String)snapshot.getValue());
                        head_name=(String)snapshot.getValue();
                        navUsername.setText(head_name);
                    }
                    else if(snapshot.getKey()=="email"){
                        head_id=(String)snapshot.getValue();
                        navEmail.setText(head_id);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setnav();
        imageView1 = (ImageView)findViewById(R.id.camera_icon);
        imageView2 = (ImageView)findViewById(R.id.voice_icon);
        imageView3 = (ImageView)findViewById(R.id.pen_icon);
        imageView1.setImageResource(R.drawable.camera);
        imageView2.setImageResource(R.drawable.voice);
        imageView3.setImageResource(R.drawable.pen);

        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CameraActivity.class);
                startActivity(intent);
            }
        });

        today_photo = (ImageView)findViewById(R.id.today_recipe);

        today_photo.setImageResource(R.drawable.todaymenu);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.all_product) {
            Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
            startActivity(intent);
        }
        /*
        else if (id == R.id.today_menu) {
            Intent intent = new Intent(MainActivity.this, recipe_list.class);
            startActivity(intent);
        } else if (id == R.id.menu_notice) {
            Intent intent = new Intent(MainActivity.this, menu_notice.class);
            startActivity(intent);
        } else if (id == R.id.camera_recognition) {
            Intent intent = new Intent(MainActivity.this, camera_option.class);
            startActivity(intent);
        } else if (id == R.id.voice_recognition) {
            Intent intent = new Intent(MainActivity.this, voice_option.class);
            startActivity(intent);
        } else if (id == R.id.write_recognition) {
            Intent intent = new Intent(MainActivity.this, pen_option.class);
            startActivity(intent);
        } else if(id == R.id.setting){
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        } //else if 로그아웃 기능
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
