package SuperRainbowReefGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.logging.Level.*;
import static java.util.logging.Logger.*;
import static javax.imageio.ImageIO.*;

public class Console implements Runnable {
    private int SCREEN_WIDTH = 874, SCREEN_HEIGHT = 648, OUTLINE_WIDTH = 874, OUTLINE_HEIGHT = 648;
    private int[][] outlineSetup;
    private boolean sustained = false;
    private String graphicSources[];
    private ArrayList<Biglegs> biglegs;
    private ArrayList<UpgradeLives> upgradeLives;
    private ArrayList<DestructibleBricks> destructibleBricks;
    private ArrayList<IndestructibleBricks> indestructibleBricks;
    private ArrayList<IndestructibleBorder> indestructibleBorder;
    private static Katch characterK;
    private static Pop characterBlast;
    private UserExp positionOutline;
    private final OverlookOBJ overlook;
    private Thread trdExe;

    public Console() {
        this.overlook = new OverlookOBJ();
    }

    public static void main(String args[]) {
        Console play;
        play = new Console();
        play.develop();
    }

    @Override
    public void run() {
        try {
            load();
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }

        try {
            if (sustained) {
                do {
                    this.overlook.setChanged();
                    this.overlook.notifyObservers();
                    execute();
                    Thread.sleep(1000 / 144);
                    if (!positionOutline.acqIncreaseGameDifficulty()) {}
                    else {
                        positionOutline.positionIncreaseGameDifficulty(false);
                        positionOutline.intensifyGameDifficulty();
                        destructibleBricks.removeAll(destructibleBricks);
                        indestructibleBricks.removeAll(indestructibleBricks);
                        upgradeLives.removeAll(upgradeLives);
                        outlineSetup(positionOutline.acqGameDifficulty());
                        buildOutlineOBJ();
                    }
                }
                while (sustained);
            }
        }
        catch (InterruptedException exception) {
            getLogger(Console.class.getName()).log(SEVERE, null, exception);
        }
        terminate();
    }

