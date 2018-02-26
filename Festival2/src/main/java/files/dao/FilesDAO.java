package files.dao;

import files.dto.FilesDTO;

public interface FilesDAO {
	public boolean insert(FilesDTO dto);
	public boolean update(FilesDTO dto);
	public boolean boardDel(String b_id);
	public boolean profileDel(String u_email);
	public FilesDTO boardselect(String b_id);
	public FilesDTO profileselect(String u_email);
}
