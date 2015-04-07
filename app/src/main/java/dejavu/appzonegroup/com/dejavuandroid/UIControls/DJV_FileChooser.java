package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.FileChooserListener;
import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;

/**
 * Created by CrowdStar on 3/9/2015.
 */
public class DJV_FileChooser extends Button implements View.OnClickListener {


    static private ArrayList<UI_Model> ui_models;
    FileChooserListener mListener;

    public void setViewAttribute(JSONObject attributeDict,String data) {
        ui_models = new AttributeDefiner().AttributeReader(attributeDict,data);
    }


    public void setDefaultAttribute() {
        setText(getCustomViewAttribute().get(0).getName());
        setId(getCustomViewAttribute().get(0).getId());
        setTextColor(Color.BLACK);
        setText("Select File");
    }


    public ArrayList<UI_Model> getCustomViewAttribute() {
        return ui_models;
    }

    public DJV_FileChooser(Context context) {
        super(context);
        ui_models = new ArrayList<>();
    }

    public DJV_FileChooser(Context context, AttributeSet attrs, int defStyleAttr, JSONObject controlAttributes, String data,FileChooserListener listener) {
        super(context);
        mListener = listener;
        setViewAttribute(controlAttributes,data);
        setDefaultAttribute();
        setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        mListener.openFileChooser(v);
    }
}
