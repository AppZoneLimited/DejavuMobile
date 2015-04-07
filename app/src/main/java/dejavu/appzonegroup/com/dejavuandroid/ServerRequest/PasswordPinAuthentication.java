package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.AuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.Models.PasswordPinModel;
import dejavu.appzonegroup.com.dejavuandroid.JSONReader.PasswordPinJsonReader;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class PasswordPinAuthentication {

    private Context mContext;
    private AuthenticationListener authenticationListener;



    public PasswordPinAuthentication(Context context, AuthenticationListener listener, ArrayList<PasswordPinModel> passwordPinModels) {
        authenticationListener = listener;
        mContext = context;
        AuthUserDetails(passwordPinModels);
    }

    private void AuthUserDetails(ArrayList<PasswordPinModel> passwordPinModels) {
        new AsyncTask<String, String, String>() {

            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                new PasswordPinJsonReader(s,authenticationListener);
            }
        }.execute();
    }
}
