package com.first.demo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.first.demo.DbHelperClasses.DatabaseHelper;
import com.first.demo.DbHelperClasses.PatientDbHelper;
import com.first.demo.models.Patient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    FloatingActionButton addPatientBtn;
    RecyclerView patientRecycleView;

    public PatientsFragment() {
        // Required empty public constructor
    }


    public static PatientsFragment newInstance(String param1, String param2) {
        PatientsFragment fragment = new PatientsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patients, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPatientBtn = view.findViewById(R.id.addPatientBtn);
        addPatientBtn.setOnClickListener(this::toAddPatient);
        DatabaseHelper db = DatabaseHelper.getInstance(getContext());
//       PatientDbHelper.seedPatients(db);
        ArrayList<Patient> patients = PatientDbHelper.queryAll(db);




        patientRecycleView = view.findViewById(R.id.patientRecyclerView);
        patientRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));



        PatientRecycleViewAdaptor adaptor = new PatientRecycleViewAdaptor(getContext(), patients,position -> {
            FragmentManager fm = getParentFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment newFrag = new ViewPatientFragment();
                Patient p = patients.get(position);
                Bundle args = new Bundle();
                args.putString("id",String.valueOf(p.getId()));
                newFrag.setArguments(args);
                ft.replace(R.id.frameLayout,newFrag);
                ft.setReorderingAllowed(true);
                ft.addToBackStack("name");
                ft.commit();
        });
        patientRecycleView.setAdapter(adaptor);


//        assert getActivity() != null;




//
//        ArrayAdapter<Patient> arr = new ArrayAdapter<Patient>(getActivity().getApplicationContext(), R.layout.patient_list, patients);
//        list.setAdapter(arr);
//
//        patientRecycleView.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                FragmentManager fm = getParentFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                Fragment newFrag = new ViewPatientFragment();
//                Patient p = (Patient) adapterView.getItemAtPosition(i);
//                Bundle args = new Bundle();
//                args.putString("id",String.valueOf(p.getId()));
//                newFrag.setArguments(args);
//                ft.replace(R.id.frameLayout,newFrag);
//                ft.setReorderingAllowed(true);
//                ft.addToBackStack("name");
//                ft.commit();
//                ;
//
//            }
//        });
    }

    public void toAddPatient(View view){
        FragmentManager fm = getParentFragmentManager();
        fm.beginTransaction().setReorderingAllowed(true).addToBackStack("name").replace(R.id.frameLayout,new AddPatientFragment()).commit();
    }




}