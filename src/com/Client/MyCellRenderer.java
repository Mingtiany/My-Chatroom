package com.Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class MyCellRenderer implements ListCellRenderer{

	JPanel jp=null;
	JLabel icon=null;
	JLabel name=null;
	JLabel detail=null;

	
	public MyCellRenderer(){
		jp=new JPanel();
		
		icon=new JLabel();
		name=new JLabel();
		detail=new JLabel();

		jp.setLayout(new BorderLayout());
		jp.add(icon, BorderLayout.WEST);
		jp.add(name);
		jp.add(detail, BorderLayout.SOUTH);
		
		
		
	}
	
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		Person person=(Person)value;
		
		icon.setIcon(person.icon);
		icon.setPreferredSize(new Dimension(50, 50));
		name.setText(person.name+'('+person.QQnumber+')');
		detail.setText(person.motto);
		
		if(isSelected){
		
		detail.setVisible(true);
		jp.setBackground(new Color(255,248,220));
		}else{
			detail.setVisible(false);
			jp.setBackground(new Color(255,255,240));
			
		}
		return jp;
	}
	
	
	private MouseListener listener=new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("点击");

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
			System.out.println("进入");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("退出");

		}
		
	};

}
