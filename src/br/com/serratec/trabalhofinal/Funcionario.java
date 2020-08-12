package br.com.serratec.trabalhofinal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Funcionario extends Pessoa implements CalculadoraIR {
	private double salarioBruto;
	private double salarioLiquido;
	private double descontoInss;
	private double descontoIR;
	private List<Dependente> listaDependentes = new ArrayList<Dependente>();

	public Funcionario(String nome, String cpf, LocalDate dataNascimento, double salarioBruto) {
		super(nome, cpf, dataNascimento);
		this.salarioBruto = salarioBruto;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

	public double getDescontoInss() {
		return descontoInss;
	}

	public void setDescontoInss(double descontoInss) {
		this.descontoInss = descontoInss;
	}

	public double getDescontoIR() {
		return descontoIR;
	}

	public void setDescontoIR(double descontoIR) {
		this.descontoIR = descontoIR;
	}

	@Override
	public String toString() {
		return "Funcionario [salarioBruto=" + salarioBruto + ", salarioLiquido=" + salarioLiquido + ", descontoInss="
				+ descontoInss + ", descontoIR=" + descontoIR + "Dependente" + listaDependentes + "]";
	}

	@Override
	public double calcularInss() {
		if (salarioBruto <= 1751.81) {
			descontoInss = salarioBruto * 0.08;
		}
		if (salarioBruto >= 1751.82 && salarioBruto <= 2919.72) {
			descontoInss = salarioBruto * 0.09;
		}
		if (salarioBruto >= 2919.73 && salarioBruto <= 5839.45 || salarioBruto >= 5839.46) {
			descontoInss = salarioBruto * 0.11;
		}
		return descontoInss;
	}

	public void adicionarDependente(Dependente d, String cpfDepen, LocalDate data) {

		if (LocalDate.now().getYear() - data.getYear() >= 18) {
			throw new DependenteException("O dependente é maior de idade ");
		} else {
			for (Dependente a : listaDependentes) {
				if (a.getCpf().equals(cpfDepen)) {
					throw new DependenteException("O cpf já existe");
				}
			}
			listaDependentes.add(d);
		}
	}

	@Override
	public double calcularIR() {

		double valorCalculo;

		valorCalculo = salarioBruto - descontoInss - listaDependentes.size() * 189.59;

		if (valorCalculo <= 1903.98) {
			return 0;

		}
		if (valorCalculo > 1903.99 && valorCalculo < 2826.65) {
			descontoIR = valorCalculo * 0.075 - 142.8;

		}
		if (valorCalculo > 2826.66 && valorCalculo < 3751.05) {
			descontoIR = valorCalculo * 0.15 - 354.8;

		}
		if (valorCalculo > 3751.06 && valorCalculo < 4664.68) {
			descontoIR = valorCalculo * 0.225 - 636.13;

		}
		if (valorCalculo >= 4664.69) {
			descontoIR = valorCalculo * 0.275 - 869.36;
		}

		return descontoIR;
	}

	@Override
	public double calcularSalarioLiquido() {
		return salarioLiquido = salarioBruto - descontoInss - descontoIR;
	}

}
