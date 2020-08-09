package com.example.studentbillingsystem.fragments;


import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentbillingsystem.R;
import com.example.studentbillingsystem.helpers.AppFragment;
import com.example.studentbillingsystem.helpers.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class RoutineFragment extends AppFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_routine, container, false);
        initializedView(view);
        initializedListners();
        return view;

    }



    @Override
    protected void initializedView(View view) {
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager=view.findViewById(R.id.view_pager);

    }

    @Override
    protected void initializedListners() {
        viewPagerAdapter=new ViewPagerAdapter(getActivity().getSupportFragmentManager());

        //tab layout fragment
        viewPagerAdapter.AddFragment(new Routine_Monday(),"Mon");
        viewPagerAdapter.AddFragment(new Routine_Tuesday(),"Tue");
        viewPagerAdapter.AddFragment(new Routine_Wednesday(),"Wed");
        viewPagerAdapter.AddFragment(new Routine_Thursday(),"Thu");
        viewPagerAdapter.AddFragment(new Routine_Friday(),"Fri");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);





    }
}
