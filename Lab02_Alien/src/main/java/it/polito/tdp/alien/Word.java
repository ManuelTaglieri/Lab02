package it.polito.tdp.alien;

import java.util.*;

public class Word {
	
	private String alienWord;
	private List<String> translation;
	
	public Word(String alienWord, String translation) {
		super();
		this.alienWord = alienWord;
		this.translation = new LinkedList<String>();
		this.translation.add(translation);
	}

	public List<String> getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation.add(translation);
	}

	public String getAlienWord() {
		return alienWord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alienWord == null) ? 0 : alienWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (alienWord == null) {
			if (other.alienWord != null)
				return false;
		} else if (!alienWord.equals(other.alienWord))
			return false;
		return true;
	}
	
	
	
	

}
