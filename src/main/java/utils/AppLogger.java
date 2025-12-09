package main.java.utils;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import main.java.application.*;
import main.java.entities.*;



public class AppLogger {

	ChromeAdm chromeAdm = new ChromeAdm();
	ArchiveAdm archiveAdm = new ArchiveAdm();

	public EmpresaBB logarBB(Integer empresa, Integer filial) {

		System.out.println();
		System.out.println("Logando no BB - Empresa: " + empresa);

		chromeAdm.fechaChrome();

		chromeAdm.abreChromeBB();

		EmpresaBB tempEmpresa = new EmpresaBB();
		List<EmpresaBB> empresas = new ArrayList<>();

		// Obtém o arquivo CSV do classpath (resources/data/acessBB.csv)
		InputStream is = AppMain.class.getResourceAsStream("/data/acessBB.csv");
		if (is == null) {
			System.out.println("Erro: arquivo '/data/acessBB.csv' não encontrado no classpath!");
		} else {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

				String linha;

				// Pula o cabeçalho
				br.readLine();

				while ((linha = br.readLine()) != null) {
					String[] campos = linha.split(";");

					if (campos.length < 4)
						continue;

					try {
						Integer codEmp = Integer.parseInt(campos[0].trim());
						Integer codFil = Integer.parseInt(campos[1].trim());
						String chaveJ = campos[2].trim();
						String pass = campos[3].trim();

						EmpresaBB e = new EmpresaBB(codEmp, codFil, chaveJ, pass);
						empresas.add(e);
					} catch (NumberFormatException ex) {
						System.out.println("Linha ignorada por formato inválido: " + linha);
					}
				}

			} catch (IOException e) {
				System.out.println("Erro ao ler arquivo: " + e.getMessage());
			}
		}

		// Procura a empresa e filial desejada
		for (EmpresaBB emp : empresas) {
			if (emp.getCodEmp().equals(empresa) && emp.getCodFil().equals(filial)) {
				tempEmpresa = emp;
			}
		}

		try {
			Robot robot;
			robot = new Robot();

			System.out.println();
			System.out.println("Aguardando tela de login.");

			while (chromeAdm.confirmaChegadaNaTela("/prints/telalogin.png") == false) {
				Thread.sleep(1000);
			}

			System.out.println();
			System.out.println("Tela de login carregada.");

			boolean capsOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
			if (capsOn) {
				robot.keyPress(KeyEvent.VK_CAPS_LOCK);
				robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
				Thread.sleep(500); // espera estabilizar
			}

			Screen s = new Screen();
			App.setClipboard(tempEmpresa.getChaveJ()); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			s.type(Key.TAB);
			App.setClipboard(tempEmpresa.getPass()); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type(Key.ENTER);

			System.out.println();
			System.out.println("Aguardando entrada no portal.");

			while (chromeAdm.confirmaChegadaNaTela("/prints/homebb.png") == false) {
				Thread.sleep(5000);
			}

			System.out.println();
			System.out.println("Tela de portal carregada.");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Logado com sucesso - Empresa: " + empresa);

		return tempEmpresa;

	}

	public EmpresaABC logarABC(Integer empresa, Integer filial) {

		System.out.println();
		System.out.println("Logando no ABC - Empresa: " + empresa);

		chromeAdm.fechaChrome();

		chromeAdm.abreChromeABC();

		EmpresaABC tempEmpresa = new EmpresaABC();

		List<EmpresaABC> empresas = new ArrayList<>();

		// Obtém o arquivo CSV do classpath (resources/data/acessABC.csv)
		InputStream is = AppMain.class.getResourceAsStream("/data/acessABC.csv");
		if (is == null) {
			System.out.println("Erro: arquivo '/data/acessABC.csv' não encontrado no classpath!");
		} else {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

				String linha;

				// Pula o cabeçalho
				br.readLine();

				while ((linha = br.readLine()) != null) {
					String[] campos = linha.split(";");
					if (campos.length < 4)
						continue;

					try {
						Integer codEmp = Integer.parseInt(campos[0].trim());
						Integer codFil = Integer.parseInt(campos[1].trim());
						String user = campos[2].trim();
						String pass = campos[3].trim();

						EmpresaABC e = new EmpresaABC(codEmp, codFil, user, pass);
						empresas.add(e);
					} catch (NumberFormatException ex) {
						System.out.println("Linha ignorada por formato inválido: " + linha);
					}
				}

			} catch (IOException e) {
				System.out.println("Erro ao ler arquivo: " + e.getMessage());
			}
		}