    private void outlineSetup(int gameDifficulty) {
        if (1 == gameDifficulty) {
            this.outlineSetup = new int[][] {
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 12, 0, 6, 0, 5, 0, 7, 0, 1, 0, 2, 0, 3, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 3, 0, 2, 0, 1, 0, 7, 0, 5, 0, 6, 0, 12, 0, 8},
                    {8, 12, 0, 6, 0, 5, 0, 7, 0, 1, 0, 2, 0, 3, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 3, 0, 2, 0, 1, 0, 7, 0, 5, 0, 6, 0, 12, 0, 8},
                    {8, 12, 0, 6, 0, 5, 0, 7, 0, 1, 0, 2, 0, 3, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 3, 0, 2, 0, 1, 0, 7, 0, 5, 0, 6, 0, 12, 0, 8},
                    {8, 12, 0, 6, 0, 5, 0, 7, 0, 1, 0, 2, 0, 3, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 3, 0, 2, 0, 1, 0, 7, 0, 5, 0, 6, 0, 12, 0, 8},
                    {8, 12, 0, 6, 0, 5, 0, 7, 0, 1, 0, 2, 0, 3, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 3, 0, 2, 0, 1, 0, 7, 0, 5, 0, 6, 0, 12, 0, 8},
                    {8, 12, 0, 6, 0, 5, 0, 7, 0, 1, 0, 2, 0, 3, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 3, 0, 2, 0, 1, 0, 7, 0, 5, 0, 6, 0, 12, 0, 8},
                    {8, 12, 0, 6, 0, 5, 0, 7, 0, 1, 0, 2, 0, 3, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 3, 0, 2, 0, 1, 0, 7, 0, 5, 0, 6, 0, 12, 0, 8},
                    {8, 12, 0, 6, 0, 5, 0, 7, 0, 1, 0, 2, 0, 3, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 3, 0, 2, 0, 1, 0, 7, 0, 5, 0, 6, 0, 12, 0, 8},
                    {8, 12, 0, 6, 0, 5, 0, 7, 0, 1, 0, 2, 0, 3, 0, 7, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 7, 0, 3, 0, 2, 0, 1, 0, 7, 0, 5, 0, 6, 0, 12, 0, 8},
                    {8, 12, 0, 6, 0, 5, 0, 7, 0, 1, 0, 2, 0, 3, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 3, 0, 2, 0, 1, 0, 7, 0, 5, 0, 6, 0, 12, 0, 8},
                    {8, 12, 0, 6, 0, 5, 0, 7, 0, 9, 0, 2, 0, 9, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 9, 0, 2, 0, 9, 0, 7, 0, 5, 0, 6, 0, 12, 0, 8},
                    {8, 12, 0, 9, 0, 5, 0, 7, 0, 1, 0, 9, 0, 3, 0, 4, 0, 3, 0, 4, 0, 3, 0, 4, 0, 3, 0, 4, 0, 7, 0, 3, 0, 9, 0, 1, 0, 7, 0, 5, 0, 9, 0, 12, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},

            };
        }
        else if (2 == gameDifficulty) {
            this.outlineSetup = new int[][] {
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 9, 0, 9, 0, 7, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 7, 0, 9, 0, 9, 0, 8},
                    {8, 1, 0, 2, 0, 7, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 7, 0, 2, 0, 1, 0, 8},
                    {8, 1, 0, 2, 0, 7, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 7, 0, 2, 0, 1, 0, 8},
                    {8, 1, 0, 2, 0, 7, 0, 7, 0, 7, 0, 0, 0, 0, 0, 10, 0, 0, 0, 10, 0, 0, 0, 0, 0, 10, 0, 0, 0, 10, 0, 0, 0, 0, 0, 7, 0, 7, 0, 7, 0, 2, 0, 1, 0, 8},
                    {8, 3, 0, 6, 0, 2, 0, 3, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 3, 0, 2, 0, 6, 0, 3, 0, 8},
                    {8, 4, 0, 5, 0, 2, 0, 1, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 1, 0, 1, 0, 5, 0, 4, 0, 8},
                    {8, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 12, 0, 12, 0, 6, 0, 5, 0, 4, 0, 3, 0, 2, 0, 1, 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 6, 0, 12, 0, 1, 0, 8},
                    {8, 2, 0, 1, 0, 4, 0, 3, 0, 6, 0, 9, 0, 12, 0, 12, 0, 5, 0, 6, 0, 9, 0, 4, 0, 1, 0, 2, 0, 2, 0, 1, 0, 9, 0, 3, 0, 6, 0, 5, 0, 1, 0, 12, 0, 8},
                    {8, 4, 0, 4, 0, 1, 0, 6, 0, 3, 0, 12, 0, 5, 0, 12, 0, 6, 0, 3, 0, 4, 0, 1, 0, 2, 0, 2, 0, 1, 0, 4, 0, 6, 0, 1, 0, 4, 0, 2, 0, 3, 0, 4, 0, 8},
                    {8, 9, 0, 9, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 9, 0, 9, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
            };
        }
        else if (3 == gameDifficulty) {
            this.outlineSetup = new int[][] {
                    {8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 0, 0, 10, 0, 0, 0, 0, 10, 0, 0, 0, 0, 10, 0, 0, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 9, 0, 12, 0, 7, 0, 1, 0, 2, 0, 9, 0, 7, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 7, 0, 9, 0, 2, 0, 1, 0, 7, 0, 12, 0, 9, 0, 8},
                    {8, 5, 0, 12, 0, 7, 0, 1, 0, 2, 0, 4, 0, 7, 0, 3, 0, 12, 0, 2, 0, 4, 0, 1, 0, 3, 0, 5, 0, 3, 0, 7, 0, 4, 0, 2, 0, 1, 0, 7, 0, 12, 0, 5, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 5, 0, 12, 0, 2, 0, 1, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
                    {8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8},
            };
        }
    }

