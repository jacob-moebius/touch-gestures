package edu.miracostacollege.cs134.touchgestures;

import android.accessibilityservice.GestureDescription;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity
        extends AppCompatActivity
        implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    /**
     * Turn on GestureDetector to listen for gestures
     * Gestures include:
     * 1) OnDown - make contact with device surface
     * 2) onPress - Holding/maintain contact with device surface
     * 3) onLongPress - Holding down for more than 100ms
     * 4) on SingleTapUp - After releasing contact
     * 5) onDoubleTap - two single taps up in succession
     * 6) onScroll - maintaining contact while moving up, down, left, or right
     * 7) onFling (swipe) - onScroll with higher velocity
    */

    private GestureDetector mGestureDetector;

    private ScrollView gesturesScrollView;

    private TextView gesturesLogTextView;
    private TextView singleTapTextView;
    private TextView doubleTapTextView;
    private TextView longPressTextView;
    private TextView scrollTextView;
    private TextView flingTextView;
    private Button clearButtonView;

    private int singleTaps = 0, doubleTaps = 0, longPresses = 0, scrolls = 0, flings = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gesturesScrollView = findViewById(R.id.gesturesScrollView);
        gesturesLogTextView = findViewById(R.id.gesturesLogTextView);
        singleTapTextView = findViewById(R.id.singleTapTextView);
        doubleTapTextView = findViewById(R.id.doubleTapTextView);
        longPressTextView = findViewById(R.id.longPressTextView);
        scrollTextView = findViewById(R.id.scrollTextView);
        flingTextView = findViewById(R.id.flingTextView);
        clearButtonView = findViewById(R.id.clearButton);

        // Instruct the gesture detector to listen to the ScrollView
        mGestureDetector = new GestureDetector(gesturesScrollView.getContext(), this);
    }

    /**
     * Called to process touch screen events.  You can override this to
     * intercept all touch screen events before they are dispatched to the
     * window.  Be sure to call this implementation for touch screen events
     * that should be handled normally.
     *
     * @param ev The touch screen event.
     * @return boolean Return true if this event was consumed.
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        super.dispatchTouchEvent(ev);
        return mGestureDetector.onTouchEvent(ev);
    }

    /**
     * // This event occurs when use lets go (releases contact after onDown)
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        gesturesLogTextView.append("\nonSingleTapConfirmed");
        singleTapTextView.setText(String.valueOf(++singleTaps));
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        gesturesLogTextView.append("\nonDoubleTapConfirmed");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        doubleTapTextView.setText(String.valueOf(++doubleTaps));
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        gesturesLogTextView.append(
                String.format(
                        "\nonScroll: distanceX is: %s distanceX is: %s", distanceX, distanceY));
        scrollTextView.setText(String.valueOf(++scrolls));
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        gesturesLogTextView.append("\nonLongPressConfirmed");
        longPressTextView.setText(String.valueOf(++longPresses));
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        gesturesLogTextView.append(
                String.format(
                        "\nonFling: velocityX is: %s velocityY is: %s", velocityX, velocityY));
        flingTextView.setText(String.valueOf(++flings));
        return true;
    }

    public void clearAll(View v) {
        longPresses = 0;
        singleTaps = 0;
        doubleTaps = 0;
        scrolls = 0;
        flings = 0;
        longPressTextView.setText(String.valueOf(longPresses));
        singleTapTextView.setText(String.valueOf(singleTaps));
        doubleTapTextView.setText(String.valueOf(doubleTaps));
        scrollTextView.setText(String.valueOf(scrolls));
        flingTextView.setText(String.valueOf(flings));
        gesturesLogTextView.setText("");
    }
}
