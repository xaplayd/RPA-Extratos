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

public class EmpresaABC {
	
	private Integer codEmp;
	private Integer codFil;
	private String user;
	private String pass;
	ChromeAdm chromeAdm = new ChromeAdm();
	private String caminhoPastaSalvar;
	private LocalDate data;
	private String nomeArquivo;
	
	public EmpresaABC(Integer codEmp, Integer codFil, String user, String pass) {
		this.codEmp = codEmp;
		this.codFil = codFil;
		this.user = user;
		this.pass = pass;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public EmpresaABC() {};
	
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

	@Override
	public int hashCode() {
		return Objects.hash(codEmp, codFil, pass, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpresaABC other = (EmpresaABC) obj;
		return Objects.equals(codEmp, other.codEmp) && Objects.equals(codFil, other.codFil)
				&& Objects.equals(pass, other.pass) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "EmpresaABC [codEmp=" + codEmp + ", codFil=" + codFil + ", user=" + user + ", pass=" + pass + "]";
	}
	
	

	public void geraExtratoCorrenteABC() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();
			
			LocalDate dataExtrato = data;

			DateTimeFormatter iniefimFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataParaInieFim = dataExtrato.format(iniefimFormatter);

			Pattern consultaExtratoBtn = new Pattern(getClass().getResource("/prints/abc/consultaextrato.png")).similar(0.6);
			Match mConsultaExtrato = s.wait(consultaExtratoBtn, 10);
			Location locConsultaExtrato = mConsultaExtrato.getTarget();
			robot.mouseMove(locConsultaExtrato.getX(), locConsultaExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(10000);
			
			while (chromeAdm.confirmaChegadaNaTela("/prints/abc/confirmatelaextrato.png") == false) {
				Thread.sleep(1000);
			}
			
			
			s = new Screen();
			Pattern hojeBtn = new Pattern(getClass().getResource("/prints/abc/hoje.png")).similar(0.6);
			Match mHoje = s.wait(hojeBtn, 10);
			Location locHoje = mHoje.getTarget();
			robot.mouseMove(locHoje.getX(), locHoje.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type(dataParaInieFim);
			s.type(Key.TAB);
			s.type(dataParaInieFim);
			
			
			s = new Screen();
			Pattern detalhadoBtn = new Pattern(getClass().getResource("/prints/abc/detalhado.png")).similar(0.6);
			Match mDetalhado = s.wait(detalhadoBtn, 10);
			Location locDetalhado = mDetalhado.getTarget();
			robot.mouseMove(locDetalhado.getX(), locDetalhado.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s = new Screen();
			Pattern filtrarBtn = new Pattern(getClass().getResource("/prints/abc/filtrar.png")).similar(0.6);
			Match mFiltrar = s.wait(filtrarBtn, 10);
			Location locFiltrar = mFiltrar.getTarget();
			robot.mouseMove(locFiltrar.getX(), locFiltrar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(10000);
			
			s = new Screen();
			Pattern baixarBtn = new Pattern(getClass().getResource("/prints/abc/baixar.png")).similar(0.6);
			Match mBaixar = s.wait(baixarBtn, 10);
			Location locBaixar = mBaixar.getTarget();
			robot.mouseMove(locBaixar.getX(), locBaixar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s = new Screen();
			Pattern pdfBtn = new Pattern(getClass().getResource("/prints/abc/pdf.png")).similar(0.6);
			Match mPdf = s.wait(pdfBtn, 10);
			Location locPdf = mPdf.getTarget();
			robot.mouseMove(locPdf.getX(), locPdf.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(10000);

			ArchiveAdm arquivo = new ArchiveAdm();
			
			arquivo.salvarArquivoABC(nomeArquivo, caminhoPastaSalvar, data);
			
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	

}
