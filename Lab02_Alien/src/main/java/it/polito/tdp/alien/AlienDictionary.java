package it.polito.tdp.alien;

import java.util.*;

public class AlienDictionary {
	
	private LinkedHashMap<String, Word> parole;
	
	public AlienDictionary() {
		this.parole = new LinkedHashMap<>();
	}
	
	public void addWord(String alienWord, String translation) {
		if (parole.get(alienWord)==null) {
			parole.put(alienWord, new Word(alienWord, translation));
		}
		else {
			parole.get(alienWord).setTranslation(translation);
		}
	}
	
	public List<String> translateWord(String alienWord) {
		return parole.get(alienWord).getTranslation();
	}

	public LinkedHashMap<String, Word> getParole() {
		return parole;
	}

}
