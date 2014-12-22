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

import action.ImageAction;

public class ImageChange extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel content;
	private JLabel img;
	private BufferedImage image;
	private BufferedImage image1;
	private int cvtColor;

	public ImageChange() {
		super();
		build();
	}

	private void build() {
		// lecture de l'image
		imagecolor();
		this.setTitle("Image HSV");
		this.setSize(500, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		// affichage de l'image modifie
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
		//Action sur les boutons pour modifier l'image
		JButton bouton = new JButton(new ImageAction(this, "Gray"));
		JButton bouton1 = new JButton(new ImageAction(this, "HSV"));
		JButton bouton2 = new JButton(new ImageAction(this, "reset"));

		boutons.add(bouton);
		boutons.add(bouton1);
		boutons.add(bouton2);

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
