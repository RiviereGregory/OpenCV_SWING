import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class Image {

	public static void main(String[] args) {
		JFrame fenetre = new JFrame();

		try {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			//Lecture de l'image
			File input = new File("src/main/resources/lena1.png");
			BufferedImage image = ImageIO.read(input);

			byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			// cr�ation d'une matrice de la taille de l'image est en couleur CV_8UC3
			Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
			// Remplissage matrice avec image
			mat.put(0, 0, data);
			// cr�ation d'une matrice de la taille de l'image est en NB CV_8UC1
			Mat mat1 = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
			// convertion image couleur en NB
			Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);

			byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
			mat1.get(0, 0, data1);
			BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
			image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
			// Enregistrement de l'image converti
			File ouptut = new File("src/main/resources/grayscale.jpg");
			ImageIO.write(image1, "jpg", ouptut);
			// Cr�ation de la fenetre avec SWING
			fenetre.setTitle("Image gray");
			fenetre.setSize(image.getWidth()+image.getWidth()/10, image.getHeight()+image.getHeight()/10);
			fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fenetre.setLocationRelativeTo(null);

			JPanel content = new JPanel();
			JLabel img = new JLabel(new ImageIcon(image1));
			img.setVerticalAlignment(JLabel.CENTER);
			img.setHorizontalAlignment(JLabel.CENTER);
			img.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
			content.setSize(image.getWidth(), image.getHeight());
			content.add(img);
			fenetre.setContentPane(content);
			fenetre.setVisible(true);

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
