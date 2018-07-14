package com.delta.generics;

import android.app.Activity;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.service.dreams.DreamService;
import android.util.StringBuilderPrinter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GenericsActivity extends Activity {

    public TextView nameTextView = null;
    public TextView descriptionTextView = null;
    public RatingBar ratingView = null;
    public ImageView portraitView = null;
    public Button nextButton = null;

    private int currentSelection = 0;

    // Adaptors
    CatAdapter catAdapter;
    AdoptAdaptor<Cat> catAdoptAdaptor;
    AdoptAdaptor<Dog> dogAdoptAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generics);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        ratingView = (RatingBar) findViewById(R.id.ratingView);
        portraitView = (ImageView) findViewById(R.id.portraitView);
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNext();
            }
        });

        //catAdapter = new CatAdapter(this,nameTextView,descriptionTextView,ratingView,portraitView);
        //catAdapter.set(AdoptData.mCatList.get(0));

        catAdoptAdaptor = new AdoptAdaptor<Cat>(this,nameTextView,descriptionTextView,ratingView,portraitView);
        //catAdoptAdaptor.set(AdoptData.mCatList.get(0));

        dogAdoptAdaptor = new AdoptAdaptor<Dog>(this,nameTextView,descriptionTextView,ratingView,portraitView);
        dogAdoptAdaptor.set(AdoptData.mDogList.get(0));

    }

    int catOrDog;
    public void showNext(){
        int random = currentSelection;
        while(random == currentSelection){
            //avoid same selection twice.
            catOrDog = 0;
            while (catOrDog == 0) {
                catOrDog = (int) (Math.random() * 3);
            }
            if (catOrDog==1){
                random = (int) (Math.random() *AdoptData.mCatList.size());
            }
            else {
                random = (int) (Math.random() *AdoptData.mDogList.size());
            }
            random = random;
        }
        currentSelection = random;

        if (catOrDog==1)
        {
            Cat c = AdoptData.mCatList.get(random);
            catAdoptAdaptor.set(c);
        }
        else {
            Dog d = AdoptData.mDogList.get(random);
            dogAdoptAdaptor.set(d);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.generics, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
