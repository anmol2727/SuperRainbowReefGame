package SuperRainbowReefGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class UpgradeLives extends Destructible implements Observer {
    @Override
    public void update(Observable overlook, Object param) {
    }

    UpgradeLives(int a, int b, int w, int h, BufferedImage graphic, int livesScoreWorth) {
        super(a, b, w, h, graphic, livesScoreWorth);
        switch (this.h = graphic.getHeight()) {
        }
        switch (this.w = graphic.getWidth()) {
        }
        new Rectangle(a, b, w, h);
    }

    void illustrateGraphic(Graphics2D graphics) {
        graphics.drawImage(this.graphic, this.a, this.b, this);
    }
}