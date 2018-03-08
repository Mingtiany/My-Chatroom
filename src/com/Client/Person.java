package com.Client;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.ImageIcon;
//人物类
public class Person {
	
		 public String name;//人物昵称
		 public ImageIcon icon;//头像
		 public int QQnumber;//QQ号
		 public String motto;//签名
		 public int Check;//检测数字，用于检测是否是群 1-群 2-个人用户
		 public ArrayList<Person> array=new ArrayList<Person>();//群成员列表
		 //个人用户的构造器
		 public Person(String name, ImageIcon icon,int QQnumber, String motto,int Check) {
			
			this.name = name;
			this.icon = icon;
			this.QQnumber=QQnumber;
			this.motto = motto;
			this.Check=Check;
		}	
		//群聊构造器
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

