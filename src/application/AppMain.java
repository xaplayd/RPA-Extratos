package application;

import empresas.BBEmpresa10;
import empresas.BBEmpresa100;
import empresas.BBEmpresa20;
import empresas.BBEmpresa30Filial;
import empresas.BBEmpresa30Matriz;
import empresas.BBEmpresa40;

public class AppMain {
	public static void main(String[] args) {
		
		
		try {
		BBEmpresa20 empresa20 = new BBEmpresa20();
		empresa20.geraExtratos();
		
		Thread.sleep(5000);

		BBEmpresa40 empresa40 = new BBEmpresa40();
		empresa40.geraExtratos();
		
		Thread.sleep(5000);
		
		BBEmpresa100 empresa100 = new BBEmpresa100();
		empresa100.geraExtratos();
		
		Thread.sleep(5000);
		
		BBEmpresa30Matriz empresa30matriz = new BBEmpresa30Matriz();
		empresa30matriz.geraExtratos();
		
		Thread.sleep(5000);
		
		BBEmpresa30Filial empresa30filial = new BBEmpresa30Filial();
		empresa30filial.geraExtratos();
		
		Thread.sleep(5000);
		
		BBEmpresa10 empresa10 = new BBEmpresa10();
		empresa10.geraExtratos();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
