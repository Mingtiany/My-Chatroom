package com.Client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.parser.Element;

import org.omg.CORBA.Context;


public class listen implements ActionListener,MouseListener{

	public static JTextField jt;
	public static JTextField jt1;
	public static Myarraylist<CreatTalkFrame> mylist=new Myarraylist<CreatTalkFrame>();//用来存储创建的聊天窗口
	public JFrame jFrame;
	private JList<Person> list;
	public static Vector<Person> ve =new Vector<Person>();//用于存储所有用户成员
	private JScrollPane jp;
	private OutputStream out1;
	private InputStream in1;
	private DataOutputStream out;
	private DataInputStream in;
	private Accept a;
	public static Person p;//选择的用户
	private Person p1;
	private Person p2;
	private Person p3;
	private Person p4;
	private Person me; 
    public   ArrayList<byte[]> iconbyte=new ArrayList<byte[]>();//用于存储一条消息里的文字和图片 
    public   ArrayList<Object> txtandicon=new ArrayList<Object>();//用于存储一条消息里的文字和图片
	private Document doc;
	private  ArrayList<Person> Community=new ArrayList<Person>();//用于存储群聊成员	
	//分离图片和文字消息
	public void copyContent(JTextPane jTextPane1,JTextPane jTextPane) throws BadLocationException {
		int k = 0;
		String str="";
		
		for (int i = 0; i < jTextPane1.getText().length(); i++) {
//			if(i==0){
//				jTextPane.getStyledDocument().insertString(jTextPane.getDocument().getLength(),"I:",null);
//			}
		if (jTextPane1.getStyledDocument().getCharacterElement(i).getName()
		.equals("icon")) {
//			jTextPane.insertIcon(new ImageIcon(iconbyte.get(k)));
		    
		    if(str==""){
		    	txtandicon.add(new ImageIcon(iconbyte.get(k)));
		    }
		    else{
		    	txtandicon.add(str);
		    	txtandicon.add(new ImageIcon(iconbyte.get(k)));
                str="";
		    }
		    k++;
		} 
		else {
		try {
//			jTextPane.getStyledDocument().insertString(jTextPane.getDocument().getLength(),
//					jTextPane1.getStyledDocument().getText(i, 1), null);
			str=str+jTextPane1.getStyledDocument().getText(i, 1);
		 if(i==jTextPane1.getText().length()-1){
			 txtandicon.add(str);
			 str="";
		 }
		 
		} catch (BadLocationException ble) {
		ble.printStackTrace();
		}
		}
		}
		
      
		}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if("登录".equals(e.getActionCommand())){
			String title=Login.jTextField.getText();
			 //初始化
			initialize(title);
			
			//根据title找出person
			    if(p1.name.equals(title))
				    me=p1;
		        if(p2.name.equals(title))
					me=p2;
		        if(p3.name.equals(title))
					me=p3;
		        if(p4.name.equals(title))
					me=p4;
			
			Login.j.dispose();//登陆成功后，关闭登陆窗口
			jFrame=new JFrame();
			jFrame.setTitle(title);
			jFrame.setLocationRelativeTo(null);
			jFrame.setLayout(new FlowLayout());
			jFrame.setDefaultCloseOperation(2);
			jFrame.setSize(400, 600);
			JPanel jp1 = new JPanel();//头像面板
			jp1.setPreferredSize(new Dimension(380, 100));
			jp1.setBackground(new Color(255,222,173));
			jp1.setLayout(new BorderLayout());
			jFrame.add(jp1);
			//我的头像
			JLabel label=new JLabel();
			label.setPreferredSize(new Dimension(100,100));
			label.setIcon(me.icon);
			jp1.add(label,BorderLayout.WEST);
			//我的motto
			JLabel label1=new JLabel();
			label1.setText(me.motto);
			jp1.add(label1,BorderLayout.SOUTH);
			//我的name
			JLabel label2=new JLabel();
			label2.setText(me.name);
			jp1.add(label2);
			
			
			list.updateUI();
			list.setCellRenderer(new MyCellRenderer());
			list.setPreferredSize(new Dimension(380,600));
			list.addMouseListener(this);
			jFrame.add(list);
			jFrame.setVisible(true);
			
			//创建Socket对象
			Socket client;
			
			try {//连接服务器
				client = new Socket("localhost", 8889);
			
				 in1 = client.getInputStream();
				 out1 =  client.getOutputStream();
				 out=new DataOutputStream(out1);
				 in=new DataInputStream(in1);
				 a=new Accept(in);
	             a.start();
					
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		}

		if("发送".equals(e.getActionCommand())){
			//找到聊天窗口
			CreatTalkFrame ctf = null;
			for(int i=0;i<mylist.size();i++){
				if(p.name.equals(mylist.get(i).FrameName)){
					ctf=mylist.get(i);
					break;
				}
			}
			try {//分离图片文字消息
				txtandicon.clear();
	            iconbyte.clear();
				copyContent(ctf.jTextPane1,ctf.jTextPane);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            //发送和显示一条消息
            int isEnd=0;//用来判断是否是消息的最后一部分
            int isStart=0;//用来判断是否是消息的第一部分
            int h=0;//用来同级发图片数
        	
			for(int k=0;k<txtandicon.size();k++){
				if(k==(txtandicon.size()-1)){
					isEnd=1;//结束
				}
				if(k==0){
					isStart=1;
				}else{
					isStart=0;

				}
				
			if(p.Check==1){//群聊
				if(txtandicon.get(k).getClass().getName().equals("java.lang.String")){
				try {
					out.write(11);//消息类型-群聊文字
					out.write(me.QQnumber);//消息的发送者
					out.write(p.name.getBytes().length);//消息接收的群名长度
					out.write(p.name.getBytes());//消息接收的群名
					out.writeInt(txtandicon.get(k).toString().getBytes().length);//发送消息内容的长度
					out.write(txtandicon.get(k).toString().getBytes());//发送消息内容
					out.write(isEnd);//消息是否是最后一部分              
					out.write(isStart);//消息是否是第一部分   
					
				
                    doc=ctf.jTextPane.getDocument();//获取文档
                	ctf.jTextPane.setCaretPosition(doc.getLength());//设置光标位置
                	
                	
                    if(isEnd==1){//换行
                    	
                        Bubble bubble=new Bubble(me.icon,txtandicon,iconbyte);//气泡对象
                    	ctf.jTextPane.insertComponent(bubble);//显示气泡

                        //bubble.repaint();

                    	doc=ctf.jTextPane.getDocument();//获取文档
                    	doc.insertString(doc.getLength(), "\r\n", null);
                        //将存放一条消息的数组队列清空
//                    	txtandicon.clear();
//                        iconbyte.clear();
                    }
                    
                    
                    
					ctf.jTextPane1.setText("");
				} catch (IOException | BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				}
				else{//发图片
					try {
						out.write(12);//消息类型-群聊图片
						out.write(me.QQnumber);//消息的发送者
						out.write(p.name.getBytes().length);//消息接收的群名长度
						out.write(p.name.getBytes());//消息接收的群名
						out.writeInt(iconbyte.get(h).length);//发送消息内容的长度
						out.write(iconbyte.get(h));//发送消息内容
						out.write(isEnd);//消息是否是最后一部分              
						out.write(isStart);//消息是否是第一部分    
			            
			            h++;
						doc=ctf.jTextPane.getDocument();//获取文档
	                	ctf.jTextPane.setCaretPosition(doc.getLength());//设置光标位置

	                	if(isEnd==1){//换行
	                        Bubble bubble=new Bubble(me.icon,txtandicon,iconbyte);//气泡对象
	    					bubble.repaint();
	                		ctf.jTextPane.insertComponent(bubble);//显示气泡
	                    	doc=ctf.jTextPane.getDocument();//获取文档
	                    	doc.insertString(doc.getLength(), "\r\n", null);
	                    	txtandicon.clear();
	                    	iconbyte.clear();
	                	}
						ctf.jTextPane1.setText("");
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			
			if(p.Check==2){//私聊

				if(txtandicon.get(k).getClass().getName().equals("java.lang.String")){
					try {
						out.write(21);//消息类型-私聊文字
						out.write(me.QQnumber);//消息的发送者
						out.write(p.QQnumber);//消息的接收者
						out.writeInt(txtandicon.get(k).toString().getBytes().length);//发送消息内容的长度
						out.write(txtandicon.get(k).toString().getBytes());//发送消息内容
						out.write(isEnd);//消息是否是最后一部分              
						out.write(isStart);//消息是否是第一部分  
						
						doc=ctf.jTextPane.getDocument();//获取文档
	                	ctf.jTextPane.setCaretPosition(doc.getLength());//设置光标位置
	                    if(isEnd==1){//换行
	                        Bubble bubble=new Bubble(me.icon,txtandicon,iconbyte);//气泡对象
	    					bubble.repaint();
	    					System.out.println("刷新3");

	                		ctf.jTextPane.insertComponent(bubble);//显示气泡
	                    	doc=ctf.jTextPane.getDocument();//获取文档
	                    	doc.insertString(doc.getLength(), "\r\n", null);
	                        txtandicon.clear();
	                        iconbyte.clear();
	                    }
	                    
						ctf.jTextPane1.setText("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
					else{//发图片
						try {
							System.out.println("发图片");
							out.write(22);//消息类型-私聊图片
							out.write(me.QQnumber);//消息的发送者
							out.write(p.QQnumber);//消息的接收者
							out.writeInt(iconbyte.get(h).length);//发送消息内容的长度
							out.write(iconbyte.get(h));//发送消息内容
							out.write(isEnd);//消息是否是最后一部分              
							out.write(isStart);//消息是否是第一部分    
							h++;
				
							doc=ctf.jTextPane.getDocument();//获取文档
		                	ctf.jTextPane.setCaretPosition(doc.getLength());//设置光标位置

		                	if(isEnd==1){//换行
		                        Bubble bubble=new Bubble(me.icon,txtandicon,iconbyte);//气泡对象
		    					bubble.repaint();
		                		ctf.jTextPane.insertComponent(bubble);//显示气泡
		                    	doc=ctf.jTextPane.getDocument();//获取文档
		                    	doc.insertString(doc.getLength(), "\r\n", null);
		                    	txtandicon.clear();
		                    	iconbyte.clear();
		                	}
							ctf.jTextPane1.setText("");

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				
			   }
			}
		
			}
		
		if("获取图片".equals(e.getActionCommand())){
			byte[] byt=null;
			JFileChooser jFileChooser=new JFileChooser();
			jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnVal=jFileChooser.showDialog(null,"确定");
			 if(returnVal==JFileChooser.APPROVE_OPTION){
				 String path=jFileChooser.getSelectedFile().getAbsolutePath();
				//读取图片
			   FileInputStream Fileinput=null;
			try {
				 Fileinput = new FileInputStream(path);
				 //获取需要读取图片的字节长度 
				 int len=Fileinput.available();
			    byt=new byte[len];
			      Fileinput.read(byt);
			      iconbyte.add(byt);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//找到聊天窗口
			CreatTalkFrame ctf1 = null;
			for(int w=0;w<mylist.size();w++){
				if(p.name.equals(mylist.get(w).FrameName)){
					ctf1=mylist.get(w);
					break;
				}
			}
		
			
    
					ctf1.jTextPane1.insertIcon(new ImageIcon(byt));
				    doc=ctf1.jTextPane1.getDocument();//获取文档
					ctf1.jTextPane1.setCaretPosition(doc.getLength());

			 }
		 }
		}
	
//		if("注册".equals(e.getActionCommand())){
//			JFrame jFrame=new JFrame();
//			jFrame.setTitle("用户注册");
//			jFrame.setLocationRelativeTo(null);
//			jFrame.setLayout(new FlowLayout());
//			jFrame.setDefaultCloseOperation(2);
//			jFrame.setSize(400, 400);
//			jt=new JTextField("用户名", 30);
//			jt1=new JTextField("密码", 30);
//	   
//			
//			jFrame.add(jt);
//			jFrame.add(jt1);
//			
//			Button but=new Button("确定注册");
//	        
//			but.addActionListener(this);
//			jFrame.add(but);
//			
//			jFrame.setVisible(true);
//
//		}
		
//		if("确定注册".equals(e.getActionCommand())){
//
//				// TODO Auto-generated method stub
//				JFrame jFrame1=new JFrame();
//				jFrame1.setTitle("注册成功");
//				jFrame1.setLocationRelativeTo(null);
//				jFrame1.setLayout(new FlowLayout());
//				jFrame1.setDefaultCloseOperation(2);
//				jFrame1.setSize(300, 200);
//				JTextField jtt=new JTextField("注册成功!", 5);
//				jtt.setForeground(Color.RED);
//				jtt.setFont(new Font("注册成功！", Font.BOLD, 40));
//				
//				jFrame1.add(jtt);
//				
//				jFrame1.setVisible(true);
//				
//				byte[] pw=null;
//				byte[] u=null;
//				try {
//					MessageDigest md=MessageDigest.getInstance("MD5");
//					
//				   pw=md.digest(jt1.getText().getBytes());
//				   u=md.digest(jt.getText().getBytes());
//
//				} catch (NoSuchAlgorithmException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//			
//
//				try {
//					File file=new File("‪C:\\Users\\YMT\\Desktop\\Server.txt");
//					DataOutputStream output=new DataOutputStream(new FileOutputStream(file));		
//					
//					int length=u.length;
//					output.writeInt(length);
//					
//					output.write(u);
//					System.out.println("写入的user："+new String(u)+"长度"+length);
//					int length1=pw.length;
//					output.writeInt(length1);
//					output.write(pw);
//					System.out.println("写入的password："+new String(pw)+"长度"+length1);
//
//					output.flush();
//					output.close();
//					
//				} catch (FileNotFoundException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}


	//初始化好友列表
	public void initialize(String s){
		 p1=new Person("YMT", new ImageIcon("picture/YMT.jpg"), 1, "再见，不见，昨天……",2);
		 p2=new Person("Went", new ImageIcon("picture/Went.jpg"), 2, "dream/这个世界注定要有人比你更强,更好,不能因此而放弃奔跑.跑不过别人,但一定要跑过昨天的自己!",2);
		 p3=new Person("Will", new ImageIcon("picture/Will.jpg"), 3, "A day without discovery . . . isn’t much of a day.",2);
		//初始化群聊成员
		 initiaCommunity();
		 p4=new Person("群聊",new ImageIcon("picture/群聊.gif"),4,"",1,Community);
		 
		 if(!(p1.name.equals(s)))
		 ve.add(p1);
         if(!(p2.name.equals(s)))
		 ve.add(p2);
         if(!(p3.name.equals(s)))
		ve.add(p3);
         if(!(p4.name.equals(s)))
     		ve.add(p4);
		list=new JList<Person>(ve);
	}
	
	//初始化群聊成员
	public void initiaCommunity(){
    Community.add(p1);
    Community.add(p2);
    Community.add(p3);
   
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2){
		JList<Person> list1=(JList)e.getSource();
		int Selected=list1.getSelectedIndex();
		p=list1.getModel().getElementAt(Selected);
        CreatTalkFrame ct=new CreatTalkFrame();
        mylist.add(ct);//添加已创建的窗口对象
		ct.CreatFrame(p);//创建聊天窗口
		
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
