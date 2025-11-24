package entities;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import utils.ArchiveAdm;
import utils.ChromeAdm;

public class EmpresaSICREDI {

	private Integer codEmp;
	private Integer codFil;
	private String user;
	private String pass;
	private String trans;
	private String cnpj;
	ChromeAdm chromeAdm = new ChromeAdm();
	private String caminhoPastaSalvar;
	private LocalDate data;
	private String nomeArquivo;
	
	public EmpresaSICREDI(Integer codEmp, Integer codFil, String user, String pass, String trans, String cnpj) {
		this.codEmp = codEmp;
		this.codFil = codFil;
		this.user = user;
		this.pass = pass;
		this.trans = trans;
		this.cnpj = cnpj;
	}
	
	public EmpresaSICREDI() {};
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String getCaminhoPastaSalvar() {
		return caminhoPastaSalvar;
	}
	
	public void setCaminhoPastaSalvar(String caminhoPastaSalvar) {
		this.caminhoPastaSalvar = caminhoPastaSalvar;
	}

	public Integer getCodEmp() {
		return codEmp;
	}

	public void setCodEmp(Integer codEmp) {
		this.codEmp = codEmp;
	}

	public Integer getCodFil() {
		return codFil;
	}

	public void setCodFil(Integer codFil) {
		this.codFil = codFil;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTrans() {
		return trans;
	}

	public void setTrans(String trans) {
		this.trans = trans;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj, codEmp, codFil, pass, trans, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpresaSICREDI other = (EmpresaSICREDI) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(codEmp, other.codEmp)
				&& Objects.equals(codFil, other.codFil) && Objects.equals(pass, other.pass)
				&& Objects.equals(trans, other.trans) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "EmpresaSICREDI [codEmp=" + codEmp + ", codFil=" + codFil + ", user=" + user + ", pass=" + pass
				+ ", trans=" + trans + ", cnpj=" + cnpj + "]";
	}
	
	public void geraExtratoCorrenteSICREDI() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();
			
			LocalDate dataExtrato = data;

			DateTimeFormatter iniefimFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataParaInieFim = dataExtrato.format(iniefimFormatter);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("extrato");
			
			s = new Screen();
			
			Pattern clicaExtratoBtn = new Pattern(getClass().getResource("/prints/sicredi/clicaextrato.png")).similar(0.6);
			Match mClicaExtrato = s.wait(clicaExtratoBtn, 10);
			Location locClicaExtrato = mClicaExtrato.getTarget();
			robot.mouseMove(locClicaExtrato.getX(), locClicaExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			

			
			while (chromeAdm.confirmaChegadaNaTela("/prints/sicredi/confirmatelaextrato.png") == false) {
				Thread.sleep(1000);
			}
			

			LocalDate dataHoje = LocalDate.now();

			DateTimeFormatter formatoBusca = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataHojeStr = dataHoje.format(formatoBusca);
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type(dataHojeStr);
			
			
			
			Thread.sleep(3000);
			
			s = new Screen();
			
			Pattern dataHojeBtn = new Pattern(getClass().getResource("/prints/sicredi/datahoje.png")).similar(0.6);
			Match mDataHoje = s.wait(dataHojeBtn, 10);
			Location locDataHoje = mDataHoje.getTarget();
			robot.mouseMove(locDataHoje.getX(), locDataHoje.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s.type(dataParaInieFim);
			
			s.keyDown(Key.SHIFT);
			s.type(Key.TAB);
			s.keyUp(Key.SHIFT);
			
			s.type(dataParaInieFim);
			
			Pattern pesquisarBtn = new Pattern(getClass().getResource("/prints/sicredi/pesquisar.png")).similar(0.6);
			Match mPesquisar = s.wait(pesquisarBtn, 10);
			Location locPesquisar = mPesquisar.getTarget();
			robot.mouseMove(locPesquisar.getX(), locPesquisar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("gerar pdf");
			
			Pattern gerarPdfBtn = new Pattern(getClass().getResource("/prints/sicredi/gerarpdf.png")).similar(0.6);
			Match mGerarPdf = s.wait(gerarPdfBtn, 10);
			Location locGerarPdf = mGerarPdf.getTarget();
			robot.mouseMove(locGerarPdf.getX(), locGerarPdf.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(5000);
			

			ArchiveAdm arquivo = new ArchiveAdm();
			
			arquivo.salvarArquivoSICREDI(nomeArquivo, caminhoPastaSalvar, data);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
}