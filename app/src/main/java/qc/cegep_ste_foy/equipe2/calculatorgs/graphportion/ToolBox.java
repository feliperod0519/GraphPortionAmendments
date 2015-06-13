package qc.cegep_ste_foy.equipe2.calculatorgs.graphportion;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import java.util.Hashtable;
import android.util.Log;

public class ToolBox
{
    public static final float delta= 5;

    public static Hashtable getLimitCoords(Canvas canvas)
    {
        Hashtable<String,RealCoords> h = new Hashtable<String,RealCoords>();
        h.put("0,0",new RealCoords(0,0));
        h.put("MAX,0",new RealCoords(canvas.getWidth(),0));
        h.put("0,MAX",new RealCoords(0,canvas.getWidth()));
        h.put("MAX,MAX",new RealCoords(canvas.getWidth(),canvas.getWidth()));
        return h;
    }

    public static float getRealUnitSize(Canvas canvas)
    {
        return canvas.getWidth()/2;
    }


}
