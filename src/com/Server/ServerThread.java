package com.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread{

	private Socket Client;//�����Ϸ�������Socket����
	private DataOutputStream out=null;//�����
	private DataInputStream in=null;//������
    private int Style;//��Ϣ����
    private int Senter;//��Ϣ������QQ��
    private int Receiver;//��Ϣ������QQ��
    private int length;//��Ϣ���ݳ���
	private int isEnd;//�����ж��Ƿ������һ����
	private int isStart;//�����ж��Ƿ������һ����

	public  ServerThread(Socket Client) throws IOException{
		this.Client=Client;
		in=new DataInputStream(Client.getInputStream());//��ȡ������
	}
	public void run(){
		
		//��Ҫһ������ѭ�������ַ�����һֱ����
		while(true){
			try {
				Style=in.read();//��ͨ��Э���ȡ
				if(Style==11||Style==12){//Ⱥ�����ֻ�ͼƬ
					
				Senter=in.read();
				int Receiverlen=in.read();
				byte[] by=new byte[Receiverlen];
				in.read(by);
				length=in.readInt();
				byte[] b=new byte[length];
				in.read(b);
				isEnd=in.read();
				isStart=in.read();
				String n=Server.list.get(Senter-1).name;//�����ߵ��ǳ�
				for(int j=0;j<Server.lists.size();j++){
					if(Server.list.get(j).QQnumber!=Senter){//�ж��Ƿ����Լ�
						out=new DataOutputStream(Server.lists.get(j)
								.getOutputStream());//���λ�ȡ�����
						out.write(Style);//��ͨ��Э�鷢��
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
				
				if(Style==21||Style==22){//˽�����ֻ�ͼƬ
					Senter=in.read();
					Receiver=in.read();
					length=in.readInt();
					byte[] b=new byte[length];
					in.read(b);
					isEnd=in.read();
					isStart=in.read();
					String n=Server.list.get(Senter-1).name;//�����ߵ��ǳ�
					for(int j=0;j<Server.lists.size();j++){
						if(Server.list.get(j).QQnumber==Receiver){//�ж��Ƿ��ǽ�����
							out=new DataOutputStream(Server.lists.get(j)
									.getOutputStream());//��ȡ�����
							out.write(Style);//��ͨ��Э�鷢��
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
