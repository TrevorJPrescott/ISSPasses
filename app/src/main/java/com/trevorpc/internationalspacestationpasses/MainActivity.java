package com.trevorpc.internationalspacestationpasses;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.trevorpc.internationalspacestationpasses.adapter.ResponseAdapter;

import com.trevorpc.internationalspacestationpasses.model.FullResponse;
import com.trevorpc.internationalspacestationpasses.model.Response;
import com.trevorpc.internationalspacestationpasses.retrofit.DaggerNewRetrofitClient;
import com.trevorpc.internationalspacestationpasses.retrofit.IMyAPI;
import com.trevorpc.internationalspacestationpasses.retrofit.NewRetrofitClient;
import com.trevorpc.internationalspacestationpasses.retrofit.RetrofitClient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    IMyAPI myAPI;
    RecyclerView recycle_responses;

    LocationManager manager;
    LocationListener listener;

    Double longitude =45.5;
    Double latitude = 45.5;

    private static final String TAG = "soFar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Location
        LocationGetter();



        NewRetrofitClient client = DaggerNewRetrofitClient.create();
        RetrofitClient retrofitClient = client.getRetrofitClient();
        Retrofit retrofit = retrofitClient.getOurInstance();

        myAPI = retrofit.create(IMyAPI.class);
        Log.d(TAG, "API setUp success ");

        //RecyclerView
        recycle_responses = (RecyclerView) findViewById(R.id.recycle_responses);
        recycle_responses.setHasFixedSize(false);
        recycle_responses.setLayoutManager(new LinearLayoutManager(this));

        Log.d(TAG, "RecycleView setup success ");

    }

    private void LocationGetter()
    {
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.d(TAG, "Location: " + latitude + " " + longitude);
                fetchData();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            } else {
                manager.requestLocationUpdates("gps", 5000, 0, listener);
            }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED){
                manager.requestLocationUpdates("gps",5000,0,listener);
            }
        }
    }

    private void fetchData() {
        myAPI.getResponse(latitude,longitude,20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<FullResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onSuccess(FullResponse fullResponse) {
                        List<Response> responses = fullResponse.response;
                        displayData(responses);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: error fetching data");
                    }
                });
    }

    private void displayData(List<Response> responses) {
        ResponseAdapter adapter = new ResponseAdapter(this,responses);
        recycle_responses.setAdapter(adapter);
    }


}
