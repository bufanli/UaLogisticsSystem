package com.rt.common.utils;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public class ImageKit {
    /**
     * 改变图片尺寸
     * @param srcFileName 源图片路径
     * @param tagFileName 目的图片路径
     * @param width 修改后的宽度
     * @param height 修改后的高度
     */
    public static void zoomImage(String srcFileName, String tagFileName, int width, int height,String suffixName) throws IOException {
            BufferedImage bi = ImageIO.read(new File(srcFileName));
            BufferedImage tag=new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
            tag.getGraphics().drawImage(bi, 0, 0, width, height, null);
            ImageIO.write(tag, suffixName, new File(tagFileName));//画图
    }


    /**
     * 图像切割(按指定起点坐标和宽高切割)
     * @param srcImageFile 源图像地址
     * @param result 切片后的图像地址
     * @param x 目标切片起点坐标X
     * @param y 目标切片起点坐标Y
     * @param width 目标切片宽度
     * @param height 目标切片高度
     * @param suffixName  目标文件后缀名 -- 图片类型
     */
    public static void cut(String srcImageFile, String result,
                    int x, int y, int width, int height,String suffixName) throws IOException {
            FileInputStream is = null;
            ImageInputStream iis = null;
            try {
                // 读取图片文件
                is = new FileInputStream(srcImageFile);
                /** * * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader * * 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 . * * (例如 "jpeg" 或 "tiff")等 。 */
                Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(suffixName);
                ImageReader reader = it.next();
                // 获取图片流
                iis = ImageIO.createImageInputStream(is);
                /** * * <p> * iis:读取源。true:只向前搜索 * </p> * .将它标记为 ‘只向前搜索'。 * * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader * * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。 */
                reader.setInput(iis, true);
                /** * * <p> * 描述如何对流进行解码的类 * <p> * .用于指定如何在输入时从 Java Image I/O * * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 * * 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回 * * ImageReadParam 的实例。 */
                ImageReadParam param = reader.getDefaultReadParam();
                /** * * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象 * * 的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域。 */
                Rectangle rect = new Rectangle(x, y, width, height);
                // 提供一个 BufferedImage，将其用作解码像素数据的目标。
                param.setSourceRegion(rect);
                /** * * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 * * 它作为一个完整的 BufferedImage 返回。 */
                BufferedImage bi = reader.read(0, param);
                // 保存新图片
                ImageIO.write(bi, suffixName, new File(result));
            } finally {
                if (is != null) is.close();
                if (iis != null) iis.close();
            }
    }


    /**
     * <p>Title: thumbnailImage</p>
     * <p>Description: 根据图片路径生成缩略图 </p>
     * @param imagePath    原图片路径
     * @param targetImgPath 缩略图路径
     * @param w            缩略图宽
     * @param h            缩略图高
     * @param prevfix    生成缩略图的前缀
     * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    public static void thumbnailImage(String imagePath, String targetImgPath,int w, int h, String prevfix, boolean force) throws IOException {
        File imgFile = new File(imagePath);
        // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
        String types = Arrays.toString(ImageIO.getReaderFormatNames());
        String suffix = null;
        // 获取图片后缀
        if(imgFile.getName().indexOf(".") > -1) {
            suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
        }// 类型和图片后缀全部小写，然后判断后缀是否合法
        if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0){
            return ;
        }
        Image img = ImageIO.read(imgFile);
        if(!force){
            // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
            int width = img.getWidth(null);
            int height = img.getHeight(null);
            if((width*1.0)/w < (height*1.0)/h){
                if(width > w){
                    h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
                }
            } else {
                if(height > h){
                    w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
                }
            }
        }
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
        g.dispose();
        String p = imgFile.getPath();
        // 将图片保存在原目录并加上前缀
        ImageIO.write(bi, suffix, new File(targetImgPath));
    }


}