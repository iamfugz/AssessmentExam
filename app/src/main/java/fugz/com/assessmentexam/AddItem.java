package fugz.com.assessmentexam;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class AddItem extends AppCompatActivity implements AddItemView, View.OnClickListener{

    private EditText et_itemName;
    private EditText et_description;
    private Button btnAddPhoto;
    private TextView fileName;
    private Button btnSave;
    private Button btnCancel;

    private String selectedImagePath = "null";

    private AddItemPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);


        et_itemName = findViewById(R.id.editText_itemName);
        et_description = findViewById(R.id.editText_desc);
        fileName = findViewById(R.id.txtPhotoFileName);
        btnAddPhoto = findViewById(R.id.btnAddPhoto);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnAddPhoto.setOnClickListener(this);

        presenter = new AddItemPresenter(AddItem.this, this);

    }

    @Override
    public void intentSave(String data) {
        Intent i = new Intent();
        i.putExtra("DATA",data);
        setResult(RESULT_OK,i);
        finish();
    }

    @Override
    public void intentCancel() {
        Intent i = new Intent();
        setResult(RESULT_CANCELED,i);
        finish();
    }

    @Override
    public void intentPhotoPick() {
        Intent photoPickerIntent = new Intent();
        photoPickerIntent.setType("image/*");
        photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(photoPickerIntent, 1);
    }

    @Override
    public void setImagePath(String imagePath, String fileNameText) {
        selectedImagePath = imagePath;
        fileName.setText(fileNameText);
    }

    @Override
    public void initPermission() {
        String[] permissions = {android.Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(AddItem.this, permissions, 2);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSave){
            presenter.intentSave(et_itemName.getText().toString(), et_description.getText().toString(), selectedImagePath);
        }else if(v.getId() == R.id.btnCancel){
            presenter.intentCancel();
        }else if(v.getId() == R.id.btnAddPhoto){
            presenter.intentPhotoPick();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if (null == data) return;

            presenter.setImagePath(data);

        }else if(requestCode == 2){
            presenter.intentPhotoPick();
        }
    }



}
