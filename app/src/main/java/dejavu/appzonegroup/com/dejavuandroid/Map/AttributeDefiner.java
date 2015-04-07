package dejavu.appzonegroup.com.dejavuandroid.Map;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import dejavu.appzonegroup.com.dejavuandroid.Models.UI_Model;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 3/16/2015.
 */
public class AttributeDefiner {


    UI_Model ui_model;
    ArrayList<UI_Model> modelArrayList;

    public ArrayList<UI_Model> AttributeReader(JSONObject attribute, String data) {
        ui_model = new UI_Model();
        modelArrayList = new ArrayList<>();
        Iterator<?> keys = attribute.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            try {
                AttributeMap(ui_model, attribute.get(key).toString(), key);
                setValue(attribute.get(key).toString(), data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        modelArrayList.add(ui_model);

        return modelArrayList;
    }

    public void AttributeMap(UI_Model ui_model, String value, String key) {
        switch (key.toLowerCase()) {
            case "failureMessage":
                ui_model.setFailureMessage(value);
                break;
            case "name":
                ui_model.setName(value);
                break;

            case "id":
                ui_model.setId(Integer.parseInt(value));
                break;
            case "eventDescription":
                ui_model.setEventDescription(value);
                break;
        }
    }

    public void setValue(String key, String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            if (jsonArray.getJSONObject(0).has(key)) {
                String value = jsonArray.getJSONObject(0).getString(key);
                ui_model.setValue(value);
                ui_model.setValueKey(key);
            }

        } catch (JSONException e) {
            ui_model.setValue("");
        }
    }
}
