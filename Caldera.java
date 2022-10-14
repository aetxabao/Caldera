/**
 * Clase que gestiona los gastos de campanya de
 * una caldera de comunitaria sin contadores.
 * Permite analizar gastos y hacer una regularizacion
 * informando cuanto se tiene que devolver o
 * cuanto mas tiene que pagar cada vecino.
 * 
 * @author Alberto Navarro
 * @version 1.0
 */
public class Caldera {
    
    // Constantes
    
    //Constantes impuestos
    private final double IMP_IVA = 0.22;
    private final double IMP_HIDROCARBUROS = 0.22;
    
    //Constantes gastos
    private final String AGUA = "A";
    private final String LUZ = "L";
    private final char NADA = 'N';
    
    //Constante Ningún mes
    private final int NINGUNO = 0;
    
    //Constantes periodo
    private final int PERIODO_OCTUBRE_DICIEMBRE = 1;
    private final int PERIODO_ENERO_MARZ0 = 2;
    private final int PERIODO_ABRIL_JUNIO = 3;
    private final int PERIODO_JULIO_SEPTIEMBRE = 4;
    
    //Atributos
    private int vecinos;
    private double presupuesto;
    
    //Atributos gastos
    private double acumuladoConsumo;
    private double acumuladoMantenimiento;
    private double gastoAgua;
    private double gastoLuz;
    
    //Atributos meses
    private int mesMasConsumo;
    private int maxConsumo;
    private int mesMasCaro;
    private int maxCaro;
    private int mesMasBarato;
    private int minPrecio;
    
    //Otros Atributos
    private int periodoMasMantenimiento;
    private double maxMantenimiento;
    
    //Atributos gastos
    private int mesMasGasto;
    private double MaxGasto;
    private double conceptoMasGasto;
    
    
    /** El IVA se aplica a todos los gasto. */
    
    /** El impuesto de hidrocarburos solo se aplica al gas, ademas del iva. */
    
    // TODO: constructores
    
    public Caldera()
    {
        //Atributos meses
        mesMasConsumo = NINGUNO;
        maxConsumo = NINGUNO;
        mesMasCaro = NINGUNO;
        maxCaro = NINGUNO;
        mesMasBarato = NINGUNO;
        minPrecio = NINGUNO;
            
        //Atributos periodo
        periodoMasMantenimiento = NINGUNO;
        
        //Atributo masGasto
        conceptoMasGasto = NADA;
    }
    
        public Caldera(int queVecinos, double quePresupuesto)
    {
        //Atributos con valor pasado
        vecinos = queVecinos;
        presupuesto = quePresupuesto;
        
        //Atributos meses
        mesMasConsumo = NINGUNO;
        maxConsumo = NINGUNO;
        mesMasCaro = NINGUNO;
        maxCaro = NINGUNO;
        mesMasBarato = NINGUNO;
        minPrecio = NINGUNO;
            
        //Atributos periodo
        periodoMasMantenimiento = NINGUNO;
        
        //Atributo masGasto
        conceptoMasGasto = 0;
    }
    
    //Getters
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
    * Obtiene el valor del presupuesto
    * 
    * @return valor del presupuesto, ej. 38638
    */
    public double getPresupuesto()
    {
        return presupuesto;
    }
 
    //Setters
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
    * Fija el valor del presupuesto
    * 
    * @param quePresupuesto Valor del presupuesto, ej. 38638
    */
    public void setPresupuesto(double quePresupuesto)
    {
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
        // TODO: consumo
    }

    /**
     * Gasto de mantenimiento en cada periodo
     * 
     * @param periodo Numero que representa el periodo, ej. OCTUBRE-DICIEMBRE es 1
     * @param importe Valor del gasto de mantenimiento
     */
    public void mantenimiento(int periodo, double importe) {
        // TODO: mantenimiento
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
    }

    /**
     * Devuelve el nombre del mes asociado a su valor numerico
     * 
     * @param numMes Numero del mes del 1 al 12, ej. 1
     * @return Nombre del mes, ej. ENERO
     */
    public String getNombreMes(int numMes) {
        switch (numMes)
        {
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
                return "Enero";
        }        
    }

    /**
     * Devuelve el nombre del concepto asociado al caracter
     * 
     * @param concepto Valor caracter, ej. 'L'
     * @return Nombre del concepto, ej. 'LUZ'. Si no es agua o luz devuelve "NADA"
     */
    public String getNombreConcepto(char concepto) {
        if (concepto == 'L')
        {
            return "LUZ";
        }
        else if (concepto == 'A')
        {
            return "AGUA";
        }
        else
        {
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
        switch (numPeriodo)
        {
            case 1:
                return "OCTUBRE-DICIEMBRE";
            case 2:
                return "ENERO-MARZO";
            case 3:
                return "ABRIL-JUNIO";
            case 4:
                return "JULIO-SEPTIEMBRE";
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
        if (resultado > 0)
        {
            String str = "El resultado ha sido POSITIVO,"+ </br> +"se devolvera"+ resultado +"Euros"
            +</br>+"El pago se realizara en breve en"+</br>+"una transferencia";
        }

        return string;
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
        double redondeoValor = Math.round(valor*100.0)/100.0;
        return redondeoValor;
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
        return 0;
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
        return 0;
    }

}
