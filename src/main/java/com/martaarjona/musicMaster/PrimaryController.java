package com.martaarjona.musicMaster;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import com.martaarjona.MariaDB.ListReproductionDAO;
import com.martaarjona.MariaDB.SongDAO;
import com.martaarjona.MariaDB.UserDAO;
import com.martaarjona.model.ListReproduction;
import com.martaarjona.model.Song;
import com.martaarjona.model.User;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController{
	
	
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnBorrar;
	@FXML
	private Button btn_addList;
	
	@FXML
	private TableView<ListReproductionDAO> tblListas;
	@FXML
	private TableView<SongDAO> tblCanciones;
	
	@FXML
	private TableColumn<ListReproduction, String> colId;
	@FXML
	private TableColumn<ListReproduction, String> colNombre;
	@FXML
	private TableColumn<ListReproduction, String> colCreador;
	@FXML
	private TableColumn<Song, String> colCancion;
	 @FXML
	private Hyperlink btn_borrar;
	 @FXML
		private Hyperlink btn_editarUser;
	
	
	
	private ObservableList<ListReproductionDAO> lists;
	private ObservableList<SongDAO> songs;
	
	private ObservableList<UserDAO> users;
	private static UserDAO user;
	private static ListReproductionDAO list;
	
	/**
	 * Inicializa la escena
	 * @param users
	 */
	public static void iniAttributtes(UserDAO u) {
		user=u;
	}
	/**
	 * Inicializa los atributos de la escena
	 * @param users
	 * @param u
	 */
	
	public void iniAttributtes(ObservableList<UserDAO> users, UserDAO u) {
		this.users=users;
		user=u;
		//this.txt_nombre.setText(u.getName());
		//this.txt_correo.setText(u.getEmail());
		//this.txt_contrasena.setText(u.getPassword());
	}
	
	/**
	 * Inicializa la escena
	 */
	@FXML
	public void initialize() {
		System.out.println(user);
		
		this.colId.setCellValueFactory(list->new SimpleStringProperty(list.getValue().getId()+""));
		this.colNombre.setCellValueFactory(list->new SimpleStringProperty(list.getValue().getName()));
		this.colCreador.setCellValueFactory(list->new SimpleStringProperty(list.getValue().getCreator().getId()+""));
		
		
		
		this.tblListas.setItems(FXCollections.observableList(ListReproductionDAO.showAll()));
		this.tblListas.refresh();
		
	}
	
	@FXML
	private void deleteUser(ActionEvent event) {
		
		if(this.user==null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No hay ningun usuario");
			alert.showAndWait();
		}else {
			//this.users.remove(user);
			user.delete();
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Usuario eliminado");
			alert.showAndWait();
		}
		Stage stage = (Stage) this.btn_borrar.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void editUser (ActionEvent event) {
		
		
		try {
			RegistroController.iniAttributtes(users,user);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("registro.fxml"));
			Parent root = loader.load();
			RegistroController controlador = loader.getController();
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
	
	@FXML
	private void seleccionar(MouseEvent event) {
		
		ListReproductionDAO l = (ListReproductionDAO) this.tblListas.getSelectionModel().getSelectedItem();
		songs = FXCollections.observableArrayList();
		this.tblCanciones.setItems(FXCollections.observableList(l.getsongsbyid(l.getId())));
		this.colCancion.setCellValueFactory(list->new SimpleStringProperty(list.getValue().getId()+""));
		//this.colCancion.setCellValueFactory(new PropertyValueFactory("name"));
		
		ObservableList<SongDAO> items = FXCollections.observableList(l.getsongsbyid(l.getId()));
		this.tblCanciones.setItems(items);
		
	}
	
	@FXML
	void addList(ActionEvent event) {

		
		try {
			ListaController.iniAttributtes(user);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("lista.fxml"));
			Parent root = loader.load();
			ListaController controlador = loader.getController();
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
	
	@FXML
	 void addSong(ActionEvent event) {
		
		
		list = (ListReproductionDAO) this.tblListas.getSelectionModel().getSelectedItem();
		
		try {
			SecondaryController.iniAttributtes(user,list);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("secondary.fxml"));
			Parent root = loader.load();
			SecondaryController controlador = loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@FXML
	void deleteSong(ActionEvent event) {
		//list.getId();
		list = (ListReproductionDAO) this.tblListas.getSelectionModel().getSelectedItem();
		SongDAO s = (SongDAO) this.tblCanciones.getSelectionModel().getSelectedItem();
	
		//System.out.println(list.getsongsbyid(list.getId()));
		//System.out.println(s);
		System.out.println(list.getsongsbyid(list.getId()));
		list.removeSong(s,list);
		
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Canción eliminada con éxito");
		alert.showAndWait();
	}
	
	
	
	
   
}
