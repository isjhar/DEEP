/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas_besar_uas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Isjhar-pc
 */
public class ImageProcessing {
    public BufferedImage getImage(File imagePath) throws IOException{
         BufferedImage image = ImageIO.read(imagePath);
         return image;
    }
    public Color getImageColor(BufferedImage img, int x, int y) throws IOException {
        //img.getRGB(x, y);
        Color c = new Color(img.getRGB(x, y));
        return c;
//        BufferedImage image = ImageIO.read(imagePath);
        //int color = image.getRGB(0, 0);
//        for (int r = 0; r < image.getHeight(); r += 1) {
//            for (int c = 0; c < image.getWidth(); c += 1) {
//                System.out.println(image.getRGB(c, r));
                
//                if (image.getRGB(c, r) != color) {
//                    throw new IllegalArgumentException("Image: " + imagePath + " is not a solid color.");
//                }
//            }
//        }

//        return new Color(color);
    }   
    
    public BufferedImage rotate90(BufferedImage image) throws IOException{
        BufferedImage imagebefore = image;
        BufferedImage imageafter = new BufferedImage(imagebefore.getHeight(), imagebefore.getWidth(), imagebefore.getType());
        int[] tempColor = new int[imagebefore.getWidth()];
        for (int r = 0; r < imagebefore.getHeight(); r += 1) {
            for (int c = 0; c < imagebefore.getWidth(); c += 1) {
                tempColor[c] = imagebefore.getRGB(c, r);
            }
            
            for(int y = 0; y < imageafter.getHeight(); y += 1) {
                imageafter.setRGB(imageafter.getWidth() - r - 1 , y, tempColor[y]);
            }
            
        }
//        File outputFile = new File("hasil.jpg");
//        ImageIO.write(imageafter, "jpg", outputFile);
        System.out.println("sukses");  
        return imageafter;
    }
    
    public BufferedImage rotate270(BufferedImage image) throws IOException{
        BufferedImage imagebefore = image;
        BufferedImage imageafter = new BufferedImage(imagebefore.getHeight(), imagebefore.getWidth(), imagebefore.getType());
        int[] tempColor = new int[imagebefore.getWidth()];
        for (int r = 0; r < imagebefore.getHeight(); r += 1) {
            for (int c = 0; c < imagebefore.getWidth(); c += 1) {
                tempColor[c] = imagebefore.getRGB(c, r);
            }
            
            for(int y = 0; y < imageafter.getHeight(); y += 1) {
                imageafter.setRGB(r , y, tempColor[imageafter.getHeight() - y - 1]);
            }
            
        }
//        File outputFile = new File("hasil.jpg");
//        ImageIO.write(imageafter, "jpg", outputFile);
        System.out.println("sukses");  
        return imageafter;
    }
    
    public BufferedImage zoomIn(BufferedImage image) throws IOException{
        BufferedImage imagebefore = image;
        BufferedImage imageafter = new BufferedImage(2*imagebefore.getWidth(), 2*imagebefore.getHeight(), imagebefore.getType());
        int countRow, countCol;
        countRow = 0;
        for (int r = 0; r < imagebefore.getHeight(); r += 1) {
            countCol = 0;
            for (int c = 0; c < imagebefore.getWidth(); c += 1) {
                for(int repeatCol = 0 ; repeatCol < 2; repeatCol++){
                    imageafter.setRGB(countCol, countRow, imagebefore.getRGB(c, r));
                    countCol++;
                }  
            }
            
            countCol = 0;
            countRow++;
            for (int c = 0; c < imagebefore.getWidth(); c += 1) {
                for(int repeatCol = 0 ; repeatCol < 2; repeatCol++){
                    imageafter.setRGB(countCol, countRow, imagebefore.getRGB(c, r));
                    countCol++;
                }  
            }
            countRow++;
        }
//        File outputFile = new File("hasil.jpg");
//        ImageIO.write(imageafter, "jpg", outputFile);
        System.out.println("sukses");  
        return imageafter;
    }
    
    public BufferedImage zoomOut(BufferedImage image) throws IOException{
        BufferedImage imagebefore = image;
        BufferedImage imageafterAdd = null;
        Color tempcolor = null;
        /*
         * proses pengubahan ukuran gambar, jika lebar gambar ganjil maka lebar baru + 1, dan jika panjang
         * gambar ganjil maka panjang gambar + 1
         */
        
        if(imagebefore.getWidth() % 2 == 1 && imagebefore.getHeight() % 2 == 1){
            imageafterAdd = new BufferedImage(imagebefore.getWidth()+1, imagebefore.getHeight()+1, imagebefore.getType());
            for(int i = 0;i < imagebefore.getHeight();i++){
                for(int j = 0;j < imagebefore.getWidth();j++){
                    imageafterAdd.setRGB(j, i, imagebefore.getRGB(j, i));
                }
                imageafterAdd.setRGB(imageafterAdd.getWidth()-1, i, imagebefore.getRGB(imagebefore.getWidth()-1, i));
            }
            for(int i = 0;i < imageafterAdd.getWidth();i++){
                imageafterAdd.setRGB(i, imageafterAdd.getHeight()-1, imageafterAdd.getRGB(i, imageafterAdd.getHeight()-2));
            }
        } else if(imagebefore.getWidth() % 2 == 1){
            imageafterAdd = new BufferedImage(imagebefore.getWidth()+1, imagebefore.getHeight(), imagebefore.getType());
            for(int i = 0;i < imagebefore.getHeight();i++){
                for(int j = 0;j < imagebefore.getWidth();j++){
                    imageafterAdd.setRGB(j, i, imagebefore.getRGB(j, i));
                }
                imageafterAdd.setRGB(imageafterAdd.getWidth()-1, i, imagebefore.getRGB(imagebefore.getWidth()-1, i));
            }
        } else if(imagebefore.getHeight() % 2 == 1){
            imageafterAdd = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight()+1, imagebefore.getType());
            for(int i = 0;i < imagebefore.getHeight();i++){
                for(int j = 0;j < imagebefore.getWidth();j++){
                    imageafterAdd.setRGB(j, i, imagebefore.getRGB(j, i));
                }
            }
            for(int i = 0;i < imageafterAdd.getWidth();i++){
                imageafterAdd.setRGB(i, imageafterAdd.getHeight()-1, imageafterAdd.getRGB(i, imageafterAdd.getHeight()-2));
            }
        } else {
            imageafterAdd = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
            for(int i = 0;i < imagebefore.getHeight();i++){
                for(int j = 0;j < imagebefore.getWidth();j++){
                    imageafterAdd.setRGB(j, i, imagebefore.getRGB(j, i));
                }
            }
        } 
        
