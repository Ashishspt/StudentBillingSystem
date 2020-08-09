package com.example.studentbillingsystem.helpers;

import android.view.View;

import androidx.fragment.app.Fragment;



public abstract class AppFragment extends Fragment {
    abstract protected void initializedView(View view);
    abstract protected void initializedListners();


}
