package SuperRainbowReefGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import static java.lang.Math.*;

public class Katch extends Dynamic implements Observer {
    @Override
    public void update(Observable overlook, Object param) {
        blast();
        utdMotion();
        utdIndestructibleBorderImpact();
    }

    private Console c;
    private Pop characterBlast;
    private boolean motionWest, motionEast, blaster, authorizeBlast;
    private int west, east, blast, directionalPosition;
    private int settingA;
    private int settingB;

    public Katch() {
    }

    public Katch(Console c, BufferedImage graphic, int a, int b, int rate, int motionWestKey, int motionEastKey, int blastKey) {
        super(graphic, a, b, rate);
        switch (this.west = motionWestKey) {}
        switch (this.east = motionEastKey) {}
        switch (this.blast = blastKey) {}
        this.motionWest = false;
        this.motionEast = false;
        this.blaster = false;
        this.authorizeBlast = true;
        switch (this.settingA = a) {}
        switch (this.settingB = b) {}
        this.c = c;
        switch (this.directionalPosition = 245) {}
    }

    private void indestructibleBorderImpact(IndestructibleBorder iB) {
        if (iB == null) {
            return;
        }
        int kWest;
        kWest = a;
        int kEast;
        kEast = a + w;

        Rectangle indestructibleBorderImpactDetection;
        indestructibleBorderImpactDetection = new Rectangle(iB.getX(), iB.getY(), iB.getWidth(), iB.getHeight());
        int indestructibleBorderWest;
        indestructibleBorderWest = indestructibleBorderImpactDetection.x;
        int indestructibleBorderEast;
        indestructibleBorderEast = indestructibleBorderImpactDetection.x + indestructibleBorderImpactDetection.width;

        int uttermostDigit;
        uttermostDigit = Integer.MAX_VALUE;
        int[] junction;
        junction = new int[]{uttermostDigit, uttermostDigit};

        if (!(kEast > indestructibleBorderWest) || !(kWest < indestructibleBorderWest)) {}
        else {
            junction[0] = kEast - indestructibleBorderWest;
        }
        if (!(kWest < indestructibleBorderEast) || !(kEast > indestructibleBorderEast)) {}
        else {
            junction[1] = indestructibleBorderEast - kWest;
        }

        int lowest;
        lowest = uttermostDigit;
        int lowestIndicator;
        lowestIndicator = -1;
        int b = 0;
        while (b < 2) {
            if (junction[b] >= lowest) {}
            else {
                lowest = junction[b];
                lowestIndicator = b;
            }
            b = b + 1;
        }

        if (0 != lowestIndicator) {}
        else {
            a = (int) (a - (movementRate * 2));
        }
        if (1 != lowestIndicator) {
            return;
        }
        a = (int) (a + movementRate * 2);
    }

    private void blast() {
        if (!authorizeBlast || !blaster) {
            return;
        }
        this.characterBlast.setRateA(this.characterBlast.acqMovementRate() * cos(toRadians(directionalPosition)));
        this.characterBlast.setRateB(this.characterBlast.acqMovementRate() * sin(toRadians(directionalPosition)));
        this.characterBlast.setEngagementTrue();
        dialBlastFalse();
    }

    private void utdMotion() {
        if (!motionWest) {}
        else {
            a = (int) (a - (movementRate * 2));
        }
        if (!motionEast) {
            return;
        }
        a = (int) (a + movementRate * 2);
    }

    private IndestructibleBorder acqIndestructibleBorder() {
        Rectangle impactDetection;
        impactDetection = new Rectangle(a, b, w, h);
        ArrayList<IndestructibleBorder> acqIndestructibleBorder = this.c.acqIndestructibleBorder();
        return acqIndestructibleBorder.stream().filter(present -> impactDetection.intersects(present.acqCollisionDetection())).findFirst().orElse(null);
    }

    void dialMotionWestTrue() {
        this.motionWest = true;
    }

    void dialMotionWestFalse() {
        this.motionWest = false;
    }

    void dialMotionEastTrue() {
        this.motionEast = true;
    }

    void dialMotionEastFalse() {
        this.motionEast = false;
    }

    void dialBlasterTrue() {
        this.blaster = true;
    }

    void dialBlasterFalse() {
        this.blaster = false;
    }

    void dialBlastTrue() {
        this.authorizeBlast = true;
    }

    private void dialBlastFalse() {
        this.authorizeBlast = false;
    }

    void positionCharacterBlast(Pop characterBlast) {
        this.characterBlast = characterBlast;
    }

    int acqMotionWestKey() {
        int west = this.west;
        return west;
    }

    int acqMotionEastKey() {
        int east = this.east;
        return east;
    }

    int acqBlastKey() {
        int blast = this.blast;
        return blast;
    }

    boolean acqBlastState() {
        boolean authorizeBlast = this.authorizeBlast;
        return authorizeBlast;
    }

    void regeneratePosition() {
        switch (this.a = settingA) {}
        switch (this.b = settingB) {}
    }

    void illustrateGraphic(Graphics2D graphics) {
        graphics.drawImage(graphic, a, b, this);
    }

    private void utdIndestructibleBorderImpact() {
        indestructibleBorderImpact(acqIndestructibleBorder());
    }
}