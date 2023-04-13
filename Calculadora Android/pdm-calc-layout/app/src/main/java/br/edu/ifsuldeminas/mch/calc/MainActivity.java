package br.edu.ifsuldeminas.mch.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MaterialButton btnReset, btnDelete;
    MaterialButton btnPorcento, btnDivisao, btnMulti, btnSoma, btnSubtracao;
    MaterialButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnVirgula;

    private static final String TAG = "ifsuldeminas.mch.calc";
    private Button btnIgual;
    private TextView textViewResultado;
    private TextView textViewUltimaExpressao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResultado = findViewById(R.id.textViewResultadoID);
        textViewUltimaExpressao = findViewById(R.id.textViewUltimaExpressaoID);

        btnIgual = findViewById(R.id.buttonIgualID);
        assignId(btnReset, R.id.buttonResetID);
        assignId(btnDelete, R.id.buttonDeleteID);
        assignId(btnPorcento, R.id.buttonPorcentoID);
        assignId(btnDivisao, R.id.buttonDivisaoID);
        assignId(btnMulti, R.id.buttonMultiplicacaoID);
        assignId(btnSoma, R.id.buttonSomaID);
        assignId(btnSubtracao, R.id.buttonSubtracaoID);
        assignId(btn0, R.id.buttonZeroID);
        assignId(btn1, R.id.buttonUmID);
        assignId(btn2, R.id.buttonDoisID);
        assignId(btn3, R.id.buttonTresID);
        assignId(btn4, R.id.buttonQuatroID);
        assignId(btn5, R.id.buttonCincoID);
        assignId(btn6, R.id.buttonSeisID);
        assignId(btn7, R.id.buttonSeteID);
        assignId(btn8, R.id.buttonOitoID);
        assignId(btn9, R.id.buttonNoveID);
        assignId(btnVirgula, R.id.buttonVirgulaID);
/*
        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calculable avaliadorExpressao = null;
                try {
                    String expressao = "5+1+4*2";
                    avaliadorExpressao = new ExpressionBuilder(expressao).build();

                    Double resultado = avaliadorExpressao.calculate();

                    textViewUltimaExpressao.setText(expressao);
                    textViewResultado.setText(resultado.toString());
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        });*/
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = textViewUltimaExpressao.getText().toString();

        if(buttonText.equals("C")){
            textViewUltimaExpressao.setText("");
            textViewResultado.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            textViewUltimaExpressao.setText(textViewResultado.getText());
            return;
        }
        if(buttonText.equals("D")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate + buttonText;
        }
        textViewUltimaExpressao.setText(dataToCalculate);

        String finalresult = getResult(dataToCalculate);

        if (!finalresult.equals("Err")){
            textViewResultado.setText(finalresult);
        }
    }

    String getResult(String data){
        Calculable avaliadorExpressao = null;
        try {
            String expressao = data;
            avaliadorExpressao = new ExpressionBuilder(expressao).build();

            Double resultado = avaliadorExpressao.calculate();
            String resultFinal = resultado.toString();

            //textViewUltimaExpressao.setText(expressao);
            //textViewResultado.setText(resultado.toString());
            if(resultFinal.endsWith(".0")){
                resultFinal = resultFinal.replace(".0", "");
            }

            return resultFinal;
        } catch (Exception e) {
            //Log.d(TAG, e.getMessage());
            return "Err";
        }



    }
}