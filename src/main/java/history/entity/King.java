package history.entity;

import crawler.manager.CrawlerManager;
import history.History;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

public class King extends History{
	protected String mienHieu;
	protected String thuyHieu;
	protected String nienHieu;
	protected String tenHuy;
	protected String triVi;
	protected String tienNhiem;
	protected String keNhiem;
	protected String sinh;
	protected String mat;
	protected String trieuDai;
	protected String anTang;
	protected String thanPhu;
	protected String thanMau;
	public King() {
		super();
		// TODO Auto-generated constructor stub
	}
	public King(String name, String time, String description) {
		super(name, time, description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<King> loadDataJson() throws IOException {
		 Gson gson = new Gson();
		 Reader reader = Files.newBufferedReader(Paths.get("src/main/java/json/King.json"));
		 List<King> dks = Arrays.asList(gson.fromJson(reader, King[].class));
		 reader.close();

		CrawlerManager.setEntityDisplay("King", dks.size());

	        return dks;
	}

	public String getMienHieu() {
		return mienHieu;
	}
	public void setMienHieu(String mienHieu) {
		if(mienHieu!=null) this.mienHieu = mienHieu;
		else this.mienHieu = "Không có";
	}
	public String getThuyHieu() {
		return thuyHieu;
	}
	public void setThuyHieu(String thuyHieu) {
		if(thuyHieu!=null) this.thuyHieu = thuyHieu;
		else this.thuyHieu = "Không có";
	}
	public String getNienHieu() {
		return nienHieu;
	}
	public void setNienHieu(String nienHieu) {
		if(nienHieu!=null) this.nienHieu = nienHieu;
		else this.nienHieu = "Không có";
	}
	public String getTenHuy() {
		return tenHuy;
	}
	public void setTenHuy(String tenHuy) {
		if(tenHuy!=null) this.tenHuy = tenHuy;
		else this.tenHuy = "Không có";
	}
	public String getTriVi() {
		return triVi;
	}
	public void setTriVi(String triVi) {
		if(triVi!=null) this.triVi = triVi;
		else this.triVi = "Không rõ";
	}
	public String getTienNhiem() {
		return tienNhiem;
	}
	public void setTienNhiem(String tienNhiem) {
		if(tienNhiem!=null) this.tienNhiem = tienNhiem;
		else this.tienNhiem = "Không có";
	}
	public String getKeNhiem() {
		return keNhiem;
	}
	public void setKeNhiem(String keNhiem) {
		if(keNhiem!=null) this.keNhiem = keNhiem;
		else this.keNhiem = "Không có";
	}
	public String getSinh() {
		return sinh;
	}
	public void setSinh(String sinh) {
		if(sinh!=null) this.sinh = sinh;
		else this.sinh = "?";
	}
	public String getMat() {
		return mat;
	}
	public void setMat(String mat) {
		if(mat!=null) this.mat = mat;
		else this.mat = "?";
	}
	public String getTrieuDai() {
		return trieuDai;
	}
	public void setTrieuDai(String trieuDai) {
		if(trieuDai!=null) this.trieuDai = trieuDai;
		else this.trieuDai = "Không rõ";
	}
	public String getAntang() {
		return anTang;
	}
	public void setAntang(String anTang) {
		if(anTang!=null) this.anTang = anTang;
		else this.anTang = "Không rõ";
	}
	public String getThanPhu() {
		return thanPhu;
	}
	public void setThanPhu(String thanPhu) {
		if(thanPhu!=null) this.thanPhu = thanPhu;
		else this.thanPhu = "Không rõ";
	}
	public String getThanMau() {
		return thanMau;
	}
	public void setThanMau(String thanMau) {
		if(thanMau!=null) this.thanMau = thanMau;
		else this.thanMau = "Không rõ";
	}
	public void setSinhMat() {
		this.setTime("Sinh: " + this.sinh + "\n" + "Mất: " + this.mat);
	}
	@Override
	public String hienthi() {
		return "Tên: " + this.getName() + "\n"  + "Miếu hiệu: " + this.getMienHieu() + "\n" + "Thụy hiệu: " + this.getThuyHieu() + "\n" + 
			"Niên hiệu: " + this.getNienHieu() + "\n" + "Trị vì: " + this.getTriVi() + "\n" + "Tiền nhiệm: " + this.getTienNhiem() + "\n"
			+ "Kế nhiệm: " + this.getKeNhiem() + "\n" + "Sinh: " + this.getSinh() + "\n" + "Mất: " + this.getMat() + "\n"
			+ "Triều đại: " + this.getTrieuDai() + "\n" + "An Táng: " + this.getAntang() + "\n" + "Thân phụ: " + this.getThanPhu() + "\n"
			+ "Thân mẫu: " + this.getThanMau() + "\n" + "Chi tiết: " + this.getDescription();
	}
}
