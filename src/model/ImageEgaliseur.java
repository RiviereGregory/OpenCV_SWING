package model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Core;

import action.EgaliseurAction;

public class ImageEgaliseur extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel content;
	private JLabel img;
	private BufferedImage image;
	private BufferedImage image1;
	private int cvtColor;

	public ImageEgaliseur() {
		super();
		build();
	}

	private void build() {
		imagecolor();
		this.setTitle("Image HSV");
		this.setSize(500, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		setContentPane(buildContentPane());

	}

	private JPanel buildContentPane() {

		content = new JPanel();
		content.setLayout(new BorderLayout());
		JPanel imageContent = new JPanel();
		setImg(new JLabel(new ImageIcon(getImage())));
		getImg().setVerticalAlignment(JLabel.CENTER);
		getImg().setHorizontalAlignment(JLabel.CENTER);
		getImg().setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		imageContent.add(img);
		getContent().setSize(image.getWidth(), image.getHeight());
		getContent().add(imageContent, BorderLayout.CENTER);

		JPanel boutons = new JPanel();
		JButton bouton = new JButton(new EgaliseurAction(this, "Egaliseur"));
		JButton bouton1 = new JButton(new EgaliseurAction(this, "Gray"));
		JButton bouton2 = new JButton(new EgaliseurAction(this, "reset"));
		JButton bouton3 = new JButton(new EgaliseurAction(this, "Alpha"));
		JButton bouton4 = new JButton(new EgaliseurAction(this, "Sharpness"));

		boutons.add(bouton);
		boutons.add(bouton1);
		boutons.add(bouton2);
		boutons.add(bouton3);
		boutons.add(bouton4);

		getContent().add(boutons, BorderLayout.SOUTH);

		return content;
	}

	private void imagecolor() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		File input = new File("src/main/resources/lena1.png");
		try {
			image = ImageIO.read(input);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		// byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		// Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
		// mat.put(0, 0, data);
		//
		// Mat mat1 = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
		// Imgproc.cvtColor(mat, mat1, cvtColor);
		//
		// byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
		// mat1.get(0, 0, data1);
		// setImage1(new BufferedImage(mat1.cols(), mat1.rows(), 5));
		// getImage1().getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
		//
		// File ouptut = new File("src/main/resources/grayscale.jpg");
		// try {
		// ImageIO.write(getImage1(), "jpg", ouptut);
		// } catch (IOException e) {
		// System.out.println("Error: " + e.getMessage());
		// }
	}

	public JLabel getImg() {
		return img;
	}

	public void setImg(JLabel img) {
		this.img = img;
	}

	public JPanel getContent() {
		return content;
	}

	public void setContent(JPanel content) {
		this.content = content;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getCvtColor() {
		return cvtColor;
	}

	public void setCvtColor(int cvtColor) {
		this.cvtColor = cvtColor;
	}

	public BufferedImage getImage1() {
		return image1;
	}

	public void setImage1(BufferedImage image1) {
		this.image1 = image1;
	}
}
