package br.com.juliorgm.pelota;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PartidaActivity extends AppCompatActivity {

    TextView textCronomtro, textNomeTime1, textNomeTime2, textGolTime1, textGolTime2;
    TextView textFaltaTime1, textFaltaTime2, textEscanteioTime1, textEscanteioTime2;
    TextView textAmareloTime1, textAmareloTime2, textVermelhoTime1, textVermelhoTime2;

    Button btNovaPartida;

    boolean statusPartida;//False para finalizada
    String configuracoes[];
    CountDownTimer contador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        carregaCampos();
        configuraPartida();
    }

    private void carregaCampos() {
        statusPartida = true;//inicia a partida
        textCronomtro = findViewById(R.id.txtPartidaTempo);

        textNomeTime1 = findViewById(R.id.txtPartidaTime1);
        textNomeTime2 = findViewById(R.id.txtPartidaTime2);

        textGolTime1 = findViewById(R.id.txtPartidaGolTime1);
        textGolTime2 = findViewById(R.id.txtPartidaGolTime2);

        textFaltaTime1 = findViewById(R.id.txtPartidaFaltaTime1);
        textFaltaTime2 = findViewById(R.id.txtPartidaFaltaTime2);

        textEscanteioTime1 = findViewById(R.id.txtPartidaEscanteioTime1);
        textEscanteioTime2 = findViewById(R.id.txtPartidaEscanteioTime2);

        textAmareloTime1 = findViewById(R.id.txtPartidaAmareloTime1);
        textAmareloTime2 = findViewById(R.id.txtPartidaAmareloTime2);

        textVermelhoTime1 = findViewById(R.id.txtPartidaVermelhoTime1);
        textVermelhoTime2 = findViewById(R.id.txtPartidaVermelhoTime2);

        btNovaPartida = findViewById(R.id.btnPartidaNova);

    }

    private void configuraPartida() {
        Intent intent = getIntent();
        String [] configuracoes = intent.getStringArrayExtra(MainActivity.MAIN_KEY);
        textNomeTime1.setText(configuracoes[0]);
        textNomeTime2.setText(configuracoes[1]);

        int tempo = Integer.parseInt(configuracoes[2]);

        try {
            rolaABolaTempo(60 * 1000 * tempo);
        } catch (Exception e) {
            rolaABolaTempo(60 * 1000 * 10);
        }
    }


    public void rolaABolaTempo(long var){
        contador= new CountDownTimer(var,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutos = (int) (millisUntilFinished/1000/60);
                int segundos = (int)(millisUntilFinished-(minutos*60*1000))/1000;
                String tempo = "Tempo " + minutos + ":" + segundos;
                textCronomtro.setText(tempo);
            }

            @Override
            public void onFinish() {
                finalizarPartida();
            }
        };
        contador.start();
    }

    public String atualizaPlacar(TextView textView){
        int placarAtual = Integer.parseInt(textView.getText().toString()) + 1;
        return String.valueOf(placarAtual);
    }


    public void finalizaPartida(View view){
        contador.onFinish();
    }


    public void finalizarPartida() {
        Toast.makeText(this, "Partida Finalizada", Toast.LENGTH_LONG).show();

        btNovaPartida.setVisibility(View.VISIBLE);
        contador.cancel();
        verificaResultado();
        statusPartida = false;
    }

    public void verificaResultado(){
        int golsTime1 = Integer.parseInt(textGolTime1.getText().toString());
        int golsTime2 = Integer.parseInt(textGolTime2.getText().toString());
        String resultado = "";

        if (golsTime1 > golsTime2)
            resultado = textNomeTime1.getText().toString() + R.string.partida_vence;
        if (golsTime1 < golsTime2)
            resultado = textNomeTime2.getText().toString() + R.string.partida_vence;
        if (golsTime1 == golsTime2)
            resultado = "Empate!";

        textCronomtro.setText(resultado);// aqui podemos implementar um cara ou coroa
    }

    public void terminarPartida(View view){
        finish();
    }

    public void addPonto(View view) {

        switch (view.getId()) {
            case R.id.btnPartidaGolTime1:
                textGolTime1.setText(atualizaPlacar(textGolTime1));
                break;
            case R.id.btnPartidaGolTime2:
                textGolTime2.setText(atualizaPlacar(textGolTime2));
                break;
            case R.id.btnPartidaFaltaTime1:
                textFaltaTime1.setText(atualizaPlacar(textFaltaTime1));
                break;
            case R.id.btnPartidaFaltaTime2:
                textFaltaTime2.setText(atualizaPlacar(textFaltaTime2));
                break;
            case R.id.btnPartidaEscanteioTime1:
                textEscanteioTime1.setText(atualizaPlacar(textEscanteioTime1));
                break;
            case R.id.btnPartidaEscanteioTime2:
                textEscanteioTime2.setText(atualizaPlacar(textEscanteioTime2));
                break;
            case R.id.btnPartidaAmareloTime1:
                textAmareloTime1.setText(atualizaPlacar(textAmareloTime1));
                break;
            case R.id.btnPartidaAmareloTime2:
                textAmareloTime2.setText(atualizaPlacar(textAmareloTime2));
                break;
            case R.id.btnPartidaVermelhoTime1:
                textVermelhoTime1.setText(atualizaPlacar(textVermelhoTime1));
                break;
            case R.id.btnPartidaVermelhoTime2:
                textVermelhoTime2.setText(atualizaPlacar(textVermelhoTime2));
                break;
        }
    }
}
