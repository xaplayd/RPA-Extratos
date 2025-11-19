package utils;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import entities.EmpresaBB;

public class AppLogger {

	public void logarBB(Integer empresa, Integer filial) {

		EmpresaBB tempEmpresa = new EmpresaBB();

		String caminho = "src/data/acessBB.csv";
		List<EmpresaBB> empresas = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {

			String linha;

			br.readLine();

			while ((linha = br.readLine()) != null) {
				String[] campos = linha.split(";");

				if (campos.length < 4)
					continue;

				Integer codEmp = Integer.parseInt(campos[0].trim());
				Integer codFil = Integer.parseInt(campos[1].trim());
				String chaveJ = campos[2].trim();
				String pass = campos[3].trim();

				EmpresaBB e = new EmpresaBB(codEmp, codFil, chaveJ, pass);
				empresas.add(e);
			}

		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo: " + e.getMessage());
		}

		for (EmpresaBB emp : empresas) {
			if (emp.getCodEmp().equals(empresa) && emp.getCodFil().equals(filial)) {
				tempEmpresa = emp;
			}
		}

		try {
			Robot robot;
			robot = new Robot();

			boolean capsOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
			if (capsOn) {
				robot.keyPress(KeyEvent.VK_CAPS_LOCK);
				robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
				Thread.sleep(500); // espera estabilizar
			}
			
			Screen s = new Screen();
			s.type(tempEmpresa.getChaveJ());
			s.type(Key.TAB);
			s.type(tempEmpresa.getPass());
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type(Key.TAB);
			s.type(Key.ENTER);
			Thread.sleep(20000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
