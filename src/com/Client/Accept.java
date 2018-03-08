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
	private int Style;//��Ϣ����
	private int namelen;//������QQ�ǳƳ���
	private int length;//��Ϣ����
	private String name;//�����ߵ��ǳ�
	private CreatTalkFrame ct;//���촰�ڶ���
	private String Qunname=null;//Ⱥ��
	private int isEnd;//�ж��Ƿ�����Ϣ�����һ����
	private int isStart;//�ж��Ƿ�����Ϣ�ĵ�һ����

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
				if(Style==11||Style==12){//Ⱥ��������Ϣ
					System.out.println("�յ�Ⱥ����Ϣ");
				int	Receivelen=in.read();
				byte []byt=new byte[Receivelen];
				in.read(byt);
				Qunname=new String(byt);	
				System.out.println("���ܵ���Ⱥ��"+Qunname);
				}
				length=in.readInt();
				byte[]b=new byte[length];
				in.read(b);
				String s=new String(b);
				isEnd=in.read();
				isStart=in.read();

				//�ҳ����ڶ���
				boolean flag=true;
				for(int i=0;i<listen.mylist.size();i++){
					
					//Ⱥ��
					if(Style==11||Style==12){
						
					if(Qunname.equals(listen.mylist.get(i).FrameName)){
					flag=false;//�Ѵ��ڸô���,����Ҫ�ٴ������Ĵ���
				    ct= listen.mylist.get(i);
					break;
					}
					}
					//˽��
					if(Style==21||Style==22){
						System.out.println("Ѱ��˽�Ĵ���");

					if(name.equals(listen.mylist.get(i).FrameName)){
					flag=false;//�Ѵ��ڸô���,����Ҫ�ٴ������Ĵ���
				    ct= listen.mylist.get(i);
					break;
					}
					}
				}
				if(flag){
					//��������
					Person p1 = null;
					CreatTalkFrame ctf=new CreatTalkFrame();
					//�ҳ����Ѷ���
					for(int i=0;i<listen.ve.size();i++){
						//Ⱥ��
						if(Style==11||Style==12){
						if(Qunname.equals(listen.ve.get(i).name)){
							p1=listen.ve.get(i);
							System.out.println("Ⱥ�Ĵ��ڴ����ɹ�");
						  }
						}
						//˽��
						if(Style==21||Style==22){
							 if(name.equals(listen.ve.get(i).name)){
							p1=listen.ve.get(i);
							System.out.println("˽�Ĵ��ڴ����ɹ�");
							 }
						}
						
					}
					listen.p=p1;
					System.out.println(p1.name);
					ctf.CreatFrame(p1);//��������
				
					ct=ctf;
					listen.mylist.add(ct);
				}
				
				//��ʾ��Ϣ
				if(Style==11){//Ⱥ��������Ϣ
					   if(isStart==1){
						ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), name+":"+s, null);
					   }
					   else
							ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), s, null);
 
						Document doc=ct.jTextPane.getDocument();//��ȡ�ĵ�
	                	ct.jTextPane.setCaretPosition(doc.getLength());//���ù��λ��

						if(isEnd==1){//����
							doc=ct.jTextPane.getDocument();//��ȡ�ĵ�
	                    	doc.insertString(doc.getLength(), "\r\n", null);	
	                    	ct.jTextPane.setCaretPosition(doc.getLength());//���ù��λ��
						}
                         
			  }
				else if(Style==12){//Ⱥ��ͼƬ��Ϣ
				if(isStart==1){
					ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), name+":", null);
					ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());
					ct.jTextPane.insertIcon(new ImageIcon(b));
					ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());

				}else{
					ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());
					ct.jTextPane.insertIcon(new ImageIcon(b));
				}
					Document doc=ct.jTextPane.getDocument();//��ȡ�ĵ�
                	ct.jTextPane.setCaretPosition(doc.getLength());//���ù��λ��

                	if(isEnd==1){//����
						doc=ct.jTextPane.getDocument();//��ȡ�ĵ�
                    	doc.insertString(doc.getLength(), "\r\n", null);	
                    	ct.jTextPane.setCaretPosition(doc.getLength());//���ù��λ��
	
                	}					

				}

				else if(Style==21){//˽��������Ϣ
					 if(isStart==1){
							ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), name+":"+s, null);
					 }
						   else
								ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), s, null);
	 
							Document doc=ct.jTextPane.getDocument();//��ȡ�ĵ�
		                	ct.jTextPane.setCaretPosition(doc.getLength());//���ù��λ��

							if(isEnd==1){//����
								doc=ct.jTextPane.getDocument();//��ȡ�ĵ�
		                    	doc.insertString(doc.getLength(), "\r\n", null);	
		                    	ct.jTextPane.setCaretPosition(doc.getLength());//���ù��λ��
							}
	                         

				}
				else{//˽��ͼƬ��Ϣ
					if(isStart==1){
						ct.jTextPane.getStyledDocument().insertString(ct.jTextPane.getDocument().getLength(), name+":", null);
						ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());
						ct.jTextPane.insertIcon(new ImageIcon(b));
						ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());

					}else{
						ct.jTextPane.setCaretPosition(ct.jTextPane.getDocument().getLength());
						ct.jTextPane.insertIcon(new ImageIcon(b));
					}
						Document doc=ct.jTextPane.getDocument();//��ȡ�ĵ�
	                	ct.jTextPane.setCaretPosition(doc.getLength());//���ù��λ��

	                	if(isEnd==1){//����
							doc=ct.jTextPane.getDocument();//��ȡ�ĵ�
	                    	doc.insertString(doc.getLength(), "\r\n", null);	
	                    	ct.jTextPane.setCaretPosition(doc.getLength());//���ù��λ��
		
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