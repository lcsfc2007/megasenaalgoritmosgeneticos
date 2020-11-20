/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package megasenaalgoritmosgeneticos;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

/**
 *
 * @author User
 */
public class Aposta implements Comparable<Aposta>{
    private TreeSet<Integer> numeros;

    private int fitness = 0;
    
    private int fitnessquadras,fitnessquinas,fitnessmegas;

    public int getFitnessquadras() {
        return fitnessquadras;
    }

    public void setFitnessquadras(int fitnessquadras) {
        this.fitnessquadras = fitnessquadras;
    }

    public int getFitnessquinas() {
        return fitnessquinas;
    }

    public void setFitnessquinas(int fitnessquinas) {
        this.fitnessquinas = fitnessquinas;
    }

    public int getFitnessmegas() {
        return fitnessmegas;
    }

    public void setFitnessmegas(int fitnessmegas) {
        this.fitnessmegas = fitnessmegas;
    }
    
    public Aposta()
    {
        this.numeros = new TreeSet<Integer>();
    }
            
    
    public static Aposta create(int size)
    {
        SecureRandom random = new SecureRandom();
        Aposta aposta = new Aposta();
        int bolas = 0;
        while(bolas < size)
        {
           int bola = 1+random.nextInt(60);
           if(aposta.getBolas().contains(bola) == false)
           {
                aposta.addNumber(bola);
                bolas++;
           }
        }
        return aposta;
    }
    public TreeSet<Integer> getBolas()
    {
        return this.numeros;
    }
    public void addNumber(int number)
    {
        this.numeros.add(number);
    }
    
    public void mutate(double rate)
    {
        SecureRandom random = new SecureRandom();
        double chance = random.nextDouble();
        if(chance <= rate)
        {
            int pos = random.nextInt(numeros.size());
            Object[] array = numeros.toArray();
            numeros.remove((Integer)(array[pos]));
            int novaBola = numeros.first();
            while(numeros.contains(novaBola))
            {
                novaBola = 1+random.nextInt(60);
            }
            numeros.add(novaBola);
            
        }
    }
    public Aposta crossover(Aposta other)
    {
        Aposta aposta = new Aposta();
        SecureRandom random = new SecureRandom();
        
        int originPos = random.nextInt(this.getBolas().size());
        int otherPos = random.nextInt(other.getBolas().size());
        
        Object[] originArray = this.getBolas().toArray();
        Object[] otherArray = other.getBolas().toArray();
        
        for(int i = 0; i < originPos; i++)
        {
            aposta.addNumber((int)originArray[i]);
        }
        for(int i = otherPos ; i < otherArray.length && aposta.getBolas().size() <8 ; i++)
        {
            aposta.addNumber((int)otherArray[i]);
        }
        if(aposta.getBolas().size()<6)
            return null;
        return aposta;
    }
    
        
    @Override
    public String toString()
    {
        return this.numeros.toString();
    }
    
    public void setFitness(int fitness)
    {
        this.fitness = fitness;
    }
    
    public int getFitness()
    {
        return this.fitness;
    }

    

    @Override
    public int compareTo(Aposta o) {
        if(this.fitness > o.fitness)
            return -1;
        if(this.fitness < o.fitness)
            return 1;
        return 0;
    }
}
