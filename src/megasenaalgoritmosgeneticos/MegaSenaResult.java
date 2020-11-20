/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package megasenaalgoritmosgeneticos;

import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

/**
 *
 * @author User
 */
public class MegaSenaResult {
    private Date data;
    private TreeSet numeros;
    
    public MegaSenaResult()
    {
        this.numeros = new TreeSet<Integer>();
    }
    
    public void addNumber(int number)
    {
        this.numeros.add(number);
    }
    public void addNumbers(int ...numbers)
    {
        for(int number : numbers)
        {
            this.numeros.add(number);
        }
    }
    public void setDate(Date c)
    {
        this.data = c;
    }
    public TreeSet<Integer> getNumbers()
    {
        return numeros;
    }
    @Override
    public String toString()
    {
        return this.numeros.toString();
    }
}
