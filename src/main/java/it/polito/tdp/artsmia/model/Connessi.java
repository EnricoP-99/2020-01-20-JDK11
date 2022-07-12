package it.polito.tdp.artsmia.model;

import java.util.Objects;

public class Connessi {

	private int a1;
	private int a2;
	private int peso;
	public Connessi(int a1, int a2, int peso) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.peso = peso;
	}
	public int getA1() {
		return a1;
	}
	public void setA1(int a1) {
		this.a1 = a1;
	}
	public int getA2() {
		return a2;
	}
	public void setA2(int a2) {
		this.a2 = a2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		return Objects.hash(a1, a2, peso);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connessi other = (Connessi) obj;
		return a1 == other.a1 && a2 == other.a2 && peso == other.peso;
	}
	@Override
	public String toString() {
		return "Connessi [a1=" + a1 + ", a2=" + a2 + ", peso=" + peso + "]";
	}
	
	
}
