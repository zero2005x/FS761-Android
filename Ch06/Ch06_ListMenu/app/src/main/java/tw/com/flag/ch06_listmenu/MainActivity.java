package tw.com.flag.ch06_listmenu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得 ListView 物件, 並設定按下動作的監聽器
        ListView lv=(ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(this);
    }

    // 儲存目前選取的項目 (餐點) 名稱字串
    ArrayList<String> selected= new ArrayList<>();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView txv = (TextView) view; // 將被按下的 View 物件轉成 TextView
        String item=txv.getText().toString();

        if(selected.contains(item)) // 若 ArrayList 已有同名項目
            selected.remove(item);  // 就將它移除
        else                        // 若 ArrayList 沒有同名項目
            selected.add(item);     // 就將它加到 ArrayList (當成選取的項目)

        String msg;
        if(selected.size()>0){   // 若 ArrayList 中的項目數大於 0
            msg="你點了:";
            for(String str:selected)
                msg+=" "+str;    // 每個項目 (餐點) 名稱前空一格
        }                        // 並附加到訊息字串後面
        else                     // 若 ArrayList 中的項目數等於 0
            msg="請點餐!";

        TextView msgTxv =(TextView) findViewById(R.id.msgTxv);
        msgTxv.setText(msg);  // 顯示訊息字串
    }
}
