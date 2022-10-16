/**
 * Clase que gestiona los gastos de campanya de
 * una caldera de comunitaria sin contadores.
 * Permite analizar gastos y hacer una regularizacion
 * informando cuanto se tiene que devolver o
 * cuanto mas tiene que pagar cada vecino.
 * 
 * @author Crismeiry Calcaño
 * @version 1.0
 */
public class Caldera {
    
    // TODO: CONSTANTES
   

    /** El IVA se aplica a todos los gasto. */
    
    /** El impuesto de hidrocarburos solo se aplica al gas, ademas del iva. */
    private final double IMP_IVA = 0.22;
    private final double IMP_HIDROCARBUROS = 0.20;

    // Conceptos gastos
     private final char AGUA = 'A';
     private final char LUZ = 'L';
     private final char NADA = 'N';

    // Ningun mes
    private final int NINGUNO = 0;
    

    // Periodos
    private final int PERIODO_OCTUBRE_DICIEMBRE = 1;
    private final int PERIODO_ENERO_MARZO = 2;
    private final int PERIODO_ABRIL_JUNIO = 3;
    private final int PERIODO_JULIO_SEPTIEMBRE = 4;
    
    // TODO: VARIABLES DE CLASE (PROPIEDADES/ATRIBUTOS)
    private int mesMasGasto;
    private char conceptoMasGasto;
    private int periodoMasMantenimiento;
    // vecinos y presupuesto
    private int vecinos;
    private double presupuesto;

    // acumulados
    private double acumuladoConsumo;
    private double acumuladoMantenimiento;
    private double gastoAgua;
    private double gastoLuz;
    // estadisticas
    private double maxConsumo;
    private double maxPrecio;
    private double minPrecio;
    private double maxMantenimiento;
    private double maxGasto;
    private int mesMasConsumo;
    private int mesMasBarato;
    private int mesMasCaro;
    
    // TODO: constructores
    
    /**
     * Constructor de la clase Caldera. Inicializa los atributos.
     */
     public Caldera(){
        vecinos = 0;
        presupuesto = 0;
        acumuladoMantenimiento = 0;
        acumuladoConsumo = 0;
        gastoAgua = 0;
        gastoLuz = 0;
        mesMasConsumo = NINGUNO;
        maxConsumo = 0;
        mesMasCaro = NINGUNO;
        maxPrecio = 0;
        mesMasBarato = NINGUNO;
        minPrecio = 0;
        periodoMasMantenimiento = NINGUNO;
        maxMantenimiento = 0;
        mesMasGasto = NINGUNO;
        maxGasto = 0;
        conceptoMasGasto = NADA;

        }

    /**
     * Constructor de la clase Caldera. Inicializa los atributos.
     * 
     * @param queVecinos     Numero de vecinos que conforman la comunidad
     * @param quePresupuesto Presupuesto inicial con el que se pretende afrontar los
     *                       gastos
     */
     public Caldera(int cuantosVecinos,double quePresupuesto){
        vecinos = cuantosVecinos ;
        presupuesto = quePresupuesto;
        }
    // TODO: getters y setters
    
    /**
     * Fija el valor del presupuesto
     * 
     * @param quePresupuesto Valor del presupuesto, ej. 38638
     */
     public void setPresupuesto( double Presupuesto){
        presupuesto = Presupuesto;
        }

    /**
     * Obtiene el valor del presupuesto
     * 
     * @return valor del presupuesto, ej. 38638
     */
    
     public double getPresupuesto(){       
        return presupuesto;
        }
        
    /**
     * Fija el numero de vecinos de la comunidad
     * 
     * @param queVecinos numero de vecinos, ej. 48
     */
     public void setVecinos(int cuantovecinos){
        vecinos = cuantovecinos;      
        }

    /**
     * Obtiene el numero de vecinos
     * 
     * @return numero de vecinos, ej. 48
     */
     public int getVecinos(){
         return vecinos;
        }

