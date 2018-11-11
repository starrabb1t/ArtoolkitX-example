package org.artoolkitx.arx.arsquaretracking;

import android.opengl.GLES20;

import org.artoolkitx.arx.arsquaretracking.Graphics.Primitives.Sprite;
import org.artoolkitx.arx.arsquaretracking.Graphics.fColor;
import org.artoolkitx.arx.arxj.ARController;
import org.artoolkitx.arx.arxj.Trackable;
import org.artoolkitx.arx.arxj.rendering.ARRenderer;
import org.artoolkitx.arx.arxj.rendering.shader_impl.SimpleFragmentShader;
import org.artoolkitx.arx.arxj.rendering.shader_impl.SimpleShaderProgram;
import org.artoolkitx.arx.arxj.rendering.shader_impl.SimpleVertexShader;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class eRenderer extends ARRenderer {

    private SimpleShaderProgram shaderProgram;

    private static final Trackable trackables[] = new Trackable[]{
            new Trackable("id_0", 80.0f),
            new Trackable("id_1", 80.0f),
            new Trackable("id_2", 80.0f)
    };
    private int trackableUIDs[] = new int[trackables.length];

    private Sprite sprite_0;
    private Sprite sprite_1;
    private Sprite sprite_2;

    /**
     * Markers can be configured here.
     */
    @Override
    public boolean configureARScene() {
        int i = 0;
        for (Trackable trackable : trackables) {
            trackableUIDs[i] = ARController.getInstance().addTrackable("single;Data/" + trackable.getName() + ".patt;" + trackable.getWidth());
            if (trackableUIDs[i] < 0) return false;
            i++;
        }
        return true;
    }

    //Shader calls should be within a GL thread. GL threads are onSurfaceChanged(), onSurfaceCreated() or onDrawFrame()
    //As the cube instantiates the shader during setShaderProgram call we need to create the cube here.
    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        this.shaderProgram = new SimpleShaderProgram(new SimpleVertexShader(), new SimpleFragmentShader());

        sprite_0 = new Sprite(new fColor(1.0F,0.0F,0.0F),40.0f, 40.0f, 0.0f, 0.0f, 0.0f);
        sprite_0.setShaderProgram(shaderProgram);

        sprite_1 = new Sprite(new fColor(0.0F,1.0F,0.0F),40.0f, 40.0f, 0.0f, 0.0f, 0.0f);
        sprite_1.setShaderProgram(shaderProgram);

        sprite_2 = new Sprite(new fColor(0.0F,0.0F,1.0F),40.0f, 40.0f, 0.0f, 0.0f, 0.0f);
        sprite_2.setShaderProgram(shaderProgram);

        super.onSurfaceCreated(unused, config);
    }

    /**
     * Override the draw function from ARRenderer.
     */
    @Override
    public void draw() {
        super.draw();

        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        GLES20.glFrontFace(GLES20.GL_CCW);

        // Look for trackables, and draw on each found one.
        for (int trackableUID : trackableUIDs) {
            // If the trackable is visible, apply its transformation, and render a cube
            float[] modelViewMatrix = new float[16];
            if (ARController.getInstance().queryTrackableVisibilityAndTransformation(trackableUID, modelViewMatrix)) {
                float[] projectionMatrix = ARController.getInstance().getProjectionMatrix(10.0f, 10000.0f);

                switch(trackableUID){
                    case 0:
                        sprite_0.draw(projectionMatrix, modelViewMatrix);
                        break;
                    case 1:
                        sprite_1.draw(projectionMatrix, modelViewMatrix);
                        break;
                    case 2:
                        sprite_2.draw(projectionMatrix, modelViewMatrix);
                        break;
                }
            }
        }
    }
}