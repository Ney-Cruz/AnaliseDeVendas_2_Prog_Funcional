package package16_ProgramacaoFuncional.challenge.desafio_AnaliseDeVendas_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import package16_ProgramacaoFuncional.challenge.desafio_AnaliseDeVendas_2.entities.Sale;

public class AnaliseDeVendas_2 {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		System.out.print("Entre o caminho do arquivo: C:\\temp\\in.csv");

		String path = "C:\\temp\\in.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Sale> sales = new ArrayList<>();

			String line = br.readLine();

			while (line != null) {
				//@formatter:off
				String[] fields = line.split(",");
				sales.add(new Sale(Integer.parseInt(fields[0]),
								   Integer.parseInt(fields[1]),
								   fields[2],
								   Integer.parseInt(fields[3]),
								   Double.parseDouble(fields[4])));
				//@formatter:on
				line = br.readLine();
			}
			System.out.println("\n\nTotal de vendas por vendedor: ");
			//@formatter:off
			Set<String> setSellers = sales.stream().map(s -> s.getSeller())
												   .collect(Collectors.toSet());

			for (String seller : setSellers) {
				Double totalSales = sales.stream().filter(s -> s.getSeller().equals(seller))
												  .map(s -> s.getTotal())
												  .reduce(0.0, (x, y) -> x + y);
												  //@formatter:on
				System.out.println(seller + " - R$ " + String.format("%.2f", totalSales));
			}
		} catch (IOException e) {
			System.out.println("Erro: c:\\in (O sistema não pode encontrar o arquivo especificado)");
		}
	}
}
