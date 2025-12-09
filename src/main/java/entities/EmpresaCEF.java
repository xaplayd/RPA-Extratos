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

public class EmpresaCEF {

	private Integer codEmp;
	private Integer codFil;
	private String user;
	private String pass;
	ChromeAdm chromeAdm = new ChromeAdm();
	ArchiveAdm archiveAdm = new ArchiveAdm();
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
			Pattern saldoEExtratoBtn = archiveAdm.getPatternFromJar("/prints/cef/saldoseextrato.png", 0.6);
			Match mSaldoEExtrato = s.wait(saldoEExtratoBtn, 10);
			Location locSaldoEExtrato = mSaldoEExtrato.getTarget();
			robot.mouseMove(locSaldoEExtrato.getX(), locSaldoEExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			/*
			 * s.keyDown(Key.CTRL); s.type("f"); s.keyUp(Key.CTRL);
			 * 
			 * App.setClipboard("extrato caixa");
			 * 
			 * // Colar no campo desejado s.type("v", KeyModifier.CTRL);
			 * 
			 * s = new Screen(); Pattern extratoCaixaBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/extratocaixa.png",0.6); Match
			 * mExtratoCaixa = s.wait(extratoCaixaBtn, 10); Location locExtratoCaixa =
			 * mExtratoCaixa.getTarget(); robot.mouseMove(locExtratoCaixa.getX(),
			 * locExtratoCaixa.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * s.keyDown(Key.CTRL); s.type("f"); s.keyUp(Key.CTRL);
			 * 
			 * App.setClipboard("outro");
			 * 
			 * // Colar no campo desejado s.type("v", KeyModifier.CTRL);
			 * 
			 * Thread.sleep(3000);
			 * 
			 * s = new Screen(); Pattern outroPeriodoBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/outroperiodo.png",0.6); Match
			 * mOutroPeriodo = s.wait(outroPeriodoBtn, 10); Location locOutroPeriodo =
			 * mOutroPeriodo.getTarget(); robot.mouseMove(locOutroPeriodo.getX(),
			 * locOutroPeriodo.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * s = new Screen(); Pattern dataInicialBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/datainicial.png",0.6); Match
			 * mDataInicial = s.wait(dataInicialBtn, 10); Location locDataInicial =
			 * mDataInicial.getTarget(); robot.mouseMove(locDataInicial.getX(),
			 * locDataInicial.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * DateTimeFormatter formatoFiltro = DateTimeFormatter.ofPattern("ddMMyyyy");
			 * String dataStr = data.format(formatoFiltro);
			 * 
			 * s.type(dataStr); s.type(Key.TAB); s.type(Key.TAB);
			 * 
			 * s.type(dataStr);
			 * 
			 * Thread.sleep(7000);
			 * 
			 * s = new Screen(); Pattern exportarPdfBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/exportarpdf.png",0.6); Match
			 * mExportarPdf = s.wait(exportarPdfBtn, 10); Location locExportarPdf =
			 * mExportarPdf.getTarget(); robot.mouseMove(locExportarPdf.getX(),
			 * locExportarPdf.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(12000);
			 */

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("extrato por período");

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern extratoPorPeriodoBtn = archiveAdm.getPatternFromJar("/prints/cef/extratoporperiodo.png", 0.6);
			Match mExtratoPorPeriodo = s.wait(extratoPorPeriodoBtn, 10);
			Location locExtratoPorPeriodo = mExtratoPorPeriodo.getTarget();
			robot.mouseMove(locExtratoPorPeriodo.getX(), locExtratoPorPeriodo.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(3000);

			DateTimeFormatter formatoFiltroMes = DateTimeFormatter.ofPattern("MM");
			Integer mesStr = Integer.parseInt(data.format(formatoFiltroMes));

			LocalDate hoje = LocalDate.now();
			Integer mesHojeStr = Integer.parseInt(hoje.format(formatoFiltroMes));

			if (mesStr != mesHojeStr) {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);

				App.setClipboard("outro mês");

				// Colar no campo desejado
				s.type("v", KeyModifier.CTRL);

				Thread.sleep(3000);

				s = new Screen();
				Pattern outroMesBtn = archiveAdm.getPatternFromJar("/prints/cef/outromes.png", 0.6);
				Match mOutroMes = s.wait(outroMesBtn, 10);
				Location locOutroMes = mOutroMes.getTarget();
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

				Thread.sleep(3000);

				s = new Screen();

				Pattern escolhaOMesBtn = archiveAdm.getPatternFromJar("/prints/cef/escolhaomes.png", 0.6);
				Match mEscolhaOMes = s.wait(escolhaOMesBtn, 10);
				Location locEscolhaOMes = mEscolhaOMes.getTarget();
				robot.mouseMove(locEscolhaOMes.getX(), locEscolhaOMes.getY());
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(5000);

				if (mesStr == 12) {
					s.type("dez");
				}
				if (mesStr == 11) {
					s.type("nov");
				}
				if (mesStr == 10) {
					s.type("out");
				}
				if (mesStr == 9) {
					s.type("set");
				}
				if (mesStr == 8) {
					s.type("ago");
				}
				if (mesStr == 7) {
					s.type("jul");
				}
				if (mesStr == 6) {
					s.type("jun");
				}
				if (mesStr == 5) {
					s.type("mai");
				}
				if (mesStr == 4) {
					s.type("abr");
				}
				if (mesStr == 3) {
					s.type("mar");
				}
				if (mesStr == 2) {
					s.type("fev");
				}
				if (mesStr == 1) {
					s.type("jan");
				}

				s.type(Key.ENTER);

				Thread.sleep(3000);

			}

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("até");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern diaAteBtn = archiveAdm.getPatternFromJar("/prints/cef/diaate.png", 0.6);
			Match mDiaAte = s.wait(diaAteBtn, 10);
			Location locDiaAte = mDiaAte.getTarget();
			robot.mouseMove(locDiaAte.getX(), locDiaAte.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			DateTimeFormatter formatoFiltroDia = DateTimeFormatter.ofPattern("dd");
			String diaStr = data.format(formatoFiltroDia);

			s.type(diaStr);
			Thread.sleep(300);
			s.type(diaStr);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			App.setClipboard("continuar");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(7000);

			s = new Screen();
			Pattern continuarBtn = archiveAdm.getPatternFromJar("/prints/cef/continuar.png", 0.6);
			Match mContinuar = s.wait(continuarBtn, 10);
			Location locContinuar = mContinuar.getTarget();
			robot.mouseMove(locContinuar.getX(), locContinuar.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			App.setClipboard("imprimir");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(7000);

			s = new Screen();
			Pattern imprimirBtn = archiveAdm.getPatternFromJar("/prints/cef/imprimir.png", 0.6);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(12000);

			archiveAdm.salvarArquivoCEF(nomeArquivo, caminhoPastaSalvar, data);

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
			Pattern clicaContaAdmBtn = archiveAdm.getPatternFromJar("/prints/cef/clicacontaadm.png", 0.6);
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
			Pattern contaGarantidaBtn = archiveAdm.getPatternFromJar("/prints/cef/contagarantidaadm.png", 0.6);
			Match mContaGarantida = s.wait(contaGarantidaBtn, 10);
			Location locContaGarantida = mContaGarantida.getTarget();
			robot.mouseMove(locContaGarantida.getX(), locContaGarantida.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(7000);

			/* inserir confirmação de mudança conta aqui */

			s = new Screen();
			Pattern saldoEExtratoBtn = archiveAdm.getPatternFromJar("/prints/cef/saldoseextrato.png", 0.6);
			Match mSaldoEExtrato = s.wait(saldoEExtratoBtn, 10);
			Location locSaldoEExtrato = mSaldoEExtrato.getTarget();
			robot.mouseMove(locSaldoEExtrato.getX(), locSaldoEExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			/*
			 * s.keyDown(Key.CTRL); s.type("f"); s.keyUp(Key.CTRL);
			 * 
			 * App.setClipboard("extrato caixa");
			 * 
			 * // Colar no campo desejado s.type("v", KeyModifier.CTRL);
			 * 
			 * s = new Screen(); Pattern extratoCaixaBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/extratocaixa.png",0.6); Match
			 * mExtratoCaixa = s.wait(extratoCaixaBtn, 10); Location locExtratoCaixa =
			 * mExtratoCaixa.getTarget(); robot.mouseMove(locExtratoCaixa.getX(),
			 * locExtratoCaixa.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * s.keyDown(Key.CTRL); s.type("f"); s.keyUp(Key.CTRL);
			 * 
			 * App.setClipboard("outro");
			 * 
			 * // Colar no campo desejado s.type("v", KeyModifier.CTRL);
			 * 
			 * Thread.sleep(3000);
			 * 
			 * s = new Screen(); Pattern outroPeriodoBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/outroperiodo.png",0.6); Match
			 * mOutroPeriodo = s.wait(outroPeriodoBtn, 10); Location locOutroPeriodo =
			 * mOutroPeriodo.getTarget(); robot.mouseMove(locOutroPeriodo.getX(),
			 * locOutroPeriodo.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * s = new Screen(); Pattern dataInicialBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/datainicial.png",0.6); Match
			 * mDataInicial = s.wait(dataInicialBtn, 10); Location locDataInicial =
			 * mDataInicial.getTarget(); robot.mouseMove(locDataInicial.getX(),
			 * locDataInicial.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * DateTimeFormatter formatoFiltro = DateTimeFormatter.ofPattern("ddMMyyyy");
			 * String dataStr = data.format(formatoFiltro);
			 * 
			 * s.type(dataStr); s.type(Key.TAB); s.type(Key.TAB);
			 * 
			 * s.type(dataStr);
			 * 
			 * Thread.sleep(7000);
			 * 
			 * s = new Screen(); Pattern exportarPdfBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/exportarpdf.png",0.6); Match
			 * mExportarPdf = s.wait(exportarPdfBtn, 10); Location locExportarPdf =
			 * mExportarPdf.getTarget(); robot.mouseMove(locExportarPdf.getX(),
			 * locExportarPdf.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			 * 
			 * Thread.sleep(12000);
			 */

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("extrato por período");

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern extratoPorPeriodoBtn = archiveAdm.getPatternFromJar("/prints/cef/extratoporperiodo.png", 0.6);
			Match mExtratoPorPeriodo = s.wait(extratoPorPeriodoBtn, 10);
			Location locExtratoPorPeriodo = mExtratoPorPeriodo.getTarget();
			robot.mouseMove(locExtratoPorPeriodo.getX(), locExtratoPorPeriodo.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(3000);

			DateTimeFormatter formatoFiltroMes = DateTimeFormatter.ofPattern("MM");
			Integer mesStr = Integer.parseInt(data.format(formatoFiltroMes));

			LocalDate hoje = LocalDate.now();
			Integer mesHojeStr = Integer.parseInt(hoje.format(formatoFiltroMes));

			if (mesStr != mesHojeStr) {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);

				App.setClipboard("outro mês");

				// Colar no campo desejado
				s.type("v", KeyModifier.CTRL);

				Thread.sleep(3000);

				s = new Screen();
				Pattern outroMesBtn = archiveAdm.getPatternFromJar("/prints/cef/outromes.png", 0.6);
				Match mOutroMes = s.wait(outroMesBtn, 10);
				Location locOutroMes = mOutroMes.getTarget();
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

				Thread.sleep(3000);

				s = new Screen();

				Pattern escolhaOMesBtn = archiveAdm.getPatternFromJar("/prints/cef/escolhaomes.png", 0.6);
				Match mEscolhaOMes = s.wait(escolhaOMesBtn, 10);
				Location locEscolhaOMes = mEscolhaOMes.getTarget();
				robot.mouseMove(locEscolhaOMes.getX(), locEscolhaOMes.getY());
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(5000);

				if (mesStr == 12) {
					s.type("dez");
				}
				if (mesStr == 11) {
					s.type("nov");
				}
				if (mesStr == 10) {
					s.type("out");
				}
				if (mesStr == 9) {
					s.type("set");
				}
				if (mesStr == 8) {
					s.type("ago");
				}
				if (mesStr == 7) {
					s.type("jul");
				}
				if (mesStr == 6) {
					s.type("jun");
				}
				if (mesStr == 5) {
					s.type("mai");
				}
				if (mesStr == 4) {
					s.type("abr");
				}
				if (mesStr == 3) {
					s.type("mar");
				}
				if (mesStr == 2) {
					s.type("fev");
				}
				if (mesStr == 1) {
					s.type("jan");
				}

				s.type(Key.ENTER);

				Thread.sleep(3000);

			}

			s = new Screen();
			Pattern diaAteBtn = archiveAdm.getPatternFromJar("/prints/cef/diaate.png", 0.6);
			Match mDiaAte = s.wait(diaAteBtn, 10);
			Location locDiaAte = mDiaAte.getTarget();
			robot.mouseMove(locDiaAte.getX(), locDiaAte.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			DateTimeFormatter formatoFiltroDia = DateTimeFormatter.ofPattern("dd");
			String diaStr = data.format(formatoFiltroDia);

			s.type(diaStr);
			Thread.sleep(300);
			s.type(diaStr);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			App.setClipboard("continuar");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(7000);

			s = new Screen();
			Pattern continuarBtn = archiveAdm.getPatternFromJar("/prints/cef/continuar.png", 0.6);
			Match mContinuar = s.wait(continuarBtn, 10);
			Location locContinuar = mContinuar.getTarget();
			robot.mouseMove(locContinuar.getX(), locContinuar.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			App.setClipboard("imprimir");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(7000);

			s = new Screen();
			Pattern imprimirBtn = archiveAdm.getPatternFromJar("/prints/cef/imprimir.png", 0.6);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(12000);

			archiveAdm.salvarArquivoCEF(nomeArquivo, caminhoPastaSalvar, data);

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
			Pattern clicaContaAdmBtn = archiveAdm.getPatternFromJar("/prints/cef/clicacontavig.png", 0.6);
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
			Pattern contaGarantidaBtn = archiveAdm.getPatternFromJar("/prints/cef/contagarantidavig.png", 0.6);
			Match mContaGarantida = s.wait(contaGarantidaBtn, 10);
			Location locContaGarantida = mContaGarantida.getTarget();
			robot.mouseMove(locContaGarantida.getX(), locContaGarantida.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(7000);

			s = new Screen();
			Pattern saldoEExtratoBtn = archiveAdm.getPatternFromJar("/prints/cef/saldoseextrato.png", 0.6);
			Match mSaldoEExtrato = s.wait(saldoEExtratoBtn, 10);
			Location locSaldoEExtrato = mSaldoEExtrato.getTarget();
			robot.mouseMove(locSaldoEExtrato.getX(), locSaldoEExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			/*
			 * s.keyDown(Key.CTRL); s.type("f"); s.keyUp(Key.CTRL);
			 * 
			 * App.setClipboard("extrato caixa");
			 * 
			 * // Colar no campo desejado s.type("v", KeyModifier.CTRL);
			 * 
			 * s = new Screen(); Pattern extratoCaixaBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/extratocaixa.png",0.6); Match
			 * mExtratoCaixa = s.wait(extratoCaixaBtn, 10); Location locExtratoCaixa =
			 * mExtratoCaixa.getTarget(); robot.mouseMove(locExtratoCaixa.getX(),
			 * locExtratoCaixa.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * s.keyDown(Key.CTRL); s.type("f"); s.keyUp(Key.CTRL);
			 * 
			 * App.setClipboard("outro");
			 * 
			 * // Colar no campo desejado s.type("v", KeyModifier.CTRL);
			 * 
			 * Thread.sleep(3000);
			 * 
			 * s = new Screen(); Pattern outroPeriodoBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/outroperiodo.png",0.6); Match
			 * mOutroPeriodo = s.wait(outroPeriodoBtn, 10); Location locOutroPeriodo =
			 * mOutroPeriodo.getTarget(); robot.mouseMove(locOutroPeriodo.getX(),
			 * locOutroPeriodo.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * s = new Screen(); Pattern dataInicialBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/datainicial.png",0.6); Match
			 * mDataInicial = s.wait(dataInicialBtn, 10); Location locDataInicial =
			 * mDataInicial.getTarget(); robot.mouseMove(locDataInicial.getX(),
			 * locDataInicial.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * DateTimeFormatter formatoFiltro = DateTimeFormatter.ofPattern("ddMMyyyy");
			 * String dataStr = data.format(formatoFiltro);
			 * 
			 * s.type(dataStr); s.type(Key.TAB); s.type(Key.TAB);
			 * 
			 * s.type(dataStr);
			 * 
			 * Thread.sleep(7000);
			 * 
			 * s = new Screen(); Pattern exportarPdfBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/exportarpdf.png",0.6); Match
			 * mExportarPdf = s.wait(exportarPdfBtn, 10); Location locExportarPdf =
			 * mExportarPdf.getTarget(); robot.mouseMove(locExportarPdf.getX(),
			 * locExportarPdf.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(12000);
			 */

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("extrato por período");

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern extratoPorPeriodoBtn = archiveAdm.getPatternFromJar("/prints/cef/extratoporperiodo.png", 0.6);
			Match mExtratoPorPeriodo = s.wait(extratoPorPeriodoBtn, 10);
			Location locExtratoPorPeriodo = mExtratoPorPeriodo.getTarget();
			robot.mouseMove(locExtratoPorPeriodo.getX(), locExtratoPorPeriodo.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(3000);

			DateTimeFormatter formatoFiltroMes = DateTimeFormatter.ofPattern("MM");
			Integer mesStr = Integer.parseInt(data.format(formatoFiltroMes));

			LocalDate hoje = LocalDate.now();
			Integer mesHojeStr = Integer.parseInt(hoje.format(formatoFiltroMes));

			if (mesStr != mesHojeStr) {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);

				App.setClipboard("outro mês");

				// Colar no campo desejado
				s.type("v", KeyModifier.CTRL);

				Thread.sleep(3000);

				s = new Screen();
				Pattern outroMesBtn = archiveAdm.getPatternFromJar("/prints/cef/outromes.png", 0.6);
				Match mOutroMes = s.wait(outroMesBtn, 10);
				Location locOutroMes = mOutroMes.getTarget();
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

				Thread.sleep(3000);

				s = new Screen();

				Pattern escolhaOMesBtn = archiveAdm.getPatternFromJar("/prints/cef/escolhaomes.png", 0.6);
				Match mEscolhaOMes = s.wait(escolhaOMesBtn, 10);
				Location locEscolhaOMes = mEscolhaOMes.getTarget();
				robot.mouseMove(locEscolhaOMes.getX(), locEscolhaOMes.getY());
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(5000);

				if (mesStr == 12) {
					s.type("dez");
				}
				if (mesStr == 11) {
					s.type("nov");
				}
				if (mesStr == 10) {
					s.type("out");
				}
				if (mesStr == 9) {
					s.type("set");
				}
				if (mesStr == 8) {
					s.type("ago");
				}
				if (mesStr == 7) {
					s.type("jul");
				}
				if (mesStr == 6) {
					s.type("jun");
				}
				if (mesStr == 5) {
					s.type("mai");
				}
				if (mesStr == 4) {
					s.type("abr");
				}
				if (mesStr == 3) {
					s.type("mar");
				}
				if (mesStr == 2) {
					s.type("fev");
				}
				if (mesStr == 1) {
					s.type("jan");
				}

				s.type(Key.ENTER);

				Thread.sleep(3000);

			}

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("até");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern diaAteBtn = archiveAdm.getPatternFromJar("/prints/cef/diaate.png", 0.6);
			Match mDiaAte = s.wait(diaAteBtn, 10);
			Location locDiaAte = mDiaAte.getTarget();
			robot.mouseMove(locDiaAte.getX(), locDiaAte.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			DateTimeFormatter formatoFiltroDia = DateTimeFormatter.ofPattern("dd");
			String diaStr = data.format(formatoFiltroDia);

			s.type(diaStr);
			Thread.sleep(300);
			s.type(diaStr);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			App.setClipboard("continuar");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(7000);

			s = new Screen();
			Pattern continuarBtn = archiveAdm.getPatternFromJar("/prints/cef/continuar.png", 0.6);
			Match mContinuar = s.wait(continuarBtn, 10);
			Location locContinuar = mContinuar.getTarget();
			robot.mouseMove(locContinuar.getX(), locContinuar.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			App.setClipboard("imprimir");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(7000);

			s = new Screen();
			Pattern imprimirBtn = archiveAdm.getPatternFromJar("/prints/cef/imprimir.png", 0.6);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(12000);

			archiveAdm.salvarArquivoCEF(nomeArquivo, caminhoPastaSalvar, data);

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
			Pattern clicaContaAdmBtn = archiveAdm.getPatternFromJar("/prints/cef/clicacontavig.png", 0.6);
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
			Pattern contaGarantidaBtn = archiveAdm.getPatternFromJar("/prints/cef/contacorrentevig.png", 0.6);
			Match mContaGarantida = s.wait(contaGarantidaBtn, 10);
			Location locContaGarantida = mContaGarantida.getTarget();
			robot.mouseMove(locContaGarantida.getX(), locContaGarantida.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(7000);

			/* insere confirmação mudança conta aqui */

			s = new Screen();
			Pattern saldoEExtratoBtn = archiveAdm.getPatternFromJar("/prints/cef/saldoseextrato.png", 0.6);
			Match mSaldoEExtrato = s.wait(saldoEExtratoBtn, 10);
			Location locSaldoEExtrato = mSaldoEExtrato.getTarget();
			robot.mouseMove(locSaldoEExtrato.getX(), locSaldoEExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			/*
			 * s.keyDown(Key.CTRL); s.type("f"); s.keyUp(Key.CTRL);
			 * 
			 * App.setClipboard("extrato caixa");
			 * 
			 * // Colar no campo desejado s.type("v", KeyModifier.CTRL);
			 * 
			 * s = new Screen(); Pattern extratoCaixaBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/extratocaixa.png",0.6); Match
			 * mExtratoCaixa = s.wait(extratoCaixaBtn, 10); Location locExtratoCaixa =
			 * mExtratoCaixa.getTarget(); robot.mouseMove(locExtratoCaixa.getX(),
			 * locExtratoCaixa.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * s.keyDown(Key.CTRL); s.type("f"); s.keyUp(Key.CTRL);
			 * 
			 * App.setClipboard("outro");
			 * 
			 * // Colar no campo desejado s.type("v", KeyModifier.CTRL);
			 * 
			 * Thread.sleep(3000);
			 * 
			 * s = new Screen(); Pattern outroPeriodoBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/outroperiodo.png",0.6); Match
			 * mOutroPeriodo = s.wait(outroPeriodoBtn, 10); Location locOutroPeriodo =
			 * mOutroPeriodo.getTarget(); robot.mouseMove(locOutroPeriodo.getX(),
			 * locOutroPeriodo.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * s = new Screen(); Pattern dataInicialBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/datainicial.png",0.6); Match
			 * mDataInicial = s.wait(dataInicialBtn, 10); Location locDataInicial =
			 * mDataInicial.getTarget(); robot.mouseMove(locDataInicial.getX(),
			 * locDataInicial.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * DateTimeFormatter formatoFiltro = DateTimeFormatter.ofPattern("ddMMyyyy");
			 * String dataStr = data.format(formatoFiltro);
			 * 
			 * s.type(dataStr); s.type(Key.TAB); s.type(Key.TAB);
			 * 
			 * s.type(dataStr);
			 * 
			 * Thread.sleep(7000);
			 * 
			 * s = new Screen(); Pattern exportarPdfBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/exportarpdf.png",0.6); Match
			 * mExportarPdf = s.wait(exportarPdfBtn, 10); Location locExportarPdf =
			 * mExportarPdf.getTarget(); robot.mouseMove(locExportarPdf.getX(),
			 * locExportarPdf.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(12000);
			 */

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("extrato por período");

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern extratoPorPeriodoBtn = archiveAdm.getPatternFromJar("/prints/cef/extratoporperiodo.png", 0.6);
			Match mExtratoPorPeriodo = s.wait(extratoPorPeriodoBtn, 10);
			Location locExtratoPorPeriodo = mExtratoPorPeriodo.getTarget();
			robot.mouseMove(locExtratoPorPeriodo.getX(), locExtratoPorPeriodo.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(3000);

			DateTimeFormatter formatoFiltroMes = DateTimeFormatter.ofPattern("MM");
			Integer mesStr = Integer.parseInt(data.format(formatoFiltroMes));

			LocalDate hoje = LocalDate.now();
			Integer mesHojeStr = Integer.parseInt(hoje.format(formatoFiltroMes));

			if (mesStr != mesHojeStr) {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);

				App.setClipboard("outro mês");

				// Colar no campo desejado
				s.type("v", KeyModifier.CTRL);

				Thread.sleep(3000);

				s = new Screen();
				Pattern outroMesBtn = archiveAdm.getPatternFromJar("/prints/cef/outromes.png", 0.6);
				Match mOutroMes = s.wait(outroMesBtn, 10);
				Location locOutroMes = mOutroMes.getTarget();
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

				Thread.sleep(3000);

				s = new Screen();

				Pattern escolhaOMesBtn = archiveAdm.getPatternFromJar("/prints/cef/escolhaomes.png", 0.6);
				Match mEscolhaOMes = s.wait(escolhaOMesBtn, 10);
				Location locEscolhaOMes = mEscolhaOMes.getTarget();
				robot.mouseMove(locEscolhaOMes.getX(), locEscolhaOMes.getY());
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(5000);

				if (mesStr == 12) {
					s.type("dez");
				}
				if (mesStr == 11) {
					s.type("nov");
				}
				if (mesStr == 10) {
					s.type("out");
				}
				if (mesStr == 9) {
					s.type("set");
				}
				if (mesStr == 8) {
					s.type("ago");
				}
				if (mesStr == 7) {
					s.type("jul");
				}
				if (mesStr == 6) {
					s.type("jun");
				}
				if (mesStr == 5) {
					s.type("mai");
				}
				if (mesStr == 4) {
					s.type("abr");
				}
				if (mesStr == 3) {
					s.type("mar");
				}
				if (mesStr == 2) {
					s.type("fev");
				}
				if (mesStr == 1) {
					s.type("jan");
				}

				s.type(Key.ENTER);

				Thread.sleep(3000);

			}

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("até");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern diaAteBtn = archiveAdm.getPatternFromJar("/prints/cef/diaate.png", 0.6);
			Match mDiaAte = s.wait(diaAteBtn, 10);
			Location locDiaAte = mDiaAte.getTarget();
			robot.mouseMove(locDiaAte.getX(), locDiaAte.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			DateTimeFormatter formatoFiltroDia = DateTimeFormatter.ofPattern("dd");
			String diaStr = data.format(formatoFiltroDia);

			s.type(diaStr);
			Thread.sleep(300);
			s.type(diaStr);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			App.setClipboard("continuar");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(7000);

			s = new Screen();
			Pattern continuarBtn = archiveAdm.getPatternFromJar("/prints/cef/continuar.png", 0.6);
			Match mContinuar = s.wait(continuarBtn, 10);
			Location locContinuar = mContinuar.getTarget();
			robot.mouseMove(locContinuar.getX(), locContinuar.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			App.setClipboard("imprimir");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(7000);

			s = new Screen();
			Pattern imprimirBtn = archiveAdm.getPatternFromJar("/prints/cef/imprimir.png", 0.6);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(12000);

			archiveAdm.salvarArquivoCEF(nomeArquivo, caminhoPastaSalvar, data);

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
			Pattern clicaContaAdmBtn = archiveAdm.getPatternFromJar("/prints/cef/clicacontavig.png", 0.6);
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
			Pattern contaGarantidaBtn = archiveAdm.getPatternFromJar("/prints/cef/contacorrenteadm.png", 0.6);
			Match mContaGarantida = s.wait(contaGarantidaBtn, 10);
			Location locContaGarantida = mContaGarantida.getTarget();
			robot.mouseMove(locContaGarantida.getX(), locContaGarantida.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(7000);

			/* insere confirmação mudança conta aqui */

			s = new Screen();
			Pattern saldoEExtratoBtn = archiveAdm.getPatternFromJar("/prints/cef/saldoseextrato.png", 0.6);
			Match mSaldoEExtrato = s.wait(saldoEExtratoBtn, 10);
			Location locSaldoEExtrato = mSaldoEExtrato.getTarget();
			robot.mouseMove(locSaldoEExtrato.getX(), locSaldoEExtrato.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			/*
			 * s.keyDown(Key.CTRL); s.type("f"); s.keyUp(Key.CTRL);
			 * 
			 * App.setClipboard("extrato caixa");
			 * 
			 * // Colar no campo desejado s.type("v", KeyModifier.CTRL);
			 * 
			 * s = new Screen(); Pattern extratoCaixaBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/extratocaixa.png",0.6); Match
			 * mExtratoCaixa = s.wait(extratoCaixaBtn, 10); Location locExtratoCaixa =
			 * mExtratoCaixa.getTarget(); robot.mouseMove(locExtratoCaixa.getX(),
			 * locExtratoCaixa.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * 
			 * s.keyDown(Key.CTRL); s.type("f"); s.keyUp(Key.CTRL);
			 * 
			 * App.setClipboard("outro");
			 * 
			 * // Colar no campo desejado s.type("v", KeyModifier.CTRL);
			 * 
			 * Thread.sleep(3000);
			 * 
			 * s = new Screen(); Pattern outroPeriodoBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/outroperiodo.png",0.6); Match
			 * mOutroPeriodo = s.wait(outroPeriodoBtn, 10); Location locOutroPeriodo =
			 * mOutroPeriodo.getTarget(); robot.mouseMove(locOutroPeriodo.getX(),
			 * locOutroPeriodo.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * s = new Screen(); Pattern dataInicialBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/datainicial.png",0.6); Match
			 * mDataInicial = s.wait(dataInicialBtn, 10); Location locDataInicial =
			 * mDataInicial.getTarget(); robot.mouseMove(locDataInicial.getX(),
			 * locDataInicial.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(3000);
			 * 
			 * DateTimeFormatter formatoFiltro = DateTimeFormatter.ofPattern("ddMMyyyy");
			 * String dataStr = data.format(formatoFiltro);
			 * 
			 * s.type(dataStr); s.type(Key.TAB); s.type(Key.TAB);
			 * 
			 * s.type(dataStr);
			 * 
			 * Thread.sleep(7000);
			 * 
			 * s = new Screen(); Pattern exportarPdfBtn =
			 * archiveAdm.getPatternFromJar("/prints/cef/exportarpdf.png",0.6); Match
			 * mExportarPdf = s.wait(exportarPdfBtn, 10); Location locExportarPdf =
			 * mExportarPdf.getTarget(); robot.mouseMove(locExportarPdf.getX(),
			 * locExportarPdf.getY()); Thread.sleep(300);
			 * robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			 * robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); Thread.sleep(12000);
			 */

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("extrato por período");

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern extratoPorPeriodoBtn = archiveAdm.getPatternFromJar("/prints/cef/extratoporperiodo.png", 0.6);
			Match mExtratoPorPeriodo = s.wait(extratoPorPeriodoBtn, 10);
			Location locExtratoPorPeriodo = mExtratoPorPeriodo.getTarget();
			robot.mouseMove(locExtratoPorPeriodo.getX(), locExtratoPorPeriodo.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(3000);

			DateTimeFormatter formatoFiltroMes = DateTimeFormatter.ofPattern("MM");
			Integer mesStr = Integer.parseInt(data.format(formatoFiltroMes));

			LocalDate hoje = LocalDate.now();
			Integer mesHojeStr = Integer.parseInt(hoje.format(formatoFiltroMes));

			if (mesStr != mesHojeStr) {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);

				App.setClipboard("outro mês");

				// Colar no campo desejado
				s.type("v", KeyModifier.CTRL);

				Thread.sleep(3000);

				s = new Screen();
				Pattern outroMesBtn = archiveAdm.getPatternFromJar("/prints/cef/outromes.png", 0.6);
				Match mOutroMes = s.wait(outroMesBtn, 10);
				Location locOutroMes = mOutroMes.getTarget();
				robot.mouseMove(locOutroMes.getX(), locOutroMes.getY());
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

				Thread.sleep(3000);

				s = new Screen();

				Pattern escolhaOMesBtn = archiveAdm.getPatternFromJar("/prints/cef/escolhaomes.png", 0.6);
				Match mEscolhaOMes = s.wait(escolhaOMesBtn, 10);
				Location locEscolhaOMes = mEscolhaOMes.getTarget();
				robot.mouseMove(locEscolhaOMes.getX(), locEscolhaOMes.getY());
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(5000);

				if (mesStr == 12) {
					s.type("dez");
				}
				if (mesStr == 11) {
					s.type("nov");
				}
				if (mesStr == 10) {
					s.type("out");
				}
				if (mesStr == 9) {
					s.type("set");
				}
				if (mesStr == 8) {
					s.type("ago");
				}
				if (mesStr == 7) {
					s.type("jul");
				}
				if (mesStr == 6) {
					s.type("jun");
				}
				if (mesStr == 5) {
					s.type("mai");
				}
				if (mesStr == 4) {
					s.type("abr");
				}
				if (mesStr == 3) {
					s.type("mar");
				}
				if (mesStr == 2) {
					s.type("fev");
				}
				if (mesStr == 1) {
					s.type("jan");
				}

				s.type(Key.ENTER);

				Thread.sleep(3000);

			}

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);

			App.setClipboard("até");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s = new Screen();
			Pattern diaAteBtn = archiveAdm.getPatternFromJar("/prints/cef/diaate.png", 0.6);
			Match mDiaAte = s.wait(diaAteBtn, 10);
			Location locDiaAte = mDiaAte.getTarget();
			robot.mouseMove(locDiaAte.getX(), locDiaAte.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			DateTimeFormatter formatoFiltroDia = DateTimeFormatter.ofPattern("dd");
			String diaStr = data.format(formatoFiltroDia);

			s.type(diaStr);
			Thread.sleep(300);
			s.type(diaStr);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			App.setClipboard("continuar");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(7000);

			s = new Screen();
			Pattern continuarBtn = archiveAdm.getPatternFromJar("/prints/cef/continuar.png", 0.6);
			Match mContinuar = s.wait(continuarBtn, 10);
			Location locContinuar = mContinuar.getTarget();
			robot.mouseMove(locContinuar.getX(), locContinuar.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			App.setClipboard("imprimir");
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(7000);

			s = new Screen();
			Pattern imprimirBtn = archiveAdm.getPatternFromJar("/prints/cef/imprimir.png", 0.6);
			Match mImprimir = s.wait(imprimirBtn, 10);
			Location locImprimir = mImprimir.getTarget();
			robot.mouseMove(locImprimir.getX(), locImprimir.getY());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(12000);

			archiveAdm.salvarArquivoCEF(nomeArquivo, caminhoPastaSalvar, data);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
