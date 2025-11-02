package tw.com.flag.ch15_hellosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final String db_name="testDB";	// 資料庫名稱
    static final String tb_name="test";		// 資料表名稱
    SQLiteDatabase db;	//資料庫

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 開啟或建立資料庫
        db = openOrCreateDatabase(db_name,  Context.MODE_PRIVATE, null);

        String createTable="CREATE TABLE IF NOT EXISTS " +
                tb_name +			// 資料表名稱
                "(name VARCHAR(32), " +	//姓名欄位
                "phone VARCHAR(16), " +	//電話欄位
                "email VARCHAR(64))";	//Email欄位
        db.execSQL(createTable);	// 建立資料表

        Cursor c=db.rawQuery("SELECT * FROM "+tb_name, null);	// 查詢tb_name資料表中的所有資料
        if (c.getCount()==0){	// 若無資料, 則立即新增 2筆資料
            addData("Flag Technology Co.","02-23963257","service@flag.com.tw");
            addData("Flag Information Co","02-23214335","service@cio.com.tw");
            c=db.rawQuery("SELECT * FROM "+tb_name, null);	// 重新查詢
        }

        if (c.moveToFirst()){	// 移到第 1 筆資料 (若有資料才繼續)
            String str="總共有 "+c.getCount()+"筆資料\n";
            str+="-----\n";

            do{		// 逐筆讀出資料
                str+="name:"+c.getString(0)+"\n";
                str+="phone:"+c.getString(1)+"\n";
                str+="email:"+c.getString(2)+"\n";
                str+="-----\n";
            } while(c.moveToNext());	// 有一下筆就繼續迴圈

            TextView txv=(TextView)findViewById(R.id.txv);
            txv.setText(str);
        }
        db.close();		// 關閉資料庫
    }

    private void addData(String name, String phone, String email) {
        ContentValues cv=new ContentValues(3);	// 建立含3個資料項目的物件
        cv.put("name", name);
        cv.put("phone", phone);
        cv.put("email", email);

        db.insert(tb_name, null, cv);	// 將資料加到資料表
    }
}
