import java.lang.Math;
/**
 * Clase que gestiona los gastos de campanya de
 * una caldera de comunitaria sin contadores.
 * Permite analizar gastos y hacer una regularizacion
 * informando cuanto se tiene que devolver o
 * cuanto mas tiene que pagar cada vecino.
 * 
 * @author Carlos Alonso
 * @version 1.0
 */
public class Caldera {
    
    // CONSTANTES

    /** El IVA se aplica a todos los gasto. */
    private final double IMP_IVA = 0.22;
    /** El impuesto de hidrocarburos solo se aplica al gas, ademas del iva. */
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

    // VARIABLES DE CLASE (PROPIEDADES/ATRIBUTOS)
    
    // vecinos y presupuesto
    private int vecinos;
    private double presupuesto;

    // acumulados
    private double acumuladoConsumo;
    private double acumuladoMantenimiento;
    private double gastoAgua;
    private double gastoLuz;
    
    // estadisticas
    private int mesMasConsumo;
    private double maxConsumo;
    private int mesMasCaro;
    private double maxPrecio;
    
    private int mesMasBarato;
    private double minPrecio;
    
    private int periodoMasMantenimiento;
    private double maxMantenimiento;
    
    private int mesMasGasto;
    private double maxGasto;
    private char conceptoMasGasto;
    
    // constructores
    
