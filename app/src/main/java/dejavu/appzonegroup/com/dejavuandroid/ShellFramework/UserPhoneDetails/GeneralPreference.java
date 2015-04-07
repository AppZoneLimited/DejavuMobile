package dejavu.appzonegroup.com.dejavuandroid.ShellFramework.UserPhoneDetails;

import android.content.Context;

import dejavu.appzonegroup.com.dejavuandroid.Constant.FlowConstant;

/**
 * Created by CrowdStar on 2/24/2015.
 */
public class GeneralPreference {

    public static void setFlowType(Context context, int flowType) {
        context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).edit().putInt(GeneralPreference.class.getSimpleName() + ".flowType", flowType).commit();
    }

    public static int getFlowType(Context context) {
        return context.getSharedPreferences(GeneralPreference.class.getSimpleName(), Context.MODE_PRIVATE).getInt(GeneralPreference.class.getSimpleName() +".flowType", FlowConstant.WRONG_FLOW);
    }
}
