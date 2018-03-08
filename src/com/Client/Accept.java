package com.Client;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.DataInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


class Accept extends Thread{
	private java.io.DataInputStream in;
	public static int numofclient;
	private int Style;//消息类型
	private int namelen;//发送者QQ昵称长度
	private int length;//消息长度
	private String name;//发送者的昵称
	private CreatTalkFrame ct;//聊天窗口对象
	private String Qunname=null;//群名
	private int isEnd;//判断是否是消息的最后一部分
	private int isStart;//判断是否是消息的第一部分

	public Accept(java.io.InputStream in){
		this.in=new DataInputStream(in);
	}
	
	public void run(){
		
		while(true){
			try {
				Style=(int)in.read();
				namelen=(int)in.read();
				byte []by=new byte[namelen];
				in.read(by);
				name=new String(by);
				if(Style==11||Style==12){//群聊文字信息
					System.out.println("收到群聊信息");
				int	Receivelen=in.read();
				byte []byt=new byte[Receivelen];
				in.read(byt);
				Qunname=new String(byt);	
				System.out.println("接受到的群："+Qunname);
				}
				length=in.readInt();
				byte[]b=new byte[length];
				in.read(b);
				String s=new String(b);
				isEnd=in.read();
				isStart=in.read();

				//找出窗口对象
				boolean flag=true;
				for(int i=0;i<listen.mylist.size();i++){
					
					//群聊
					if(Style==11||Style==12){
						
					if(Qunname.equals(listen.mylist.get(i).FrameName)){
					flag=false;//已存在该窗口,不需要再创建更改窗口
				    ct= listen.mylist.get(i);
					break;
					}
					}
					//私聊
					if(Style==21||Style==22){
						System.out.println("寻找私聊窗口");

					if(name.equals(listen.mylist.get(i).FrameName)){
					flag=false;//已存在该窗口,不需要再创建更改窗口
				    ct= listen.mylist.get(i);
					break;
					}
					}
				}
				if(flag){
					//创建窗口
					Person p1 = null;
					CreatTalkFrame ctf=new CreatTalkFrame();
					//找出好友对象
					for(int i=0;i<listen.ve.size();i++){
						//群聊
						if(Style==11||Style==12){
						if(Qunname.equals(listen.ve.get(i).name)){
							p1=listen.ve.get(i);
							System.out.println("群聊窗口创建成功");
						  }
						}
						//私聊
						if(Style==21||Style==22){
							 if(name.equals(listen.ve.get(i).name)){
							p1=listen.ve.get(i);
							System.out.println("私聊窗口创建成功");
							 }
						}
						
					}
					listen.p=p1;
					System.out.println(p1.name);
					ctf.CreatFrame(p1);//创建窗口
				
					ct=ctf;
					listen.mylist.add(ct);
				}
				
				//显示消息
				if(Style==11){//群聊文字消息
					   if(isStart==1){
						ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), name+":"+s, null);
					   }
					   else
							ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), s, null);
 
						Document doc=ct.jTextPane.getDocument();//获取文档
	                	ct.jTextPane.setCaretPosition(doc.getLength());//设置光标位置

						if(isEnd==1){//换行
							doc=ct.jTextPane.getDocument();//获取文档
	                    	doc.insertString(doc.getLength(), "\r\n", null);	
	                    	ct.jTextPane.setCaretPosition(doc.getLength());//设置光标位置
						}
                         
			  }
				else if(Style==12){//群聊图片消息
				if(isStart==1){
					ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), name+":", null);
					ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());
					ct.jTextPane.insertIcon(new ImageIcon(b));
					ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());

				}else{
					ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());
					ct.jTextPane.insertIcon(new ImageIcon(b));
				}
					Document doc=ct.jTextPane.getDocument();//获取文档
                	ct.jTextPane.setCaretPosition(doc.getLength());//设置光标位置

                	if(isEnd==1){//换行
						doc=ct.jTextPane.getDocument();//获取文档
                    	doc.insertString(doc.getLength(), "\r\n", null);	
                    	ct.jTextPane.setCaretPosition(doc.getLength());//设置光标位置
	
                	}					

				}

				else if(Style==21){//私聊文字消息
					 if(isStart==1){
							ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), name+":"+s, null);
					 }
						   else
								ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), s, null);
	 
							Document doc=ct.jTextPane.getDocument();//获取文档
		                	ct.jTextPane.setCaretPosition(doc.getLength());//设置光标位置

							if(isEnd==1){//换行
								doc=ct.jTextPane.getDocument();//获取文档
		                    	doc.insertString(doc.getLength(), "\r\n", null);	
		                    	ct.jTextPane.setCaretPosition(doc.getLength());//设置光标位置
							}
	                         

				}
				else{//私聊图片消息
					if(isStart==1){
						ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), name+":", null);
						ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());
						ct.jTextPane.insertIcon(new ImageIcon(b));
						ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());

					}else{
						ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());
						ct.jTextPane.insertIcon(new ImageIcon(b));
					}
						Document doc=ct.jTextPane.getDocument();//获取文档
	                	ct.jTextPane.setCaretPosition(doc.getLength());//设置光标位置

	                	if(isEnd==1){//换行
							doc=ct.jTextPane.getDocument();//获取文档
	                    	doc.insertString(doc.getLength(), "\r\n", null);	
	                    	ct.jTextPane.setCaretPosition(doc.getLength());//设置光标位置
		
	                	}	
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		}
}