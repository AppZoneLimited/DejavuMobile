package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.jar.Attributes;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 3/6/2015.
 */
public class DJV_TextField extends EditText {


    private ArrayList<UI_Model> ui_models;


    public final void setViewAttribute(JSONObject attributeDict, String data) {
        ui_models = new AttributeDefiner().AttributeReader(attributeDict, data);
    }


    public final void setDefaultAttribute() {
        setId(getCustomViewAttribute().get(0).getId());
        setText(getCustomViewAttribute().get(0).getValue());
        setSingleLine();
    }

    public final ArrayList<UI_Model> getCustomViewAttribute() {
        return ui_models;
    }


    public DJV_TextField(Context context, AttributeSet attrs, int defStyleAttr, JSONObject controlAttributes, String data) {
        super(context, attrs, defStyleAttr);
        setViewAttribute(controlAttributes, data);
        setDefaultAttribute();
    }

}
