package dejavu.appzonegroup.com.dejavuandroid.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Adapter.FunctionAdapter;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.Function;
import dejavu.appzonegroup.com.dejavuandroid.DataBases.FunctionCategory;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.FlowSyncService;
import dejavu.appzonegroup.com.dejavuandroid.DataSynchronization.ZoneDataUtils;
import dejavu.appzonegroup.com.dejavuandroid.R;
import dejavu.appzonegroup.com.dejavuandroid.ToastMessageHandler.ShowMessage;

/**
 * Created by CrowdStar on 2/24/2015.
 */
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        GridView gridView = (GridView) findViewById(R.id.main_function_grid);

        ZoneDataUtils.syncDB(MainActivity.this);
        final ArrayList<FunctionCategory> categories = FunctionCategory.getAllFunctionCategory(MainActivity.this);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Function> functions = Function.getFunctionByCategory(MainActivity.this, categories.get(position).getId());
                ArrayList<String> arrayList = new ArrayList<String>();
                ArrayList<String> arrayListID = new ArrayList<String>();
                String keep = "";
                for (int x = 0; x < functions.size(); x++) {
                    arrayList.add(functions.get(x).getName());
                    arrayListID.add("" + functions.get(x).getFlowGuid());
                }

//                MobileFlow mf = new MobileFlow(loadJson(MainActivity.this)); // Create flow object
//                StepsAbstraction sa = mf.getstepAbstractionion(); // Returns an object
//                Step initStep = sa.getNextStep();
                ListFunction listfunction = new ListFunction();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(ListFunction.FUNCTION_KEY, arrayList);
                bundle.putStringArrayList(ListFunction.FUNCTION_ID_KEY, arrayListID);
                listfunction.setArguments(bundle);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.add(R.id.content_frame, listfunction).commitAllowingStateLoss();
            }
        });

        gridView.setAdapter(new FunctionAdapter(this, categories));
        ZoneDataUtils.copyDBToSDCard();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int result = Integer.parseInt(intent.getStringExtra("PARAM_MESSAGE"));
            if (result < 0) {
                new ShowMessage(MainActivity.this, "failure", Toast.LENGTH_LONG);
            } else {
                new ShowMessage(MainActivity.this, "success", Toast.LENGTH_LONG);
            }
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(MainActivity.this).registerReceiver(receiver, new IntentFilter("FlowSyncService.ACTION_DOWNLOAD_FUNCTION_CATEGORY"));

    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(MainActivity.this).unregisterReceiver(receiver);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.noti) {
            if (ZoneDataUtils.isNetworkAvailable(MainActivity.this)) {

//              /  new ShowMessage(MainActivity.this, " " + categories.size(), Toast.LENGTH_LONG);
                ZoneDataUtils.syncDB(MainActivity.this);
                FlowSyncService.startActionDownloadFunctionCategories(MainActivity.this);
                FlowSyncService.startActionDownloadFunctions(MainActivity.this);
                ArrayList<Function> functions = Function.getAllFunctions(MainActivity.this);
                if (functions.size() > 0){
                    for (int i=0; i<functions.size(); i++){
                        FlowSyncService.startActionDownloadFlows(MainActivity.this, functions.get(i).getId());
                    }
                }
            } else {
                new ShowMessage(MainActivity.this, "confirm failure", Toast.LENGTH_LONG);
            }
        }

        return super.onOptionsItemSelected(item);
    }

}