/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package megasenaalgoritmosgeneticos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class MegaSenaResults {
    
    private ArrayList<MegaSenaResult> results;
    
    public void init()
    {
        results = new ArrayList<MegaSenaResult>();
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            InputStream in = getClass().getResourceAsStream("mega.txt"); 
           // File arq = new File("mega.txt");
             Scanner input = new Scanner(in);
             while(input.hasNext())
             {
                 MegaSenaResult res = new MegaSenaResult();
                 int number = input.nextInt();
                 Date data = dateFormat.parse(input.next());
                 int bola1 = input.nextInt();
                 int bola2 = input.nextInt();
                 int bola3 = input.nextInt();
                 int bola4 = input.nextInt();
                 int bola5 = input.nextInt();
                 int bola6 = input.nextInt();
                 res.setDate(data);
                 res.addNumbers(bola1,bola2,bola3,bola4,bola5,bola6);
                 results.add(res);
                 System.out.println(res.toString());
             }
             input.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
        }
       
        
    }
    
    public ArrayList<MegaSenaResult> getResults()
    {
        return results;
    }
    
}
