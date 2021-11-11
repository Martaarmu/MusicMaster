package com.martaarjona.musicMaster;


import java.io.IOException;

import com.martaarjona.MariaDB.ListReproductionDAO;
import com.martaarjona.MariaDB.UserDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {
	 	@FXML
	    private TextField txtUsername;

	    @FXML
	    private PasswordField txtPassword;

	    @FXML
	    private Button btn_inicioSesion;

	    @FXML
	    private Button btn_registrarse;
	    
	    private UserDAO user=new UserDAO();
	    private ObservableList<UserDAO> users =FXCollections.observableArrayList();
	    
	    @FXML
	    void inicio(ActionEvent event) {
	    	
	    	this.users=FXCollections.observableList(user.getUsers());
	    	
	    	UserDAO u = new UserDAO();
	    	u.setName(this.txtUsername.getText());
	    	u.setPassword(this.txtPassword.getText());
	    	
	    	System.out.println(u);
	    	System.out.println(users);
	    	
	   
	    	
	    	
	    	 if(u.getPassword(u)) {
	   
					try {
						
						Stage stage = (Stage) this.btn_inicioSesion.getScene().getWindow();
			    		stage.close();
						
						PrimaryController.iniAttributtes(u);
						FXMLLoader loader2 = new FXMLLoader(getClass().getResource("primary.fxml"));
						Parent root2 = loader2.load();
						PrimaryController controlador2 = loader2.getController();
						Scene scene2 = new Scene(root2);
						Stage stage2 = new Stage();
						stage2.initModality(Modality.APPLICATION_MODAL);
						stage2.setScene(scene2);
						stage2.showAndWait();
						
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
	    	 }else {
	    		 Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Error");
					alert.setContentText("Contraseña incorrecta");
					alert.showAndWait();
	    	 }
	    	
	    	

	    }
	    
	    /**
	     * Método que abre otra pantalla para crear un nueo usuario
	     * @param event
	     */
	    @FXML
	    void registro(ActionEvent event) {
	    	
	    	try {
	    		FXMLLoader loader = new FXMLLoader(getClass().getResource("registro.fxml"));
				Parent root = loader.load();
				RegistroController controlador = loader.getController();
				controlador.iniAttributtes(users);
				Scene scene = new Scene(root);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(scene);
				stage.showAndWait();

				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
			
	    }
	    
	   
		
	    
	    

}
