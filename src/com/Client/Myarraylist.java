package com.Client;

import java.util.ArrayList;

public class Myarraylist<E>{

	private Object []a=new Object[0];
	public void add(E e) {//Ôö¼Ó
		Object[]a1=new Object[a.length+1];
		a1[a.length]=e;
		for(int i=0;i<a.length;i++){
			a1[i]=a[i];	
		}
		a=a1;
	
	}
   //É¾³ý
	public void delete(E e) {
		Object[]a1=new Object[a.length-1];
		boolean flag=true;
		for(int i=0;i<a.length;i++){
		if(flag){
			if(a[i]!=e)
			a1[i]=a[i];
			else{
				flag=false;
	     	    continue;
			}
		}	
		a1[i]=a[i+1];
		}
		a=a1;
	}
	
	
	public E get(int num) {
		E e=(E)a[num];
		return e;
	}


	public int size() {
		
		return a.length;
	}
	
	
}
