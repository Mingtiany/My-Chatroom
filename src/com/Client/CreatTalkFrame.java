package com.Client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class CreatTalkFrame {
    public String FrameName;//������
	public  JTextPane jTextPane;//Ⱥ����ʾ���
	public JScrollPane jScrollPane;//��ʾ������
	public  JTextPane jTextPane1;//���Ϳ����
	public JScrollPane jScrollPane1;//���Ϳ򻬶���
	
	
	
public void CreatFrame(Person p){
		
		JFrame jfr=new JFrame();
		jfr.setSize(500, 800);
		jfr.setTitle(p.name);
		FrameName=p.name;
		jfr.setDefaultCloseOperation(2);
		jfr.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<listen.mylist.size();i++){
					if(FrameName.equals(listen.mylist.get(i).FrameName)){
						listen.mylist.delete(listen.mylist.get(i));
	
					}
					
				}
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		jfr.setLocationRelativeTo(null);
		jfr.setLayout(new FlowLayout());
	
		
		JPanel jPanel=new JPanel(new BorderLayout());
		jPanel.setPreferredSize(new Dimension(475, 90));
		JLabel picture=new JLabel(p.icon);
		JLabel mottolabel=new JLabel(p.motto);
		jPanel.add(picture,BorderLayout.WEST);
		jPanel.add(mottolabel,BorderLayout.SOUTH);
		jPanel.setBackground(new Color(220,180,150));
        jfr.add(jPanel);
       //������ʾ������
        jTextPane=new JTextPane();
        jTextPane.setPreferredSize(new Dimension(200, 200));
        jTextPane.setCaretPosition(0);//���ù��λ��
		jScrollPane=new JScrollPane(jTextPane);
		jScrollPane.setPreferredSize(new Dimension(300, 300));
		jfr.add(jScrollPane);
		
		//���Ϳ�����
		jTextPane1=new JTextPane();
		jTextPane1.setPreferredSize(new Dimension(200, 100));
		jScrollPane1=new JScrollPane(jTextPane1);
		jScrollPane1.setPreferredSize(new Dimension(200, 200));
		jfr.add(jScrollPane1);
		
		Button but=new Button("����");
		but.addActionListener(Login.o);
		jfr.add(but);
		//��ͼƬ��ť
		Button but1=new Button("��ȡͼƬ");
		but1.addActionListener(Login.o);
		jfr.add(but1);

		jfr.setVisible(true);
		
		
		
	}
}
