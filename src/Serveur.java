import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;


public class Serveur
{
	public static void main(String[] args)
	{
		ServerSocket socketserver;
		Socket connexion;

		Service service;

		int alea;


		try
		{
			socketserver = new ServerSocket(4444);

			System.out.println("Définition du nombre aléatoire...");
			alea = (int) (Math.random() * 999) + 1;
			System.out.println("Nombre aléatoire défini à " + alea + " !");

			while (true)
			{
				System.out.println("\nAttente d'une connexion...");
				connexion = socketserver.accept();
				System.out.println("Connexion d’un client !");

				System.out.println("Affectation d'un service au client...");
				service = new Service(connexion, alea);
				service.start();
				System.out.println("Affectation effectué !");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}