        BufferedImage imageafter = new BufferedImage(imageafterAdd.getWidth()/2, imageafterAdd.getHeight()/2, imageafterAdd.getType());
        int countRow = 0, countCol, colMultiple = 0, rowMultiple = 0;
        int meanR = 0, meanG = 0, meanB = 0, meanA = 255;
        for(int i = 0;i < imageafter.getHeight();i++){
            countCol = colMultiple;
            for(int j = 0;j < imageafter.getWidth();j++){
                meanR = 0;
                meanG = 0;
                meanB = 0;
                for(int y = 0;y < 2;y++){
                    for(int x = 0;x < 2;x++){
                        tempcolor = new Color(imageafterAdd.getRGB(countCol, countRow));
                        meanR += tempcolor.getRed();
                        meanG += tempcolor.getGreen();
                        meanB += tempcolor.getBlue();
                        countCol++;
                    }
                    countRow++;
                    countCol = colMultiple;
                }
                tempcolor = new Color(meanR/4, meanG/4, meanB/4, meanA);
                countRow = rowMultiple;
                colMultiple += 2;
                countCol = colMultiple;
                imageafter.setRGB(j, i, tempcolor.getRGB());
                meanR = 0;
                meanG = 0;
                meanB = 0;
            }
            rowMultiple += 2;
            countRow = rowMultiple;
            colMultiple = 0;
        }
        return imageafter;
    }
    
    public BufferedImage smoothingMean3x3(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+2, imagebefore.getHeight()+2, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());;
        Color tempcolor = null;
        int meanR = 0, meanG = 0, meanB = 0, meanA = 255, multiple = 9;
        tempcolor = new Color(0, 0, 0, 255);
        for(int i = 0;i < imagetemp.getWidth();i++){
            imagetemp.setRGB(i, 0, tempcolor.getRGB());
            imagetemp.setRGB(i, imagetemp.getHeight()-1, tempcolor.getRGB());
        }
        
        for(int i = 0;i < imagetemp.getHeight();i++){
            imagetemp.setRGB(0, i, tempcolor.getRGB());
            imagetemp.setRGB(imagetemp.getWidth()-1, i, tempcolor.getRGB());
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+1, j+1, imagebefore.getRGB(i, j));
            }
        }
        
        for(int j = 1;j < imagetemp.getHeight()-1;j++){
            for(int i = 1;i < imagetemp.getWidth()-1;i++){
                meanR = 0;
                meanG = 0;
                meanB = 0;
                for(int y = j - 1;y < j + 2;y++){
                    for(int x = i - 1;x < i + 2;x++){
                        tempcolor = new Color(imagetemp.getRGB(x, y));
                        meanR += tempcolor.getRed();
                        meanG += tempcolor.getGreen();
                        meanB += tempcolor.getBlue();
                    }
                }
                tempcolor = new Color(meanR/multiple, meanG/multiple, meanB/multiple, meanA);
                imageafter.setRGB(i-1, j-1, tempcolor.getRGB());
            }
        }
        return imageafter;
    }
    
    public BufferedImage smoothingMean5x5(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+4, imagebefore.getHeight()+4, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());;
        Color tempcolor = null;
        int meanR = 0, meanG = 0, meanB = 0, meanA = 255, multiple = 25;
        tempcolor = new Color(0, 0, 0, 255);
        for(int j = 0;j < 2;j++){
            for(int i = 0;i < imagetemp.getWidth();i++){
                imagetemp.setRGB(i, j, tempcolor.getRGB());
                imagetemp.setRGB(i, imagetemp.getHeight() - 1 - j, tempcolor.getRGB());
            }
        }
        for(int j = 0;j < 2;j++){
            for(int i = 0;i < imagetemp.getHeight();i++){
                imagetemp.setRGB(j, i, tempcolor.getRGB());
                imagetemp.setRGB(imagetemp.getWidth() - 1 - j, i, tempcolor.getRGB());
            }
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+2, j+2, imagebefore.getRGB(i, j));
            }
        }
        
        for(int j = 2;j < imagetemp.getHeight()-2;j++){
            for(int i = 2;i < imagetemp.getWidth()-2;i++){
                meanR = 0;
                meanG = 0;
                meanB = 0;
                for(int y = j - 2;y < j + 3;y++){
                    for(int x = i - 2;x < i + 3;x++){
                        tempcolor = new Color(imagetemp.getRGB(x, y));
                        meanR += tempcolor.getRed();
                        meanG += tempcolor.getGreen();
                        meanB += tempcolor.getBlue();
                    }
                }
                tempcolor = new Color(meanR/multiple, meanG/multiple, meanB/multiple, meanA);
                imageafter.setRGB(i-2, j-2, tempcolor.getRGB());
            }
        }
        return imageafter;
    }
    
    public BufferedImage smoothingMean7x7(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+6, imagebefore.getHeight()+6, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());;
        Color tempcolor = null;
        int meanR = 0, meanG = 0, meanB = 0, meanA = 255, multiple = 49;
        tempcolor = new Color(0, 0, 0, 255);
        for(int j = 0;j < 3;j++){
            for(int i = 0;i < imagetemp.getWidth();i++){
                imagetemp.setRGB(i, j, tempcolor.getRGB());
                imagetemp.setRGB(i, imagetemp.getHeight() - 1 - j, tempcolor.getRGB());
            }
        }
        for(int j = 0;j < 3;j++){
            for(int i = 0;i < imagetemp.getHeight();i++){
                imagetemp.setRGB(j, i, tempcolor.getRGB());
                imagetemp.setRGB(imagetemp.getWidth() - 1 - j, i, tempcolor.getRGB());
            }
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+3, j+3, imagebefore.getRGB(i, j));
            }
        }
        
        for(int j = 3;j < imagetemp.getHeight()-3;j++){
            for(int i = 3;i < imagetemp.getWidth()-3;i++){
                meanR = 0;
                meanG = 0;
                meanB = 0;
                for(int y = j - 3;y < j + 4;y++){
                    for(int x = i - 3;x < i + 4;x++){
                        tempcolor = new Color(imagetemp.getRGB(x, y));
                        meanR += tempcolor.getRed();
                        meanG += tempcolor.getGreen();
                        meanB += tempcolor.getBlue();
                    }
                }
                tempcolor = new Color(meanR/multiple, meanG/multiple, meanB/multiple, meanA);
                imageafter.setRGB(i-3, j-3, tempcolor.getRGB());
            }
        }
        return imageafter;
    }
    
    public BufferedImage smoothingMedian3x3(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+2, imagebefore.getHeight()+2, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        LinkedList<Integer> listColor = new LinkedList<Integer>();
        Color tempcolor = null;
        int median = 0, min = 0, multiple = 9;
        tempcolor = new Color(0, 0, 0, 255);
        for(int i = 0;i < imagetemp.getWidth();i++){
            imagetemp.setRGB(i, 0, tempcolor.getRGB());
            imagetemp.setRGB(i, imagetemp.getHeight()-1, tempcolor.getRGB());
        }
        
        for(int i = 0;i < imagetemp.getHeight();i++){
            imagetemp.setRGB(0, i, tempcolor.getRGB());
            imagetemp.setRGB(imagetemp.getWidth()-1, i, tempcolor.getRGB());
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+1, j+1, imagebefore.getRGB(i, j));
            }
        }
        
        for(int j = 1;j < imagetemp.getHeight()-1;j++){
            for(int i = 1;i < imagetemp.getWidth()-1;i++){
                listColor.removeAll(listColor);
                for(int y = j - 1;y < j + 2;y++){
                    for(int x = i - 1;x < i + 2;x++){
                        if(listColor.isEmpty()){
                            listColor.addFirst(imagetemp.getRGB(x, y));
                        } else {
                            if(imagetemp.getRGB(x, y) <= listColor.getFirst()){
                                listColor.addFirst(imagetemp.getRGB(x, y));
                            } else if(imagetemp.getRGB(x, y) > listColor.getLast()){
                                listColor.addLast(imagetemp.getRGB(x, y));
                            } else {
                                int counter = 0;
                                while(counter < listColor.size()){
                                    if(imagetemp.getRGB(x, y) <= listColor.get(counter)){
                                        listColor.add(counter, imagetemp.getRGB(x, y));
                                        break;
                                    }
                                    counter++;
                                }
                            }
                        }
                    }
                }
                imageafter.setRGB(i-1, j-1, listColor.get((multiple/2)+1));
            }
        }
        return imageafter;
    }
    
    public BufferedImage smoothingMedian5x5(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+4, imagebefore.getHeight()+4, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        LinkedList<Integer> listColor = new LinkedList<Integer>();
        Color tempcolor = null;
        int median = 0, min = 0, multiple = 25;
        tempcolor = new Color(0, 0, 0, 255);
        for(int j = 0;j < 2;j++){
            for(int i = 0;i < imagetemp.getWidth();i++){
                imagetemp.setRGB(i, j, tempcolor.getRGB());
                imagetemp.setRGB(i, imagetemp.getHeight() - 1 - j, tempcolor.getRGB());
            }
        }
        for(int j = 0;j < 2;j++){
            for(int i = 0;i < imagetemp.getHeight();i++){
                imagetemp.setRGB(j, i, tempcolor.getRGB());
                imagetemp.setRGB(imagetemp.getWidth() - 1 - j, i, tempcolor.getRGB());
            }
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+2, j+2, imagebefore.getRGB(i, j));
            }
        }
        
        for(int j = 2;j < imagetemp.getHeight()-2;j++){
            for(int i = 2;i < imagetemp.getWidth()-2;i++){
                listColor.removeAll(listColor);
                for(int y = j - 2;y < j + 3;y++){
                    for(int x = i - 2;x < i + 3;x++){
                        if(listColor.isEmpty()){
                            listColor.addFirst(imagetemp.getRGB(x, y));
                        } else {
                            if(imagetemp.getRGB(x, y) <= listColor.getFirst()){
                                listColor.addFirst(imagetemp.getRGB(x, y));
                            } else if(imagetemp.getRGB(x, y) > listColor.getLast()){
                                listColor.addLast(imagetemp.getRGB(x, y));
                            } else {
                                int counter = 0;
                                while(counter < listColor.size()){
                                    if(imagetemp.getRGB(x, y) <= listColor.get(counter)){
                                        listColor.add(counter, imagetemp.getRGB(x, y));
                                        break;
                                    }
                                    counter++;
                                }
                            }
                        }
                    }
                }
                imageafter.setRGB(i-2, j-2, listColor.get((multiple/2)+1));
            }
        }
        return imageafter;
    }
    
    public BufferedImage smoothingMedian7x7(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+6, imagebefore.getHeight()+6, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        LinkedList<Integer> listColor = new LinkedList<Integer>();
        Color tempcolor = null;
        int median = 0, min = 0, multiple = 49;
        tempcolor = new Color(0, 0, 0, 255);
        for(int j = 0;j < 3;j++){
            for(int i = 0;i < imagetemp.getWidth();i++){
                imagetemp.setRGB(i, j, tempcolor.getRGB());
                imagetemp.setRGB(i, imagetemp.getHeight() - 1 - j, tempcolor.getRGB());
            }
        }
        for(int j = 0;j < 3;j++){
            for(int i = 0;i < imagetemp.getHeight();i++){
                imagetemp.setRGB(j, i, tempcolor.getRGB());
                imagetemp.setRGB(imagetemp.getWidth() - 1 - j, i, tempcolor.getRGB());
            }
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+3, j+3, imagebefore.getRGB(i, j));
            }
        }
        
        for(int j = 3;j < imagetemp.getHeight()-3;j++){
            for(int i = 3;i < imagetemp.getWidth()-3;i++){
                listColor.removeAll(listColor);
                for(int y = j - 3;y < j + 4;y++){
                    for(int x = i - 3;x < i + 4;x++){
                        if(listColor.isEmpty()){
                            listColor.addFirst(imagetemp.getRGB(x, y));
                        } else {
                            if(imagetemp.getRGB(x, y) <= listColor.getFirst()){
                                listColor.addFirst(imagetemp.getRGB(x, y));
                            } else if(imagetemp.getRGB(x, y) > listColor.getLast()){
                                listColor.addLast(imagetemp.getRGB(x, y));
                            } else {
                                int counter = 0;
                                while(counter < listColor.size()){
                                    if(imagetemp.getRGB(x, y) <= listColor.get(counter)){
                                        listColor.add(counter, imagetemp.getRGB(x, y));
                                        break;
                                    }
                                    counter++;
                                }
                            }
                        }
                    }
                }
                imageafter.setRGB(i-3, j-3, listColor.get((multiple/2)+1));
            }
        }
        return imageafter;
    }
    
    public BufferedImage smoothingModus3x3(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+2, imagebefore.getHeight()+2, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        LinkedList<Warna> listColor = new LinkedList<Warna>();
        Color tempcolor = null;
        int median = 0, min = 0, multiple = 9;
        tempcolor = new Color(0, 0, 0, 255);
        for(int i = 0;i < imagetemp.getWidth();i++){
            imagetemp.setRGB(i, 0, tempcolor.getRGB());
            imagetemp.setRGB(i, imagetemp.getHeight()-1, tempcolor.getRGB());
        }
        
        for(int i = 0;i < imagetemp.getHeight();i++){
            imagetemp.setRGB(0, i, tempcolor.getRGB());
            imagetemp.setRGB(imagetemp.getWidth()-1, i, tempcolor.getRGB());
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+1, j+1, imagebefore.getRGB(i, j));
            }
        }
        
        
        boolean isContaint;
        for(int j = 1;j < imagetemp.getHeight()-1;j++){
            for(int i = 1;i < imagetemp.getWidth()-1;i++){
                listColor.removeAll(listColor);
                for(int y = j - 1;y < j + 2;y++){
                    for(int x = i - 1;x < i + 2;x++){
                        isContaint = false;
                        for(int a = 0;a < listColor.size();a++){
                            if(listColor.get(a).getRGB() == imagetemp.getRGB(x,y)){
                                listColor.get(a).addCount();
                                isContaint = true;
                                break;
                            }
                        }
                        if(!isContaint){
                            listColor.addLast(new Warna(imagetemp.getRGB(x,y)));
                        }
                    }
                }
                int maxCount = 1;
                int maxIndex = 0;
                for(int a = 0;a < listColor.size();a++){
                    if(maxCount < listColor.get(a).getCount()){
                        maxCount = listColor.get(a).getCount();
                        maxIndex = a;
                    }
                }
                imageafter.setRGB(i-1, j-1, listColor.get(maxIndex).getRGB());
            }
        }
        return imageafter;
    }
    
    public BufferedImage smoothingModus5x5(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+2, imagebefore.getHeight()+2, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        LinkedList<Warna> listColor = new LinkedList<Warna>();
        Color tempcolor = null;
        int median = 0, min = 0, multiple = 25;
        tempcolor = new Color(0, 0, 0, 255);
        for(int j = 0;j < 2;j++){
            for(int i = 0;i < imagetemp.getWidth();i++){
                imagetemp.setRGB(i, j, tempcolor.getRGB());
                imagetemp.setRGB(i, imagetemp.getHeight() - 1 - j, tempcolor.getRGB());
            }
        }
        for(int j = 0;j < 2;j++){
            for(int i = 0;i < imagetemp.getHeight();i++){
                imagetemp.setRGB(j, i, tempcolor.getRGB());
                imagetemp.setRGB(imagetemp.getWidth() - 1 - j, i, tempcolor.getRGB());
            }
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+2, j+2, imagebefore.getRGB(i, j));
            }
        }
        
        
        boolean isContaint;
        for(int j = 2;j < imagetemp.getHeight()-2;j++){
            for(int i = 2;i < imagetemp.getWidth()-2;i++){
                listColor.removeAll(listColor);
                for(int y = j - 2;y < j + 3;y++){
                    for(int x = i - 1;x < i + 3;x++){
                        isContaint = false;
                        for(int a = 0;a < listColor.size();a++){
                            if(listColor.get(a).getRGB() == imagetemp.getRGB(x,y)){
                                listColor.get(a).addCount();
                                isContaint = true;
                                break;
                            }
                        }
                        if(!isContaint){
                            listColor.addLast(new Warna(imagetemp.getRGB(x,y)));
                        }
                    }
                }
                int maxCount = 1;
                int maxIndex = 0;
                for(int a = 0;a < listColor.size();a++){
                    if(maxCount < listColor.get(a).getCount()){
                        maxCount = listColor.get(a).getCount();
                        maxIndex = a;
                    }
                }
                imageafter.setRGB(i-2, j-2, listColor.get(maxIndex).getRGB());
            }
        }
        return imageafter;
    }
    
    public BufferedImage smoothingModus7x7(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+6, imagebefore.getHeight()+6, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        LinkedList<Warna> listColor = new LinkedList<Warna>();
        Color tempcolor = null;
        int median = 0, min = 0, multiple = 49;
        tempcolor = new Color(0, 0, 0, 255);
        for(int j = 0;j < 3;j++){
            for(int i = 0;i < imagetemp.getWidth();i++){
                imagetemp.setRGB(i, j, tempcolor.getRGB());
                imagetemp.setRGB(i, imagetemp.getHeight() - 1 - j, tempcolor.getRGB());
            }
        }
        for(int j = 0;j < 3;j++){
            for(int i = 0;i < imagetemp.getHeight();i++){
                imagetemp.setRGB(j, i, tempcolor.getRGB());
                imagetemp.setRGB(imagetemp.getWidth() - 1 - j, i, tempcolor.getRGB());
            }
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+3, j+3, imagebefore.getRGB(i, j));
            }
        }
        
        
        boolean isContaint;
        for(int j = 3;j < imagetemp.getHeight()-3;j++){
            for(int i = 3;i < imagetemp.getWidth()-3;i++){
                listColor.removeAll(listColor);
                for(int y = j - 3;y < j + 4;y++){
                    for(int x = i - 3;x < i + 4;x++){
                        isContaint = false;
                        for(int a = 0;a < listColor.size();a++){
                            if(listColor.get(a).getRGB() == imagetemp.getRGB(x,y)){
                                listColor.get(a).addCount();
                                isContaint = true;
                                break;
                            }
                        }
                        if(!isContaint){
                            listColor.addLast(new Warna(imagetemp.getRGB(x,y)));
                        }
                    }
                }
                int maxCount = 1;
                int maxIndex = 0;
                for(int a = 0;a < listColor.size();a++){
                    if(maxCount < listColor.get(a).getCount()){
                        maxCount = listColor.get(a).getCount();
                        maxIndex = a;
                    }
                }
                imageafter.setRGB(i-3, j-3, listColor.get(maxIndex).getRGB());
            }
        }
        return imageafter;
    }
    
    private class Warna{
        private int rgb;
        private int count;
        
        public Warna(int rgb){
            this.rgb = rgb;
            this.count = 1;
        }
        
        public void addCount(){
            count++;
        }
        
        public int getRGB(){
            return this.rgb;
        }
        
        public int getCount(){
            return this.count;
        }
    }
    
    public BufferedImage flipHorizontal(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dimg = new BufferedImage( w,h, image.getType());
        Graphics2D g = dimg.createGraphics();
        g.drawImage(image, 0, 0, w, h, w, 0, 0, h,null);
	g.dispose();
        return dimg;
    }
    
    public BufferedImage flipVertical(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dimg = new BufferedImage(w, h, image.getColorModel().getTransparency());
        Graphics2D g = dimg.createGraphics();
        g.drawImage(image, 0, 0, w, h, 0, h, w, 0,null);
        return dimg;
    }
    
    public BufferedImage rgbToGrayscale(BufferedImage image) throws IOException{
        BufferedImage imagebefore = image;
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        Color tempColor = null;
        int tempAlpha, tempRed, tempGreen, tempBlue, value;
        
        for(int j = 0;j < imageafter.getHeight();j++){
            for(int i = 0;i < imageafter.getWidth();i++){
                tempColor = new Color(imagebefore.getRGB(i, j));
                tempAlpha = tempColor.getAlpha();
                tempRed = tempColor.getRed();
                tempGreen = tempColor.getGreen();
                tempBlue = tempColor.getBlue();
                value = (tempRed + tempGreen + tempBlue) / 3;
                tempRed = tempGreen = tempBlue = value;
                
                tempColor = new Color(tempRed, tempGreen, tempBlue, tempAlpha);
                imageafter.setRGB(i, j, tempColor.getRGB());
            }
        }
        
        return imageafter;
    }
    
    public BufferedImage rgbToNegative(BufferedImage image) throws IOException{
        BufferedImage imageafter = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        
        int alpha=0, reds=0, greens=0, blues=0, imgRGB, newRGB;
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                imgRGB = image.getRGB(x, y);
                //filter out the argb values

                alpha = (imgRGB >> 24) & 0xff;
                reds = (imgRGB >> 16) & 0xff;
                greens = (imgRGB >> 8) & 0xff;
                blues = (imgRGB) & 0xff;
                //Apply the filter    
                
                reds = 255 - reds;
                greens = 255 -greens;
                blues = 255 - blues;
                newRGB = (alpha << 24) + (reds << 16) + (greens << 8) + blues;
                //assign the current pixel to the new aRGB value
                imageafter.setRGB(x, y, newRGB);
            }
        }   
        return imageafter;
    }
    
    public BufferedImage rgbToBW(BufferedImage image, int threshold) throws IOException{
        BufferedImage imagebefore = image;
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        Color tempColor = null;
        int tempAlpha, tempRed, tempGreen, tempBlue, value;
        
        for(int j = 0;j < imageafter.getHeight();j++){
            for(int i = 0;i < imageafter.getWidth();i++){
                tempColor = new Color(imagebefore.getRGB(i, j));
                tempAlpha = tempColor.getAlpha();
                tempRed = tempColor.getRed();
                tempGreen = tempColor.getGreen();
                tempBlue = tempColor.getBlue();
                value = (tempRed + tempGreen + tempBlue) / 3;
                if(value <= threshold){
                    tempRed = tempGreen = tempBlue = 0;
                } else {
                    tempRed = tempGreen = tempBlue = 255;
                }
                tempColor = new Color(tempRed, tempGreen, tempBlue, tempAlpha);
                imageafter.setRGB(i, j, tempColor.getRGB());
            }
        }
        
        return imageafter;
    }
    
    public BufferedImage transformFourier(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+2, imagebefore.getHeight()+2, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());;
        Color tempcolor = null;
        int meanR = 0, meanG = 0, meanB = 0, meanA = 255, multiple = 9;
        tempcolor = new Color(0, 0, 0, 255);
        for(int i = 0;i < imagetemp.getWidth();i++){
            imagetemp.setRGB(i, 0, tempcolor.getRGB());
            imagetemp.setRGB(i, imagetemp.getHeight()-1, tempcolor.getRGB());
        }
        
        for(int i = 0;i < imagetemp.getHeight();i++){
            imagetemp.setRGB(0, i, tempcolor.getRGB());
            imagetemp.setRGB(imagetemp.getWidth()-1, i, tempcolor.getRGB());
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+1, j+1, imagebefore.getRGB(i, j));
            }
        }
        
        for(int j = 1;j < imagetemp.getHeight()-1;j++){
            for(int i = 1;i < imagetemp.getWidth()-1;i++){
                meanR = 0;
                meanG = 0;
                meanB = 0;
                for(int y = j - 1;y < j + 2;y++){
                    for(int x = i - 1;x < i + 2;x++){
                        tempcolor = new Color(imagetemp.getRGB(x, y));
//                        meanR += tempcolor.getRed() * Math.pow(Math.exp(1),-2*Math.PI*());
                        meanG += tempcolor.getGreen();
                        meanB += tempcolor.getBlue();
                    }
                }
                tempcolor = new Color(meanR/multiple, meanG/multiple, meanB/multiple, meanA);
                imageafter.setRGB(i-1, j-1, tempcolor.getRGB());
            }
        }
        return imageafter;
    }
    
    public BufferedImage sharpeningLaplacian(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+2, imagebefore.getHeight()+2, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        Color tempcolor = null;
        int meanR = 0, meanG = 0, meanB = 0, meanA = 255, multiple = 9;
        long minR, minG, minB, maxR, maxG, maxB;
        int[] kernel = {-1,-1,-1,-1,8,-1,-1,-1,-1};
        int countKernel = 0;
        tempcolor = new Color(0, 0, 0, 255);
        double R, G, B, t, R2, G2, B2;
        MyColor[][] tempfilter = new MyColor[imagebefore.getWidth()][imagebefore.getHeight()];
        
        int c = 0;
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                c++;
                tempcolor = new Color(imagebefore.getRGB(i, j));
//                System.out.println(c + ". (R=" + tempcolor.getRed() + ",G=" + tempcolor.getGreen() + ",B=" + tempcolor.getBlue() + ") ");
                
            }
        }
