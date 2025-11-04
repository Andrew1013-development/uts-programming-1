package memeticOperators;

public class Individual <T, N extends Number> {
	
	private T representation;
	private N fitness;
	
	public Individual(T representation, N fitness) {
		this.representation = representation;
		this.fitness = fitness;
	}

	public T getIndividual(){
		return this.representation;
	}
	
	public void setIndividual(T t){
		this.representation = t;
	}
	
	public N getFitness(){
		return this.fitness;
	}
	
	public void setFitness(N n){
		this.fitness = n;
	}
	
	@Override
	public String toString(){
		
		return "<" + this.representation.toString() + ", " + this.fitness.toString() + ">";
		
	}
	
	public void setIndividualAndFitness(T t, N n){
		
		this.setIndividual(t);
		this.setFitness(n);
		
	}

}
