package com.first.demo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.first.demo.DbHelperClasses.DatabaseHelper;
import com.first.demo.DbHelperClasses.PatientDbHelper;
import com.first.demo.models.Patient;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ViewPatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewPatientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView firstName;
    TextView lastName;
    TextView email;
    TextView phone;
    TextView gender;
    TextView emergencyContact;
    TextView address;
    TextView dob;
    TextView bloodType;
    TextView height;
    TextView weight;
    TextView allergies;

    Patient patient;

    Button deletePatientBtn;
    Button updatePatientBtn;






    public ViewPatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewPatientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewPatientFragment newInstance(String param1, String param2) {
        ViewPatientFragment fragment = new ViewPatientFragment();
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
        return inflater.inflate(R.layout.fragment_view_patient, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstName= view.findViewById(R.id.firstName);
        lastName= view.findViewById(R.id.lastName);
        email= view.findViewById(R.id.email);
        phone= view.findViewById(R.id.phone);
        dob= view.findViewById(R.id.dob);
        gender= view.findViewById(R.id.gender);
        address= view.findViewById(R.id.address);
        emergencyContact= view.findViewById(R.id.emergencyContact);
        bloodType= view.findViewById(R.id.bloodType);
        height = view.findViewById(R.id.height);
        weight = view.findViewById(R.id.weight);
        allergies = view.findViewById(R.id.allergies);
        deletePatientBtn = view.findViewById(R.id.removePatientBtn);
        updatePatientBtn = view.findViewById(R.id.udpatePatientBtn);
        updatePatientBtn.setOnClickListener(this::updatePatient);
        deletePatientBtn.setOnClickListener(this::deletePatient);

        assert getArguments() != null;
        String value = getArguments().getString("id");
        DatabaseHelper db = DatabaseHelper.getInstance(getContext());
        assert value != null;
        patient = PatientDbHelper.query(db,Integer.parseInt(value));

        updatePatientDetails();




    }
    public void updatePatient(View view){

        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment newFrag = new UpdatePatientFragment();
        Bundle args = new Bundle();
        args.putString("id",String.valueOf(patient.getId()));
        newFrag.setArguments(args);
        ft.replace(R.id.frameLayout,newFrag);
        ft.addToBackStack("name");
        ft.setReorderingAllowed(true);
        ft.commit();

    }


    public void deletePatient(View view){
        DatabaseHelper db = DatabaseHelper.getInstance(getContext());
        PatientDbHelper.delete(db,patient.getId());
        FragmentManager fm = getParentFragmentManager();
        fm.popBackStack();

    }

    public void updatePatientDetails(){

        String[] str= patient.getName().split(" ",2);
        firstName.setText("First Name"+str[0]);
        lastName.setText("Last Name: "+str[1]);
        email.setText("Email: "+patient.getEmailAddress());
        phone.setText("Phone: "+patient.getPhoneNumber());
        gender.setText("Gender: "+patient.getGender());
        dob.setText("Date of birth: "+patient.getDob().toString());
        address.setText("Address: "+patient.getAddress());
        emergencyContact.setText("Emergency Contact: "+patient.getEmergencyContact());
        bloodType.setText("Blood Type: "+patient.getBloodType());
        height.setText("height: "+patient.getHeight());
        weight.setText("Blood Type: "+patient.getWeight());
        allergies.setText("Blood Type: "+patient.getAllergies());
    }
}




