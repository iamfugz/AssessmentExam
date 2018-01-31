package fugz.com.assessmentexam;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.io.File;

/**
 * Created by t-kdfugaban on 1/31/18.
 */

public class AddItemPresenter {
    private AddItemView addItemView;
    private Context mContext;

    public AddItemPresenter(Context mContext, AddItemView addItemView) {
        this.addItemView = addItemView;
        this.mContext = mContext;
    }

    public void intentSave(String itemName, String description, String imagePath){
        if(checkNull(itemName, description)){
            String data = itemName + "|" + description + "|" + imagePath;
            this.addItemView.intentSave(data);
        }
    }

    public void intentCancel(){
        this.addItemView.intentCancel();
    }

    public void intentPhotoPick(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String permission2 = android.Manifest.permission.READ_EXTERNAL_STORAGE;
            if (ActivityCompat.checkSelfPermission(this.mContext, permission2) == PackageManager.PERMISSION_GRANTED) {
                this.addItemView.intentPhotoPick();
            } else {
                this.addItemView.initPermission();
            }
        }else{
            this.addItemView.intentPhotoPick();
        }
    }

    public void setImagePath(Intent data){
        Uri selectedImageUri = data.getData();
        String imagePath = ImageFilePath.getPath(this.mContext, selectedImageUri);
        File file = new File(imagePath);
        String strFileName = file.getName();
        this.addItemView.setImagePath(imagePath, strFileName);
    }

    private boolean checkNull(String itemName, String description){
        if(itemName.equals("")){
            Toast.makeText(this.mContext, "Item name must filled up.", Toast.LENGTH_SHORT).show();
            return  false;
        }else if(description.equals("")){
            Toast.makeText(this.mContext, "Description must filled up.", Toast.LENGTH_SHORT).show();
            return  false;
        }else{
            return true;
        }
    }
}
