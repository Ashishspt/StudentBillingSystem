package com.example.studentbillingsystem.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentbillingsystem.R;
import com.example.studentbillingsystem.helpers.AppFragment;
import com.example.studentbillingsystem.helpers.AppRecyclleViewAdaapter;
import com.example.studentbillingsystem.helpers.DefineClassType;
import com.example.studentbillingsystem.helpers.ShowToast;
import com.example.studentbillingsystem.helpers.SpacesItemDecoration;
import com.example.studentbillingsystem.models.Subject;
import com.example.studentbillingsystem.presenters.SubjectPresenter;

import java.util.List;


public class HomeFragment extends AppFragment implements SubjectPresenter.View {
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private SubjectPresenter subjectPresenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        initializedView(view);
        initializedListners();

        return view;
    }


    @Override
    protected void initializedView(View view) {
        recyclerView=view.findViewById(R.id.subject_recycler_view);


    }

    @Override
    protected void initializedListners() {
        subjectPresenter=new SubjectPresenter(this);
        subjectPresenter.Subject();
        prepareRecyclerView();

    }
    private void prepareRecyclerView() {
        homeRecyclerViewAdapter=new HomeRecyclerViewAdapter();

        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(homeRecyclerViewAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);


    }

    @Override
    public void onSubjectSuccess(Subject subject) {
        homeRecyclerViewAdapter.add(subject);
        homeRecyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void onFailure(String message) {
        ShowToast.withMessage(message);

    }

    public  class HomeRecyclerViewAdapter extends AppRecyclleViewAdaapter {

public Subject subject;


        @Override
        public void add(Object object) {
            subject= DefineClassType.getType(object,Subject.class);


        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject,parent,false);
            return new VHItem(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            VHItem vhItem= (VHItem) holder;

            List<Subject.Detail> detail=subject.getDetails();
            vhItem.sub_name.setText(detail.get(position).getSubjectName());
            vhItem.sub_code.setText(detail.get(position).getSubjectCode());
            vhItem.credit_hour.setText(detail.get(position).getSubjectCredit());



        }

        @Override
        public int getItemCount() {
            if (subject != null) {
                return subject.getDetails().size();
                }


                //+1

            return 0;

        }

        private class VHItem extends RecyclerView.ViewHolder {
            private TextView sub_name;
            private TextView sub_code;
            private TextView credit_hour;
            public VHItem(View itemView) {
                super(itemView);
                sub_name=itemView.findViewById(R.id.subject_name);
                sub_code=itemView.findViewById(R.id.subject_code);
                credit_hour=itemView.findViewById(R.id.credithour);
            }
        }
    }
}
