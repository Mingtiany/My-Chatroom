package com.Client;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.ImageIcon;
//������
public class Person {
	
		 public String name;//�����ǳ�
		 public ImageIcon icon;//ͷ��
		 public int QQnumber;//QQ��
		 public String motto;//ǩ��
		 public int Check;//������֣����ڼ���Ƿ���Ⱥ 1-Ⱥ 2-�����û�
		 public ArrayList<Person> array=new ArrayList<Person>();//Ⱥ��Ա�б�
		 //�����û��Ĺ�����
		 public Person(String name, ImageIcon icon,int QQnumber, String motto,int Check) {
			
			this.name = name;
			this.icon = icon;
			this.QQnumber=QQnumber;
			this.motto = motto;
			this.Check=Check;
		}	
		//Ⱥ�Ĺ�����
	    public Person(String name, ImageIcon icon,int QQnumber, String motto,int Check,ArrayList<Person> array) {
			
			this.name = name;
			this.icon = icon;
			this.QQnumber=QQnumber;
			this.motto = motto;
			this.Check=Check;
			this.array=array;
		}
		   public String toString(){
		    	
		    	return name;
		    } 
	}

