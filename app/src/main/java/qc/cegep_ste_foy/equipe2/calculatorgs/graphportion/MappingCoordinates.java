package qc.cegep_ste_foy.equipe2.calculatorgs.graphportion;

import android.graphics.Canvas;
import net.objecthunter.exp4j.*;
import net.objecthunter.exp4j.operator.*;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MappingCoordinates {

    public static final float aproxValue = 0.2F;
    public static final float delta= 0.05F;
    private String expression = "x";
    private boolean error = false;

    public String getExpression()
    {
        return this.expression;
    }

    public void setExpression(String value)
    {
        this.expression = value;
    }

    public boolean getError() { return this.error; }

    public void yAxisSweeping(float x, Canvas canvas)
    {
        if (this.expression.contains("!"))
        {
            float yi = -10;
            while (yi>=-10 && yi<=10) {
                Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {
                    @Override
                    public double apply(double... args) {
                        final int arg = (int) args[0];
                        double result = 1;
                        if ((double) arg != args[0]) {
                            result = 1;
                        }
                        if (arg < 0) {
                            result = 1;
                        }
                        for (int i = 1; i <= arg; i++) {
                            result *= i;
                        }
                        return result;
                    }
                };
                try
                {
                    double result = new ExpressionBuilder(this.expression)
                            .variables("x")
                            .operator(factorial)
                            .build()
                            .setVariable("x", x)
                            .evaluate();
                    if (result >= (yi - MappingCoordinates.delta) && result <= (yi + MappingCoordinates.delta)) {
                        MappingCoordinates.drawAPoint(canvas, x, yi * -1);
                    }
                }
                catch (Exception ex)
                {
                    this.error = true;
                }
                yi += MappingCoordinates.aproxValue;
            }

        }
        else
        {
            float yi = -10;
            while (yi >= -10 && yi <= 10) {
                try
                {
                    Expression e = new ExpressionBuilder(this.expression)
                            .variables("x")
                            .build()
                            .setVariable("x", x);
                    double valueToCompare = e.evaluate();
                    if (valueToCompare >= (yi - MappingCoordinates.delta) && valueToCompare <= (yi + MappingCoordinates.delta)) {
                        MappingCoordinates.drawAPoint(canvas, x, yi * -1);
                    }
                }
                catch (Exception ex)
                {
                   this.error = true;
                }
                yi += MappingCoordinates.aproxValue;
            }
        }
    }

    private boolean validateIfCoordinatesMeetCriteria(AbstractCoords point)
    {
        float y= Evaluate(point.getX());
        if (point.getY()==y)
        {
            return true;
        }
        return false;
    }

    private float Evaluate(float x)
    {
        return 0.1F;
    }

    public static void drawAPoint(Canvas canvas, float x, float y)
    {
        float radius = ToolBox.delta;
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        AbstractCoords p = new AbstractCoords(x,y,canvas.getWidth());
        float xReal = 0;
        try
        {
            xReal = p.convertAxisValueFromAbstractToReal(p.getX());
        }
        catch (Exception ex)
        {
            xReal=0;
        }
        float yReal = 0;
        try
        {
            yReal = p.convertAxisValueFromAbstractToReal(p.getY());
        }
        catch (Exception ex)
        {
            yReal=0;
        }
        canvas.drawCircle(xReal,yReal,radius,paint);
    }


}
