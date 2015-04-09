package dejavu.appzonegroup.com.dejavuandroid.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.appzone.zone.orchestra.engine.MobileFlow;
import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;
import com.appzone.zone.orchestra.engine.interfaces.StepResultCallback;
import com.appzone.zone.orchestra.engine.script.interpeter.JSInterpreterEngine;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.DataBases.ClientFlows;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.LocalEntityService;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 4/6/2015.
 */
public class ListFunction extends Fragment implements StepResultCallback {
    public static final String TAG = ListFunction.class.getSimpleName();

    public static String FUNCTION_KEY = "name";
    public static String FUNCTION_ID_KEY = "flow";

    public static final String SERVICE_UI = "Dejavu.UI";
    public static final String SERVICE_ENTITY = "Dejavu.ENTITY";
    public static final String SERVICE_GOTO = "Dejavu.GOTO";
    public static final String SERVICE_SCRIPT = "Dejavu.SCRIPT";

    private ArrayList<String> arrayListId;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_function, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.function_list);
        ArrayList<String> functionArrayList = getArguments().getStringArrayList(FUNCTION_KEY);
        arrayListId = getArguments().getStringArrayList(FUNCTION_ID_KEY);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, functionArrayList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);//choose your color :)
                return view;
            }
        };
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // check for children
//                ListFragment listFragment = new ListFragment();
//                Bundle bundle = new Bundle();
//                bundle.putStringArrayList(FUNCTION_KEY,null);
//                bundle.putStringArrayList(FUNCTION_ID_KEY,null);
//                listFragment.setArguments(bundle);
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.addToBackStack(null);
//                transaction.add(R.id.content_frame, listFragment).commitAllowingStateLoss();
                String clientFlow = ClientFlows.getFlowByIdAsString(getActivity(), arrayListId.get(position));
                if (clientFlow != null)
                loadFlow(clientFlow);

            }
        });
        return rootView;
    }

    public void loadFlow(String JsonString) {
        new ShowMessage(getActivity(), JsonString, Toast.LENGTH_LONG);
        MobileFlow mf = new MobileFlow(JsonString); // Create flow object
        StepsAbstraction sa = mf.getstepAbstractionion(); // Returns an object
        Step initStep = sa.getNextStep();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(R.id.content_frame, new UiControlTrier().newInstance(initStep)).commitAllowingStateLoss();
    }


    @Override
    public void onStepResult(StepsAbstraction stepsAbstraction, Step step, JSONObject jsonObject) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.addToBackStack(null);
        Log.e("OnStepResult", step.getStepId());
    }

    @Override
    public void onGetNextStep(Step step) {
        Log.e("OnStepResult", step.getStepId());
        String serviceName = step.getServiceName();
        switch (serviceName){
            //TODO: go through on the script and goto
            case SERVICE_UI:
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.add(R.id.content_frame, new UiControlTrier().newInstance(step))
                        .commitAllowingStateLoss();
                break;

            case SERVICE_ENTITY:
                //TODO: handles local entity service
                JSONObject data = null;
                try {
                    data = step.getData();
                } catch (JSONException e) {
                    Log.e(TAG+"/StepEntity", e.getMessage());
                }
                LocalEntityService.startLocalEntityService(getActivity(), step.getCommandName(),
                        data.toString());
                break;

            case SERVICE_SCRIPT:
                //TODO handles both script interpreter I and II
                JSInterpreterEngine jsEngine = new JSInterpreterEngine(getActivity());
                jsEngine.evaluateScript().evaluate(step.getCommandName());
                break;

            case SERVICE_GOTO:
                //TODO handles goto implementation
                break;
        }
    }
}



