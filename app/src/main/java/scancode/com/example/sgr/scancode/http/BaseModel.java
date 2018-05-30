package scancode.com.example.sgr.scancode.http;

import android.content.Context;


import com.tz.mvp.framework.base.model.MvpModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 作者: Dream on 16/9/24 21:10
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

public class BaseModel implements MvpModel {

    private Context context;

    public BaseModel(Context context){
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public String getServerUrl(){
        return "http://104.233.252.50/";
    }

    public <T> T buildService(Class<T> service){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getServerUrl())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(service);
    }

    public void buildObserve(Observable<Object> Mobservable, final HttpUtils.OnHttpResultListener onLceHttpResultListener){

        Mobservable .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {

                    @Override
                    public void onError(Throwable e) {
                        onLceHttpResultListener.onError(new Exception(e));
                    }

                    @Override
                    public void onComplete() {
                        onLceHttpResultListener.onCompleted();
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object model) {
//                        Result result=(Result) model;  只有登录的时候不存在转化
//                        if(result.)     这里可以统一处理状态码，可以统一跳转到 登录界面（如果token过期了）
                        onLceHttpResultListener.onResult(model);
                    }});

    }

}