    /**
     * Cantidad de gas consumido cada mes al precio de mercado
     * 
     * @param mes    Numero de mes, 1 es enero, 2 febrero, ..., 12 diciembre, ej. 9
     * @param gas    Cantidad de gas consumido en KWh, ej. 15496
     * @param precio Precio en Euros al que se ha conseguido el gas, ej. 0.067668
     */
    public void consumo(int mes, int gas, double precio) {
        // TODO: consumo
        double multiplicacion = (precio * gas) ;
        acumuladoConsumo += multiplicacion;
        if (acumuladoConsumo > maxConsumo){
            maxConsumo = acumuladoConsumo;
            mesMasConsumo = mes;
        }
        if (precio == 0){
            maxPrecio = precio;
        }
        else if (precio > maxPrecio){
            maxPrecio = precio;
            mesMasCaro = mes;
        }
        else if (precio < minPrecio && mesMasBarato == NINGUNO){
            minPrecio = precio;
            mesMasBarato = mes;
        }
    }

    /**
     * Gasto de mantenimiento en cada periodo
     * 
     * @param periodo Numero que representa el periodo, ej. OCTUBRE-DICIEMBRE es 1
     * @param importe Valor del gasto de mantenimiento
     */
    public void mantenimiento(int periodo, double importe) {
        // TODO: mantenimiento
        acumuladoMantenimiento += importe;
        if (importe == 0){
            maxMantenimiento = importe;
        }else if (importe > maxMantenimiento){
            maxMantenimiento = importe;
            periodoMasMantenimiento = periodo;
        }
    }
    

    /**
     * Gasto mensual en concepto de agua o luz.
     * 
     * @param mes      Numero del mes, ej. ENERO es 1
     * @param concepto Agua 'A' o luz 'L'
     * @param importe  Valor del gasto, ej. 189.03
     */
    public void gasto(int mes, char concepto, double importe) {
        // TODO: gasto
        if(concepto == 'A'){
            gastoAgua += importe;
        }else if (concepto == 'L'){
            gastoLuz += importe;
        }
        if (importe == maxGasto){
            importe = maxGasto;
        } else if (importe > maxGasto){
            importe = maxGasto;
            conceptoMasGasto = concepto;
            mesMasGasto = mes;
            maxGasto = mesMasGasto ;
        }
        }
    

    /**
     * Imprime el resultado del periodo, ej.
     * 
     * ==================
     * RESULTADO GLOBAL
     * ==================
     * Presupuesto: 38638.0
     * Consumo gas: 61688.26
     * Impuestos g.: 25909.07
     * Mantenimiento: 4157.58
     * Iva manto.: 914.67
     * Gasto agua: 2647.83
     * Iva agua: 582.52
     * Gasto luz: 4663.01
     * Iva luz: 1025.86
     * ------------------
     * TOTAL : -62950.8 Euros.
     * ------------------
     * ==================
     * RESULTADO X VECINO
     * ==================
     * Vecinos: 48
     * Aporte v.: 804.96
     * Gasto v.: 2116.43
     * Resultado: -1311.47
     * ------------------
     * El resultado ha sido NEGATIVO,
     * se tiene que pagar 1311.47 Euros.
     * El pago se pasara en
     * 5 cuotas de 262.29 Euros.
     * ------------------
     */
    public void printResultados() {
        // TODO: printResultados
         double IMP_IVA = 0.22;
         double IMP_HIDROCARBUROS = 0.20;
        double aporte = presupuesto / vecinos;
        double total = (presupuesto + acumuladoConsumo + (acumuladoConsumo *0.22 )+ acumuladoMantenimiento + maxMantenimiento + gastoAgua + (gastoAgua * 0.22 )+ gastoLuz + (gastoLuz * 0.22 +0.20));
        System.out.println("==================");
        System.out.println(" RESULTADO GLOBAL");
        System.out.println("==================");
        System.out.println("Presupuesto:   "+presupuesto+"");
        System.out.println("Consumo gas:   "+redondeo2decimales(acumuladoConsumo)+"");
        System.out.println("Impuesto g.:   "+redondeo2decimales(acumuladoConsumo * 0.22 + 0.20)+ "");
        System.out.println("Mantenimiento: "+redondeo2decimales(acumuladoMantenimiento)+"");
        System.out.println("Iva manto.:    "+redondeo2decimales(maxMantenimiento)+"");
        System.out.println("Gasto agua:    "+redondeo2decimales(gastoAgua )+"");
        System.out.println("Iva agua:      "+redondeo2decimales(gastoAgua * 0.22)+" ");
        System.out.println("Gasto luz:     "+redondeo2decimales(gastoLuz)+"");
        System.out.println("Iva luz:       "+redondeo2decimales(gastoLuz * 0.22 )+" ");
        System.out.println("------------------");
        System.out.println("TOTAL :        "+redondeo2decimales(total)+ " ");
        System.out.println("------------------");
        System.out.println("==================");
        System.out.println("RESULTADO X VECINO");
        System.out.println("==================");
        System.out.println("Vecinos:       "+vecinos+"");
        System.out.println("Aporte v.:     "+redondeo2decimales(aporte));
        System.out.println("Gasto v.:      " + redondeo2decimales(total / vecinos));
        System.out.println("Resultado:     "+redondeo2decimales(aporte - (total / vecinos)));
        System.out.println("------------------");
        System.out.println( ""+analisisResultado(aporte -(total / vecinos))+"");
        System.out.println("------------------");
    }

