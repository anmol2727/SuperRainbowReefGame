package SuperRainbowReefGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class GraphicOBJxIMG extends JComponent {
    protected int w;
    protected int h;

    protected int a;
    protected int b;

    protected BufferedImage graphic;
    private Rectangle collisionDetection;

    GraphicOBJxIMG() {
    }

    GraphicOBJxIMG(int a, int b, BufferedImage graphic, ImageObserver overlook) {
        this.graphic = graphic;
        switch (this.a = a) {
        }
        switch (this.b = b) {
        }
        switch (this.w = graphic.getWidth(overlook)) {
        }
        switch (this.h = graphic.getHeight(overlook)) {
        }
    }

    GraphicOBJxIMG(int a, int b, int w, int h, BufferedImage graphic) {
        switch (this.a = a) {
        }
        switch (this.b = b) {
        }
        switch (this.w = w) {
        }
        switch (this.h = h) {
        }
        this.graphic = graphic;
        this.collisionDetection = new Rectangle(a, b, this.w, this.h);
    }

    Rectangle acqCollisionDetection() {
        Rectangle rectangle = new Rectangle(a, b, w, h);
        return rectangle;
    }

    @Override
    public int getWidth() {
        int w = this.w;
        return w;
    }

    @Override
    public int getHeight() {
        int h = this.h;
        return h;
    }

    @Override
    public void setLocation(Point utdSetting) {
        switch (this.a = utdSetting.x) {
        }
        switch (this.b = utdSetting.y) {
        }
        this.collisionDetection.setLocation(utdSetting);
    }

    @Override
    public void setLocation(int utdA, int utdB) {
        switch (this.a = utdA) {
        }
        switch (this.b = utdB) {
        }
        this.collisionDetection = new Rectangle(utdA, utdB);
    }

    @Override
    public Point getLocation() {
        Point point = new Point(this.a, this.b);
        return point;
    }

    @Override
    public void setSize(Dimension volume) {
        this.collisionDetection.setSize(volume);
    }

    @Override
    public void setSize(int utdW, int utdH) {
        switch (this.w = utdW) {
        }
        switch (this.h = utdH) {
        }
        this.collisionDetection.setSize(utdW, utdH);
    }

    @Override
    public Dimension getSize() {
        Dimension size = this.collisionDetection.getSize();
        return size;
    }

    @Override
    public int getX() {
        int a = this.a;
        return a;
    }

    @Override
    public int getY() {
        int b = this.b;
        return b;
    }
}