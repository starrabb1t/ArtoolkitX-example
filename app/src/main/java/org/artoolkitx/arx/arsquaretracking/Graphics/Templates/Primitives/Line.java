package org.artoolkitx.arx.arsquaretracking.Graphics.Templates.Primitives;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.artoolkitx.arx.arsquaretracking.Graphics.fColor;
import org.artoolkitx.arx.arxj.rendering.ARDrawable;
import org.artoolkitx.arx.arxj.rendering.RenderUtils;
import org.artoolkitx.arx.arxj.rendering.ShaderProgram;

public final class Line implements ARDrawable {
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;
    private ByteBuffer mIndexBuffer;
    private ShaderProgram shaderProgram;

    public Line(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }

    public Line() {
        this(new fColor(1.0F, 0.0F, 0.0F
        ), 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.0F);
    }

    public Line(fColor color, float x0, float y0, float x, float y, float z_index, float width) {
        this.setArrays(color, x0, y0, x, y, z_index, width);
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

    private void setArrays(fColor color,  float x0, float y0, float x, float y, float z_index, float width) {
        float ws = width / 20.0F;
        float dx;
        float dy;
        float[] vertices;

        if (x0 != x) {
            float alpha = (float)Math.atan((y-y0)/(x-x0));
            dy = ws*(float)Math.cos(alpha);
            dx = ws*(float)Math.sin(alpha);

            if (x0 <  x) {
                vertices = new float[]{
                        x0 - dx, y0 + dy, z_index,
                        x0 + dx, y0 - dy, z_index,
                        x + dx, y - dy, z_index,
                        x - dx, y + dy, z_index
                };
            }
            else {
                vertices = new float[]{
                        x - dx, y + dy, z_index,
                        x + dx, y - dy, z_index,
                        x0 + dx, y0 - dy, z_index,
                        x0 - dx, y0 + dy, z_index
                };
            }
        }
        else {
            dx = ws;
            dy = 0;

            if (y0 <  y) {
                vertices = new float[]{
                        x0 - dx, y0 + dy, z_index,
                        x0 + dx, y0 - dy, z_index,
                        x + dx, y - dy, z_index,
                        x - dx, y + dy, z_index
                };
            }
            else {
                vertices = new float[]{
                        x0 + dx, y0 - dy, z_index,
                        x0 - dx, y0 + dy, z_index,
                        x - dx, y + dy, z_index,
                        x + dx, y - dy, z_index
                };
            }
        }

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