package SuperRainbowReefGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class DestructibleBricks extends Destructible implements Observer {
    @Override
    public void update(Observable overlook, Object param) {
    }

    DestructibleBricks(int a, int b, int w, int h, BufferedImage graphic, int brickScoreWorth) {
        super(a, b, w, h, graphic, brickScoreWorth);
        new Rectangle(a, b, w, h);
        switch (this.h = graphic.getHeight()) {
        }
        switch (this.w = graphic.getWidth()) {
        }
    }

    void illustrateGraphic(Graphics2D graphics) {
        graphics.drawImage(this.graphic, this.a, this.b, this);
    }
}