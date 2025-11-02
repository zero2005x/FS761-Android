package tw.com.flag.ch13_hellowebview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv = (WebView) findViewById(R.id.wv);
        pb = (ProgressBar) findViewById(R.id.pb);
        wv.getSettings().setJavaScriptEnabled(true);	// 啟用 JavaScript
        wv.getSettings().setBuiltInZoomControls(true);	// 啟用縮放功能
        wv.invokeZoomPicker();	                        // 顯示縮放小工具
        wv.setWebViewClient(new WebViewClient());		// 建立及使用 WebViewClient 物件
        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                pb.setProgress(progress);       //設定進度
                pb.setVisibility(progress < 100? View.VISIBLE: View.GONE);  //依進度來讓進度條顯示或消失
            }
        });
        wv.loadUrl("http://www.flag.com.tw");   // 連到旗標網站，可以不用加網頁的檔名了
    }

    @Override
    public void onBackPressed() {
        if(wv.canGoBack()){   // 如果 WebView 有上一頁
            wv.goBack();	  // 回上一頁
            return;
        }
        super.onBackPressed();  //呼叫父類別的同名方法, 以執行預設動作(結束程式)
    }
}
