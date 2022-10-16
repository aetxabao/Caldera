/**
 * Clase que gestiona los gastos de campanya de
 * una caldera de comunitaria sin contadores.
 * Permite analizar gastos y hacer una regularizacion
 * informando cuanto se tiene que devolver o
 * cuanto mas tiene que pagar cada vecino.
 * 
 * @author José Ángel Ramírez Moreno
 * @version 1.0
 */
public class Caldera {
    /** Aquí estan las constantes. */
    private final double IMP_IVA = 0.22;
    private final double IMP_HIDROCARBUROS = 0.20;
    
    private final char AGUA = 'A';
    private final char LUZ = 'L';
    private final char NADA = 'N';
    
    private final int NINGUNO = 0;
    private final int PERIODO_OCTUBRE_DICIEMBRE = 1;
    private final int PERIODO_ENERO_MARZO = 2;
    private final int PERIODO_ABRIL_JUNIO = 3;
    private final int PERIODO_JULIO_SEPTIEMBRE = 4;
    
    /** Aquí estan los atributos. */
    private int vecinos;
    private double presupuesto;
    
    private double acumuladoConsumo;
    private double acumuladoMantenimiento;
    private double gastoAgua;
    private double gastoLuz;
    
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
    
    /**
     * Constructor de la clase caldera.
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
     * Constructor de la clase Caldera. Cambia el valor de vecinos y presupuesto.
     */
    public Caldera(int queVecinos, int quePresupuesto){
        vecinos = queVecinos;
        presupuesto = quePresupuesto;
        acumuladoConsumo = 0;
        acumuladoMantenimiento = 0;
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
     * Fija el valor del presupuesto.
     * Se usa el parámetro quePresupuesto.
     */
    public void setPresupuesto (double quePresupuesto){
        presupuesto = quePresupuesto;
    }

    /**
     * Retorna el valor del presupuesto.
     */
    public double getPresupuesto(){
        return presupuesto;
    }

    /**
     * Fija el numero de vecinos de la comunidad.
     * Se usa el parámetro queVecinos.
     */
    public void setVecinos(int queVecinos){
        vecinos = queVecinos;
    }

    /**
     * Retorna el numero de vecinos.
     */
    public int getVecinos(){
        return vecinos;
    }

    /**
     * Cantidad de gas consumido cada mes al precio de mercado
     * Se usan parámetros para darle valor a los meses, la cantidad de gas y el precio de este.
     */
    public void consumo(int mes, int gas, double precio) {
        double valorGas = (double) gas * precio;
        acumuladoConsumo = valorGas + acumuladoConsumo;
        if (maxConsumo < valorGas){
            maxConsumo = valorGas;
            mesMasConsumo = mes;
        }
        if (maxPrecio < precio){
            maxPrecio = precio;
            mesMasCaro = mes;
        }
        if (minPrecio == 0){
            minPrecio = precio;
            mesMasBarato = mes;
        }else if(minPrecio > precio){
            minPrecio = precio;
            mesMasBarato = mes;
        }
    }

    /**
     * Gasto de mantenimiento en cada periodo
     * Se usan parámetros para el periodo de meses que transcurre y el importe del mantenimiento en ese tiempo.
     */
    public void mantenimiento(int periodo, double importe) {
        acumuladoMantenimiento = acumuladoMantenimiento + importe;
        if(maxMantenimiento < importe){
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
        if (concepto == 'A'){
            gastoAgua = gastoAgua + importe;
        }
        if (concepto == 'L'){
            gastoLuz = gastoLuz + importe;
        }
        if (importe > maxGasto){
            maxGasto = importe;
            mesMasGasto = mes;
            conceptoMasGasto = concepto;
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
     * Primeramente se realizan los cálculos de cada uno de los valores de los gastos e impuestos asociados a los
     * valores que queremos representar en la impresión.
     * Tras realizar los cálculos se llama al método redondeo2decimales() para evitar números decimales muy largos de representar.
     * Por último se representa la impresión.
     */
    public void printResultados() {
        double impuestosGas = acumuladoConsumo * IMP_IVA + acumuladoConsumo 
        * IMP_HIDROCARBUROS;
        double impuestosMantenimiento = acumuladoMantenimiento * IMP_IVA;
        double impuestosAgua = gastoAgua * IMP_IVA;
        double impuestosLuz = gastoLuz * IMP_IVA;
        double totalGasto = presupuesto - acumuladoConsumo - impuestosGas 
        - acumuladoMantenimiento - impuestosMantenimiento 
        - gastoAgua - impuestosAgua 
        - gastoLuz - impuestosLuz;
        double aporteVecino = presupuesto / vecinos;
        double gastoVecino = (presupuesto + acumuladoConsumo + impuestosGas 
        + acumuladoMantenimiento 
        + impuestosMantenimiento 
        + gastoAgua + impuestosAgua 
        + gastoLuz + impuestosLuz) / vecinos;
        double resultadoAporte = totalGasto / vecinos;

        
        System.out.println("========================"+ "\n" +"    RESULTADO GLOBAL    "
        +"\n"+ "========================"+ 
        "\n" + "Presupuesto:   " + presupuesto + 
        "\n" + "Consumo gas:   " + redondeo2decimales(acumuladoConsumo) +
        "\n" + "Impuestos g.:  " + redondeo2decimales(impuestosGas) +
        "\n" + "Mantenimiento: " + redondeo2decimales(acumuladoMantenimiento) +
        "\n" + "Iva manto.:    " + redondeo2decimales(impuestosMantenimiento) +
        "\n" + "Gasto Agua:    " + redondeo2decimales(gastoAgua) +
        "\n" + "Iva agua:      " + redondeo2decimales(impuestosAgua) +
        "\n" + "Gasto luz:     " + redondeo2decimales(gastoLuz) +
        "\n" + "Iva luz:       " + redondeo2decimales(impuestosLuz) +
        "\n" + "-------------------------" + 
        "\n" + "TOTAL :        " + redondeo2decimales(totalGasto) +
        "\n" + "-------------------------" + 
        "\n" + "========================" + 
        "\n" + "   RESULTADO X VECINO   " + 
        "\n" + "========================" + 
        "\n" + "Vecinos:    " + vecinos +
        "\n" + "Aporte v.:  " + redondeo2decimales(aporteVecino) +
        "\n" + "Gasto v.:   " + redondeo2decimales(gastoVecino) +
        "\n" + "Resultado:  " + redondeo2decimales(resultadoAporte)+
        "\n" + "-------------------------" +
        "\n" + analisisResultado(resultadoAporte));
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
     * Aprovechando los métodos que asocian los atributos con los valores que queremos imprimir, se llama a los métodos
     * para aplicarlos en el System.out.println.
     */
    public void printEstadisticas() {
        System.out.println( "==========================" + 
        "\n" + "       ESTADISTICAS       " + 
        "\n" + "==========================" + 
        "\n" + "Max. consumo:   " + getNombreMes(mesMasConsumo) +" "+ redondeo2decimales(maxConsumo) +
        "\n" + "Mes mas caro:   " + getNombreMes(mesMasCaro) +" "+ maxPrecio +
        "\n" + "Mes mas barato: " + getNombreMes(mesMasBarato) +" "+ minPrecio+
        "\n" + "Mayor gasto en: " + getNombreMes(mesMasGasto)+ " "+ maxGasto+" "+ getNombreConcepto(conceptoMasGasto) +
        "\n" + "P. mas manto.:  " + getNombrePeriodo(periodoMasMantenimiento)+ " "+ maxMantenimiento );
    }

    /**
     * Devuelve el nombre del mes asociado a su valor numerico
     * 
     * @param numMes Numero del mes del 1 al 12, ej. 1
     * @return Nombre del mes, ej. ENERO
     */
    public String getNombreMes(int numMes) {
        switch(numMes){
            case 1: 
                return "ENERO";
            case 2:
                return "FEBRERO";   
            case 3:
                return "MARZO";
            case 4:
                return "ABRIL";
            case 5:
                return "MAYO";
            case 6:
                return "JUNIO";
            case 7:
                return "JULIO";
            case 8:
                return "AGOSTO";
            case 9:
                return "SEPTIEMBRE";
            case 10:
                return "OCTUBRE";
            case 11:
                return "NOVIEMBRE";
            case 12:  
                return "DICIEMBRE";
            default:
                return "NINGUNO";
        }        
    }

    /**
     * Devuelve el nombre del concepto asociado al caracter
     * 
     * @param concepto Valor caracter, ej. 'L'
     * @return Nombre del concepto, ej. 'LUZ'. Si no es agua o luz devuelve "NADA"
     */
    public String getNombreConcepto(char concepto) {
        if (concepto == 'L'){
            return "LUZ";
        }else if (concepto == 'A'){
            return "AGUA";
        }else{
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
        switch (numPeriodo){
            case 1:
                return "ENERO-MARZO";
            case 2:
                return "ABRIL-JUNIO";
            case 3:
                return "JULIO-SEPTIEMBRE";
            case 4:
                return "OCTUBRE-DICIEMBRE";
            default:
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
     *         
     *         En este método calculamos el dinero que hay que devolver 
     *         o recibir dependiendo de lo que se haya consumido. Se calculan
     *         las cuotas y se envía un mensaje que comenta el resultado del
     *         análisis.
     */
    public String analisisResultado(double resultado) {
        double resto = 0;
        int cuotas = 0;
        String mensaje = "NINGUNO";
        if (resultado >= -200 && resultado < 0){
            resultado = -(resultado);
            resultado = redondeo2decimales(resultado);
            mensaje = ("El resultado ha sido NEGATIVO,"+"\n"+
            "se tiene que pagar "+ resultado +" Euros."+ "\n" +
            "El pago se pasara en un solo cobro");
        }else if (resultado<-200 && resultado>=-600){
            resto = resultado%200;
            cuotas = (int) resultado/200;
            resultado = -(resultado);
            resultado = redondeo2decimales(resultado);
            mensaje = ("El resultado ha sido NEGATIVO,"+"\n"+
            "se tiene que pagar "+ resultado +" Euros."+ "\n" +
            "El pago se pasara en "+ cuotas+ " cuotas de 200 Euros y otro cobro de" + resto);
        }else if (resultado < -600){
            double dineroCuotas = -(resultado / 5);
            resultado = redondeo2decimales(resultado);
            dineroCuotas = redondeo2decimales(dineroCuotas);
            mensaje = ("El resultado ha sido NEGATIVO,"+ "\n" +
            "se tiene que pagar "+ resultado+" Euros." + "\n" +
            "El pago se pasara en" + "\n" +
            "5 cuotas de " + dineroCuotas + " Euros.");
        }else if (resultado >= 0){
             mensaje = ("El resultado ha sido POSITIVO,"+ "\n" +
             "se devolvera "+ resultado +"Euros."+ "\n" +
             "El pago se realizara en breve en" + "\n" +
             "una transferencia.");
        }
        return mensaje;
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
     *         
     * Se decide redondear con la librería Math los resultados.
     */
    public double redondeo2decimales(double valor) {
        valor = (double)Math.round(valor * 100d)/100;
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
     *         
     * Se añade el int delante del return para informar de que el resultado lo
     * queremos en números enteros.
     */
    public int divisionEntera(double dividendo, int divisor) {
        return (int)dividendo/divisor;
    }

    /**
     * Divide un numero decimal entre un numero entero y devuelve el resto con
     * decimales.
     * 
     * @param dividendo Numero con decimales que se divide, ej. 647.55
     * @param divisor   Numero entero que divide, ej. 200
     * @return Resto con decimales, ej. 47.55
     * 
     * Marcamos que el valor del divisor lo queremos en double para que el 
     * resultado sea decimal.
     */
    public double restoDivisionEntera(double dividendo, int divisor) {
        return dividendo/(double)divisor;
    }

}
