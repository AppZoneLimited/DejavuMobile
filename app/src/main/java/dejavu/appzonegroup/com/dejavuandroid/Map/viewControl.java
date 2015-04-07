package dejavu.appzonegroup.com.dejavuandroid.Map;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONObject;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.FileChooserListener;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_Button;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_CheckBox;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_DatePicker;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_DropDown;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_List;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_TextArea;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_TextField;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_FileChooser;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_YesNo;

/**
 * Created by CrowdStar on 3/12/2015.
 */
public class viewControl {

    static public View getViewByType(Context context, JSONObject attrs, String data, FragmentManager fragmentManager, FileChooserListener listener, int type) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(2, 2, 2, 2);
        switch (type) {
            case UI_Type.DJV_SingleLineField:
                DJV_TextField djv_textField = new DJV_TextField(new ContextThemeWrapper(context, R.style.DJV_TextField_Style), null, 0, attrs, data);
                djv_textField.setLayoutParams(layoutParams);
                return djv_textField;
            case UI_Type.DJV_MultilineField:
                DJV_TextArea djv_textArea = new DJV_TextArea(new ContextThemeWrapper(context, R.style.DJV_TextArea_Style), null, 0, attrs, data);
                djv_textArea.setLayoutParams(layoutParams);
                return djv_textArea;
            case UI_Type.DJV_Button:
                DJV_Button djv_button = new DJV_Button(new ContextThemeWrapper(context, R.style.DJV_TextArea_Style), null, 0, attrs, data);
                djv_button.setLayoutParams(layoutParams);
                return djv_button;
            case UI_Type.DJV_CheckBox:
                DJV_CheckBox djv_checkBox = new DJV_CheckBox(new ContextThemeWrapper(context, R.style.DJV_CheckBox_Style), null, 0, attrs, data);
                djv_checkBox.setLayoutParams(layoutParams);
                return djv_checkBox;
            case UI_Type.DJV_YesNO:
                return new DJV_YesNo(context, attrs, data);
            case UI_Type.DJV_DropDown:
                return new DJV_DropDown(context, attrs, data);
            case UI_Type.DJV_List:
                return new DJV_List(context, attrs, data);
            case UI_Type.DJV_DateField:
                return new DJV_DatePicker(context, attrs, data, fragmentManager);
            case UI_Type.DJV_FileUpload:
                layoutParams = new LinearLayout.LayoutParams(
                        ViewParams.width, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(2, 2, 2, 2);
                DJV_FileChooser djv_fileChooser = new DJV_FileChooser(new ContextThemeWrapper(context, R.style.DJV_Button_Style), null, 0, attrs, data, listener);
                djv_fileChooser.setLayoutParams(layoutParams);
                return djv_fileChooser;
            default:
                return null;
        }
    }


}
