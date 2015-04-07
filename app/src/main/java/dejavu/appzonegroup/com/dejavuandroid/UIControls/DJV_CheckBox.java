package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.CheckBox;

import org.json.JSONObject;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;

/**
 * Created by CrowdStar on 3/9/2015.
 */
public class DJV_CheckBox extends CheckBox {

    private ArrayList<UI_Model> ui_models;


    public final void setViewAttribute(JSONObject attributeDict, String data) {
        ui_models = new AttributeDefiner().AttributeReader(attributeDict, data);
    }


    public final void setDefaultAttribute() {
        setId(getCustomViewAttribute().get(0).getId());
        setTextColor(Color.BLACK);
        setText(getCustomViewAttribute().get(0).getValue());
    }

    public final ArrayList<UI_Model> getCustomViewAttribute() {
        return ui_models;
    }

    public DJV_CheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public DJV_CheckBox(Context context) {
        super(context);
        ui_models = new ArrayList<>();
    }

    public DJV_CheckBox(Context context, AttributeSet attrs, int defStyleAttr, JSONObject controlAttributes, String data) {
        super(context, attrs, defStyleAttr);
        setViewAttribute(controlAttributes, data);
        setDefaultAttribute();
    }
}
