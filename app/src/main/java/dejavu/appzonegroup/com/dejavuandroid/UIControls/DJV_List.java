package dejavu.appzonegroup.com.dejavuandroid.UIControls;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dejavu.appzonegroup.com.dejavuandroid.Map.AttributeDefiner;
import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;

/**
 * Created by CrowdStar on 3/9/2015.
 */
public class DJV_List extends ListView {
    private ArrayList<UI_Model> ui_models;
    private String[] items;
    private ArrayAdapter itemAdapter;

    public final void setViewAttribute(JSONObject attributeDict, String data) {
        ui_models = new AttributeDefiner().AttributeReader(attributeDict, data);
    }


    public final void setDefaultAttribute() {
        setId(getCustomViewAttribute().get(0).getId());

        items = getCustomViewAttribute().get(0).getValue().split(",");

    }

    public final ArrayList<UI_Model> getCustomViewAttribute() {
        return ui_models;
    }


    public DJV_List(Context context) {
        super(context);
        ui_models = new ArrayList<>();
    }


    public DJV_List(Context context, JSONObject controlAttributes, String data) {
        super(context);
        setViewAttribute(controlAttributes, data);
        setDefaultAttribute();
        itemAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, items){
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                //we know that simple_spinner_item has android.R.id.text1 TextView:

        /* if(isDroidX) {*/
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);//choose your color :)
                return view;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                //we know that simple_spinner_item has android.R.id.text1 TextView:

        /* if(isDroidX) {*/
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);//choose your color :)
                return view;
            }
        };
        setAdapter(itemAdapter);

    }


}
