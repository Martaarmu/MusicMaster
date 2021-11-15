package com.martaarjona.musicMaster;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.martaarjona.MariaDB.DAOExcepcion;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

	@FXML
	private Button btnAdd;
	@FXML
	private Button btnBorrar;
	@FXML
	private Button btn_addList;
	@FXML
	private Button btn_todas;
	@FXML
	private Button btn_borrarlista;

	@FXML
	private Button btn_actualizar;
	@FXML
	private Button btn_des_suscribe;
	@FXML
	private Button btn_reproducir;
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
	private TableColumn<ListReproduction, String> col_nReprod;
	
	@FXML
	private Hyperlink btn_borrar;
	@FXML
	private Hyperlink btn_editarUser;

	@FXML
	private TableView<ListReproductionDAO> tblSuscripciones;

	@FXML
	private TableColumn<ListReproductionDAO, String> colId_suscripcion;

	@FXML
	private TableColumn<ListReproductionDAO, String> colNombre_suscripcion;

	@FXML
	private TableColumn<ListReproductionDAO, String> colCreador_suscripcion;
	@FXML
	private TableColumn<ListReproduction, String> col_nReprod_suscripcion;

	private ObservableList<ListReproductionDAO> lists;
	private ObservableList<SongDAO> songs;

	private ObservableList<UserDAO> users;
	private static UserDAO user;
	private static ListReproductionDAO list;
	private static List<ListReproductionDAO> mislistas;

	/**
	 * Inicializa la escena
	 * 
	 * @param users
	 */
	public static void iniAttributtes(UserDAO u) {
		user = u;
	}

	/**
	 * Inicializa los atributos de la escena
	 * 
	 * @param users
	 * @param u
	 */

	public void iniAttributtes(ObservableList<UserDAO> us, UserDAO u) {
		this.users = us;
		user = u;

	}

	public static void iniAttributtes(List<ListReproductionDAO> listas) {
		mislistas = listas;
	}

	/**
	 * Inicializa la escena
	 * @throws DAOExcepcion 
	 */
	@FXML
	public void initialize() throws DAOExcepcion {
		this.users = FXCollections.observableList(user.getUsers());

		this.colId.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getId() + ""));
		this.colNombre.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getName()));
		this.colCreador.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getCreator().getId() + ""));
		this.col_nReprod.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getnReproduccion() + ""));
		this.tblListas.setItems(FXCollections.observableList(ListReproductionDAO.showbyuser(user)));

		this.colId_suscripcion.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getId() + ""));
		this.colNombre_suscripcion.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getName()));
		this.colCreador_suscripcion
				.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getCreator().getId() + ""));
		this.col_nReprod_suscripcion.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getnReproduccion() + ""));

		this.tblSuscripciones.setItems(FXCollections.observableList(ListReproductionDAO.showbysuscripcion(user)));

		this.tblSuscripciones.refresh();
		this.tblListas.refresh();

	}

	@FXML
	private void deleteUser(ActionEvent event) throws DAOExcepcion {

		if (this.user == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("No hay ningun usuario");
			alert.showAndWait();
		} else {

			Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
			alert1.setHeaderText(null);
			alert1.setTitle("AVISO");
			alert1.setContentText("¿Estas seguro de que quiere eliminar su cuenta?");
			// alert1.showAndWait();
			Optional<ButtonType> result = alert1.showAndWait();
			if (result.get() == ButtonType.OK) {
				// ... user chose OK
				this.users.remove(user);
				user.delete();

				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Info");
				alert.setContentText("Usuario eliminado");
				alert.showAndWait();
				Stage stage = (Stage) this.btn_borrar.getScene().getWindow();
				stage.close();

			} else {
				// ... user chose CANCEL or closed the dialog

				//
			}

		}

	}

	@FXML
	private void editUser(ActionEvent event) {

		try {
			EditarUserController.iniAttributtes(user);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("editarUser.fxml"));
			Parent root = loader.load();
			EditarUserController controlador = loader.getController();
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
	private void seleccionar(MouseEvent event) throws DAOExcepcion {

		ListReproductionDAO l = (ListReproductionDAO) this.tblListas.getSelectionModel().getSelectedItem();
		songs = FXCollections.observableArrayList();
		this.tblCanciones.setItems(FXCollections.observableList(l.getsongsbyid(l.getId())));
		this.colCancion.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getName()));

		ObservableList<SongDAO> items = FXCollections.observableList(l.getsongsbyid(l.getId()));
		this.tblCanciones.setItems(items);

	}

	@FXML
	private void seleccionar1(MouseEvent event) throws DAOExcepcion {

		ListReproductionDAO l1 = (ListReproductionDAO) this.tblSuscripciones.getSelectionModel().getSelectedItem();
		songs = FXCollections.observableArrayList();

		this.tblCanciones.setItems(FXCollections.observableList(l1.getsongsbyid(l1.getId())));
		this.colCancion.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getName()));

		ObservableList<SongDAO> items = FXCollections.observableList(l1.getsongsbyid(l1.getId()));
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
		this.tblListas.refresh();

	}

	@FXML
	void deleteLista(ActionEvent event) throws DAOExcepcion {

		list = (ListReproductionDAO) this.tblListas.getSelectionModel().getSelectedItem();

		if (list == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debes seleccionar una lista");
			alert.showAndWait();
		} else {
			list.delete();

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Lista eliminada con éxito");
			alert.showAndWait();
		}
	}

	@FXML
	void addSong(ActionEvent event) {

		list = (ListReproductionDAO) this.tblListas.getSelectionModel().getSelectedItem();

		if (list == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debes seleccionar una lista");
			alert.showAndWait();
		} else {
			try {
				SecondaryController.iniAttributtes(user, list);
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

	}

	@FXML
	void deleteSong(ActionEvent event) throws DAOExcepcion {

		list = (ListReproductionDAO) this.tblListas.getSelectionModel().getSelectedItem();
		SongDAO s = (SongDAO) this.tblCanciones.getSelectionModel().getSelectedItem();

		if (s == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debes seleccionar una canción");
			alert.showAndWait();
		} else {
			list.removeSong(s, list);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Canción eliminada con éxito");
			alert.showAndWait();
		}
	}

	@FXML
	void actualizar(ActionEvent event) throws DAOExcepcion {
		this.tblListas.setItems(FXCollections.observableList(ListReproductionDAO.showbyuser(user)));
		this.tblListas.refresh();

		this.tblSuscripciones.setItems(FXCollections.observableList(ListReproductionDAO.showbysuscripcion(user)));
		this.tblSuscripciones.refresh();
	}

	@FXML
	void vertodas(ActionEvent event) {

		try {
			TodasListasController.iniAttributtes(user, list);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("todaslistas.fxml"));
			Parent root = loader.load();
			TodasListasController controlador = loader.getController();
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
	void dessubcribe(ActionEvent event) throws DAOExcepcion {

		list = (ListReproductionDAO) this.tblSuscripciones.getSelectionModel().getSelectedItem();

		if (list == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debes seleccionar una lista");
			alert.showAndWait();
		} else {
			user.des_suscribe(user, list);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Lista eliminada con éxito");
			alert.showAndWait();
		}

	}
	
	@FXML
	void reproducir(ActionEvent event) throws DAOExcepcion {
		if((ListReproductionDAO) this.tblListas.getSelectionModel().getSelectedItem()!=null) {
			list=this.tblListas.getSelectionModel().getSelectedItem();
			int nrepBD= nRep(list.getId());
	        nrepBD++;
	        list.reproducir(nrepBD, list);
		}else if((ListReproductionDAO) this.tblSuscripciones.getSelectionModel().getSelectedItem()!=null){
			list=this.tblSuscripciones.getSelectionModel().getSelectedItem();
			int nrepBD= nRep(list.getId());
	        nrepBD++;
	        list.reproducir(nrepBD, list);
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debes seleccionar una lista");
			alert.showAndWait();
		}
		
		
		
	//	list = ;
			
	
	}
	
	private int nRep(int id) throws DAOExcepcion {
		int result;
		ListReproductionDAO l = ListReproductionDAO.showbyid(id);
		result=l.getnReproduccion();
		System.out.println(result);
		return result;
	}

}
