package main.java.utils;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.sikuli.script.App;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;


public class ArchiveAdm {

	ChromeAdm chromeAdm = new ChromeAdm();

	public void salvarArquivoBB(String nomeArquivoSalvar, String caminhoSalvarPasta, LocalDate data) {
		try {

			LocalDate dataExtrato = data;

			DateTimeFormatter formatterano = DateTimeFormatter.ofPattern("yyyy");
			String ano = dataExtrato.format(formatterano);
			DateTimeFormatter mesExtensoFormatter = DateTimeFormatter.ofPattern("MM MMMM", new Locale("pt", "BR"));
			String mesNomeExtenso = dataExtrato.format(mesExtensoFormatter);
			DateTimeFormatter porDiaFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
			String porDiaNome = dataExtrato.format(porDiaFormatter);
			

			Thread.sleep(5000);

			Robot robot = new Robot();
			Screen s = new Screen();
			Pattern impressoraBtn = getPatternFromJar("/prints/tipoimpressora.png",0.4);
			Match mImpressora = s.wait(impressoraBtn, 10);
			Location locImpressora = mImpressora.getTarget();
			robot.mouseMove(locImpressora.getX(), locImpressora.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();
			Pattern impressoraUnselectBtn = getPatternFromJar("/prints/comopdfunselected.png",0.4);
			Match mImpressoraUnselected = s.wait(impressoraUnselectBtn, 10);
			Location locImpressoraUnselected = mImpressoraUnselected.getTarget();

			if (locImpressoraUnselected != null) {
				robot.mouseMove(locImpressoraUnselected.getX(), locImpressoraUnselected.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
			} else {
				Pattern impressoraSelectBtn = getPatternFromJar("/prints/comopdfselected.png",0.4);
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

			Pattern imprimirChromeBtn = getPatternFromJar("/prints/salvarchrome.png",0.3);

			Match mImprimirChrome = s.wait(imprimirChromeBtn, 10);
			Location locImprimirChrome = mImprimirChrome.getTarget();

			robot.mouseMove(locImprimirChrome.getX(), locImprimirChrome.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(5000);

			while (chromeAdm.confirmaChegadaNaTela("/prints/telasalvar.png") == false) {
				Thread.sleep(3000);
			}

			/* Nova captura */
			s = new Screen();

			Pattern caminhoSalvar = getPatternFromJar("/prints/caminhoSalvar.png",0.3);

			Match mCaminhoSalvar = s.wait(caminhoSalvar, 10);
			Location locCaminhoSalvar = mCaminhoSalvar.getTarget();

			robot.mouseMove(locCaminhoSalvar.getX(), locCaminhoSalvar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
			Thread.sleep(3000);

			String pastaBase = caminhoSalvarPasta + ano + "\\" + mesNomeExtenso + "\\" + porDiaNome;

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

			App.setClipboard(pastaBase); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern nomeArquivo = getPatternFromJar("/prints/nomeArquivo.png",0.3);

			Match mNomeArquivo = s.wait(nomeArquivo, 10);
			Location locNomeArquivo = mNomeArquivo.getTarget();

			robot.mouseMove(locNomeArquivo.getX(), locNomeArquivo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			App.setClipboard(nomeArquivoSalvar); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern salvarBtn = getPatternFromJar("/prints/salvar.png",0.3);

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

		System.out.println("Extrato salvo com sucesso.");

	}

	public void salvarArquivoABC(String nomeArquivoSalvar, String caminhoSalvarPasta, LocalDate data) {

		try {

			LocalDate dataExtrato = data;

			DateTimeFormatter formatterano = DateTimeFormatter.ofPattern("yyyy");
			String ano = dataExtrato.format(formatterano);
			DateTimeFormatter mesExtensoFormatter = DateTimeFormatter.ofPattern("MM MMMM", new Locale("pt", "BR"));
			String mesNomeExtenso = dataExtrato.format(mesExtensoFormatter);
			DateTimeFormatter porDiaFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
			String porDiaNome = dataExtrato.format(porDiaFormatter);

			while (chromeAdm.confirmaChegadaNaTela("/prints/telasalvar.png") == false) {
				Thread.sleep(3000);
			}

			Robot robot = new Robot();
			Screen s = new Screen();
			Pattern caminhoSalvar = getPatternFromJar("/prints/caminhoSalvar.png",0.3);
			Match mCaminhoSalvar = s.wait(caminhoSalvar, 10);
			Location locCaminhoSalvar = mCaminhoSalvar.getTarget();

			robot.mouseMove(locCaminhoSalvar.getX(), locCaminhoSalvar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
			Thread.sleep(3000);

			String pastaBase = caminhoSalvarPasta + ano + "\\" + mesNomeExtenso + "\\" + porDiaNome;

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

			App.setClipboard(pastaBase); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern nomeArquivo = getPatternFromJar("/prints/abc/nomeArquivo.png",0.3);

			Match mNomeArquivo = s.wait(nomeArquivo, 10);
			Location locNomeArquivo = mNomeArquivo.getTarget();

			robot.mouseMove(locNomeArquivo.getX(), locNomeArquivo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			s.keyDown(Key.CTRL);
			s.type("a");
			s.keyUp(Key.CTRL);

			App.setClipboard(nomeArquivoSalvar); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern salvarBtn = getPatternFromJar("/prints/salvar.png",0.3);

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

		System.out.println("Extrato salvo com sucesso.");

	}

	public void salvarArquivoSICREDI(String nomeArquivoSalvar, String caminhoSalvarPasta, LocalDate data) {

		try {

			LocalDate dataExtrato = data;

			DateTimeFormatter formatterano = DateTimeFormatter.ofPattern("yyyy");
			String ano = dataExtrato.format(formatterano);
			DateTimeFormatter mesExtensoFormatter = DateTimeFormatter.ofPattern("MM MMMM", new Locale("pt", "BR"));
			String mesNomeExtenso = dataExtrato.format(mesExtensoFormatter);
			DateTimeFormatter porDiaFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
			String porDiaNome = dataExtrato.format(porDiaFormatter);

			while (chromeAdm.confirmaChegadaNaTela("/prints/telasalvar.png") == false) {
				Thread.sleep(3000);
			}

			Robot robot = new Robot();
			Screen s = new Screen();
			Pattern caminhoSalvar = getPatternFromJar("/prints/caminhoSalvar.png",0.3);
			Match mCaminhoSalvar = s.wait(caminhoSalvar, 10);
			Location locCaminhoSalvar = mCaminhoSalvar.getTarget();

			robot.mouseMove(locCaminhoSalvar.getX(), locCaminhoSalvar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
			Thread.sleep(3000);

			String pastaBase = caminhoSalvarPasta + ano + "\\" + mesNomeExtenso + "\\" + porDiaNome;

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

			App.setClipboard(pastaBase); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern nomeArquivo = getPatternFromJar("/prints/abc/nomeArquivo.png",0.3);

			Match mNomeArquivo = s.wait(nomeArquivo, 10);
			Location locNomeArquivo = mNomeArquivo.getTarget();

			robot.mouseMove(locNomeArquivo.getX(), locNomeArquivo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			s.keyDown(Key.CTRL);
			s.type("a");
			s.keyUp(Key.CTRL);

			App.setClipboard(nomeArquivoSalvar); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern salvarBtn = getPatternFromJar("/prints/salvar.png",0.3);

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

		System.out.println("Extrato salvo com sucesso.");

	}

	public void salvarArquivoSANTANDER(String nomeArquivoSalvar, String caminhoSalvarPasta, LocalDate data) {

		try {

			LocalDate dataExtrato = data;

			DateTimeFormatter formatterano = DateTimeFormatter.ofPattern("yyyy");
			String ano = dataExtrato.format(formatterano);
			DateTimeFormatter mesExtensoFormatter = DateTimeFormatter.ofPattern("MM MMMM", new Locale("pt", "BR"));
			String mesNomeExtenso = dataExtrato.format(mesExtensoFormatter);
			DateTimeFormatter porDiaFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
			String porDiaNome = dataExtrato.format(porDiaFormatter);

			Thread.sleep(5000);

			Robot robot = new Robot();
			Screen s = new Screen();

			Pattern caminhoSalvar = getPatternFromJar("/prints/caminhoSalvar.png",0.3);
			Match mCaminhoSalvar = s.wait(caminhoSalvar, 10);
			Location locCaminhoSalvar = mCaminhoSalvar.getTarget();

			robot.mouseMove(locCaminhoSalvar.getX(), locCaminhoSalvar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
			Thread.sleep(3000);

			String pastaBase = caminhoSalvarPasta + ano + "\\" + mesNomeExtenso + "\\" + porDiaNome;

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

			App.setClipboard(pastaBase); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern nomeArquivo = getPatternFromJar("/prints/abc/nomeArquivo.png",0.3);

			Match mNomeArquivo = s.wait(nomeArquivo, 10);
			Location locNomeArquivo = mNomeArquivo.getTarget();

			robot.mouseMove(locNomeArquivo.getX(), locNomeArquivo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			s.keyDown(Key.CTRL);
			s.type("a");
			s.keyUp(Key.CTRL);

			App.setClipboard(nomeArquivoSalvar); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern salvarBtn = getPatternFromJar("/prints/salvar.png",0.3);

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

		System.out.println("Extrato salvo com sucesso.");
	}

	public void salvarArquivoCEF(String nomeArquivoSalvar, String caminhoSalvarPasta, LocalDate data) {
		try {

			LocalDate dataExtrato = data;

			DateTimeFormatter formatterano = DateTimeFormatter.ofPattern("yyyy");
			String ano = dataExtrato.format(formatterano);
			DateTimeFormatter mesExtensoFormatter = DateTimeFormatter.ofPattern("MM MMMM", new Locale("pt", "BR"));
			String mesNomeExtenso = dataExtrato.format(mesExtensoFormatter);
			DateTimeFormatter porDiaFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
			String porDiaNome = dataExtrato.format(porDiaFormatter);
			



			Robot robot = new Robot();
			Screen s = new Screen();
			s.keyDown(Key.WIN);
			s.type(Key.UP);
			s.keyUp(Key.WIN);
			Thread.sleep(3000);
			s = new Screen();
			Pattern impressoraBtn = getPatternFromJar("/prints/tipoimpressora.png",0.4);
			Match mImpressora = s.wait(impressoraBtn, 10);
			Location locImpressora = mImpressora.getTarget();
			robot.mouseMove(locImpressora.getX(), locImpressora.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(3000);

			s = new Screen();
			Pattern impressoraUnselectBtn = getPatternFromJar("/prints/comopdfunselected.png",0.4);
			Match mImpressoraUnselected = s.wait(impressoraUnselectBtn, 10);
			Location locImpressoraUnselected = mImpressoraUnselected.getTarget();

			if (locImpressoraUnselected != null) {
				robot.mouseMove(locImpressoraUnselected.getX(), locImpressoraUnselected.getY());
				Thread.sleep(300);
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(3000);
			} else {
				Pattern impressoraSelectBtn = getPatternFromJar("/prints/comopdfselected.png",0.4);
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

			Pattern imprimirChromeBtn = getPatternFromJar("/prints/salvarchrome.png",0.3);

			Match mImprimirChrome = s.wait(imprimirChromeBtn, 10);
			Location locImprimirChrome = mImprimirChrome.getTarget();

			robot.mouseMove(locImprimirChrome.getX(), locImprimirChrome.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			Thread.sleep(5000);

			while (chromeAdm.confirmaChegadaNaTela("/prints/telasalvar.png") == false) {
				Thread.sleep(3000);
			}

			/* Nova captura */
			s = new Screen();

			Pattern caminhoSalvar = getPatternFromJar("/prints/caminhoSalvar.png",0.3);

			Match mCaminhoSalvar = s.wait(caminhoSalvar, 10);
			Location locCaminhoSalvar = mCaminhoSalvar.getTarget();

			robot.mouseMove(locCaminhoSalvar.getX(), locCaminhoSalvar.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
			Thread.sleep(3000);

			String pastaBase = caminhoSalvarPasta + ano + "\\" + mesNomeExtenso + "\\" + porDiaNome;

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

			App.setClipboard(pastaBase); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			s.keyDown(Key.ENTER);
			s.keyUp(Key.ENTER);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern nomeArquivo = getPatternFromJar("/prints/nomeArquivo.png",0.3);

			Match mNomeArquivo = s.wait(nomeArquivo, 10);
			Location locNomeArquivo = mNomeArquivo.getTarget();

			robot.mouseMove(locNomeArquivo.getX(), locNomeArquivo.getY());
			Thread.sleep(300);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			App.setClipboard(nomeArquivoSalvar); // copia para clipboard

			// Colar no campo desejado
			s.type("v", KeyModifier.CTRL);

			Thread.sleep(5000);

			/* Nova captura */
			s = new Screen();

			Pattern salvarBtn = getPatternFromJar("/prints/salvar.png",0.3);

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

		System.out.println("Extrato salvo com sucesso.");

	}
    

    public Pattern getPatternFromJar(String resourcePath, double similar) throws IOException {
        InputStream is = getClass().getResourceAsStream(resourcePath);
        if (is == null) {
            throw new IOException("Recurso não encontrado no JAR: " + resourcePath);
        }

        // Cria arquivo temporário
        String tempFileName = new File(resourcePath).getName();
        File tempFile = File.createTempFile("sikuli_", "_" + tempFileName);
        tempFile.deleteOnExit();

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = is.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }
        } finally {
            is.close();
        }

        return new Pattern(tempFile.getAbsolutePath()).similar(similar);
    }




}