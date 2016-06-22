package yh.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeTest {

	/**
	 * 生成图像
	 */
	@Test
	public void testEncode() {
		// 二维码图片存放位置
		String filePath = "E://";
		// 二维码图片的名字
		String fileName = "zxing.jpg";
		// 内容
		String content = "http://weixin.qq.com/r/moNieu7EoLPfraoZ99aI";
		// 图像宽度
		int width = 400;
		// 图像高度
		int height = 400;
		// 图像类型
		String format = "jpg";
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		// 设置QR二维码的纠错级别（H为最高级别）具体级别信息
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
			// 生成矩阵
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
					BarcodeFormat.QR_CODE, width, height, hints);
			Path path = FileSystems.getDefault().getPath(filePath, fileName);
			// 输出图像
			MatrixToImageWriter.writeToPath(bitMatrix, format, path);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("二维码生成了");
	}

	/**
	 * 给二维码图片添加Logo
	 * 
	 * @param qrPic
	 * @param logoPic
	 */
	@Test
	public void addLogo_QRCode() {

		try {
			File zxing = new File("E://zxing.jpg");
			File login = new File("E://login.jpg");	//需要加在zxing中间的login图片

			/**
			 * 读取二维码图片，并构建绘图对象
			 */
			BufferedImage image = ImageIO.read(zxing);
			Graphics2D g = image.createGraphics();

			/**
			 * 读取Logo图片
			 */
			BufferedImage logo = scale(login, image.getWidth()/5, image
					.getHeight()/5, true);
			int widthLogo = logo.getWidth(), heightLogo = logo.getHeight();

			// 计算图片放置位置
			int x = (image.getWidth() - widthLogo) / 2;
			int y = (image.getHeight() - logo.getHeight()) / 2;

			// 开始绘制图片
			g.drawImage(logo, x, y, widthLogo, heightLogo, null);
			g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
			// 设置边框为5
			g.setStroke(new BasicStroke(5));
			// 设置边框颜色为白色
			g.setColor(Color.WHITE);
			g.drawRect(x, y, widthLogo, heightLogo);

			g.dispose();

			ImageIO.write(image, "jpeg", new File("E:/newPic.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标
	 * 
	 * @param srcImageFile
	 *            源文件地址
	 * @param height
	 *            目标高度
	 * @param width
	 *            目标宽度
	 * @param hasFiller
	 *            比例不对时是否需要补白：true为补白; false为不补白;
	 * @throws IOException
	 */
	private BufferedImage scale(File file, int height, int width,
			boolean hasFiller) throws IOException {
		double ratio = 0.0; // 缩放比例
		BufferedImage srcImage = ImageIO.read(file);
		Image destImage = srcImage.getScaledInstance(width, height,
				BufferedImage.SCALE_SMOOTH);
		// 计算比例
		if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
			if (srcImage.getHeight() > srcImage.getWidth()) {
				ratio = (new Integer(height)).doubleValue()
						/ srcImage.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue()
						/ srcImage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform
					.getScaleInstance(ratio, ratio), null);
			destImage = op.filter(srcImage, null);
		}
		if (hasFiller) {// 补白
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = image.createGraphics();
			graphic.setColor(Color.white);
			graphic.fillRect(0, 0, width, height);
			if (width == destImage.getWidth(null))
				graphic.drawImage(destImage, 0, (height - destImage
						.getHeight(null)) / 2, destImage.getWidth(null),
						destImage.getHeight(null), Color.white, null);
			else
				graphic.drawImage(destImage,
						(width - destImage.getWidth(null)) / 2, 0, destImage
								.getWidth(null), destImage.getHeight(null),
						Color.white, null);
			graphic.dispose();
			destImage = image;
		}
		return (BufferedImage) destImage;
	}

	/**
	 * 解析图像
	 */
	@Test
	public void testDecode() {
		// 解析的图像的路径
		String filePath = "E://zxing.jpg";
		BufferedImage image;
		try {
			image = ImageIO.read(new File(filePath));
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
			System.out.println("解析结果 = " + result.toString());
			System.out.println("二维码格式类型 = " + result.getBarcodeFormat());
			System.out.println("二维码文本内容 = " + result.getText());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
}
