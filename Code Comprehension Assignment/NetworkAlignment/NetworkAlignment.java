import graph.Graph;
import graph.UndirectedGraph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import memeticOperators.Individual;

enum IndividualFitnessComparator implements Comparator<Individual<List<Integer>, Integer>> {

	INSTANCE;

	@Override
	public int compare(Individual<List<Integer>, Integer> arg0, Individual<List<Integer>, Integer> arg1) {

		return Integer.compare(arg1.getFitness(), arg0.getFitness());

	}
}

public class NetworkAlignment {

	public static void main(String[] args) {

		System.out.println("Launching network alignment memetic algorithm.");

		long startTime = System.currentTimeMillis();

		System.out.println("Initialising and loading data.");

		NetworkAlignment na = new NetworkAlignment(args[0], args[1]);
		long initialisationTime = System.currentTimeMillis();

		System.out.println("Beginning memetic algorithm.");

		na.runMemeticAlgorithm(1000, 100, 100);
		long endTime = System.currentTimeMillis();

		System.out.println("Time to initialise: " + (initialisationTime - startTime));
		System.out.println("Time in memetic algorithm: " + (endTime - initialisationTime));
		System.out.println("Total time: " + (endTime - startTime));

	}

	private List<Individual<List<Integer>, Integer>> population;
	private Graph graphOne;
	private Graph graphTwo;

	private final List<String> graphOneVertices;
	private final List<String> graphTwoVertices;

	private final Map<String, Integer> graphOneVertexIndices;
	private final Map<String, Integer> graphTwoVertexIndices;

	private Map<String, Set<String>> graphOneAdjacencies;
	private Map<String, Set<String>> graphTwoAdjacencies;

	private List<Set<Integer>> graphOneIndexAdjacencies;
	private List<Set<Integer>> graphTwoIndexAdjacencies;

	private final Random random;

	private final int EDGEVALUE = 1;
	private final int VERTEXVALUE = 0;

	private final int upperbound;

	private final int individualSize;

