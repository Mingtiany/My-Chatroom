package com.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread{

	private Socket Client;//连接上服务器的Socket对象
	private DataOutputStream out=null;//输出流
	private DataInputStream in=null;//输入流
    private int Style;//消息类型
    private int Senter;//消息发送者QQ号
    private int Receiver;//消息接收者QQ号
    private int length;//消息内容长度
	private int isEnd;//用于判断是否是最后一部分
	private int isStart;//用于判断是否是最后一部分

	public  ServerThread(Socket Client) throws IOException{
		this.Client=Client;
		in=new DataInputStream(Client.getInputStream());//获取输入流
	}
	public void run(){
		
		//需要一个无限循环，保持服务器一直工作
		while(true){
			try {
				Style=in.read();//按通信协议读取
				if(Style==11||Style==12){//群聊文字或图片
					
				Senter=in.read();
				int Receiverlen=in.read();
				byte[] by=new byte[Receiverlen];
				in.read(by);
				length=in.readInt();
				byte[] b=new byte[length];
				in.read(b);
				isEnd=in.read();
				isStart=in.read();
				String n=Server.list.get(Senter-1).name;//发送者的昵称
				for(int j=0;j<Server.lists.size();j++){
					if(Server.list.get(j).QQnumber!=Senter){//判断是否是自己
						out=new DataOutputStream(Server.lists.get(j)
								.getOutputStream());//依次获取输出流
						out.write(Style);//按通信协议发送
						out.write(n.getBytes().length);
						out.write(n.getBytes());
						out.write(Receiverlen);
						out.write(by);
						out.writeInt(length);
						out.write(b);
						out.write(isEnd);
						out.write(isStart);

					}
				  }
				}
				
				if(Style==21||Style==22){//私聊文字或图片
					Senter=in.read();
					Receiver=in.read();
					length=in.readInt();
					byte[] b=new byte[length];
					in.read(b);
					isEnd=in.read();
					isStart=in.read();
					String n=Server.list.get(Senter-1).name;//发送者的昵称
					for(int j=0;j<Server.lists.size();j++){
						if(Server.list.get(j).QQnumber==Receiver){//判断是否是接收者
							out=new DataOutputStream(Server.lists.get(j)
									.getOutputStream());//获取输出流
							out.write(Style);//按通信协议发送
							out.write(n.getBytes().length);
							out.write(n.getBytes());
							out.writeInt(length);
							out.write(b);
							out.write(isEnd);
							out.write(isStart);
							
						}
						
					}
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
}
