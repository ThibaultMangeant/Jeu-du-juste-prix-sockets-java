import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client
{
	public static void main(String[] args)
	{
		Socket socket;
		BufferedReader br;
		PrintWriter pw;

		Scanner clavier;

		String message;
		String reponse;


		try
		{
			socket = new Socket(InetAddress.getLocalHost(),4444);

			pw = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			clavier = new Scanner(System.in);
			
			// Début du jeu
			message = null;
			while (message == null)
			{
				message = br.readLine();
			}
			System.out.println(message);

			while (!message.contains("Bravo vous avez trouvé N"))
			{
				System.out.print("Entrer un nombre : ");
				reponse = clavier.nextLine();

				pw.println(reponse);
				pw.flush();

				message = null;
				while (message == null)
				{
					message = br.readLine();
				}
				System.out.println(message);
			}
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}