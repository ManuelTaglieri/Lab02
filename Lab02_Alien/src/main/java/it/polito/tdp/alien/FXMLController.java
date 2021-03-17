package it.polito.tdp.alien;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private Button btnTranslate;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;
    
    private AlienDictionary dizionario;
    private boolean pieno;

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtParola.clear();
    	txtResult.clear();

    }

    @FXML
    void doTranslate(ActionEvent event) {
    	
    	String parola = txtParola.getText();

    	if (parola.matches("[a-zA-Z\s?]+")) {
    		parola = parola.toLowerCase();
    		String[] parole = parola.split(" ");
    		if (parole.length==1 && pieno) {
    			if (parole[0].contains("?")) {
    				parole [0] = parole[0].replaceAll("\\?", ".");
    				LinkedHashMap<String, Word> voci = dizionario.getParole();
    				txtResult.appendText("Ecco le possibili corrispondenze con la parola incognita cercata:\n");
    				for (Word w : voci.values()) {
    					if (w.getAlienWord().matches(parole[0])) {
    						List<String> traduzione = dizionario.translateWord(w.getAlienWord());
    						if (traduzione==null) {
    	    					txtResult.appendText("La traduzione del termine " + w.getAlienWord() +" non è ancora presente.\n");
    	    					return;
    	    				}
    	    				txtResult.appendText("La traduzione della parola " + w.getAlienWord() + " é:\n");
    	    				String[] traduzioni = new String[traduzione.size()];
    	    				traduzioni = traduzione.toArray(traduzioni);
    	    				for (String t : traduzioni) {
    	    					txtResult.appendText(t + ".\n");
    	    				}
    					}
    				}
    			}
    			else {
    				List<String> traduzione = dizionario.translateWord(parole[0]);
    				if (traduzione==null) {
    					txtResult.appendText("La traduzione del termine " + parole[0] +" non è ancora presente.\n");
    					return;
    				}
    				txtResult.appendText("La traduzione della parola " + parole[0] + " é:\n");
    				String[] traduzioni = new String[traduzione.size()];
    				traduzioni = traduzione.toArray(traduzioni);
    				for (String t : traduzioni) {
    					txtResult.appendText(t + ".\n");
    				}
    			}
    		}
    		else if (parole.length==2) {
    			if (!parola.contains("?")) {
    			dizionario.addWord(parole[0], parole[1]);
    			txtResult.appendText("La parola " + parole[0] + " é stata correttamente inserita nel dizionario con il significato di " + parole[1] +".\n");
    			this.pieno = true;
    			}
    			else {
    				txtResult.appendText("ERRORE: carattere ? consentito solo in ricerca.");
    			}
    		}
    		else if (!pieno) {
    			txtResult.appendText("Inserisci almeno una parola in dizionario prima di poter tradurre.\n");
    		}
    		else {
    			txtResult.appendText("ERRORE: inserisci al massimo 2 parole.\n");
    		}
    	}
    	else {
    		txtResult.appendText("ERRORE: consentiti solo caratteri di testo e spazi.\n");
    		return;
    	}
    }

    @FXML
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTranslate != null : "fx:id=\"btnTranslate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        
        this.dizionario = new AlienDictionary();
        this.pieno = false;
    }
}
