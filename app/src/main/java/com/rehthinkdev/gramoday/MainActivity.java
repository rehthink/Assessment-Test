package com.rehthinkdev.gramoday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.rehthinkdev.gramoday.API.RetrofitClient;
import com.rehthinkdev.gramoday.Model.Product;
import com.rehthinkdev.gramoday.Model.GramModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView username, location, language;
    ImageView userImage;
    Button connect, contact;
    TabLayout tabLayout;
    ViewPager viewPager;

    String name;
    String userLocation;
    String speak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        username = findViewById(R.id.userName);
        location = findViewById(R.id.location);
        language = findViewById(R.id.language);
        userImage = findViewById(R.id.imageView);

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Business"));
        tabLayout.addTab(tabLayout.newTab().setText("Review"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getUsers();
    }

    private void getUsers() {
        Call<GramModel> call = RetrofitClient.getInstance().getMyApi().getUser();
        call.enqueue(new Callback<GramModel>() {
            @Override
            public void onResponse(Call<GramModel> call, Response<GramModel> response) {
                GramModel gramModel = response.body();

                assert gramModel != null;
                name = gramModel.getName();
                userLocation = gramModel.getLoclevel3Name()+", "+gramModel.getLoclevel2Name();
                speak = gramModel.getLanguage();

                username.setText(name);
                location.setText(userLocation);
                //language.setText(speak);

                SharedPreferences preferences=getSharedPreferences("myPref",MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("Firm", gramModel.getBusiness().getFirmName());
                editor.putString("Mandi",gramModel.getBusiness().getMarketStdName());
                editor.putString("Shop Number",gramModel.getBusiness().getMandiShopnum());
                editor.apply();

                Log.d( "TAG" , gramModel.get_id());
                Log.d( "TAG" , gramModel.getBusiness().getMarketStdName());
                Log.d( "TAG" , gramModel.getBusiness().getFirmName());
                //Log.d( "TAG" , gramModel.getName());
                Log.d( "TAG" , String.valueOf(gramModel.getProducts()));
                Log.d( "TAG" , String.valueOf(gramModel.getProducts()));
            }

            @Override
            public void onFailure(Call<GramModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

}