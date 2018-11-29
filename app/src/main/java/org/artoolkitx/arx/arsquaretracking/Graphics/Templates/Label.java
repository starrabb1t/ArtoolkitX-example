package org.artoolkitx.arx.arsquaretracking.Graphics.Templates;

import org.artoolkitx.arx.arsquaretracking.Graphics.Templates.Primitives.Line;
import org.artoolkitx.arx.arsquaretracking.Graphics.Templates.Primitives.Sprite;
import org.artoolkitx.arx.arsquaretracking.Graphics.fColor;
import org.artoolkitx.arx.arxj.rendering.ShaderProgram;

public class Label {

    private Sprite body;
    private Line border_left;
    private Line border_rigtht;
    private Line border_top;
    private Line border_bottom;
    private Line line_0;
    private Line line_1;

    public Label() {
        this(new fColor(1.0F, 1.0F, 1.0F),new fColor(0.0F, 0.0F, 0.0F));
    }

    public Label(fColor color, fColor border_color) {

        body = new Sprite(color,120.0f, 60.0f, 80.0f, 170.0f, 0.0f);
        border_left = new Line(border_color,20.0F,140.0F,20.0F,200.0F,0.0F,20.0F);
        border_rigtht = new Line(border_color,140.0F,140.0F,140.0F,200.0F,0.0F,20.0F);
        border_top = new Line(border_color,20.0F,200.0F,140.0F,200.0F,0.0F,20.0F);
        border_bottom = new Line(border_color,20.0F,140.0F,140.0F,140.0F,0.0F,20.0F);
        line_0 = new Line(border_color,0.0F,120.0F,80.0F,120.0F,0.0F,20.0F);
        line_1 = new Line(border_color,80.0F,120.0F,80.0F,140.0F,0.0F,20.0F);

    }

    public void draw(float[] projectionMatrix, float[] modelViewMatrix) {

        body.draw(projectionMatrix, modelViewMatrix);
        border_left.draw(projectionMatrix,modelViewMatrix);
        border_rigtht.draw(projectionMatrix,modelViewMatrix);
        border_top.draw(projectionMatrix,modelViewMatrix);
        border_bottom.draw(projectionMatrix,modelViewMatrix);
        line_0.draw(projectionMatrix,modelViewMatrix);
        line_1.draw(projectionMatrix,modelViewMatrix);

    }

    public void setShaderProgram(ShaderProgram shaderProgram) {
        body.setShaderProgram(shaderProgram);
        border_left.setShaderProgram(shaderProgram);
        border_rigtht.setShaderProgram(shaderProgram);
        border_top.setShaderProgram(shaderProgram);
        border_bottom.setShaderProgram(shaderProgram);
        line_0.setShaderProgram(shaderProgram);
        line_1.setShaderProgram(shaderProgram);
    }
}
