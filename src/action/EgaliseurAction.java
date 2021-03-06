package action;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import model.ImageEgaliseur;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class EgaliseurAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ImageEgaliseur fenetre;
	private String type;

	public EgaliseurAction(ImageEgaliseur fenetre, String texte) {
		super(texte);
		this.setType(texte);
		this.setFenetre(fenetre);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		byte[] data = ((DataBufferByte) fenetre.getImage().getRaster().getDataBuffer()).getData();
		Mat mat = new Mat(fenetre.getImage().getHeight(), fenetre.getImage().getWidth(), CvType.CV_8UC3);
		mat.put(0, 0, data);

		if (type.equalsIgnoreCase("Egaliseur")) {
			System.out.println("Dans action "+type);
			Mat source = new Mat(fenetre.getImage().getHeight(), fenetre.getImage().getWidth(), CvType.CV_8UC1);
			Imgproc.cvtColor(mat, source, Imgproc.COLOR_RGB2GRAY);
			Mat destination = new Mat(fenetre.getImage().getHeight(), fenetre.getImage().getWidth(), CvType.CV_8UC1);
			
			Imgproc.equalizeHist(source, destination);
			
			byte[] data1 = new byte[destination.rows() * destination.cols() * (int) (destination.elemSize())];
			destination.get(0, 0, data1);
			fenetre.setImage1(new BufferedImage(destination.cols(), destination.rows(), BufferedImage.TYPE_BYTE_GRAY));
			fenetre.getImage1().getRaster().setDataElements(0, 0, destination.cols(), destination.rows(), data1);

			fenetre.getImg().setIcon(new ImageIcon(fenetre.getImage1()));

		} else if (type.equalsIgnoreCase("Gray")) {
			System.out.println("Dans action "+type);
			Mat mat1 = new Mat(fenetre.getImage().getHeight(), fenetre.getImage().getWidth(), CvType.CV_8UC1);
			Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);

			byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
			mat1.get(0, 0, data1);
			fenetre.setImage1(new BufferedImage(mat1.cols(), mat1.rows(), BufferedImage.TYPE_BYTE_GRAY));
			fenetre.getImage1().getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

			fenetre.getImg().setIcon(new ImageIcon(fenetre.getImage1()));

		}  else if (type.equalsIgnoreCase("Alpha")) {
			System.out.println("Dans action "+type);
			Mat mat1 = new Mat(fenetre.getImage().getHeight(), fenetre.getImage().getWidth(), CvType.CV_8UC3);
			mat.convertTo(mat1, -1, 1, 50);
			
			byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
			mat1.get(0, 0, data1);
			fenetre.setImage1(new BufferedImage(mat1.cols(), mat1.rows(), 5));
			fenetre.getImage1().getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

			fenetre.getImg().setIcon(new ImageIcon(fenetre.getImage1()));

		} else if (type.equalsIgnoreCase("Sharpness")) {
			System.out.println("Dans action "+type);
			Mat mat1 = new Mat(fenetre.getImage().getHeight(), fenetre.getImage().getWidth(), CvType.CV_8UC3);
			
			Imgproc.GaussianBlur(mat, mat1, new Size(0,0), 10);
	        Core.addWeighted(mat, 1.5, mat1, -0.5, 0, mat1);
	        
			byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
			mat1.get(0, 0, data1);
			fenetre.setImage1(new BufferedImage(mat1.cols(), mat1.rows(), 5));
			fenetre.getImage1().getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

			fenetre.getImg().setIcon(new ImageIcon(fenetre.getImage1()));

		}else {
			System.out.println("Dans action "+type);
			fenetre.getImg().setIcon(new ImageIcon(fenetre.getImage()));
		}

	}

	public ImageEgaliseur getFenetre() {
		return fenetre;
	}

	public void setFenetre(ImageEgaliseur fenetre) {
		this.fenetre = fenetre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
