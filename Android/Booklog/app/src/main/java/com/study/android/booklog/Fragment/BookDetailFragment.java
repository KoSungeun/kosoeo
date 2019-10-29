package com.study.android.booklog.Fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextLinks;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.tasks.OnSuccessListener;
import com.study.android.booklog.ImageRequester;
import com.study.android.booklog.MyRequestQueue;
import com.study.android.booklog.R;
import com.study.android.booklog.VolleyCallback;
import com.study.android.booklog.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * A simple {@link Fragment} subclass.
 */
public class BookDetailFragment extends Fragment {

    public static final String TAG = "lecture";
    private TextView tvTitle;
    private TextView tvIntroContent;
    private ImageRequester imageRequester;
    private NetworkImageView coverImg;

    private SupportMapFragment supportMapFragment;
    private GoogleMap map;
    MarkerOptions myLocationMarker;
    private FusedLocationProviderClient fusedLocationClient;


    public BookDetailFragment() {
        imageRequester = ImageRequester.getInstance();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);
        tvTitle = view.findViewById(R.id.tvTitleData);
        tvIntroContent = view.findViewById(R.id.tvIntroContent);
        coverImg = view.findViewById(R.id.cover_image);
        setUpToolbar(view);
        Book.initBookDetail(getArguments().getString("bid"), new VolleyCallback() {
            @Override
            public void onSuccess(Object result) {
                Book book = (Book) result;
                tvTitle.setText(book.getTitle());
                tvIntroContent.setText(book.getIntroContent());
                imageRequester.setImageFromUrl(coverImg, book.getCoverUrl());

            }
        });


        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                requestMyLocation();
                map.setMyLocationEnabled(true);
                map.setOnMyLocationClickListener(new GoogleMap.OnMyLocationClickListener() {
                    @Override
                    public void onMyLocationClick(@NonNull Location location) {
                        Toast.makeText(getActivity(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
                    }
                });
                map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                    @Override
                    public boolean onMyLocationButtonClick() {
                        Toast.makeText(getActivity(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

               getBookStorePlace();

            }
        });

        MapsInitializer.initialize(getActivity());

        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                // TransitionManager.beginDelayedTransition(container);
                //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void getBookStorePlace() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {

                            Log.d(TAG, location.toString());
                            RequestQueue queue = MyRequestQueue.getInstance();

//                          String url = "https://maps.googleapis.com/maps/api/place/textsearch/json";

                            String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?" +
                                    "location=" + location.getLatitude()+"," +location.getLongitude() +
                                    "&radius=5000" +
                                    "&type=book_store" +
                                    "&language=ko" +
                                    "&key=AIzaSyBxOLsR194XUro8eboTUmM9ileNgi9sKP4";

                            JSONObject jsonRequest = new JSONObject();
//                            try {
//                                jsonRequest.put("location", location.getLatitude()+"," +location.getLongitude());
//                                jsonRequest.put("radius", "5000");
//                                jsonRequest.put("type", "book_store");
//                                jsonRequest.put("key", "AIzaSyBxOLsR194XUro8eboTUmM9ileNgi9sKP4");
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }



                            JsonObjectRequest stringRequest = new JsonObjectRequest(url, jsonRequest, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d(TAG, response.toString());
                                    try {
                                        JSONArray result = response.getJSONArray("results");

                                        for(int i = 0; i < result.length(); i++){
                                            JSONObject item = result.getJSONObject(i);
                                            JSONObject geometry = item.getJSONObject("geometry");
                                            JSONObject location = geometry.getJSONObject("location");

                                            Location bookStoreLocation = new Location(item.getString("name"));
                                            bookStoreLocation.setLatitude(Double.parseDouble(location.getString("lat")));
                                            bookStoreLocation.setLongitude(Double.parseDouble(location.getString("lng")));
                                            MarkerOptions myLocationMarker = new MarkerOptions();

                                            myLocationMarker.position(new LatLng(bookStoreLocation.getLatitude(), bookStoreLocation.getLongitude()));
                                            myLocationMarker.title(item.getString("name"));
                                            myLocationMarker.snippet(item.getString("formatted_address"));
                                            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
                                            map.addMarker(myLocationMarker);




                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            });

                            queue.add(stringRequest);




                        }
                    }
                });


    }

    private void requestMyLocation() {
        LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        try {
            long minTime = 10000;
            float minDistance = 0;

            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            Log.d(TAG, "onLocationChanged");
                            showCurrentLocation(location);
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
                    }
            );


            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastLocation != null) {
                showCurrentLocation(lastLocation);
            }

            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            showCurrentLocation(location);
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
                    });
        } catch (SecurityException e) {
            e.printStackTrace();
        }



    }

    private void showCurrentLocation(Location location) {
        LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
//        map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

        showMyLocationMarker(location, "내위치");
    }

    private void showMyLocationMarker(Location location, String title) {
        if (myLocationMarker == null) {
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(new LatLng(location.getLatitude(), location.getLongitude()));
            myLocationMarker.title(title);
            myLocationMarker.snippet("GPS로 확인한 위치");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            map.addMarker(myLocationMarker);
        } else {
            myLocationMarker.position(new LatLng(location.getLatitude(), location.getLongitude()));
        }
    }

}
