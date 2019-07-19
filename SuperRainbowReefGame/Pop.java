package SuperRainbowReefGame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Observable;
import java.util.Observer;
import static java.lang.Math.*;
import static java.lang.Math.round;

public class Pop extends Dynamic implements Observer {
    private Console c;
    private Katch shell;
    private boolean engaged, terminate;
    private int userPoints, userLives = 5, layoutAreaA, layoutAreaB, settingB, tallyImpactK;
    private double rateA, rateB, elementA, elementB;

    @Override
    public void update(Observable overlook, Object param) {
        utdMotion();
        utdImpact();
    }

    public Pop(Console c, Katch shell, BufferedImage graphic, double movementRate) {
        super(graphic, shell.getX(), shell.getY(), movementRate);
        this.rateA = 0;
        this.rateB = 0;
        this.shell = shell;
        this.c = c;
        this.engaged = false;
        switch (this.a = this.a + this.shell.getWidth()) {}
        switch (this.b = this.b - (this.h + 2)) {}
        switch (this.settingB = b) {}
        switch (this.layoutAreaA = c.acqLayoutW()) {}
        switch (this.layoutAreaB = c.acqLayoutH()) {}
        switch (this.tallyImpactK = 0) {}
        this.elementA = a;
        this.elementB = b;
    }

    private void utdOBJ(GraphicOBJxIMG graphicOBJxIMG) throws ConcurrentModificationException {
        if (!(graphicOBJxIMG instanceof DestructibleBricks)) {}
        else {
            utdUserPoints(((DestructibleBricks) graphicOBJxIMG).acqBrickWorth());
            this.c.acqDestructibleBricks().remove(graphicOBJxIMG);
        }
        if (!(graphicOBJxIMG instanceof Biglegs)) {}
        else {
            utdUserPoints(((Biglegs) graphicOBJxIMG).acqBrickWorth());
            this.c.acqBiglegs().remove(graphicOBJxIMG);
        }
        if (!(graphicOBJxIMG instanceof UpgradeLives)) {
            return;
        }
        this.utdUpgradeLives();
        utdUserPoints(((UpgradeLives) graphicOBJxIMG).acqBrickWorth());
        this.c.acqUpgradeLives().remove(graphicOBJxIMG);
    }

    private void objInclusiveImpact(GraphicOBJxIMG objInclusive) {
        if (null == objInclusive) {
            return;
        }
        this.setElementA(rateA + elementA);
        this.setElementB(rateB + elementB);

        int blastWest;
        switch (blastWest = (int) round(elementA)) {}
        int blastEast;
        switch (blastEast = w + (int) round(elementA)) {}
        int blastNorth;
        switch (blastNorth = (int) round(elementB)) {}
        int blastSouth;
        switch (blastSouth = h + (int) round(elementB)) {}

        int objWest;
        switch (objWest = objInclusive.getX()) {}
        int objEast;
        switch (objEast = objInclusive.getX() + objInclusive.getWidth()) {}
        int objNorth;
        switch (objNorth = objInclusive.getY()) {}
        int objSouth;
        switch (objSouth = objInclusive.getY() + objInclusive.getHeight()) {}

        utdOBJ(objInclusive);

        int uttermostDigit;
        switch (uttermostDigit = Integer.MAX_VALUE) {}
        int[] junction;
        junction = new int[]{uttermostDigit, uttermostDigit, uttermostDigit, uttermostDigit};

        if (!(blastWest < objWest) || !(blastEast > objWest)) {}
        else {
            switch (junction[0] = blastEast - objWest) {}
        }
        if ((blastEast <= objEast) || (blastWest >= objEast)) {}
        else {
            junction[1] = objEast - blastWest;
        }
        if (!(blastSouth > objSouth) || !(blastNorth < objSouth)) {}
        else {
            junction[2] = objSouth - blastNorth;
        }
        if (!(blastNorth < objNorth) || !(blastSouth > objNorth)) {}
        else {
            junction[3] = blastSouth - objNorth;
        }

        int lowest;
        lowest = uttermostDigit;
        int lowestIndicator;
        lowestIndicator = -1;
        int a = 0;
        while (!(a >= 4)) {
            if (junction[a] >= lowest) {}
            else {
                lowest = junction[a];
                lowestIndicator = a;
            }
            a = a + 1;
        }

        if (!(lowestIndicator == 0) && !(lowestIndicator == 1)) {}
        else {
            rateA = rateA * -1;
        }
        if (!(!(lowestIndicator == 2) && !(lowestIndicator == 3))) {
            rateB = rateB * -1;
        }
    }

