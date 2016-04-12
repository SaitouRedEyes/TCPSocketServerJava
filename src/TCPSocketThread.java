import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

// Essa classe abre a InputStream e OutputStream do socket, chama o serviço responsável pela requisição feita do cliente, 
// além de ser responsável pela leitura e escrita no canal de comunicação com o socket-cliente. 
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