package com.martaarjona.musicMaster;

import java.util.List;

import com.martaarjona.MariaDB.DAOExcepcion;
import com.martaarjona.MariaDB.UserDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroController {

	@FXML
	private TextField txt_nombre;

	@FXML
	private TextField txt_correo;

	@FXML
	private PasswordField txt_contrasena;

	@FXML
	private Button btn_registrar;

	private UserDAO user;
	private static UserDAO u;
	private static List<UserDAO> users;

	/**
	 * Inicializa la escena
	 * 
	 * @param users2
	 */
	public void iniAttributtes(List<UserDAO> users2) {
		this.users = users2;
	}

	/**
	 * Inicializa los atributos de la escena
	 * 
	 * @param users
	 * @param u
	 */

	public static void iniAttributtes(List<UserDAO> us, UserDAO user) {
		users = us;
		u = user;
	}

	/**
	 * Inserta o modifica en la BD un usuario
	 * 
	 * @param event
	 * @throws DAOExcepcion 
	 */
	@FXML
	void addUser(ActionEvent event) throws DAOExcepcion {

		String nombre = this.txt_nombre.getText();
		String correo = this.txt_correo.getText();
		String contrasena = this.txt_contrasena.getText();
		UserDAO u = new UserDAO(nombre, correo, contrasena);
		users = FXCollections.observableList(u.getUsers());
		if(nombre.isEmpty() || correo.isEmpty()|| contrasena.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Complete todos los campos");
			alert.showAndWait();
		}else {
			this.user = u;
			users.add(u);
			u.save();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Se ha añadido conrrectamente");
			alert.showAndWait();
			Stage stage = (Stage) this.btn_registrar.getScene().getWindow();
			stage.close();
		}
		
	}

	

	/**
	 * Devuelve un usuario
	 * 
	 * @return
	 */
	public UserDAO getUser() {
		return user;
	}

}