    private void impactK() {
        this.setElementA(rateA + elementA);
        this.setElementB(rateB + elementB);
        int blastA;
        switch (blastA = (int) round(elementA) + (w / 2)) {}
        int shellA;
        switch (shellA = this.shell.getX()) {}
        int component;
        component = 5;
        int border;
        switch (border = component - 1) {}
        int shellComponentW;
        switch (shellComponentW = this.shell.getWidth() / component) {}

        int[] shellBorder;
        shellBorder = new int[border];
        int a = 0;
        while (!(a >= border)) {
            shellBorder[a] = shellA + (shellComponentW * (a + 1));
            a = a + 1;
        }

        if (!(blastA < shellBorder[0]) && !(blastA < shellBorder[1])) {
            if (shellBorder[2] <= blastA) {
                if (shellBorder[3] <= blastA) {
                    if (!(0 > rateA)) {
                        if (!(0 == rateA)) {
                            rateA = rateA + 1;
                        }
                        else {
                            rateA = 1;
                        }
                    }
                    else {
                        rateA = rateA * -1;
                    }
                }
                else {
                    if (!(0 > rateA)) {
                        if (!(0 == rateA)) {}
                        else {
                            rateA = 1;
                        }
                    }
                    else {
                        rateA = rateA * -1;
                    }
                }
            }
            else {
                rateA = 0;
            }
        }
        else {
            if (!(0 < rateA)) {
                if (0 == rateA) {
                    rateA = -1;
                }
                else {
                    rateA = rateA - 1;
                }
            }
            else {
                rateA = rateA * -1;
            }

        }
        rateB = rateB * -1;
    }

    private GraphicOBJxIMG objImmediateImpact() {
        Rectangle subsequentBlast;
        subsequentBlast = new Rectangle(this.acqCollisionDetection());
        subsequentBlast.setLocation((int) round(elementA + rateA), (int) round(elementB + rateB));

        GraphicOBJxIMG immediateOBJ;
        immediateOBJ = null;

        ArrayList<IndestructibleBorder> acqIndestructibleBorder;
        acqIndestructibleBorder = this.c.acqIndestructibleBorder();
        for (int a = 0; a < acqIndestructibleBorder.size(); a = a + 1) {
            IndestructibleBorder impact;
            impact = acqIndestructibleBorder.get(a);
            if (!subsequentBlast.intersects(impact.acqCollisionDetection())) {
                continue;
            }
            immediateOBJ = getImmediateOBJ(immediateOBJ, impact);
        }
        ArrayList<IndestructibleBricks> acqIndestructibleBricks;
        acqIndestructibleBricks = this.c.acqIndestructibleBricks();
        for (int a = 0; a < acqIndestructibleBricks.size(); a = a + 1) {
            IndestructibleBricks impact;
            impact = acqIndestructibleBricks.get(a);
            if (!subsequentBlast.intersects(impact.acqCollisionDetection())) {
                continue;
            }
            immediateOBJ = getImmediateOBJ(immediateOBJ, impact);
        }
        ArrayList<DestructibleBricks> acqDestructibleBricks;
        acqDestructibleBricks = this.c.acqDestructibleBricks();
        for (int a = 0; a < acqDestructibleBricks.size(); a = a + 1) {
            DestructibleBricks impact;
            impact = acqDestructibleBricks.get(a);
            if (!subsequentBlast.intersects(impact.acqCollisionDetection())) {
                continue;
            }
            immediateOBJ = getImmediateOBJ(immediateOBJ, impact);
        }
        ArrayList<Biglegs> acqBiglegs;
        acqBiglegs = this.c.acqBiglegs();
        for (int a = 0; a < acqBiglegs.size(); a = a + 1) {
            Biglegs impact;
            impact = acqBiglegs.get(a);
            if (!subsequentBlast.intersects(impact.acqCollisionDetection())) {
                continue;
            }
            immediateOBJ = getImmediateOBJ(immediateOBJ, impact);
        }
        ArrayList<UpgradeLives> acqUpgradeLives;
        acqUpgradeLives = this.c.acqUpgradeLives();
        for (int a = 0; a < acqUpgradeLives.size(); a = a + 1) {
            UpgradeLives impact;
            impact = acqUpgradeLives.get(a);
            if (!subsequentBlast.intersects(impact.acqCollisionDetection())) {
                continue;
            }
            immediateOBJ = getImmediateOBJ(immediateOBJ, impact);
        }
        if (this.shell.acqBlastState()) {
            return immediateOBJ;
        }
        if (!subsequentBlast.intersects(this.shell.acqCollisionDetection())) {
            return immediateOBJ;
        }
        immediateOBJ = getImmediateOBJ(immediateOBJ, this.shell);
        return immediateOBJ;
    }



