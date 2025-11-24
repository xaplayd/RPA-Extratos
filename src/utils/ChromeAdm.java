package utils;

import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ChromeAdm {

	String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";

	public void fechaChrome() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Thread.sleep(2000); // aguarda o encerramento

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public void abreChromeBB() {
		try {
			Runtime.getRuntime().exec(new String[] { chromePath, "--start-maximized", "--incognito",
					"https://autoatendimento.bb.com.br/apf-apj-acesso" });

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public void abreChromeSANTANDER() {
		try {
			Runtime.getRuntime().exec(
					new String[] { chromePath, "--start-maximized", "--incognito", "https://www.santander.com.br/" });

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public void abreChromeSICREDI() {
		try {
			Runtime.getRuntime()
					.exec(new String[] { chromePath, "--start-maximized", "--incognito", "IBPJ.SICREDI.COM.BR " });

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public void abreChromeABC() {
		try {
			Runtime.getRuntime().exec(new String[] { chromePath, "--start-maximized", "--incognito",
					"https://wwws.abcbrasil.com.br/internetbankingpj/auth" });

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public void abreChromeCEF() {
		try {
			Runtime.getRuntime().exec(new String[] { chromePath, "--start-maximized", "--incognito",
					"https://gerenciador.caixa.gov.br/" });

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public boolean confirmaChegadaNaTela(String caminhoTela) {
		try {
			Screen s = new Screen();
			Pattern tela = new Pattern(getClass().getResource(caminhoTela)).similar(0.8);

			Match mTela = s.wait(tela, 10);
			Location locTela = mTela.getTarget();
			if (locTela != null) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;

	}

}
