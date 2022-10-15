/**
 * Clase que gestiona los gastos de campanya de
 * una caldera de comunitaria sin contadores.
 * Permite analizar gastos y hacer una regularizacion
 * informando cuanto se tiene que devolver o
 * cuanto mas tiene que pagar cada vecino.
 * 
 * @author Gerard Joshua Salazar Obando
 * @version 1.0
 */
public class Caldera {
    
    // TODO: CONSTANTES
    
    /** El IVA se aplica a todos los gasto. */
    
    /** El impuesto de hidrocarburos solo se aplica al gas, ademas del iva. */
    
    private final double IMP_IVA = 0.22;
    private final double IMP_HIDROCARBUROS = 0.20;
    
    // Conceptos gastos
    
    private final String AGUA = "A";
    private final String LUZ = "L";
    private final String NADA = "N";

    // Ningun mes
    
    private final int NINGUNO = 0;

    // Periodos
    
    private final int PERIODO_OCTUBRE_DICIEMBRE = 1;
    private final int PERIODO_ENERO_MARZO = 2;
    private final int PERIODO_ABRIL_JUNIO = 3;
    private final int PERIODO_JULIO_SEPTIEMBRE = 4;
    
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
    private int mesMasBarato;
    private double maxPrecio;
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
    
    public void CalderaProyecto()
    {
        vecinos = 0;
        presupuesto = 0;
        acumuladoConsumo = 0;
        acumuladoMantenimiento = 0;
        gastoAgua = 0;
        gastoLuz = 0;
        mesMasConsumo = NINGUNO;
        maxConsumo = 0;
        mesMasCaro = NINGUNO;
        mesMasBarato = NINGUNO;
        maxPrecio = NINGUNO;
        minPrecio = NINGUNO;
        periodoMasMantenimiento = NINGUNO;
        maxMantenimiento = 0;
        mesMasGasto = NINGUNO;
        maxGasto = 0;
        conceptoMasGasto = ' ';
    }

    /**
     * Constructor de la clase Caldera. Inicializa los atributos.
     * 
     * @param queVecinos     Numero de vecinos que conforman la comunidad
     * @param quePresupuesto Presupuesto inicial con el que se pretende afrontar los
     *                       gastos
     */
    
    public void CalderaProyecto(int queVecinos, double quePresupuesto)
    {
        vecinos = queVecinos;
        presupuesto = quePresupuesto;
        acumuladoConsumo = 0;
        acumuladoMantenimiento = 0;
        gastoAgua = 0;
        gastoLuz = 0;
        mesMasConsumo = NINGUNO;
        maxConsumo = 0;
        mesMasCaro = NINGUNO;
        mesMasBarato = NINGUNO;
        maxPrecio = 0;
        minPrecio = 0;
        periodoMasMantenimiento = NINGUNO;
        maxMantenimiento = 0;
        mesMasGasto = NINGUNO;
        maxGasto = 0;
        conceptoMasGasto = ' ';
    }

    
    // TODO: getters y setters
    
    /**
     * Fija el valor del presupuesto
     * 
     * @param quePresupuesto Valor del presupuesto, ej. 38638
     */
    
    public void setPresupuesto(double quePresupuesto)
    {
        presupuesto = quePresupuesto;
    }

    /**
     * Obtiene el valor del presupuesto
     * 
     * @return valor del presupuesto, ej. 38638
     */
    
    public double getPresupuesto()
    {
        return presupuesto;
    }

    /**
     * Fija el numero de vecinos de la comunidad
     * 
     * @param queVecinos numero de vecinos, ej. 48
     */
    
    public void setVecinos(int queVecinos)
    {
        vecinos = queVecinos;
    }

    /**
     * Obtiene el numero de vecinos
     * 
     * @return numero de vecinos, ej. 48
     */
    
    public int getVecinos()
    {
        return vecinos;
    }
    
