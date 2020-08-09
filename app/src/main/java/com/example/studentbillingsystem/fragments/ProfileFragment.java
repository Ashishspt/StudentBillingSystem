package com.example.studentbillingsystem.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentbillingsystem.R;
import com.example.studentbillingsystem.helpers.AppFragment;
import com.example.studentbillingsystem.helpers.AppRecyclleViewAdaapter;
import com.example.studentbillingsystem.helpers.DefineClassType;
import com.example.studentbillingsystem.helpers.ShowToast;
import com.example.studentbillingsystem.models.Profile;
import com.example.studentbillingsystem.presenters.ProfilePresenter;


public class ProfileFragment extends AppFragment implements ProfilePresenter.View {
    private ProfilePresenter profilePresenter;

    private RecyclerView recyclerView;

    private ProfileRecyclerViewAdapter profileRecyclerViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        initializedView(view);

        initializedListners();

        return view;
    }

    @Override
    protected void initializedView(View view) {

        recyclerView=view.findViewById(R.id.profile_recycler_view);


    }

    @Override
    protected void initializedListners() {
        profilePresenter =new ProfilePresenter(this);
        profilePresenter.userProfile();
        prepareRecyclerView();


    }
    private void prepareRecyclerView() {
        profileRecyclerViewAdapter=new ProfileRecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(profileRecyclerViewAdapter);
    }





    @Override
    public void onUserProfileSuccess(Profile profile) {
        profileRecyclerViewAdapter.add(profile);
        profileRecyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailure(String message) {
        ShowToast.withMessage(message);

    }

    public class ProfileRecyclerViewAdapter extends AppRecyclleViewAdaapter{
        public Profile myprofile;

    @Override
    public void add(Object object) {
        myprofile= DefineClassType.getType(object,Profile.class);

    }

    @Override
    public void clear() {

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile,parent,false);
        return new VHItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VHItem vhItem= (VHItem) holder;
        Profile.Details details=myprofile.getDetails();
        vhItem.name.setText(details.getStudentName());
        vhItem.semester.setText(details.getSemesterName());
        vhItem.matrix.setText(details.getMatrixId());
        vhItem.number.setText(details.getContactNumber());
        vhItem.address.setText(details.getAddress());
        vhItem.email.setText(details.getEmail());
        Profile.Details.AdmissionDTO admissionDTO=myprofile.getDetails().getAdmissionDTO();
        vhItem.addmission.setText(admissionDTO.getAdmissionDate());


    }

    @Override
    public int getItemCount() {
        if(myprofile!=null){
            if(myprofile.getDetails()!=null){
                return 1;
            }
        }


        return 0;
    }

    private class VHItem extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView email;
        private TextView matrix;
        private TextView semester;
        private TextView number;
        private TextView address;
        private TextView addmission;
        private TextView tvEditProfile;
        public VHItem(View view) {
            super(view);
            name=view.findViewById(R.id.std_name);
            email=view.findViewById(R.id.std_email);
            matrix=view.findViewById(R.id.std_matrix);
            semester=view.findViewById(R.id.std_semester);
            number=view.findViewById(R.id.std_number);
            address=view.findViewById(R.id.std_address);
            addmission=view.findViewById(R.id.std_addmission_date);
            tvEditProfile=view.findViewById(R.id.tvEditProfile);
            tvEditProfile.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {



        }
    }
}





}
