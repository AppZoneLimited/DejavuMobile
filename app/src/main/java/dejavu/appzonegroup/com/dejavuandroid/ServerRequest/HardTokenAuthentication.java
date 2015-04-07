package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.TokenAuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.JSONReader.HardTokenResponseJsonReader;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class HardTokenAuthentication {
    private Context mContext;
    private TokenAuthenticationListener mAuthenticationListener;


    public HardTokenAuthentication(Context context, TokenAuthenticationListener listener, String hardKey) {
        mAuthenticationListener = listener;
        mContext = context;
        sendHardToken(hardKey);
    }

    private void sendHardToken(String token) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                new HardTokenResponseJsonReader(s, mAuthenticationListener);
            }
        }.execute();
    }

}
