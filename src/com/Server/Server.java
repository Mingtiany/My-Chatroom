package com.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.Client.Person;

public class Server {

	/**
	 * �������Ĵ���
	 */
	private ServerSocket server;
	public static List<Person> list=new ArrayList<Person>();
	public static List<Socket> lists=new ArrayList<Socket>();
	
	//����������,portΪ�����Ķ˿ں�QQ�û��ĳ�ʼ��
	public void CreatServer(int port){
		try {
			server=new ServerSocket(port);
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�������ӵ��������Ŀͻ�����,ÿ������һ���û�������һ���û��������߳�
	public void HandleClient() throws IOException{
		Socket client=server.accept();
		lists.add(client);
		ServerThread s=new ServerThread(client);
		
		s.start();
	}
	//��ʼ��QQ�û�
	public void initialize(){
	Person p1=new Person("YMT", new ImageIcon("picture/YMT.jpg"), 1, "�ټ������������졭��",2);
	Person p2=new Person("Went", new ImageIcon("picture/Went.jpg"), 2, "dream/�������ע��Ҫ���˱����ǿ,����,������˶���������.�ܲ�������,��һ��Ҫ�ܹ�������Լ�!",2);
	Person p3=new Person("Will", new ImageIcon("picture/Will.jpg"), 3, "A day without discovery . . . isn��t much of a day.",2);
	      list.add(p1);
	      list.add(p2);
	      list.add(p3);

	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Server server=new Server();
		server.CreatServer(8889);
		//ѭ�������ͻ������������ϼ���Ƿ����û�����
		while(true){
			server.HandleClient();
		}
	}

}
