package utils;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.sikuli.script.Key;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ArchiveAdm {

	public void salvarArquivo(String nomeArquivoSalvar) {
		try {
			LocalDate dataExtrato = LocalDate.now().minusDays(1);

			DateTimeFormatter formatterano = DateTimeFormatter.ofPattern("yyyy");
			String ano = dataExtrato.format(formatterano);
			DateTimeFormatter mesExtensoFormatter = DateTimeFormatter.ofPattern("MM MMMM", new Locale("pt", "BR"));
			String mesNomeExtenso = dataExtrato.format(mesExtensoFormatter);
			DateTimeFormatter porDiaFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
			String porDiaNome = dataExtrato.format(porDiaFormatter);
			DateTimeFormatter iniefimFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataParaInieFim = dataExtrato.format(iniefimFormatter);
			
			Robot robot = new Robot();
			Screen s = new Screen();
			Pattern impressoraBtn = new Pattern(getClass().getResource("/prints/tipoimpressora.png")).similar(0.4);
			Match mImpressora = s.wait(impressoraBtn, 10);
			Location locImpressora = mImpressora.getTarget();
			robot.mouseMove(locImpressora.getX(), locImpressora.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();
			Pattern impressoraUnselectBtn = new Pattern(getClass().getResource("/prints/comopdfunselected.png"))
					.similar(0.4);
			Match mImpressoraUnselected = s.wait(impressoraUnselectBtn, 10);
			Location locImpressoraUnselected = mImpressoraUnselected.getTarget();

			if (locImpressoraUnselected != null) {
				robot.mouseMove(locImpressoraUnselected.getX(), locImpressoraUnselected.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
			} else {
				Pattern impressoraSelectBtn = new Pattern(getClass().getResource("/prints/comopdfselected.png"))
						.similar(0.4);
				Match mImpressoraSelected = s.wait(impressoraSelectBtn, 10);
				Location locImpressoraSelected = mImpressoraSelected.getTarget();
				robot.mouseMove(locImpressoraSelected.getX(), locImpressoraSelected.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);

			}

			/* Nova captura */
			s = new Screen();

			Pattern imprimirChromeBtn = new Pattern(getClass().getResource("/prints/salvarchrome.png")).similar(0.3);

			Match mImprimirChrome = s.wait(imprimirChromeBtn, 10);
			Location locImprimirChrome = mImprimirChrome.getTarget();

			robot.mouseMove(locImprimirChrome.getX(), locImprimirChrome.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern caminhoSalvar = new Pattern(getClass().getResource("/prints/caminhoSalvar.png")).similar(0.3);

			Match mCaminhoSalvar = s.wait(caminhoSalvar, 10);
			Location locCaminhoSalvar = mCaminhoSalvar.getTarget();

			robot.mouseMove(locCaminhoSalvar.getX(), locCaminhoSalvar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			String pastaBase = "C:\\Users\\suporte07\\Downloads\\" + ano + "\\" + mesNomeExtenso + "\\" + porDiaNome;

			// Verifica se a pasta existe
			File pasta = new File(pastaBase);

			// Se não existir → cria
			if (!pasta.exists()) {
				System.out.println("📁 Pasta não existe. Criando: " + pasta);
				boolean criada = pasta.mkdirs();

				if (criada) {
					System.out.println("📁 Pasta criada com sucesso!");
				} else {
					System.out.println("❌ Erro ao criar a pasta!");
				}
			} else {
				System.out.println("📁 Pasta já existe.");
			}

			s.type(pastaBase);

			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern nomeArquivo = new Pattern(getClass().getResource("/prints/nomeArquivo.png")).similar(0.3);

			Match mNomeArquivo = s.wait(nomeArquivo, 10);
			Location locNomeArquivo = mNomeArquivo.getTarget();

			robot.mouseMove(locNomeArquivo.getX(), locNomeArquivo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			s.type(nomeArquivoSalvar);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern salvarBtn = new Pattern(getClass().getResource("/prints/salvar.png")).similar(0.3);

			Match mSalvar = s.wait(salvarBtn, 10);
			Location locSalvar = mSalvar.getTarget();

			robot.mouseMove(locSalvar.getX(), locSalvar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(5000);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
