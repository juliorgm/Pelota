package br.com.juliorgm.pelota;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class PartidaActivity extends AppCompatActivity {

    TextView textCronomtro, textTime1,textTime2, textPlacarTime1,textPlacarTime2;
    Button button ;

    boolean statusPartida;//False para finalizada
    String configuracoes[];
    CountDownTimer contador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);
        statusPartida = true;//inicia a partida
        textCronomtro = findViewById(R.id.txtPartidaTempo);
        textTime1 = findViewById(R.id.txtPartidaTime1);
        textTime2 = findViewById(R.id.txtPartidaTime2);
        textPlacarTime1 = findViewById(R.id.txtGolsPrimeiroTime);
        textPlacarTime2 = findViewById(R.id.txtGolsSegundoTime);
        button = findViewById(R.id.btnTerminarPartida);
        Intent intent = getIntent();
        String [] configuracoes = intent.getStringArrayExtra(MainActivity.MAIN_KEY);
        //onfiguracoes = new String[]{"Bola de Treta","RealMatismo","5"};
        textTime1.setText(configuracoes[0]);
        textTime2.setText(configuracoes[1]);
        rolaABolaTempo(60*1000* Integer.parseInt(configuracoes[2]));
    }



    public void rolaABolaTempo(long var){
        contador= new CountDownTimer(var,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutos = (int) (millisUntilFinished/1000/60);
                int segundos = (int)(millisUntilFinished-(minutos*60*1000))/1000;
                textCronomtro.setText("Tempo " + minutos + ":" + segundos) ;
            }

            @Override
            public void onFinish() {
                textCronomtro.setText("ACABOU!!!!");
                statusPartida = false;
            }
        };
        contador.start();
    }

    public String atualizaPlacar(TextView textView){
        int placarAtual = Integer.parseInt(textView.getText().toString());
        placarAtual++;
        return String.valueOf(placarAtual);
    }

    public void golTime1(View view){
        if (statusPartida)
        textPlacarTime1.setText(atualizaPlacar(textPlacarTime1));
    }
    public void golTime2(View view){
        if (statusPartida)
        textPlacarTime2.setText(atualizaPlacar(textPlacarTime2));
    }

    public void finalizaPartida(View view){
        Toast.makeText(this,"Partida Finalizada",Toast.LENGTH_LONG);

        button.setVisibility(View.VISIBLE);

        contador.onFinish();
        contador.cancel();
        verificaResultado();
        statusPartida = false;
    }

    public void verificaResultado(){
        int golsTime1 = Integer.parseInt(textPlacarTime1.getText().toString());
        int golsTime2 = Integer.parseInt(textPlacarTime2.getText().toString());
        if (golsTime1>golsTime2) textCronomtro.setText(textTime1.getText().toString() + " Venceu");
        if (golsTime1<golsTime2) textCronomtro.setText(textTime2.getText().toString() + " Venceu");
        if (golsTime1==golsTime2) textCronomtro.setText("Empate");// aqui podemos implementar um cara ou coroa
    }

    public void terminarPartida(View view){
        finish();
    }


}
