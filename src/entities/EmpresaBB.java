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

public class EmpresaBB {
	
	private Integer codEmp;
	private Integer codFil;
	private String chaveJ;
	private String pass;
	ChromeAdm chromeAdm = new ChromeAdm();
	private String caminhoPastaSalvar;
	private String nomeArquivo;
	private LocalDate data;
	
	public EmpresaBB(Integer codEmp, Integer codFil, String chaveJ, String pass) {
		this.codEmp = codEmp;
		this.codFil = codFil;
		this.chaveJ = chaveJ;
		this.pass = pass;
	}
	
	public EmpresaBB() {};
	
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

	public String getChaveJ() {
		return chaveJ;
	}

	public void setChaveJ(String chaveJ) {
		this.chaveJ = chaveJ;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chaveJ, codEmp, codFil, pass);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpresaBB other = (EmpresaBB) obj;
		return Objects.equals(chaveJ, other.chaveJ) && Objects.equals(codEmp, other.codEmp)
				&& Objects.equals(codFil, other.codFil) && Objects.equals(pass, other.pass);
	}

	@Override
	public String toString() {
		return "Empresa [codEmp=" + codEmp + ", codFil=" + codFil + ", chaveJ=" + chaveJ + ", pass=" + pass + "]";
	}
	
	public void geraExtratoCorrenteBB() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();

			LocalDate dataExtrato = data;

			DateTimeFormatter iniefimFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataParaInieFim = dataExtrato.format(iniefimFormatter);
			
			while (chromeAdm.confirmaChegadaNaTela("/prints/bbloading.png") == true) {
				Thread.sleep(5000);
			}
			
			while (chromeAdm.confirmaChegadaNaTela("/prints/homebb.png") == false) {
				Thread.sleep(5000);
			}

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("extrato de conta corrente"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();
			Pattern extratoBtn = new Pattern(getClass().getResource("/prints/extratodacontacorrente.png")).similar(0.6);

			/* Encontra e clica */
			Match mExtrato = s.wait(extratoBtn, 10);
			Location locExtrato = mExtrato.getTarget();
			robot.mouseMove(locExtrato.getX(), locExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(10000);
			
			while (chromeAdm.confirmaChegadaNaTela("/prints/telaextrato.png") == false) {
				Thread.sleep(3000);
			}

			s = new Screen();
			Pattern categoriaBtn = new Pattern(getClass().getResource("/prints/linhaTempo.png")).similar(0.5);
			Match mCategoria = s.wait(categoriaBtn, 10);
			Location locCategoria = mCategoria.getTarget();
			robot.mouseMove(locCategoria.getX(), locCategoria.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(2000);

			s = new Screen();
			Pattern periodoBtn = new Pattern(getClass().getResource("/prints/periodo.png")).similar(0.5);
			Match mPeriodo = s.wait(periodoBtn, 10);
			Location locPeriodo = mPeriodo.getTarget();
			robot.mouseMove(locPeriodo.getX(), locPeriodo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(2000);

			s = new Screen();
			Pattern inicioBtn = new Pattern(getClass().getResource("/prints/datainicial.png")).similar(0.5);
			Match mInicio = s.wait(inicioBtn, 10);
			Location locInicio = mInicio.getTarget();
			robot.mouseMove(locInicio.getX(), locInicio.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(2000);

			s.type(dataParaInieFim);

			s = new Screen();
			Pattern fimBtn = new Pattern(getClass().getResource("/prints/datafinal.png")).similar(0.5);
			Match mFim = s.wait(fimBtn, 10);
			Location locFim = mFim.getTarget();
			robot.mouseMove(locFim.getX(), locFim.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(2000);

			s.type(dataParaInieFim);

			/* Nova captura */
			s = new Screen();

			Pattern okBtn = new Pattern(getClass().getResource("/prints/ok.png")).similar(0.6);
			Match mOk = s.wait(okBtn, 10);
			Location locOk = mOk.getTarget();
			robot.mouseMove(locOk.getX(), locOk.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(5000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			App.setClipboard("imprimir"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s = new Screen();

			Pattern imprimirBtn = new Pattern(getClass().getResource("/prints/imprimir.png")).similar(0.3);
			Thread.sleep(5000);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();

			Thread.sleep(300);
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(5000);

			ArchiveAdm arquivo = new ArchiveAdm();
			
			arquivo.salvarArquivoBB(nomeArquivo, caminhoPastaSalvar, data);
		
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void geraExtratoCobrancaBB() {
		try {
			Screen s = new Screen();
			Robot robot = new Robot();

			LocalDate dataExtrato = data;

			DateTimeFormatter iniefimFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataParaInieFim = dataExtrato.format(iniefimFormatter);

			while (chromeAdm.confirmaChegadaNaTela("/prints/bbloading.png") == true) {
				Thread.sleep(5000);
			}
			
			while (chromeAdm.confirmaChegadaNaTela("/prints/homebb.png") == false) {
				Thread.sleep(5000);
			}

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("extrato de conta corrente"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();
			Pattern extratoBtn = new Pattern(getClass().getResource("/prints/extratodacontacorrente.png")).similar(0.6);

			/* Encontra e clica */
			Match mExtrato = s.wait(extratoBtn, 10);
			Location locExtrato = mExtrato.getTarget();
			robot.mouseMove(locExtrato.getX(), locExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(10000);

			
			while (chromeAdm.confirmaChegadaNaTela("/prints/telaextrato.png") == false) {
				Thread.sleep(5000);
			}

			s = new Screen();
			Pattern contaBtn = new Pattern(getClass().getResource("/prints/empresa10/conta1.png")).similar(0.5);
			Match mConta = s.wait(contaBtn, 10);
			Location locConta = mConta.getTarget();
			robot.mouseMove(locConta.getX(), locConta.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();
			Pattern conta2Btn = new Pattern(getClass().getResource("/prints/empresa10/conta2.png")).similar(0.5);
			Match mConta2 = s.wait(conta2Btn, 10);
			Location locConta2 = mConta2.getTarget();
			robot.mouseMove(locConta2.getX(), locConta2.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();
			Pattern categoriaBtn = new Pattern(getClass().getResource("/prints/linhaTempo.png")).similar(0.5);
			Match mCategoria = s.wait(categoriaBtn, 10);
			Location locCategoria = mCategoria.getTarget();
			robot.mouseMove(locCategoria.getX(), locCategoria.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(2000);

			s = new Screen();
			Pattern periodoBtn = new Pattern(getClass().getResource("/prints/periodo.png")).similar(0.5);
			Match mPeriodo = s.wait(periodoBtn, 10);
			Location locPeriodo = mPeriodo.getTarget();
			robot.mouseMove(locPeriodo.getX(), locPeriodo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(2000);

			s = new Screen();
			Pattern inicioBtn = new Pattern(getClass().getResource("/prints/datainicial.png")).similar(0.5);
			Match mInicio = s.wait(inicioBtn, 10);
			Location locInicio = mInicio.getTarget();
			robot.mouseMove(locInicio.getX(), locInicio.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(2000);

			s.type(dataParaInieFim);

			s = new Screen();
			Pattern fimBtn = new Pattern(getClass().getResource("/prints/datafinal.png")).similar(0.5);
			Match mFim = s.wait(fimBtn, 10);
			Location locFim = mFim.getTarget();
			robot.mouseMove(locFim.getX(), locFim.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(2000);

			s.type(dataParaInieFim);

			/* Nova captura */
			s = new Screen();

			Pattern okBtn = new Pattern(getClass().getResource("/prints/ok.png")).similar(0.6);
			Match mOk = s.wait(okBtn, 10);
			Location locOk = mOk.getTarget();
			robot.mouseMove(locOk.getX(), locOk.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(5000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("imprimir");

			s = new Screen();

			Pattern imprimirBtn = new Pattern(getClass().getResource("/prints/imprimir.png")).similar(0.3);
			Thread.sleep(5000);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();

			Thread.sleep(300);
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(5000);

			ArchiveAdm arquivo = new ArchiveAdm();
			
			arquivo.salvarArquivoBB(nomeArquivo, caminhoPastaSalvar, data);
			
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
