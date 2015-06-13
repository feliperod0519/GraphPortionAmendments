package qc.cegep_ste_foy.equipe2.calculatorgs.graphportion;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;

import qc.cegep_ste_foy.felipe.equipe2.calculatorgs.R;


public class GraphPortionActivity extends ActionBarActivity {

    public static String KEY_EXPRESSION = "expression";

    CustomView graphCustomView;
    String expression;

    public static Intent getIntentGraphPortionActivity(Context context, String expression){
        Intent intent = new Intent(context, GraphPortionActivity.class);
        intent.putExtra(KEY_EXPRESSION, expression);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_portion);

        graphCustomView = (CustomView) findViewById(R.id.graphCustomView);

        String expression = getIntent().getStringExtra(KEY_EXPRESSION);
        if (expression != null) {
            graphCustomView.setExpression(expression);
            String title = expression;
            title = title.replaceAll("3.14159","π");
            title = title.replaceAll("2.7182818284","e");
            setTitle(title);
        }
    }

}
