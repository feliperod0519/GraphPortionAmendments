package qc.cegep_ste_foy.equipe2.calculatorgs.helpers;

import java.util.List;

import qc.cegep_ste_foy.equipe2.calculatorgs.models.DeviceOperation;
import qc.cegep_ste_foy.equipe2.calculatorgs.models.NetworkResult;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface NetworkRequestApiRetrofit {
		
	@POST("/operations")
	public void createDeviceOperation(@Body DeviceOperation deviceOperation, Callback<NetworkResult> networkResult);

    @GET("/operations/{deviceId}")
    public void findDeviceOperationsForDeviceId(@Path("deviceId") String deviceId, Callback<List<DeviceOperation>> deviceOperations);

    @DELETE("/operations/{deviceId}")
    public void deleteDeviceOperationsForDeviceId(@Path("deviceId") String deviceId, Callback<NetworkResult> networkResult);

}
