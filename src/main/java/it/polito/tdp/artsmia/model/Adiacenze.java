package it.polito.tdp.artsmia.model;

import java.util.Objects;

public class Adiacenze implements Comparable<Adiacenze> {

	private Artisti a1;
	private Artisti a2;
	private int peso;
	public Adiacenze(Artisti a1, Artisti a2, int peso) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.peso = peso;
	}
	public Artisti getA1() {
		return a1;
	}
	public void setA1(Artisti a1) {
		this.a1 = a1;
	}
	public Artisti getA2() {
		return a2;
	}
	public void setA2(Artisti a2) {
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
		Adiacenze other = (Adiacenze) obj;
		return Objects.equals(a1, other.a1) && Objects.equals(a2, other.a2) && peso == other.peso;
	}
	@Override
	public String toString() {
		return   a1 + "  -  " + a2 + "  -  " + peso + "\n";
	}
	@Override
	public int compareTo(Adiacenze o) {
		return this.peso-o.peso;
	}
	
	
}
