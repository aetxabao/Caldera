/**
 * Clase que gestiona los gastos de campanya de
 * una caldera de comunitaria sin contadores.
 * Permite analizar gastos y hacer una regularizacion
 * informando cuanto se tiene que devolver o
 * cuanto mas tiene que pagar cada vecino.
 * 
 * @author Anas Ejbari
 * @version 1.0
 */
public class Caldera {
    
    //CONSTANTES
            
            // Impuestos:
    private final double IMP_IVA = 0.22;
    private final double IMP_HIDROCARBUROS = 0.20;
    /** El IVA se aplica a todos los gasto. */
    
    /** El impuesto de hidrocarburos solo se aplica al gas, ademas del iva. */
    
            // Conceptos gastos:
    private final char AGUA = 'A';
    private final char LUZ = 'L';
    private final char NADA = 'N';

            // Ningun mes:
    private final int NINGUNO = 0;

            // Periodos:
    private final int PERIODO_OCTUBRE_DICIEMBRE = 1;
    private final int PERIODO_ENERO_MARZO = 2;
    private final int PERIODO_ABRIL_JUNIO = 3;
    private final int PERIODO_JULIO_SEPTIEMBRE = 4;

    
    //VARIABLES DE CLASE (PROPIEDADES/ATRIBUTOS)

            // vecinos y presupuesto:
    private int vecinos;
    private double presupuesto;

            // acumulados:
    private double acumuladoConsumo;
    private double acumuladoMantenimiento;
    private double gastoAgua;
    private double gastoLuz;
            // estadisticas:
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
    
    // Constructores:
    
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
        minPrecio = 0;
        periodoMasMantenimiento = 0;
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
    