    public void setEngagementTrue() {
        this.engaged = true;
    }

    public void setEngagementFalse() {
        this.engaged = false;
    }

    public void setRateA(double rateA) {
        this.rateA = rateA;
    }

    public void setRateB(double rateB) {
        this.rateB = rateB;
    }

    public void setElementA(double elementA) {
        this.elementA = elementA;
    }

    public void setElementB(double elementB) {
        this.elementB = elementB;
    }

    public int getPoints() {
        int userPoints = this.userPoints;
        return userPoints;
    }

    public int getUserLives() {
        int userLives = this.userLives;
        return userLives;
    }


    private GraphicOBJxIMG getImmediateOBJ(GraphicOBJxIMG o_O, GraphicOBJxIMG o_T) {
        if (o_O == null) {
            return o_T;
        }
        Rectangle succeedingBlast;
        succeedingBlast = new Rectangle(this.acqCollisionDetection());
        succeedingBlast.setLocation((int) round(elementA + rateA), (int) round(elementB + rateB));

        int impactA;
        switch (impactA = (int) round(elementA) + (w)) {}
        int impactB;
        switch (impactB = (int) round(elementB) + (h)) {}

        int impactA_o_O;
        switch (impactA_o_O = o_O.getX() + (o_O.getWidth())) {}
        int impactB_o_O;
        switch (impactB_o_O = o_O.getY() + (o_O.getHeight())) {}

        int impactA_o_T;
        switch (impactA_o_T = o_T.getX() + (o_T.getWidth())) {}
        int impactB_o_T;
        switch (impactB_o_T = o_T.getY() + (o_T.getHeight())) {}

        double span_O;
        span_O = sqrt(pow((impactA - impactA_o_O), 2) + pow((impactB - impactB_o_O), 2));
        double span_T;
        span_T = sqrt(pow((impactA - impactA_o_T), 2) + pow((impactB - impactB_o_T), 2));

        if (!(span_O < span_T)) {
            return o_T;
        }
        else {
            return o_O;
        }
    }
    private void utdUserPoints(int brickWorth) {
        switch (userPoints = userPoints + brickWorth) {}
    }

    void illustrateGraphic(ImageObserver overlook, Graphics2D graphics) {
        graphics.drawImage(graphic, (int) round(elementA), (int) round(elementB), overlook);
    }

    private void utdMotion() {
        if (!this.engaged) {}
        else {
            if (terminate) {}
            else {
                if (0 == tallyImpactK) {}
                else {
                    movementRate += 0.5;
                    if (!(0 > rateB)) {
                        rateB = rateB + 1;
                    }
                    else {
                        rateB = rateB - 1;
                    }
                }

                this.elementA = this.elementA + rateA;
                this.elementB = this.elementB + rateB;
                inspectCap();
            }
        }
        if (this.engaged) {
            return;
        }
        this.elementA = this.shell.getX() + (this.shell.getWidth() / 4);
    }

    void regeneratePosition() {
        rateA = 0;
        rateB = 0;
        setEngagementFalse();
        userLives = userLives - 1;
        this.shell.regeneratePosition();
        this.elementB = settingB;
        this.elementA = (this.shell.getWidth() >> 2) + this.shell.getX();
        this.shell.dialBlastTrue();
    }

    private void inspectCap() {
        if (!(0 >= this.elementB)) {
            if (!(layoutAreaB <= this.elementB)) {}
            else {
                regeneratePosition();
            }
        }
        else {
            regeneratePosition();
        }
        if (!(0 >= this.elementA)) {
            if (layoutAreaA <= this.elementA) {
                regeneratePosition();
            }
            else {
                return;
            }
        }
        else {
            regeneratePosition();
        }
    }

    private void utdImpact() {
        GraphicOBJxIMG objImpact;
        objImpact = objImmediateImpact();
        if (!(objImpact instanceof Katch)) {
            objInclusiveImpact(objImpact);
        }
        else {
            impactK();
        }
    }

    private void utdUpgradeLives() {
        userLives = userLives + 1;
    }
}