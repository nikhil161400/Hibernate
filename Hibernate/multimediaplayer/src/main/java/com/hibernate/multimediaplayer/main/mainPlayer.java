package com.hibernate.multimediaplayer.main;

import com.hibernate.multimediaplayer.player.player;

public class mainPlayer {
	
	static boolean loops=true;

	public static void main(String[] args) {
		while(cycle(loops)) {
			player.functions();
		}
		System.out.println("Exited");
	}
	
	
	public static boolean cycle(boolean loop) {
		loops=loop;
		return loops;
	}
}

