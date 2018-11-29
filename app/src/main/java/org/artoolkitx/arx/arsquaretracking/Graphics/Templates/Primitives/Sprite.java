package org.artoolkitx.arx.arsquaretracking.Graphics.Templates.Primitives;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.artoolkitx.arx.arsquaretracking.Graphics.fColor;
import org.artoolkitx.arx.arxj.rendering.ARDrawable;
import org.artoolkitx.arx.arxj.rendering.RenderUtils;
import org.artoolkitx.arx.arxj.rendering.ShaderProgram;

public final class Sprite implements ARDrawable {
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;
    private ByteBuffer mIndexBuffer;
    private ShaderProgram shaderProgram;

    public Sprite(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }

    public Sprite() {
        this(new fColor(1.0F, 0.0F, 0.0F
        ), 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
    }

    public Sprite(fColor color, float width, float height, float x, float y, float z_index) {
        this.setArrays(color, width, height, x, y, z_index);
    }

    public FloatBuffer getmVertexBuffer() {
        return this.mVertexBuffer;
    }

    public FloatBuffer getmColorBuffer() {
        return this.mColorBuffer;
    }

    public ByteBuffer getmIndexBuffer() {
        return this.mIndexBuffer;
    }

    private void setArrays(fColor color, float width, float height, float x, float y, float z_index) {
        float ws = width / 2.0F;
        float hs = height / 2.0F;

        float[] vertices = new float[]{
                x - ws, y - hs, z_index,
                x + ws, y - hs, z_index,
                x + ws, y + hs, z_index,
                x - ws, y + hs, z_index
        };

        float[] colors = new float[]{
                color.red, color.green, color.blue, color.alpha,
                color.red, color.green, color.blue, color.alpha,
                color.red, color.green, color.blue, color.alpha,
                color.red, color.green, color.blue, color.alpha
        };
        byte[] indices = new byte[]{
                1, 2, 0,
                0, 2, 3
        };

        this.mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
        this.mColorBuffer = RenderUtils.buildFloatBuffer(colors);
        this.mIndexBuffer = RenderUtils.buildByteBuffer(indices);
    }

    public void draw(float[] projectionMatrix, float[] modelViewMatrix) {
        this.shaderProgram.setProjectionMatrix(projectionMatrix);
        this.shaderProgram.setModelViewMatrix(modelViewMatrix);
        this.shaderProgram.render(this.getmVertexBuffer(), this.getmColorBuffer(), this.getmIndexBuffer());
    }

    public void setShaderProgram(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }
}
