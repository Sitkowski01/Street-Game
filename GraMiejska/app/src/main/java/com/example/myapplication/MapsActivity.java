package com.example.myapplication;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.example.myapplication.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Polyline previousPolyline;
    private List<Marker> markersList = new ArrayList<Marker>();
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LatLng ZUTLat = new LatLng(53.424459, 14.535451);
    LatLng walyChrobregoLat = new LatLng(53.429872, 14.565118);
    LatLng pomnikOrlowSzczecinLat = new LatLng(53.442761, 14.537694);
    LatLng amfiteatrLat = new LatLng(53.445149, 14.530404);
    LatLng pomnikKrzysztofaJarzynyLat = new LatLng(53.427763, 14.574762);
    LatLng bulwarySzczecinskieLat = new LatLng(53.422119, 14.561800);
    LatLng marinaLat = new LatLng(53.400222, 14.617926);
    LatLng kinoPionierLat = new LatLng(53.426487, 14.545675);
    LatLng zamekKsiazatPomorskichWSzczecinieLat = new LatLng(53.425963, 14.560419);
    LatLng stareMiastoLat = new LatLng(53.424956, 14.561134);
    LatLng ogrodRozanyLat = new LatLng(53.446334, 14.525802);
    LatLng filharmoniaLat = new LatLng(53.429039, 14.557917);
    LatLng placAdamowiczaLat = new LatLng(53.429613, 14.551533);
    LatLng jezioroGlebokieLat = new LatLng(53.472396, 14.484883);
    LatLng jezioroSzmaragdoweLat = new LatLng(53.372846, 14.624553);
    LatLng cafe22Lat = new LatLng(53.432670, 14.555398);
    LatLng centrumDialoguPrzelomyLat = new LatLng(53.428574, 14.558258);
    LatLng dawnyDomGrabarzaLat = new LatLng(53.432623, 14.568514);
    LatLng domRzeznikaZNiebuszewaLat = new LatLng(53.446575, 14.542466);
    LatLng dzwigozauryLat = new LatLng(53.426332, 14.568590);
    LatLng bramaKrolewskaLat = new LatLng(53.428334, 14.556697);
    LatLng budynekPoStarejGazowniLat = new LatLng(53.447200, 14.555068);
    LatLng wiezaQuistorpaLat = new LatLng(53.464535, 14.516117);
    LatLng cmentarzCentralnyKaplicaLat = new LatLng(53.417490, 14.523997);
    public void drawRoute(View view) {
        // Lista wszystkich markerów
        List<LatLng> allMarkers = Arrays.asList(
                ZUTLat, walyChrobregoLat, pomnikOrlowSzczecinLat, amfiteatrLat, pomnikKrzysztofaJarzynyLat,
                bulwarySzczecinskieLat, marinaLat, kinoPionierLat, zamekKsiazatPomorskichWSzczecinieLat,
                stareMiastoLat, ogrodRozanyLat, filharmoniaLat, placAdamowiczaLat, jezioroGlebokieLat,
                jezioroSzmaragdoweLat, cafe22Lat, centrumDialoguPrzelomyLat, dawnyDomGrabarzaLat,
                domRzeznikaZNiebuszewaLat, dzwigozauryLat, bramaKrolewskaLat, budynekPoStarejGazowniLat,
                wiezaQuistorpaLat, cmentarzCentralnyKaplicaLat);

        // Losowe wybieranie dwóch różnych markerów
        Random random = new Random();
        int index1 = random.nextInt(allMarkers.size());
        int index2 = random.nextInt(allMarkers.size() - 1);
        if (index2 >= index1) {
            index2++;
        }
        LatLng marker1 = allMarkers.get(index1);
        LatLng marker2 = allMarkers.get(index2);

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("")
                .build();

        // Tworzenie obiektu DirectionsResult za pomocą Directions API
        DirectionsResult directionsResult;
        try {
            directionsResult = DirectionsApi.newRequest(context)
                    .origin(marker1.latitude + "," + marker1.longitude)
                    .destination(marker2.latitude + "," + marker2.longitude)
                    .await();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Pobieranie pierwszej trasy z DirectionsResult
        DirectionsRoute route = directionsResult.routes[0];
        List<LatLng> points = new ArrayList<>();
        // Pobieranie punktów z trasy
        for (com.google.maps.model.LatLng latLng : route.overviewPolyline.decodePath()) {
            points.add(new LatLng(latLng.lat, latLng.lng));
        }

        // Rysowanie linii między punktami na mapie
        if (previousPolyline != null) {
            previousPolyline.remove();
        }

// Rysowanie linii między punktami na mapie
        PolylineOptions lineOptions = new PolylineOptions();
        lineOptions.addAll(points);
        lineOptions.width(10);
        lineOptions.color(Color.BLUE);

        previousPolyline = mMap.addPolyline(lineOptions);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Add "Quit" button to the menu
        MenuItem quitMenuItem = menu.add(Menu.NONE, R.id.menu_quit, Menu.NONE, "Quit");
        quitMenuItem.setIcon(R.drawable.ic_quit); // Set the icon for the "Quit" button
        quitMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER); // Change to SHOW_AS_ACTION_ALWAYS if you want it to always appear in the action bar

        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button quitButton = findViewById(R.id.btn_quit);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the activity and exit the application
            }
        });

        Button optionsButton = findViewById(R.id.btn_options);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOptionsDialog(); // Open the options dialog
            }
        });
    }

    private void openOptionsDialog() {
        // Start the OptionsActivity
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
//     * it inside the SupportMapFragment. This method will only be triggered once the user has
//     * installed Google Play services and returned to the app.
//     */


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        MarkerOptions marker = new MarkerOptions();

        // TWORZENIE MARKERÓW

        Marker ZUT = mMap.addMarker(marker
                .position(ZUTLat)
                .title("Zut"));

        Marker walyChrobrego = mMap.addMarker(marker
                .position(walyChrobregoLat)
                .title("Wały Chrobrego"));

        Marker pomnikOrlowSzczecin = mMap.addMarker(marker
                .position(pomnikOrlowSzczecinLat)
                .title("Pomnik Orłów"));

        Marker amfiteatr = mMap.addMarker(marker
                .position(amfiteatrLat)
                .title("Amfiteatr"));

        Marker pomnikKrzysztofaJarzyny = mMap.addMarker(marker
                .position(pomnikKrzysztofaJarzynyLat)
                .title("Pomnik Krzysztofa Jarzyny"));

        Marker bulwarySzczecinskie = mMap.addMarker(marker
                .position(bulwarySzczecinskieLat)
                .title("Bulwary Szczecinskie"));

        Marker marina = mMap.addMarker(marker
                .position(marinaLat)
                .title("Marina"));

        Marker kinoPionier = mMap.addMarker(marker
                .position(kinoPionierLat)
                .title("Kino Pionier"));

        Marker zamekKsiazatPomorskichWSzczecinie = mMap.addMarker(marker
                .position(zamekKsiazatPomorskichWSzczecinieLat)
                .title("Zamek Książąt Pomorskich w Szczecinie"));

        Marker stareMiasto = mMap.addMarker(marker
                .position(stareMiastoLat)
                .title("Stare Miasto"));

        Marker ogrodRozany = mMap.addMarker(marker
                .position(ogrodRozanyLat)
                .title("Ogród Różany"));

        Marker filharmonia = mMap.addMarker(marker
                .position(filharmoniaLat)
                .title("Filharmonia"));

        Marker placAdamowicza = mMap.addMarker(marker
                .position(placAdamowiczaLat)
                .title("Plac Adamowicza"));

        Marker jezioroGlebokie = mMap.addMarker(marker
                .position(jezioroGlebokieLat)
                .title("Jezioro Głebokie"));

        Marker jezioroSzmaragdowe = mMap.addMarker(marker
                .position(jezioroSzmaragdoweLat)
                .title("Jezioro Szmargdowe"));

        Marker cafe22 = mMap.addMarker(marker
                .position(cafe22Lat)
                .title("Cafe22"));

        Marker centrumDialoguPrzelomy = mMap.addMarker(marker
                .position(centrumDialoguPrzelomyLat)
                .title("Centrum Dialogu Przełomy"));

        Marker dawnyDomGrabarza = mMap.addMarker(marker
                .position(dawnyDomGrabarzaLat)
                .title("Dawny Dom Grabarza"));

        Marker domRzeznikaZNiebuszewa = mMap.addMarker(marker
                .position(domRzeznikaZNiebuszewaLat)
                .title("Dom Rzeźnika z Niebuszewa"));

        Marker dzwigozaury = mMap.addMarker(marker
                .position(dzwigozauryLat)
                .title("Dźwigozaury"));

        Marker bramaKrolewska = mMap.addMarker(marker
                .position(bramaKrolewskaLat)
                .title("Brama Królewska(Wedel)"));

        Marker budynekPoStarejGazowni = mMap.addMarker(marker
                .position(budynekPoStarejGazowniLat)
                .title("Budynek po Starej Gazowni"));

        Marker wiezaQuistorpa = mMap.addMarker(marker
                .position(wiezaQuistorpaLat)
                .title("Wieża Quistorpa"));

        Marker cmentarzCentralnyKaplica = mMap.addMarker(marker
                .position(cmentarzCentralnyKaplicaLat)
                .title("Cmentarz Centralny Kaplica"));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(ZUTLat,15);
        googleMap.animateCamera(cameraUpdate);

        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(App.getContext(), R.raw.map_style));


        // DODANIE MARKERÓW DO LISTY W CELU UTWORZENIA ALGORYTMU LOSOWANIA

        markersList.add(ZUT);
        markersList.add(walyChrobrego);
        markersList.add(pomnikOrlowSzczecin);
        markersList.add(amfiteatr);
        markersList.add(pomnikKrzysztofaJarzyny);
        markersList.add(bulwarySzczecinskie);
        markersList.add(marina);
        markersList.add(kinoPionier);
        markersList.add(zamekKsiazatPomorskichWSzczecinie);
        markersList.add(stareMiasto);
        markersList.add(ogrodRozany);
        markersList.add(filharmonia);
        markersList.add(placAdamowicza);
        markersList.add(jezioroGlebokie);
        markersList.add(jezioroSzmaragdowe);
        markersList.add(cafe22);
        markersList.add(centrumDialoguPrzelomy);
        markersList.add(dawnyDomGrabarza);
        markersList.add(domRzeznikaZNiebuszewa);
        markersList.add(dzwigozaury);
        markersList.add(bramaKrolewska);
        markersList.add(budynekPoStarejGazowni);
        markersList.add(wiezaQuistorpa);
        markersList.add(cmentarzCentralnyKaplica);

        Random random = new Random();
        List<LatLng> markerPoints = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(markersList.size());
            LatLng latLng = markersList.get(randomIndex).getPosition();
            markerPoints.add(latLng);
            markersList.remove(randomIndex);
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker marker){
                String markertitle = marker.getTitle();

                Intent i=new Intent(MapsActivity.this,DetailsActivity.class);
                i.putExtra("title",markertitle);
                startActivity(i);

                return false;
            }
        });
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }
            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.custom_info_window, null);
                TextView title = (TextView) view.findViewById(R.id.title);
                TextView snippet = (TextView) view.findViewById(R.id.snippet);

                title.setText(marker.getTitle());
                snippet.setText(marker.getSnippet());

                return view;
            }
        });
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getPosition().equals(ZUTLat)) {
                    marker.setTitle("ZUT");
                    marker.setSnippet("Parking, aleja Piastów 17, 70-310 Szczecin");
                } else if (marker.getPosition().equals(walyChrobregoLat)) {
                    marker.setTitle("Wały Chrobrego");
                    marker.setSnippet("Wały Chrobrego, 71-899 Szczecin");
                } else if (marker.getPosition().equals(pomnikOrlowSzczecinLat)) {
                    marker.setTitle("Pomnik Orłów");
                    marker.setSnippet("Pomnik Orłów na Jasnych BŁoniach");
                } else if (marker.getPosition().equals(amfiteatrLat)) {
                    marker.setTitle("Amfiteatr");
                    marker.setSnippet("Juliana Fałata, 70-496 Szczecin");
                } else if (marker.getPosition().equals(pomnikKrzysztofaJarzynyLat)) {
                    marker.setTitle("Pomnik Krzysztofa Jarzyny");
                    marker.setSnippet("Krzysztof Jarzyna Monument, 70-655 Szczecin");
                } else if (marker.getPosition().equals(bulwarySzczecinskieLat)) {
                    marker.setTitle("Bulwary Szczecinskie");
                    marker.setSnippet("Nabrzeże Celne, 71-899 Szczecin");
                } else if (marker.getPosition().equals(marinaLat)) {
                    marker.setTitle("Marina");
                    marker.setSnippet("Wyspa Grodzka, Grodzka 1,70-655 Szczecin");
                } else if (marker.getPosition().equals(kinoPionierLat)) {
                    marker.setTitle("Kino Pionier");
                    marker.setSnippet("al. Wojska Polskiego 2, 70-471 Szczecin");
                } else if (marker.getPosition().equals(zamekKsiazatPomorskichWSzczecinieLat)) {
                    marker.setTitle("Zamek Książąt Pomorskich w Szczecinie");
                    marker.setSnippet("ul. Korsarzy 34, 70-540 Szczecin");
                } else if (marker.getPosition().equals(stareMiastoLat)) {
                    marker.setTitle("Stare Miasto");
                    marker.setSnippet("Stare miasto");
                } else if (marker.getPosition().equals(ogrodRozanyLat)) {
                    marker.setTitle("Ogród Różany");
                    marker.setSnippet("Pawła Jasienicy 8, 71-899 Szczecin");
                } else if (marker.getPosition().equals(filharmoniaLat)) {
                    marker.setTitle("Filharmonia");
                    marker.setSnippet("Małopolska 48, 70-515 Szczecin");
                } else if (marker.getPosition().equals(placAdamowiczaLat)) {
                    marker.setTitle("Plac Adamowicza");
                    marker.setSnippet("Plac Pawła Adamowicza70-413 Szczecin");
                } else if (marker.getPosition().equals(jezioroGlebokieLat)) {
                    marker.setTitle("Jezioro Głebokie");
                    marker.setSnippet("W stronę Arkonki/Niemierzyna");
                } else if (marker.getPosition().equals(cafe22Lat)) {
                    marker.setTitle("Cafe22");
                    marker.setSnippet("Plac Rodła 8, 70-419 Szczecin");
                } else if (marker.getPosition().equals(centrumDialoguPrzelomyLat)) {
                    marker.setTitle("Centrum Dialogu Przełomy");
                    marker.setSnippet("Plac Solidarności 1, 70-515 Szczecin");
                } else if (marker.getPosition().equals(dawnyDomGrabarzaLat)) {
                    marker.setTitle("Dawny Dom Grabarza");
                    marker.setSnippet("Storrady-Świętosławy 2, 71-899 Szczecin");
                } else if (marker.getPosition().equals(domRzeznikaZNiebuszewaLat)) {
                    marker.setTitle("Dom Rzeźnika z Niebuszewa");
                    marker.setSnippet("Ulicy Wilsona 7 (dzisiejsza Niemierzyńska 7)");
                } else if (marker.getPosition().equals(dzwigozauryLat)) {
                    marker.setTitle("Dźwigozaury");
                    marker.setSnippet("Tadeusza Apolinarego Wendy 6, 71-899 Szczecin");
                } else if (marker.getPosition().equals(bramaKrolewskaLat)) {
                    marker.setTitle("Brama Królewska(Wedel)");
                    marker.setSnippet("Plac Brama Portowa 2, 70-001 Szczecin");
                } else if (marker.getPosition().equals(budynekPoStarejGazowniLat)) {
                    marker.setTitle("Budynek po Starej Gazowni");
                    marker.setSnippet("ul, Adama Asnyka, Szczecin");
                } else if (marker.getPosition().equals(wiezaQuistorpaLat)) {
                    marker.setTitle("Wieża Quistorpa");
                    marker.setSnippet("Wzgórze Arkony, Park Leśny Arkoński");
                } else if (marker.getPosition().equals(cmentarzCentralnyKaplicaLat)) {
                    marker.setTitle("Cmentarz Centralny Kaplica");
                    marker.setSnippet("Ku Słońcu 125A, 71-080 Szczecin");
                }   else if (marker.getPosition().equals(jezioroSzmaragdoweLat)) {
                    marker.setTitle("Jezioro Szmaragdowe");
                    marker.setSnippet("Szczecin Zdroje");
                }
                marker.showInfoWindow();
                return true;
            }

        });


    }

}
