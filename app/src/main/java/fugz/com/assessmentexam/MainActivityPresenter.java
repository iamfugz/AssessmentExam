package fugz.com.assessmentexam;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by t-kdfugaban on 1/31/18.
 */

public class MainActivityPresenter {
    private MainActivityView mainView;
    private Context mContext;
    private TinyDB db;
    private ArrayList<String> dataList = new ArrayList<>();

    public MainActivityPresenter(Context mContext, MainActivityView mainView) {
        this.mainView = mainView;
        this.mContext = mContext;
        db = new TinyDB(this.mContext);
    }

    public void navigateToAddItemsView(){
        this.mainView.navigateToAddItemView();
    }

    public void addDataToArray(){
        if(db.getListString("DATA").size() != 0){
            dataList = db.getListString("DATA");
            this.mainView.setItems(dataList);
        }
    }

    public void addDataToDB(String data){
        dataList.add(data);
        db.putListString("DATA", dataList);
        this.mainView.refreshListView();
    }
}
