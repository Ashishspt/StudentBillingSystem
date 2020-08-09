package com.example.studentbillingsystem.activities;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.studentbillingsystem.R;
import com.example.studentbillingsystem.fragments.FeesFragment;
import com.example.studentbillingsystem.fragments.FeesdummyFragment;
import com.example.studentbillingsystem.fragments.HomeFragment;
import com.example.studentbillingsystem.fragments.NotificationFragment;
import com.example.studentbillingsystem.fragments.ProfileFragment;
import com.example.studentbillingsystem.fragments.RoutineFragment;
import com.example.studentbillingsystem.helpers.AppActivity;
import com.fxn.BubbleTabBar;
import com.fxn.OnBubbleClickListener;

public class Dashboard extends AppActivity implements OnBubbleClickListener {
    private BubbleTabBar bubbleTabBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initializedView();
        initializedListners();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, new FeesdummyFragment()).commit();
    }

    @Override
    protected void initializedView() {
        bubbleTabBar=findViewById(R.id.bottom_navigation);


    }

    @Override
    protected void initializedListners() {
        bubbleTabBar.addBubbleListener(this);


    }


    @Override
    public void onBubbleClick(int i) {
        Fragment selectedFragment=null;
        switch (i){
            case R.id.fee:
//                selectedFragment=new FeesFragment();
                selectedFragment=new FeesdummyFragment();
                break;
            case    R.id.notification:
                selectedFragment=new NotificationFragment();
                break;
            case  R.id.routine:
                selectedFragment=new RoutineFragment();
                break;

            case  R.id.profile:
                selectedFragment=new ProfileFragment();
                break;

            case R.id.home:
                selectedFragment=new HomeFragment();
                break;

        }
        
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,selectedFragment).commit();

    }
}
