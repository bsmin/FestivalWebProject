package common.service;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class CommonServiceImpl implements CommonService {

	@Override
	public String upload(MultipartFile file, String kind, HttpSession session) {
		//������Ʈ�� ��������ġ: ..metadata/ ../wtpwebapps/sw/
		String project = session.getServletContext().getRealPath("");
		//������ ���ε��� ������Ʈ�� ��������ġ: ..metadata/ ../wtpwebapps/sw/upload
//								(D:\SpringApp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\sw)
		String upload = project + "upload";
		//������ ���ε��� ����: upload/notice/2018/01/24/abc.txt
		String filepath = allPath(upload, kind);
		//���ôٹ������� ������ ���ϸ��� ���� �ٸ� ������ ���ε��� ���� �����Ƿ�
		//������ id�� �ο��Ͽ� ������ ���ε�����
		//��������ĺ���(Universally Unique IDentifier) : UUID
		//upload/notice/2018/01/24/1154212ab0dfs_abc.txt
		String uuid = new Date().getTime() + UUID.randomUUID().toString()
						+"_" + file.getOriginalFilename();
		try{
			file.transferTo(new File(filepath, uuid));
		}catch (Exception e) {
		}
		
		return filepath.replace(project, "") + File.separator + uuid;
	}

	String allPath(String upload, String kind){
		String path[] = new String[5];
		path[0] = upload; //upload �������� 
		path[1] = upload + File.separator + kind; //upload/notice ��������
		
		Date now = new Date();
		//upload/notice/2018 ��������
		path[2] = path[1] + File.separator + new SimpleDateFormat("yyyy").format(now);
		//upload/notice/2018/01 ��������
		path[3] = path[2] + File.separator + new SimpleDateFormat("MM").format(now);
		//upload/notice/2018/01/24 ��������
		path[4] = path[3] + File.separator + new SimpleDateFormat("dd").format(now);
//		File : ���ϵ� �����̰�, ������ ����
//		�ش� ������ ���ٸ� ������ ����
		if( !new File(path[4]).exists() ){
			for(String p : path){
				File dir = new File(p);
				if(!dir.exists()) dir.mkdir();
			}
		}
		
		return path[4];
	}

	@Override
	public File download(String filepath, String filename, HttpSession session, HttpServletResponse response) {
//������ ���ε�� ������ �������� ��ġ���� ������ ã�ƾ� ��		
		filepath = session.getServletContext().getRealPath("") + filepath;
//�ٿ�ε��� ���ϰ�ü ����
		File file = new File(filepath);
		
//�ٿ�ε��� ������ ����Ÿ���� �˾Ƴ���.
		String mime = session.getServletContext().getMimeType(filename);
		if(mime==null) mime = "application/octet-stream";
		
		response.setContentType(mime);
		
		try{
//���ϸ��� �ѱ��� ��� ���ڰ� ������ �ʵ��� ó��
		filename = URLEncoder.encode(filename, "utf-8")
				.replaceAll("\\+", "%20")
				.replaceAll("%5B", "[")
				.replaceAll("%5D", "]")
				.replaceAll("%28", "(")
				.replaceAll("%29", ")");
				
		response.setHeader("content-disposition", 
								"attachment; filename="+filename);
		ServletOutputStream out = response.getOutputStream();
		FileCopyUtils.copy(new FileInputStream(file), out);
		out.flush();
		
		
		}catch(Exception e){}
		return file;
	}
/*
	@Override
	public void emailSend(String email, String name, HttpSession session) {
//		simpleEmailSend(email, name);
		htmlEmailSend(email, name, session);
	}
	//html���·� ȸ�����Խ� ���� ������ 
	public void htmlEmailSend(String email, String name, HttpSession session) {
		//����÷���ϴ� ������ �̸��� ����
		//MultiPartEmail mail = new MultiPartEmail();
		HtmlEmail mail = new HtmlEmail();
		
		mail.setDebug(true); 					//�ߺ������� Ȯ��(Debug)�� ����
		mail.setCharset("utf-8");				//��� ����
		mail.setHostName("smtp.naver.com");		//�̸��ϼ�������
		//�۽��� ���� �������� ���̵�/��� ����
		mail.setAuthentication("skvk4113","suma?????");//�ڽ��� �̸��� ���̵�/����� �־��.
		mail.setSSLOnConnect(true);  			//����
		
		try {
			mail.setFrom("skvk4113@naver.com", "������"); //�۽���(�����»��)�� �̸����ּ�
			mail.addTo(email, "������"); 					//������(ȸ�������ѻ��)�� �̸����ּ�, �����ڸ�
			mail.setSubject("SMȨ�������� �������ּż� �����մϴ�.���Ժ���� 1000���� �Դϴ�");	//���� ����

//			mail.setMsg("ȸ�������� �����մϴ�!"); 			//���� ����
			String content = "<html>";
			content += "<a href='https://www.naver.com/'>���̹� �븸�������</a>";
			content += "<img src='http://img.yonhapnews.co.kr/photo/yna/YH/2018/02/07/PYH2018020705040034000_P2.jpg'/>";
			content += "<h3>�ѿ� ����SW����</h3>";
			content += "<h3>�����մϴ�!<br>������ �����ϼ̽��ϴ�.</h3>";
			content += "</html>";
//			mail.setMsg(content);
			mail.setHtmlMsg(content);
			
			
			//����÷�� ��ü ����
			EmailAttachment attach = new EmailAttachment();
			//÷������ ����
			attach.setPath("D:\\sql\\SW.sql");
			mail.attach(attach);
			
			attach = new EmailAttachment();
			//÷������ ����
			attach.setPath(session.getServletContext().getRealPath("")+"imgs"+File.separator+"hanul.png");
			mail.attach(attach);
			mail.send();
			
		} catch (Exception e) {

		}
		
	}
	
	//������ ȸ�����Խ� ���Ϻ�����
	public void simpleEmailSend(String email, String name) {
		SimpleEmail mail = new SimpleEmail(); 	//��ü����
		mail.setDebug(true); 					//�ߺ������� Ȯ��(Debug)�� ����
		mail.setCharset("utf-8");				//��� ����
		mail.setHostName("smtp.naver.com");		//�̸��ϼ�������
		//�۽��� ���� �������� ���̵�/��� ����
		mail.setAuthentication("skvk4113","suman8315");//�ڽ��� �̸��� ���̵�/����� �־��.
		mail.setSSLOnConnect(true);  			//����
		
		
		try {
			mail.setFrom("skvk4113@naver.com", "������"); //�۽���(�����»��)�� �̸����ּ�
			mail.addTo(email, "������"); 					//������(ȸ�������ѻ��)�� �̸����ּ�, �����ڸ�
			mail.setSubject("SMȨ�������� �������ּż� �����մϴ�.���Ժ���� 1000���� �Դϴ�");	//���� ����
			mail.setMsg("ȸ�������� �����մϴ�!"); 			//���� ����
			mail.send(); //���� ����
			
		} catch (EmailException e) {
			e.printStackTrace();
		} 
	}*/
}
//zhapt250r
//Ch92c02c19!











