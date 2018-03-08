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
	 * 服务器的创建
	 */
	private ServerSocket server;
	public static List<Person> list=new ArrayList<Person>();
	public static List<Socket> lists=new ArrayList<Socket>();
	
	//创建服务器,port为创建的端口和QQ用户的初始化
	public void CreatServer(int port){
		try {
			server=new ServerSocket(port);
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//处理连接到服务器的客户方法,每个连接一个用户，启动一个用户单独的线程
	public void HandleClient() throws IOException{
		Socket client=server.accept();
		lists.add(client);
		ServerThread s=new ServerThread(client);
		
		s.start();
	}
	//初始化QQ用户
	public void initialize(){
	Person p1=new Person("YMT", new ImageIcon("picture/YMT.jpg"), 1, "再见，不见，昨天……",2);
	Person p2=new Person("Went", new ImageIcon("picture/Went.jpg"), 2, "dream/这个世界注定要有人比你更强,更好,不能因此而放弃奔跑.跑不过别人,但一定要跑过昨天的自己!",2);
	Person p3=new Person("Will", new ImageIcon("picture/Will.jpg"), 3, "A day without discovery . . . isn’t much of a day.",2);
	      list.add(p1);
	      list.add(p2);
	      list.add(p3);

	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Server server=new Server();
		server.CreatServer(8889);
		//循环启动客户处理方法，不断检测是否有用户接入
		while(true){
			server.HandleClient();
		}
	}

}
