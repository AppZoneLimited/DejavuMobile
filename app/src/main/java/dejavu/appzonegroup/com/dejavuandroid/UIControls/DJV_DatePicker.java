package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Fragment.DatePicker;
import dejavu.appzonegroup.com.dejavuandroid.Interfaces.DateSetListener;
import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;

/**
 * Created by CrowdStar on 3/8/2015.
 */
public class DJV_DatePicker extends Button implements DateSetListener, View.OnClickListener {

    private FragmentManager fragmentManager;

    public DJV_DatePicker(Context context) {
        super(context);
        ui_models = new ArrayList<>();
    }

    static private ArrayList<UI_Model> ui_models;


    public void setViewAttribute(JSONObject attributeDict, String data) {
        ui_models = new AttributeDefiner().AttributeReader(attributeDict, data);
    }


    public void setDefaultAttribute() {
        setText("Date of Birth");
        setId(getCustomViewAttribute().get(0).getId());
        setOnClickListener(this);
        setTextColor(Color.BLACK);
    }

    public ArrayList<UI_Model> getCustomViewAttribute() {

        return ui_models;
    }

    public DJV_DatePicker(Context context, JSONObject attrs, String data, FragmentManager manager) {
        super(context);
        fragmentManager = manager;
        setViewAttribute(attrs, data);
        setDefaultAttribute();
    }


    @Override
    public void onDateSet(String dateString) {
        setText(dateString);
    }

    @Override
    public void onClick(View v) {
        DatePicker datePicker = new DatePicker();
        datePicker.newInstance(DJV_DatePicker.this).show(fragmentManager, "Choose date of birth");
    }
}
