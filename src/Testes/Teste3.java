
package Testes;

import bean.Pessoa;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Teste3 {

   public Time hora;

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
    
    
    
    public static Date retornaHora(){
        Date dataHoraAtual = new Date();
        //String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        //String hora = new SimpleDateFormat("HH:mm").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm").format(dataHoraAtual);
        System.out.println(hora);

        SimpleDateFormat s = new SimpleDateFormat("HH:mm");
        Date h = new Date();
        try {
            h=s.parse(hora);
        } catch (ParseException ex) {
            Logger.getLogger(Teste3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return h;
    }
    
    public static String retornaDiaSemana(String nome){
        Date d = new Date(); 
        Calendar c = new GregorianCalendar(); 
        c.setTime(d); 
        //String nome = ""; 
        int dia = c.get(c.DAY_OF_WEEK);
       
        //pega o dia da semana que estamos
        switch(dia){
            case Calendar.SUNDAY: nome = "Domingo"; 
                break; 
            case Calendar.MONDAY: nome = "Segunda";
                break;
            case Calendar.TUESDAY: nome = "Terça";
                break; 
            case Calendar.WEDNESDAY: nome = "Quarta";
                break; 
            case Calendar.THURSDAY: nome = "Quinta";
                break; 
            case Calendar.FRIDAY: nome = "Sexta";
                break; 
            case Calendar.SATURDAY: nome = "sabado";
                break; 
        }
        return nome;
    }
    
    public int retornaDiaSemanaNUM(String nome){
        Date d = new Date(); 
        Calendar c = new GregorianCalendar(); 
        c.setTime(d); 
        int diaSem = 0; 
        int dia = c.get(c.DAY_OF_WEEK);
       
        //pega o dia da semana que estamos
        switch(dia){
            case Calendar.SUNDAY: nome = "Domingo";
                diaSem=1;
                break; 
            case Calendar.MONDAY: nome = "Segunda";
                diaSem=2;
                break;
            case Calendar.TUESDAY: nome = "Terça";
                diaSem=3;
                break; 
            case Calendar.WEDNESDAY: nome = "Quarta";
                diaSem=4;
                break; 
            case Calendar.THURSDAY: nome = "Quinta";
                diaSem=5;
                break; 
            case Calendar.FRIDAY: nome = "Sexta";
                diaSem=6;
                break; 
            case Calendar.SATURDAY: nome = "sabado";
                diaSem=7;
                break; 
        }
        return diaSem;
    }

    
    public static int retornaDia(){
        Date d = new Date(); 
        Calendar c = new GregorianCalendar(); 
        c.setTime(d); 
        int dia = c.get(c.DAY_OF_WEEK);
        //pega o dia da semana que estamos
        switch(dia){
            case Calendar.SUNDAY: dia = 1;
                break; 
            case Calendar.MONDAY: dia = 2;
                break;
            case Calendar.TUESDAY: dia = 3;
                break; 
            case Calendar.WEDNESDAY: dia = 4;
                break; 
            case Calendar.THURSDAY: dia = 5;
                break; 
            case Calendar.FRIDAY: dia = 6;
                break; 
            case Calendar.SATURDAY: dia = 7;
                break; 
        }
        return(dia);
    }
    
    public boolean verificaDataHora(Pessoa p){
        Date d = new Date(); 
        Calendar c = new GregorianCalendar(); 
        c.setTime(d); 
        String nome = "";
        
        Time HI, HF;
        int DI, DF;
        DI=retornaDiaSemanaNUM(p.getControle().getDia_inicio());
        DF=retornaDiaSemanaNUM(p.getControle().getDia_fim());
        HI=p.getControle().getHora_inicio();
        HF=p.getControle().getHora_fim();
        
        long now = System.currentTimeMillis();
        Time horaAgora = new Time(now);
        
        int diaHoje=retornaDia();
        if(diaHoje>=DI && diaHoje<=DF){
            if(horaAgora.after(HI) && horaAgora.before(HF)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    
    public static void main(String[] args) {
       /* 
       retornaHora();
        String str;
        str = retornaDiaSemana("Domingo");
        */
       /*
        String h="18:00:00";
        //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Time tt;
        tt=Time.valueOf(h);
        System.out.println("Time: "+tt);
        String ho = String.valueOf(tt);
        
        Date data;
        DateFormat f = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat s = new SimpleDateFormat("HH:mm:SS");
        try {
            data = f.parse(ho);
            System.out.println("Date: "+data);
            data = s.parse(ho);
            System.out.println("Date: "+data);

        } catch (ParseException ex) {
            Logger.getLogger(Teste3.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
       /*
        Time hh;
        String hor="19:00";
	DateFormat formato = new SimpleDateFormat("HH:mm:ss");
	
       try {
           hh = new Time(formato.parse(hor).getTime());
           System.out.println("teste: "+hh);
       } catch (ParseException ex) {
           Logger.getLogger(Teste3.class.getName()).log(Level.SEVERE, null, ex);
       }
	*/
        DateFormat f = new SimpleDateFormat("HHmm");
        SimpleDateFormat s = new SimpleDateFormat("HHmm");
       
        long now = System.currentTimeMillis();
        Time sqlTime = new Time(now);
        System.out.println("currentTimeMillis: " + now);
        System.out.println("SqlTime          : " + sqlTime);

        System.out.println("eitaaaa agora vai:   "+sqlTime.toString().replace(":", ""));
        int fi=Integer.parseInt(sqlTime.toString().replaceAll(":", ""));
        System.out.println("inteiro: "+fi);
    
       
       int i=retornaDia();
        System.out.println(i);
       
       retornaHora();
       
       
       
        
    }

    
}
