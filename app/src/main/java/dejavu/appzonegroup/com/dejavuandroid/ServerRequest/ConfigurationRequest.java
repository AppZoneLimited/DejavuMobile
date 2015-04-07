package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import dejavu.appzonegroup.com.dejavuandroid.JSONReader.ConfigurationJsonReader;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class ConfigurationRequest {

    private onConfigurationRequest mConfigurationRequest;
    private Context mContext;

    public interface onConfigurationRequest {
        public void onConfigurationRequestSuccessful(int flow, boolean debit, boolean password, boolean hardToken, boolean softToken);

        public void onConfigurationRequestFailed();
    }

    public ConfigurationRequest(Context context, onConfigurationRequest configurationRequest) {
        mConfigurationRequest = configurationRequest;
        mContext = context;
        sendRequestToServer();
    }

    public void sendRequestToServer() {
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                new ConfigurationJsonReader(result, mConfigurationRequest);
            }
        }.execute();
    }
}
