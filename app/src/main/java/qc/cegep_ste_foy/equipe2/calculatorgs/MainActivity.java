package qc.cegep_ste_foy.equipe2.calculatorgs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import qc.cegep_ste_foy.equipe2.calculatorgs.graphportion.GraphPortionActivity;
import qc.cegep_ste_foy.equipe2.calculatorgs.helpers.ApplicationData;
import qc.cegep_ste_foy.equipe2.calculatorgs.helpers.NetworkRequest;
import qc.cegep_ste_foy.equipe2.calculatorgs.models.DeviceOperation;
import qc.cegep_ste_foy.equipe2.calculatorgs.models.NetworkResult;
import qc.cegep_ste_foy.felipe.equipe2.calculatorgs.R;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends ActionBarActivity  {
	
	EditText editText, edittext2;
	
	Button button0, button1, button2, button3, button4,
		   button5, button6, button7, button8, button9;
	
	Button buttonPlus, buttonMinus, buttonMultiply, buttonDivide,
		   buttonModulo, buttonReset, buttonPoint, buttonNeg,
		   buttonFnc;

    Button buttonSin, buttonCos, buttonTan;

    Button buttonLog, buttonLog10;

    Button buttonShowHistory;

	String equation="";
	Vibrator vibrator;
	Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        calculator = new Calculator();

        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button5=(Button)findViewById(R.id.button5);
        button6=(Button)findViewById(R.id.button6);
        button7=(Button)findViewById(R.id.button7);
        button8=(Button)findViewById(R.id.button8);
        button9=(Button)findViewById(R.id.button9);

        buttonPlus=(Button)findViewById(R.id.buttonPlus);
        buttonMinus=(Button)findViewById(R.id.buttonMinus);
		buttonMultiply=(Button)findViewById(R.id.buttonMultiply);
		buttonDivide=(Button)findViewById(R.id.buttonDivide);
		buttonModulo=(Button)findViewById(R.id.buttonModulo);
		buttonReset=(Button)findViewById(R.id.buttonReset);
		buttonPoint=(Button)findViewById(R.id.buttonPoint);
		buttonNeg=(Button)findViewById(R.id.buttonNeg);
		buttonFnc=(Button)findViewById(R.id.buttonFn);

        buttonSin = (Button)findViewById(R.id.buttonSin);
        buttonCos = (Button)findViewById(R.id.buttonCos);
        buttonTan = (Button)findViewById(R.id.buttonTang);

        buttonLog = (Button)findViewById(R.id.buttonLog);
        buttonLog10 = (Button)findViewById(R.id.buttonLog10);

        buttonShowHistory = (Button)findViewById(R.id.buttonShowHistory);

        editText=(EditText)findViewById(R.id.editText1);  
        edittext2=(EditText)findViewById(R.id.editText2);  
        
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); 
       
        editText.setText("0.0");

        findDeviceOperationsForCurrentDeviceId();

    }

     public void addcar(char val) {
    	vibrator.vibrate(30);
    	equation=equation+val;
        edittext2.setText(equation);
    }

    public void addString(String string) {
        vibrator.vibrate(30);
        equation=equation+string;
        edittext2.setText(equation);
    }

    public void onClickListenerFnc(View v){
    }  
     
    public void onClickListenerNeg(View v){
            addcar('-');
    }  
     
    public void onClickListenerPoint(View v){
        addcar('.');
    } 
     
    public void onClickListener0(View v){
        addcar('0');
    }
    
    public void onClickListener1(View v){
        addcar('1');
    }
    
    public void onClickListener2(View v){
        addcar('2');
    }
    
    public void onClickListener3(View v){
        addcar('3');
    }
    
    public void onClickListener4(View v){
        addcar('4');
    }
    
    public void onClickListener5(View v){
        addcar('5');
    }
    
    public void onClickListener6(View v){
        addcar('6');
    }
    
    public void onClickListener7(View v){
        addcar('7');
    }
    
    public void onClickListener8(View v){
        addcar('8');
    }
    
    public void onClickListener9(View v){
        addcar('9');
    }
    
    public void onClickListenerPlus(View v){
        if(equation != "") addcar('+');
    }

    public void onClickListenerMinus(View v){
        if(equation != "") addcar('-');
    }
    
    public void onClickListenerMultiply(View v){
        if(equation != "") addcar('*');
    }
    
    public void onClickListenerDivide(View v){
        if(equation != "") addcar('/');
    }
    
    public void onClickListenerModulo(View v){
        if(equation != "") addcar('%');
    }
    
    public void onClickListenerReset(View v){
    	vibrator.vibrate(30);
    	equation="";
        edittext2.setText(equation);
        editText.setText("0.0");
    }
	
    public void onClickListenerEqual(View v){
        vibrator.vibrate(30);
 
        calculator.setEquation(equation);
		try {
			calculator.calculate();
		} catch (Exception e) {
            Toast.makeText(this, getString(R.string.calculation_error), Toast.LENGTH_LONG).show();
            e.printStackTrace();
		}
        double res = calculator.getResult();
        String total2 = String.valueOf(res);
        editText.setText(total2);

        saveToHistory(equation);
    }

    private void saveToHistory(String operation) {
        final String deviceId = (ApplicationData.getCurrentSessionId(this) == null)? ApplicationData.getSessionId(this):ApplicationData.getCurrentSessionId(this);
        DeviceOperation deviceOperation = new DeviceOperation(deviceId, operation);
        NetworkRequest.api(this).createDeviceOperation(deviceOperation, new Callback<NetworkResult>() {
            @Override
            public void success(NetworkResult networkResult, Response response) {
                Log.i("saveToHistory", "Success: " + networkResult.getResult());
                ApplicationData.saveCurrentSessionId(MainActivity.this, deviceId);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("saveToHistory", "Error: " + error.toString());
            }
        });
    }

    public void onClickListenerSin(View v){
        equation = "";
        addString(getString(R.string.sin_trigo));
    }

    public void onClickListenerCos(View v){
        equation = "";
        addString(getString(R.string.cos_trigo));
    }

    public void onClickListenerTan(View v){
        equation = "";
        addString(getString(R.string.tan_trigo));
    }

    public void onClickListenerOpenParenthese(View v){
        addString(getString(R.string.open_parenth));
    }

    public void onClickListenerCloseParenthese(View v){
        addString(getString(R.string.close_parenth));
    }

    public void onClickListenerLog(View v){
        equation = "";
        addString(getString(R.string.log));
    }

    public void onClickListenerLog10(View v){
        equation = "";
        addString(getString(R.string.log_10));
    }

    public void onClickListenerFactorial(View v){
        addString(getString(R.string.factorial));
    }

    public void onClickListenerVariableX(View v){
        addString(getString(R.string.variable_x));
    }

    public void onClickListenerGraph(View v){

        if (equation != null && equation.length()> 0) {
            Intent intentGraph = GraphPortionActivity.getIntentGraphPortionActivity(this, equation);
            this.startActivity(intentGraph);
        } else {
            Toast.makeText(this,getString(R.string.no_equation_for_graph), Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickListenerExp(View v){
        addString(getString(R.string.variable_exp));
    }

    public void onClickListenerPi(View v){
        addString(getString(R.string.variable_pi));
    }
    public void onClickListenerConstE(View v){
        addString(getString(R.string.variable_E));
    }

    public void onClickListenerShowHistory(View v) {
        findDeviceOperationsForCurrentDeviceId();
    }

    public void onClickListenerClearHistory(View v){
        String deviceId = ApplicationData.getCurrentSessionId(this);
        NetworkRequest.api(this).deleteDeviceOperationsForDeviceId(deviceId, new Callback<NetworkResult>() {
            @Override
            public void success(NetworkResult networkResult, Response response) {
                Toast.makeText(MainActivity.this, getString(R.string.clear_history_success), Toast.LENGTH_SHORT).show();
                edittext2.setText("");
                editText.setText("0.0");
                equation = "";
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Get device history", "Error: " + error.toString());
                Toast.makeText(MainActivity.this, getString(R.string.clear_history_error), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void findDeviceOperationsForCurrentDeviceId() {
        String deviceId = ApplicationData.getCurrentSessionId(this);
        NetworkRequest.api(this).findDeviceOperationsForDeviceId(deviceId, new Callback<List<DeviceOperation>>() {
            @Override
            public void success(List<DeviceOperation> deviceOperations, Response response) {
                String history = "";
                for (DeviceOperation deviceOperation : deviceOperations) {
                    String line = deviceOperation.getOperation() + "\n";
                    history += line;
                }

                edittext2.setText(history);
                equation = "";
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("Get device history", "Error: " + error.toString());
                Toast.makeText(MainActivity.this, getString(R.string.get_history_error), Toast.LENGTH_LONG).show();

            }
        });
    }

}
