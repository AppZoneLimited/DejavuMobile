package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RadioButton;

import org.json.JSONObject;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;

/**
 * Created by CrowdStar on 3/8/2015.
 */
public class DJV_RadioButton extends RadioButton {


    private ArrayList<UI_Model> ui_models;


    public final void setViewAttribute(JSONObject attributeDict, String data) {
        ui_models = new AttributeDefiner().AttributeReader(attributeDict, data);
    }


    public final void setDefaultAttribute() {
        //setId(getCustomViewAttribute().get(0).getId() + 2);
        setTextColor(Color.BLACK);
        setText(getCustomViewAttribute().get(0).getValue());
    }

    public final ArrayList<UI_Model> getCustomViewAttribute() {
        return ui_models;
    }

    public DJV_RadioButton(Context context) {
        super(context);
        ui_models = new ArrayList<>();
    }

    public DJV_RadioButton(Context context, JSONObject attrs, String data) {
        super(context);
        setViewAttribute(attrs, data);
        setDefaultAttribute();
    }
}