	public NetworkAlignment(String graphOneFilename, String graphTwoFilename) {

		this.random = new Random();

		this.graphOne = new UndirectedGraph();
		this.graphTwo = new UndirectedGraph();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File(graphOneFilename)));

			String graphString = "";

			while (reader.ready()) {
				graphString += reader.readLine();
			}

			this.graphOne = this.graphOne.edgeListToGraph(graphString);

			reader.close();

			reader = new BufferedReader(new FileReader(new File(graphTwoFilename)));

			graphString = "";

			while (reader.ready()) {
				graphString += reader.readLine();
			}

			this.graphTwo = this.graphTwo.edgeListToGraph(graphString);

			reader.close();

		} catch (FileNotFoundException e) {

			System.err.println("File not found: " + e.getMessage());

		} catch (IOException e) {

			System.err.println("Error reading file: " + e.getMessage());

		}

		int i = 0;

		while (this.graphOne.numVertices() < this.graphTwo.numVertices()) {

			this.graphOne.addVertex("dummyVertex" + i++);

		}

		i = 0;

		while (this.graphTwo.numVertices() < this.graphOne.numVertices()) {

			this.graphTwo.addVertex("dummyVertex" + i++);

		}

		this.graphOneVertices = this.graphOne.vertices();
		this.graphTwoVertices = this.graphTwo.vertices();

		this.graphOneVertexIndices = new HashMap<String, Integer>();
		this.graphTwoVertexIndices = new HashMap<String, Integer>();

		for (int j = 0; j < graphOneVertices.size(); j++) {
			this.graphOneVertexIndices.put(this.graphOneVertices.get(j), j);
			this.graphTwoVertexIndices.put(this.graphTwoVertices.get(j), j);
		}

		this.graphOneAdjacencies = new HashMap<String, Set<String>>();
		this.graphTwoAdjacencies = new HashMap<String, Set<String>>();

		this.graphOneIndexAdjacencies = new ArrayList<Set<Integer>>();
		this.graphTwoIndexAdjacencies = new ArrayList<Set<Integer>>();

		for (String v : this.graphOneVertices) {

			Set<String> vNeighbours = new HashSet<String>();
			vNeighbours.addAll(this.graphOne.neighbours(v));
			this.graphOneAdjacencies.put(v, vNeighbours);

			Set<Integer> vIndexNeighbours = new HashSet<Integer>();
			for (String u : vNeighbours) {
				vIndexNeighbours.add(this.graphOneVertexIndices.get(u));
			}
			this.graphOneIndexAdjacencies.add(vIndexNeighbours);

		}

		for (String v : this.graphTwoVertices) {

			Set<String> vNeighbours = new HashSet<String>();
			vNeighbours.addAll(this.graphTwo.neighbours(v));
			this.graphTwoAdjacencies.put(v, vNeighbours);

			Set<Integer> vIndexNeighbours = new HashSet<Integer>();
			for (String u : vNeighbours) {
				vIndexNeighbours.add(this.graphTwoVertexIndices.get(u));
			}
			this.graphTwoIndexAdjacencies.add(vIndexNeighbours);

		}

		this.upperbound = this.graphOne.numVertices() * this.VERTEXVALUE
				+ Math.min(this.graphOne.numEdges(), this.graphTwo.numEdges()) * this.EDGEVALUE;

		this.individualSize = this.graphOneVertices.size();
		
		System.out.println("Graph 1: " + graphOne);
		System.out.println("Graph 2: " + graphTwo);

		System.out.println("Upper bound: " + this.upperbound);

	}

	private List<Integer> alignNeighbourhoods(int i, int j, List<Integer> individual) {

		List<Integer> chromosome = new ArrayList<Integer>(individual);

		List<Integer> neighboursOfI = new ArrayList<Integer>();

		for (String v : graphOne.neighbours(graphOneVertices.get(i))) {

			neighboursOfI.add(graphOneVertices.indexOf(v));

		}

		List<Integer> indexOfNeighboursOfThingAtJ = new ArrayList<Integer>();

		for (String v : graphTwo.neighbours(graphTwoVertices.get(chromosome.get(j)))) {

			indexOfNeighboursOfThingAtJ.add(chromosome.indexOf(graphTwoVertices.indexOf(v)));

		}

		neighboursOfI.remove(j);
		indexOfNeighboursOfThingAtJ.remove(i);

		int temp = chromosome.get(i);
		chromosome.set(i, chromosome.get(j));
		chromosome.set(j, temp);

		List<Integer> overlap = new ArrayList<Integer>();

		for (Integer neighbour : neighboursOfI) {
			if (indexOfNeighboursOfThingAtJ.contains(neighbour)) {
				overlap.add(neighbour);
			}
		}

		neighboursOfI.removeAll(overlap);
		indexOfNeighboursOfThingAtJ.removeAll(overlap);

		neighboursOfI = neighboursOfI.subList(0, Math.min(neighboursOfI.size(), indexOfNeighboursOfThingAtJ.size()));
		indexOfNeighboursOfThingAtJ = indexOfNeighboursOfThingAtJ.subList(0,
				Math.min(neighboursOfI.size(), indexOfNeighboursOfThingAtJ.size()));

		for (int k = 0; k < neighboursOfI.size(); k++) {
			temp = chromosome.get(indexOfNeighboursOfThingAtJ.get(k));
			chromosome.set(indexOfNeighboursOfThingAtJ.get(k), chromosome.get(neighboursOfI.get(k)));
			chromosome.set(neighboursOfI.get(k), temp);
		}

		return chromosome;
	}

	private Individual<List<Integer>, Integer> copyIndividual(Individual<List<Integer>, Integer> individual) {

		List<Integer> newList = new ArrayList<Integer>();
		newList.addAll(individual.getIndividual());
		return new Individual<List<Integer>, Integer>(newList, individual.getFitness());

	}

	private Individual<List<Integer>, Integer> crossover(Individual<List<Integer>, Integer> parent1,
			Individual<List<Integer>, Integer> parent2) {

		List<Integer> childList = new ArrayList<Integer>(parent1.getIndividual().size());

		Set<Integer> unusedEntries = new HashSet<Integer>();
		Set<Integer> unusedIndices = new HashSet<Integer>();

		for (int i = 0; i < parent1.getIndividual().size(); i++) {

			if (parent1.getIndividual().get(i) == parent2.getIndividual().get(i)) {

				childList.add(parent1.getIndividual().get(i));

			} else {

				childList.add(-1);
				unusedIndices.add(i);
				unusedEntries.add(parent1.getIndividual().get(i));
				unusedEntries.add(parent2.getIndividual().get(i));

			}

		}

		Integer[] unusedEntriesArray = unusedEntries.toArray(new Integer[unusedEntries.size()]);
		Integer[] unusedIndicesArray = unusedIndices.toArray(new Integer[unusedIndices.size()]);

		for (int i = 0; i < unusedIndicesArray.length; i++) {

			childList.set(unusedIndicesArray[i], unusedEntriesArray[i]);

		}

		return new Individual<List<Integer>, Integer>(childList, this.fitness(childList));
	}

	private List<Individual<List<Integer>, Integer>> cxNeighbourhood(Individual<List<Integer>, Integer> parent1,
			Individual<List<Integer>, Integer> parent2) {

		Set<Integer> cxPoints = new HashSet<Integer>();
		int initialIndex = this.random.nextInt(this.individualSize);
		cxPoints.add(initialIndex);

		List<Integer> p1 = new ArrayList<Integer>(this.individualSize);
		List<Integer> p2 = new ArrayList<Integer>(this.individualSize);

		for (int i = 0; i < this.individualSize; i++) {
			p1.add(0);
			p2.add(0);
		}

		for (int i = 0; i < this.individualSize; i++) {
			p1.set(parent1.getIndividual().get(i), i);
			p2.set(parent2.getIndividual().get(i), i);
		}

		for (String v : this.graphOneAdjacencies.get(this.graphOneVertices.get(initialIndex))) {

			cxPoints.add(this.graphOneVertexIndices.get(v));

		}

		List<Integer> twoNeighbourhood = new ArrayList<Integer>();

		for (int i : cxPoints) {

			for (String u : this.graphOneAdjacencies.get(this.graphOneVertices.get(i))) {

				twoNeighbourhood.add(this.graphOneVertexIndices.get(u));

			}

		}

		cxPoints.addAll(twoNeighbourhood);

		List<Integer> childOneList = new ArrayList<Integer>(parent1.getIndividual());
		List<Integer> childTwoList = new ArrayList<Integer>(parent2.getIndividual());

		for (int i : cxPoints) {

			int tempOne = childOneList.get(i);
			int tempTwo = childTwoList.get(i);

			childOneList.set(i, tempTwo);
			childOneList.set(p1.get(tempTwo), tempOne);

			childTwoList.set(i, tempOne);
			childTwoList.set(p2.get(tempOne), tempTwo);

			int swap = p1.get(tempOne);
			p1.set(tempOne, p1.get(tempTwo));
			p1.set(tempTwo, swap);

			swap = p2.get(tempOne);
			p2.set(tempOne, p2.get(tempTwo));
			p2.set(tempTwo, swap);

		}

		Individual<List<Integer>, Integer> childOne = new Individual<List<Integer>, Integer>(childOneList,
				this.fitness(childOneList));
		Individual<List<Integer>, Integer> childTwo = new Individual<List<Integer>, Integer>(childTwoList,
				this.fitness(childTwoList));

		List<Individual<List<Integer>, Integer>> children = new ArrayList<Individual<List<Integer>, Integer>>(2);

		children.add(childOne);
		children.add(childTwo);

		return children;
	}

	private int fitness(List<Integer> individual) {

		int fitness = 0;

		for (int i = 0; i < this.individualSize; i++) {

			Set<Integer> graphOneNeighbours = this.graphOneIndexAdjacencies.get(i);
			Set<Integer> graphTwoNeighbours = this.graphTwoIndexAdjacencies.get(individual.get(i));

			for (int j : graphOneNeighbours) {

				if (graphTwoNeighbours.contains(individual.get(j))) {
					fitness += this.EDGEVALUE;
				}

			}

		}

		fitness /= 2;

		for (int i = 0; i < this.individualSize; i++) {

			if (this.graphOneVertices.get(i).equals(this.graphTwoVertices.get(individual.get(i)))) {

				fitness += this.VERTEXVALUE;

			}

		}

		return fitness;
	}

	private void improve(Individual<List<Integer>, Integer> individual) {

		List<Integer> availableIndices = new ArrayList<Integer>(this.graphOneVertexIndices.values());

		Collections.shuffle(availableIndices, random);

		while (!availableIndices.isEmpty()) {

			int firstIndex = availableIndices.remove(0);
			int bestSwapIndex = firstIndex;
			int bestGap = 0;

			for (int j = 0; j < availableIndices.size(); j++) {

				int secondIndex = availableIndices.get(j);

				int before = 0;

				for (int neighbour : this.graphOneIndexAdjacencies.get(firstIndex)) {

					int mappedNeighbour = individual.getIndividual().get(neighbour);

					if (this.graphTwoIndexAdjacencies.get(individual.getIndividual().get(firstIndex))
							.contains(mappedNeighbour)) {
						before++;
					}

				}

				for (int neighbour : this.graphOneIndexAdjacencies.get(secondIndex)) {

					int mappedNeighbour = individual.getIndividual().get(neighbour);

					if (this.graphTwoIndexAdjacencies.get(individual.getIndividual().get(secondIndex))
							.contains(mappedNeighbour)) {
						before++;
					}

				}

				int after = 0;

				for (int neighbour : this.graphOneIndexAdjacencies.get(secondIndex)) {

					int mappedNeighbour = individual.getIndividual().get(neighbour);

					if (this.graphTwoIndexAdjacencies.get(individual.getIndividual().get(firstIndex))
							.contains(mappedNeighbour)) {
						before++;
					}

				}

				for (int neighbour : this.graphOneIndexAdjacencies.get(firstIndex)) {

					int mappedNeighbour = individual.getIndividual().get(neighbour);

					if (this.graphTwoIndexAdjacencies.get(individual.getIndividual().get(secondIndex))
							.contains(mappedNeighbour)) {
						before++;
					}

				}

				int gap = after - before;

				if (gap > bestGap) {
					bestGap = gap;
					bestSwapIndex = secondIndex;
				}

			}

			if (bestSwapIndex != firstIndex) {

				List<Integer> newList = new ArrayList<Integer>(individual.getIndividual());
				int temp = newList.get(firstIndex);
				newList.set(firstIndex, newList.get(bestSwapIndex));
				newList.set(bestSwapIndex, temp);
				individual.setIndividualAndFitness(newList, this.fitness(newList));
				availableIndices.remove(bestSwapIndex);

			}

		}

	}

	private void initialisePopulation(int populationSize) {

		this.population = new ArrayList<Individual<List<Integer>, Integer>>();

		for (int i = 0; i < populationSize; i++) {

			List<Integer> individual = new ArrayList<Integer>(graphOne.numVertices());

			for (int j = 0; j < graphOne.numVertices(); j++) {

				individual.add(j);

			}

			for (int k = 0; k < individual.size(); k++) {

				int indexToSwap = this.random.nextInt(individual.size() - k) + k;
				int temp = individual.get(k);
				individual.set(k, individual.get(indexToSwap));
				individual.set(indexToSwap, temp);

			}

			this.population.add(new Individual<List<Integer>, Integer>(individual, this.fitness(individual)));
		}

	}

	private void mutate(Individual<List<Integer>, Integer> individual) {

		int i = this.random.nextInt(individual.getIndividual().size());

		int j = this.random.nextInt(individual.getIndividual().size() - 1);

		if (j >= i)
			j++;

		List<Integer> chromosome = this.alignNeighbourhoods(i, j, individual.getIndividual());

		individual.setIndividualAndFitness(chromosome, this.fitness(chromosome));

	}

	private void shuffleMutate(Individual<List<Integer>, Integer> individual, float swapProb) {

		List<Integer> newList = new ArrayList<Integer>(individual.getIndividual());

		for (int i = 0; i < this.individualSize; i++) {

			if (this.random.nextFloat() < swapProb) {

				int swapIndex = this.random.nextInt(this.individualSize - 1);
				if (swapIndex >= i) {
					swapIndex++;
				}

				int temp = newList.get(i);
				newList.set(i, newList.get(swapIndex));
				newList.set(swapIndex, temp);

			}

		}

		individual.setIndividualAndFitness(newList, this.fitness(newList));

	}

	public void runMemeticAlgorithm(int numRounds, int populationSize, int improvementFrequency) {

		this.initialisePopulation(populationSize);

		for (Individual<List<Integer>, Integer> individual : this.population) {
			this.improve(individual);
		}

		Collections.sort(this.population, IndividualFitnessComparator.INSTANCE);

		int currentRound = 1;

		Individual<List<Integer>, Integer> best = this.population.get(0);
		Individual<List<Integer>, Integer> prevGenerationBest = best;

		int roundsSinceImprovement = 0;
		int numRestarts = 0;
		int improvementRounds = 0;

		while (currentRound < numRounds) {

			List<Individual<List<Integer>, Integer>> children = new ArrayList<Individual<List<Integer>, Integer>>();

			for (int i = 0; i < this.population.size() / 2; i++) {

				Individual<List<Integer>, Integer> child = this.crossover(this.population.get(i),
						this.population.get(this.random.nextInt(this.population.size())));

				children.add(child);

				List<Individual<List<Integer>, Integer>> newChildren = this.cxNeighbourhood(this.population.get(i),
						this.population.get(this.random.nextInt(this.population.size())));
				children.addAll(newChildren);

			}

			this.population.addAll(children);

			for (Individual<List<Integer>, Integer> individual : this.population) {

				if (this.random.nextFloat() < 0.3) {

					this.shuffleMutate(individual, 0.05f);

				}

			}

			if (currentRound % improvementFrequency == 0) {

				if (improvementRounds != 0) {

					for (Individual<List<Integer>, Integer> individual : this.population) {

						this.improve(individual);

					}

				} else {

					for (int i = 0; i < populationSize / 10; i++) {
						//this.strongImprove(this.population.get(i));
					}

				}

				improvementRounds = (improvementRounds + 1) % 1;

			}

			Collections.sort(this.population, IndividualFitnessComparator.INSTANCE);

			this.population = this.population.subList(0, populationSize);

			prevGenerationBest = this.copyIndividual(best);

			best = this.copyIndividual(this.population.get(0));

			if (best.getFitness() <= prevGenerationBest.getFitness()) {

				roundsSinceImprovement++;
				best = prevGenerationBest;

			} else {

				roundsSinceImprovement = 0;
			}

			if (best.getFitness() >= this.upperbound) {

				System.out.println("Found optimal in round " + currentRound + ".");
				break;
			}

			if (roundsSinceImprovement > 50 * (numRestarts + 1) && numRestarts < 5) {

				System.out.println("Restarting population.");
				this.initialisePopulation(populationSize);
//				currentRound = 0;
				numRestarts++;
				roundsSinceImprovement = 0;

			}

			System.out.println("Best fitness in generation " + currentRound + ": " + best.getFitness());

			currentRound++;
		}

		System.out.println("Best individual found: " + best.toString());
		System.out.println("Fitness value: " + best.getFitness());

	}

	private void strongImprove(Individual<List<Integer>, Integer> individual) {

		boolean improving = true;

		while (improving) {
			
			improving = false;
			
			List<Integer> bestSwap = individual.getIndividual();
			int bestSwapFitness = individual.getFitness();

			List<Integer> availableIndices = new ArrayList<Integer>(this.graphOneVertexIndices.values());

			Collections.shuffle(availableIndices, random);

			while (!availableIndices.isEmpty()) {

				int firstIndex = availableIndices.remove(0);
				int bestSwapIndex = firstIndex;

				for (int secondIndex : availableIndices) {

					List<Integer> newChromosome = this.alignNeighbourhoods(firstIndex, secondIndex,
							individual.getIndividual());
					int newFitness = this.fitness(newChromosome);

					if (newFitness > bestSwapFitness) {

						bestSwap = newChromosome;
						bestSwapFitness = newFitness;
						bestSwapIndex = secondIndex;

					}

				}

				if (bestSwapIndex != firstIndex) {

					improving = true;
					individual.setIndividualAndFitness(bestSwap, bestSwapFitness);
					availableIndices.remove(bestSwapIndex);

				}

			}

		}

	}

}
