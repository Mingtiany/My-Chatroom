package com.Client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JTextPane;

public class Bubble extends JTextPane{
	
	public Graphics g=null;
	public int width=10;//���������Ϣÿһ�еĿ��
	public int height=15;//���������Ϣ�еĸ߶�
	public ImageIcon icon;
    public  ArrayList<Object> txtandicon1=new ArrayList<Object>();//���ڴ洢һ����Ϣ������ֺ�ͼƬ
    public  ArrayList<byte[]> iconbyte1=new ArrayList<byte[]>();//���ڴ洢һ����Ϣ������ֺ�ͼƬ 

	
	public Bubble(ImageIcon icon,ArrayList<Object> txtandicon,ArrayList<byte[]> iconbyte){
		this.icon=icon;
		this.txtandicon1=txtandicon;
		this.iconbyte1=iconbyte;
		this.setPreferredSize(new Dimension(200, 50));
		this.g=this.getGraphics();
	
	}
	
	public void paint(Graphics g){
    	super.paint(g);
    System.out.println(txtandicon1.get(0).toString());
  
		paintComponents(g);
        
		}
	
    public void paintComponents(Graphics g ){
    	//�����Ϣ
    	this.setBackground(Color.yellow);
    	int h=0;

    	for(int i=0;i<txtandicon1.size();i++){
    		if(i==0){
    			
    			g.setColor(Color.black);
                   g.drawLine(100, 100, 300, 300);
    			   g.drawImage(icon.getImage(), 0, 0,40,40,this);
    			   int[]x={40,60,60};
    			   int[]y={10,10,20};
    			   g.setColor(Color.green);
    			   g.fillPolygon(x, y, 3);
    			   g.fillRoundRect(60, 0, 200, 40, 20, 20);
    		}
    		if(txtandicon1.get(i).getClass().getName().equals("java.lang.String")){
    			//����ַ���

    			 for(int k=0;k<txtandicon1.get(i).toString().length();k++){//���ַ�һ��һ������ȥ
     	    		char item=txtandicon1.get(i).toString().charAt(k);
     	    		String it=String.valueOf(item);//���ַ�ת��Ϊ�ַ���
     	    		System.out.println("ppp="+it);
     	    		if(width>190){//����
     	    		    height+=10;
     	    		    width=20;
     	    			this.setPreferredSize(new Dimension(200, 50));
     	    		    g.setColor(Color.black);
     	    			g.setFont(new Font(getText(), ALLBITS, 20));
     	    			g.drawString(it, width, height);
     	    		    width+=20;
     	    		}
     	    		else{

     	    			g.setColor(Color.black);
     	    			g.setFont(new Font(getText(), ALLBITS, 15));
     	    			g.drawString(it, width, height);
     	    		    width+=20;
     	    		}
     	    		
     	    		
     	    	}
    			
    		}
    		else{
    			//���ͼƬ
    			ImageIcon im=new ImageIcon(iconbyte1.get(h));
    			h++;
    			  if(width>170){//����
         			    height+=30;
         			    width=60;
         				this.setPreferredSize(new Dimension(200, 50));
         				g.setColor(Color.black);
         				g.drawImage(im.getImage(), width, height,30,30,this);
         			    width+=30;
         			}
         			else{
         				g.setColor(Color.black);
         				g.setFont(new Font(getText(), ALLBITS, 20));
         				g.drawImage(im.getImage(), width, height,30,30,this);
         			    width+=30;
         			}
    			
    		}
    		
    		
    	}
    
    	}
 
 
	
}
