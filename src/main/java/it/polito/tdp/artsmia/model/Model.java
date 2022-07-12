package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {

	ArtsmiaDAO dao ;
	Graph<Artisti,DefaultWeightedEdge> grafo;
	Map<Integer,Artisti> idMap;

	public Model() {
		
		dao = new ArtsmiaDAO();
		idMap = new HashMap<>();
	}
	
	public List<String> getRuolo() {
		return this.dao.getRuolo();
	}
	
	public void creaGrafo(String s)
	{
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(s,idMap));
		
		for(Adiacenze a : this.dao.getArchi(s, idMap))
		{
			Graphs.addEdgeWithVertices(this.grafo, a.getA1(), a.getA2(), a.getPeso());
		}
	}
	
	
	public int getNVertici()
	{
		return this.grafo.vertexSet().size();
	}
	public int getNArchi()
	{
		return this.grafo.edgeSet().size();
	}
	
	public List<Connessi> getConnessi(String s) {
		return this.dao.getConnessi(s);
	}
	
	public List<Adiacenze> getCollegati(String s)
	{
		List<Adiacenze> result = new ArrayList<Adiacenze>();
		for(Connessi c : this.getConnessi(s))
		{
			Adiacenze a = new Adiacenze(idMap.get(c.getA1()),idMap.get(c.getA2()),c.getPeso());
			result.add(a);
		}
		Collections.sort(result);
		return result;
	}
	public boolean grafoCreato()
	{
		if(this.grafo==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
