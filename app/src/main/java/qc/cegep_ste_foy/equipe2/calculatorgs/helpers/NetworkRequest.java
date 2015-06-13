package qc.cegep_ste_foy.equipe2.calculatorgs.helpers;

import android.content.Context;

import qc.cegep_ste_foy.felipe.equipe2.calculatorgs.R;
import retrofit.RestAdapter;

public class NetworkRequest {
		
	private static NetworkRequestApiRetrofit api;
	
	public static NetworkRequestApiRetrofit api(Context context) {
		
		if (api == null) {

			RestAdapter adapter = new RestAdapter.Builder()
			.setEndpoint(context.getString(R.string.end_point))
			.build();
			api = adapter.create(NetworkRequestApiRetrofit.class);
		}
		
		return api;
	}
	
	

}
