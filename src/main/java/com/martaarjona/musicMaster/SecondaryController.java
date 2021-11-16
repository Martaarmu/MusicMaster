package com.martaarjona.musicMaster;

import java.io.IOException;
import java.sql.Time;

import com.martaarjona.MariaDB.DAOExcepcion;
import com.martaarjona.MariaDB.DiskDAO;
import com.martaarjona.MariaDB.ListReproductionDAO;
import com.martaarjona.MariaDB.SongDAO;
import com.martaarjona.MariaDB.UserDAO;
import com.martaarjona.model.Disk;
import com.martaarjona.model.ListReproduction;
import com.martaarjona.model.Song;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class SecondaryController {
	@FXML
	private TableView<Song> tblCanciones;

	@FXML
	private TableColumn<Song, String> colId;

	@FXML
	private TableColumn<Song, String> colNombre;

	@FXML
	private TableColumn<Song, String> colDuracion;

	@FXML
	private TableColumn<Song, String> colGenero;

	@FXML
	private TableColumn<Song, String> colDisco;
	@FXML
	private Button btn_info;
	@FXML
	private Button btnAdd;

	private static UserDAO user;
	private static ListReproductionDAO list;

	/**
	 * Inicializa la escena
	 * 
	 * @param users
	 */
	public static void iniAttributtes(UserDAO u, ListReproductionDAO l) {
		user = u;
		list = l;
	}

	@FXML
	public void initialize() throws DAOExcepcion {
		System.out.println(user);
		System.out.println(list);

		this.colId.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getId() + ""));
		this.colNombre.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getName()));
		this.colDuracion.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getDuration() + ""));
		this.colGenero.setCellValueFactory(new PropertyValueFactory("genero"));
		this.colDisco.setCellValueFactory(list -> new SimpleStringProperty(list.getValue().getId() + ""));

		this.tblCanciones.setItems(FXCollections.observableArrayList(SongDAO.showAll()));
		this.tblCanciones.refresh();
	}

	@FXML
	void addSong(ActionEvent event) throws DAOExcepcion {
		SongDAO s = (SongDAO) this.tblCanciones.getSelectionModel().getSelectedItem();
		
		if(s==null) {
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debes seleccionar una canción");
			alert.showAndWait();

		}else {
			list.getId();
			s.getId();
			list.addSong(s, list);

			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText("Canción añadida");
			alert.showAndWait();

			Stage stage = (Stage) this.btnAdd.getScene().getWindow();
			stage.close();
			
		}
		
	}

	public void info(ActionEvent event) {
		Song s = this.tblCanciones.getSelectionModel().getSelectedItem();
		if(s==null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("Debes seleccionar una canción");
			alert.showAndWait();
		}else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Info");
			alert.setContentText(s.getDisk().toString());
			alert.showAndWait();
		}
		
	}
}