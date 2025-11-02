package tw.com.flag.ch13_hellowebview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView wv = (WebView) findViewById(R.id.wv);
        wv.loadUrl("http://www.flag.com.tw/index.asp");   // 連到旗標網站
    }
}
