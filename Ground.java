package CY;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ground {
	
	BufferedImage image;
	int x,y;//定义地面的中心位置
	int width,height;//定义地面的宽高
	public Ground(int y)throws IOException{
		image=ImageIO.read(getClass().getResource("/TP/ground.png"));
		this.y=y;
		width=image.getWidth();
		height=image.getHeight();
		x=0;
	}
	public void step(){
		x--;
		if(x<=-(width-350)){
			x=0;
		}
	}
	public void paint(Graphics g){
		g.drawImage(image,x,y,null);
	}

}
