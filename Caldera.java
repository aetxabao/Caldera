/**
 * Clase que gestiona los gastos de campanya de
 * una caldera de comunitaria sin contadores.
 * Permite analizar gastos y hacer una regularizacion
 * informando cuanto se tiene que devolver o
 * cuanto mas tiene que pagar cada vecino.
 * 
 * @author Asier Taberna Robles
 * @version 1.0
 */
public class Caldera {
    
    // DONE: CONSTANTES 
    
    /** El IVA se aplica a todos los gastos. */
    private final double IMP_IVA = 0.22;
    /** El impuesto de hidrocarburos solo se aplica al gas, ademas del iva. */
    private final double IMP_HIDROCARBUROS = 0.20;    

    // Conceptos gastos
    private final char AGUA = 'A';
    private char LUZ = 'L';
    private char NADA = 'N';
    
    // Ningun mes
    private int NINGUNO = 0;
    // Periodos
    private int PERIODO_OCTUBRE_DICIEMBRE = 1;
    private int PERIODO_ENERO_MARZO = 2;
    private int PERIODO_ABRIL_JUNIO = 3;
    private int PERIODO_JULIO_SEPTIEMBRE = 4;
    // DONE: VARIABLES DE CLASE (PROPIEDADES/ATRIBUTOS)
    
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
    private int periodoMasMantenimiento;
    private double maxMantenimiento;
    private int mesMasGasto;
    private double maxGasto;
    private char conceptoMasGasto;
    // DONE: constructores
    
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
        mesMasCaro = NINGUNO;
        maxPrecio = 0;
        mesMasBarato = NINGUNO;
        periodoMasMantenimiento = NINGUNO;
        maxMantenimiento = 0;
        mesMasGasto = NINGUNO;
        maxGasto = 0;
        conceptoMasGasto = NADA;
    }
        
    /**
     * Obtiene el numero de vecinos
     * 
     * @return numero de vecinos, ej. 48
     */
    public int getVecinos() {
        return vecinos;
    }
    
    /**
     * Fija el numero de vecinos de la comunidad
     * 
     * @param queVecinos numero de vecinos, ej. 48
     */
    public void setVecinos(int queVecinos) {
        vecinos = queVecinos;
    }
    
    /**
     * Obtiene el valor del presupuesto
     * 
     * @return valor del presupuesto, ej. 38638
     */
    public double getPresupuesto() {
        return presupuesto;
    }
    
    /**
     * Fija el valor del presupuesto
     * 
     * @param quePresupuesto Valor del presupuesto, ej. 38638
     */
    public void setPresupuesto(double quePresupuesto) {
        presupuesto = quePresupuesto;
    }
    
    /**
     * Cantidad de gas consumido cada mes al precio de mercado
     * 
     * @param mes    Numero de mes, 1 es enero, 2 febrero, ..., 12 diciembre, ej. 9
     * @param gas    Cantidad de gas consumido en KWh, ej. 15496
     * @param precio Precio en Euros al que se ha conseguido el gas, ej. 0.067668
     */
    public void consumo(int mes, int gas, double precio) {
        // DONE: consumo
        double euros = (double)gas*precio;
        acumuladoConsumo += euros;
        if(maxConsumo < euros) {
            maxConsumo = euros;
            mesMasConsumo = mes;
        } 
        
        if(maxPrecio < precio) {
            maxPrecio = precio;
            mesMasCaro = mes;
        }
        
        //if(minPrecio == 0) {
        //    minPrecio += precio;
        //   mesMasBarato = mes;
        //} else if(minPrecio > precio) {
        //    minPrecio = precio;
        //    mesMasBarato = mes;
        //} 
    }

    /**
     * Gasto de mantenimiento en cada periodo
     * 
     * @param periodo Numero que representa el periodo, ej. OCTUBRE-DICIEMBRE es 1
     * @param importe Valor del gasto de mantenimiento
     */
    public void mantenimiento(int periodo, double importe) {
        // DONE: mantenimiento
        acumuladoMantenimiento += importe;
        if(importe > maxMantenimiento) {
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
        double cantAgua = 0;
        double cantLuz = 0;
        if(concepto == 'A') {
            gastoAgua += importe;
        }
        if(importe > cantAgua) {
            cantAgua = importe;
        }
        
        if(concepto == 'L') {
            gastoLuz += importe;
        }
        if(importe > cantLuz) {
            cantLuz = importe;
        }
        
        if(cantAgua > cantLuz) {
            maxGasto = cantAgua;
            mesMasGasto = mes;
            conceptoMasGasto = 'A';
        } else {
            maxGasto = cantLuz;
            mesMasGasto = mes;
            conceptoMasGasto = 'L';
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
        double ivaAgua = gastoAgua * IMP_IVA;
        double ivaLuz = gastoLuz * IMP_IVA;
        double total = -(- presupuesto + acumuladoConsumo + acumuladoMantenimiento 
             + gastoAgua + ivaAgua + gastoLuz + ivaLuz);
            
        double aporteVecinosGastos = (acumuladoConsumo  + acumuladoMantenimiento
            + gastoAgua + ivaAgua + gastoLuz + ivaLuz) / vecinos;
            
        double aporteVecinosPresupuesto = +presupuesto / vecinos;
        double resultadoGastos = total / vecinos;
        
        System.out.println("=============================" + 
            "\nRESULTADO GLOBAL" + 
            "\nPresupuesto: " + redondeo2decimales(presupuesto) + 
            "\nConsumo de gas: " + redondeo2decimales(acumuladoConsumo) + 
           // "\nImpuestos gas: " + redondeo2decimales(impuestos) +
            "\nMantenimiento: " + redondeo2decimales(acumuladoMantenimiento) +
          //  "\nIVA mantenimiento: " + redondeo2decimales(ivaMantenimiento) + 
            "\nGasto de agua: " + redondeo2decimales(gastoAgua) + 
            "\nIVA agua: " + redondeo2decimales(ivaAgua) + 
            "\nGasto luz: " + redondeo2decimales(gastoLuz) +
            "\nIVA luz: " + redondeo2decimales(ivaLuz) +
            "\n---------------------" +
            "\nTOTAL: " + redondeo2decimales(total) + " Euros" +
            "\n---------------------" + 
            "\n=============================" +
            "\nRESULTADO X VECINO" +
            "\nVecinos: " + vecinos + " " +
            "\nAporte vecino: " + redondeo2decimales(presupuesto / vecinos) +
            "\n Gasto vecino: " + redondeo2decimales(total / vecinos) +
            "\nResultado: " + redondeo2decimales( - (total / vecinos)) +
            "\n----------------------" +
            "\n" + analisisResultado( redondeo2decimales(presupuesto / vecinos) 
                - (acumuladoConsumo + acumuladoConsumo - (IMP_HIDROCARBUROS + IMP_IVA)  + acumuladoMantenimiento 
                + acumuladoMantenimiento * IMP_IVA + gastoLuz + gastoLuz * IMP_IVA + gastoAgua + gastoAgua
                * IMP_IVA) / vecinos) +
            "\n -----------------------"
            );
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
        // DONE: printEstadisticas
        System.out.println("==============================" + 
            "\n ESTADISTICAS" + 
            "\n ===========================" + 
            "\nMax consumo: " + getNombreMes(mesMasConsumo) + " " + redondeo2decimales(maxConsumo) +
            "\nMes más caro: " +getNombreMes(mesMasCaro) + " " + (maxPrecio) +
           // "\nMes más barato: " + getNombreMes(mesMasBarato) + " " + (minPrecio) + 
            "\nMayor gasto en: " + getNombreMes(mesMasGasto) + " " + redondeo2decimales(maxGasto) + " " 
                                                                        + getNombreConcepto(conceptoMasGasto) +
            "\nPeriodo más mantenimiento: " + getNombreMes(periodoMasMantenimiento) + " "
                                                                + redondeo2decimales(maxMantenimiento));
    }

    /**
     * Devuelve el nombre del mes asociado a su valor numerico
     * 
     * @param numMes Numero del mes del 1 al 12, ej. 1
     * @return Nombre del mes, ej. ENERO
     */
    public String getNombreMes(int numMes) {
        // DONE: getNombreMes
        switch (numMes) {
            case 1: 
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
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
        // DONE: getNombreConcepto
        if(concepto == 'L') {
            return "LUZ";
        } else if(concepto == 'A') {
            return "AGUA";
        } else {
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
        // DONE: getNombrePeriodo
        switch(numPeriodo) {
             case 1:
                 return "PERIODO_OCTUBRE_DICIEMBRE";
             case 2:
                 return "PERIODO_ENERO_MARZO";
             case 3:
                 return "PERIODO_ABRIL_JUNIO";
             case 4:
                 return "PERIODO_JULIO_SEPTIEMBRE";
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
     */
    public String analisisResultado(double resultado) {
        // DONE: analisisResultado
        if(resultado >= 0) {
            return "El resultado es positivo, se devolverá " + redondeo2decimales(resultado) +
                "El pago se realizará mediante una transferencia en breves instantes";
        }
        if(resultado <= -200) {
            return "El resultado ha sido negativo, se tiene que pagar " + redondeo2decimales(resultado) +
                "El pago se pagará en un solo cobro";
        } else if(resultado < -600) {
            return "El resultado ha sido negativo, se tiene que pagar " + redondeo2decimales(resultado) +
                "El pago se pagará en 2 cuotas de 200 euros y otro cobro de " + divisionEntera(resultado, 200);
        } else if(resultado > -600) {
            return "El resultado ha sido negativo, se tiene que pagar " + redondeo2decimales(resultado) +
                "El pago se pagatá en 5 cuotas de " + divisionEntera(resultado, 200) + "euros";
        } else {
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
        // DONE: redondeo2decimales
        valor = Math.round(valor*100)/100.0;
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
        // DONE: divisionEntera
        int division = ((int)dividendo / divisor);
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
        // DONE: restoDivisionEntera
        double resto = redondeo2decimales(dividendo % divisor);
        return resto;
    }

}
