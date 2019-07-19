package SuperRainbowReefGame;

import java.awt.image.BufferedImage;

class Destructible extends Static {
    private int obj;

    Destructible(int a, int b, int w, int h, BufferedImage graphic, int objScoreWorth) {
        super(a, b, w, h, graphic);
        switch (this.obj = objScoreWorth) {
        }
    }

    int acqBrickWorth() {
        int obj = this.obj;
        return obj;
    }
}