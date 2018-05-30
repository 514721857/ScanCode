package scancode.com.example.sgr.scancode;



import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.camera2.CaptureRequest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import scancode.com.example.sgr.scancode.http.CommonModel;
import scancode.com.example.sgr.scancode.http.HttpUtils;

public class MainActivity extends AppCompatActivity {
    long firstTime;
    String key="shenguoradf";
    private LinearLayout lin_result;
    private TextView result;
    CommonModel commonModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        startActivityForResult(new Intent(this, CaptureActivity.class),0);

    }

    private void initView() {
        commonModel=new CommonModel(this);
        result= (TextView) this.findViewById(R.id.result);



    }

    //扫描二维码
    public void scan(View view) {
        startActivityForResult(new Intent(this, CaptureActivity.class),0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String result=bundle.getString("result");
                this.result.setText("扫描结果："+result);
            }
        }

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 1000) {
                Toast.makeText(MainActivity.this, "在按一次退出客戶端", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
            } else {
                finish();
            }
        }
        return true;
    }

    public void getSave(String content){
        commonModel.getSave(content, new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(Object result) {

                String tempResult= (String)result;
                if(tempResult.equals("success")){
                    Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }else if(tempResult.equals("success")){
                    Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, "访问失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

