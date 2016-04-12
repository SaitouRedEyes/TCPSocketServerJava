import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

//A classe abre um servidor de socket na porta 7777 e fica aguardando uma conex�o.
//O m�todo accept() fica parado at� que um cliente se conecte ao socket.
//Quando a conex�o � realizada, por quest�es de arquitetura foi criada outra 
//thread para tratar a conex�o.
//Desta forma, o servidor pode tratar diversas conex�es simultaneamente.
public class TCPSocket 
{
	private static final int DOOR = 7777;
	
	public static void main(String[] args) throws IOException
	{
		ServerSocket serverSocket = new ServerSocket(DOOR);
		System.out.println("Socket aberto na porta: " + DOOR);
		
		while(true)
		{
			try
			{
				System.out.println("waiting...");
				Socket socket = serverSocket.accept();
				
				System.out.println("Conected!!!");
				new TCPSocketThread(socket).start();
			}
			catch(SocketException e)
			{
				System.out.println("ERROR: " + e.getMessage());
			}
		}		
	}
}