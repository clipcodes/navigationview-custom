package net.clipcodes;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    RecyclerView menurv;
    AdapRV adapRV;
    LinearLayoutManager lm;
    ImageView imageView;
    TextView name, web;
    TextView textmessage;
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    ImageView iconimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        view();
        navview(navigationView);
    }

    public void view(){
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        textmessage = findViewById(R.id.textmessage);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        iconimage = findViewById(R.id.iconimage);
    }

    public void navview(NavigationView v){
        imageView = v.findViewById(R.id.imageView);
        name = v.findViewById(R.id.name);
        web = v.findViewById(R.id.web);
        menurv = v.findViewById(R.id.menurv);
        final String[] title = {"Buyer", "Seller", "Favorite", "Payments"};
        final int[] color = {R.color.red, R.color.purple, R.color.teal, R.color.blue};
        final int[] icon = {R.drawable.icon_buyer, R.drawable.icon_seller, R.drawable.icon_favorite, R.drawable.icon_payments};
        adapRV = new AdapRV(this, icon, title, color);
        lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        menurv.setLayoutManager(lm);
        menurv.setAdapter(adapRV);
        adapRV.setItemClickListener(new AdapRV.ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                drawer.closeDrawers();
                textmessage.setText(title[position]);
                iconimage.setImageResource(icon[position]);
                iconimage.setColorFilter(getResources().getColor(color[position]));
                Toast.makeText(MainActivity.this, title[position]+" Menu Clicked !", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