		// Procura a empresa e filial desejada
		for (EmpresaABC emp : empresas) {
			if (emp.getCodEmp().equals(empresa) && emp.getCodFil().equals(filial)) {
				tempEmpresa = emp;
			}
		}

		try {
			Screen s = new Screen();

			Robot robot;
			robot = new Robot();

			System.out.println();
			System.out.println("Aguardando tela de login.");

			while (chromeAdm.confirmaChegadaNaTela("/prints/abc/confirmatelaloginabc.png") == false) {
				Thread.sleep(1000);
			}

			System.out.println();
			System.out.println("Tela de login carregada.");

			boolean capsOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
			if (capsOn) {
				robot.keyPress(KeyEvent.VK_CAPS_LOCK);
				robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
				Thread.sleep(500); // espera estabilizar
			}

			s = new Screen();
			Pattern digitaCpfBtn = archiveAdm.getPatternFromJar("/prints/abc/digitacpfabc.png", 0.6);
			Match mDigitaCpf = s.wait(digitaCpfBtn, 10);
			Location locDigitaCpf = mDigitaCpf.getTarget();
			robot.mouseMove(locDigitaCpf.getX(), locDigitaCpf.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			App.setClipboard(tempEmpresa.getUser());

			s.type("v", KeyModifier.CTRL);

			Thread.sleep(3000);

			s.type(Key.TAB);

			Thread.sleep(7000);
			
		

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("2 -");

			s = new Screen();

			Pattern numeroDoisBtn = archiveAdm.getPatternFromJar("/prints/abc/2esquerda.png", 0.7);
			Match mNumeroDois = s.exists(numeroDoisBtn, 10);
			Location locNumeroDois;

			if (mNumeroDois != null) {

				locNumeroDois = mNumeroDois.getTarget();

			} else {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type("- 2");

				Thread.sleep(1500);

				s = new Screen();

				numeroDoisBtn = archiveAdm.getPatternFromJar("/prints/abc/2direita.png", 0.7);
				mNumeroDois = s.wait(numeroDoisBtn, 10);
				locNumeroDois = mNumeroDois.getTarget();
			}

			robot.mouseMove(locNumeroDois.getX(), locNumeroDois.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("6 -");

			Thread.sleep(1500);

			s = new Screen();

			Pattern numeroSeisBtn = archiveAdm.getPatternFromJar("/prints/abc/6esquerda.png", 0.7);
			Match mNumeroSeis = s.exists(numeroSeisBtn, 10);
			Location locNumeroSeis;
			if (mNumeroSeis != null) {

				locNumeroSeis = mNumeroSeis.getTarget();

			} else {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type("- 6");

				Thread.sleep(1500);

				s = new Screen();

				numeroSeisBtn = archiveAdm.getPatternFromJar("/prints/abc/6direita.png", 0.7);
				mNumeroSeis = s.wait(numeroSeisBtn, 10);
				locNumeroSeis = mNumeroSeis.getTarget();
			}

			robot.mouseMove(locNumeroSeis.getX(), locNumeroSeis.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			robot.mouseMove(locNumeroDois.getX(), locNumeroDois.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("5 -");
			Thread.sleep(1500);

			s = new Screen();

			Pattern numeroCincoBtn = archiveAdm.getPatternFromJar("/prints/abc/5esquerda.png", 0.7);
			Match mNumeroCinco = s.exists(numeroCincoBtn, 10);
			Location locNumeroCinco;
			if (mNumeroCinco != null) {

				locNumeroCinco = mNumeroCinco.getTarget();

			} else {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type("- 5");
				Thread.sleep(1500);

				s = new Screen();

				numeroCincoBtn = archiveAdm.getPatternFromJar("/prints/abc/5direita.png", 0.7);
				mNumeroCinco = s.wait(numeroCincoBtn, 10);
				locNumeroCinco = mNumeroCinco.getTarget();
			}

			robot.mouseMove(locNumeroCinco.getX(), locNumeroCinco.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("1 -");
			Thread.sleep(1500);

			s = new Screen();

			Pattern numeroUmBtn = archiveAdm.getPatternFromJar("/prints/abc/1esquerda.png", 0.7);
			Match mNumeroUm = s.exists(numeroUmBtn, 10);
			Location locNumeroUm;
			if (mNumeroUm != null) {

				locNumeroUm = mNumeroUm.getTarget();

			} else {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type("- 1");
				Thread.sleep(1500);

				s = new Screen();

				numeroUmBtn = archiveAdm.getPatternFromJar("/prints/abc/1direita.png", 0.7);
				mNumeroUm = s.wait(numeroUmBtn, 10);
				locNumeroUm = mNumeroUm.getTarget();
			}

			robot.mouseMove(locNumeroUm.getX(), locNumeroUm.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			robot.mouseMove(locNumeroCinco.getX(), locNumeroCinco.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			robot.mouseMove(locNumeroUm.getX(), locNumeroUm.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("4 -");
			Thread.sleep(1500);

			s = new Screen();

			Pattern numeroQuatroBtn = archiveAdm.getPatternFromJar("/prints/abc/4esquerda.png", 0.7);
			Match mNumeroQuatro = s.exists(numeroQuatroBtn, 10);
			Location locNumeroQuatro;
			if (mNumeroQuatro != null) {

				locNumeroQuatro = mNumeroQuatro.getTarget();

			} else {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type("- 4");
				Thread.sleep(1500);

				s = new Screen();

				numeroQuatroBtn = archiveAdm.getPatternFromJar("/prints/abc/4direita.png", 0.7);
				mNumeroQuatro = s.wait(numeroQuatroBtn, 10);
				locNumeroQuatro = mNumeroQuatro.getTarget();
			}
			robot.mouseMove(locNumeroQuatro.getX(), locNumeroQuatro.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();

			Pattern entrarBtn = archiveAdm.getPatternFromJar("/prints/abc/entrarabc.png", 0.6);
			Match mEntrar = s.wait(entrarBtn, 10);
			Location locEntrar = mEntrar.getTarget();
			robot.mouseMove(locEntrar.getX(), locEntrar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			System.out.println();
			System.out.println("Aguardando entrada no portal.");

			while (chromeAdm.confirmaChegadaNaTela("/prints/abc/confirmaportalabc.png") == false) {
				Thread.sleep(1000);
			}

			System.out.println();
			System.out.println("Tela de portal carregada.");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Logado com sucesso - Empresa: " + empresa);

		return tempEmpresa;
	}

	public EmpresaSICREDI logarSICREDI(Integer empresa, Integer filial) {

		System.out.println();
		System.out.println("Logando no SICREDI - Empresa: " + empresa);

		ChromeAdm chromeAdm = new ChromeAdm();

		chromeAdm.fechaChrome();

		chromeAdm.abreChromeSICREDI();

		EmpresaSICREDI tempEmpresa = new EmpresaSICREDI();
		List<EmpresaSICREDI> empresas = new ArrayList<>();

		InputStream is = AppMain.class.getResourceAsStream("/data/acessSICREDI.csv");
		if (is == null) {
			System.out.println("Erro: arquivo '/data/acessSICREDI.csv' não encontrado no classpath!");
		} else {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

				String linha;

				// Pula o cabeçalho
				br.readLine();

				while ((linha = br.readLine()) != null) {
					String[] campos = linha.split(";");

					if (campos.length < 6)
						continue;

					try {
						Integer codEmp = Integer.parseInt(campos[0].trim());
						Integer codFil = Integer.parseInt(campos[1].trim());
						String user = campos[2].trim();
						String pass = campos[3].trim();
						String trans = campos[4].trim();
						String cnpj = campos[5].trim();

						EmpresaSICREDI e = new EmpresaSICREDI(codEmp, codFil, user, pass, trans, cnpj);
						empresas.add(e);
					} catch (NumberFormatException ex) {
						System.out.println("Linha ignorada por formato inválido: " + linha);
					}
				}

			} catch (IOException e) {
				System.out.println("Erro ao ler arquivo: " + e.getMessage());
			}
		}

		// Procura a empresa e filial desejada
		for (EmpresaSICREDI emp : empresas) {
			if (emp.getCodEmp().equals(empresa) && emp.getCodFil().equals(filial)) {
				tempEmpresa = emp;
			}
		}

		try {

			Thread.sleep(3000);
			Screen s = new Screen();

			Robot robot;
			robot = new Robot();

			boolean capsOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
			if (capsOn) {
				robot.keyPress(KeyEvent.VK_CAPS_LOCK);
				robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
				Thread.sleep(500); // espera estabilizar
			}

			System.out.println();
			System.out.println("Aguardando tela inicial.");
			
			s = new Screen();
			Pattern httpsTelaBtn = archiveAdm.getPatternFromJar("/prints/sicredi/httpsTela.png", 0.6);
			Match mHttpsTela = s.exists(httpsTelaBtn, 10);
			if (mHttpsTela != null) {
				Pattern irParaSiteBtn = archiveAdm.getPatternFromJar("/prints/sicredi/irparaite.png", 0.6);
				Match mIrParaSite = s.exists(irParaSiteBtn, 10);
				Location locIrParaSite = mIrParaSite.getTarget();
				robot.mouseMove(locIrParaSite.getX(), locIrParaSite.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
			}


			while (chromeAdm.confirmaChegadaNaTela("/prints/sicredi/confirmatelaacesso.png") == false) {
				Thread.sleep(1000);
			}

			System.out.println();
			System.out.println("Tela de inicio carregada.");

			App.setClipboard(tempEmpresa.getCnpj());

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Pattern acessarBtn = archiveAdm.getPatternFromJar("/prints/sicredi/acessar.png", 0.6);
			Match mAcessar = s.wait(acessarBtn, 10);
			Location locAcessar = mAcessar.getTarget();
			robot.mouseMove(locAcessar.getX(), locAcessar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(5000);

			System.out.println();
			System.out.println("Aguardando tela login.");

			while (chromeAdm.confirmaChegadaNaTela("/prints/sicredi/confirmatelalogin.png") == false) {
				Thread.sleep(1000);
			}

			System.out.println();
			System.out.println("Tela de login carregada.");
			robot.mouseMove(0, 0);

			Thread.sleep(3000);			

			s = new Screen();
			Pattern usuarioAcessoBtn = archiveAdm.getPatternFromJar("/prints/sicredi/usuarioacesso2.png", 0.6);
			Match mUsuarioAcesso = s.wait(usuarioAcessoBtn, 10);
			Location loc = mUsuarioAcesso.getTarget();
			int x = loc.getX();
			int y = mUsuarioAcesso.getY() + mUsuarioAcesso.getH() - 3; 
			Location rodape = new Location(x, y);
			robot.mouseMove(rodape.getX(), rodape.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			App.setClipboard(tempEmpresa.getUser());

			s.type("v", KeyModifier.CTRL);
			Thread.sleep(1000);

			s = new Screen();
			Pattern informaSenhaBtn = archiveAdm.getPatternFromJar("/prints/sicredi/informasenha.png", 0.6);
			Match mInformaSenha = s.wait(informaSenhaBtn, 10);
			Location locInformaSenha = mInformaSenha.getTarget();
			robot.mouseMove(locInformaSenha.getX(), locInformaSenha.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("0 ou");

			s = new Screen();
			Thread.sleep(1500);

			Pattern numeroZeroBtn = archiveAdm.getPatternFromJar("/prints/sicredi/0esquerda.png", 0.7);
			Match mNumeroZero = s.exists(numeroZeroBtn, 10);
			Location locNumeroZero;
			if (mNumeroZero != null) {

				locNumeroZero = mNumeroZero.getTarget();

			} else {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type("ou 0");
				s = new Screen();
				Thread.sleep(1500);

				numeroZeroBtn = archiveAdm.getPatternFromJar("/prints/sicredi/0direita.png", 0.7);
				mNumeroZero = s.wait(numeroZeroBtn, 10);
				locNumeroZero = mNumeroZero.getTarget();
			}

			robot.mouseMove(locNumeroZero.getX(), locNumeroZero.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1500);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("1 ou");

			s = new Screen();
			Thread.sleep(1500);

			Pattern numeroUmBtn = archiveAdm.getPatternFromJar("/prints/sicredi/1esquerda.png", 0.7);
			Match mNumeroUm = s.exists(numeroUmBtn, 10);
			Location locNumeroUm;
			if (mNumeroUm != null) {
				locNumeroUm = mNumeroUm.getTarget();

			} else {
				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type("ou 1");

				s = new Screen();
				Thread.sleep(1500);

				numeroUmBtn = archiveAdm.getPatternFromJar("/prints/sicredi/1direita.png", 0.7);
				mNumeroUm = s.wait(numeroUmBtn, 10);
				locNumeroUm = mNumeroUm.getTarget();
			}

			robot.mouseMove(locNumeroUm.getX(), locNumeroUm.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1500);

			robot.mouseMove(locNumeroZero.getX(), locNumeroZero.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1500);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("2 ou");

			s = new Screen();
			Thread.sleep(1500);

			Pattern numeroDoisBtn = archiveAdm.getPatternFromJar("/prints/sicredi/2esquerda.png", 0.7);
			Match mNumeroDois = s.exists(numeroDoisBtn, 10);
			Location locNumeroDois;
			if (mNumeroDois != null) {
				locNumeroDois = mNumeroDois.getTarget();
			} else {
				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type("ou 2");

				s = new Screen();
				Thread.sleep(1500);

				numeroDoisBtn = archiveAdm.getPatternFromJar("/prints/sicredi/2direita.png", 0.7);
				mNumeroDois = s.wait(numeroDoisBtn, 10);
				locNumeroDois = mNumeroDois.getTarget();
			}

			robot.mouseMove(locNumeroDois.getX(), locNumeroDois.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1500);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("3 ou");

			s = new Screen();
			Thread.sleep(1500);

			Pattern numeroTresBtn = archiveAdm.getPatternFromJar("/prints/sicredi/3esquerda.png", 0.7);
			Match mNumeroTres = s.exists(numeroTresBtn, 10);
			Location locNumeroTres;
			if (mNumeroTres != null) {
				locNumeroTres = mNumeroTres.getTarget();
			} else {

				s.keyDown(Key.CTRL);
				s.type("f");
				s.keyUp(Key.CTRL);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type(Key.BACKSPACE);
				s.type("ou 3");

				s = new Screen();
				Thread.sleep(1500);

				numeroTresBtn = archiveAdm.getPatternFromJar("/prints/sicredi/3direita.png", 0.7);
				mNumeroTres = s.wait(numeroTresBtn, 10);
				locNumeroTres = mNumeroTres.getTarget();
			}

			robot.mouseMove(locNumeroTres.getX(), locNumeroTres.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1500);

			robot.mouseMove(locNumeroZero.getX(), locNumeroZero.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(1500);

			s = new Screen();

			acessarBtn = archiveAdm.getPatternFromJar("/prints/sicredi/acessar.png", 0.6);
			mAcessar = s.wait(acessarBtn, 10);
			locAcessar = mAcessar.getTarget();
			robot.mouseMove(locAcessar.getX(), locAcessar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			System.out.println();
			System.out.println("Aguardando entrada no portal.");

			while (chromeAdm.confirmaChegadaNaTela("/prints/sicredi/confirmaentradalogin.png") == false) {
				Thread.sleep(1000);
			}

			System.out.println();
			System.out.println("Tela de portal carregada.");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Logado com sucesso - Empresa: " + empresa);

		return tempEmpresa;
	}

	public EmpresaSANTANDER logarSANTANDER(Integer empresa, Integer filial) {

		System.out.println();
		System.out.println("Logando no SANTANDER - Empresa: " + empresa);

		ChromeAdm chromeAdm = new ChromeAdm();

		chromeAdm.fechaChrome();

		chromeAdm.abreChromeSANTANDER();

		EmpresaSANTANDER tempEmpresa = new EmpresaSANTANDER();
		List<EmpresaSANTANDER> empresas = new ArrayList<>();

		// Obtém o arquivo CSV do classpath (resources/data/acessSANTANDER.csv)
		InputStream is = AppMain.class.getResourceAsStream("/data/acessSANTANDER.csv");
		if (is == null) {
			System.out.println("Erro: arquivo '/data/acessSANTANDER.csv' não encontrado no classpath!");
		} else {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

				String linha;

				// Pula o cabeçalho
				br.readLine();

				while ((linha = br.readLine()) != null) {
					String[] campos = linha.split(";");

					if (campos.length < 6)
						continue;

					try {
						Integer codEmp = Integer.parseInt(campos[0].trim());
						Integer codFil = Integer.parseInt(campos[1].trim());
						String agencia = campos[2].trim();
						String conta = campos[3].trim();
						String user = campos[4].trim();
						String pass = campos[5].trim();

						EmpresaSANTANDER e = new EmpresaSANTANDER(codEmp, codFil, agencia, conta, user, pass);
						empresas.add(e);
					} catch (NumberFormatException ex) {
						System.out.println("Linha ignorada por formato inválido: " + linha);
					}
				}

			} catch (IOException e) {
				System.out.println("Erro ao ler arquivo: " + e.getMessage());
			}
		}

		// Procura a empresa e filial desejada
		for (EmpresaSANTANDER emp : empresas) {
			if (emp.getCodEmp().equals(empresa) && emp.getCodFil().equals(filial)) {
				tempEmpresa = emp;
			}
		}

		try {
			Screen s = new Screen();

			Robot robot;
			robot = new Robot();

			while (chromeAdm.confirmaChegadaNaTela("/prints/santander/confirmaportalsantander.png") == false) {
				Thread.sleep(5000);
			}

			boolean capsOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
			if (capsOn) {
				robot.keyPress(KeyEvent.VK_CAPS_LOCK);
				robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
				Thread.sleep(500); // espera estabilizar
			}

			Pattern pessoaFisicaBtn = archiveAdm.getPatternFromJar("/prints/santander/pessoafisica.png", 0.6);
			Match mPessoaFisica = s.wait(pessoaFisicaBtn, 10);
			Location locPessoaFisica = mPessoaFisica.getTarget();
			robot.mouseMove(locPessoaFisica.getX(), locPessoaFisica.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();
			Pattern pessoaJuridicaBtn = archiveAdm.getPatternFromJar("/prints/santander/pessoajuridica.png", 0.6);
			Match mPessoaJuridica = s.wait(pessoaJuridicaBtn, 10);
			Location locPessoaJuridica = mPessoaJuridica.getTarget();
			robot.mouseMove(locPessoaJuridica.getX(), locPessoaJuridica.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();
			Pattern agenciaBtn = archiveAdm.getPatternFromJar("/prints/santander/agencia.png", 0.6);
			Match mAgencia = s.wait(agenciaBtn, 10);
			Location locAgencia = mAgencia.getTarget();
			robot.mouseMove(locAgencia.getX(), locAgencia.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			App.setClipboard(tempEmpresa.getAgencia()); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(300);

			App.setClipboard(tempEmpresa.getConta()); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			s.type(Key.ENTER);

			while (chromeAdm.confirmaChegadaNaTela("/prints/santander/confirmatelalogin.png") == false) {
				Thread.sleep(5000);
			}

			Thread.sleep(10000);

			App.setClipboard(tempEmpresa.getUser()); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			s.type(Key.TAB);
			Thread.sleep(300);

			App.setClipboard(tempEmpresa.getPass()); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);
			s.type(Key.ENTER);

			while (chromeAdm.confirmaChegadaNaTela("/prints/santander/confirmalogado.png") == false) {
				Thread.sleep(1000);
			}

			Thread.sleep(10000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Logado com sucesso - Empresa: " + empresa);

		return tempEmpresa;
	}

	public EmpresaCEF logarCEF(Integer empresa, Integer filial) {

		System.out.println();
		System.out.println("Logando na CEF - Empresa: " + empresa);

		ChromeAdm chromeAdm = new ChromeAdm();

		chromeAdm.fechaChrome();

		chromeAdm.abreChromeCEF();

		EmpresaCEF tempEmpresa = new EmpresaCEF();
		List<EmpresaCEF> empresas = new ArrayList<>();

		
		InputStream is = AppMain.class.getResourceAsStream("/data/acessCEF.csv");
		if (is == null) {
			System.out.println("Erro: arquivo '/data/acessCEF.csv' não encontrado no classpath!");
		} else {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

				String linha;

				// Pula o cabeçalho
				br.readLine();

				while ((linha = br.readLine()) != null) {
					String[] campos = linha.split(";");

					if (campos.length < 4)
						continue;

					try {
						Integer codEmp = Integer.parseInt(campos[0].trim());
						Integer codFil = Integer.parseInt(campos[1].trim());
						String user = campos[2].trim();
						String pass = campos[3].trim();

						EmpresaCEF e = new EmpresaCEF(codEmp, codFil, user, pass);
						empresas.add(e);
					} catch (NumberFormatException ex) {
						System.out.println("Linha ignorada por formato inválido: " + linha);
					}
				}

			} catch (IOException e) {
				System.out.println("Erro ao ler arquivo: " + e.getMessage());
			}
		}

		// Procura a empresa e filial desejada
		for (EmpresaCEF emp : empresas) {
			if (emp.getCodEmp().equals(empresa) && emp.getCodFil().equals(filial)) {
				tempEmpresa = emp;
			}
		}

		try {
			Screen s = new Screen();

			Robot robot;
			robot = new Robot();

			while (chromeAdm.confirmaChegadaNaTela("/prints/cef/confirmasite.png") == false) {
				Thread.sleep(5000);
			}

			boolean capsOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
			if (capsOn) {
				robot.keyPress(KeyEvent.VK_CAPS_LOCK);
				robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
				Thread.sleep(500); // espera estabilizar
			}

			Thread.sleep(5000);
			
			Pattern empresasBtn = archiveAdm.getPatternFromJar("/prints/cef/empresas.png", 0.6);
			Match mEmpresas = s.wait(empresasBtn, 10);
			Location locEmpresas = mEmpresas.getTarget();
			robot.mouseMove(locEmpresas.getX(), locEmpresas.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			while (chromeAdm.confirmaChegadaNaTela("/prints/cef/acessargerenciador.png") == false) {
				Thread.sleep(5000);
			}

			s = new Screen();
			Pattern acessarGerenciadorBtn = archiveAdm.getPatternFromJar("/prints/cef/acessargerenciador.png", 0.6);
			Match mAcessarGerenciador = s.wait(acessarGerenciadorBtn, 10);
			Location locAcessarGerenciador = mAcessarGerenciador.getTarget();
			robot.mouseMove(locAcessarGerenciador.getX(), locAcessarGerenciador.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);
			
			while (chromeAdm.confirmaChegadaNaTela("/prints/cef/telalogin.png") == false) {
				Thread.sleep(5000);
			}

			App.setClipboard(tempEmpresa.getUser());

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);


			s.type(Key.TAB);
			
			App.setClipboard(tempEmpresa.getPass());

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s.type(Key.ENTER);

			s = new Screen();
			Pattern permitirDessaVezBtn = archiveAdm.getPatternFromJar("/prints/cef/permitirdessavez.png", 0.6);
			Match mPermitirDessaVez = s.wait(permitirDessaVezBtn, 10);
			Location locPermitirDessaVez = mPermitirDessaVez.getTarget();
			robot.mouseMove(locPermitirDessaVez.getX(), locPermitirDessaVez.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(8000);


			s = new Screen();
			Pattern fecharFiqueAtentoBtn = archiveAdm.getPatternFromJar("/prints/cef/fecharfiqueatento.png", 0.6);
			Match mFecharFiqueAtento = s.exists(fecharFiqueAtentoBtn, 10);
			if (mFecharFiqueAtento != null) {
				Location locFecharFiqueAtento = mFecharFiqueAtento.getTarget();
				robot.mouseMove(locFecharFiqueAtento.getX(), locFecharFiqueAtento.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
			}

			while (chromeAdm.confirmaChegadaNaTela("/prints/cef/entradaportal.png") == false) {
				Thread.sleep(5000);
			}

			Thread.sleep(10000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Logado com sucesso - Empresa: " + empresa);

		return tempEmpresa;
	}
}