//        System.out.println("");
        for(int i = 0;i < imagetemp.getWidth();i++){
            imagetemp.setRGB(i, 0, tempcolor.getRGB());
            imagetemp.setRGB(i, imagetemp.getHeight()-1, tempcolor.getRGB());
        }
        
        for(int i = 0;i < imagetemp.getHeight();i++){
            imagetemp.setRGB(0, i, tempcolor.getRGB());
            imagetemp.setRGB(imagetemp.getWidth()-1, i, tempcolor.getRGB());
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+1, j+1, imagebefore.getRGB(i, j));
            }
        }
        
        minR = 0;
        minG = 0;
        minB = 0;
        for(int j = 1;j < imagetemp.getHeight()-1;j++){
            for(int i = 1;i < imagetemp.getWidth()-1;i++){
                meanR = 0;
                meanG = 0;
                meanB = 0;
                countKernel = 0;
                for(int y = j - 1;y < j + 2;y++){
                    for(int x = i - 1;x < i + 2;x++){
                        tempcolor = new Color(imagetemp.getRGB(x, y));
                        meanR = meanR + (tempcolor.getRed() * kernel[countKernel]);
                        meanG = meanG + (tempcolor.getGreen() * kernel[countKernel]);
                        meanB = meanB + (tempcolor.getBlue() * kernel[countKernel]);
                        countKernel++;
                    }
                }
                
                tempfilter[i-1][j-1] = new MyColor(meanR, meanG, meanB);
                
                if(meanR < minR){
                    minR = meanR;
                }
                
                if(meanG < minG){
                    minG = meanG;
                }
                
                if(meanB < minB){
                    minB = meanB;
                }
            }
        }
        
        maxR = 0;
        maxG = 0;
        maxB = 0;
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                tempfilter[i][j].setR(tempfilter[i][j].getR() - minR);
                tempfilter[i][j].setG(tempfilter[i][j].getG() - minG);
                tempfilter[i][j].setB(tempfilter[i][j].getB() - minB);
                if(tempfilter[i][j].getR() > maxR){
                    maxR = tempfilter[i][j].getR();
                }
                
                if(tempfilter[i][j].getG() > maxG){
                    maxG = tempfilter[i][j].getG();
                }
                
                if(tempfilter[i][j].getB() > maxB){
                    maxB = tempfilter[i][j].getB();
                }
                
            }
        } 
        
        R = 255.0 / maxR;
        G = 255.0 / maxG;
        B = 255.0 / maxB;
