package classes;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Plata {
    private static int last_id=0;
    private int plataId;
    private String data_platii;
    private float suma;
    private MetodaPlata metodaPlata;
    private StatusPlata statusPlata;
    private String beneficiar;
    private Bilet[] bilete_cump;

    public Plata(){
        plataId = 0;
        data_platii = "";
        suma = 0;
        metodaPlata = null;
        statusPlata = null;
        beneficiar = "";
        bilete_cump = null;
    }

    public Plata(String data, float sum, MetodaPlata metodaP, Bilet[] bilete) {
        plataId = ++last_id;
        data_platii = data;
        suma = sum;
        metodaPlata = metodaP;
        beneficiar = "DAV SRL";
        bilete_cump = bilete;
        statusPlata = StatusPlata.In_asteptare;

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            statusPlata = StatusPlata.Procesata;
            System.out.println("Plata cu numarul " + plataId + " a fost procesata. Multumim!");
            scheduler.shutdown();
        }, 1, TimeUnit.MINUTES);

    }
    
    public void setStatusPlata(StatusPlata statusPlata) {
        this.statusPlata = statusPlata;
    }

    public StatusPlata getStatusPlata() {
        return statusPlata;
    }

}
