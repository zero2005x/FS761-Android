package tw.com.flag.ch13_searchflickr;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    ProgressBar pb;
    EditText keyText;
    String keyword;    //用來記錄關鍵字
    String baseURL="https://m.flickr.com/#/search/advanced_QM_q_IS_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv = (WebView) findViewById(R.id.webView);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        keyText=(EditText)findViewById(R.id.editText);

        wv.getSettings().setJavaScriptEnabled(true);	// 啟用 JavaScript
        wv.setWebViewClient(new WebViewClient());		// 建立及使用 WebViewClient 物件
        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                pb.setProgress(progress);       //設定進度
                pb.setVisibility(progress < 100 ? View.VISIBLE : View.GONE);  //依進度來讓進度條顯示或消失
            }
        });
    }

    @Override
    public void onBackPressed() {  //按下返回鍵時的事件處理
        if(wv.canGoBack()){   // 如果 WebView 有上一頁
            wv.goBack();	  // 回上一頁
            return;
        }
        super.onBackPressed();  //呼叫父類別的同名方法, 以執行預設動作(結束程式)
    }

    public void search(View v){
        keyword = keyText.getText().toString().replaceAll("\\s+", "+"); //將字串中的單一或連續空白置換成 +
        wv.loadUrl(baseURL + keyword);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString("關鍵字", keyword);  // 儲存目前的查詢參數
        editor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences myPref = getPreferences(MODE_PRIVATE);
        keyword = myPref.getString("關鍵字", "Taipei+101");
        if(wv.getUrl()==null)
            wv.loadUrl(baseURL+keyword);
    }
}
