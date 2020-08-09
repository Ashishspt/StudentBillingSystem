package com.example.studentbillingsystem.presenters;

import android.util.Log;

import com.example.studentbillingsystem.apiservices.ApiClientToken;
import com.example.studentbillingsystem.apiservices.NewsApi;
import com.example.studentbillingsystem.constants.AppConstants;
import com.example.studentbillingsystem.models.News;
import com.example.studentbillingsystem.utils.UtilitiesFunctions;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsPresenter {
    private WeakReference<View> view;

    CompositeDisposable compositeDisposable;

    public NewsPresenter(View view) {
        this.view = new WeakReference<>(view);
        this.compositeDisposable = new CompositeDisposable();

    }

    private View getView() throws NullPointerException {
        if (view != null)
            return view.get();
        else
            throw new NullPointerException("View is unavailable");
    }

    public void news() {
        Observable<News> newsObservable = ApiClientToken.getClient().create(NewsApi.class)
                .getNews()
                .subscribeOn(Schedulers.io())
                .retry(AppConstants.API_RETRY_COUNT)
                .observeOn(AndroidSchedulers.mainThread());
        DisposableObserver<News> newsDisposableObserver = new DisposableObserver<News>() {
            @Override
            public void onNext(News news) {
                if (getView() != null && news != null) {

                    getView().onNewsSucess(news);

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
        compositeDisposable.add(newsObservable.subscribeWith(newsDisposableObserver));


    }

    public void onActivityStop() {

        if (getView() != null) {
            compositeDisposable.clear();

        }
    }


    public interface View {

        void onNewsSucess(News news);

        void onFailure(String message);


    }


}
