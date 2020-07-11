package com.cxl.image;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;

public class ImageUtil {
    //    public void CutImage(String srcPath,String subPath,int x,int y,int width,int height){
//        try {
//            BufferedImage image= ImageIO.read(new File(srcPath));
//            int srcWidth = image.getWidth();
//            int srcHeight = image.getHeight();
//            if (srcWidth>=width&&srcHeight>=height){
//                Image image1=image.getScaledInstance(srcWidth,srcHeight,Image.SCALE_DEFAULT);
//                ImageFilter filter=new CropImageFilter(x,y,width,height);
//                Image image2 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image1.getSource(), filter));
//                BufferedImage tar = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//                Graphics graphics = tar.getGraphics();
//                graphics.drawImage(image2,0,0,null);
//                graphics.dispose();
//                ImageIO.write(tar,"PNG",new File(subPath));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public void cut(){
//        try {
//            Thumbnails.of("/home/cxl/cxl/test-demo/test/src/main/resources/331077379.png").scale(0.1,0.1).outputQuality(1.0f).allowOverwrite(true).toFile("111.png");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public void zoomImage(File imageFile, String newPath, int width, int height) throws IOException {
        if (imageFile != null && !imageFile.canRead())
            return;
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        if (null == bufferedImage)
            return;

        zoomImageUtils(imageFile, newPath, bufferedImage, width, height);
    }

    private static void zoomImageUtils(File imageFile, String newPath, BufferedImage bufferedImage, int width, int height)
            throws IOException {

        String suffix = StringUtils.substringAfterLast(imageFile.getName(), ".");

        // 处理 png 背景变黑的问题
        if (suffix != null && (suffix.trim().toLowerCase().endsWith("png") || suffix.trim().toLowerCase().endsWith("gif"))) {
            BufferedImage to = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            g2d.dispose();

            g2d = to.createGraphics();
            Image from = bufferedImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();

            ImageIO.write(to, suffix, new File(newPath));
        } else {
            // 高质量压缩，其实对清晰度而言没有太多的帮助
//            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            tag.getGraphics().drawImage(bufferedImage, 0, 0, width, height, null);
//
//            FileOutputStream out = new FileOutputStream(newPath);    // 将图片写入 newPath
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
//            jep.setQuality(1f, true);    //压缩质量, 1 是最高值
//            encoder.encode(tag, jep);
//            out.close();

            BufferedImage newImage = new BufferedImage(width, height, bufferedImage.getType());
            Graphics g = newImage.getGraphics();
            g.drawImage(bufferedImage, 0, 0, width, height, null);
            g.dispose();
            ImageIO.write(newImage, suffix, new File(newPath));
        }
    }

    public static void main(String[] args) {
        ImageUtil imageUtil = new ImageUtil();
        File file = new File("/home/cxl/cxl/test-demo/test/src/main/resources/331077379.png");
        try {
            imageUtil.zoomImage(file,"1111.png",400,400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
