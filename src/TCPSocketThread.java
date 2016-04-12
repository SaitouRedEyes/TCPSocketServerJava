import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// Essa classe abre a InputStream e OutputStream do socket, chama o servi�o respons�vel pela requisi��o feita do cliente, 
// al�m de ser respons�vel pela leitura e escrita no canal de comunica��o com o socket-cliente. 
public class TCPSocketThread extends Thread
{
	private final Socket socket;	
	
	public TCPSocketThread(Socket s)
	{
		this.socket = s;		
	}
	
	public void run()
	{
		try
		{
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			Calculator calc = new Calculator();
			
			out.writeFloat(calc.Sum(in.readFloat(), in.readFloat()));
			
			out.close();
			in.close();	
			socket.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}			
}