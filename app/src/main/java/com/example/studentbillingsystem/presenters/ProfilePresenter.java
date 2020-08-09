package com.example.studentbillingsystem.presenters;

import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.example.studentbillingsystem.apiservices.ApiClient;
import com.example.studentbillingsystem.apiservices.ApiClientToken;
import com.example.studentbillingsystem.apiservices.LoginApi;
import com.example.studentbillingsystem.apiservices.ProfileApi;
import com.example.studentbillingsystem.constants.AppConstants;
import com.example.studentbillingsystem.models.Login;
import com.example.studentbillingsystem.models.Profile;
import com.example.studentbillingsystem.utils.Utilities;
import com.example.studentbillingsystem.utils.UtilitiesFunctions;
import com.google.gson.GsonBuilder;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ProfilePresenter {
    private WeakReference<View> view;

    CompositeDisposable compositeDisposable;

    public ProfilePresenter(View view) {
        this.view = new WeakReference<>(view);
        this.compositeDisposable = new CompositeDisposable();

    }

    private View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    public void userProfile() {
        Observable<Profile> profileObservable= ApiClientToken.getClient().create(ProfileApi.class)
                .getProfile()
                .subscribeOn(Schedulers.io())
                .retry(AppConstants.API_RETRY_COUNT)
                .observeOn(AndroidSchedulers.mainThread());

            DisposableObserver<Profile> profileDisposableObserver=new DisposableObserver<Profile>() {
                @Override
                public void onNext(Profile profile) {
                    Log.e( "onNexttask: ", "called");
                    if (getView() != null && profile != null) {
                        Log.e("onNext: ",new GsonBuilder().create().toJson(profile));
                        getView().onUserProfileSuccess(profile);




                    }

                }

                @Override
                public void onError(Throwable e) {
                    try {

                        getView().onFailure(UtilitiesFunctions.handleApiError(e));
                        Log.e( "onError: ",UtilitiesFunctions.handleApiError(e) );

                    } catch (Exception ignored) {

                    }

                }

                @Override
                public void onComplete() {

                }
            };

        compositeDisposable.add(profileObservable.subscribeWith(profileDisposableObserver));

    }
    public void onActivityStop() {

        if (getView() != null) {
            compositeDisposable.clear();

        }
    }


    public interface View {

        void onUserProfileSuccess(Profile profile);
        void onFailure(String message);


    }


}
