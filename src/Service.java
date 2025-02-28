import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

public class Service extends Thread
{
	private Socket client;
	private BufferedReader in;
	private PrintWriter    out;

	private int alea;


	public Service(Socket client, int alea)
	{
		try
		{
			this.client = client;

			this.in  = new BufferedReader(new InputStreamReader(client.getInputStream()));
			this.out = new PrintWriter(client.getOutputStream());

			this.alea = alea;
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}


	public void run()
	{
		String message;
		String victoire;

		int    reponse;

		int nbEssai;


		try
		{
			message = null;

			nbEssai = 1;

			this.out.println("prêt");
			this.out.flush();

			// Début du jeu
			while (this.client != null)
			{
				while (message == null)
				{
					message = this.in.readLine();
				}
	
				reponse = Integer.parseInt(message);
				System.out.println("[SERVICE] reponse du client = " + reponse);
	
				if (reponse > this.alea)
				{
					nbEssai++;
					this.out.println("trop grand");
					this.out.flush();
					message = null;
				}
				else if (reponse < this.alea)
				{
					nbEssai++;
					this.out.println("trop petit");
					this.out.flush();
					message = null;
				}
				else
				{
					victoire = "Bravo vous avez trouvé N en " + nbEssai + " essai";
					if (nbEssai > 1) victoire += "s";
					this.out.println(victoire);
					this.out.flush();

					this.client.close();
					message = null;
					this.client = null;
				}
			}

			System.out.println("[SERVICE] Fermeture du service !");
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch (NumberFormatException nfe)
		{
			this.out.println("Veuillez entrer un nombre la prochaine fois !");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