    /**
     * Imprime las estadisticas del periodo, ej.
     * 
     * ==================
     * ESTADISTICAS
     * ==================
     * Max. consumo: ENERO 12527.66
     * Mes mas caro: AGOSTO 0.202504
     * Mes mas barato: SEPTIEMBRE 0.067668
     * Mayor gasto en: ABRIL 679.94 LUZ
     * P. mas manto.: OCTUBRE-DICIEMBRE 1552.1
     * ------------------
     */
    
    public void printEstadisticas() {
        // TODO: printEstadisticas
        System.out.println("==================");
        System.out.println("ESTADISTICAS");
        System.out.println("==================");
        System.out.println("Max. consumo:  "+getNombreMes(mesMasConsumo)+"  " +redondeo2decimales(maxConsumo)+"");
        System.out.println("Mes mas caro:  "+getNombreMes(mesMasCaro)+"  "+redondeo2decimales(maxPrecio)+"");
        System.out.println("Mes mas barato: "+getNombreMes(mesMasBarato)+ " "+redondeo2decimales(minPrecio)+"");
        System.out.println("Mayor gasto en:  "+getNombreMes(mesMasGasto)+"  "+getNombreConcepto(conceptoMasGasto)+" "+mesMasGasto+"");
        System.out.println("P. mas manto:  "+getNombrePeriodo(periodoMasMantenimiento)+"  "+redondeo2decimales(maxMantenimiento)+"");
        System.out.println("------------------");
    }

    /**
     * Devuelve el nombre del mes asociado a su valor numerico
     * 
     * @param numMes Numero del mes del 1 al 12, ej. 1
     * @return Nombre del mes, ej. ENERO
     */
    public String getNombreMes(int numMes) {
        // TODO: getNombreMes
        switch (numMes){
        
         case 1:
             return"ENERO";
              
         case 2:
             return"FEBRERO";
              
         case 3: 
             return"MARZO";
            
         case 4:
             return"ABRIL";
            
         case 5: 
             return"MAYO";
              
         case 6:
             return"JUNIO";
              
         case 7: 
             return"JULIO";
         case 8: 
             return"AGOSTO";
              
         case 9: 
             return"SEPTIEMBRE";
              
         case 10: 
             return"OCTUBRE";
              
         case 11: 
             return"NOVIEMBRE";
         case 12: 
             return"DICIEMBRE";
         default :
             return "NINGUN MES";
        }
    }

    /**
     * Devuelve el nombre del concepto asociado al caracter
     * 
     * @param concepto Valor caracter, ej. 'L'
     * @return Nombre del concepto, ej. 'LUZ'. Si no es agua o luz devuelve "NADA"
     */
    public String getNombreConcepto(char concepto) {
        // TODO: getNombreConcepto
        if(concepto == 'A'){
         return"AGUA";
        }else if (concepto == 'L'){
         return "LUZ";
        }else {
            return "NADA";
        }
        
    }

    /**
     * Devuelve el nombre del periodo asociado a su numero
     * 
     * @param numPeriodo Numero de periodo, del 1 al 4, ej. 4
     * @return Nombre del periodo con nombres de los meses separados por guion, ej.
     *         "OCTUBRE-DICIEMBRE". Sino devuelve "NINGUN PERIODO"
     */
    public String getNombrePeriodo(int numPeriodo) {
        // TODO: getNombrePeriodo
        switch (numPeriodo){
        case 1: 
            return"OCTUBRE-DICIEMBRE";
        case 2: 
            return"ENERO-MARZO";
        case 3: 
            return"ABRIL-JUNIO";
        case 4: 
            return"JULIO-SEPTIEMBRE";
            default :
                return "NINGUN PERIODO";
        }       
    }

