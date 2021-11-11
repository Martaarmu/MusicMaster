package com.martaarjona.musicMaster;

import java.util.List;

import com.martaarjona.MariaDB.ListReproductionDAO;
import com.martaarjona.MariaDB.SongDAO;
import com.martaarjona.MariaDB.UserDAO;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TodasListasController {
	
	@FXML
    private Button btn_suscribe;
	 @FXML
	    private TableView<ListReproductionDAO> tbl_listas;

	    @FXML
	    private TableColumn<ListReproductionDAO, String> colId;

	    @FXML
	    private TableColumn<ListReproductionDAO, String> colNombre;

	    @FXML
	    private TableColumn<ListReproductionDAO, String> colCreador;

	    @FXML
	    private TableView<SongDAO> tbl_canciones;

	    @FXML
	    private TableColumn<SongDAO, String> colCanciones;
	    
		private ObservableList<SongDAO> songs;

	private static UserDAO user;
    private static ListReproductionDAO list;
    public static List<ListReproductionDAO> mislistas;
    
    /**
	 * Inicializa la escena
	 * @param users
	 */
	public static void iniAttributtes(UserDAO u) {
		user=u;
	}
    /**
	 * Inicializa la escena
	 * @param users
	 */
	public static void iniAttributtes(UserDAO u, ListReproductionDAO l) {
		user=u;
		list=l;
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
		
		
		
		this.tbl_listas.setItems(FXCollections.observableList(ListReproductionDAO.showAll()));
		this.tbl_listas.refresh();
		
	}
	
	@FXML
	private void seleccionar(MouseEvent event) {
		
		ListReproductionDAO l = (ListReproductionDAO) this.tbl_listas.getSelectionModel().getSelectedItem();
		songs = FXCollections.observableArrayList();
		this.tbl_canciones.setItems(FXCollections.observableList(l.getsongsbyid(l.getId())));
		this.colCanciones.setCellValueFactory(list->new SimpleStringProperty(list.getValue().getId()+""));
		//this.colCancion.setCellValueFactory(new PropertyValueFactory("name"));
		
		ObservableList<SongDAO> items = FXCollections.observableList(l.getsongsbyid(l.getId()));
		this.tbl_canciones.setItems(items);
		
	}
	@FXML
	private void suscribirse(ActionEvent event) {
		
		ListReproductionDAO l = (ListReproductionDAO) this.tbl_listas.getSelectionModel().getSelectedItem();
		System.out.println(l);
    	user.suscribe(user, l);
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Info");
		alert.setContentText("Suscrito con éxito");
		alert.showAndWait();
    	
    	Stage stage = (Stage) this.btn_suscribe.getScene().getWindow();
		stage.close();
		
		
	}
	
	
}
