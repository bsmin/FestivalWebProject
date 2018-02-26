package files.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import files.dto.FilesDTO;



public interface FilesService {
	public boolean insert(FilesDTO dto);
	public boolean update(FilesDTO dto);
	public boolean boardDel(String b_id);
	public boolean profileDel(String u_email);
	public FilesDTO boardselect(String b_id);
	public FilesDTO profileselect(String u_email);
	String upload( MultipartFile file, String kind, HttpSession session );
	File download(String filepath, String filename, HttpSession session, HttpServletResponse response );
}
