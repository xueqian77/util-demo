package com.study.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @program: java-pdf-demo
 * @description: pdf转utils
 * @author: 唐嘉
 * @create: 2020-07-08 09:16
 **/
public class PdfUtil {

    public static String pdf2Image(String PdfFilePath, String imgFolderPath, int dpi) {
        File file = new File(PdfFilePath);
        PDDocument pdDocument;
        try {
            int dot = file.getName().lastIndexOf('.');
            String imagePDFName = file.getName().substring(0, dot); // 获取图片文件名
            pdDocument = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(pdDocument);
            StringBuffer imgFilePath = null;
            int page = pdDocument.getNumberOfPages();
            for (int i = 1; i < page; i++) {
                String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName + File.separator + i;
                imgFilePath = new StringBuffer();
                imgFilePath.append(imgFilePathPrefix);
                imgFilePath.append(".png");
                File dstFile = new File(imgFilePath.toString());
                /* dpi越大转换后越清晰，相对转换速度越慢 */
                BufferedImage image = renderer.renderImageWithDPI(i, dpi);
                ImageIO.write(image, "png", dstFile);
            }
            System.out.println("PDF文档转PNG图片成功！");
            return imgFilePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
