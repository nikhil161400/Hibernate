package com.hibernate.multimediaplayer.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.hibernate.multimediaplayer.dto.songsDTO;
import com.hibernate.multimediaplayer.main.mainPlayer;

@SuppressWarnings("unchecked")
public class playerDAO {
	private static EntityManagerFactory factory;
	private static EntityManager manager;
	private static EntityTransaction transaction;
	private static Query query;
	private static String jpqlQuery;
	private static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
	private static BufferedReader reader = new BufferedReader(inputStreamReader);
	private static Scanner scanner = new Scanner(System.in);
	private static songsDTO song;
	private static int sizeOf;
	
	private static void openConnection() {
		
		factory=Persistence.createEntityManagerFactory("songs");
		manager=factory.createEntityManager();
		transaction=manager.getTransaction();
		transaction.begin();
	}
	
	private static void closeConnection() {
		if(factory != null)
			factory.close();
		if(manager != null)
			manager.close();
		if(transaction.isActive())
			transaction.rollback();
	}
	
	public static void addSongs() {
		openConnection();
		System.out.println("Enter the number of Songs you want to add");
		int num=scanner.nextInt();
		while(num-->0){
		try {
			System.out.print("Enter ID:");
			song.setId(scanner.nextInt());
			System.out.print("Enter Song Name:");
			song.setName(reader.readLine());
			System.out.print("Enter Singer Name:");
			song.setSinger(reader.readLine());
			System.out.print("Enter Duration:");
			song.setDuration(scanner.nextDouble());
		} catch (IOException e) {
			e.printStackTrace();
		}
		manager.persist(song);
		transaction.commit();
		}
		System.out.println("Song(s) Added");
	}
	
	public static void removeSongs() {
		System.out.println("Enter the number of song "
				+ "you want to remove");
		int removeSongs=scanner.nextInt();
		
		try {
			openConnection();
			song = manager.find(songsDTO.class, removeSongs);
			manager.remove(song);
			transaction.commit();
		} catch (Exception e) {
			closeConnection();
		}
	}
	
	public static void displaySongs() {
		try {
			openConnection();
			jpqlQuery="from songsDTO";
			query=manager.createQuery(jpqlQuery);
			
			List<songsDTO> songList=query.getResultList();
			
			if (numberOf()==0) {
				System.out.println("Please Add Songs");
			} else {
				System.out.printf("Num\t|Name\t|Singer\t|Duration\n");
				System.out.println("----------------------------------");
				for (songsDTO song : songList) {
					System.out.printf("%d\t|%s\t|%s\t|%2.2f\n",song.getId(),song.getName(),song.getSinger(),song.getDuration());
				}
				transaction.commit();
			}
		} catch (Exception e) {
			closeConnection();
		}
	}
	
	public static void updateSongName(int songNum) {
		try {
			openConnection();
			song=manager.find(songsDTO.class, songNum);
			System.out.println("Enter new Song Name");
			song.setName(reader.readLine());
			manager.persist(song);
			
			transaction.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}
	
	public static void updateSingerName(int songNum) {
		try {
			openConnection();
			song=manager.find(songsDTO.class, songNum);
			System.out.println("Enter new Singer Name");
			song.setSinger(reader.readLine());
			manager.persist(song);
			
			transaction.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}
	
	public static void updateSongDuration(int songNum) {
		try {
			openConnection();
			song=manager.find(songsDTO.class, songNum);
			System.out.println("Enter new Duration");
			song.setDuration(scanner.nextDouble());
			manager.persist(song);
			transaction.commit();
		} finally {
			closeConnection();
		}
	}
	
	public static void exit() {
		System.out.println("Please wait..!");
		mainPlayer.cycle(false);
	}
	
	public static void playAllSongs() {
		try {
			int i=0;
			openConnection();
			jpqlQuery="from songsDTO";
			query=manager.createQuery(jpqlQuery);
	
			List<songsDTO> songList=query.getResultList();
			for (songsDTO song : songList) {
				if(!songList.isEmpty()) {
					if(i==0) {
						i=2;
						System.out.println("Playing: "+song.getName()+" by "+song.getSinger()+" \tDuration "+song.getDuration());
					}
					else {
						System.out.println("Next Song: "+song.getName()+" by "+song.getSinger()+" \tDuration "+song.getDuration());
					}
				}else {
					System.out.println("Please Add Songs");
				}
			}
			transaction.commit();
		} catch (Exception e) {
			closeConnection();
		}
	}
	
	public static void playSong(int playSong) {
	
		try {
			openConnection();
			song = manager.find(songsDTO.class, playSong);
			
			System.out.println("Playing "+song.getName()+" by "+song.getSinger()+" \nDuration "+song.getDuration());
			
			transaction.commit();
			
		} catch (Exception e) {
			closeConnection();
		}
	}
	
	public static int numberOf() {
		try {
			openConnection();
			jpqlQuery="from songsDTO";
			query=manager.createQuery(jpqlQuery);
			
			List<songsDTO> songList=query.getResultList();
			sizeOf=songList.size();
			
			transaction.commit();
		}finally {
			closeConnection();
		}
		return sizeOf;
	}
}
