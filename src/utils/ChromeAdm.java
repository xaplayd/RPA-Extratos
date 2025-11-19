package utils;

import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class ChromeAdm {

	public void fechaChrome() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Thread.sleep(2000); // aguarda o encerramento

		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public void abreChrome() {
		try {
			String chromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
			Runtime.getRuntime().exec(new String[] { chromePath, "--start-maximized", "--incognito",
					"https://autoatendimento.bb.com.br/apf-apj-acesso" });
			Thread.sleep(15000);

			while (confirmaTelaLogin() == false) {
				Thread.sleep(15000);
			}
		} catch (Exception e) {
			System.out.println(e);

		}
	}

	public boolean confirmaTelaLogin() {
		try {
			Screen s = new Screen();
			Pattern telaLogin = new Pattern(getClass().getResource("/prints/telalogin.png")).similar(0.5);

			Match mLogin = s.wait(telaLogin, 10);
			Location locLogin = mLogin.getTarget();
			if (locLogin != null) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean confirmaEntradaPortal() {
		try {
			Screen s = new Screen();
			Pattern telaPortal = new Pattern(getClass().getResource("/prints/acessoportal.png")).similar(0.5);

			Match mPortal = s.wait(telaPortal, 10);
			Location locPortal = mPortal.getTarget();
			if (locPortal != null) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean confirmaEntradaExtrato() {
		try {
			Screen s = new Screen();
			Pattern telaExtrato = new Pattern(getClass().getResource("/prints/telaextrato.png")).similar(0.5);

			Match mExtrato = s.wait(telaExtrato, 10);
			Location locExtrat = mExtrato.getTarget();
			if (locExtrat != null) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	
}
