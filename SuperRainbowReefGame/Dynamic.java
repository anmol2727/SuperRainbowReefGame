package SuperRainbowReefGame;

import java.awt.image.BufferedImage;

class Dynamic extends GraphicOBJxIMG {
    Dynamic() {
    }

    double movementRate;

    Dynamic(BufferedImage graphic, int a, int b, double movementRate) {
        super(a, b, graphic, null);
        this.movementRate = movementRate;
    }

    double acqMovementRate() {
        double movementRate = this.movementRate;
        return movementRate;
    }
}