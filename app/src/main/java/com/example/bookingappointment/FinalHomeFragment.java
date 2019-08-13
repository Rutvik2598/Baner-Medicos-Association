package com.example.bookingappointment;



import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.SearchManager;

import android.widget.SearchView.OnQueryTextListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FinalHomeFragment extends Fragment implements OnQueryTextListener, SearchView.OnQueryTextListener {

    List<DoctorCategory> listCategory;
    RecyclerViewAdapter recylerAdapter;
    private RecyclerView recyclerView;
    SearchView mySearchView;
    View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.home_fragment, container, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.app_bar);
        toolbar.setTitle("Select a category");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        mySearchView = (SearchView) v.findViewById(R.id.searchView);
        listCategory = new ArrayList<>();
        listCategory.add(new DoctorCategory("Ophthalmologist"));
        listCategory.add(new DoctorCategory("Clinical Psycholigist"));
        listCategory.add(new DoctorCategory("Dietitian"));
        listCategory.add(new DoctorCategory("Nutritionist"));
        listCategory.add(new DoctorCategory("Homeopathy"));
        listCategory.add(new DoctorCategory("Paediatrician"));
        listCategory.add(new DoctorCategory("Physiotherapist"));
        listCategory.add(new DoctorCategory("Surgeons"));
        listCategory.add(new DoctorCategory("Oncologist"));
        listCategory.add(new DoctorCategory("Dentist"));
        listCategory.add(new DoctorCategory("Gynaecologist"));
        listCategory.add(new DoctorCategory("Gynaecologist"));
        listCategory.add(new DoctorCategory("Gynaecologist"));
        listCategory.add(new DoctorCategory("Gynaecologist"));
        listCategory.add(new DoctorCategory("Gynaecologist"));
        listCategory.add(new DoctorCategory("Gynaecologist"));
        listCategory.add(new DoctorCategory("Gynaecologist"));


        for(int i = 0 ; i < listCategory.size() ; i++){
            DoctorCategory category = listCategory.get(i);

            Log.d(category.getCategory().toLowerCase(),"In Method");
        }

        //v = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview_id);
        recylerAdapter = new RecyclerViewAdapter(getActivity(),listCategory);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(recylerAdapter);


        mySearchView.setOnQueryTextListener(this);


        return v;



    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        String userInput = s.toLowerCase();
        Log.d(userInput,"user Input");
        List<DoctorCategory> newList = new ArrayList<>();
        Iterator<DoctorCategory> itr = listCategory.iterator();


        for(DoctorCategory category : listCategory) {
            Log.d(category.toString().toLowerCase(),"In loop");
            if(category.getCategory().toLowerCase().contains(userInput)){
                newList.add(category);
            }
        }
        recylerAdapter.updateList(newList);
        return true;
    }
}

