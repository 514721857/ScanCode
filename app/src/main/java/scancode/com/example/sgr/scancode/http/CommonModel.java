package scancode.com.example.sgr.scancode.http;

import android.content.Context;
import android.util.Log;


import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;


/**
 * 作者: Dream on 16/9/24 21:09
 * QQ:510278658
 * E-mail:510278658@qq.com
 */
public class CommonModel extends BaseModel {

    public CommonModel(Context context) {
        super(context);
    }

    /**
     * 获取二维码
     * @param content
     * @param onLceHttpResultListener
     */
    public void getSave(String content,final HttpUtils.OnHttpResultListener onLceHttpResultListener) {

        HttpService essenceService= buildService(HttpService.class);
        buildObserve((Observable)essenceService.getSave(content),onLceHttpResultListener);

    }
}
