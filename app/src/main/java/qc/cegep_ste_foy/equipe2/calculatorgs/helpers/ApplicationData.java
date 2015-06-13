package qc.cegep_ste_foy.equipe2.calculatorgs.helpers;

import java.util.UUID;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;

import qc.cegep_ste_foy.felipe.equipe2.calculatorgs.R;

public class ApplicationData {

    public static void saveCurrentSessionId(Context context, String CurrentSessionId) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = pref.edit();
		editor.putString(context.getString(R.string.current_session_id), CurrentSessionId);
		editor.commit();		
	}
	
	public static String getCurrentSessionId(Context context) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);		
		return pref.getString(context.getString(R.string.current_session_id), null);
	}
	

    public static String getSessionId(Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDeviceId, tmSerialNumber, androidId;
        tmDeviceId = "" + tm.getDeviceId();
        tmSerialNumber = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDeviceId.hashCode() << 32) | tmSerialNumber.hashCode());
        String deviceId = deviceUuid.toString();

        return deviceId;
    }


}
