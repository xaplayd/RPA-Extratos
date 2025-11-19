package empresas;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import utils.AppLogger;
import utils.ArchiveAdm;
import utils.ChromeAdm;

public class BBEmpresa20 {

	public void geraExtratos() {

		ChromeAdm chromeAdm = new ChromeAdm();

		chromeAdm.fechaChrome();

		AppLogger appLogger = new AppLogger();


		try {

			chromeAdm.abreChrome();

			appLogger.logarBB(20, 1);
			
			while (chromeAdm.confirmaEntradaPortal() == false) {
				Thread.sleep(15000);
			}

			geraExtratoCorrenteBB();

			chromeAdm.fechaChrome();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void geraExtratoCorrenteBB() {

		try {

			Screen s = new Screen();
			Robot robot;

			LocalDate dataExtrato = LocalDate.now().minusDays(1);

			DateTimeFormatter iniefimFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataParaInieFim = dataExtrato.format(iniefimFormatter);

			s.keyDown(Key.CTRL);
			s.type("f");
			s.keyUp(Key.CTRL);
			s.type("extrato de conta corrente");
			Thread.sleep(1000);

			robot = new Robot();
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

			ChromeAdm chromeAdm = new ChromeAdm();

			chromeAdm.confirmaEntradaExtrato();

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
			
			arquivo.salvarArquivo("BB ESPECIAIS");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
