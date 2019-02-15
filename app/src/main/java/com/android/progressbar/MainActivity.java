package com.android.progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ProgressBar progressBarCarregando;
    private int progresso = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBarId);
        progressBarCarregando = findViewById(R.id.progressBarCarregandoId);

        progressBarCarregando.setVisibility(View.GONE);

    }

    public void carregarProgressBar(View view){

        progressBarCarregando.setVisibility(View.VISIBLE);

        /*this.progresso = this.progresso + 10;
        progressBar.setProgress(this.progresso);*/



        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i <= 100; i ++){
                    System.out.println(i);

                    final int progresso = i;

                    /*O runOnUiThread é um método da Activity para executar uma tarefa na UiThread,
                    /* nesse método é verificado se já está na UiThread, se sim a tarefa é executada imediatamente,
                    /*se não, a tarefa é colocada na fila de eventos para ser processada oportunamente.
                    */

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progresso);

                            if (progresso == 100){
                                progressBarCarregando.setVisibility(View.GONE);
                            }
                        }
                    });

                    try {
                        /*Suspende o thread atual para o período de tempo especificado.
                        * Ele que dará o efeito de "carregando" na tela pela quantidade de tempo especificada
                         */
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }
        }).start();

    }
}
