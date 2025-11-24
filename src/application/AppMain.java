package application;

import java.io.File;
import java.io.FilenameFilter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import entities.EmpresaABC;
import entities.EmpresaBB;
import entities.EmpresaCEF;
import entities.EmpresaSANTANDER;
import entities.EmpresaSICREDI;
import utils.AppLogger;
import utils.ChromeAdm;

public class AppMain {

	public static String salvaOnde = "C:\\USERS\\SUPORTE07\\DOWNLOADS\\";
	public static LocalDate data = LocalDate.of(2025, 10, 20);

	public static void main(String[] args) {
		ChromeAdm chromeAdm = new ChromeAdm();
		System.out.println("Iniciando robo de geração de extratos.");

		try {

			List<String> pendentes = checaGerados();
			AppLogger appLogger = new AppLogger();

			for (String extrato : pendentes) {
				if (extrato.equals("SANTANDER ADM")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato SANTANDER ADM");

					EmpresaSANTANDER empresa = appLogger.logarSANTANDER(10, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);

					}
					empresa.setData(data);
					empresa.setNomeArquivo("SANTANDER ADM");
					empresa.geraExtratoCorrenteSANTANDERadm();

				} else if (extrato.equals("ABC")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato ABC");
					EmpresaABC empresa = appLogger.logarABC(10, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);
					}
					empresa.setData(data);
					empresa.setNomeArquivo("ABC");
					empresa.geraExtratoCorrenteABC();

				} else if (extrato.equals("BB ADM COBRANCA")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato BB ADM COBRANCA");

					EmpresaBB empresa = appLogger.logarBB(10, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);
					}
					empresa.setData(data);
					empresa.setNomeArquivo("BB ADM COBRANCA");
					empresa.geraExtratoCobrancaBB();

				} else if (extrato.equals("BB ADM")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato BB ADM");

					EmpresaBB empresa = appLogger.logarBB(10, 1);

					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);

					}
					empresa.setNomeArquivo("BB ADM");
					empresa.setData(data);
					empresa.geraExtratoCorrenteBB();
				} else if (extrato.equals("BB ESPECIAIS")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato BB ESPECIAIS");
					EmpresaBB empresa = appLogger.logarBB(20, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);

					}
					empresa.setData(data);
					empresa.setNomeArquivo("BB ESPECIAIS");
					empresa.geraExtratoCorrenteBB();

				}

				else if (extrato.equals("BB QUALITY")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato BB QUALITY");

					EmpresaBB empresa = appLogger.logarBB(100, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);

					}
					empresa.setData(data);
					empresa.setNomeArquivo("BB QUALITY");
					empresa.geraExtratoCorrenteBB();

				} else if (extrato.equals("BB SERVIÇOS")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato BB SERVIÇOS");

					EmpresaBB empresa = appLogger.logarBB(40, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);

					}
					empresa.setNomeArquivo("BB SERVIÇOS");
					empresa.setData(data);
					empresa.geraExtratoCorrenteBB();

				} else if (extrato.equals("BB VIG FILIAL")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato BB VIG FILIAL");

					EmpresaBB empresa = appLogger.logarBB(30, 2);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);

					}
					empresa.setData(data);
					empresa.setNomeArquivo("BB VIG FILIAL");
					empresa.geraExtratoCorrenteBB();

				} else if (extrato.equals("SANTANDER QUALITY")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato SANTANDER QUALITY");

					EmpresaSANTANDER empresa = appLogger.logarSANTANDER(10, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);
					}

					empresa.setData(data);
					empresa.setNomeArquivo("SANTANDER QUALITY");
					empresa.geraExtratoCorrenteSANTANDERquality();
				} else if (extrato.equals("BB VIG")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato BB VIG");

					EmpresaBB empresa = appLogger.logarBB(30, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);

					}
					empresa.setData(data);
					empresa.setNomeArquivo("BB VIG");
					empresa.geraExtratoCorrenteBB();

				}

				else if (extrato.equals("SICREDI ADM")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato SICREDI ADM");

					EmpresaSICREDI empresa = appLogger.logarSICREDI(10, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);

					}
					empresa.setData(data);
					empresa.setNomeArquivo("SICREDI ADM");
					empresa.geraExtratoCorrenteSICREDI();

				} else if (extrato.equals("SICREDI VIG")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato SICREDI VIG");

					EmpresaSICREDI empresa = appLogger.logarSICREDI(30, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);

					}
					empresa.setData(data);
					empresa.setNomeArquivo("SICREDI VIG");
					empresa.geraExtratoCorrenteSICREDI();
				}

				else if (extrato.equals("SANTANDER VIG")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato SANTANDER VIG");

					EmpresaSANTANDER empresa = appLogger.logarSANTANDER(10, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);
					}
					empresa.setData(data);
					empresa.setNomeArquivo("SANTANDER VIG");
					empresa.geraExtratoCorrenteSANTANDERvig();
				}

				else if (extrato.equals("CAIXA VIG COBRANCA")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato CAIXA VIG COBRANCA");

					EmpresaCEF empresa = appLogger.logarCEF(30, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);
					}
					empresa.setData(data);
					empresa.setNomeArquivo("CAIXA VIG COBRANCA");
					empresa.geraExtratoCobranca();
				}

				else if (extrato.equals("CAIXA ADM COBRANCA")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato CAIXA ADM COBRANCA");

					EmpresaCEF empresa = appLogger.logarCEF(10, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);
					}
					empresa.setData(data);
					empresa.setNomeArquivo("CAIXA ADM COBRANCA");
					empresa.geraExtratoCobranca();
				}

				else if (extrato.equals("CAIXA ADM GARANTIDA")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato CAIXA ADM GARANTIDA");

					EmpresaCEF empresa = appLogger.logarCEF(10, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);
					}
					empresa.setData(data);
					empresa.setNomeArquivo("CAIXA ADM GARANTIDA");
					empresa.geraExtratoGarantidaADM();

				}

				else if (extrato.equals("CAIXA VIG GARANTIDA")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato CAIXA VIG GARANTIDA");

					EmpresaCEF empresa = appLogger.logarCEF(30, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);
					}
					empresa.setData(data);
					empresa.setNomeArquivo("CAIXA VIG GARANTIDA");
					empresa.geraExtratoGarantidaVIG();
				} else if (extrato.equals("CAIXA ADM")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato CAIXA ADM");

					EmpresaCEF empresa = appLogger.logarCEF(10, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);
					}
					empresa.setData(data);
					empresa.setNomeArquivo("CAIXA ADM ");
					empresa.geraExtratoCorrenteADM();

				} else if (extrato.equals("CAIXA VIG ")) {
					System.out.println();
					System.out.println("Iniciando geração do extrato CAIXA VIG ");

					EmpresaCEF empresa = appLogger.logarCEF(30, 1);
					empresa.setCaminhoPastaSalvar(salvaOnde);
					if (data == null) {
						data = LocalDate.now().minusDays(1);
					}
					empresa.setData(data);
					empresa.setNomeArquivo("CAIXA VIG ");
					empresa.geraExtratoCorrenteVIG();
				}

			}

			chromeAdm.fechaChrome();

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * else if (extrato.equals("CAIXA ADM COBRANCA")) {
		 * System.out.println("Faltando: CAIXA ADM COBRANCA"); } else if
		 * (extrato.equals("CAIXA ADM")) { System.out.println("Faltando: CAIXA ADM"); }
		 * else if (extrato.equals("CAIXA VIG")) {
		 * System.out.println("Faltando: CAIXA VIG"); } else if
		 * (extrato.equals("XP ADM 1")) { System.out.println("Faltando: XP ADM 1"); }
		 * else if (extrato.equals("XP ADM")) { System.out.println("Faltando: XP ADM");
		 * } else if (extrato.equals("XP CONTINGENCIA")) {
		 * System.out.println("Faltando: XP CONTINGENCIA"); } else if
		 * (extrato.equals("XP QUALITY")) { System.out.println("Faltando: XP QUALITY");
		 * } else if (extrato.equals("XP VIG 1")) {
		 * System.out.println("Faltando: XP VIG 1"); } else if
		 * (extrato.equals("XP VIG")) { System.out.println("Faltando: XP VIG"); }
		 */

	}

	public static String caminhoPastaData(LocalDate data) {

		if (data == null) {
			LocalDate dataExtrato = LocalDate.now().minusDays(1);
			DateTimeFormatter formatterano = DateTimeFormatter.ofPattern("yyyy");
			String ano = dataExtrato.format(formatterano);
			DateTimeFormatter mesExtensoFormatter = DateTimeFormatter.ofPattern("MM MMMM", new Locale("pt", "BR"));
			String mesNomeExtenso = dataExtrato.format(mesExtensoFormatter);
			DateTimeFormatter porDiaFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
			String porDiaNome = dataExtrato.format(porDiaFormatter);

			return ano + "\\" + mesNomeExtenso + "\\" + porDiaNome;
		} else {
			LocalDate dataExtrato = data;
			DateTimeFormatter formatterano = DateTimeFormatter.ofPattern("yyyy");
			String ano = dataExtrato.format(formatterano);
			DateTimeFormatter mesExtensoFormatter = DateTimeFormatter.ofPattern("MM MMMM", new Locale("pt", "BR"));
			String mesNomeExtenso = dataExtrato.format(mesExtensoFormatter);
			DateTimeFormatter porDiaFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
			String porDiaNome = dataExtrato.format(porDiaFormatter);
			return ano + "\\" + mesNomeExtenso + "\\" + porDiaNome;
		}

	}

	public static List<String> checaGerados() {
		String caminhoSalva = salvaOnde + caminhoPastaData(data);

		List<String> listaExtratos = Arrays.asList("ABC", "BB ADM COBRANCA", "SANTANDER ADM", "BB ADM", "BB ESPECIAIS",
				"BB QUALITY", "BB SERVIÇOS", "BB VIG FILIAL", "BB VIG", "CAIXA ADM COBRANCA", "SANTANDER VIG",
				"CAIXA ADM", "CAIXA ADM GARANTIDA", "CAIXA VIG COBRANCA", "CAIXA VIG", "CAIXA VIG GARANTIDA",
				"SANTANDER QUALITY", "SICREDI ADM", "SICREDI VIG", "XP ADM 1", "XP ADM", "XP CONTINGENCIA",
				"XP QUALITY", "XP VIG 1", "XP VIG");

		File pasta = new File(caminhoSalva);

		// ---- CRIA A PASTA SE NÃO EXISTIR ----
		if (!pasta.exists()) {
			boolean criada = pasta.mkdirs();
			if (criada) {
				System.out.println("Pasta criada: " + caminhoSalva);
			} else {
				System.out.println("⚠ Erro ao criar a pasta: " + caminhoSalva);
				// Se não foi possível criar a pasta, tudo continua pendente
				return listaExtratos;
			}
		}

		// ---- LÊ OS PDFs ----
		File[] arquivosPdf = pasta.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".pdf");
			}
		});

		// Se não conseguiu listar (algo deu errado)
		if (arquivosPdf == null) {
			System.out.println("⚠ Não foi possível listar arquivos da pasta.");
			return listaExtratos; // tudo pendente
		}

		List<String> nomesEncontrados = new ArrayList<>();

		// Remove extensão .pdf
		for (File f : arquivosPdf) {
			String nome = f.getName();
			if (nome.toLowerCase().endsWith(".pdf")) {
				nome = nome.substring(0, nome.length() - 4);
				nomesEncontrados.add(nome);
			}
		}

		// Extratos faltando
		List<String> extratosFaltando = new ArrayList<>();

		for (String extrato : listaExtratos) {
		    // comparação consistente ignorando possíveis espaços indesejados
		    if (!nomesEncontrados.contains(extrato.trim())) {
		        extratosFaltando.add(extrato);
		    }
		}

		// Imprime resultado
		if (extratosFaltando.isEmpty()) {
		    System.out.println("Todos os extratos foram gerados!");
		} else {
		    System.out.println("Extratos faltando:");
		    for (String f : extratosFaltando) {
		        System.out.println(f);
		    }
		}

		return extratosFaltando;
	}

}
