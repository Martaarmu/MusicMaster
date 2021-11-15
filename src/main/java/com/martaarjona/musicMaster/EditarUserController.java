package com.martaarjona.musicMaster;

import java.util.List;

import com.martaarjona.MariaDB.ListReproductionDAO;
import com.martaarjona.MariaDB.UserDAO;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarUserController {

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtCorreo;

	@FXML
	private TextField txtContrasena;

	@FXML
	private Button btnEditar;

	private static UserDAO u;
	private ObservableList<UserDAO> users;

	/**
	 * Inicializa los atributos de la escena
	 * @param u
	 */

	public static void iniAttributtes(UserDAO user) {
		u = user;
	}
	/**
	 * Inicializa la escena
	 */

	@FXML
	public void initialize() {
		this.users = FXCollections.observableList(u.getUsers());
		System.out.println(u);
		System.out.println(users);
		this.txtNombre.setText(u.getName());
		this.txtCorreo.setText(u.getEmail());
		this.txtContrasena.setText(u.getPassword());

	}

	/**
	 * Método para actualizar el usuario
	 * 
	 * @param event
	 */
	@FXML
	void editUser(ActionEvent event) {

		if (this.u != null) {
			this.u.setName(this.txtNombre.getText());
			this.u.setEmail(this.txtCorreo.getText());
			this.u.setPassword(this.txtContrasena.getText());
			u.edit();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Se ha modificado conrrectamente");
			alert.showAndWait();

			Stage stage = (Stage) this.btnEditar.getScene().getWindow();
			stage.close();

		}
	}

}
