/**
 * class DemoCaldera
 * 
 * @author
 * @version
 */
public class DemoCaldera {

    private Caldera caldera;

    public DemoCaldera() {
        caldera = new Caldera();
    }

    public void iniciar() {
        int vecinos = 48;
        double presupuesto = 38638;
        int[] meses = { 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8 };
        int[] consumoGas = { 15496, 13561, 32423, 82212, 98024, 70623, 71688, 61249, 35286, 14956, 13711, 14459 };
        double[] preciosGas = { 0.067668, 0.086249, 0.101845, 0.105528, 0.127802, 0.100393, 0.132327,
                0.126696, 0.113683, 0.103000, 0.156691, 0.202504 };
        double[] gastosLuz = { 189.03, 271.05, 435.99, 382.32, 502.54, 558.34, 486.63, 679.94, 434.06, 294.65, 189.44,
                239.02 };
        double[] gastosAgua = { 167.01, 202.39, 221.08, 239.75, 265.29, 239.42, 236.45, 225.57, 248.30, 208.77, 195.91,
                197.89 };
        double[] gastosManto = { 912.86, 846.31, 846.31, 1552.10, };
        caldera.setVecinos(vecinos);
        caldera.setPresupuesto(presupuesto);
        int mes, gas;
        double precio, gastoLuz, gastoAgua;
        for (int i = 0; i < 12; i++) {
            mes = meses[i];
            gas = consumoGas[i];
            precio = preciosGas[i];
            caldera.consumo(mes, gas, precio);
            gastoLuz = gastosLuz[i];
            caldera.gasto(mes, 'L', gastoLuz);
            gastoAgua = gastosAgua[i];
            caldera.gasto(mes, 'A', gastoAgua);
        }
        double gastoManto;
        for (int i = 1; i <= 4; i++) {
            gastoManto = gastosManto[i - 1];
            caldera.mantenimiento(i, gastoManto);
        }
    }

    public void printResultados() {
        caldera.printResultados();
    }

    public void printEstadisticas() {
        caldera.printEstadisticas();
    }

    public static void main(String[] args) {
        DemoCaldera demo = new DemoCaldera();
        demo.iniciar();
        demo.printResultados();
        demo.printEstadisticas();
    }

}