//        System.out.println("(R=" + R + ",G=" + G + ",B=" + B + ") \n");
        for(int j = 0;j < imageafter.getHeight();j++){
            for(int i = 0;i < imageafter.getWidth();i++){
                tempfilter[i][j].setR(Math.round(tempfilter[i][j].getR() * R));
                tempfilter[i][j].setG(Math.round(tempfilter[i][j].getG() * G));
                tempfilter[i][j].setB(Math.round(tempfilter[i][j].getB() * B));
//                tempcolor = new Color((int)tempfilter[i][j].getR(), (int)tempfilter[i][j].getG(), (int)tempfilter[i][j].getB(), meanA);
//                imageafter.setRGB(i, j, tempcolor.getRGB());
            }
        }
        
        minR = 0;
        minG = 0;
        minB = 0;
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                tempcolor = new Color(imagebefore.getRGB(i, j));
                tempfilter[i][j].setR(tempfilter[i][j].getR() + tempcolor.getRed());
                tempfilter[i][j].setG(tempfilter[i][j].getG() + tempcolor.getGreen());
                tempfilter[i][j].setB(tempfilter[i][j].getB() + tempcolor.getBlue());
                if(tempfilter[i][j].getR() < minR){
                    minR = tempfilter[i][j].getR();
                }
                
                if(tempfilter[i][j].getG() < minG){
                    minG = tempfilter[i][j].getG();
                }
                
                if(tempfilter[i][j].getB() < minB){
                    minB = tempfilter[i][j].getB();
                }
            }
        } 
        
        maxR = 0;
        maxG = 0;
        maxB = 0;
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                tempfilter[i][j].setR(tempfilter[i][j].getR() - minR);
                tempfilter[i][j].setG(tempfilter[i][j].getG() - minG);
                tempfilter[i][j].setB(tempfilter[i][j].getB() - minB);
                if(tempfilter[i][j].getR() > maxR){
                    maxR = tempfilter[i][j].getR();
                }
                
                if(tempfilter[i][j].getG() > maxG){
                    maxG = tempfilter[i][j].getG();
                }
                
                if(tempfilter[i][j].getB() > maxB){
                    maxB = tempfilter[i][j].getB();
                }
                
            }
        } 
        
        R = 255.0 / maxR;
        G = 255.0 / maxG;
        B = 255.0 / maxB;
        t = 140.0 / 255.0;
