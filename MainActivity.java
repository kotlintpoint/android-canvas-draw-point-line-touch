package tops.com.canvastouchpoint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new SceneView(this));
    }
    public class SceneView extends View {
        Matrix viewMatrix = new Matrix(), invertMatrix = new Matrix();
        Paint paint = new Paint();
        ArrayList<RectF> rectangles = new ArrayList<>();
        ArrayList<Point> points=new ArrayList<>();
        RectF moving = null;

        public SceneView(Context context) {
            super(context);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setColor(Color.BLUE);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            event.transform(invertMatrix);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    /*moving = null;
                    for (RectF f : rectangles) {
                        if (f.contains(event.getX(), event.getY())) {
                            moving = f;
                            return true;
                        }
                    }
                    viewMatrix.postTranslate(50, 50);
                    viewMatrix.postScale(.99f, .99f);
                    viewMatrix.postRotate(5);
                    invertMatrix = new Matrix(viewMatrix);
                    invertMatrix.invert(invertMatrix);*/
                    break;
                case MotionEvent.ACTION_MOVE:
                    /*if (moving != null) {
                        moving.set(event.getX() - 50, event.getY() - 50, event.getX() + 50, event.getY() + 50);
                    }*/
                    break;
                case MotionEvent.ACTION_UP:
                   // if (moving == null) {
                      //  rectangles.add(new RectF(event.getX() - 50, event.getY() - 50, event.getX() + 50, event.getY() + 50));

                    //}
            points.add(new Point((int)event.getX(),(int)event.getY()));
                    Log.i("X : ",event.getX()+"");
                    Log.i("Y : ",event.getY()+"");
                    break;
            }
            invalidate();
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.concat(viewMatrix);
            /*for (RectF f : rectangles) {
                canvas.drawRect(f, paint);
            }*/
            Point previousPoint=null;
            /*for (Point point:points){
                //canvas.drawPoint(point.x,point.y,paint);
                if(previousPoint==null){
                    previousPoint=point;
                }else{
                    canvas.drawCircle(point.x,point.y,5.0f,paint);
                    canvas.drawLine(previousPoint.x,previousPoint.y,
                            point.x,point.y,paint);
                    previousPoint=point;
                }
            }*/
            for(int i=0;i<points.size();i++){
                Point point=points.get(i);
                canvas.drawCircle(point.x,point.y,5.0f,paint);
                for (int j=i+1;j<points.size();j++){
                    Point secondPoint=points.get(j);
                    canvas.drawLine(point.x,point.y,
                            secondPoint.x,secondPoint.y,paint);

                }
            }
        }
    }
}
