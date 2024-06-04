package Objects;
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Class in charge of the pitch to the batter
 * Code from Justin Montagne, Sam Goldstein, Arizona Belden, and Anthony Russo
 * Some names have been modified and I will be working on modifying the code to make it better
 * Just using the other code for a base set up
 * 
 * 
 * @author Colin Willits
 * @version June 2024
 */
public class Ball extends AnimatedObject {
    public static final int DELAY_TIME = 33;

    //pixels to move each frame
    public int ySpeed = 4;

    // latest location of the ball
    private Point2D.Double upperLeft;
    
    //Bottom of the panel
    private int bottom;

    //Image of the baseball
    private static Image baseballImage;

    private static final String ballPicName = "baseball.jpg.png";

    /**
     * Constructs a Ball object
     * @param topCenter: initial point at which the top of the ball should be drawn
     * @param container component in which the ball is being drawn to repaint it
     * @param ySpeed
     */
    public Ball(Point2D.Double topCenter, JComponent container, int ySpeed) {
        super(container);
        Random r = new Random();

        upperLeft = new Point2D.Double(topCenter.x - 100 / 2, topCenter.y);
        this.bottom = container.getHeight();
        this.container = container;
        this.ySpeed = r.nextInt(9) + 5;
    }

    public void paint(Graphics g) {
        g.drawImage(baseballImage, (int)upperLeft.x, (int)upperLeft.y, null);

    }

    public void run() {
        //Ball still moves as long as it is not at the bottom of the screen

        while (upperLeft.y < bottom) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {
            }

            upperLeft.setLocation(upperLeft.x, upperLeft.y + ySpeed);

            container.repaint();
        }
        done = true;
    }


    public static void loadBaseballPicture() {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Ball.baseballImage = tool.getImage(ballPicName).getScaledInstance(30, 30, Image.SCALE_DEFAULT);
    }

    public Point2D.Double getLocation() {
        return new Point2D.Double(upperLeft.x + 15, upperLeft.y + 15);
    }



}
