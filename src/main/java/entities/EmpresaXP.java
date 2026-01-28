package main.java.entities;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import main.java.utils.*;

public class EmpresaXP {
	
	private Integer codEmp;
	private Integer codTip;
	private String cpf;
	private String pass;
	ChromeAdm chromeAdm = new ChromeAdm();
	ArchiveAdm archiveAdm = new ArchiveAdm();
	private String caminhoPastaSalvar;
	private String nomeArquivo;
	private LocalDate data;
	
	public EmpresaXP(Integer codEmp, Integer codTip, String cpf, String pass) {
		this.codEmp = codEmp;
		this.codTip = codTip;
		this.cpf = cpf;
		this.pass = pass;
	}
	
	public EmpresaXP() {};
	
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
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Integer getCodEmp() {
		return codEmp;
	}

	public void setCodEmp(Integer codEmp) {
		this.codEmp = codEmp;
	}

	public Integer getCodTip() {
		return codTip;
	}

	public void setCodTip(Integer codTip) {
		this.codTip = codTip;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, codEmp, codTip, pass);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpresaXP other = (EmpresaXP) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(codEmp, other.codEmp)
				&& Objects.equals(codTip, other.codTip) && Objects.equals(pass, other.pass);
	}

	@Override
	public String toString() {
		return "Empresa [codEmp=" + codEmp + ", codTip=" + codTip + ", cpf=" + cpf + ", pass=" + pass + "]";
	}
	
	public void geraExtratoCorrenteXP() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();

			LocalDate dataExtrato = data;

			DateTimeFormatter iniefimFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataParaInieFim = dataExtrato.format(iniefimFormatter);
			
			while (chromeAdm.confirmaChegadaNaTela("/prints/xp/confabrport.png") == false) {
				Thread.sleep(5000);
			}
			
			s.keyDown(Key.CTRL);
			s.type("p");
			s.keyUp(Key.CTRL);			
			
			archiveAdm.salvarArquivoXPCapa(nomeArquivo + " 1", caminhoPastaSalvar, data);
			

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("investimentos"); 

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();
			Pattern investimentosBtn = archiveAdm.getPatternFromJar("/prints/xp/investimentos.png",0.6);

			/* Encontra e clica */
			Match mInvestimentos = s.wait(investimentosBtn, 10);
			Location locInvestimentos = mInvestimentos.getTarget();
			robot.mouseMove(locInvestimentos.getX(), locInvestimentos.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			
			s.type(Key.DELETE);
			
			
			App.setClipboard("extrato");
			
			s.type("v", KeyModifier.CTRL);
			
			s = new Screen();
			Pattern extratoBtn = archiveAdm.getPatternFromJar("/prints/xp/extrato.png",0.5);
			Match mExtrato = s.wait(extratoBtn, 10);
			Location locExtrato = mExtrato.getTarget();
			robot.mouseMove(locExtrato.getX(), locExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(2000);
			
			while (chromeAdm.confirmaChegadaNaTela("/prints/xp/meuextrato.png") == false) {
				Thread.sleep(5000);
			}
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			
			s.type(Key.DELETE);
			
			
			App.setClipboard("30 dias");
			
			s.type("v", KeyModifier.CTRL);
			
			Thread.sleep(2000);

			s = new Screen();
			Pattern mesBtn = archiveAdm.getPatternFromJar("/prints/xp/30dias.png",0.5);
			Match mMesBtn = s.wait(mesBtn, 10);
			Location locMesBtn = mMesBtn.getTarget();
			robot.mouseMove(locMesBtn.getX(), locMesBtn.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(8000);

			s = new Screen();
			Pattern pdfBtn = archiveAdm.getPatternFromJar("/prints/xp/pdf.png",0.5);
			Match mPdfBtn = s.wait(pdfBtn, 10);
			Location locPdfBtn = mPdfBtn.getTarget();
			robot.mouseMove(locPdfBtn.getX(), locPdfBtn.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(5000);


			s = new Screen();
			Pattern exportarBtn = archiveAdm.getPatternFromJar("/prints/xp/exportar.png",0.5);
			Match mExportarBtn = s.wait(exportarBtn, 10);
			Location locExportarBtn = mExportarBtn.getTarget();
			robot.mouseMove(locExportarBtn.getX(), locExportarBtn.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(8000);
			
			archiveAdm.salvarArquivoXP(nomeArquivo, caminhoPastaSalvar, data);
		
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}


}
