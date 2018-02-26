package files.service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import files.dao.FilesDAO;
import files.dto.FilesDTO;

public class FilesServiceImpl implements FilesService {
	
	@Autowired FilesDAO dao;
	
	@Override
	public boolean insert(FilesDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(FilesDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean boardDel(String b_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean profileDel(String u_email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FilesDTO boardselect(String b_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilesDTO profileselect(String u_email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(MultipartFile file, String kind, HttpSession session) {
//		프로젝트의 물리적위치: ..metadata/ ../wtpwebapps/sw/
		String project = session.getServletContext().getRealPath("");
//		파일을 업로드할 물리적위치: ..metadata/ ../wtpwebapps/sw/upload
		String upload =  project + "upload"; 
//		파일을 업로드할 형태: upload/notice/2018/01/24/abc.txt
		String filepath = allPath( upload,  kind);
//		동시다발적으로 동일한 파일명의 서로 다른 파일을 업로드할 수도 있으므로
//		고유한 id를 부여하여 파일을 업로드하자
//		범용고유식별자(Universally Unique IDentifier) : UUID
//		upload/notice/2018/01/24/1154212ab0dfs_abc.txt
		String uuid = new Date().getTime() + UUID.randomUUID().toString() 
							+ "_" + file.getOriginalFilename();
		try{
			file.transferTo( new File(filepath, uuid) );
		}catch(Exception e){
		}
		
		return filepath.replace(project, "") + File.separator + uuid ;
	}

	String allPath(String upload, String kind){
		String path[] = new String[5];
		path[0] = upload;  // upload
		path[1] = path[0] + File.separator + kind;  // upload/notice
		
		Date now = new Date();
		// upload/notice/2018
		path[2] =  path[1] + File.separator 
								+ new SimpleDateFormat("yyyy").format(now) ;
		// upload/notice/2018/01
		path[3] =  path[2] + File.separator 
								+ new SimpleDateFormat("MM").format(now) ;
		// upload/notice/2018/01/24
		path[4] =  path[3] + File.separator 
				+ new SimpleDateFormat("dd").format(now) ;
		
//		File : 파일도 파일이고, 폴더도 파일 
//		해당 폴더가 없다면 폴더를 생성
		if(  !new File(path[4]).exists() ){
			for( String p : path ){
				File dir = new File( p );
				if( !dir.exists() ) dir.mkdir();
			}
		}
		return path[4];
	}
	

	@Override
	public File download(String filepath, String filename, HttpSession session, HttpServletResponse response) {
//		서버의 업로드된 파일의 물리적인 위치에서 파일을 찾아야 함 
		filepath = session.getServletContext().getRealPath("") + filepath;
//	다운로드할 파일객체 생성
		File file = new File( filepath );
		
// 다운로드할 파일의 마임타입을 알아낸다.
		String mime = session.getServletContext().getMimeType(filename);
		if( mime==null ) mime = "application/octet-stream";
		
		response.setContentType(mime);
		
		try{
//			파일명이 한글인 경우 문자가 깨지지 않도록 처리
			filename = URLEncoder.encode(filename, "utf-8")
								.replaceAll("\\+", "%20")
								.replaceAll("%5B", "[")
								.replaceAll("%5D", "]")
								.replaceAll("%28", "(")
								.replaceAll("%29", ")");
			response.setHeader("content-disposition",
											"attachment; filename="+filename); 
			ServletOutputStream  out = response.getOutputStream();
			FileCopyUtils.copy( new FileInputStream(file), out);
			out.flush();
			
		}catch(Exception e){	}
		return file;
	}

}
