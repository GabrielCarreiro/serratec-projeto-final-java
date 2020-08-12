package br.com.serratec.trabalhofinal;

import java.time.LocalDate;

public class Dependente extends Pessoa {
	private GrauParentesco grauParentesco;
	private int idade;
	

	public Dependente(String nome, String cpf, LocalDate dataNascimento, GrauParentesco grauParentesco) {
		super(nome, cpf, dataNascimento);
		this.grauParentesco = grauParentesco;
	}


	public int getIdade() {
		return idade;
	}


	public void setIdade(int idade) {
		this.idade = idade;
	}


	@Override
	public String toString() {
		return "Dependente" + " nome: " + nome + " grauParentesco= " + grauParentesco;
	}



}
