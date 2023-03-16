package com.hibernate.multimediaplayer.player;

import java.util.Random;
import java.util.Scanner;

import com.hibernate.multimediaplayer.dao.playerDAO;
import com.hibernate.multimediaplayer.main.mainPlayer;

public class player {
	public static void functions() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("1.Play Songs\n"
						 + "2.Add/Remove Songs\n"
						 + "3.Update Songs\n"
						 + "4.Display All Songs\n"
						 + "5.Exit");
		int choiceMenu=scanner.nextInt();
		switch (choiceMenu) {
		case 1:
			System.out.println("1.Play All Songs\n"
							 + "2.Play Random Songs\n"
							 + "3.Select Song\n"
							 + "4.Go Back");
			int choicePlaying=scanner.nextInt();
			switch (choicePlaying) {
			case 1:
				playerDAO.playAllSongs();
				break;
			case 2:
				Random random = new Random();
				playerDAO.playSong(random.nextInt(playerDAO.numberOf()));
				break;
			case 3:
				System.out.println("Enter the Song number you want to play");
				int songNumber=scanner.nextInt();
					playerDAO.playSong(songNumber);
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid Input\n Please try again\n");
				break;
			}
			break;
			
			
		case 2:{
			System.out.println("1.Add Songs\n"
							 + "2.Remove Songs\n"
							 + "3.Go Back");
			int choiceAddRemove =scanner.nextInt();
			switch (choiceAddRemove) {
			case 1:
				playerDAO.addSongs();
				break;
			case 2:
				playerDAO.displaySongs();
				playerDAO.removeSongs();
				break;
			case 3:
				break;
			
			default:
				System.out.println("Invalid Input\n Please try again\n");
				break;
			}
			break;
		}
		
		case 3:
			playerDAO.displaySongs();
			System.out.println("Enter the number of you want to update");
			int songNum=scanner.nextInt();
			System.out.println("1.Update Song Name\n"
							 + "2.Update Singer Name\n"
							 + "3.Update Duration");
			int updateSongDetail=scanner.nextInt();
			switch (updateSongDetail) {
			case 1:
				playerDAO.updateSongName(songNum);
				break;
			case 2:
				playerDAO.updateSingerName(songNum);
				break;
			case 3:
				playerDAO.updateSongDuration(songNum);
				break;
			default:
				System.out.println("Invalid Input\n Please try again");
				break;
			}
			break;
			
		case 4:
			playerDAO.displaySongs();
			break;
			
		case 5:
			mainPlayer.cycle(false);
			break;
		
		default:
			System.out.println("Invalid Input\n Please try again");
			break;
		}if(choiceMenu != 5) {
			functions();
		}
		scanner.close();
		}
}

