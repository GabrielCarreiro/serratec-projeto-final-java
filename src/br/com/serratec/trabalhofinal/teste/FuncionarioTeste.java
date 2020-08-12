package br.com.serratec.trabalhofinal.teste;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JOptionPane;
import br.com.serratec.trabalhofinal.*;

public class FuncionarioTeste {
	
	public static void main(String[] args) {
		
				String file;
				String exit;
				Set <Funcionario> Func = new HashSet<Funcionario>();
				
		try {		
				JOptionPane.showMessageDialog(null, "Bem vindo ao sistema de folha de pagamento !","Olá",JOptionPane.INFORMATION_MESSAGE);
				file = JOptionPane.showInputDialog("Digite o caminho do arquivo de entrada","C:\\");
			
				BufferedReader reader = new BufferedReader(new FileReader(file));
				List<String> lista = new ArrayList<String>();
				List<String> listaDp = new ArrayList<String>();
				
				Funcionario f1 = null;
				Dependente dp = null;
				String[] vetor = new String [8];
			    String line = null;
			   while ((line = reader.readLine()) != null) {
			    
			   vetor = line.split(";");   
			   
			   if(vetor.length == 1) {
					lista.clear();
					continue;
				}
			   
			   if(lista.size() < 4) {
				  lista.add(vetor[0]);
				  lista.add(vetor[1]);
				  lista.add(vetor[2]);
				  lista.add(vetor[3]);
				 	  
				  String nome = lista.get(0);
				  String cpf = lista.get(1);
				  LocalDate dataNasci = LocalDate.parse(lista.get(2), DateTimeFormatter.BASIC_ISO_DATE);
				  double salario = Double.parseDouble(lista.get(3));
				  
				  f1 = new Funcionario(nome, cpf, dataNasci, salario);
				  
				  f1.verificarCpf(cpf);
			   }else {
				   listaDp.add(vetor[0]);
				   listaDp.add(vetor[1]);
				   listaDp.add(vetor[2]);
				   listaDp.add(vetor[3]);
				   
				   String nomeDp = listaDp.get(0);
				   String cpfDp = listaDp.get(1);
				   LocalDate dataNascDp = LocalDate.parse(listaDp.get(2), DateTimeFormatter.BASIC_ISO_DATE);
				   GrauParentesco parentesco = Enum.valueOf(GrauParentesco.class, listaDp.get(3));
				   
				   dp = new Dependente(nomeDp, cpfDp, dataNascDp, parentesco);
				   
				   dp.verificarCpf(cpfDp);
				   
				   f1.adicionarDependente(dp, cpfDp, dataNascDp);
				   
				   listaDp.clear();
			   		}
			   		
			   		Func.add(f1);
			   		
				   }
			   
			   reader.close();
			
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo");
		}
		   
		  try {
		
			  exit = JOptionPane.showInputDialog("Digite o caminho do arquivo de Saida", "C:\\");
				
			  BufferedWriter bf = new BufferedWriter(new FileWriter(exit));

			  for(Funcionario a : Func) {
			   	  a.calcularInss();
			   	  a.calcularIR();
			   	  a.calcularSalarioLiquido();
			   	  bf.write(a.getNome()+";"+a.getCpf()+";"+ String.format("%.2f", a.getDescontoInss())+ ";"+ String.format("%.2f", a.getDescontoIR())+";"+ String.format("%.2f", a.getSalarioLiquido()) +"\r\n");
		   }
			  
		   	bf.close();
			JOptionPane.showMessageDialog(null,"Arquivo criado com sucesso \\o/","Concluido",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			System.out.println("Erro ao gerar o arquivo");
		}

	}
}
