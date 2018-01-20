package CY;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Bird {

	int x,y;//鸟的中心位置
	double angle;//飞行角度
	BufferedImage[] images;//动画帧
	BufferedImage image;//当前图片
	int index=0;//图片序号
	final double g;//中立加速度
	final double t;//时间间隔（秒）
	final double v0;//初始上抛速度
	double speed;//当前上抛速度
	double s;//移动距离
	int size=14;//鸟的范围，大小
	
	public Bird()throws Exception{
		this.g=4.0;//重力加速度
		this.t=0.25;//每次计算时间间隔
		this.v0=20;//初始上抛速度
		x=100;
		y=270;//鸟的初始位置
		//加载动画帧
		images =new BufferedImage[3];
		images[0]=ImageIO.read(getClass().getResource("/TP/0.png"));
		images[1]=ImageIO.read(getClass().getResource("/TP/1.png"));
		images[2]=ImageIO.read(getClass().getResource("/TP/2.png"));
		image=images[0];
	}
	//小鸟在上抛的某个过程有一个做上抛运动
	//上抛速度v=v0-gt;距离s=v0t-g*t*t/2
	public void step(){
		double v0=speed;//v0当前上抛速度
		double v=v0-g*t;//计算t之后的速度
		speed=v;//速度更新
		s=v0*t-g*t*t/2;//计算t之内移动的垂直距离
		y=y-(int)s;//更新小鸟的垂直高度
		//计算仰角
		angle=-Math.atan2(s, 8);
		index++;
		image=images[(index/8)%3];
	}
	public void fly(){
		speed=v0;//每次小鸟跳跃是当前点重新以初速度v0向上抛
	}
	public synchronized void paint(Graphics g){
		//g.drawRect(x-size/2, y-size/2, size, size);
		Graphics2D g2=(Graphics2D)g;
		g2.rotate(angle,this.x,this.y);
		int x=this.x-image.getWidth()/2;
		int y=this.y-image.getHeight()/2;
		g.drawImage(image, x, y,null);
		g2.rotate(-angle,this.x,this.y);
		//回执图像
	}
	public String toString() {
		return "Bird [x="+x+",y="+y+",g="+g+",t="+t+",v0="+v0+",speed="+speed+",s="+s+"]";
		}
	public boolean pass(Column col1,Column col2){
		//判断是否通过了柱子
		return  col1.x==x||col2.x==x;
	}
	public boolean hit(Column col1,Column col2,Ground ground){
		if(y-size/2>ground.y)//撞到地面
		{
		return true;
		}
		return hit(col1)||hit(col2);
	}
	public boolean hit(Column col){
		 
		if( x>col.x-col.width/2-size/2&&x<col.x+col.width/2+size/2)
		  {
		    if(y>col.y-col.gap/2+size/2&&y<col.y+col.gap/2-size/2 )
		         {
		               return false;
		         }
		               return true;
		  }
		               return false;
	}
}
