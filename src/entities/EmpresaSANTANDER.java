package entities;

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

import utils.ArchiveAdm;
import utils.ChromeAdm;

public class EmpresaSANTANDER {
	
	private Integer codEmp;
	private Integer codFil;
	private String agencia;
	private String conta;
	private String user;
	private String pass;
	ChromeAdm chromeAdm = new ChromeAdm();
	private String caminhoPastaSalvar;
	private String nomeArquivo;
	private LocalDate data;
	
	public EmpresaSANTANDER(Integer codEmp, Integer codFil, String agencia, String conta, String user, String pass) {
		this.codEmp = codEmp;
		this.codFil = codFil;
		this.agencia = agencia;
		this.conta = conta;
		this.user = user;
		this.pass = pass;
	}
	
	public EmpresaSANTANDER() {}
	
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

	public Integer getCodFil() {
		return codFil;
	}

	public void setCodFil(Integer codFil) {
		this.codFil = codFil;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
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
		return Objects.hash(agencia, codEmp, codFil, conta, pass, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpresaSANTANDER other = (EmpresaSANTANDER) obj;
		return Objects.equals(agencia, other.agencia) && Objects.equals(codEmp, other.codEmp)
				&& Objects.equals(codFil, other.codFil) && Objects.equals(conta, other.conta)
				&& Objects.equals(pass, other.pass) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "EmpresaSANTANDER [codEmp=" + codEmp + ", codFil=" + codFil + ", agencia=" + agencia + ", conta=" + conta
				+ ", user=" + user + ", pass=" + pass + "]";
	}

	public void geraExtratoCorrenteSANTANDERadm() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();

			Pattern opcoesContaBtn = new Pattern(getClass().getResource("/prints/santander/opcoesconta.png")).similar(0.6);
			Match mOpcoesConta = s.wait(opcoesContaBtn, 10);
			Location locOpcoesConta = mOpcoesConta.getTarget();
			robot.mouseMove(locOpcoesConta.getX(), locOpcoesConta.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			robot.mouseMove(0, 0);
			
			Thread.sleep(3000);
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			
			App.setClipboard("consultar"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			
			
			s = new Screen();
			Pattern consultarBtn = new Pattern(getClass().getResource("/prints/santander/consultar.png")).similar(0.6);
			Match mConsultar = s.wait(consultarBtn, 10);
			Location locConsultar = mConsultar.getTarget();
			robot.mouseMove(locConsultar.getX(), locConsultar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s = new Screen();
			Pattern editaDataBtn = new Pattern(getClass().getResource("/prints/santander/editadata.png")).similar(0.6);
			Match mEditaData = s.wait(editaDataBtn, 10);
			Location locEditaData = mEditaData.getTarget();
			robot.mouseMove(locEditaData.getX(), locEditaData.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			
			
			
			DateTimeFormatter formatoBusca = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataStr = data.format(formatoBusca);

			
			s.type(dataStr);
			
			Thread.sleep(300);
			
			s.type(" - ");
			
			s.type(dataStr);
			
			
			
			s = new Screen();
			Pattern okBtn = new Pattern(getClass().getResource("/prints/santander/ok.png")).similar(0.6);
			Match mOk = s.wait(okBtn, 10);
			Location locOk = mOk.getTarget();
			robot.mouseMove(locOk.getX(), locOk.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			

			App.setClipboard("salvar em pdf"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			
			s.type(Key.ENTER);
			
			s = new Screen();
			Pattern salvarEmPdfBtn = new Pattern(getClass().getResource("/prints/santander/salvarempdf.png")).similar(0.6);
			Match mSalvarEmPdf = s.exists(salvarEmPdfBtn, 10);
			Location locSalvarEmPdf = mSalvarEmPdf.getTarget();
				
			robot.mouseMove(locSalvarEmPdf.getX(), locSalvarEmPdf.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			
	

			ArchiveAdm arquivo = new ArchiveAdm();
			
			arquivo.salvarArquivoSANTANDER(nomeArquivo, caminhoPastaSalvar, data);
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void geraExtratoCorrenteSANTANDERquality() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			
			App.setClipboard("trocar conta"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			
			s = new Screen();
			Pattern trocaContaBtn = new Pattern(getClass().getResource("/prints/santander/trocarconta.png")).similar(0.6);
			Match mTrocaConta = s.wait(trocaContaBtn, 10);
			Location locTrocaConta = mTrocaConta.getTarget();
			robot.mouseMove(locTrocaConta.getX(), locTrocaConta.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s = new Screen();
			Pattern contaQualityBtn = new Pattern(getClass().getResource("/prints/santander/contaquality.png")).similar(0.6);
			Match mContaQuality = s.wait(contaQualityBtn, 10);
			Location locContaQuality = mContaQuality.getTarget();
			robot.mouseMove(locContaQuality.getX(), locContaQuality.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			
			App.setClipboard("ok, entendi"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			
			Thread.sleep(5000);
			
			s = new Screen();
			Pattern okEntendiBtn = new Pattern(getClass().getResource("/prints/santander/okentendi.png")).similar(0.6);
			Match mOkEntendi = s.exists(okEntendiBtn, 10);
			Location locOkEntendi;
			if(mOkEntendi != null) {
			locOkEntendi = mOkEntendi.getTarget();
			robot.mouseMove(locOkEntendi.getX(), locOkEntendi.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			}
			

			Pattern opcoesContaBtn = new Pattern(getClass().getResource("/prints/santander/opcoesconta.png")).similar(0.6);
			Match mOpcoesConta = s.wait(opcoesContaBtn, 10);
			Location locOpcoesConta = mOpcoesConta.getTarget();
			robot.mouseMove(locOpcoesConta.getX(), locOpcoesConta.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			robot.mouseMove(0, 0);
			
			Thread.sleep(3000);
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			
			App.setClipboard("consultar"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			
			
			s = new Screen();
			Pattern consultarBtn = new Pattern(getClass().getResource("/prints/santander/consultar.png")).similar(0.6);
			Match mConsultar = s.wait(consultarBtn, 10);
			Location locConsultar = mConsultar.getTarget();
			robot.mouseMove(locConsultar.getX(), locConsultar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s = new Screen();
			Pattern editaDataBtn = new Pattern(getClass().getResource("/prints/santander/editadata.png")).similar(0.6);
			Match mEditaData = s.wait(editaDataBtn, 10);
			Location locEditaData = mEditaData.getTarget();
			robot.mouseMove(locEditaData.getX(), locEditaData.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			
			
			
			DateTimeFormatter formatoBusca = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataStr = data.format(formatoBusca);

			
			s.type(dataStr);
			
			Thread.sleep(300);
			
			s.type(" - ");
			
			s.type(dataStr);
			
			
			
			s = new Screen();
			Pattern okBtn = new Pattern(getClass().getResource("/prints/santander/ok.png")).similar(0.6);
			Match mOk = s.wait(okBtn, 10);
			Location locOk = mOk.getTarget();
			robot.mouseMove(locOk.getX(), locOk.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			

			App.setClipboard("salvar em pdf"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			
			s.type(Key.ENTER);
			
			s = new Screen();
			Pattern salvarEmPdfBtn = new Pattern(getClass().getResource("/prints/santander/salvarempdf.png")).similar(0.6);
			Match mSalvarEmPdf = s.exists(salvarEmPdfBtn, 10);
			Location locSalvarEmPdf = mSalvarEmPdf.getTarget();
				
			robot.mouseMove(locSalvarEmPdf.getX(), locSalvarEmPdf.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			
	

			ArchiveAdm arquivo = new ArchiveAdm();
			
			arquivo.salvarArquivoSANTANDER(nomeArquivo, caminhoPastaSalvar, data);
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void geraExtratoCorrenteSANTANDERvig() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			
			App.setClipboard("trocar conta"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			
			s = new Screen();
			Pattern trocaContaBtn = new Pattern(getClass().getResource("/prints/santander/trocarconta.png")).similar(0.6);
			Match mTrocaConta = s.wait(trocaContaBtn, 10);
			Location locTrocaConta = mTrocaConta.getTarget();
			robot.mouseMove(locTrocaConta.getX(), locTrocaConta.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s = new Screen();
			Pattern contaQualityBtn = new Pattern(getClass().getResource("/prints/santander/contavig.png")).similar(0.6);
			Match mContaQuality = s.wait(contaQualityBtn, 10);
			Location locContaQuality = mContaQuality.getTarget();
			robot.mouseMove(locContaQuality.getX(), locContaQuality.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			
			Thread.sleep(5000);
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			
			App.setClipboard("ok, entendi"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			
			s = new Screen();
			Pattern okEntendiBtn = new Pattern(getClass().getResource("/prints/santander/okentendi.png")).similar(0.6);
			Match mOkEntendi = s.exists(okEntendiBtn, 10);
			Location locOkEntendi;
			Thread.sleep(5000);
			if(mOkEntendi != null) {
			locOkEntendi = mOkEntendi.getTarget();
			robot.mouseMove(locOkEntendi.getX(), locOkEntendi.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			}
			
			Thread.sleep(7000);
			
			s = new Screen();
			Pattern opcoesContaBtn = new Pattern(getClass().getResource("/prints/santander/opcoesconta.png")).similar(0.6);
			Match mOpcoesConta = s.wait(opcoesContaBtn, 10);
			Location locOpcoesConta = mOpcoesConta.getTarget();
			robot.mouseMove(locOpcoesConta.getX(), locOpcoesConta.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			robot.mouseMove(0, 0);
			
			Thread.sleep(3000);
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			
			App.setClipboard("consultar"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			
			
			s = new Screen();
			Pattern consultarBtn = new Pattern(getClass().getResource("/prints/santander/consultar.png")).similar(0.6);
			Match mConsultar = s.wait(consultarBtn, 10);
			Location locConsultar = mConsultar.getTarget();
			robot.mouseMove(locConsultar.getX(), locConsultar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s = new Screen();
			Pattern editaDataBtn = new Pattern(getClass().getResource("/prints/santander/editadata.png")).similar(0.6);
			Match mEditaData = s.wait(editaDataBtn, 10);
			Location locEditaData = mEditaData.getTarget();
			robot.mouseMove(locEditaData.getX(), locEditaData.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			s.type(Key.BACKSPACE);
			
			
			
			DateTimeFormatter formatoBusca = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataStr = data.format(formatoBusca);

			
			s.type(dataStr);
			
			Thread.sleep(300);
			
			s.type(" - ");
			
			s.type(dataStr);
			
			
			
			s = new Screen();
			Pattern okBtn = new Pattern(getClass().getResource("/prints/santander/ok.png")).similar(0.6);
			Match mOk = s.wait(okBtn, 10);
			Location locOk = mOk.getTarget();
			robot.mouseMove(locOk.getX(), locOk.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			

			App.setClipboard("salvar em pdf"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			
			s.type(Key.ENTER);
			
			s = new Screen();
			Pattern salvarEmPdfBtn = new Pattern(getClass().getResource("/prints/santander/salvarempdf.png")).similar(0.6);
			Match mSalvarEmPdf = s.exists(salvarEmPdfBtn, 10);
			Location locSalvarEmPdf = mSalvarEmPdf.getTarget();
				
			robot.mouseMove(locSalvarEmPdf.getX(), locSalvarEmPdf.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			
	

			ArchiveAdm arquivo = new ArchiveAdm();
			
			arquivo.salvarArquivoSANTANDER(nomeArquivo, caminhoPastaSalvar, data);
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

}
