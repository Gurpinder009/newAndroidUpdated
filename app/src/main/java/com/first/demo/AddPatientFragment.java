package com.first.demo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.first.demo.DbHelperClasses.DatabaseHelper;
import com.first.demo.DbHelperClasses.PatientDbHelper;
import com.first.demo.models.Patient;

import java.util.Date;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPatientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPatientFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Button registerButton;
    EditText firstNameInput;
    EditText lastNameInput;
    EditText emailInput;
    EditText phoneInput;
    DatePicker dobInput;
    EditText genderInput;
    EditText addressInput;
    EditText emergencyContactInput;
    EditText bloodTypeInput;
    EditText heightInput;
    EditText weightInput;
    EditText allergiesInput;



    public AddPatientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPatientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPatientFragment newInstance(String param1, String param2) {
        AddPatientFragment fragment = new AddPatientFragment();
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
        return inflater.inflate(R.layout.fragment_add_patient, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        registerButton = view.findViewById(R.id.registerPatientBtn);
        registerButton.setOnClickListener(this::onRegisterPatient);
        firstNameInput = view.findViewById(R.id.firstNameInput);
        lastNameInput = view.findViewById(R.id.lastNameInput);
        emailInput = view.findViewById(R.id.emailInput);
        phoneInput = view.findViewById(R.id.phoneInput);
        dobInput = view.findViewById(R.id.dobInput);
        genderInput = view.findViewById(R.id.genderInput);
        addressInput = view.findViewById(R.id.addressInput);
        emergencyContactInput = view.findViewById(R.id.emergencyContactInput);
        bloodTypeInput = view.findViewById(R.id.bloodTypeInput);
        heightInput = view.findViewById(R.id.heightInput);
        weightInput = view.findViewById(R.id.weightInput);
        allergiesInput = view.findViewById(R.id.allergiesInput);





    }




    public void onRegisterPatient(View view) {
        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String email = emailInput.getText().toString();
        String phone = phoneInput.getText().toString();
        String gender = genderInput.getText().toString();
        String address = addressInput.getText().toString();
        String emergenecyContact = emergencyContactInput.getText().toString();
        String bloodType = bloodTypeInput.getText().toString();
        String height = heightInput.getText().toString();
        String weight = weightInput.getText().toString();
        String allergies = allergiesInput.getText().toString();
        int day = dobInput.getDayOfMonth();
        int month = dobInput.getMonth();
        int year = dobInput.getYear();

        DatabaseHelper db = DatabaseHelper.getInstance(getContext());
        PatientDbHelper.add(db,new Patient(18,firstName+" "+lastName,email,phone,new java.sql.Date(year,month,day),address,gender,emergenecyContact,bloodType,height,weight,allergies,1));
        FragmentManager fm = getParentFragmentManager();
        fm.popBackStack();

    }
}


//
//
//package com.first.demo;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//
//import com.first.demo.DbHelperClasses.DatabaseHelper;
//import com.first.demo.DbHelperClasses.PatientDbHelper;
//import com.first.demo.models.Patient;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link UpdatePatientFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class UpdatePatientFragment extends Fragment {
//
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//
//
//    EditText firstNameInput;
//    EditText lastNameInput;
//    EditText emailInput;
//    EditText phoneInput;
//    DatePicker dobInput;
//    EditText genderInput;
//    EditText addressInput;
//    EditText emergencyContactInput;
//    EditText bloodTypeInput;
//    EditText heightInput;
//    EditText weightInput;
//    EditText allergiesInput;
//    Button updateBtn;
//
//
//
//    public UpdatePatientFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment AddPatientFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static AddPatientFragment newInstance(String param1, String param2) {
//        AddPatientFragment fragment = new AddPatientFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_add_patient, container, false);
//    }
//
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        firstNameInput = view.findViewById(R.id.firstNameInput);
//        lastNameInput = view.findViewById(R.id.lastNameInput);
//        emailInput = view.findViewById(R.id.emailInput);
//        phoneInput = view.findViewById(R.id.phoneInput);
//        dobInput = view.findViewById(R.id.dobInput);
//        genderInput = view.findViewById(R.id.genderInput);
//        addressInput = view.findViewById(R.id.addressInput);
//        emergencyContactInput = view.findViewById(R.id.emergencyContactInput);
//        bloodTypeInput = view.findViewById(R.id.bloodTypeInput);
//        heightInput = view.findViewById(R.id.heightInput);
//        weightInput = view.findViewById(R.id.weightInput);
//        allergiesInput = view.findViewById(R.id.allergiesInput);
//        updateBtn = view.findViewById(R.id.udpatePatientBtn);
//        updateBtn.setOnClickListener(this::updatePatient);
//
//
//
//
//
//
//
//
//        DatabaseHelper db = DatabaseHelper.getInstance(getContext());
//
//
//        assert getArguments() != null;
//        String value = getArguments().getString("id");
//        Log.i("INFO", "onViewCreated: "+value);
//
//
//        assert value != null;
//        Patient patient = PatientDbHelper.query(db,Integer.parseInt(value));
//        String[] nameString = patient.getName().split(" ",2);
//        String firstName = nameString[0];
//        String lastName = nameString[1];
//        firstNameInput.setText(firstName);
//        lastNameInput.setText(lastName);
//        emailInput.setText(patient.getEmailAddress());
//        phoneInput.setText(patient.getPhoneNumber());
//        genderInput.setText(patient.getGender());
//        addressInput.setText(patient.getAddress());
//        emergencyContactInput.setText(patient.getEmergencyContact());
//        bloodTypeInput.setText(patient.getBloodType());
//        heightInput.setText(patient.getHeight());
//        weightInput.setText(patient.getWeight());
//        allergiesInput.setText(patient.getAllergies());
//
//
//
//
//
//
//
//
//
//    }
//
//
//    public void updatePatient(View view){
//        Log.i("Infoooooooooooooooooooooooooooooooo", "updatePatient:???????? ");
//        String firstName = firstNameInput.getText().toString();
//        String lastName = lastNameInput.getText().toString();
//        String email = emailInput.getText().toString();
//        String phone = phoneInput.getText().toString();
//        String gender = genderInput.getText().toString();
//        String address = addressInput.getText().toString();
//        String emergenecyContact = emergencyContactInput.getText().toString();
//        String bloodType = bloodTypeInput.getText().toString();
//        String height = heightInput.getText().toString();
//        String weight = weightInput.getText().toString();
//        String allergies = allergiesInput.getText().toString();
//        int day = dobInput.getDayOfMonth();
//        int month = dobInput.getMonth();
//        int year = dobInput.getYear();
//
//        DatabaseHelper db = DatabaseHelper.getInstance(getContext());
//        PatientDbHelper.update(db,new Patient(1,firstName+" "+lastName,email,phone,new java.sql.Date(year,month,day),address,gender,emergenecyContact,bloodType,height,weight,allergies,1));
//        FragmentManager fm = getParentFragmentManager();
//        fm.popBackStack();
//    }
//
//
//
//
//}
