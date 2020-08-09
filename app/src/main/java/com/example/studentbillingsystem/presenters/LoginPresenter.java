package com.example.studentbillingsystem.presenters;

import android.util.Log;
import android.view.View;

import com.example.studentbillingsystem.apiservices.ApiClient;
import com.example.studentbillingsystem.apiservices.LoginApi;
import com.example.studentbillingsystem.constants.AppConstants;
import com.example.studentbillingsystem.models.Login;
import com.example.studentbillingsystem.utils.Utilities;
import com.example.studentbillingsystem.utils.UtilitiesFunctions;
import com.google.gson.GsonBuilder;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter {
    private WeakReference<View> view;

    CompositeDisposable compositeDisposable;

    public LoginPresenter(View view) {
        this.view = new WeakReference<>(view);
        this.compositeDisposable = new CompositeDisposable();

    }

    private View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    public void userLogin(String grantType,String email, String password) {


        Observable<Login> loginObservable = ApiClient.getClient().create(LoginApi.class)
                .login(grantType,email, password)
                .subscribeOn(Schedulers.io())
                .retry(AppConstants.API_RETRY_COUNT)
                .observeOn(AndroidSchedulers.mainThread());


        DisposableObserver<Login> loginDisposableObserver = new DisposableObserver<Login>() {



            @Override
            public void onNext(Login userLogin) {
                Log.e( "onNextask: ", "called");

                if (getView() != null && userLogin != null) {
                    Utilities.saveLoginResponse(userLogin);
                    Log.e( "onNext: ",new GsonBuilder().create().toJson(userLogin));
                    getView().onUserLoginSuccess(userLogin);


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

        compositeDisposable.add(loginObservable.subscribeWith(loginDisposableObserver));

    }
    public void onActivityStop() {

        if (getView() != null) {
            compositeDisposable.clear();

        }
    }


    public interface View {

        void onUserLoginSuccess(Login login);

        void onFailure(String message);

    }


}
