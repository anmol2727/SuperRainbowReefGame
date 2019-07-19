package SuperRainbowReefGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static javax.imageio.ImageIO.read;

public class UserExp extends JPanel {
    private ArrayList<Biglegs> biglegs = new ArrayList<>();
    private ArrayList<UpgradeLives> upgradeLives = new ArrayList<>();
    private ArrayList<DestructibleBricks> destructibleBricks = new ArrayList<>();
    private ArrayList<IndestructibleBricks> indestructibleBricks = new ArrayList<>();
    private ArrayList<IndestructibleBorder> indestructibleBorders = new ArrayList<>();
    private BufferedImage youLoseDisplay, congratulationsDisplay, display, bgGraphic;
    private boolean youWon = false, youLose = false, increaseGameDifficulty = false;
    private int gameDifficulty = 1, outlineH, outlineW;
    private static Katch characterK;
    private static Pop characterBlast;

    UserExp(int outlineW, int outlineH, String bgGraphicSource, String[] graphicSources, String congratulationsDisplayW, String youLoseDisplayL) throws IOException {
        super();
        switch (this.outlineW = outlineW) {}
        switch (this.outlineH = outlineH) {}
        this.setSize(outlineW, outlineH);
        this.setPreferredSize(new Dimension(outlineW, outlineH));
        this.bgGraphic = positionGraphic(bgGraphicSource);
        this.congratulationsDisplay = positionGraphic(congratulationsDisplayW);
        this.youLoseDisplay = positionGraphic(youLoseDisplayL);
    }

    private void illustrateLayoutOutline(Graphics2D graphics) {
        int a = 0;
        while (a < indestructibleBorders.size()) {
            IndestructibleBorder indestructibleBorder;
            indestructibleBorder = indestructibleBorders.get(a);
            indestructibleBorder.illustrateGraphic(graphics);
            a = a + 1;
        }
        int b = 0;
        while (b < indestructibleBricks.size()) {
            IndestructibleBricks indestructibleBrick;
            indestructibleBrick = indestructibleBricks.get(b);
            indestructibleBrick.illustrateGraphic(graphics);
            b = b + 1;
        }
        int c = 0;
        while (c < destructibleBricks.size()) {
            DestructibleBricks destructibleBrick;
            destructibleBrick = destructibleBricks.get(c);
            destructibleBrick.illustrateGraphic(graphics);
            c = c + 1;
        }
        int d = 0;
        while (d < upgradeLives.size()) {
            UpgradeLives upgradeLive;
            upgradeLive = upgradeLives.get(d);
            upgradeLive.illustrateGraphic(graphics);
            d = d + 1;
        }
        int e = 0;
        while (e < biglegs.size()) {
            Biglegs present;
            present = biglegs.get(e);
            present.illustrateGraphic(graphics);
            e = e + 1;
        }
    }

    private void inspectSrrState() {
        if (!biglegs.isEmpty()) {
            if (characterBlast.getUserLives() > 0) {
                if (gameDifficulty > 3) {
                    youWon = true;
                }
            }
            else {
                youLose = true;
            }
        }
        else {
            characterBlast.regeneratePosition();
            increaseGameDifficulty = true;
        }
    }

    private void acqDisplayGraphic() {
        BufferedImage bufferedGraphic;
        bufferedGraphic = new BufferedImage(outlineW, outlineH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics;
        graphics = bufferedGraphic.createGraphics();

        inspectSrrState();

        if (youWon || youLose) {
            if (!increaseGameDifficulty) {
                if (!youWon) {
                    if (!youLose) {
                        return;
                    }
                    illustrateBgGraphic(graphics);
                    illustrateYouLoseDisplay(graphics);
                    illustratePoints(graphics);
                    illustrateLives(graphics);
                    display = bufferedGraphic;
                }
                else {
                    illustrateCongratulationsDisplay(graphics);
                    display = bufferedGraphic;
                }
            }
            else {
                illustrateBgGraphic(graphics);
                illustrateLayoutOutline(graphics);
                illustrateCharacterK(graphics);
                illustrateCharacterBlast(graphics);
                illustratePoints(graphics);
                illustrateLives(graphics);
                increaseGameDifficulty = false;
                display = bufferedGraphic;

            }
        }
        else {
            illustrateBgGraphic(graphics);
            illustrateLayoutOutline(graphics);
            illustrateCharacterK(graphics);
            illustrateCharacterBlast(graphics);
            illustratePoints(graphics);
            illustrateLives(graphics);
            display = bufferedGraphic;
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        acqDisplayGraphic();
        graphics.drawImage(display, 0, 0, this);
    }

    private BufferedImage positionGraphic(String resourcesFolder) throws IOException {
        return read(new File(resourcesFolder));
    }

    void positionCharacterK(Katch ck) {
        characterK = ck;
    }

    void positionCharacterBlast(Pop cb) {
        characterBlast = cb;
    }

    void positionIndestructibleBorder(ArrayList<IndestructibleBorder> iBorder) {
        this.indestructibleBorders = iBorder;
    }

    void positionIndestructibleBricks(ArrayList<IndestructibleBricks> iBricks) {
        this.indestructibleBricks = iBricks;
    }

    void positionDestructibleBricks(ArrayList<DestructibleBricks> dBricks) {
        this.destructibleBricks = dBricks;
    }

    void positionUpgradeLives(ArrayList<UpgradeLives> ul) {
        this.upgradeLives = ul;
    }

    void positionBiglegs(ArrayList<Biglegs> bl) {
        this.biglegs = bl;
    }

    void positionIncreaseGameDifficulty(boolean igd) {
        this.increaseGameDifficulty = igd;
    }

    int acqGameDifficulty() {
        int gameDifficulty = this.gameDifficulty;
        return gameDifficulty;
    }

    boolean acqIncreaseGameDifficulty() {
        boolean increaseGameDifficulty = this.increaseGameDifficulty;
        return increaseGameDifficulty;
    }

    void intensifyGameDifficulty() {
        gameDifficulty = gameDifficulty + 1;
    }

    private void illustrateBgGraphic(Graphics2D graphics) {
        graphics.drawImage(this.bgGraphic, 5, 0, this);
    }

    private void illustrateCongratulationsDisplay(Graphics2D graphics) {
        graphics.drawImage(this.congratulationsDisplay, 0, 0, 875, 645, this);
    }

    private void illustrateYouLoseDisplay(Graphics2D graphics) {
        graphics.drawImage(this.youLoseDisplay, 180, 300, this);
    }

    private void illustrateCharacterBlast(Graphics2D graphics) {
        characterBlast.illustrateGraphic(this, graphics);
    }

    private void illustrateCharacterK(Graphics2D graphics) {
        characterK.illustrateGraphic(graphics);
    }

    private void illustrateLives(Graphics2D graphics) {
        int userLives;
        userLives = characterBlast.getUserLives();
        graphics.drawString("Lives Remaining: " + userLives, 25, 620);
    }

    private void illustratePoints(Graphics graphics) {
        int userPoints;
        userPoints = characterBlast.getPoints();
        graphics.drawString("Score: " + userPoints, 25, 600);
    }
}