    /**
     * Constructor de la clase Caldera. Inicializa los atributos.
     */
    public Caldera(){
         vecinos = 0;
         presupuesto = 0;
        
         acumuladoConsumo = 0;
         acumuladoMantenimiento = 0;
         gastoAgua = 0;
         gastoLuz = 0;
            
         mesMasConsumo = NINGUNO;
         maxConsumo = 0;
         mesMasCaro = 0;
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
    public Caldera(int queVecinos, int quePresupuesto){
        vecinos = queVecinos;
        presupuesto = quePresupuesto;
    }

    // getters y setters
    
    /**
     * Fija el valor del presupuesto
     * 
     * @param quePresupuesto Valor del presupuesto, ej. 38638
     */
    public void setPresupuesto(double quePresupuesto){
        presupuesto = quePresupuesto;
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
    public void setVecinos(int queVecinos){
        vecinos = queVecinos;
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
        double euros = precio * gas;
        System.out.println(" En el mes de " + getNombreMes(mes) + " se han consumido " + gas + 
        "KWh a un precio de " + euros + " Euros/KWh");
        acumuladoConsumo += euros;
        if (precio > maxPrecio){
            maxPrecio = precio;
            mesMasCaro = mes;
        }else if (precio < minPrecio){
            minPrecio = precio;
            mesMasBarato = mes;
        }
        if(euros > maxConsumo){
            maxConsumo = euros;
            mesMasConsumo = mes;
        }
    }

    /**
     * Gasto de mantenimiento en cada periodo
     * 
     * @param periodo Numero que representa el periodo, ej. OCTUBRE-DICIEMBRE es 1
     * @param importe Valor del gasto de mantenimiento
     */
    public void mantenimiento(int periodo, double importe) {
        double periodo1T = 0;
        double periodo2T = 0;
        double periodo3T = 0;
        double periodo4T = 0;
        acumuladoMantenimiento += importe;
        if(periodo == 1){
            periodo1T += importe;
        }else if(periodo == 2){
            periodo2T += importe;
        }else if(periodo == 3){
            periodo3T += importe;
        }else{
            periodo4T += importe;
        }
        
        if(periodo1T > maxMantenimiento){
            maxMantenimiento = periodo1T;
            periodoMasMantenimiento = periodo;
        }else if(periodo2T > maxMantenimiento){
            maxMantenimiento = periodo2T;
            periodoMasMantenimiento = periodo;
        }else if(periodo3T > maxMantenimiento){
            maxMantenimiento = periodo3T;
            periodoMasMantenimiento = periodo;
        }else{
            maxMantenimiento = periodo4T;
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
        if(concepto == 'A'){
            gastoAgua += importe;
        }else{
            gastoLuz += importe;
        }
        
        if(importe > maxGasto){
            maxGasto = importe;
            conceptoMasGasto = concepto;
            mesMasGasto = mes;
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
        double impuestoG = (acumuladoConsumo * IMP_IVA) + (acumuladoConsumo * IMP_HIDROCARBUROS);
        double ivaMantenimiento = acumuladoMantenimiento * IMP_IVA;
        double ivaAgua = gastoAgua * IMP_IVA;
        double ivaLuz = gastoLuz * IMP_IVA;
        
        double gTotal = acumuladoConsumo + acumuladoMantenimiento + gastoLuz + gastoAgua +
               impuestoG + ivaMantenimiento + ivaLuz + ivaAgua;
        
        double total = presupuesto - gTotal;
        
        double gVecino = gTotal / vecinos;
        double vResultado = total / vecinos;
        
        String sResultado;
        
        System.out.println("================== \n" +
                            " RESULTADO GLOBAL \n" +
                           "================== \n");
                           
        System.out.println("Presupuesto: " + presupuesto + "\n Consumo gas: " + redondeo2decimales(acumuladoConsumo) +
        "\n Impuestos g.: " + redondeo2decimales(impuestoG) + "\n Mantenimiento: " + acumuladoMantenimiento + "\n Iva manto.: " + redondeo2decimales(ivaMantenimiento) +
        "\n Gasto agua: " + gastoAgua + "\n Iva agua: "+ redondeo2decimales(ivaAgua) + "\n Gasto luz: "+ gastoLuz +
        "\n Iva luz: "+ redondeo2decimales(ivaLuz));
        
        System.out.println("------------------ \n" +
                        "TOTAL :" + redondeo2decimales(total) + "\n" +
                           "------------------ \n");
                           
        System.out.println("================== \n" +
                            "RESULTADO X VECINO \n" +
                           "================== \n");  
                           
        System.out.println("Vecinos: " + vecinos + "\n Aporte v.: " + redondeo2decimales((presupuesto/vecinos)) + "\n Gasto v.: " + redondeo2decimales(gVecino) +
         "\n Resultado: " + redondeo2decimales(vResultado));
        
        if(vResultado < 0){
            sResultado = "NEGATIVO";
        }else{
            sResultado = "POSITIVO";
        }
        
        
        System.out.println("El resultado ha sido " + sResultado +",\nse tiene que pagar " + Math.abs(redondeo2decimales(vResultado)) +
        " Euros.\n El pago se pasara en \n 5 cuotas de " + Math.abs(redondeo2decimales((vResultado/5.0))) + " Euros.");
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
        
        System.out.println("================== \n" +
                          "   ESTADISTICAS \n" +
                           "================== \n");
                           
        System.out.println("Max. consumo: " + getNombreMes(mesMasConsumo) + " " + maxConsumo + "\n Mes mas caro: " + getNombreMes(mesMasCaro) + " "
        + maxPrecio + "\n Mes mas barato: " + getNombreMes(mesMasBarato) + " " + minPrecio + "\n Mayor gasto en: " + getNombreMes(mesMasGasto) + " " + maxGasto + " " +
        getNombreConcepto(conceptoMasGasto) + "\n P. mas manto.: " + getNombrePeriodo(periodoMasMantenimiento) + " "+ maxMantenimiento);
         
    }

    /**
     * Devuelve el nombre del mes asociado a su valor numerico
     * 
     * @param numMes Numero del mes del 1 al 12, ej. 1
     * @return Nombre del mes, ej. ENERO
     */
    public String getNombreMes(int numMes) {
        String nombreMes = "";
        switch(numMes)
        {
            case 1: nombreMes = "ENERO";
                    break;
            case 2: nombreMes = "FEBRERO";
                    break;
            case 3: nombreMes = "MARZO";
                    break;
            case 4: nombreMes = "ABRIL";
                    break;        
            case 5: nombreMes = "MAYO";
                    break;
            case 6: nombreMes = "JUINIO";
                    break;
            case 7: nombreMes = "JULIO";
                    break;
            case 8: nombreMes = "AGOSTO";
                    break; 
            case 9: nombreMes = "SEPTIEMBRE";
                    break;
            case 10: nombreMes = "OCTUBRE";
                    break;
            case 11: nombreMes = "NOVIEMBRE";
                    break;
            case 12: nombreMes = "DICIEMBRE";
                    break;
            default: nombreMes = "NINGUNO";
                    break;        
            
        }
        return nombreMes;
    }

    /**
     * Devuelve el nombre del concepto asociado al caracter
     * 
     * @param concepto Valor caracter, ej. 'L'
     * @return Nombre del concepto, ej. 'LUZ'. Si no es agua o luz devuelve "NADA"
     */
    public String getNombreConcepto(char concepto) {
        String nombreConcepto = "";
        if(concepto == LUZ){
            nombreConcepto = "LUZ";
        }else if(concepto == AGUA){
            nombreConcepto = "AGUA";
        }else{
            nombreConcepto = "NINGUNO";
        }
        return nombreConcepto;        
    }

    /**
     * Devuelve el nombre del periodo asociado a su numero
     * 
     * @param numPeriodo Numero de periodo, del 1 al 4, ej. 4
     * @return Nombre del periodo con nombres de los meses separados por guion, ej.
     *         "OCTUBRE-DICIEMBRE". Sino devuelve "NINGUN PERIODO"
     */
    public String getNombrePeriodo(int numPeriodo) {
        String nombrePeriodo = "";
        switch(numPeriodo)
        {
            case PERIODO_OCTUBRE_DICIEMBRE: nombrePeriodo = "OCTUBRE-DICIEMBRE";
                        break;
            case PERIODO_ENERO_MARZO: nombrePeriodo = "ENERO-MARZO";
                        break;
            case PERIODO_ABRIL_JUNIO: nombrePeriodo = "ABRIL-JUNIO";
                        break;
            case PERIODO_JULIO_SEPTIEMBRE: nombrePeriodo = "JULIO-SEPTIEMBRE";
                        break;
            default: nombrePeriodo = "NINGUN PERIODO";
        }
        return nombrePeriodo;        
    }

    /**
     * Analiza el resultado, si el valor es negativo se tendra que pagar si es
     * positivo se devolvera.
     * En el caso negativo se debera pagar de una vez si el importe es menor o igual
     * que 200,
     * es multiplo de 200 y el resto si el resultado es menor o igual que 600 o
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
        String sResultado = "";
        if(resultado < 0 && resultado <= 200){
            sResultado = "El resultado ha sido NEGATIVO, \n se tiene que pagar " + resultado + " Euros.\n" +
            "El pago se pasara en un solo cobro";
        }else if (resultado <= 600){
            int cuotas = 0;
            double resto = 0;
            int x = (int)resultado;
            cuotas = x / 200;
            resto = resultado % cuotas;
            sResultado = "El resultado ha sido NEGATIVO, \n se tiene que pagar " + resultado + " Euros.\n" +
            "El pago se pasara en " + cuotas + " cuota(s) de 200 Euros y otro cobro de " + resto + " Euros." ;
        }else if(resultado > 600){
            double x = (double)5;
            double cantidad = resultado / x;
            sResultado = "El resultado ha sido NEGATIVO, \n se tiene que pagar " + resultado + " Euros.\n" +
            "El pago se pasara en 5 cuotas de " + cantidad + " Euros.";
        }else{
            sResultado = "El resultado ha sido POSITIVO, \n se devolvera " + resultado + " Euros.\n" +
            "El pago se realizara en breve en una transferencia";
        }
        printResultados();   
        return sResultado;
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
        double rValor = Math.round(valor * 100.0) / 100.0;
        return rValor;
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
        int x = (int)dividendo;
        int cociente = x / divisor;
        return cociente;
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
        double x = (double)divisor;
        double resto = dividendo % x;
        return resto;
    }

}
