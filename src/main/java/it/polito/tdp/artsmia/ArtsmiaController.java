package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Adiacenze;
import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnArtistiConnessi;

    @FXML
    private Button btnCalcolaPercorso;

    @FXML
    private ComboBox<String> boxRuolo;

    @FXML
    private TextField txtArtista;

    @FXML
    private TextArea txtResult;

    @FXML
    void doArtistiConnessi(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola artisti connessi");
    	if(this.model.grafoCreato())
    	{
    		for(Adiacenze a : this.model.getCollegati(boxRuolo.getValue()))
    		{
    			txtResult.appendText(a.toString());
    		}
    		
    	}
    	else
    	{
    		txtResult.appendText("Bisogna prima creare il grafo!");
    	}
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola percorso");
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	if(!(boxRuolo.getValue()==null))
    	{
    		this.model.creaGrafo(boxRuolo.getValue());
    		txtResult.appendText("Grafo creato con successo\n");
    		txtResult.appendText("#Vertici: "+this.model.getNVertici()+"\n");
    		txtResult.appendText("#Archi: "+this.model.getNArchi()+"\n");
    	}
    	else
    	{
    		txtResult.appendText("Bisogna selezionare un ruolo!");
    	}
    	
    }

    public void setModel(Model model) {
    	this.model = model;
    	for(String s: this.model.getRuolo())
    	{
    		boxRuolo.getItems().add(s);
    	}
    }

    
    @FXML
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnArtistiConnessi != null : "fx:id=\"btnArtistiConnessi\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert boxRuolo != null : "fx:id=\"boxRuolo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtArtista != null : "fx:id=\"txtArtista\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }
}
