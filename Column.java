package CY;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Column {
    int x,y;//定义柱子的中心位置
    int width;
    int height;//宽高
    int gap=110;//缝隙大小
    Random r=new Random();
    BufferedImage image;
    public Column(int x)throws IOException{
    	image = ImageIO.read(getClass().getResource("/TP/column.png"));
    	width=image.getWidth();
    	height=image.getHeight();
    	this.x=x;
    	this.y=r.nextInt(140)+140;
    }
    public void step(){
    	x--;
    	if(x<-width){
    		x=320;
    		this.y=r.nextInt(140)+140;
    	}
    }
    public void paint(Graphics g){
		//g.drawRect(x-width/2, y-height/2, width, height/2-gap/2);
    	//g.drawRect(x-width/2, y+gap/2, width, height/2-gap/2);
    	g.drawImage(image,x-width/2,y-height/2,null);
    }
}
