package dejavu.appzonegroup.com.dejavuandroid.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;
import com.appzone.zone.orchestra.engine.interfaces.StepResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.FileChooserListener;
import dejavu.appzonegroup.com.dejavuandroid.Map.viewControl;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_Button;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_CheckBox;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_DatePicker;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_FileChooser;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_TextArea;
import dejavu.appzonegroup.com.dejavuandroid.UIControls.DJV_TextField;

/**
 * Created by CrowdStar on 3/6/2015.
 */
public class UiControlTrier extends Fragment implements FileChooserListener {

    View rootView;
    UiControlTrier uiControlTrier;
    static DJV_DatePicker djv_datePicker;
    LinearLayout rootLayout;
    private ShowMessage showMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.ui_control, container, false);
        rootLayout = (LinearLayout) rootView.findViewById(R.id.root_linear_vertical);
        try {

            JSONObject commandJsonObject = step.getCommands();
            String dataString = "[" + step.getData().toString() + "]";
            Iterator<?> keys = commandJsonObject.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (step.getCommands().get(key) instanceof JSONObject) {
                    int type = Integer.parseInt(((JSONObject) commandJsonObject.get(key)).get("type").toString());
                    rootLayout.addView(viewControl.getViewByType(getActivity(), (JSONObject) commandJsonObject.get(key), dataString, getFragmentManager(), UiControlTrier.this, type));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button button = new Button(getActivity());
        button.setText("Read Output");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new ShowMessage(getActivity(), readOutPut(), Toast.LENGTH_LONG);
                step.setStepResult(readOutPut());
                step.setStepResult(readOutPut(), step.getStepAbstract(), new StepResultCallback() {
                    @Override
                    public void onStepResult(StepsAbstraction stepsAbstraction, Step step, JSONObject jsonObject) {

                    }

                    @Override
                    public void onGetNextStep(Step step) {
                        try {
                            new ShowMessage(getActivity(), step.getCommands().toString(), Toast.LENGTH_LONG);
                        } catch (Exception ex) {
                            new ShowMessage(getActivity(), ex.getMessage(), Toast.LENGTH_LONG);
                        }
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.add(R.id.content_frame, new UiControlTrier().newInstance(step)).commitAllowingStateLoss();
                    }
                });
            }
        });
        rootLayout.addView(button);

        return rootView;
    }

    static Step step;

    public UiControlTrier newInstance(Step step) {
        uiControlTrier = new UiControlTrier();
        this.step = step;
        return uiControlTrier;
    }

    public JSONObject readOutPut() {
        JSONObject jsonObject = new JSONObject();
        for (int viewIndex = 0; viewIndex < rootLayout.getChildCount() - 1; viewIndex++) {
            viewInstance(rootLayout.getChildAt(viewIndex), jsonObject);
        }

        return jsonObject;
    }

    public void viewInstance(View view, JSONObject jsonObject) {
        String key = "";
        String value = "";
        if (view instanceof DJV_Button) {
            key = ((DJV_Button) view).getCustomViewAttribute().get(0).getValueKey();
            value = ((DJV_Button) view).getCustomViewAttribute().get(0).getValue();
        } else if (view instanceof DJV_DatePicker) {
            key = ((DJV_DatePicker) view).getCustomViewAttribute().get(0).getValueKey();
            value = ((DJV_DatePicker) view).getText().toString();
        } else if (view instanceof DJV_TextField) {
            key = ((DJV_TextField) view).getCustomViewAttribute().get(0).getValueKey();
            value = ((DJV_TextField) view).getText().toString();
        } else if (view instanceof DJV_TextArea) {
            key = ((DJV_TextArea) view).getCustomViewAttribute().get(0).getValueKey();
            value = ((DJV_TextArea) view).getText().toString();
        } else if (view instanceof DJV_FileChooser) {
            key = ((DJV_FileChooser) view).getCustomViewAttribute().get(0).getValueKey();
            value = ((DJV_FileChooser) view).getText().toString();
        } else if (view instanceof DJV_CheckBox) {
            DJV_CheckBox djv_checkBox = ((DJV_CheckBox) view);
            if (djv_checkBox.isChecked()) {
                key = (djv_checkBox).getCustomViewAttribute().get(0).getValueKey();
                value = (djv_checkBox).getText().toString();
            }
        }

        try {
            jsonObject.put(key, value);
        } catch (JSONException e) {
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ((TextView) manipView).setText(data.getData().getPath());
        }
    }

    View manipView;

    @Override
    public void openFileChooser(View view) {
        manipView = view;
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("audio/*");
        chooseFile.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(chooseFile, "Choose a file"), 2);
    }
}