    /**
     * Analiza el resultado, si el valor es negativo se tendra que pagar si es
     * positivo se devolvera.
     * En el caso negativo se debera pagar de una vez si el importe en menor o igual
     * que 200,
     * en multiplos de 200 y el resto si el resultado es menor o igual que 600 o
     * en 5 partes alicuotas sino.
     * 
     * @param resultado cantidad positiva o negativa, ej. -1311.47
     * @return Mensaje con la informacion sobre el pago. Ej.
     *         </br>
     *         El resultado ha sido NEGATIVO,
     *         se tiene que pagar 114.56 Euros.
     *         El pago se pasara en un solo cobro.
     *         </br>
     *         El resultado ha sido NEGATIVO,
     *         se tiene que pagar 513.33 Euros.
     *         El pago se pasara en
     *         2 cuotas de 200 Euros y
     *         otro cobro de 113.33 Euros.
     *         </br>
     *         El resultado ha sido NEGATIVO,
     *         se tiene que pagar 1311.47 Euros.
     *         El pago se pasara en
     *         5 cuotas de 262.29 Euros.
     *         </br>
     *         El resultado ha sido POSITIVO,
     *         se devolvera 45.52 Euros.
     *         El pago se realizara en breve en
     *         una transferencia.
     */
    public String analisisResultado(double resultado) {
        // TODO: analisisResultado
        if (resultado >=0){
            return ("El resultado ha sido POSITIVO ,Se devolvera " +redondeo2decimales(resultado)+ "El pago se realizara en breve en una transferencia");
        }
        if (resultado <= -200){
            return ("El resultado ha sido NEGATIVO Se tiene que pagar " +redondeo2decimales(resultado)+ " El pago se pasara en un solo cobro");
        }
        else if (resultado < -600 ){
            return ("El resultado has sido NEGATIVO ,se tiene que pagar " +resultado+ 
            " EUROS El pago se pasara en 2 cuotas de 200 Euros y otro cobro de "
            +divisionEntera(resultado ,200));
        }else if (resultado > -600 ){
            return ("El resultado ha sido NEGATIVO , se tiene que pagar " +resultado+ 
            "EUROS. El pago se pasara en 5 cuotas de "+divisionEntera(resultado ,200)+ 
            " EUROS.");
        }else {
            return "NADA";
        }
        
        }
    

    /**
     * Redondea un valor de tipo double a dos decimales.
     * Al imprimirlo se vera al menos un decimal y como mucho dos.
     *
     * @param valor Numero con decimales de tipo double
     * @return Redondeo con 1 o 2 decimales, ej.
     *         38638 -> 38638.0
     *         61688.255730000004 -> 61688.26
     *         25909.067406600003 -> 25909.07
     *         -62950.79553660001 -> -62950.8
     *         -1311.4749070125 -> -1311.47
     */
    public double redondeo2decimales(double valor) {
        // TODO: redondeo2decimales
        return Math.round(valor * 100)/100d;
    }

    /**
     * Divide un numero decimal entre un numero entero y devuelve el resultado
     * entero.
     * 
     * @param dividendo Numero con decimales que se divide, ej. 647.55
     * @param divisor   Numero entero que divide, ej. 200
     * @return Cociente, numero entero, cuantos divisores caben en el dividendo, ej.
     *         3
     */
    public int divisionEntera(double dividendo, int divisor) {
        // TODO: divisionEntera 
        return Math.round((int)dividendo / divisor *100)/100;
    }

    /**
     * Divide un numero decimal entre un numero entero y devuelve el resto con
     * decimales.
     * 
     * @param dividendo Numero con decimales que se divide, ej. 647.55
     * @param divisor   Numero entero que divide, ej. 200
     * @return Resto con decimales, ej. 47.55
     */
    public double restoDivisionEntera(double dividendo, int divisor) {
        // TODO: restoDivisionEntera
        return Math.round((dividendo % divisor)*100)/100d;
    }

}
