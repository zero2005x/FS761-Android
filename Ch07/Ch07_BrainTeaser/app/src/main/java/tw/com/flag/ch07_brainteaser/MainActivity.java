package tw.com.flag.ch07_brainteaser;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    // 建立問題陣列
    String[] queArr = {"什麼門永遠關不上","什麼東西沒人愛吃？",
                        "什麼瓜不能吃？","什麼布切不斷？",
                        "什麼鼠最愛乾淨？","偷什麼不犯法？"};
    // 建立答案陣列
    String[] ansArr = { "球門", "虧",
                        "傻瓜","瀑布",
                        " 環保署","偷笑" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 建立供 ListView 使用的 ArrayAdapter 物件
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, // 使用內建的佈局資源
                queArr);						// 以 queArr 陣列當資料來源

        ListView lv = (ListView)findViewById(R.id.lv);  //取得  ListView
        lv.setAdapter(adapter);			 //設定 ListView 使用的 Adapter
        lv.setOnItemClickListener(this); //設定 ListView 項目被按時的事件監聽器
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,
                "答案︰" + ansArr[position], Toast.LENGTH_SHORT).show();
    }

}
