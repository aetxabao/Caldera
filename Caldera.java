/**
 * Clase que gestiona los gastos de campanya de
 * una caldera de comunitaria sin contadores.
 * Permite analizar gastos y hacer una regularizacion
 * informando cuanto se tiene que devolver o
 * cuanto mas tiene que pagar cada vecino.
 * 
 * @author Rubén Gurrea
 * @version 1.0
 */
public class Caldera {

    /** El IVA se aplica a todos los gasto. */
    private final double IMP_IVA = 0.22;
    
    /** El impuesto de hidrocarburos solo se aplica al gas, ademas del iva. */
    private final double IMP_HIDROCARBUROS = 0.20;

    // Conceptos gastos
    private final char AGUA = 'A';
    private final char LUZ = 'L';
    private final char NADA = 'N';

    // Ningun mes
    private String NINGUNO = "0";

    // Periodos
    private final char PERIODO_OCTUBRE_DICIEMBRE = '1';
    private final char PERIODO_ENERO_MARZO = '2';
    private final char PERIODO_ABRIL_JUNIO = '3';
    private final char PERIODO_JULIO_SEPTIEMBRE = '4';

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
    private String conceptoMasGasto;
    
    
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
        mesMasConsumo = 0;
        maxConsumo = 0;
        mesMasCaro = 0;
        maxPrecio = 0;
        mesMasBarato = 0;
        minPrecio = 0;
        periodoMasMantenimiento = 0;
        maxMantenimiento = 0;
        mesMasGasto = 0;
        maxGasto = 0;
        conceptoMasGasto = "Nada";
        NINGUNO = "Nada";
    }

    /**
     * Constructor de la clase Caldera. Inicializa los atributos.
     * 
     * @param queVecinos     Numero de vecinos que conforman la comunidad
     * @param quePresupuesto Presupuesto inicial con el que se pretende afrontar los
     *                       gastos
     */
    public Caldera(int queVecinos, double quePresupuesto)
    {
        vecinos = queVecinos;
        presupuesto = quePresupuesto;
        acumuladoConsumo = 0;
        acumuladoMantenimiento = 0;
        gastoAgua = 0;
        gastoLuz = 0;
        mesMasConsumo = 0;
        maxConsumo = 0;
        mesMasCaro = 0;
        maxPrecio = 0;
        mesMasBarato = 0;
        minPrecio = 0;
        periodoMasMantenimiento = 0;
        maxMantenimiento = 0;
        mesMasGasto = 0;
        maxGasto = 0;
        conceptoMasGasto = "Nada";
        NINGUNO = "Nada";
    }

    /**
     * Fija el valor del presupuesto
     * 
     * @param quePresupuesto Valor del presupuesto, ej. 38638
     */
    public void setPresupuesto (double quePresupuesto)
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
    public void setVecinos (int queVecinos)
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
        acumuladoConsumo += precio;
        if (acumuladoConsumo == precio)
        {
            mesMasBarato = mes;
            mesMasCaro = mes;
        }
        if (gas > maxConsumo)
        {
            maxConsumo = gas;
            mesMasConsumo = mes;
        }
        double total€KW = gas / precio;
        redondeo2decimales(total€KW);
        if (total€KW > maxPrecio)
        {
            maxPrecio = total€KW;
            mesMasCaro = mes;
        }
        if (total€KW < minPrecio || minPrecio == 0)
        {
            minPrecio = total€KW;
            mesMasBarato = mes;
        }
        redondeo2decimales(maxPrecio);
        redondeo2decimales(minPrecio);
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
        
        if (acumuladoMantenimiento == importe)
        {
            periodoMasMantenimiento = periodo;
        }
        if (importe > maxMantenimiento || maxMantenimiento == 0)
        {
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
    public void gasto(int mes, char concepto, double importe) 
    {
        if (concepto == 'A')
        {
            gastoAgua += importe;
        }
        else if (concepto == 'L')
        {
            gastoLuz += importe;
        }
        
        if (mesMasGasto == 0)
        {
            mesMasGasto = mes;
            maxGasto = importe;
        }
        
        if (importe > maxGasto)
        {
            maxGasto = importe;
            mesMasGasto = mes;
        }
        
        if (importe == maxGasto && concepto == 'A')
        {
            conceptoMasGasto = "Agua";
        }
        else if (importe == maxGasto && concepto == 'L')
        {
            conceptoMasGasto = "Luz";
        }
        else if (maxGasto == 0)
        {
            conceptoMasGasto = "Nada";
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
    public void printResultados() 
    {
        // TODO: printResultados
        //TODO: redondear los decimales
        double impuestosGas = (acumuladoConsumo * 0.22)*0.20;
        double impuestosManto = acumuladoMantenimiento * 0.22;
        double impuestosAgua = gastoAgua * 0.22;
        double impuestosLuz = gastoLuz * 0.22;
        double total = presupuesto+(acumuladoConsumo-impuestosGas-acumuladoMantenimiento-
        impuestosManto-gastoAgua-impuestosAgua-gastoLuz-impuestosLuz);
        redondeo2decimales(total);
        
        System.out.println("==================");
        System.out.println(" RESULTADO GLOBAL");
        System.out.println("==================");
        System.out.println("Presupuesto: \t" + presupuesto);
        System.out.println("Consumo gas: \t" + redondeo2decimales(acumuladoConsumo));
        System.out.println("Impuestos g.: \t" + redondeo2decimales(impuestosGas));
        System.out.println("Mantenimiento: \t" + redondeo2decimales(acumuladoMantenimiento));
        System.out.println("Iva manto.: \t" + redondeo2decimales(impuestosManto));
        System.out.println("Gasto agua: \t" + redondeo2decimales(gastoAgua));
        System.out.println("Iva agua: \t" + redondeo2decimales(impuestosAgua));
        System.out.println("Gasto luz: \t" + redondeo2decimales(gastoLuz));
        System.out.println("Iva luz: \t" + redondeo2decimales(impuestosLuz));
        System.out.println("------------------");
        System.out.println("TOTAL: " + redondeo2decimales(total) + " Euros");
        System.out.println("------------------");
        System.out.println("==================");
        System.out.println("RESULTADO X VECINO");
        System.out.println("==================");
        System.out.println("Vecinos: \t" + vecinos);
        double aporteIndividual = presupuesto / vecinos;
        System.out.println("Aporte v.: \t" + redondeo2decimales(aporteIndividual));
        double gastoIndividual = (acumuladoConsumo+impuestosGas+acumuladoMantenimiento+
        impuestosManto+gastoAgua+impuestosAgua+gastoLuz+impuestosLuz) / vecinos;
        System.out.println("Gasto v.: \t" + redondeo2decimales(gastoIndividual));
        double resultadoVecino = aporteIndividual - gastoIndividual;
        System.out.println("Resultado: \t" + redondeo2decimales(resultadoVecino));
        System.out.println("------------------");
        analisisResultado(resultadoVecino);
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
        // TODO: printEstadisticas
        System.out.println("==================");
        System.out.println("   ESTADISTICAS");
        System.out.println("==================");
        System.out.println("Max. consumo: \t" + getNombreMes(mesMasConsumo) + "\t" + redondeo2decimales(maxConsumo));
        System.out.println("Mes mas caro: \t" + getNombreMes(mesMasCaro) + "\t" + redondeo2decimales(maxPrecio));
        System.out.println("Mes mas barato: \t" + getNombreMes(mesMasBarato) + "\t" + redondeo2decimales(minPrecio));  
        System.out.println("Mayor gasto en: \t" + getNombreMes(mesMasGasto) + "\t" + redondeo2decimales(maxGasto) + "\t" + conceptoMasGasto);
        System.out.println("P. mas manto.: \t" + getNombrePeriodo(periodoMasMantenimiento) + "\t" + redondeo2decimales(maxMantenimiento));
        System.out.println("------------------");
    }

    /**
     * Devuelve el nombre del mes asociado a su valor numerico
     * 
     * @param numMes Numero del mes del 1 al 12, ej. 1
     * @return Nombre del mes, ej. ENERO
     */
    public String getNombreMes(int numMes) 
    {
        // TODO: getNombreMes
        switch (numMes)
        {
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
    public String getNombreConcepto(char concepto) 
    {
        // TODO: getNombreConcepto
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
        // TODO: getNombrePeriodo
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
    public String analisisResultado(double resultado) 
    {
        // TODO: analisisResultado
        if (resultado > 0)
        {
            System.out.println("El resultado ha sido POSITIVO,");
            System.out.println("han sobrado " + redondeo2decimales(resultado) + " Euros.");
            System.out.println("y se devolvera en breves");
            System.out.println("------------------");
        }
        else if (resultado < 0)
        {
            System.out.println("el resultado ha sido NEGATIVO,");
            System.out.println("se tiene que pagar " + redondeo2decimales(resultado) + " Euros.");
            if (resultado <= -200)
            {
                System.out.println("El pago se pasara en un solo cobro");
            }
            else if (resultado > -200 && resultado <= -600)
            {
                restoDivisionEntera(resultado, 200);
                System.out.println("El pago se pasara en");
                if (divisionEntera (resultado, 200) < 2)
                {
                    resultado -= -200;
                    System.out.println("2 cuotas, una de 200 Euros y");
                    System.out.println("otra de "+redondeo2decimales(resultado)+" Euros");
                    System.out.println("------------------");
                }
                else if (divisionEntera (resultado, 200) == 2)
                {
                    System.out.println("2 cuotas de "+divisionEntera(resultado, 2)+" Euros");
                    System.out.println("------------------");
                }
                else if (divisionEntera (resultado, 200) > 2)
                {
                    System.out.println(divisionEntera(resultado, 200) + " cuotas de"); 
                    double eurosDivididos = resultado / divisionEntera(resultado, 200);
                    System.out.println(redondeo2decimales(eurosDivididos) + " Euros y");
                    System.out.println("otra de "+restoDivisionEntera(resultado, 200)+" Euros");
                    System.out.println("------------------");
                }
            }
            else if (resultado > -600)
            {
                double cuota = resultado / 5;
                redondeo2decimales(cuota);
                System.out.println("5 cuotas de " + cuota + " Euros");
                System.out.println("------------------");
            }
        }
        else if (resultado == 0)
        {
            System.out.println("No ha sobrado ni hay que pagar más,");
            System.out.println("ha sido el dinero justo.");
            System.out.println("------------------");
        }
        return "";
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
    public double redondeo2decimales(double valor) 
    {
        // TODO: redondeo2decimales
        double valorDouble = valor * 100;
        int valorEntero = (int) valorDouble;
        double valorDouble2 = (double) valorEntero;
        double redondeado = valorDouble2 / 100;
        return redondeado;
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
    public int divisionEntera(double dividendo, int divisor) 
    {
        // TODO: divisionEntera
        double divisionDouble = dividendo / divisor;
        int divisionEntero = (int) divisionDouble;
        return divisionEntero;
    }

    /**
     * Divide un numero decimal entre un numero entero y devuelve el resto con
     * decimales.
     * 
     * @param dividendo Numero con decimales que se divide, ej. 647.55
     * @param divisor   Numero entero que divide, ej. 200
     * @return Resto con decimales, ej. 47.55
     */
    public double restoDivisionEntera(double dividendo, int divisor) 
    {
        // TODO: restoDivisionEntera
        double divisionDouble = dividendo / divisor;
        int divisionEntera = (int) divisionDouble;
        int cuotasAPagar = divisor * divisionEntera;
        double restoDivision = dividendo - cuotasAPagar;
        return restoDivision;
    }

}
