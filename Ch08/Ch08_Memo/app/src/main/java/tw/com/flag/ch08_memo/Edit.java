package tw.com.flag.ch08_memo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent it = getIntent();               //取得傳入的 Intent 物件
        int no = it.getIntExtra("編號", 0);    //讀出名為 "編號" 的 Int 資料, 若沒有則傳回 0
        String s = it.getStringExtra("備忘");  //讀出名為 "備忘" 的 String 資料

        TextView txv = (TextView)findViewById(R.id.textView);
        txv.setText(no + ".");                 //在畫面左上角顯示編號
        EditText edt = (EditText)findViewById(R.id.editText);
        if(s.length() > 3)
            edt.setText(s.substring(3)); //將傳來的備忘資料去除前3個字, 然後填入編輯元件中

    }

    public void onCancel(View v) {  //按取消鈕時
        finish();    //結束活動
    }

    public void onSave(View v) {    //按儲存鈕時
        finish();    //結束活動
    }
}
