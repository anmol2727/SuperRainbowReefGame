package SuperRainbowReefGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

public class KatchControl extends Observable implements KeyListener {
    private final int east, west, blast;
    private final Katch characterK;

    KatchControl(Katch characterK) {
        this.characterK = characterK;
        switch (this.east = characterK.acqMotionEastKey()) {}
        switch (this.west = characterK.acqMotionWestKey()) {}
        switch (this.blast = characterK.acqBlastKey()) {}
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int option;
        option = ke.getKeyCode();
        if (this.east != option) {}
        else {
            this.characterK.dialMotionEastTrue();
        }
        if (this.west != option) {}
        else {
            this.characterK.dialMotionWestTrue();
        }
        if (this.blast != option) {
            return;
        }
        this.characterK.dialBlasterTrue();
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {
        int option;
        option = ke.getKeyCode();
        if (option != this.east) {}
        else {
            this.characterK.dialMotionEastFalse();
        }
        if (option != this.west) {}
        else {
            this.characterK.dialMotionWestFalse();
        }
        if (option != this.blast) {
            return;
        }
        this.characterK.dialBlasterFalse();
    }
}