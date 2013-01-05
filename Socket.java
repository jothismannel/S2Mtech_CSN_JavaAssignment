/* Server side 

read the file name and sending the content of the  file to the client */

package jothis.socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server 
{

	static ServerSocket server_socket;
	static DataInputStream dis, dis_file;
	static Socket service_socket;
	static DataOutputStream dos;
	static String path,data;
	static FileInputStream fis;
	static String file_name;
	static int flag = 1,flag2=1;

	public Server(int port) 
	{
		
		try 
		{
			server_socket = new ServerSocket(port);
			
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}

	}//end of constructor

	public static void main(String[] args) 
	{
		
		Server server_side = new Server(6064);				

		/*Server waiting for the connection */
		while (true) 
		{
			System.out.println("Waiting for client....");
			
			try 
			{
				service_socket = server_socket.accept();
			} 
			catch (IOException e) 
			{
				
				e.printStackTrace();
				
			}
			
			if(flag2==1)					//flag2 ensures the file name is read only once
			{
				while(true)
				{
					try 
					{
						dis = new DataInputStream(service_socket.getInputStream());
					
						data=dis.readUTF();	//server reading the file name
					} 
					catch (IOException e2) 
					{
					
						continue;
					}
					flag2=2;
					break;
				}//end of while
			
			}
			
			
			if(flag==1)
			{
				File file=new File("/home/student/"+data);
				try 
				{
					fis=new FileInputStream(file);
				
				} 
				catch (FileNotFoundException e1) 
				{
				
					e1.printStackTrace();
				}
				
				BufferedInputStream bis=new BufferedInputStream(fis);
				dis_file=new DataInputStream(bis);
			
			}
			flag++;
		
			try 
			{
			
				dos = new DataOutputStream(service_socket.getOutputStream());
				
				if(dis_file.available()==0)
				{
					break;
				}
			
				while(dis_file.available()!=0)
				{
					
					dos.writeUTF(dis_file.readLine());
			
				}
			}
		 	catch (IOException e) 
			{
			
				e.printStackTrace();
			}
			break;
		}//end of while 	
	}//end of public void main

}//end of class Server


/*Client Side 

sending the file name and retriving the content of the file from the server

*/
 
package jothis.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



public class Client 
{

	static Socket client_socket;

	public static void main(String[] str) 
	{

		
		try 
		{
			client_socket = new Socket("127.0.0.1", 6064);
			
		} 
		catch (UnknownHostException e1) 
		{
			e1.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		/* client writing the file name to the output stream */		
		while (true) 
		{
			
			try 
			{ 
				DataOutputStream dos_client = new DataOutputStream(client_socket.getOutputStream()); 
				dos_client.writeUTF("text");			//client sending the file named "text"			
				break;
			}
			catch(IOException e)
			{
				continue;
			}
		}
		
		/* client reading the content of the file */		
		while(true)
		{	
			try 
			{
				DataInputStream dis_client = new DataInputStream(client_socket.getInputStream());
				
				System.out.println(dis_client.readUTF());
					
			} 
			catch (IOException e3) 
			{
				break;
			}
		}//end of while

	}
		
}
