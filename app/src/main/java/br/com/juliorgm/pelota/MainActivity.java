package br.com.juliorgm.pelota;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String MAIN_KEY = "br.com.juliorgm.pelota.main";

    EditText editPrimeiroTime;
    EditText editSegundoTime;
    EditText editTempoPartida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPrimeiroTime=findViewById(R.id.txtPrimeirTime);
        editSegundoTime=findViewById(R.id.txtSegundoTme);
        editTempoPartida=findViewById(R.id.txtTempoPartida);
    }

    public boolean validaCampos(){

        if (editPrimeiroTime.getText().toString().isEmpty()){
            editPrimeiroTime.setError("Informe o nome do primeiro time");
            return false;
        }
        if (editSegundoTime.getText().toString().isEmpty()){
            editSegundoTime.setError("Informe o nome do segundo time");
            return false;
        }
        if (editSegundoTime.getText().toString().equals(editPrimeiroTime.getText().toString())) {
            editPrimeiroTime.setError("Nomes dos times estão iguais");
            editSegundoTime.setError("Nomes dos times estão iguais");
            return false;
        }

        if (editTempoPartida.getText().toString().isEmpty()){
            editTempoPartida.setError("Informe o tempo da partida");
            return false;
        }

        if (Integer.parseInt(editTempoPartida.getText().toString()) > 90) {
            editTempoPartida.setError("tempo máximo de partida é 90");
            return false;
        }
        return true;
    }

    public void iniciarPartida(View view){

        if (!validaCampos()) return;

        String configuracaoPartida[] = {editPrimeiroTime.getText().toString(),
                editSegundoTime.getText().toString(),
                editTempoPartida.getText().toString()};
        Intent intent = new Intent(MainActivity.this,PartidaActivity.class);
        intent.putExtra(MainActivity.MAIN_KEY,configuracaoPartida);
        startActivity(intent);
    }
}
