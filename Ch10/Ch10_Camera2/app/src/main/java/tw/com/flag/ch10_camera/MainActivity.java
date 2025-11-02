package tw.com.flag.ch10_camera;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Uri imgUri;    //用來參照拍照存檔的 Uri 物件
    ImageView imv; //用來參照 ImageView 物件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imv = (ImageView)findViewById(R.id.imageView);  //參照 Layout 中的 ImageView 元件
    }

    public void onGet(View v) {
        if (ActivityCompat.checkSelfPermission(this,   //檢查是否已取得寫入權限
                Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            //尚未取得權限
            ActivityCompat.requestPermissions(this,  //向使用者要求允許寫入權限
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    200);
        }
        else {
            //已經取得權限
            savePhoto();  //拍照並存檔
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 200){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){ //使用者允許權限
                savePhoto();  //拍照並存檔
            }
            else { //使用者拒絕權限
                Toast.makeText(this, "程式需要寫入權限才能運作", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void savePhoto() {
        imgUri =  getContentResolver().insert(  //透過內容資料庫新增一個圖片檔
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new ContentValues());
        Intent it = new Intent("android.media.action.IMAGE_CAPTURE"); //建立動作為拍照的 Intent
        it.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);    //將 uri 加到拍照 Intent 的額外資料中
        startActivityForResult(it, 100);  //啟動 Intent 並要求傳回資料
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode==100) {
            Bitmap bmp = null;
            try {
                bmp = BitmapFactory.decodeStream(
                        getContentResolver().openInputStream(imgUri), null, null);  //讀取圖檔內容轉換為 Bitmap 物件
            } catch (IOException e) {
                Toast.makeText(this, "無法讀取照片", Toast.LENGTH_LONG).show();
            }
            imv.setImageBitmap(bmp); //將 Bitmap 物件顯示在 ImageView 中
        }
        else {
            Toast.makeText(this, "沒有拍到照片", Toast.LENGTH_LONG).show();
        }
    }
}