    /**
     * Cantidad de gas consumido cada mes al precio de mercado
     * 
     * @param mes    Numero de mes, 1 es enero, 2 febrero, ..., 12 diciembre, ej. 9
     * @param gas    Cantidad de gas consumido en KWh, ej. 15496
     * @param precio Precio en Euros al que se ha conseguido el gas, ej. 0.067668
     */
    public void consumo(int mes, int gas, double precio) 
    {
        double consumo = (double)gas * precio;
        acumuladoConsumo = acumuladoConsumo + consumo;
        
        if(consumo > maxConsumo){
            maxConsumo = consumo;
            mesMasConsumo = mes;
        }
        if(minPrecio == 0){
            minPrecio = 9999999;
        }
        if(precio > maxPrecio){
            maxPrecio = precio;
            mesMasCaro = mes;
        }if(precio < minPrecio){
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
    public void mantenimiento(int periodo, double importe) 
    {
        acumuladoMantenimiento += importe;
        if (importe > maxMantenimiento){
            periodoMasMantenimiento = periodo;
            maxMantenimiento = importe;
        }
    }

    /**
     * Gasto mensual en concepto de agua o luz.
     * 
     * @param mes      Numero del mes, ej. ENERO es 1
     * @param concepto Agua 'A' o luz 'L'
     * @param importe  Valor del gasto, ej. 189.03
     */
    public void gasto(int mes, char concepto, double importe) 
    {
        // TODO: gasto
        switch(concepto){
            case 'L':
                gastoLuz += importe;
                break;
            case 'A':
                gastoAgua += importe;
                break;
        }
        
        if(importe > maxGasto){
            mesMasGasto = mes;
            maxGasto = importe;
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
     */
    public void printResultados() {
     System.out.println(" ==================");
     System.out.println(" RESULTADO GLOBAL ");
     System.out.println(" ================== ");
     System.out.println(" Presupuesto: " + presupuesto);
     System.out.println(" Consumo gas: " + redondeo2decimales(acumuladoConsumo));
     System.out.println(" Impuestos g.:" + redondeo2decimales(acumuladoConsumo * (IMP_HIDROCARBUROS + IMP_IVA) ));
     System.out.println(" Mantenimiento: " + redondeo2decimales(acumuladoMantenimiento)); 
     System.out.println(" Iva manto.: " + redondeo2decimales(acumuladoMantenimiento * IMP_IVA));
     System.out.println(" Gasto agua: " + redondeo2decimales(gastoAgua));
     System.out.println(" Iva agua: " + redondeo2decimales(gastoAgua * IMP_IVA));
     System.out.println(" Gasto luz: " + redondeo2decimales(gastoLuz));
     System.out.println(" Iva luz: " + redondeo2decimales(gastoLuz * IMP_IVA));
     System.out.println(" ------------------ ");
     System.out.println(" TOTAL : "+ redondeo2decimales(presupuesto - (acumuladoConsumo + acumuladoConsumo * (IMP_HIDROCARBUROS + IMP_IVA) + acumuladoMantenimiento + (acumuladoMantenimiento * IMP_IVA) + gastoLuz + (gastoLuz * IMP_IVA) + gastoAgua + (gastoAgua * IMP_IVA))) +" Euros");
     System.out.println(" ------------------ ");
     System.out.println(" ================== ");
     System.out.println(" RESULTADO X VECINO ");
     System.out.println(" ================== ");
     System.out.println(" Vecinos: " + vecinos);
     System.out.println(" Aporte v.: " + redondeo2decimales(presupuesto / vecinos));
     System.out.println(" Gasto v.: " + redondeo2decimales((acumuladoConsumo + acumuladoConsumo * (IMP_HIDROCARBUROS + IMP_IVA) + acumuladoMantenimiento + acumuladoMantenimiento * IMP_IVA + gastoLuz + gastoLuz * IMP_IVA + gastoAgua + gastoAgua * IMP_IVA) / vecinos));
     System.out.println(" Resultado: " + redondeo2decimales((presupuesto / vecinos) - (acumuladoConsumo + acumuladoConsumo * (IMP_HIDROCARBUROS + IMP_IVA) + acumuladoMantenimiento + acumuladoMantenimiento * IMP_IVA + gastoLuz + gastoLuz * IMP_IVA + gastoAgua + gastoAgua * IMP_IVA) / vecinos));
     System.out.println(" ------------------ ");
     System.out.println(analisisResultado(redondeo2decimales((presupuesto / vecinos) - (acumuladoConsumo + acumuladoConsumo * (IMP_HIDROCARBUROS + IMP_IVA) + acumuladoMantenimiento + acumuladoMantenimiento * IMP_IVA + gastoLuz + gastoLuz * IMP_IVA + gastoAgua + gastoAgua * IMP_IVA) / vecinos)));
     System.out.println(" ------------------ ");
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
    public void printEstadisticas() 
    {
     System.out.println(" ================== ");
     System.out.println(" ESTADISTICAS ");
     System.out.println(" ================== ");
     System.out.println(" Max. consumo: "+ getNombreMes(mesMasConsumo) + " " + redondeo2decimales(maxConsumo));
     System.out.println(" Mes mas caro: " + getNombreMes(mesMasCaro) + " " + maxPrecio);
     System.out.println(" Mes mas barato: " + getNombreMes(mesMasBarato) + " " + minPrecio);
     System.out.println(" Mayor gasto en: " + getNombreMes(mesMasGasto) + " " + redondeo2decimales(maxGasto) + " " + getNombreConcepto(conceptoMasGasto));
     System.out.println(" P. mas manto.: " + getNombrePeriodo(periodoMasMantenimiento) + " " + redondeo2decimales(maxMantenimiento));
     System.out.println(" ------------------ ");
    }

    /**
     * Devuelve el nombre del mes asociado a su valor numerico
     * 
     * @param numMes Numero del mes del 1 al 12, ej. 1
     * @return Nombre del mes, ej. ENERO
     */
    public String getNombreMes(int numMes) {
        String mes = "mes";
        switch(numMes){
            case 1:
                mes = "ENERO";
                break;
            case 2:
                mes = "FEBRERO";
                break;
            case 3:
                mes = "MARZO";
                break;
            case 4:
                mes = "ABRIL";
                break;
            case 5:
                mes = "MAYO";
                break;
            case 6:
                mes = "JUNIO";
                break;
            case 7:
                mes = "JULIO";
                break;
            case 8:
                mes = "AGOSTO";
                break;
            case 9:
                mes = "SEPTIEMBRE";
                break;
            case 10:
                mes = "OCTUBRE";
                break;
            case 11:
                mes = "NOVIEMBRE";
                break;
            case 12:
                mes = "DICIEMBRE";
                break;
        }
        return mes;        
    }

    /**
     * Devuelve el nombre del concepto asociado al caracter
     * 
     * @param concepto Valor caracter, ej. 'L'
     * @return Nombre del concepto, ej. 'LUZ'. Si no es agua o luz devuelve "NADA"
     */
    public String getNombreConcepto(char concepto) {
        if(concepto == 'L'){
            return "LUZ";
        }else if (concepto == 'A'){
            return "AGUA";
        }else return "NADA";        
    }

    /**
     * Devuelve el nombre del periodo asociado a su numero
     * 
     * @param numPeriodo Numero de periodo, del 1 al 4, ej. 4
     * @return Nombre del periodo con nombres de los meses separados por guion, ej.
     *         "OCTUBRE-DICIEMBRE". Sino devuelve "NINGUN PERIODO"
     */
    public String getNombrePeriodo(int numPeriodo) 
    {
        String nomPeriodo = "a";
        switch(numPeriodo){
            case 1:
                nomPeriodo = "PERIODO_OCTUBRE_DICIEMBRE";
                break;
            case 2:
                nomPeriodo = "PERIODO_ENERO_MARZO";
                break;
            case 3:
                nomPeriodo = "PERIODO_ABRIL_JUNIO";
                break;
            case 4:
                nomPeriodo = "PERIODO_JULIO_SEPTIEMBRE";
                break;
            case 5:
                nomPeriodo = "NINGUN PERIODO";
        }
        return nomPeriodo;        
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
    public String analisisResultado(double resultado) 
    {
        String respuesta;
        if(resultado > 0){
            respuesta = "El resultado ha sido POSITIVO se devolverá " + resultado + " Euros \n El pago se realizará en breve con una transferencia";
            return respuesta;
           
            
        }else if(resultado < 0 && redondeo2decimales(resultado * -1)<=200){
            respuesta = "El resultado ha sido NEGATIVO se tiene que pagar " + redondeo2decimales(resultado * -1) +  " Euros \n El pago se pasará en un solo cobro";
            return respuesta;
            
        }else if(resultado < 0 && redondeo2decimales(resultado * -1)<=600){
            String pagoCuota = "";
            if (resultado < 0 && redondeo2decimales(resultado * -1)<=600 && redondeo2decimales(resultado *- 1) > 400)
                {pagoCuota = "\n El pago se pasará en 2 cuotas de 200 Euros y otro cobro de " +  (redondeo2decimales(resultado *-1) -400);}
            else{pagoCuota = "\n El pago se pasará en 1 cuota de 200 Euros y otro cobro de " +  (redondeo2decimales(resultado *-1) -200);}
            respuesta = "El resultado ha sido NEGATIVO se tiene que pagar " + redondeo2decimales(resultado * -1) +  " Euros" + "\n" + pagoCuota;
            return respuesta;
            
            
        }else{
            respuesta = "El resultado ha sido NEGATIVO se tiene que pagar " + redondeo2decimales(resultado * -1) +  " Euros \nEl pago se pasará en 5 cuotas de " + redondeo2decimales((resultado * -1)/5);
            return respuesta;
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
        return (double) Math.round(valor * 100) / 100;
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
        int division = (int)dividendo / divisor;
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
        double restoDivision = dividendo % (double)divisor;
        return restoDivision;
    }

}