    public Caldera(int queVecinos, double quePresupuesto){
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
        double consumo = precio * gas;
        acumuladoConsumo += consumo;
        if (consumo > maxConsumo) {
            maxConsumo = consumo;
            mesMasConsumo = mes;
        }
        if (precio > maxPrecio){
            maxPrecio = precio;
            mesMasCaro = mes;
        }
        if (minPrecio == 0) minPrecio = Double.MAX_VALUE;
        if (precio < minPrecio){
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
        acumuladoMantenimiento += importe;
        if (importe > maxMantenimiento){
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
        if (concepto == AGUA) {
            gastoAgua += importe;
            if (importe > maxGasto){
                maxGasto = importe;
                mesMasGasto = mes;
                conceptoMasGasto = AGUA;
            }
        }
        else{
            gastoLuz += importe;
            if (importe > maxGasto){
                maxGasto = importe;
                mesMasGasto = mes;
                conceptoMasGasto = LUZ;
        }
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
        double impuestosGas = (acumuladoConsumo * IMP_HIDROCARBUROS) + (acumuladoConsumo * IMP_IVA);
        double ivaMantenimiento = (acumuladoMantenimiento * IMP_IVA);
        double ivaAgua = (gastoAgua * IMP_IVA);
        double ivaLuz = (gastoLuz * IMP_IVA);
        double total = presupuesto-acumuladoConsumo-impuestosGas-acumuladoMantenimiento-ivaMantenimiento-gastoAgua-ivaAgua-gastoLuz-ivaLuz;
        double aporteVecino = presupuesto/vecinos;
        double gastoV = -1*(total - presupuesto)/vecinos;
        double resultado = total/vecinos;
        System.out.println("==================\nRESULTADO GLOBAL\n==================");
        System.out.println("Presupuesto:\t"+presupuesto);
        System.out.println("Consumo gas:\t"+redondeo2decimales(acumuladoConsumo));
        System.out.println("Impuestos gas:\t"+redondeo2decimales(impuestosGas));
        System.out.println("Mantenimiento:\t"+redondeo2decimales(acumuladoMantenimiento));
        System.out.println("Iva Manto:\t"+redondeo2decimales(ivaMantenimiento));
        System.out.println("Gasto Agua:\t"+redondeo2decimales(gastoAgua));
        System.out.println("Iva Agua:\t"+redondeo2decimales(ivaAgua));
        System.out.println("Gasto Luz:\t"+redondeo2decimales(gastoLuz));
        System.out.println("Iva Luz:\t"+redondeo2decimales(ivaLuz));
        System.out.println("------------------\nTOTAL: " +redondeo2decimales(total)+" Euros.\n------------------");
        System.out.println("==================\nRESULTADO X VECINO\n==================");
        System.out.println("Vecinos:\t"+vecinos);
        System.out.println("Presupuesto:\t"+redondeo2decimales(aporteVecino));
        System.out.println("Gasto v.:\t"+redondeo2decimales(gastoV));
        System.out.println("Resultado:\t"+redondeo2decimales(resultado));
        System.out.println("------------------");
        System.out.println(analisisResultado(resultado));
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
        System.out.println("==================\nESTADISTICAS\n==================");
        System.out.println("Max. consumo:\t"+getNombreMes(mesMasConsumo)+"\t"+ redondeo2decimales(maxConsumo));
        System.out.println("Mes mas caro:\t"+getNombreMes(mesMasCaro)+"\t"+ maxPrecio);
        System.out.println("Mes mas barato:\t"+getNombreMes(mesMasBarato)+"\t"+ minPrecio);
        System.out.println("Mayor gasto en:\t"+getNombreMes(mesMasGasto)+"\t"+ redondeo2decimales(maxGasto) + "\t" + getNombreConcepto(conceptoMasGasto) );
        System.out.println("P. mas manto.:\t"+getNombrePeriodo(periodoMasMantenimiento)+"\t"+ redondeo2decimales(maxMantenimiento));
    }

    /**
     * Devuelve el nombre del mes asociado a su valor numerico
     * 
     * @param numMes Numero del mes del 1 al 12, ej. 1
     * @return Nombre del mes, ej. ENERO
     */
    public String getNombreMes(int numMes) {
        // getNombreMes:
        switch(numMes){
            case 1: return "ENERO";
            case 2: return "FEBRERO";
            case 3: return "MARZO";
            case 4: return "ABRIL";
            case 5: return "MAYO";
            case 6: return "JUNIO";
            case 7: return "JULIO";
            case 8: return "AGOSTO";
            case 9: return "SEPTIEMBRE";
            case 10: return "OCTUBRE";
            case 11: return "NOVIEMBRE";
            case 12: return "DICIEMBRE";
            default: return "NINGUNO";
        }

    }

    /**
     * Devuelve el nombre del concepto asociado al caracter
     * 
     * @param concepto Valor caracter, ej. 'L'
     * @return Nombre del concepto, ej. 'LUZ'. Si no es agua o luz devuelve "NADA"
     */
    public String getNombreConcepto(char concepto) {
        if (concepto == AGUA) return "AGUA";
        else if(concepto == LUZ) return "LUZ";
        else return "NADA";
    }

    /**
     * Devuelve el nombre del periodo asociado a su numero
     * 
     * @param numPeriodo Numero de periodo, del 1 al 4, ej. 4
     * @return Nombre del periodo con nombres de los meses separados por guion, ej.
     *         "OCTUBRE-DICIEMBRE". Sino devuelve "NINGUN PERIODO"
     */
    public String getNombrePeriodo(int numPeriodo) {
        // getNombrePeriodo
        switch(numPeriodo){
            case PERIODO_OCTUBRE_DICIEMBRE: return "OCTUBRE-DICIEMBRE";
            case PERIODO_ENERO_MARZO: return "ENERO-MARZO";
            case PERIODO_ABRIL_JUNIO: return "ABRIL-JUNIO";
            case PERIODO_JULIO_SEPTIEMBRE: return "JULIO-SEPTIEMBRE";
            default: return "NINGÚN PERIODO";
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
        // analisisResultado:
        resultado = redondeo2decimales(resultado);
        if (resultado > 0){
            return ("El resultado ha sido POSITIVO,\n se devolvera "+ resultado +" Euros.\nEl pago se realizara en breve en una transferencia.");
        }
        else{
            resultado *= -1;
            if(resultado <= 200){
                return ("El resultado ha sido NEGATIVO,\n se tiene que pagar "+ resultado +" Euros.\nEl pago se pasará en un solo cobro.");
            }
            else if (resultado <= 600){
                int cuotas = divisionEntera(resultado, 200);
                double cobro = restoDivisionEntera(resultado, 200);
                return ("El resultado ha sido NEGATIVO,\nse tiene que pagar "+ resultado +" Euros.\nEl pago se pasara en\n"+cuotas+ 
                "de 200 euros y\notro cobro de "+cobro+" Euros.");
            }
            else{
                return ("El resultado ha sido NEGATIVO,\nse tiene que pagar "+ resultado +" Euros.\nEl pago se pasara en\n5 cuotas de "
                +redondeo2decimales(resultado/5)+" Euros.");
            }
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
        // redondeo2decimales:
        return Math.round(valor*100)/100.0;
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
        // divisionEntera
        int division =Math.round((int)dividendo/divisor);
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
        // restoDivisionEntera
        double resto = dividendo%(double)divisor;
        return Math.round(resto*100)/100.0;
    }

}
