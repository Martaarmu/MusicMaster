package com.martaarjona.MariaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.martaarjona.dao.IDAO;
import com.martaarjona.dao.ListDAO;
import com.martaarjona.model.Artist;
import com.martaarjona.model.Disk;
import com.martaarjona.model.Genero;
import com.martaarjona.model.ListReproduction;
import com.martaarjona.model.Song;
import com.martaarjona.model.User;
import com.martaarjona.utils.Connect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListReproductionDAO extends ListReproduction implements ListDAO {

	private static final String SHOWALL = "SELECT id, nombre, id_usuario FROM lista_reproduccion";
	private static final String INSERT = "INSERT INTO lista_reproduccion (nombre,id_usuario) VALUES (?,?)";
	private static final String SHOWBYID = "SELECT id, nombre, id_usuario FROM lista_reproduccion WHERE id=?";
	private static final String INSERTSONG = "INSERT INTO cancion_lista (id_cancion,id_lista) VALUES (?,?)";
	private final static String DELETE = "DELETE FROM cancion_lista WHERE id_cancion=? AND id_lista=?";
	private static final String GETSONGS = "SELECT cancion.id, cancion.nombre, cancion.duracion, cancion.id_genero, cancion.id_disco FROM lista_reproduccion, cancion_lista, cancion WHERE "
			+ "lista_reproduccion.id=id_lista AND cancion.id=id_cancion AND lista_reproduccion.id=?";
	private static final String SHOWBYUSER = "SELECT id,nombre,id_usuario FROM lista_reproduccion WHERE id_usuario=?";
	private static final String SHOWBYSUSCRIPCION = "SELECT l.id,l.nombre,l.id_usuario FROM lista_reproduccion AS l ,usuario_lista WHERE usuario_lista.id_lista=l.id AND usuario_lista.id_usuario=?";
	private static final String DELETELIST = "DELETE FROM lista_reproduccion WHERE id=?";

	public ListReproductionDAO() {
		super();
	}

	public ListReproductionDAO(String name, User creador) {
		super(name, creador);
	}

	public ListReproductionDAO(int id, String name, User creator, List<Song> songs, List<User> users) {
		super(id, name, creator, songs, users);
		// TODO Auto-generated constructor stub
	}

	public ListReproductionDAO(String name, User creator, List<Song> songs) {
		super(name, creator, songs);
		// TODO Auto-generated constructor stub
	}

	public ListReproductionDAO(int id, String name) {
		super(id, name);
	}

	public ListReproductionDAO(int id, String name, User u) {
		super(id, name, u);
	}

	public ListReproductionDAO(String nombre, String id_usuario) {
		// TODO Auto-generated constructor stub
		super(nombre, id_usuario);
	}

	private static Connection con = null;

	@Override
	public void save() {
		// TODO Auto-generated method stub
		con = Connect.getConnect();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				
				ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, this.name);
				ps.setInt(2, this.creator.getId());
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public int edit() {
		return id;
		// TODO Auto-generated method stub

	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		int rs = 0;
		con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETELIST);
				q.setInt(1, this.id);
				rs = q.executeUpdate();
				this.id = -1;
				this.name = "";
				this.creator=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;

	}
	
	/**
	 * Método que muestra todas las listas de reproducción
	 * @return List<ListReproduction>
	 */

	public static List<ListReproductionDAO> showAll() {
		List<ListReproductionDAO> result = new ArrayList<ListReproductionDAO>();

		con = Connect.getConnect();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SHOWALL);
				rs = ps.executeQuery();

				while (rs.next()) {
					UserDAO ud = new UserDAO();
					User u = ud.getUserById(rs.getInt("id_usuario"));

					result.add(new ListReproductionDAO(rs.getInt("id"), rs.getString("nombre"), u));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}
	
	/**
	 * Método que muestra una lista de reproducción segun su id
	 * @param id
	 * @return ListReproduction
	 */

	public ListReproductionDAO showbyid(int id) {
		ListReproductionDAO result = new ListReproductionDAO();
		con = Connect.getConnect();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SHOWBYID);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					UserDAO ud = new UserDAO();
					User u = ud.getUserById(rs.getInt("id_usuario"));
					result = new ListReproductionDAO(rs.getInt("id"), rs.getString("nombre"), u);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * Método que devuelve las listas de reproduccion que ha creado 
	 * un usuario
	 */
	public static List<ListReproductionDAO> showbyuser(UserDAO u) {
		
		List<ListReproductionDAO> result = new ArrayList<>();
		con = Connect.getConnect();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SHOWBYUSER);
				ps.setInt(1, u.getId());
				rs = ps.executeQuery();
				while (rs.next()) {
					UserDAO ud = new UserDAO();
					u = (UserDAO) ud.getUserById(rs.getInt("id_usuario"));
					result.add(new ListReproductionDAO(rs.getInt("id"), rs.getString("nombre"), u));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * Método que devuelve las listas de reproduccion a las que
	 * se ha suscrito un usuario
	 * @param u
	 * @return
	 */
public static List<ListReproductionDAO> showbysuscripcion(UserDAO u) {
		
		List<ListReproductionDAO> result = new ArrayList<>();
		con = Connect.getConnect();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(SHOWBYSUSCRIPCION);
				ps.setInt(1, u.getId());
				rs = ps.executeQuery();
				while(rs.next()) {
					UserDAO ud = new UserDAO();
					u = (UserDAO) ud.getUserById(rs.getInt("id_usuario"));
					result.add(new ListReproductionDAO(rs.getInt("id"), rs.getString("nombre"), u));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	
	

	@Override
	public int addSong(SongDAO s, ListReproductionDAO l) {
		int rs = 0;
		con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(INSERTSONG);
				q.setInt(1, s.getId());
				q.setInt(2, l.getId());
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return rs;

	}

	@Override
	public int removeSong(SongDAO s,ListReproductionDAO l) {
		// TODO Auto-generated method stub
		int rs = 0;
		con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETE);
				q.setInt(1, s.getId());
				q.setInt(2, l.getId());
				rs = q.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
		

	}
	
	/**
	 * Método que devuelve las canciones que hay en una lista
	 * @param id
	 * @return List<Song>
	 */
	public static List<SongDAO> getsongsbyid(int id) {
		List<SongDAO> songs = new ArrayList<>();
		con = Connect.getConnect();
		if (con != null) {
			PreparedStatement q;
			try {
				q = con.prepareStatement(GETSONGS);
				q.setInt(1, id);
				ResultSet rs = q.executeQuery();
				while (rs.next()) {
					GeneroDAO gd = new GeneroDAO();
					Genero g = gd.getGeneroById(rs.getInt("id_genero"));
					
					DiskDAO dd = new DiskDAO();
					Disk d = dd.getDiskById(rs.getInt("id_disco"));
					
					songs.add(new SongDAO(rs.getInt("id"),
							rs.getString("nombre"),
							rs.getTime("duracion"),
							g,d));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return songs;
	}

}
