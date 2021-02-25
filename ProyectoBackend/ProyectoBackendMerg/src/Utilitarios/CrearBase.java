package Utilitarios;

import java.util.*;
import com.mysql.jdbc.Driver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBase {
	
	static SystemProperties sp, sp2;
	static Connection con;
	
	public static void main(String[] args) {
		System.out.println("===============holaMundo=========Practico4========================");
		try {
			sp = new SystemProperties();
			sql_conectarBase();
			System.out.println("...abriendo conexion con el dbms:"+sp.getMysql_url());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sql_crearBaseYCargarla();
		
		sql_cerrarBase();
		System.out.println("========================chauMundo=============================================");		
	}

	private static void sql_crearBaseYCargarla() {
		String sql_tempVarName, sql_temp;		
        List<String> vars= null;
        vars = new LinkedList<String>();
        vars.add("DROP DATABASE IF EXISTS PROYECTO;");
		vars.add("CREATE DATABASE PROYECTO;");
		vars.add("CREATE TABLE IF NOT EXISTS `Proyecto`.`TorreControl` (  `idTCtrol` INT NOT NULL AUTO_INCREMENT,  `CoordXTCtrol` INT NULL,  `CoordYTCtrol` INT NULL,  `EstadoTCtrol` BOOLEAN NULL,  `VidaTCtrol` INT NULL,  `HayEnemigoTCtrol` BOOLEAN NULL,  `RangoDeVisionTCtrol` INT NULL,  PRIMARY KEY (`idTCtrol`),  INDEX `TControl` (`idTCtrol` ASC, `CoordXTCtrol` ASC, `CoordYTCtrol` ASC, `EstadoTCtrol` ASC, `VidaTCtrol` ASC) );");
		vars.add("CREATE TABLE IF NOT EXISTS `Proyecto`.`TanqueCombustible` (   `idTComb` INT NOT NULL AUTO_INCREMENT,   `CoordXTComb` INT NULL,   `CoordYTComb` INT NULL,   `EstadoTComb` BOOLEAN NULL,   `VidaTComb` INT NULL,   `CantCombTComb` INT NULL,   `EnUsoTComb` BOOLEAN NULL,   PRIMARY KEY (`idTComb`),   INDEX `TComb` (`idTComb` ASC, `CoordXTComb` ASC, `CoordYTComb` ASC, `EstadoTComb` ASC, `VidaTComb` ASC)   );");
		vars.add("CREATE TABLE IF NOT EXISTS `Proyecto`.`Deposito` (   `idDepo` INT NOT NULL AUTO_INCREMENT,   `CoordXDepo` INT NULL,   `CoordYDepo` INT NULL,   `EstadoDepo` BOOLEAN NULL,   `VidaDepo` INT NULL,   `CanBomboDepo` INT NULL,   `EnUsoDepo` BOOLEAN NULL,   PRIMARY KEY (`idDepo`),   INDEX `Depo` (`idDepo` ASC, `CoordXDepo` ASC, `CoordYDepo` ASC, `EstadoDepo` ASC, `VidaDepo` ASC)   );");
		vars.add("CREATE TABLE IF NOT EXISTS `Proyecto`.`Jugador` (   `idJugador` INT AUTO_INCREMENT,   `nombreJugador` VARCHAR(45) NOT NULL,   `passJugador` VARCHAR(45) NOT NULL,   `onlineJugador` BOOLEAN NULL,   `puntajeJugador` INT NOT NULL,   PRIMARY KEY (`nombreJugador`,`passJugador`),   INDEX `Jugador` (`idJugador` ASC, `nombreJugador` ASC, `passJugador` ASC, `onlineJugador` ASC, `puntajeJugador` ASC)   );");
		vars.add("CREATE TABLE IF NOT EXISTS `Proyecto`.`Partida` (   `idPartida` INT NOT NULL AUTO_INCREMENT,   `estadoPartida` VARCHAR(45) NULL,   `ultimaactualizacionPartida` date default NULL,   `guardadaPartida` BOOLEAN NULL,   `nombrePartida` VARCHAR(45) NULL,   `cantjugadoresPartida` INT NOT NULL,   `creadorPartida` INT NOT NULL,   `fechacreadaPartida` date default NULL,   `terminoPartida` BOOLEAN NOT NULL,   PRIMARY KEY (`idPartida`,`creadorPartida`),   INDEX `Jugador` (`idPartida` ASC, `estadoPartida` ASC, `ultimaactualizacionPartida` ASC, `cantjugadoresPartida` ASC, `fechacreadaPartida` ASC, `terminoPartida` ASC) , 	CONSTRAINT `Jug`     FOREIGN KEY (`creadorPartida`)     REFERENCES `Proyecto`.`Jugador` (`idJugador`)     ON DELETE NO ACTION     ON UPDATE NO ACTION     );");
		vars.add("CREATE TABLE IF NOT EXISTS `Proyecto`.`Equipo` (   `idEquipo` INT AUTO_INCREMENT,   `idJugadorEquipo` INT NOT NULL,   `bandoEquipo` VARCHAR(45) NULL,   `partidaEquipo` INT NOT NULL,   PRIMARY KEY (`idEquipo`, `idJugadorEquipo`,`partidaEquipo`),   INDEX `Equipo` (`idEquipo` ASC, `idJugadorEquipo` ASC,`partidaEquipo` ASC ) , CONSTRAINT `Eq`     FOREIGN KEY (`partidaEquipo`)     REFERENCES `Proyecto`.`Partida` (`idPartida`)     ON DELETE NO ACTION     ON UPDATE NO ACTION, CONSTRAINT `Jug_`     FOREIGN KEY (`idJugadorEquipo`)     REFERENCES `Proyecto`.`Jugador` (`idJugador`)     ON DELETE NO ACTION     ON UPDATE NO ACTION     );");
		vars.add("CREATE TABLE IF NOT EXISTS `Proyecto`.`BASE` (   `idBase` INT AUTO_INCREMENT,   `idTCtrolBase` INT NOT NULL,   `idTcombBase` INT NOT NULL,   `idDepoBase` INT NOT NULL,   `idEquipoBase` INT NOT NULL,   PRIMARY KEY (`idTCtrolBase`, `idTcombBase`, `idDepoBase`, `idEquipoBase`),   INDEX `Base_` (`idBase`, `idTCtrolBase` ASC, `idTcombBase` ASC, `idDepoBase` ASC, `idEquipoBase` ASC) , CONSTRAINT `TCONTROLBASE`     FOREIGN KEY (`idTCtrolBase`)     REFERENCES `Proyecto`.`TorreControl` (`idTCtrol`)     ON DELETE NO ACTION     ON UPDATE NO ACTION, CONSTRAINT `TCOMBBASE`     FOREIGN KEY (`idTcombBase`)     REFERENCES `Proyecto`.`TanqueCombustible` (`idTComb`)     ON DELETE NO ACTION     ON UPDATE NO ACTION, CONSTRAINT `DEPOBASE`     FOREIGN KEY (`idDepoBase`)     REFERENCES `Proyecto`.`Deposito` (`idDepo`)     ON DELETE NO ACTION     ON UPDATE NO ACTION,  CONSTRAINT `DEPOEQUIPO`     FOREIGN KEY (`idEquipoBase`)     REFERENCES `Proyecto`.`Equipo` (`idEquipo`)     ON DELETE NO ACTION     ON UPDATE NO ACTION     );");
		vars.add("CREATE TABLE IF NOT EXISTS `Proyecto`.`Artillero` (   `idArt` INT NOT NULL AUTO_INCREMENT,   `CoordXArt` INT NULL,   `CoordYArt` INT NULL,   `EstadoArt` BOOLEAN NULL,   `VidaArt` INT NULL,   `HayEnemigoArt` BOOLEAN NULL,   `RangoDeVisionArt` INT NULL,   `AnguloArt` FLOAT NULL,   `idbaseArt` INT NOT NULL,   PRIMARY KEY (`idArt`,`idbaseArt`),   INDEX `IDART_idx` (`idArt` ASC, `CoordXart` ASC, `CoordYart` ASC, `EstadoArt` ASC, `VidaArt` ASC, `idbaseArt` ASC) ,     CONSTRAINT `ArtBase`     FOREIGN KEY (`idbaseArt`)     REFERENCES `Proyecto`.`Base` (`idBase`)     ON DELETE NO ACTION     ON UPDATE NO ACTION);");
		vars.add("CREATE TABLE IF NOT EXISTS `Proyecto`.`Avion` (   `idAvion` INT NOT NULL AUTO_INCREMENT,   `CoordXAvion` INT NULL,   `CoordYAvion` INT NULL,   `EstadoAvion` boolean NULL,   `VidaAvion` INT NULL,   `AnguloAvion` INT NULL,   `BombasEnAvion` BOOLEAN NULL,   `BombasAvion` INT NULL,   `AlturaAvion` INT NULL,   `CombustibleAvion` INT NULL,   `HayEnemigoAvion` BOOLEAN NULL,   `RangoDeVisionAvion` INT NULL,   `EnCampoEnemigo` BOOLEAN NULL,   `idbaseAvion` INT NOT NULL,   PRIMARY KEY (`idAvion`),   INDEX `Avion` (`idAvion` ASC, `CoordXAvion` ASC, `CoordYAvion` ASC, `EstadoAvion` ASC, `VidaAvion` ASC, `idbaseAvion` ASC) ,   CONSTRAINT `AvionBase`     FOREIGN KEY (`idbaseAvion`)     REFERENCES `Proyecto`.`Base` (`idBase`)     ON DELETE NO ACTION     ON UPDATE NO ACTION);");
		vars.add("CREATE TABLE IF NOT EXISTS `Proyecto`.`EquipoJugadores` (   `idJugador` INT NOT NULL,   `idEquipo` INT NOT NULL,   PRIMARY KEY (`idJugador`,`idEquipo`),   INDEX `EquipoJugadores` (`idJugador` ASC, `idEquipo` ASC) ,   CONSTRAINT `JugadoresEquipos`     FOREIGN KEY (`idJugador`)     REFERENCES `Proyecto`.`Jugador` (`idJugador`)     ON DELETE NO ACTION     ON UPDATE NO ACTION, CONSTRAINT `EquiposJugadores`     FOREIGN KEY (`idEquipo`)     REFERENCES `Proyecto`.`Equipo` (`idEquipo`)     ON DELETE NO ACTION     ON UPDATE NO ACTION     );");

		
		Statement stmt = null;
		int i=1;
		for(String sql : vars) {            
			System.out.println("- - - - - - - - - - - - - - - - - - - -"); 
			sql_tempVarName = "sql_"+i;
			sql_temp		= sql;
			System.out.println("SQL::"+sql_tempVarName+"="+sql_temp);
			i++;
			try {
				stmt = con.createStatement();
				int cant = stmt.executeUpdate(sql_temp);
				stmt.close();
				System.out.println("Resultado de ==>" + sql_temp + "<==");
				System.out.println(cant + " filas afectadas");
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
		}
	}

	public static void  sql_conectarBase()  {
		String driver = sp.getMysql_driver();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = sp.getMysql_url();//+sp.getMysql_dbname();
		try {
			con = DriverManager.getConnection(url, sp.getMysql_user(), sp.getMysql_password());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void  sql_cerrarBase(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws IOException {
		sp2 = new SystemProperties();
		String driver = sp2.getMysql_driver();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = sp2.getMysql_url();//+sp.getMysql_dbname();
		try {
			con = DriverManager.getConnection(url, sp2.getMysql_user(), sp2.getMysql_password());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
