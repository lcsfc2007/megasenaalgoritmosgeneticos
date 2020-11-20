/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package megasenaalgoritmosgeneticos;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.TreeSet;

/**
 *
 * @author User
 */
public class MegaSenaGA {
    private int populationSize;
    private int generations, generation;
    private double mutation_rate;
    private ArrayList<Aposta> population;
    private MegaSenaResults results;
    private int admissionControl;
       
    public MegaSenaGA(MegaSenaResults results,int size, int generations, double mutation_rate, int admissionControl)
    {
        this.populationSize = size;
        this.generations = generations;
        this.mutation_rate = mutation_rate;
        this.population = new ArrayList<Aposta>();
        this.results = results;
        this.admissionControl = admissionControl;
    }
    
    public void init()
    {
        int pop = 0;
        while(pop < this.populationSize)
        {
            Aposta a = Aposta.create(6);
            this.population.add(a);
            pop++;
        }
        System.out.println("População inicial:");
        for(Aposta a: this.population)
        {
            System.out.printf("Aposta: %s\n",a.toString());
        }
        this.avaliatePopulation();
        System.out.println("Top 5:");
        Object[] best5 = this.getBestIndividuals(5);
        for(Object a : best5)
        {
            System.out.printf("Aposta: %s Fitness: %d\n",a.toString(),((Aposta)a).getFitness());
        }
    }
    
    public ArrayList<Aposta> doRound(int individualsToReturn)
    {
        this.selection();
        this.generateChildren();
        this.avaliatePopulation();
        
        Object[] melhores= this.getBestIndividuals(individualsToReturn);
        ArrayList<Aposta> melhoresApostas = new ArrayList<Aposta>();
        for(Object o: melhores)
        {
            melhoresApostas.add((Aposta)o);
        }
        return melhoresApostas;
    }
    
    public void selection()
    {
        int dead = 0;
        SecureRandom random = new SecureRandom();
        
        ListIterator<Aposta> it = this.population.listIterator();
        while(it.hasNext() && dead<this.admissionControl)
        {
            int val = random.nextInt(4);
            Aposta a = it.next();
            if(val > a.getFitness())
            {
                dead++;
                it.remove();
            }
        }
    }
    
  
    public void generateChildren()
    {
        int born = 0;
        
        int x = this.population.size()/2;
        int y = this.population.size()-1;
        
        while(born < this.admissionControl)
        {
            Aposta mother = this.population.get(x);
            Aposta father = this.population.get(y);
            Aposta son = mother.crossover(father);
            if(son != null)
            {
                this.population.add(son);
                born++;
            }
            x++;
            y--;
        }
        this.population.forEach((individual) -> {
            individual.mutate(mutation_rate);
        });
    }
    
    private void avaliatePopulation()
    {
        for(Aposta a: this.population)
        {
            a.setFitness(this.calculateFitness(a));
        }
    }
    
    public Object[] getBestIndividuals(int upTo)
    {
        return this.population.stream().sorted().limit(upTo).toArray();
    }
    
    private int calculateFitness(Aposta aposta)
    {
        int fitness = 0,quadras=0,quinas=0,megas=0;
        for(MegaSenaResult result : results.getResults())
        {
            TreeSet<Integer> intersect = (TreeSet<Integer>) aposta.getBolas().clone();
            intersect.retainAll(result.getNumbers());
            int resultado = intersect.size();
            if(resultado == 4)
                fitness++; 
            if(resultado == 6)
                megas++;
            if(resultado == 5)
                quinas++;
            if(resultado == 4)
                quadras++;
        }
        aposta.setFitnessmegas(megas);
        aposta.setFitnessquadras(quadras);
        aposta.setFitnessquinas(quinas);
        return fitness+quinas*5+megas*10;
    }
}
