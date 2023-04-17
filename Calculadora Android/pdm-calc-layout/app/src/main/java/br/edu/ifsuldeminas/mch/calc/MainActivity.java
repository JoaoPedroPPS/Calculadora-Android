package br.edu.ifsuldeminas.mch.calc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    MaterialButton btnPorcento, btnDivisao, btnMulti, btnSoma, btnSubtracao, btnIgual;
    MaterialButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnVirgula;

    private static final String TAG = "ifsuldeminas.mch.calc";
    // private Button btnIgual;
    private TextView textViewResultado;
    private TextView textViewUltimaExpressao;
    private String lastOperator = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        textViewResultado = findViewById(R.id.textViewResultadoID);
        textViewUltimaExpressao = findViewById(R.id.textViewUltimaExpressaoID);

        //btnIgual = findViewById(R.id.buttonIgualID);
        assignId(btnIgual, R.id.buttonIgualID);
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

    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    private boolean isOperator(String value) {
        return value.equals("+") || value.equals("-")
                || value.equals("*") || value.equals("/")|| value.equals("%");
    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = textViewUltimaExpressao.getText().toString();

        if (isOperator(buttonText)) {
            if (lastOperator != null) {
                String novaExpressao = dataToCalculate.substring(0, dataToCalculate.length() - 1) + buttonText;
                textViewUltimaExpressao.setText(novaExpressao);
            } else {
                lastOperator = buttonText;
                textViewUltimaExpressao.setText(dataToCalculate + buttonText);
            }
        } else {
            if (buttonText.equals("C")) {
                lastOperator = null;
                textViewUltimaExpressao.setText("");
                textViewResultado.setText("0");
                return;
            }
            if (buttonText.equals("=")) {
                lastOperator = null;
                textViewUltimaExpressao.setText(textViewResultado.getText());

                return;
            }
            if (buttonText.equals("D")) {
                lastOperator = null;
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            } else {
                lastOperator = null;
                dataToCalculate = dataToCalculate + buttonText;
            }
            textViewUltimaExpressao.setText(dataToCalculate);
            String finalresult = getResult(dataToCalculate);
            if (!finalresult.equals("Err")) {
                textViewResultado.setText(finalresult);
            }
        }

    }

    @SuppressLint("SetTextI18n")
    String getResult(String data){
        Calculable avaliadorExpressao;
        try {
            avaliadorExpressao = new ExpressionBuilder(data).build();

            double resultado = avaliadorExpressao.calculate();
            String resultFinal = Double.toString(resultado);

            //textViewUltimaExpressao.setText(expressao);
            textViewResultado.setText(Double.toString(resultado));
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
