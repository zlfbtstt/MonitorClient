package com.okami.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.okami.dao.IFileIndexDao;
import com.okami.entities.FileIndex;


@Component
@Scope("prototype")
public class FileIndexDao implements IFileIndexDao{
	
	
	@Autowired
    private DataSource dataSource;
    
    private Connection conn;

	private int id;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    

    
	@Override
	public List<FileIndex> queryIndex() throws Exception {
		 
		String sql = "Select * from FileIndex";
	    Statement smt = conn.createStatement();

	    ResultSet rs = smt.executeQuery(sql);
	    List<FileIndex> list = new ArrayList<FileIndex>();
	    while (rs.next()) {
	    	Integer id = rs.getInt("Id");
	    	String path = rs.getString("Path");
	    	String sha1 = rs.getString("Sha1");
	    	String size = rs.getString("Size");
	    	String type = rs.getString("Type");
	    	String time = rs.getString("Time");
	    	String owner = rs.getString("Owner");
	    	String group = rs.getString("OwnerGroup");
	    	int status = rs.getInt("Status");
	    	int read = rs.getInt("Read");
	    	int write = rs.getInt("Write");
	    	int exec = rs.getInt("Exec");
	    	FileIndex fileIndex = new FileIndex(id, path, sha1, size, type, time, owner, group, status,read,write,exec);
	    	list.add(fileIndex);
	    }
	    return list;
	}

	@Override
	public boolean insertIndex(FileIndex fileIndex) throws Exception {
		String sql = "INSERT INTO FileIndex " +
				"(Path,Sha1,Size,Type,Time,Owner,OwnerGroup,Status,Read,Write,Exec) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		Connection conn = null;
		
//		try {
//			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, fileIndex.getPath());
			ps.setString(2,  fileIndex.getSha1());
			ps.setString(3,  fileIndex.getSize());
			ps.setString(4,  fileIndex.getType());
			ps.setString(5,  fileIndex.getTime());
			ps.setString(6,  fileIndex.getOwner());
			ps.setString(7,  fileIndex.getOwnerGroup());
			ps.setInt(8,  fileIndex.getStatus());
			ps.setInt(9,  fileIndex.getRead());
			ps.setInt(10,  fileIndex.getWrite());
			ps.setInt(11,  fileIndex.getExec());
			ps.executeUpdate();
			ps.close();
			
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {}
//			}
//		}
		return true;
	}