    private void loadOutlineResources() {
        switch (this.SCREEN_WIDTH = 874) {}
        switch (this.SCREEN_HEIGHT = 648) {}
        switch (this.OUTLINE_WIDTH = 874) {}
        switch (this.OUTLINE_HEIGHT = 648) {}
    }

    private void compile() {
        JFrame console;
        console = new JFrame("Super Rainbow Reef Game");

        BufferedImage graphicK;
        graphicK = positionGraphic("Resources/Katch.gif");
        BufferedImage graphicP;
        graphicP = positionGraphic("Resources/Pop.gif");

        Console.characterK = new Katch(this, graphicK, this.OUTLINE_WIDTH / 2 - graphicK.getWidth() / 2, this.OUTLINE_HEIGHT - graphicK.getHeight() - graphicP.getHeight(), 4, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
        Console.characterBlast = new Pop(this, Console.characterK, graphicP, 2);
        Console.characterK.positionCharacterBlast(Console.characterBlast);

        KatchControl kc;
        kc = new KatchControl(characterK);

        this.overlook.addObserver(characterK);
        this.overlook.addObserver(characterBlast);
        this.positionOutline.positionCharacterK(Console.characterK);
        this.positionOutline.positionCharacterBlast(Console.characterBlast);

        outlineSetup(positionOutline.acqGameDifficulty());
        buildOutlineOBJ();

        console.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        console.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        console.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        console.setResizable(false);
        console.setLocationRelativeTo(null);
        console.add(this.positionOutline);
        console.addKeyListener(kc);
        console.setVisible(true);
    }

    private void buildOutlineOBJ() {
        indestructibleBorder = new ArrayList<>();
        indestructibleBricks = new ArrayList<>();
        destructibleBricks = new ArrayList<>();
        biglegs = new ArrayList<>();
        upgradeLives = new ArrayList<>();

        BufferedImage graphicOBJ;
        int unitMag = 19;
        int a = 0;
        while (a < 33) {
            int b = 0;
            while (b < 46) {
                if (outlineSetup[a][b] != 1) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Block1.gif");
                    destructibleBricks.add(new DestructibleBricks(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ, 25));                }
                if (outlineSetup[a][b] != 2) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Block2.gif");
                    destructibleBricks.add(new DestructibleBricks(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ, 25));                }
                if (outlineSetup[a][b] != 3) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Block3.gif");
                    destructibleBricks.add(new DestructibleBricks(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ, 25));
                }
                if (outlineSetup[a][b] != 4) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Block4.gif");
                    destructibleBricks.add(new DestructibleBricks(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ, 25));
                }
                if (outlineSetup[a][b] != 5) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Block5.gif");
                    destructibleBricks.add(new DestructibleBricks(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ, 25));
                }
                if (outlineSetup[a][b] != 6) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Block6.gif");
                    destructibleBricks.add(new DestructibleBricks(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ, 25));
                }
                if (outlineSetup[a][b] != 12) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Block7.gif");
                    destructibleBricks.add(new DestructibleBricks(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ, 25));
                }
                if (outlineSetup[a][b] != 7) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Block_solid.gif");
                    indestructibleBricks.add(new IndestructibleBricks(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ));
                }
                if (outlineSetup[a][b] != 8) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Wall.gif");
                    indestructibleBorder.add(new IndestructibleBorder(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ));
                }
                if (outlineSetup[a][b] != 9) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Block_life.gif");
                    upgradeLives.add(new UpgradeLives(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ, 60));
                }
                if (outlineSetup[a][b] != 10) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Bigleg_small.gif");
                    biglegs.add(new Biglegs(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ, 150));
                }
                if (outlineSetup[a][b] != 11) {}
                else {
                    graphicOBJ = positionGraphic("Resources/Bigleg.gif");
                    biglegs.add(new Biglegs(b * unitMag, a * unitMag, graphicOBJ.getWidth(), graphicOBJ.getHeight(), graphicOBJ, 300));
                }
                b = b + 1;
            }
            a = a + 1;
        }
        this.positionOutline.positionIndestructibleBorder(indestructibleBorder);
        this.positionOutline.positionIndestructibleBricks(indestructibleBricks);
        this.positionOutline.positionDestructibleBricks(destructibleBricks);
        this.positionOutline.positionUpgradeLives(upgradeLives);
        this.positionOutline.positionBiglegs(biglegs);

        int c = 0;
        while (c < indestructibleBorder.size()) {
            IndestructibleBorder border = indestructibleBorder.get(c);
            this.overlook.addObserver(border);
            c = c + 1;
        }
        int d = 0;
        while (d < indestructibleBricks.size()) {
            IndestructibleBricks indestructibleBrick = indestructibleBricks.get(d);
            this.overlook.addObserver(indestructibleBrick);
            d = d + 1;
        }
        int e = 0;
        while (e < destructibleBricks.size()) {
            DestructibleBricks destructibleBrick = destructibleBricks.get(e);
            this.overlook.addObserver(destructibleBrick);
            e = e + 1;
        }
        int f = 0;
        while (f < upgradeLives.size()) {
            UpgradeLives upgradeLive = upgradeLives.get(f);
            this.overlook.addObserver(upgradeLive);
            f = f + 1;
        }
        int g = 0;
        while (g < biglegs.size()) {
            Biglegs present = biglegs.get(g);
            this.overlook.addObserver(present);
            g = g + 1;
        }
    }

    int acqLayoutW() {
        int outline_width = this.OUTLINE_WIDTH;
        return outline_width;
    }

    int acqLayoutH() {
        int outline_height = this.OUTLINE_HEIGHT;
        return outline_height;
    }

    ArrayList<Biglegs> acqBiglegs() {
        ArrayList<Biglegs> biglegs = this.biglegs;
        return biglegs;
    }

    ArrayList<UpgradeLives> acqUpgradeLives() {
        ArrayList<UpgradeLives> upgradeLives = this.upgradeLives;
        return upgradeLives;
    }

    ArrayList<DestructibleBricks> acqDestructibleBricks() {
        ArrayList<DestructibleBricks> destructibleBricks = this.destructibleBricks;
        return destructibleBricks;
    }

    ArrayList<IndestructibleBricks> acqIndestructibleBricks() {
        ArrayList<IndestructibleBricks> indestructibleBricks = this.indestructibleBricks;
        return indestructibleBricks;
    }

    ArrayList<IndestructibleBorder> acqIndestructibleBorder() {
        ArrayList<IndestructibleBorder> indestructibleBorder = this.indestructibleBorder;
        return indestructibleBorder;
    }

    private void execute() {
        this.positionOutline.repaint();
    }

    private synchronized void develop() {
        if (!sustained) {
            sustained = true;
            trdExe = new Thread(this);
            trdExe.start();
        }
        else {
            return;
        }
    }

    private BufferedImage positionGraphic(String resourcesFolder) {
        BufferedImage graphic;
        graphic = null;
        try {
            graphic = read(new File(resourcesFolder));
        } catch (IOException exception) {
            System.out.println("***error loading graphic***" + exception.getMessage());
        }
        return graphic;
    }

    private void load() throws IOException {
        loadOutlineResources();
        this.positionOutline = new UserExp(OUTLINE_WIDTH, OUTLINE_HEIGHT, "Resources/Background1.png", graphicSources, "Resources/Congratulation.png", "Resources/GameOver.gif");
        compile();
    }

    private void terminate() {
        if (sustained) {
            sustained = false;
            try {
                trdExe.join();
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
        else {
            return;
        }
    }
}