/**
 * Clase que gestiona los gastos de campanya de
 * una caldera de comunitaria sin contadores.
 * Permite analizar gastos y hacer una regularizacion
 * informando cuanto se tiene que devolver o
 * cuanto mas tiene que pagar cada vecino.
 * 
 * @author Hugo Rodriguez
 * @version 1.0
 */
public class Caldera {
    
    // TODO: CONSTANTES
    private double ImpIva = 0.22;
    private double ImpHidrocarburos = 0.20;

    /** El IVA se aplica a todos los gasto. */
    
    /** El impuesto de hidrocarburos solo se aplica al gas, ademas del iva. */
    

    // Conceptos gastos
    private final char agua = 'A';
    private final char luz = 'L';
    private final char Nada = 'N';

    // Ningun mes
    private final int Ninguno = 0;

    // Periodos
    private final int PeriodoOctubreDiciembre = 1;
    private final int PeriodoEneroMarzo = 2;
    private final int PeriodoAbrilJunio = 3;
    private final int PeriodoJulioSeptiembre = 4;

    
    // TODO: VARIABLES DE CLASE (PROPIEDADES/ATRIBUTOS)

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
    private int maxMantenimiento;
    
    private int mesMasGasto;
    private int maxGasto;
    private int conceptoMasGasto;
    // TODO: constructores
    
    /**
     * Constructor de la clase Caldera. Inicializa los atributos.
     */
    public Caldera()
    {
        vecinos = 0;
        presupuesto = 0;
        acumuladoConsumo = 0;
        acumuladoMantenimiento = 0;
        gastoAgua = 0;
        gastoLuz = 0;
        mesMasConsumo = Ninguno;
        maxConsumo = Nada;
        mesMasCaro = Ninguno;
        maxPrecio = Nada;
        mesMasBarato = Nada;
        minPrecio = 0;
        periodoMasMantenimiento = Ninguno;
        maxMantenimiento = Nada;
        mesMasGasto = Ninguno;
        maxGasto = Nada;
        conceptoMasGasto = Nada;
    }

    /**
     * Constructor de la clase Caldera. Inicializa los atributos.
     * 
     * @param queVecinos     Numero de vecinos que conforman la comunidad
     * @param quePresupuesto Presupuesto inicial con el que se pretende afrontar los
     *                       gastos
     */
    public Caldera(double quePresupuesto,int queVecinos)
    {
        presupuesto = quePresupuesto;
        vecinos = queVecinos;
        acumuladoConsumo = 0;
        acumuladoMantenimiento = 0;
        gastoAgua = 0;
        gastoLuz = 0;
        mesMasConsumo = Ninguno;
        maxConsumo = Nada;
        mesMasCaro = Ninguno;
        maxPrecio = Nada;
        mesMasBarato = Nada;
        minPrecio = 0;
        periodoMasMantenimiento = Ninguno;
        maxMantenimiento = Nada;
        mesMasGasto = Ninguno;
        maxGasto = Nada;
        conceptoMasGasto = Ninguno;
    
    }

    
    // TODO: getters y setters
    
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
        // TODO: consumo
        acumuladoConsumo += precio * gas;//total del consumo
        
        if (minPrecio > precio) {
            minPrecio = precio;
            mesMasBarato = mes;
        }
        else if (minPrecio == 0){
            minPrecio = precio;
            mesMasBarato = mes;
        }
        
        if (maxPrecio < precio) {
            maxPrecio = precio;
            mesMasCaro = mes;
        }
        else if (maxPrecio == 0){
            maxPrecio = precio;
            mesMasCaro = mes;
        }
        