	@Override
	public boolean insertIndex(List<FileIndex> fileIndexList) throws Exception {
		String sql = "INSERT INTO FileIndex " +
				"(Path,Sha1,Size,Type,Time,Owner,OwnerGroup,Status,Read,Write,Exec) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		Connection conn = null;
		
//		try {
//			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			for(int i=0;i<fileIndexList.size();i++){
			
				ps.setString(1, fileIndexList.get(i).getPath());
				ps.setString(2,  fileIndexList.get(i).getSha1());
				ps.setString(3,  fileIndexList.get(i).getSize());
				ps.setString(4,  fileIndexList.get(i).getType());
				ps.setString(5,  fileIndexList.get(i).getTime());
				ps.setString(6,  fileIndexList.get(i).getOwner());
				ps.setString(7,  fileIndexList.get(i).getOwnerGroup());
				ps.setInt(8,  fileIndexList.get(i).getStatus());
				ps.setInt(9,  fileIndexList.get(i).getRead());
				ps.setInt(10,  fileIndexList.get(i).getWrite());
				ps.setInt(11,  fileIndexList.get(i).getExec());
				ps.executeUpdate();
			}
			ps.close();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {}
//			}
//		}
		return true;
	}
	
	@Override
	public boolean updateIndex(FileIndex fileIndex) throws Exception {
		String sql = "UPDATE FileIndex SET Sha1=?,Size=?,Type=?,Time=?,Owner=?,Group=?,Status=? ,Read=?,Write=?,Exec=? WHERE Path = ?";
//		Connection conn = null;
		
//		try {
//			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  fileIndex.getSha1());
			ps.setString(2,  fileIndex.getSize());
			ps.setString(3,  fileIndex.getType());
			ps.setString(4,  fileIndex.getTime());
			ps.setString(5,  fileIndex.getOwner());
			ps.setString(6,  fileIndex.getOwnerGroup());
			ps.setInt(7,  fileIndex.getStatus());
			ps.setString(8, fileIndex.getPath());
			ps.setInt(9,  fileIndex.getRead());
			ps.setInt(10,  fileIndex.getWrite());
			ps.setInt(11,  fileIndex.getExec());
			ps.executeUpdate();
			ps.close();
			
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {}
//			}
//		}
		return true;
	}

	@Override
	public boolean deleteIndex(FileIndex fileIndex) throws Exception {
		String sql = "DELETE FROM FileIndex WHERE Path = ?";
//		Connection conn = null;
		
//		try {
//			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, fileIndex.getPath());
			ps.executeUpdate();
			ps.close();
			
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {}
//			}
//		}
		return true;
	}

	@Override
	public boolean createTable() throws Exception {
		String sql = "CREATE TABLE 'FileIndex' ("
				+ "'Id'  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
				+ "'Path'  TEXT NOT NULL,"
				+ "'Sha1'  TEXT,"
				+ "'Size'  TEXT,"
				+ "'Time'  TEXT NOT NULL,"
				+ "'Type'  TEXT NOT NULL,"
				+ "'Read'  INTEGER,"
				+ "'Write'  INTEGER,"
				+ "'Exec'  INTEGER,"
				+ "'Owner'  TEXT,"
				+ "'OwnerGroup'  TEXT,"
				+ "'Status'  INTEGER NOT NULL"
				+ ");";
//		Connection conn = null;
//		
//		try {
//			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {}
//			}
//		}
		return true;
	}

	@Override
	public boolean connectDB() throws Exception {
    	try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    	return true;
		
	}
	
	@Override
	public boolean closeConnection() throws Exception{
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				return false;
			}
		}
	
		return true;
	}



	@Override
	public List<FileIndex> queryIndexByPath(String pathname) throws Exception {
		String sql = "Select * from FileIndex where Path = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, pathname);
	    List<FileIndex> list = new ArrayList<FileIndex>();
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
		    Integer id = rs.getInt("Id");
		    String path = rs.getString("Path");
	    	String sha1 = rs.getString("Sha1");
	    	String size = rs.getString("Size");
	    	String type = rs.getString("Type");
	    	String time = rs.getString("Time");
	    	String owner = rs.getString("Owner");
	    	String group = rs.getString("OwnerGroup");
	    	int status = rs.getInt("Status");
	    	int read = rs.getInt("Read");
	    	int write = rs.getInt("Write");
	    	int exec = rs.getInt("Exec");
	    	FileIndex fileIndex = new FileIndex(id, path, sha1, size, type, time, owner, group, status,read,write,exec);
	    	list.add(fileIndex);
		}
		rs.close();
		ps.close();
		return list;
	}



	@Override
	public List<FileIndex> queryIndexLikePath(String pathname) throws Exception {

		String sql = "Select * from FileIndex where Path like ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, pathname+"%");
	    List<FileIndex> list = new ArrayList<FileIndex>();
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
		    Integer id = rs.getInt("Id");
		    String path = rs.getString("Path");
	    	String sha1 = rs.getString("Sha1");
	    	String size = rs.getString("Size");
	    	String type = rs.getString("Type");
	    	String time = rs.getString("Time");
	    	String owner = rs.getString("Owner");
	    	String group = rs.getString("OwnerGroup");
	    	int status = rs.getInt("Status");
	    	int read = rs.getInt("Read");
	    	int write = rs.getInt("Write");
	    	int exec = rs.getInt("Exec");
	    	FileIndex fileIndex = new FileIndex(id, path, sha1, size, type, time, owner, group, status,read,write,exec);
	    	list.add(fileIndex);
		}
		rs.close();
		ps.close();
		return list;
	}



	@Override
	public boolean deleteIndexLikePath(String pathname) throws Exception {
		String sql = "DELETE FROM FileIndex WHERE Path Like ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, pathname+"%");
		ps.executeUpdate();
		ps.close();
		return true;
	}

}
