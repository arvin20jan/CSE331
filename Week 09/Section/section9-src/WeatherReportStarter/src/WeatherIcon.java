import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Displays an icon indicating a weather state. Icons are stored in IMAGE_PATH,
 * and correspond to the string representation of the instances of
 * WeatherReportModel.WeatherState.
 */
public class WeatherIcon extends JComponent {
	private static final long serialVersionUID = 1L;

	// Constants for the look-and-feel of the thermometer
	public static final int IMAGE_SIZE = 48;
	private static final String IMAGE_PATH = "../images/";
	private static final String IMAGE_EXT = ".png";

	// The current state to display
	private WeatherReportModel.WeatherState state;

	public WeatherIcon() {
		this.setPreferredSize(new Dimension(IMAGE_SIZE, IMAGE_SIZE));
	}

	/**
	 * Sets the state to be displayed
	 * 
	 * @param state
	 *            the state to display
	 */
	public void setState(WeatherReportModel.WeatherState state) {
		this.state = state;

		// Since our state has updated, we need to repaint.
		repaint();
	}

	/**
	 * Paints this component on the given Graphics.
	 * 
	 * @param g
	 *            the graphics to use when painting
	 */
	@Override
	public void paintComponent(Graphics g) {
		// The superclass' paintComponent must always be called first.
		super.paintComponent(g);

		// An ugly but permissible cast, since we have knowledge of Swing's
		// implementation
		Graphics2D g2d = (Graphics2D) g;

		// Load the image corresponding to the current state
		String path = IMAGE_PATH + state.toString() + IMAGE_EXT;
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

		g2d.drawImage(img, this.getWidth() / 2 - IMAGE_SIZE / 2, this.getHeight()
				/ 2 - IMAGE_SIZE / 2, null);
	}
}
