package com.example.studentbillingsystem.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.studentbillingsystem.R;
import com.example.studentbillingsystem.helpers.AppFragment;
import com.example.studentbillingsystem.helpers.AppRecyclleViewAdaapter;
import com.example.studentbillingsystem.helpers.SpacesItemDecoration;
import com.example.studentbillingsystem.models.Profile;
import com.example.studentbillingsystem.presenters.ProfilePresenter;
//import com.khalti.checkout.helper.Config;
//import com.khalti.checkout.helper.OnCheckOutListener;
//import com.khalti.checkout.helper.PaymentPreference;
//import com.khalti.utils.Constant;
//import com.khalti.widget.ButtonStyle;
//import com.khalti.widget.KhaltiButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FeesdummyFragment extends AppFragment implements ProfilePresenter.View {

    RecyclerView recyclerView;
    FeeRecyclerViewAdapter feeRecyclerViewAdapter;
    TextView tvTotalPayment,tvPaidPayment,tvRemainingPayment;
    ProfilePresenter profilePresenter;
//    private KhaltiButton khaltiButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.item_fee, container, false);
        initializedView(view);
        initializedListners();

        return view;
    }


    @Override
    protected void initializedView(View view) {
        recyclerView=view.findViewById(R.id.rvPreviousPayment);
        tvPaidPayment=view.findViewById(R.id.tvPaidAmount);
        tvTotalPayment=view.findViewById(R.id.tvTotalPayment);
        tvRemainingPayment=view.findViewById(R.id.tvRemainingFee);
//        khaltiButton=view.findViewById(R.id.khalti_button);
//        khaltiButton.setButtonStyle(ButtonStyle.BASIC);
//        khaltiButton.setText("Pay");

    }

    @Override
    protected void initializedListners() {
        profilePresenter=new ProfilePresenter(this);
        profilePresenter.userProfile();
//        khaltiIntegrated();
        prepareRecyclerView();


    }

//    private void khaltiIntegrated() {
//        Map<String, Object> map = new HashMap<>();
////    map.put("merchant_extra", "This is extra data");
//
//        Config.Builder builder = new Config.Builder(Constant.pub, "Product ID", "Main", 1100L, new OnCheckOutListener() {
//            @Override
//            public void onError(@NonNull String action, @NonNull Map<String, String> errorMap) {
//                Log.i(action, errorMap.toString());
//            }
//
//            @Override
//            public void onSuccess(@NonNull Map<String, Object> data) {
//                Log.i("success", data.toString());
//            }
//        })
//                .paymentPreferences(new ArrayList<PaymentPreference>() {{
//                    add(PaymentPreference.KHALTI);
//                    add(PaymentPreference.EBANKING);
//                    add(PaymentPreference.MOBILE_BANKING);
//                    add(PaymentPreference.CONNECT_IPS);
//                    add(PaymentPreference.SCT);
//                }})
//                .additionalData(map)
//                .productUrl("http://example.com/product")
//                .mobile("9849622334");
//
//        Config config= builder.build();
//        khaltiButton.setCheckOutConfig(config);
//    }

    private void prepareRecyclerView() {
        feeRecyclerViewAdapter=new FeeRecyclerViewAdapter();

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(feeRecyclerViewAdapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(decoration);


    }

    @Override
    public void onUserProfileSuccess(Profile profile) {
        double pay=profile.getDetails().getAdmissionDTO().getFeeAmount()-profile.getDetails().getAdmissionDTO().getScholarshipAmount();
        tvTotalPayment.setText("Rs\n"+profile.getDetails().getAdmissionDTO().getFeeAmount());
        tvPaidPayment.setText("Rs\n"+profile.getDetails().getAdmissionDTO().getScholarshipAmount());
        tvRemainingPayment.setText("Rs\n"+pay);

    }

    @Override
    public void onFailure(String message) {

    }

    public class FeeRecyclerViewAdapter extends AppRecyclleViewAdaapter{

        @Override
        public void add(Object object) {

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_previous_payment,parent,false);
           return  new VHItem(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 6;
        }

        private class VHItem extends RecyclerView.ViewHolder {
            public VHItem(View view) {
                super(view);
            }
        }
    }
}
