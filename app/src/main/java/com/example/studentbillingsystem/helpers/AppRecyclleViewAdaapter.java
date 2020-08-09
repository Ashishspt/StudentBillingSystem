package com.example.studentbillingsystem.helpers;

import androidx.recyclerview.widget.RecyclerView;

public abstract class AppRecyclleViewAdaapter extends RecyclerView.Adapter {
    public abstract void add(Object object);
    public abstract  void clear();

}
