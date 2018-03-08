package com.Client;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;





public class Login {

	/**
	 * @param µÇÂ½×¢²á½çÃæ
	 */
	
	public static JTextField jTextField;
	public static JTextField jTextField1;
	public static JFrame j;
	public static listen o;
	
	
	
	public static void main(String[] args) {
       //´´½¨µÇÂ½´°¿Ú
		
		j = new JFrame();
		j.setTitle("login");
		j.setSize(350, 500);
		j.setLocationRelativeTo(null);
		j.setDefaultCloseOperation(2);
		j.setLayout(new FlowLayout());
		ImageIcon im = new ImageIcon("picture/me.jpg");
		JLabel k = new JLabel(im);
		j.add(k);
		jTextField=new JTextField("ÕËºÅ", 25);
		j.add(jTextField);
		jTextField1=new JTextField("ÃÜÂë", 25);
		j.add(jTextField1);
		JButton ju = new JButton("µÇÂ¼");
		
		JButton ju2 = new JButton("×¢²á");

		o = new listen();
		ju.addActionListener(o);
	
		ju2.addActionListener(o);

		j.add(ju);
	
		j.add(ju2);

		j.setVisible(true);
		
	}

}
