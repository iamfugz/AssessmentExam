package fugz.com.assessmentexam;

/**
 * Created by t-kdfugaban on 1/31/18.
 */

public interface AddItemView {

    void intentSave(String data);

    void intentCancel();

    void intentPhotoPick();

    void setImagePath(String imagePath, String fileName);

    void initPermission();
}
