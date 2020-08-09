package com.example.studentbillingsystem.presenters;

import android.util.Log;

import com.example.studentbillingsystem.apiservices.ApiClientToken;
import com.example.studentbillingsystem.apiservices.SubjectApi;
import com.example.studentbillingsystem.constants.AppConstants;
import com.example.studentbillingsystem.models.Subject;
import com.example.studentbillingsystem.utils.UtilitiesFunctions;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SubjectPresenter {
    private WeakReference<View> view;

    CompositeDisposable compositeDisposable;

    public SubjectPresenter(View view) {
        this.view = new WeakReference<>(view);
        this.compositeDisposable = new CompositeDisposable();

    }

    private View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    public void Subject() {
        Observable<Subject> subjectObservable = ApiClientToken.getClient().create(SubjectApi.class)
                .getSubject()
                .subscribeOn(Schedulers.io())
                .retry(AppConstants.API_RETRY_COUNT)
                .observeOn(AndroidSchedulers.mainThread());
        DisposableObserver<Subject> subjectDisposableObserver = new DisposableObserver<Subject>() {
            @Override
            public void onNext(Subject subject) {
                if (getView() != null && subject != null) {

                    getView().onSubjectSuccess(subject);

                }
            }

            @Override
            public void onError(Throwable e) {
                try {

                    getView().onFailure(UtilitiesFunctions.handleApiError(e));
                    Log.e("onError: ", UtilitiesFunctions.handleApiError(e));

                } catch (Exception ignored) {

                }

            }

            @Override
            public void onComplete() {

            }
        };
        compositeDisposable.add(subjectObservable.subscribeWith(subjectDisposableObserver));


    }

    public void onActivityStop() {

        if (getView() != null) {
            compositeDisposable.clear();

        }
    }


    public interface View {

        void onSubjectSuccess(Subject subject);

        void onFailure(String message);


    }


}
