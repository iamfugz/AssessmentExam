package fugz.com.assessmentexam;

import java.util.ArrayList;

/**
 * Created by t-kdfugaban on 1/31/18.
 */

public interface MainActivityView {

    void setItems(ArrayList<String> data);

    void refreshListView();

    void navigateToAddItemView();
}
