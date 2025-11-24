package main.java.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Connection {

	String openVpnPath = "C:\\Program Files\\OpenVPN\\bin\\openvpn.exe";

	/**
	 * Inicia o OpenVPN com verificação de processo e reinício se já estiver rodando
	 */
	public void startVpn() {
		try {
			// --- Verifica se o OpenVPN já está rodando ---
			boolean isRunning = false;
			Process tasklist = Runtime.getRuntime().exec("tasklist /FI \"IMAGENAME eq openvpn.exe\"");
			BufferedReader reader = new BufferedReader(new InputStreamReader(tasklist.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.toLowerCase().contains("openvpn.exe")) {
					isRunning = true;
					break;
				}
			}

			// --- Se estiver rodando, finaliza e aguarda ---
			if (isRunning) {
				System.out.println("OpenVPN já está rodando. Finalizando...");
				Runtime.getRuntime().exec(new String[] { "powershell", "-Command",
						"Start-Process 'taskkill.exe' -ArgumentList '/F','/IM','openvpn.exe' -Verb RunAs" });

				// Aguarda o processo terminar
				int tentativas = 0;
				while (tentativas < 15) { // espera até 15 segundos
					Thread.sleep(1000);
					isRunning = false;

					Process check = Runtime.getRuntime().exec("tasklist /FI \"IMAGENAME eq openvpn.exe\"");
					BufferedReader checkReader = new BufferedReader(new InputStreamReader(check.getInputStream()));
					while ((line = checkReader.readLine()) != null) {
						if (line.toLowerCase().contains("openvpn.exe")) {
							isRunning = true;
							break;
						}
					}

					if (!isRunning)
						break; // processo finalizado
					tentativas++;
				}

				if (isRunning) {
					System.out.println("Não foi possível finalizar o OpenVPN automaticamente.");
					return; // interrompe se não conseguir fechar
				} else {
					System.out.println("OpenVPN finalizado com sucesso.");
				}
			}

			// --- Inicia o OpenVPN ---
			System.out.println("Iniciando OpenVPN...");
			Runtime.getRuntime()
					.exec(new String[] { "powershell", "-Command", "Start-Process '" + openVpnPath + "' "
							+ "-ArgumentList '--config C:\\Program Files\\OpenVPN\\config\\ADSERVI\\ADSERVI-AUTO.ovpn',"
							+ "'--askpass C:\\Program Files\\OpenVPN\\config\\ADSERVI\\psw.txt',"
							+ "'--log C:\\Program Files\\OpenVPN\\config\\ADSERVI\\openvpn.log' " + "-Verb RunAs" });

		} catch (Exception e) {
			System.out.println("Erro ao iniciar OpenVPN: " + e.getMessage());
		}
	}

	public void fechaOpenVpn() {
		try {
			Runtime.getRuntime().exec(new String[] { "powershell", "-Command",
					"Start-Process 'taskkill.exe' -ArgumentList '/F','/IM','openvpn.exe' -Verb RunAs" });
			Thread.sleep(2000); // aguarda o encerramento
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
