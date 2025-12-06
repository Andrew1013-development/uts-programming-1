package memeticOperators;

/*
    Judging by the package name "memeticOperators", combined with the extensive usage of this class in NetworkAlignment.java, it's reasonable to conclude that:
    This class represents an "organism" / Individual (comprised of chromosome and fitness score) (see more in Section 1.2 of NetworkAlignment.java)
    More specifically:
    - T (representation): likely a collection of some sorts, maybe a List or an alternative representation method
    - N (fitness): likely a numeric value
    Both of these guesses are supported by the generic type N explicitly stating that they extend the default Numbers class.
    Moreover, taking a look at NetworkAlignment.java, this class is used with the following type declaration Individual<List<Integer>, Integer>
*/

public class Individual <T, N extends Number> {
	/*
        The "private" access modifier is used to denote class attributes that can only be accessed within their class's declaration.
        This modifier is used on class attributes is used to limit / forbids direct modifications (e.g. individual.fitness = 3), instead using class methods.
    */
	private T representation;
	private N fitness;

    // class constructor -> assigns the class fields to their appropriate function parameters
	public Individual(T representation, N fitness) {
		this.representation = representation;
		this.fitness = fitness;
	}

    // individual getter -> return the current representation
	public T getIndividual(){
		return this.representation;
	}

    // individual setter -> set the current representation to t
	public void setIndividual(T t){
		this.representation = t;
	}

    // fitness getter -> return the current fitness value
	public N getFitness(){
		return this.fitness;
	}

    // fitness setter -> set the fitness value to n
	public void setFitness(N n){
		this.fitness = n;
	}

    // since toString() is a default method for all classes in Java, we need to override the existing function to print with our custom formatting.
    @Override
	public String toString(){
		
		return "<" + this.representation.toString() + ", " + this.fitness.toString() + ">";
        // Example output (assuming T = List<Integer>, N = Integer): <[1, 2, 3, 4, 5], 67>
        // Meaning: representation = [1,2,3,4,5] | fitness = 67
	}

    // composite function for setting both the individual representation and the fitness value in one go
	public void setIndividualAndFitness(T t, N n) {
		this.setIndividual(t);
		this.setFitness(n);
	}

}
