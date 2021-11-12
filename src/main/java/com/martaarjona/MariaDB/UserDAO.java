package com.martaarjona.MariaDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.martaarjona.dao.IDAO;
import com.martaarjona.model.ListReproduction;
import com.martaarjona.model.User;
import com.martaarjona.utils.Connect;

public class UserDAO extends User{
	
	
	private static final String GETUSERBYID="SELECT id,nombre,correo FROM usuario WHERE id=?";
	private final static String INSERT = "INSERT INTO usuario (nombre,correo,contrasena) " + "VALUES (?,?,?)";
	private final static String USERS = "SELECT id,nombre,correo,contrasena FROM usuario";
	private final static String INICIO ="SELECT id,correo,nombre,contrasena FROM usuario WHERE nombre =? AND contrasena=?";
	private final static String DELETE = "DELETE FROM usuario WHERE id=?";
	private final static String UPDATE = "UPDATE usuario SET nombre=?, correo=?, contrasena=? WHERE id=?";
	private final static String SUSCRIBE ="INSERT INTO usuario_lista (id_usuario, id_lista) VALUES (?,?)";
	private final static String DES_SUSCRIBE ="DELETE FROM usuario_lista WHERE id_usuario=? AND id_lista=?";
	
	public UserDAO() {
		super();
	}
	
	public UserDAO(int id) {
		super(id);
	}
	public UserDAO(int id, String name, String email) {
		// TODO Auto-generated constructor stub
		super(id,name,email);
	}
	public UserDAO (String name, String email, String password) {
		super(name,email,password);
		
	}
	public UserDAO(String name, String password) {
		super(name,password);
		
	}
	public UserDAO (int id, String name, String email, String password) {
		super(id,name,email,password);
		
	}
	private Connection con = null;
	
	/**
	 * Método que obtiene un usuario mediante su id
	 * @param id
	 * @return User
	 */
	public User getUserById(int id) {
		
		User u = new UserDAO();
		
		con=Connect.getConnect();
		
		if(con!=null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = con.prepareStatement(GETUSERBYID);
				ps.setInt(1, id);
				rs=ps.executeQuery();
				if(rs.next()) {
					u=new UserDAO(rs.getInt("id"),rs.getString("nombre"),
							rs.getString("correo"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		return u;
	}
	
	
	
	
	/**
	 * Obtiene todos los usuarios guardados en la BD
	 * 
	 * @return lista de usuarios
	 */
	public List<UserDAO> getUsers(){
		List<UserDAO> users = new ArrayList<>();
		con = Connect.getConnect();
		
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(USERS);
				rs = ps.executeQuery();

				while (rs.next()) {
					

					users.add(new UserDAO(rs.getInt("id"),
							rs.getString("nombre"),
							rs.getString("correo"),
							rs.getString("contrasena")));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return users;
	}
	
	/**
	 * Método para comprobar si en la BD si existe un usuario 
	 * con un nombre y una contraseña dados
	 * @param u
	 * @return
	 */
	public boolean getPassword(UserDAO u) {
		boolean result=false;
		con = Connect.getConnect();
		if(con!=null) {
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				ps = con.prepareStatement(INICIO);
				ps.setString(1, u.getName());
				ps.setString(2, u.getPassword());
				rs=ps.executeQuery();
				while(rs.next()) {
					u.setId(rs.getInt("id"));
					u.setName(rs.getString("nombre"));
					u.setEmail(rs.getString("correo"));
					u.setPassword(rs.getString("contrasena"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		if(u.getId()==0) {
			result=false;
		}else {
			result=true;
		}
		return result;
	}
	
	/**
	 * Inserta en la base de datos un nuevo usuario
	 * 
	 * @return
	 */
	
	
	public int save() {
		// TODO Auto-generated method stub
		int rs=0;
		con = Connect.getConnect();
		if(con!=null) {
			try {
				PreparedStatement ps = con.prepareStatement(INSERT);
				ps.setString(1,this.name);
				ps.setString(2, this.email);
				ps.setString(3, this.password);
				rs=ps.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return rs;
		
	}

	/**
	 * Edita los campos de un usuario en la BD
	 * @return
	 */
	public int edit() {
		// TODO Auto-generated method stub
		int rs = 0;
		Connection con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(UPDATE);
				q.setString(1, this.name);
				q.setString(2, this.email);
				q.setString(3, this.password);
				q.setInt(4, this.id);
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
		
	}

	/**
	 * Elimina un usuario de la BD
	 */
	public int delete() {
		// TODO Auto-generated method stub
		int rs = 0;
		con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DELETE);
				q.setInt(1, this.id);
				rs = q.executeUpdate();
				this.id = -1;
				this.name = "";
				this.email = "";
				this.password="";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
		
	}
	
	/**
	 * Método que permite al usuario seguir una lista
	 * @param u
	 * @param l
	 * @return
	 */
	
	public int suscribe(UserDAO u, ListReproductionDAO l) {
		int rs=0;
		con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(SUSCRIBE);
				q.setInt(1, u.getId());
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
	 * Método que permite al usuario dejar de seguir una lista
	 * @param u
	 * @param l
	 * @return
	 */
	public int des_suscribe(UserDAO u, ListReproductionDAO l) {
		int rs=0;
		con = Connect.getConnect();
		if (con != null) {
			try {
				PreparedStatement q = con.prepareStatement(DES_SUSCRIBE);
				q.setInt(1, u.getId());
				q.setInt(2, l.getId());
				rs = q.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return rs;
	}
	
	 
	
	
}
