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

public class EmpresaCEF {

	private Integer codEmp;
	private Integer codFil;
	private String user;
	private String pass;
	ChromeAdm chromeAdm = new ChromeAdm();
	private String caminhoPastaSalvar;
	private String nomeArquivo;
	private LocalDate data;

	public EmpresaCEF(Integer codEmp, Integer codFil, String user, String pass) {
		this.codEmp = codEmp;
		this.codFil = codFil;
		this.user = user;
		this.pass = pass;
	}

	public EmpresaCEF() {
	};

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
		EmpresaCEF other = (EmpresaCEF) obj;
		return Objects.equals(codEmp, other.codEmp) && Objects.equals(codFil, other.codFil)
				&& Objects.equals(pass, other.pass) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "EmpresaCEF [codEmp=" + codEmp + ", codFil=" + codFil + ", user=" + user + ", pass=" + pass + "]";
	}

	public void geraExtratoCobranca() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();

			Thread.sleep(5000);

			s = new Screen();
			Pattern saldoEExtratoBtn = new Pattern(getClass().getResource("/prints/cef/saldoseextrato.png"))
					.similar(0.6);
			Match mSaldoEExtrato = s.wait(saldoEExtratoBtn, 10);
			Location locSaldoEExtrato = mSaldoEExtrato.getTarget();
			robot.mouseMove(locSaldoEExtrato.getX(), locSaldoEExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("conta por Período"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s = new Screen();
			Pattern contaPorPeriodoBtn = new Pattern(getClass().getResource("/prints/cef/contaporperiodo.png"))
					.similar(0.6);
			Match mContaPorPeriodo = s.wait(contaPorPeriodoBtn, 10);
			Location locContaPorPeriodo = mContaPorPeriodo.getTarget();
			robot.mouseMove(locContaPorPeriodo.getX(), locContaPorPeriodo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			LocalDate hoje = LocalDate.now();     

			if (hoje.getMonthValue() != data.getMonthValue()) {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				
				App.setClipboard("outro mês"); // copia para clipboard

				// Colar no campo desejado
				s.type("v", KeyModifier.CTRL);
				
				Thread.sleep(1000);

				s = new Screen();
				Pattern outroMesBtn = new Pattern(getClass().getResource("/prints/cef/outromes.png")).similar(0.6);
				Match mOutroMes = s.wait(outroMesBtn, 10);
				Location locOutroMes = mOutroMes.getTarget();
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				s = new Screen();
				Pattern escolhaOMesBtn = new Pattern(getClass().getResource("/prints/cef/escolhaomes.png")).similar(0.6);
				Match mEscolhaOMes = s.wait(escolhaOMesBtn, 10);
				Location locEscolhaOMes = mEscolhaOMes.getTarget();
				robot.mouseMove(locEscolhaOMes.getX(), locEscolhaOMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				if(data.getMonthValue() == 12) {
					s.type("dez");
				}
				
				if(data.getMonthValue() == 11) {
					s.type("nov");
				}
				
				if(data.getMonthValue() == 10) {
					s.type("out");
				}
				
				if(data.getMonthValue() == 9) {
					s.type("set");
				}
				
				if(data.getMonthValue() == 8) {
					s.type("ago");
				}
				
				if(data.getMonthValue() == 7) {
					s.type("jul");
				}
				
				if(data.getMonthValue() == 6) {
					s.type("jul");
				}
				
				if(data.getMonthValue() == 5) {
					s.type("mai");
				}
				
				if(data.getMonthValue() == 4) {
					s.type("abr");
				}
				
				if(data.getMonthValue() == 3) {
					s.type("mar");
				}
				
				if(data.getMonthValue() == 2) {
					s.type("fev");
				}
				
				
				if(data.getMonthValue() == 2) {
					s.type("jan");
				}
				
				Thread.sleep(1000);
				
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				
			} 
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("até"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			s = new Screen();
			Pattern diaAteBtn = new Pattern(getClass().getResource("/prints/cef/diaate.png")).similar(0.6);
			Match mDiaAte = s.wait(diaAteBtn, 10);
			Location locDiaAte = mDiaAte.getTarget();
			robot.mouseMove(locDiaAte.getX(), locDiaAte.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			DateTimeFormatter formatoBusca = DateTimeFormatter.ofPattern("dd");
			String dataStr = data.format(formatoBusca);

			s.type(dataStr);
			Thread.sleep(300);
			s.type(dataStr);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("continuar"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern continuarBtn = new Pattern(getClass().getResource("/prints/cef/continuar.png")).similar(0.6);
			Match mContinuar = s.wait(continuarBtn, 10);
			Location locContinuar = mContinuar.getTarget();
			robot.mouseMove(locContinuar.getX(), locContinuar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("imprimir"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern imprimirBtn = new Pattern(getClass().getResource("/prints/cef/imprimir.png")).similar(0.6);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			ArchiveAdm arquivo = new ArchiveAdm();

			arquivo.salvarArquivoCEF(nomeArquivo, caminhoPastaSalvar, data);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void geraExtratoGarantidaADM() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();

			Thread.sleep(5000);

			s = new Screen();
			Pattern clicaContaAdmBtn = new Pattern(getClass().getResource("/prints/cef/clicacontaadm.png"))
					.similar(0.6);
			Match mClicaContaAdm = s.wait(clicaContaAdmBtn, 10);
			Location locClicaContaAdm = mClicaContaAdm.getTarget();
			robot.mouseMove(locClicaContaAdm.getX(), locClicaContaAdm.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("000577056132-3"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			s = new Screen();
			Pattern contaGarantidaBtn = new Pattern(getClass().getResource("/prints/cef/contagarantidaadm.png"))
					.similar(0.6);
			Match mContaGarantida = s.wait(contaGarantidaBtn, 10);
			Location locContaGarantida = mContaGarantida.getTarget();
			robot.mouseMove(locContaGarantida.getX(), locContaGarantida.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();
			Pattern saldoEExtratoBtn = new Pattern(getClass().getResource("/prints/cef/saldoseextrato.png"))
					.similar(0.6);
			Match mSaldoEExtrato = s.wait(saldoEExtratoBtn, 10);
			Location locSaldoEExtrato = mSaldoEExtrato.getTarget();
			robot.mouseMove(locSaldoEExtrato.getX(), locSaldoEExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("conta por Período"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s = new Screen();
			Pattern contaPorPeriodoBtn = new Pattern(getClass().getResource("/prints/cef/contaporperiodo.png"))
					.similar(0.6);
			Match mContaPorPeriodo = s.wait(contaPorPeriodoBtn, 10);
			Location locContaPorPeriodo = mContaPorPeriodo.getTarget();
			robot.mouseMove(locContaPorPeriodo.getX(), locContaPorPeriodo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			
			LocalDate hoje = LocalDate.now();     

			if (hoje.getMonthValue() != data.getMonthValue()) {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				
				App.setClipboard("outro mês"); // copia para clipboard

				// Colar no campo desejado
				s.type("v", KeyModifier.CTRL);
				
				Thread.sleep(1000);

				s = new Screen();
				Pattern outroMesBtn = new Pattern(getClass().getResource("/prints/cef/outromes.png")).similar(0.6);
				Match mOutroMes = s.wait(outroMesBtn, 10);
				Location locOutroMes = mOutroMes.getTarget();
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				s = new Screen();
				Pattern escolhaOMesBtn = new Pattern(getClass().getResource("/prints/cef/escolhaomes.png")).similar(0.6);
				Match mEscolhaOMes = s.wait(escolhaOMesBtn, 10);
				Location locEscolhaOMes = mEscolhaOMes.getTarget();
				robot.mouseMove(locEscolhaOMes.getX(), locEscolhaOMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				if(data.getMonthValue() == 12) {
					s.type("dez");
				}
				
				if(data.getMonthValue() == 11) {
					s.type("nov");
				}
				
				if(data.getMonthValue() == 10) {
					s.type("out");
				}
				
				if(data.getMonthValue() == 9) {
					s.type("set");
				}
				
				if(data.getMonthValue() == 8) {
					s.type("ago");
				}
				
				if(data.getMonthValue() == 7) {
					s.type("jul");
				}
				
				if(data.getMonthValue() == 6) {
					s.type("jul");
				}
				
				if(data.getMonthValue() == 5) {
					s.type("mai");
				}
				
				if(data.getMonthValue() == 4) {
					s.type("abr");
				}
				
				if(data.getMonthValue() == 3) {
					s.type("mar");
				}
				
				if(data.getMonthValue() == 2) {
					s.type("fev");
				}
				
				
				if(data.getMonthValue() == 2) {
					s.type("jan");
				}
				
				Thread.sleep(1000);
				
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				
			} 
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("até"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			s = new Screen();
			Pattern diaAteBtn = new Pattern(getClass().getResource("/prints/cef/diaate.png")).similar(0.6);
			Match mDiaAte = s.wait(diaAteBtn, 10);
			Location locDiaAte = mDiaAte.getTarget();
			robot.mouseMove(locDiaAte.getX(), locDiaAte.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			DateTimeFormatter formatoBusca = DateTimeFormatter.ofPattern("dd");
			String dataStr = data.format(formatoBusca);

			s.type(dataStr);
			Thread.sleep(300);
			s.type(dataStr);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("continuar"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern continuarBtn = new Pattern(getClass().getResource("/prints/cef/continuar.png")).similar(0.6);
			Match mContinuar = s.wait(continuarBtn, 10);
			Location locContinuar = mContinuar.getTarget();
			robot.mouseMove(locContinuar.getX(), locContinuar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("imprimir"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern imprimirBtn = new Pattern(getClass().getResource("/prints/cef/imprimir.png")).similar(0.6);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			ArchiveAdm arquivo = new ArchiveAdm();

			arquivo.salvarArquivoCEF(nomeArquivo, caminhoPastaSalvar, data);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void geraExtratoGarantidaVIG() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();

			Thread.sleep(5000);

			s = new Screen();
			Pattern clicaContaAdmBtn = new Pattern(getClass().getResource("/prints/cef/clicacontavig.png"))
					.similar(0.6);
			Match mClicaContaAdm = s.wait(clicaContaAdmBtn, 10);
			Location locClicaContaAdm = mClicaContaAdm.getTarget();
			robot.mouseMove(locClicaContaAdm.getX(), locClicaContaAdm.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("000577056129-3"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			s = new Screen();
			Pattern contaGarantidaBtn = new Pattern(getClass().getResource("/prints/cef/contagarantidavig.png"))
					.similar(0.6);
			Match mContaGarantida = s.wait(contaGarantidaBtn, 10);
			Location locContaGarantida = mContaGarantida.getTarget();
			robot.mouseMove(locContaGarantida.getX(), locContaGarantida.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();
			Pattern saldoEExtratoBtn = new Pattern(getClass().getResource("/prints/cef/saldoseextrato.png"))
					.similar(0.6);
			Match mSaldoEExtrato = s.wait(saldoEExtratoBtn, 10);
			Location locSaldoEExtrato = mSaldoEExtrato.getTarget();
			robot.mouseMove(locSaldoEExtrato.getX(), locSaldoEExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("conta por Período"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s = new Screen();
			Pattern contaPorPeriodoBtn = new Pattern(getClass().getResource("/prints/cef/contaporperiodo.png"))
					.similar(0.6);
			Match mContaPorPeriodo = s.wait(contaPorPeriodoBtn, 10);
			Location locContaPorPeriodo = mContaPorPeriodo.getTarget();
			robot.mouseMove(locContaPorPeriodo.getX(), locContaPorPeriodo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			LocalDate hoje = LocalDate.now();     

			if (hoje.getMonthValue() != data.getMonthValue()) {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				
				App.setClipboard("outro mês"); // copia para clipboard

				// Colar no campo desejado
				s.type("v", KeyModifier.CTRL);
				
				Thread.sleep(1000);

				s = new Screen();
				Pattern outroMesBtn = new Pattern(getClass().getResource("/prints/cef/outromes.png")).similar(0.6);
				Match mOutroMes = s.wait(outroMesBtn, 10);
				Location locOutroMes = mOutroMes.getTarget();
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				s = new Screen();
				Pattern escolhaOMesBtn = new Pattern(getClass().getResource("/prints/cef/escolhaomes.png")).similar(0.6);
				Match mEscolhaOMes = s.wait(escolhaOMesBtn, 10);
				Location locEscolhaOMes = mEscolhaOMes.getTarget();
				robot.mouseMove(locEscolhaOMes.getX(), locEscolhaOMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				if(data.getMonthValue() == 12) {
					s.type("dez");
				}
				
				if(data.getMonthValue() == 11) {
					s.type("nov");
				}
				
				if(data.getMonthValue() == 10) {
					s.type("out");
				}
				
				if(data.getMonthValue() == 9) {
					s.type("set");
				}
				
				if(data.getMonthValue() == 8) {
					s.type("ago");
				}
				
				if(data.getMonthValue() == 7) {
					s.type("jul");
				}
				
				if(data.getMonthValue() == 6) {
					s.type("jul");
				}
				
				if(data.getMonthValue() == 5) {
					s.type("mai");
				}
				
				if(data.getMonthValue() == 4) {
					s.type("abr");
				}
				
				if(data.getMonthValue() == 3) {
					s.type("mar");
				}
				
				if(data.getMonthValue() == 2) {
					s.type("fev");
				}
				
				
				if(data.getMonthValue() == 2) {
					s.type("jan");
				}
				
				Thread.sleep(1000);
				
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				
			} 
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("até"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			s = new Screen();
			Pattern diaAteBtn = new Pattern(getClass().getResource("/prints/cef/diaate.png")).similar(0.6);
			Match mDiaAte = s.wait(diaAteBtn, 10);
			Location locDiaAte = mDiaAte.getTarget();
			robot.mouseMove(locDiaAte.getX(), locDiaAte.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			DateTimeFormatter formatoBusca = DateTimeFormatter.ofPattern("dd");
			String dataStr = data.format(formatoBusca);

			s.type(dataStr);
			Thread.sleep(300);
			s.type(dataStr);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("continuar"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern continuarBtn = new Pattern(getClass().getResource("/prints/cef/continuar.png")).similar(0.6);
			Match mContinuar = s.wait(continuarBtn, 10);
			Location locContinuar = mContinuar.getTarget();
			robot.mouseMove(locContinuar.getX(), locContinuar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("imprimir"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern imprimirBtn = new Pattern(getClass().getResource("/prints/cef/imprimir.png")).similar(0.6);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			ArchiveAdm arquivo = new ArchiveAdm();

			arquivo.salvarArquivoCEF(nomeArquivo, caminhoPastaSalvar, data);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void geraExtratoCorrenteVIG() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();

			Thread.sleep(5000);

			s = new Screen();
			Pattern clicaContaAdmBtn = new Pattern(getClass().getResource("/prints/cef/clicacontavig.png"))
					.similar(0.6);
			Match mClicaContaAdm = s.wait(clicaContaAdmBtn, 10);
			Location locClicaContaAdm = mClicaContaAdm.getTarget();
			robot.mouseMove(locClicaContaAdm.getX(), locClicaContaAdm.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("000577056193-5"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			s = new Screen();
			Pattern contaGarantidaBtn = new Pattern(getClass().getResource("/prints/cef/contacorrentevig.png"))
					.similar(0.6);
			Match mContaGarantida = s.wait(contaGarantidaBtn, 10);
			Location locContaGarantida = mContaGarantida.getTarget();
			robot.mouseMove(locContaGarantida.getX(), locContaGarantida.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();
			Pattern saldoEExtratoBtn = new Pattern(getClass().getResource("/prints/cef/saldoseextrato.png"))
					.similar(0.6);
			Match mSaldoEExtrato = s.wait(saldoEExtratoBtn, 10);
			Location locSaldoEExtrato = mSaldoEExtrato.getTarget();
			robot.mouseMove(locSaldoEExtrato.getX(), locSaldoEExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("conta por Período"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s = new Screen();
			Pattern contaPorPeriodoBtn = new Pattern(getClass().getResource("/prints/cef/contaporperiodo.png"))
					.similar(0.6);
			Match mContaPorPeriodo = s.wait(contaPorPeriodoBtn, 10);
			Location locContaPorPeriodo = mContaPorPeriodo.getTarget();
			robot.mouseMove(locContaPorPeriodo.getX(), locContaPorPeriodo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			
			LocalDate hoje = LocalDate.now();     

			if (hoje.getMonthValue() != data.getMonthValue()) {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				
				App.setClipboard("outro mês"); // copia para clipboard

				// Colar no campo desejado
				s.type("v", KeyModifier.CTRL);
				
				Thread.sleep(1000);

				s = new Screen();
				Pattern outroMesBtn = new Pattern(getClass().getResource("/prints/cef/outromes.png")).similar(0.6);
				Match mOutroMes = s.wait(outroMesBtn, 10);
				Location locOutroMes = mOutroMes.getTarget();
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				s = new Screen();
				Pattern escolhaOMesBtn = new Pattern(getClass().getResource("/prints/cef/escolhaomes.png")).similar(0.6);
				Match mEscolhaOMes = s.wait(escolhaOMesBtn, 10);
				Location locEscolhaOMes = mEscolhaOMes.getTarget();
				robot.mouseMove(locEscolhaOMes.getX(), locEscolhaOMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				if(data.getMonthValue() == 12) {
					s.type("dez");
				}
				
				if(data.getMonthValue() == 11) {
					s.type("nov");
				}
				
				if(data.getMonthValue() == 10) {
					s.type("out");
				}
				
				if(data.getMonthValue() == 9) {
					s.type("set");
				}
				
				if(data.getMonthValue() == 8) {
					s.type("ago");
				}
				
				if(data.getMonthValue() == 7) {
					s.type("jul");
				}
				
				if(data.getMonthValue() == 6) {
					s.type("jul");
				}
				
				if(data.getMonthValue() == 5) {
					s.type("mai");
				}
				
				if(data.getMonthValue() == 4) {
					s.type("abr");
				}
				
				if(data.getMonthValue() == 3) {
					s.type("mar");
				}
				
				if(data.getMonthValue() == 2) {
					s.type("fev");
				}
				
				
				if(data.getMonthValue() == 2) {
					s.type("jan");
				}
				
				Thread.sleep(1000);
				
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				
			} 
			
			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("até"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			s = new Screen();
			Pattern diaAteBtn = new Pattern(getClass().getResource("/prints/cef/diaate.png")).similar(0.6);
			Match mDiaAte = s.wait(diaAteBtn, 10);
			Location locDiaAte = mDiaAte.getTarget();
			robot.mouseMove(locDiaAte.getX(), locDiaAte.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			DateTimeFormatter formatoBusca = DateTimeFormatter.ofPattern("dd");
			String dataStr = data.format(formatoBusca);

			s.type(dataStr);
			Thread.sleep(300);
			s.type(dataStr);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("continuar"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern continuarBtn = new Pattern(getClass().getResource("/prints/cef/continuar.png")).similar(0.6);
			Match mContinuar = s.wait(continuarBtn, 10);
			Location locContinuar = mContinuar.getTarget();
			robot.mouseMove(locContinuar.getX(), locContinuar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("imprimir"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern imprimirBtn = new Pattern(getClass().getResource("/prints/cef/imprimir.png")).similar(0.6);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			ArchiveAdm arquivo = new ArchiveAdm();

			arquivo.salvarArquivoCEF(nomeArquivo, caminhoPastaSalvar, data);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void geraExtratoCorrenteADM() {

		try {
			Screen s = new Screen();
			Robot robot = new Robot();

			Thread.sleep(5000);

			s = new Screen();
			Pattern clicaContaAdmBtn = new Pattern(getClass().getResource("/prints/cef/clicacontavig.png"))
					.similar(0.6);
			Match mClicaContaAdm = s.wait(clicaContaAdmBtn, 10);
			Location locClicaContaAdm = mClicaContaAdm.getTarget();
			robot.mouseMove(locClicaContaAdm.getX(), locClicaContaAdm.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("000577056225-7"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			s = new Screen();
			Pattern contaGarantidaBtn = new Pattern(getClass().getResource("/prints/cef/contacorrenteadm.png"))
					.similar(0.6);
			Match mContaGarantida = s.wait(contaGarantidaBtn, 10);
			Location locContaGarantida = mContaGarantida.getTarget();
			robot.mouseMove(locContaGarantida.getX(), locContaGarantida.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();
			Pattern saldoEExtratoBtn = new Pattern(getClass().getResource("/prints/cef/saldoseextrato.png"))
					.similar(0.6);
			Match mSaldoEExtrato = s.wait(saldoEExtratoBtn, 10);
			Location locSaldoEExtrato = mSaldoEExtrato.getTarget();
			robot.mouseMove(locSaldoEExtrato.getX(), locSaldoEExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("conta por Período"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s = new Screen();
			Pattern contaPorPeriodoBtn = new Pattern(getClass().getResource("/prints/cef/contaporperiodo.png"))
					.similar(0.6);
			Match mContaPorPeriodo = s.wait(contaPorPeriodoBtn, 10);
			Location locContaPorPeriodo = mContaPorPeriodo.getTarget();
			robot.mouseMove(locContaPorPeriodo.getX(), locContaPorPeriodo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			LocalDate hoje = LocalDate.now();     

			if (hoje.getMonthValue() != data.getMonthValue()) {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				
				App.setClipboard("outro mês"); // copia para clipboard

				// Colar no campo desejado
				s.type("v", KeyModifier.CTRL);
				
				Thread.sleep(1000);

				s = new Screen();
				Pattern outroMesBtn = new Pattern(getClass().getResource("/prints/cef/outromes.png")).similar(0.6);
				Match mOutroMes = s.wait(outroMesBtn, 10);
				Location locOutroMes = mOutroMes.getTarget();
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				s = new Screen();
				Pattern escolhaOMesBtn = new Pattern(getClass().getResource("/prints/cef/escolhaomes.png")).similar(0.6);
				Match mEscolhaOMes = s.wait(escolhaOMesBtn, 10);
				Location locEscolhaOMes = mEscolhaOMes.getTarget();
				robot.mouseMove(locEscolhaOMes.getX(), locEscolhaOMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				if(data.getMonthValue() == 12) {
					s.type("dez");
				}
				
				if(data.getMonthValue() == 11) {
					s.type("nov");
				}
				
				if(data.getMonthValue() == 10) {
					s.type("out");
				}
				
				if(data.getMonthValue() == 9) {
					s.type("set");
				}
				
				if(data.getMonthValue() == 8) {
					s.type("ago");
				}
				
				if(data.getMonthValue() == 7) {
					s.type("jul");
				}
				
				if(data.getMonthValue() == 6) {
					s.type("jul");
				}
				
				if(data.getMonthValue() == 5) {
					s.type("mai");
				}
				
				if(data.getMonthValue() == 4) {
					s.type("abr");
				}
				
				if(data.getMonthValue() == 3) {
					s.type("mar");
				}
				
				if(data.getMonthValue() == 2) {
					s.type("fev");
				}
				
				
				if(data.getMonthValue() == 2) {
					s.type("jan");
				}
				
				Thread.sleep(1000);
				
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
				
				
			} 
			

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("até"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			s = new Screen();
			Pattern diaAteBtn = new Pattern(getClass().getResource("/prints/cef/diaate.png")).similar(0.6);
			Match mDiaAte = s.wait(diaAteBtn, 10);
			Location locDiaAte = mDiaAte.getTarget();
			robot.mouseMove(locDiaAte.getX(), locDiaAte.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			DateTimeFormatter formatoBusca = DateTimeFormatter.ofPattern("dd");
			String dataStr = data.format(formatoBusca);

			s.type(dataStr);
			Thread.sleep(300);
			s.type(dataStr);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("continuar"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern continuarBtn = new Pattern(getClass().getResource("/prints/cef/continuar.png")).similar(0.6);
			Match mContinuar = s.wait(continuarBtn, 10);
			Location locContinuar = mContinuar.getTarget();
			robot.mouseMove(locContinuar.getX(), locContinuar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("imprimir"); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern imprimirBtn = new Pattern(getClass().getResource("/prints/cef/imprimir.png")).similar(0.6);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			ArchiveAdm arquivo = new ArchiveAdm();

			arquivo.salvarArquivoCEF(nomeArquivo, caminhoPastaSalvar, data);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
