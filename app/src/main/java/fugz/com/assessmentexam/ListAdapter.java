package fugz.com.assessmentexam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by t-kdfugaban on 1/31/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder>{

    private List<String> dataList;
    private Context mContext;
    private Bitmap bitmap;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView itemName, description;
        public ImageView img;

        public MyViewHolder(View view) {
            super(view);
            itemName =  view.findViewById(R.id.itemName);
            description =  view.findViewById(R.id.description);
            img = view.findViewById(R.id.imageView);
        }
    }

    public ListAdapter(Context mContext, List<String> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String[] dataSplit = dataList.get(position).split("\\|");
        holder.itemName.setText("Item Name: "+dataSplit[0]);
        holder.description.setText("Description: "+dataSplit[1]);


        if(!dataSplit[2].equals("null")) {
 //           bitmap = BitmapFactory.decodeFile(dataSplit[2]);
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//
//            bitmap.compress(Bitmap.CompressFormat.JPEG,1,stream);
//
//            byte[] byteArray = stream.toByteArray();
//            Bitmap compressedBitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);

            holder.img.setImageBitmap(resizeBitmap(dataSplit[2], 100, 100));
        }else{
            holder.img.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_default_image));
        }



    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public Bitmap resizeBitmap(String photoPath, int targetW, int targetH) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = 1;
        if ((targetW > 0) || (targetH > 0)) {
            scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        }

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true; //Deprecated API 21

        return BitmapFactory.decodeFile(photoPath, bmOptions);
    }
}
