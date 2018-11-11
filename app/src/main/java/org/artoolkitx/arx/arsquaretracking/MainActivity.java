package org.artoolkitx.arx.arsquaretracking;

import android.os.Bundle;
import android.widget.FrameLayout;

import org.artoolkitx.arx.arxj.ARActivity;
import org.artoolkitx.arx.arxj.rendering.ARRenderer;

public class MainActivity extends ARActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /**
     * Provide our own Renderer.
     */
    @Override
    protected ARRenderer supplyRenderer() {
        return new eRenderer();
    }

    /**
     * Use the FrameLayout in this Activity's UI.
     */
    @Override
    protected FrameLayout supplyFrameLayout() {
        return (FrameLayout) this.findViewById(R.id.mainFrameLayout);
    }
}