package dejavu.appzonegroup.com.dejavuandroid.ServerRequest;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import dejavu.appzonegroup.com.dejavuandroid.Interfaces.CardAuthenticationListener;
import dejavu.appzonegroup.com.dejavuandroid.Models.DebitCardModel;
import dejavu.appzonegroup.com.dejavuandroid.JSONReader.DebitCardResponseJsonReader;

/**
 * Created by CrowdStar on 2/12/2015.
 */
public class DebitCardAuthentication {

    private Context mContext;
    private CardAuthenticationListener mCardAuthenticationListener;


    public DebitCardAuthentication(Context context, CardAuthenticationListener mListener, ArrayList<DebitCardModel> debitCardModels) {
        mContext = context;
        mCardAuthenticationListener = mListener;
        submitCardDetails(debitCardModels);
    }

    private void submitCardDetails(ArrayList<DebitCardModel> debitCardModels) {
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                new DebitCardResponseJsonReader(s, mCardAuthenticationListener);
            }
        }.execute();
    }
}
