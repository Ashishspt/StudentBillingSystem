package com.example.studentbillingsystem.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentbillingsystem.R;
import com.example.studentbillingsystem.helpers.AppFragment;
import com.example.studentbillingsystem.helpers.AppRecyclleViewAdaapter;
import com.example.studentbillingsystem.helpers.Routine;


public class Routine_Tuesday extends AppFragment {
    private RecyclerView recyclerView;
    private MondayRecyclerViewAdapter mondayRecyclerViewAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_routine__monday, container, false);
        initializedView(view);
        initializedListners();
        prepareRecyclerView();
        return view;
    }




    @Override
    protected void initializedView(View view) {
        recyclerView=view.findViewById(R.id.recycler_view);

    }

    @Override
    protected void initializedListners() {

    }
    private void prepareRecyclerView() {
        mondayRecyclerViewAdapter=new MondayRecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mondayRecyclerViewAdapter);


    }

    public  class MondayRecyclerViewAdapter extends AppRecyclleViewAdaapter{


        @Override
        public void add(Object object) {

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_routine,parent,false);
            return new VHItem(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
           VHItem vhItem= (VHItem) holder;
            vhItem.sub_name.setText(Routine.subject_name[position]);
            vhItem.time.setText(Routine.sub_time[position]);
        }

        @Override
        public int getItemCount() {
            return Routine.subject_name.length;
        }

        private class VHItem extends RecyclerView.ViewHolder {
            private TextView sub_name;
            private TextView time;
            public VHItem(View itemView) {
                super(itemView);
                sub_name=itemView.findViewById(R.id.sub_title);
                time=itemView.findViewById(R.id.sub_time);
            }
        }
    }

    }
