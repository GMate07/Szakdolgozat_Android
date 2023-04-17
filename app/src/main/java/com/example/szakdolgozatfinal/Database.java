package com.example.szakdolgozatfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Database extends AppCompatActivity {

    private RecyclerView courseRV;

    // variable for our adapter
    // class and array list
    private CourseAdapter adapter;
    private ArrayList<CourseModel> courseModelArrayList;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://szakdolgozatfinal-default-rtdb.europe-west1.firebasedatabase.app");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        courseRV = findViewById(R.id.idRVCourses);

        final Button button = findViewById(R.id.button);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Database.this, "Megnyitottad az adatbázist!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Database.this, Database.class));
                finish();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Database.this, "Megnyitottad a Teachable Machinet!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Database.this, TeachableMachine.class));
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Database.this, "Megnyitottad a profilodat!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Database.this, Profile.class));
                finish();
            }
        });

        databaseReference.child("novenyvedoszerek").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> snapshotValue = snapshot.getChildren();
                // calling method to
                // build recycler view.
                buildRecyclerView(snapshotValue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<CourseModel> filteredlist = new ArrayList<CourseModel>();

        // running a for loop to compare elements.
        for (CourseModel item : courseModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getCourseName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    private void buildRecyclerView(Iterable<DataSnapshot> currentSnapshot) {

        // below line we are creating a new array list
        courseModelArrayList = new ArrayList<CourseModel>();

        // below line is to add data to our array list.
        for (DataSnapshot ds : currentSnapshot) {
            StringBuilder sb = new StringBuilder();

            for (DataSnapshot regulation : ds.child("regulations").getChildren()) {
                sb.append(regulation.child("name").getValue(String.class));
                sb.append("\n");
                sb.append(regulation.child("harmfulFactors").getValue(String.class));
                sb.append("\n");
                sb.append(regulation.child("juiceQuantity").getValue(String.class));
                sb.append("\n");
                sb.append(regulation.child("foodWaitPeriod").getValue(String.class));
                sb.append("\n");
                sb.append(regulation.child("workWaitPeriod").getValue(String.class));
                sb.append("\n");
                sb.append(regulation.child("aerialApplication").getValue(String.class));
                sb.append("\n\n");
            }

            String regulationString = sb.toString();
            if (sb.length() > 0) {
                sb.delete(0, sb.length() - 1);
            }

            sb.append(ds.child("license").child("licenseNumber").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("license").child("licenseType").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("license").child("licenseValidity").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("license").child("licenseOwner").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("license").child("licenseDomesticOwner").getValue(String.class));
            sb.append("\n\n");

            String licenseString = sb.toString();
            if (sb.length() > 0) {
                sb.delete(0, sb.length() - 1);
            }

            sb.append(ds.child("generalData").child("hungarianDistributor").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("generalData").child("manufacturer").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("generalData").child("formulation").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("generalData").child("facturingCategory").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("generalData").child("allowedInEcology").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("generalData").child("packaging").getValue(String.class));
            sb.append("\n\n");

            String generalData = sb.toString();
            if (sb.length() > 0) {
                sb.delete(0, sb.length() - 1);
            }

            for (DataSnapshot restriction : ds.child("restrictions").getChildren()) {
                sb.append(restriction.child("name").getValue(String.class));
                sb.append("\n");
                sb.append(restriction.child("restriction").getValue(String.class));
                sb.append("\n\n");
            }

            String restrictionString = sb.toString();
            if (sb.length() > 0) {
                sb.delete(0, sb.length() - 1);
            }

            sb.append(ds.child("warning").child("poLD").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("warning").child("dangerInWater").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("warning").child("dangerOnBees").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("warning").child("beeProtectionTechnology").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("warning").child("sSentences").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("warning").child("hSentences").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("warning").child("pSentences").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("warning").child("gearTrainers").getValue(String.class));
            sb.append("\n");
            sb.append(ds.child("warning").child("gearApplicators").getValue(String.class));
            sb.append("\n\n");

            CourseModel model = new CourseModel(
                    ds.child("name").getValue(String.class),
                    ds.child("ingredients").getValue(String.class),
                    regulationString,
                    licenseString,
                    generalData,
                    ds.child("allowedInAgriculture").getValue(String.class),
                    restrictionString,
                    sb.toString()
            );

            courseModelArrayList.add(model);
        }

        // initializing our adapter class.
        adapter = new CourseAdapter(courseModelArrayList, Database.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        courseRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        courseRV.setAdapter(adapter);
    }
}