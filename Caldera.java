/**
 * Clase que gestiona los gastos de campanya de
 * una caldera de comunitaria sin contadores.
 * Permite analizar gastos y hacer una regularizacion
 * informando cuanto se tiene que devolver o
 * cuanto mas tiene que pagar cada vecino.
 * 
 * @author Aitor Etxabarren
 * @version 1.0
 */
public class Caldera {

    // Tipos de impuestos
    final private double IMP_IVA = 0.22;
    final private double IMP_HIDROVARBUROS = 0.20;

    // Conceptos gastos
    final private char AGUA = 'A';
    final private char LUZ = 'L';
    final private char NADA = 'N';

    // Ningun mes
    final private int NINGUNO = 0;

    // Periodos
    final private int PERIODO_OCTUBRE_DICIEMBRE = 1;
    final private int PERIODO_ENERO_MARZO = 2;
    final private int PERIODO_ABRIL_JUNIO = 3;
    final private int PERIODO_JULIO_SEPTIEMBRE = 4;

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
    private double maxMantenimiento;
    private int mesMasGasto;
    private double maxGasto;
    private char conceptoMasGasto;

    // TODO: constructores
    /**
     * Constructor de la clase Caldera. Inicializa los atributos.
     */

    public Caldera (){
        acumuladoConsumo = 0;
        acumuladoMantenimiento  =0;
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
        maxGasto = NADA;
        conceptoMasGasto =NADA;  
    }

    /**
     * Constructor de la clase Caldera. Inicializa los atributos.
     * 
     * @param queVecinos     Numero de vecinos que conforman la comunidad
     * @param quePresupuesto Presupuesto inicial con el que se pretende afrontar los
     *                       gastos
     */
    public Caldera (int queVecinos,int quePresupuesto){
        vecinos = queVecinos;
        presupuesto = quePresupuesto;
    }

    // TODO: getters y setters

    public int getVecinos (){
        return vecinos;
    }

    public void setVecinos(int queVecinos){
        vecinos = queVecinos;
    }

    public void setPresupuesto(double quePresupuesto){
        presupuesto = quePresupuesto;

    }

    public double getPresupuesto(){
        return presupuesto;
    }
    /**
     * 
     * 
     * Fija el valor del presupuesto
     * 
     * @param quePresupuesto Valor del presupuesto, ej. 38638
     */

    /**+
     * Obtiene el valor del presupuesto
     * 
     * @return valor del presupuesto, ej. 38638
     */

    /**
     * Fija el numero de vecinos de la comunidad
     * 
     * @param queVecinos numero de vecinos, ej. 48
     */

    /**
     * Obtiene el numero de vecinos
     * 
     * @return numero de vecinos, ej. 48
     */

    /**
     * Cantidad de gas consumido cada mes al precio de mercado
     * 
     * @param mes    Numero de mes, 1 es enero, 2 febrero, ..., 12 diciembre, ej. 9
     * @param gas    Cantidad de gas consumido en KWh, ej. 15496
     * @param precio Precio en Euros al que se ha conseguido el gas, ej. 0.067668
     */
    public void consumo(int mes, int gas, double precio) {
        if(acumuladoConsumo == 0){
            acumuladoConsumo = gas;
            maxPrecio = precio;
            mesMasCaro = mes;
            mesMasBarato = mes;
            minPrecio = precio;
        }else if(acumuladoConsumo != 0){
            acumuladoConsumo = acumuladoConsumo + gas;
        }
        if (gas > maxConsumo){
            maxConsumo = gas;
            mesMasConsumo = mes;
        }
        if(precio > maxPrecio){
            maxPrecio = precio;
            mesMasCaro = mes;
        }
        if(  precio < minPrecio){
            minPrecio = precio;
            mesMasBarato = mes;
        }        
        System.out.println("Presupuesto: " + presupuesto + "\n"  + "Consumo gas:" + gas );
    }