//        System.out.println("(R=" + R + ",G=" + G + ",B=" + B + ") \n");
        for(int j = 0;j < imageafter.getHeight();j++){
            for(int i = 0;i < imageafter.getWidth();i++){
                tempfilter[i][j].setR(Math.round(tempfilter[i][j].getR() * R));
                tempfilter[i][j].setG(Math.round(tempfilter[i][j].getG() * G));
                tempfilter[i][j].setB(Math.round(tempfilter[i][j].getB() * B));
                
                
//                R2 = ((tempfilter[i][j].getR() - 0)*(t)) + 60;
//                G2 = ((tempfilter[i][j].getG() - 0)*(t)) + 60;
//                B2 = ((tempfilter[i][j].getB() - 0)*(t)) + 60;
//                
//                tempfilter[i][j].setR(Math.round(R2));
//                tempfilter[i][j].setG(Math.round(G2));
//                tempfilter[i][j].setB(Math.round(B2));
    
                tempcolor = new Color((int)tempfilter[i][j].getR(), (int)tempfilter[i][j].getG(), (int)tempfilter[i][j].getB(), meanA);
                imageafter.setRGB(i, j, tempcolor.getRGB());
            }
        }
        
        
        System.out.println("fiinish");
//        c = 0;
//        for(int j = 0;j < tempfilter[0].length;j++){
//            for(int i = 0;i < tempfilter.length;i++){
//                c++;
//                System.out.println(c + ". (R=" + tempfilter[i][j].getR() + ",G=" + tempfilter[i][j].getG() + ",B=" + tempfilter[i][j].getB() + ") ");
//                
//            }
//        }
        return imageafter;
    }
    
    public BufferedImage huetoRGB(BufferedImage image, int h, int s, int i){
        BufferedImage imagebefore = image;
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        double r ,g ,b, I, S, H, min, z, R, G, B;
        Color tempcolor = new Color(0, 0, 0, 255);
        H = h;
        I = i;
        for(int y = 0;y < imagebefore.getHeight();y++){
            for(int x = 0;x < imagebefore.getWidth();x++){
                tempcolor = new Color(imagebefore.getRGB(x, y));
//                System.out.println("after r="+tempcolor.getRed()+",g="+tempcolor.getGreen()+",b="+tempcolor.getBlue());
                R = convert0to1(tempcolor.getRed());
                G = convert0to1(tempcolor.getBlue());
                B = convert0to1(tempcolor.getGreen());
//                System.out.print("r="+R+",g="+G+",b="+B+",");
                z = R + G + B;
//                
//                r = R / z;
//                g = G / z;
//                b = B / z;
//                

              
                //calculate H
//                if(R == G && G == B){
//                    H = 90.0;
//                } else {
//                    H = Math.toDegrees(Math.acos((0.5 * ((R - G) + (R - B))) / Math.sqrt(((R - G) * (R - G)) + Math.abs((R - B) * (G - B)))));
//                }
//                
                //calculate S
                min = R;
                if(min > G){
                    min = G;
                }
                
                if(min > B){
                    min = B;
                }
                
                if(R == 0 && G == 0 && B ==0){
                    S = 1;
                } else {
                    S = 1 - ((3 / (z)) * min);
                }
                S = S * convert0to100(s);
                
///               calculate I
//                I = (z) / 3.0;
//                H = h;
//                System.out.println(H);
                if(H >= 0 && H < 120){
                    r = (1.0 / 3.0) * (1 + ((S * Math.cos(Math.toRadians(H))) / Math.cos(Math.toRadians(60 - H))));
                    b = (1.0 / 3.0) * (1 - S);
                    g = 1 - (r + b);
                } else if(H >= 120 && H < 240) {
                    H = H - 120;
                    g = (1.0 / 3.0) * (1 + ((S * Math.cos(Math.toRadians(H))) / Math.cos(Math.toRadians(60 - H))));
                    r = (1.0 / 3.0) * (1 - S);
                    b = 1 - (r + g);
                    
                } else {
                    H = H - 240;
                    b = (1.0 / 3.0) * (1 + ((S * Math.cos(Math.toRadians(H))) / Math.cos(Math.toRadians(60 - H))));
                    g = (1.0 / 3.0) * (1 - S);
                    r = 1 - (g + b);
                }
//                
               
                r = r * z * convert50to150(i + 50);
                g = g * z * convert50to150(i + 50);
                b = b * z * convert50to150(i + 50);
                r = convert0to255(r);
                g = convert0to255(g);
                b = convert0to255(b);
//                
                
                if(g > 255.0){
                    g = 255.0;
                } else if(g < 0){
                    g = 0;
                }
                if(r > 255.0){
                    r = 255.0;
                } else if(r < 0){
                    r = 0;
                }
                 
                if(b > 255.0){
                    b = 255.0;
                } else if(b < 0){
                    b = 0;
                }
//                System.out.println("r="+r+",g="+g+",b="+b);
                tempcolor = new Color((int)Math.round(r), (int)Math.round(g), (int)Math.round(b), 255);
//                System.out.println("r="+(int)Math.round(r)+",g="+(int)Math.round(g)+",b="+(int)Math.round(b));
//                System.out.println("");
                imageafter.setRGB(x, y, tempcolor.getRGB());
//                I = ()
//                System.out.println("H="+H+",S="+S+",I="+I);
                
                
            }
        }
        return imageafter;
    }
    

    
    public BufferedImage colorSlicing(BufferedImage image, int r, int g, int b){
        BufferedImage imagebefore = image;
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        int toleransi = 50;
        Color tempcolor = null;
        for(int y = 0;y < imagebefore.getHeight();y++){
            for(int x = 0;x < imageafter.getWidth();x++){
                tempcolor = new Color(imagebefore.getRGB(x, y));
                if(Math.abs((double)tempcolor.getRed() - r) > toleransi || Math.abs((double)tempcolor.getGreen() - g) > toleransi || Math.abs((double)tempcolor.getBlue() - b) > toleransi){
                    tempcolor = new Color(127, 127, 127, 255);
                }
                imageafter.setRGB(x, y, tempcolor.getRGB());
            }
        }
        return imageafter;
    }
    public BufferedImage gaussian(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        Color tempColor = null;
        Color tempColor2 = null;
        java.util.Random rand = new java.util.Random();
        for (int r = 0; r < imagebefore.getHeight(); r += 1) {
            for (int c = 0; c < imagebefore.getWidth(); c += 1) {
                tempColor = new Color(imagebefore.getRGB(c, r));
               // System.out.println(p);
                double noise = rand.nextGaussian() * Math.sqrt(100) + 0;
                int n=(int)Math.round(noise);
//                System.out.println(n);
                int red=tempColor.getRed()+n;
                int blue=tempColor.getBlue()+n;
                int green=tempColor.getGreen()+n;
                if(red<0){
                    red=0;
                }else if(red>255)
                {
                    red=255;
                }
                if(green<0){
                    green=0;
                }else if(green>255){
                    green=255;
                }
                if(blue<0){
                    blue=0;
                }else if(blue>255){
                    blue=255;
                }
                tempColor2 = new Color(red,green,blue);
                imageafter.setRGB(c, r, tempColor2.getRGB());
                //      i++;
            }
        }
//        System.out.println("sukses");
        return imageafter;
    }
    public BufferedImage saltPaper(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        Color tempColor = null;
        Color tempColor2 = null;
        for (int r = 0; r < imagebefore.getHeight(); r += 1) {
            for (int c = 0; c < imagebefore.getWidth(); c += 1) {
                tempColor = new Color(imagebefore.getRGB(c, r));
                tempColor.getRed();   
                double p=new Random().nextFloat() * (1.0);
//                System.out.println(p);
                if (p<=0.1){
                    tempColor2 = new Color(0, 0, 0);
                    imageafter.setRGB(c, r, tempColor2.getRGB());
                }else if(p>=0.9){
                    tempColor2 = new Color(255, 255, 255);
                    imageafter.setRGB(c, r, tempColor2.getRGB());
                }else{
                    imageafter.setRGB(c, r, imagebefore.getRGB(c,r));
                }
            }
        }
        System.out.println("sukses");  
        return imageafter;
    }
    public BufferedImage segmentation(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        Color snowColor = new Color(255, 255, 255);
        Color seaColor = new Color(0, 6, 20);
        Color desertColor = new Color(201, 184, 140);
        Color forestColor = new Color(20, 37, 5);
        Color redColor=new Color(255,0,0);
        Color yellowColor=new Color(255,255,0);
        Color orangeColor=new Color(255,128,0);
        Color newColor=new Color(0,128,128);
        Color temp = null;
        int snow = 255;
        int desert = 172;
        int sea = 9;
        int forest = 20;
        int red=82;
        int orange=124;
        int yellow=42;
        int newc=100;
        int avg = 0;
        int min = 0;
        for (int r = 0; r < imagebefore.getHeight(); r += 1) {
            for (int c = 0; c < imagebefore.getWidth(); c += 1) {
                temp = new Color(imagebefore.getRGB(c, r));
                avg = (temp.getRed() + temp.getBlue() + temp.getGreen()) / 3;
                min = Math.abs(avg - snow);
                temp = snowColor;
                if(Math.abs(avg - desert) < min){
                    min = Math.abs(avg - desert);
                    temp = desertColor;
                }
                if (Math.abs(avg - sea) < min){
                    min = Math.abs(avg - sea);
                    temp = seaColor;
                }
                if (Math.abs(avg - forest) < min){
                    min = Math.abs(avg - forest);
                    temp = forestColor;
                }            
                if(Math.abs(avg - red) < min){
                    min = Math.abs(avg - red);
                    temp = redColor;
                }
                if (Math.abs(avg - orange) < min){
                    min = Math.abs(avg - orange);
                    temp = orangeColor;
                }
                if (Math.abs(avg - yellow) < min){
                    min = Math.abs(avg - yellow);
                    temp = yellowColor;
                }
                if (Math.abs(avg - newc) < min){
                    min = Math.abs(avg - newc);
                    temp = newColor;
                }
                imageafter.setRGB(c, r, temp.getRGB());
            }
        }
            return imageafter;
    }
    
    public BufferedImage edgeDetection(BufferedImage image){
        BufferedImage imagebefore = image;
        BufferedImage imagetemp = new BufferedImage(imagebefore.getWidth()+2, imagebefore.getHeight()+2, imagebefore.getType());
        BufferedImage imageafter = new BufferedImage(imagebefore.getWidth(), imagebefore.getHeight(), imagebefore.getType());
        Color tempcolor = null;
        int meanR = 0, meanG = 0, meanB = 0, meanA = 255, multiple = 9;
        long minR, minG, minB, maxR, maxG, maxB;
        int[] kernelx = {-1, 0, 1, -2, 0, 2, -1, 0,1};
        int[] kernely = {1, 2, 1, 0, 0, 0, -1, -2, -1};
        int countKernel = 0, gxred, gxgreen, gxblue, gyred, gygreen, gyblue;
        tempcolor = new Color(0, 0, 0, 255);
        Color temp[] = new Color[9];
        double R, G, B, t, R2, G2, B2, gxyred, gxyblue, gxygreen;
        MyColor[][] tempfilter = new MyColor[imagebefore.getWidth()][imagebefore.getHeight()];
        
 
//        System.out.println("");
        for(int i = 0;i < imagetemp.getWidth();i++){
            imagetemp.setRGB(i, 0, tempcolor.getRGB());
            imagetemp.setRGB(i, imagetemp.getHeight()-1, tempcolor.getRGB());
        }
        
        for(int i = 0;i < imagetemp.getHeight();i++){
            imagetemp.setRGB(0, i, tempcolor.getRGB());
            imagetemp.setRGB(imagetemp.getWidth()-1, i, tempcolor.getRGB());
        }
        
        for(int j = 0;j < imagebefore.getHeight();j++){
            for(int i = 0;i < imagebefore.getWidth();i++){
                imagetemp.setRGB(i+1, j+1, imagebefore.getRGB(i, j));
            }
        }
        
        /*
         * 0 1 2
         * 3 4 5
         * 6 7 8
         */
        int c;
        for(int j = 1;j < imagetemp.getHeight() - 1;j++){
            for(int i = 1;i < imagetemp.getWidth() - 1;i++){
                c = 0;
                tempcolor = new Color(imagetemp.getRGB(i, j));
                for(int y = 0;y < 3;y++){
                    for(int x = 0;x < 3;x++){
                        temp[c] = new Color(imagetemp.getRGB(i - 1 + x , j - 1 + y));
                        c++;
                    }
                }
                gxred = temp[2].getRed() + (2*temp[5].getRed()) + temp[8].getRed() - (temp[0].getRed() + (2*temp[3].getRed()) + temp[6].getRed());
                gxgreen = temp[2].getGreen() + (2*temp[5].getGreen()) + temp[8].getGreen() - (temp[0].getGreen() + (2*temp[3].getGreen()) + temp[6].getGreen());
                gxblue = temp[2].getBlue()+ (2*temp[5].getBlue())+ temp[8].getBlue() - (temp[0].getBlue()+ (2*temp[3].getBlue())+ temp[6].getBlue());
                
                
                gyred = temp[6].getRed() + (2*temp[7].getRed()) + temp[8].getRed() - (temp[0].getRed() + (2*temp[1].getRed()) + temp[2].getRed());
                gygreen = temp[6].getGreen() + (2*temp[7].getGreen()) + temp[8].getGreen() - (temp[0].getGreen() + (2*temp[1].getGreen()) + temp[2].getGreen());
                gyblue = temp[6].getBlue()+ (2*temp[7].getBlue())+ temp[8].getBlue()- (temp[0].getBlue()+ (2*temp[1].getBlue())+ temp[2].getBlue());
                
                gxyred = Math.sqrt((gxred * gxred) + (gyred * gyred));
                gxygreen = Math.sqrt((gxgreen * gxgreen) + (gygreen * gygreen));
                gxyblue = Math.sqrt((gxblue * gxblue) + (gyblue * gyblue));
                
                if(gxyred > 255){
                    gxyred = 255;
                }
                
                if(gxygreen > 255){
                    gxygreen = 255;
                }
                
                if(gxyblue > 255){
                    gxyblue = 255;
                }
//                System.out.println("r="+tempcolor.getRed()+"g="+tempcolor.getGreen()+"b="+tempcolor.getBlue());
//                System.out.println("r="+(int)Math.round(gxyred)+",g="+(int)Math.round(gxygreen)+",b="+(int)Math.round(gxyblue));
//                System.out.println("");
                
                tempcolor = new Color((int)Math.round(gxyred), (int)Math.round(gxygreen), (int)Math.round(gxyblue), 255);
                imageafter.setRGB(i-1, j-1, tempcolor.getRGB());
//                System.out.println(c + ". (R=" + tempcolor.getRed() + ",G=" + tempcolor.getGreen() + ",B=" + tempcolor.getBlue() + ") ");
                
            }
        }

        return imageafter;
    }
    
    public double convert0to1(int value){
        return (double) (((value) * ((1.0) / 255.0)));
    }
    
    public double convert0to255(double value){
        return (value) * (255.0  / (1.0));
    }
    
    public double convert0to100(int value){
        return (double) (((value) * ((1.0) / 100.0)));
    }
    
    public double convert50to150(int value){
        return (double) (((value - 50) * ((1.0) / 100.0)) + 0.5);
    }
    private class MyColor{
        long R,G,B;
        MyColor(long R, long G, long B){
            this.R = R;
            this.G = G;
            this.B = B;
        }
        
        void setR(long R){
            this.R = R;
        }
        
        void setG(long G){
            this.G = G;
        }
        
        void setB(long B){
            this.B = B;
        }
        
        long getR(){
            return R;
        }
        
        long getB(){
            return B;
        }
        
        long getG(){
            return G;
        }
    }
}
