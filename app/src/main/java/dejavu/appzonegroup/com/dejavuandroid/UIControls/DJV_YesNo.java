package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONObject;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;

/**
 * Created by CrowdStar on 3/20/2015.
 */
public class DJV_YesNo extends RadioGroup {
    private ArrayList<UI_Model> ui_models;


    public final void setViewAttribute(JSONObject attributeDict, String data) {
        ui_models = new AttributeDefiner().AttributeReader(attributeDict, data);

    }


    public final void setDefaultAttribute() {
        setId(getCustomViewAttribute().get(0).getId());

    }

    public final ArrayList<UI_Model> getCustomViewAttribute() {
        return ui_models;
    }

    public DJV_YesNo(Context context) {
        super(context);
        ui_models = new ArrayList<>();
    }

    public DJV_YesNo(Context context, JSONObject controlAttributes, String data) {
        super(context);
        setViewAttribute(controlAttributes, data);
        setDefaultAttribute();
        RadioButton yesRadio = new RadioButton(context);
        RadioButton noRadio = new RadioButton(context);
        noRadio.setId(getCustomViewAttribute().get(0).getId() + 1);
        yesRadio.setId(getCustomViewAttribute().get(0).getId() + 2);
        yesRadio.setText("Yes");
        yesRadio.setTextColor(Color.BLACK);
        noRadio.setText("No");
        noRadio.setTextColor(Color.BLACK);
        setOrientation(HORIZONTAL);
        addView(yesRadio);
        addView(noRadio);
        check(getCustomViewAttribute().get(0).getId() + 1);

    }
}
