package tw.com.flag.ch10_camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onGet(View v) {
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  //建立動作為拍照的意圖
        startActivityForResult(it, 100);   //啟動意圖並要求傳回資料
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode==100) {
            Bundle extras = data.getExtras();         //將 Intent 的附加資料轉為 Bundle 物件
            Bitmap bmp = (Bitmap) extras.get("data"); //由 Bundle 取出名為 "data" 的 Bitmap 資料
            ImageView imv = (ImageView)findViewById(R.id.imageView);
            imv.setImageBitmap(bmp);    	    	  //將 Bitmap 資料顯示在 ImageView 中
        }
        else {
            Toast.makeText(this, "沒有拍到照片", Toast.LENGTH_LONG).show();
        }
    }
}
