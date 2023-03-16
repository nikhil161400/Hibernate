package com.hibernate.multimediaplayer.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "songs")

public class songsDTO {
	@Id
	private int Id;
	private String Name;
	private String Singer;
	private double Duration;
	@Override
	public String toString() {
		return Id + ".Song=" + Name + "\t|Singer=" + Singer + "\t|Duration=" + Duration;
	}
	
	
}