    /**
     * Gasto de mantenimiento en cada periodo
     * 
     * @param periodo Numero que representa el periodo, ej. OCTUBRE-DICIEMBRE es 1
     * @param importe Valor del gasto de mantenimiento
     */
    public void mantenimiento(int periodo, double importe) {

        if(maxMantenimiento == 0){
            maxMantenimiento = importe;
            periodoMasMantenimiento = periodo;
        }else if (maxMantenimiento != 0 && importe > maxMantenimiento){
            maxMantenimiento = importe;
            periodoMasMantenimiento = periodo;
        }

        if(acumuladoMantenimiento == 0 ){
            acumuladoMantenimiento = importe;
        } else if(acumuladoMantenimiento != 0){
            acumuladoMantenimiento = acumuladoMantenimiento + importe;
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

        if(importe > maxGasto){
            maxGasto = importe;
            conceptoMasGasto = concepto;
            mesMasGasto = mes;
        }
        if (concepto == AGUA){
            gastoAgua = importe;
        }else if(concepto == LUZ){
            gastoLuz = importe;
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
        
        double impuestoGas = acumuladoConsumo * IMP_HIDROVARBUROS;
         double mantenimientoIVA = acumuladoMantenimiento * IMP_IVA;
         double aguaIVA = gastoAgua * IMP_IVA;
         double luzIVA = gastoLuz * IMP_IVA;
         double aporteV = presupuesto / vecinos;
         double gastoV = (gastoAgua + gastoLuz) / vecinos;
         double resultado = presupuesto - acumuladoConsumo + impuestoGas + acumuladoMantenimiento + gastoAgua + gastoLuz + luzIVA + LUZ;
         double vecinosTotal = aporteV - gastoV ;
         String cantidad = analisisResultado(vecinosTotal);
        System.out.println("==================" + "\n" + "RESULTADO GLOBAL" + "\n" + "==================" + "\n");
        System.out.println("Presupuesto: " + presupuesto + "\n" );
        System.out.println("Consumo gas:" + acumuladoConsumo + "\n");
        System.out.println("Impuesto g.:" + impuestoGas + "\n" );
        System.out.println("Mantenimiento" + acumuladoMantenimiento + "\n");
        System.out.println("IVA manto: " + mantenimientoIVA + "\n");
        System.out.println("Gasto agua: " + gastoAgua + "\n");
        System.out.println("IVA agua: " + aguaIVA + "\n");
        System.out.println("Gasto luz: " + gastoLuz + "\n");
        System.out.println("IVA luz: " + luzIVA);
        System.out.println("------------------" + "\n");
        if(resultado > presupuesto ){
        System.out.println("TOTAL : " + (resultado) + "\n");
        } else{
         System.out.println("TOTAL : " + (resultado - presupuesto ) + "\n");
        }
        System.out.println("------------------" + "\n" + "\n");
        System.out.println("=================="+ "\n" + "RESULTADO X VECINO" + "\n" + "==================" + "\n");
        System.out.println("Vecinos: " + vecinos);
        System.out.println("Aporte v: " + aporteV + "\n");
        System.out.println("Gasto v:  " + gastoV + "\n" );
        System.out.println("Resultado: " + (vecinosTotal + "\n"));
        System.out.println("------------------" + "\n");
        System.out.println(cantidad + "\n");
        
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
    public void printEstadisticas( ) {

         String nombreMesCon = getNombreMes(mesMasConsumo);
         String nombreConcepto = getNombreConcepto(conceptoMasGasto);
         String nombreMesCar= getNombreMes(mesMasCaro);
         String nombreMesBar = getNombreMes(mesMasBarato);
         String nombreMesGas = getNombreMes(mesMasGasto);
         String nombrePeriodoMas = getNombrePeriodo(periodoMasMantenimiento);
        System.out.println("==================" + "\n");
        System.out.println("ESTADISTICAS" + "\n");
        System.out.println("==================");
        System.out.println("Max. consumo:  " + nombreMesCon + " " + maxConsumo + "\n");
        System.out.println("Mes mas caro:  " + nombreMesCar + " " + maxPrecio + "\n");
        System.out.println("Mes mas barato: " + nombreMesBar + " " + minPrecio + "\n");
        System.out.println("Mayor gasto en: " + nombreMesGas + " " + maxGasto + " " + conceptoMasGasto +"\n");
        System.out.println("P. mas mant0.: " + nombrePeriodoMas + " " + maxMantenimiento + "\n");
    } 
 

    /**
     * Devuelve el nombre del mes asociado a su valor numerico
     * 
     * @param numMes Numero del mes del 1 al 12, ej. 1
     * @return Nombre del mes, ej. ENERO
     */
    public String getNombreMes(int numMes) {
        String nombreMes = "";
        switch(numMes){
            case 1: nombreMes = "Enero";
                break;
            case 2: nombreMes = "Febrero";
                break;
            case 3: nombreMes = "Marzo";
                break;
            case 4: nombreMes = "Abril";
                break;
            case 5: nombreMes = "Mayo";
                break;
            case 6: nombreMes = "Junio";
                break;
            case 7: nombreMes = "Julio";
                break;
            case 8: nombreMes = "Agosto";
                break;
            case 9: nombreMes = "Septiembre";
                break;
            case 10: nombreMes = "Octubre";
                break;
            case 11: nombreMes = "Noviembre";
                break;
            case 12: nombreMes = "Diciembre";
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
        String nuevoConcepto="";
        
        if (concepto == LUZ){
            nuevoConcepto = "LUZ";       
        } 
        if(concepto == AGUA){
            nuevoConcepto = "AGUA";
        } 
        if (concepto == NADA){
            nuevoConcepto = "NADA";
        }
        return nuevoConcepto;
    }
    
    /**
     * Devuelve el nombre del periodo asociado a su numero
     * 
     * @param numPeriodo Numero de periodo, del 1 al 4, ej. 4
     * @return Nombre del periodo con nombres de los meses separados por guion, ej.
     *         "OCTUBRE-DICIEMBRE". Sino devuelve "NINGUN PERIODO"
     */
    public String getNombrePeriodo(int numPeriodo) {
           String periodo = "";
        switch(numPeriodo){
            case PERIODO_OCTUBRE_DICIEMBRE : periodo = "OCTUBRE-DICIEMBRE";
                break;
            case PERIODO_ABRIL_JUNIO : periodo = "PERIODO_ABRIL_JUNIO";
                break;
            case PERIODO_JULIO_SEPTIEMBRE : periodo = "PERIODO_JULIO_SEPTIEMBRE";
                break;
            case PERIODO_ENERO_MARZO : periodo = "PERIODO_ENERO_MARZO";
        }
        return periodo;        
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
        String nuevoResultado = "";
        if (resultado > 0){
        nuevoResultado = "El resultado  ha sido POSITIVO " + "\n" + "se devolveran " + resultado + " Euros " + 
        "el pago se realizara en breve en una transferencia" ;
    }
    if (resultado < 0 && resultado >= -200 ){
        return "El resultado ha sido NEGATIVO " + "\n" + "se tiene que pagar " + -resultado + " Euros" + "\n" + "El pago se pasara en un solo cobro";
    }
    if(resultado < 0 && resultado < -200 && resultado > -300 && resultado >= -600){
    nuevoResultado = "El resultado ha sido NEGATIVO " + "\n" + "se tiene que pagar " + - resultado + " Euros " + "\n" + 
         "El pago se pasara en 1 cuota de 200 Euros y otro cobro de " + (-resultado - 200) + " Euros" ;
    }
    if ( resultado < 0 && resultado < -200 && resultado >= -600 ) {
         nuevoResultado = "El resultado ha sido NEGATIVO," + "\n"  + "se tiene que pagar " + - resultado + " Euros " + "\n" +   
         "El pago se pasara en 2 cuotas de 200 Euros y otro cobro de " + (-resultado - 400) + " Euros" ;
        
    } 
    if (resultado < 0 && resultado < -200 && resultado < -600){
        nuevoResultado = "El resultado ha sido NEGATIVO," + "\n" +  "se tiene que pagar" + - resultado + "Euros" + "\n" + "El pago se pasara en 5 cuotas de " + 
        (-resultado / 5) + " Euros" ;
    }
    return nuevoResultado;
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
         double redondeado2 = (double)Math.round(valor*100d) / 100d;
        return redondeado2 ;
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
        int division = (int)Math.round(dividendo / divisor);
        return division;
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
        double resto = dividendo / divisor;
        double restoDec = (double)Math.round(resto*100d) / 100d;
        return restoDec ;
    }

}