        if (maxConsumo < acumuladoConsumo){
            maxConsumo = precio * gas;
            mesMasConsumo = mes;
        }
        else if (maxConsumo == 0){
            maxConsumo = precio * gas;
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
        // TODO: mantenimiento
        acumuladoMantenimiento += importe;
        double primerPeriodo = 0;
        double segundoPeriodo = 0;
        double tercerPeriodo = 0;
        double cuartoPeriodo = 0;

        switch (periodo){
            case 1:
                primerPeriodo += importe;
                break;
            case 2:
                segundoPeriodo += importe;
                break;
            case 3:
                tercerPeriodo += importe;
                break;
            case 4: 
                cuartoPeriodo += importe;
                break;
        }

         if (maxMantenimiento > primerPeriodo){
            maxMantenimiento += importe;
            periodoMasMantenimiento = periodo;
        }
        else if (maxMantenimiento > segundoPeriodo){
            maxMantenimiento += importe;
            periodoMasMantenimiento = periodo;
        }
        else if (maxMantenimiento > tercerPeriodo){
            maxMantenimiento += importe;
            periodoMasMantenimiento = periodo;
        }
        else if (maxMantenimiento > cuartoPeriodo){
            maxMantenimiento += importe;
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
    
    public void gasto(int mes, char concepto, double importe){
        // TODO: gasto
         if (concepto == 'A'){
            gastoAgua += importe;
        }
        gastoLuz += importe;

        if (maxGasto < importe){
            maxGasto += importe;
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
        // TODO: printResultados
        double total = (presupuesto - acumuladoConsumo - ((ImpIva + ImpHidrocarburos) * acumuladoConsumo) - acumuladoMantenimiento - 
        (ImpIva * acumuladoMantenimiento) - gastoAgua - (ImpIva * gastoAgua) - gastoLuz - (ImpIva * gastoLuz));
        double vecinosTotales = (total / vecinos);
        
        System.out.println("==================");
        System.out.println("RESULTADO GLOBAL");
        System.out.println("==================");
        System.out.println("Presupuesto: " + presupuesto );
        System.out.println("Consumo gas: " + redondeo2decimales(acumuladoConsumo) );
        System.out.println("Impuestos g.: " + redondeo2decimales((ImpIva + ImpHidrocarburos) * acumuladoConsumo ));
        System.out.println("Mantenimiento " + acumuladoMantenimiento );
        System.out.println("Iva manto.: " + redondeo2decimales((ImpIva * acumuladoMantenimiento)));
        System.out.println("Gasto agua: " + gastoAgua );
        System.out.println("Iva agua: " + redondeo2decimales((ImpIva * gastoAgua)));
        System.out.println("Gasto luz: " + gastoLuz );
        System.out.println("Iva luz: " + redondeo2decimales((ImpIva * gastoLuz)));
        System.out.println("------------------");
        System.out.println("TOTAL : " + redondeo2decimales(total) );
        System.out.println("------------------");
        System.out.println("==================");
        System.out.println("RESULTADO X VECINO");
        System.out.println("==================");
        System.out.println("Vecinos: " + vecinos );
        System.out.println("Aporte v.: " + redondeo2decimales((presupuesto / vecinos)));
        System.out.println("Gasto v.: " + redondeo2decimales(((total - presupuesto)) / vecinos)*(-1));
        System.out.println("Resultado: " + redondeo2decimales((total / vecinos)));
        System.out.println("------------------");
        System.out.println(analisisResultado((total / vecinos)));
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
        System.out.println ("\n ESTADISTICAS");
        System.out.println ("\n==================");
        System.out.println ("\nMax consumo:    "   + getNombreMes(mesMasConsumo) + "  " + maxConsumo);
        System.out.println ("\nMes mas caro:   "   + getNombreMes(mesMasCaro)    + "  " + maxPrecio);
        System.out.println ("\nMes mas barato: "   + getNombreMes(mesMasBarato)  + "  " + minPrecio);
        System.out.println ("\nMayor gasto en: "   + getNombreMes(mesMasGasto)   + "  " + maxGasto   + "  " +  conceptoMasGasto);
        System.out.println ("\nP. mas manto.:  "   + getNombrePeriodo(periodoMasMantenimiento)  + "  " +  maxMantenimiento);
    }

    /**
     * Devuelve el nombre del mes asociado a su valor numerico
     * 
     * @param numMes Numero del mes del 1 al 12, ej. 1
     * @return Nombre del mes, ej. ENERO
     */
    public String getNombreMes(int numMes) {
        // TODO: getNombreMes 
          switch(numMes){
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "ABRIL";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Septiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "Ninguno";
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
        if (concepto == 'A'){
            return "Agua";
        }else if (concepto == 'L'){
            return "Luz";
        }
        else if (concepto == 'N'){
            return "Nada";
        }
        return "Caracter incorrecto";        
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
            case PeriodoOctubreDiciembre:
                //OCTUBRE A DICIEMBRE
                return "Octubre-Diciembre";
            case PeriodoEneroMarzo:
                //ENERO MARZO
                return "Enero-Marzo";
            case PeriodoAbrilJunio:
                //ABRIL JUNIO
                return "Abril-Junio";
            case PeriodoJulioSeptiembre:
                //JULIO SEPTIEMBRE
                return "Julio-Septiembre";
            default:
                return "Ningun Periodo";
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
        if (resultado >= 0){
           return "El resultado ha sido POSITIVO," + "\n" + "se devolvera " + resultado 
           + " Euros." + "\n" + "El pago se realizara en breve en" + "\n" + "una transferencia.";

        }
        else if ((resultado >= -200) || (resultado < 0)){
            return "El resultado ha sido NEGATIVO," + "\n" + "se tiene que pagar " 
            + (-1 * resultado) + " Euros." + "\n" + "El pago se pasara en un solo cobro."; 
        }
        else if ((resultado >= -600) || (resultado < 0)){
            return "El resultado ha sido NEGATIVO," + "\n" + "se tiene que pagar " + resultado
            + " Euros." + "\n" + "El pago se pasara en" + "\n" + ((int)resultado / 200) + "cuotas de 200 Euros y" 
            + "\n" + "otro cobro de " + (resultado - (((int)resultado / 200)) * 200);
        }
        else if (resultado < -600){
            return "El resultado ha sido NEGATIVO," + "\n" + "se tiene que pagar " + resultado
            + " Euros." + "\n" + "El pago se pasara en" + "\n" + "5 cuotas de " + (resultado / 5) + " Euros.";
        }
        return "Error";
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
        valor = Math.round(valor * 100) / 100.0;
        return valor;
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
        int resultado = (int)dividendo / divisor;
        return resultado;
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
        int result = (int)dividendo % divisor;
        return result;
    }

}
