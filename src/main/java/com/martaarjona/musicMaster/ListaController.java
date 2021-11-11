package com.martaarjona.musicMaster;

import com.martaarjona.MariaDB.ListReproductionDAO;
import com.martaarjona.MariaDB.UserDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ListaController {
	
	 @FXML
	    private TextField txt_name;
	 
	 @FXML
	    private Button btn_crear;

	    
	private static UserDAO user;
	/**
	 * Inicializa la escena
	 * @param users
	 */
	public static void iniAttributtes(UserDAO u) {
		user=u;
	}
	
	@FXML
    void addLista(ActionEvent event) {
		
		ListReproductionDAO l = new ListReproductionDAO();
		l.setName(this.txt_name.getText());
		l.setCreator(user);
		l.save();
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Lista creada con éxito");
		alert.showAndWait();
		
		Stage stage = (Stage) this.btn_crear.getScene().getWindow();
		stage.close();
		
    }
}
