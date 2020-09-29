package com.example.tp3listviewconsecionaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tp3listviewconsecionaria.models.Autos;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
    -CREAR EL PAQUETE MODELS-- ADENTRO DEL MISMO LAS CLASES DEL MODELO(CLASE Mensajes) #-HECHO-#
    -HACER UN LAYOUT LLAMADO ITEM_LISTA_MENSAJES- Y CREAR LA VISTA INDIVIDUAL DE CADA  --COMPLETA--
    COMPONENTE QUE VA A COMPLETAR EL LISTVIEW. incompleta
    -EN LA ACTIVITY_MAIL.XML AGREGAR UN LISTVIEW.
    -CREAR LA CLASE MensajeAdapter en el paquete com,example.Tp3listview
    CON TODOS SUS METODOS Y PROGRAMAR SUS FUNCIONES
    -CREAR LA SEGUNDA PANTALLA DONDE SE VISUALICE EL CONTENIDO COMPLETO DEL MENSAJE INCLUYENDO
    REMITENTE -- EN ESTA PANTALLA AGREGAR UN ACTIONBAR PARA RETORNAR A LA PANTALLA ANTERIOR
    -CREAR LOS MENU DE OPCIONES CON LAS FUNCIONES Q PIDE EL PPRACTICO

    para refresh

    -<androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
    va en build.gradle
    -implementation 'androidx.swiperefreshlayout:swiperefreshlayout:1.0.0'

     */
    private ListView lvAutos;
    private AutoAdapter adaptador;
    private List<Autos> listaAutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("TRENTO Automotores");

        lvAutos = findViewById(R.id.lvAutos);

        listaAutos = new ArrayList<>();

        this.cargarDatos(listaAutos);

        adaptador = new AutoAdapter(listaAutos);

        lvAutos.setAdapter(new AutoAdapter(listaAutos));

        lvAutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, "Click en Auto"+ position, Toast.LENGTH_SHORT).show();
                cargarDetalles(position);
            }
        });

    }

    private void cargarDetalles(int position){
        Autos auto = adaptador.getItem(position);

        Intent intent = new Intent(MainActivity.this, DetalleActivity.class);

        intent.putExtra("E_ID", auto.getId());
        intent.putExtra("E_MODELO", auto.getModelo());
        intent.putExtra("E_KM", auto.getKm());
        intent.putExtra("E_MARCA", auto.getMarca());
        intent.putExtra("E_PRECIO", auto.getPrecio());
        intent.putExtra("E_DESCRIPCION", auto.getDescipcion());
        intent.putExtra("E_IMAGEN", auto.getImagen());

        startActivity(intent);
    }

    private void cargarDatos(List<Autos> listaAutos) {
        listaAutos.add(new Autos(1,"Ford Focus III 2.0 Se Plus At6", 945.000f, 2015, 92.000f, "Santa Rosa", "* Linea nueva caja automatica de 6ta con modo Sport y levas al volante\n" +
                "* Segundo dueño\n" +
                "* 92.000km\n" +
                "* Mantenimiento al dia\n" +
                "* VTV vigente, grabado de Autopartes y vidrios\n" +
                "* 4 cubiertas nuevas\n" +
                "* Tapizado de cuero, control crusero, sensor de lluvia, de luz y de estacionamiento\n" +
                "* Climatizador , frenos ABS, ESP y 8 AirBags\n" +
                "* Techo corredizo\n" +
                "* Titular al dia, transferencia OBLIGATORIA",R.drawable.auto_1));
        listaAutos.add(new Autos(2,"Mercedes-Benz Clase GLE 5.5", 900.222f, 2019, 32.222f, "General Pico", "GLE 63s\n" +
                "2019 patentada 2020\n" +
                "flamante vendo o permuto\n" +
                "soy titular, discrimino IVA. PAGO DOLAR BILLETE", R.drawable.auto_2));
        listaAutos.add(new Autos(3,"Volkswagen Vento 2.0 Tsi Gli 211cv App Connect + Nav", 800.000f, 2017, 13.700f, "Santa Rosa", "Aire acondicionado\n" +
                "Dirección hidráulica\n" +
                "Climatizador automático\n" +
                "4 levanta cristales eléctricos\n" +
                "Cierre centralizado de puertas\n" +
                "Asiento conductor regulable en altura\n" +
                "Faros con regulación automática\n" +
                "Tapizado de cuero\n" +
                "Apertura de baúl", R.drawable.auto_3));
        listaAutos.add(new Autos(4,"Volkswagen Gol Trend 1.6 Pack Ii", 460.000f, 2010, 145.222f, " Pilar - Bs.as", "EXCELENTE ESTADO!!VW Gol Trend 1.6 Pack II\n" +
                "Año 2010 / 145500 Km / Manual / Nafta", R.drawable.auto_4));
        listaAutos.add(new Autos(5,"Chevrolet S10 2.8 Cd 4x4 Ltz Tdci 200cv At", 900.222f, 2020, 0f, "La Pampa", "Camioneta tope de gama.\n" +
                "Excelente estado. Linea nueva 6 \n" +
                "airbags 440 Nm torque, neumaticos\n" +
                " Michelin. 4x4 manual. Excelente estado, \n" +
                "idem 0km", R.drawable.auto_5));
        listaAutos.add(new Autos(6,"Ford Ranger 2.2 Cd Xl Tdci 125cv", 275.000f, 2016, 120.234f, "Santa Rosa", "EXCELENTE ESTADO!!", R.drawable.auto_6));
        listaAutos.add(new Autos(7,"Fiat Uno 1.3 Fire Way", 200.222f, 2008, 198.222f, "Santa Rosa", "El vehículo no posee aire, ni dirección.\n" +
                "- se le realizó cambio de correa de distribución y bomba de agua a los 149 mil km.\n" +
                "- se le realizó cambio de aceite, filtro de aceite, filtro de aire y filtro de combustible a los 149 mil km.\n" +
                "Le funciona absolutamente todo. Detalles a la vista.\n" +
                "Precio charlable.", R.drawable.auto_7));
    }
}