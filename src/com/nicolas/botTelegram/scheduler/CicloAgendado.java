package com.nicolas.botTelegram.scheduler;

import com.nicolas.botTelegram.exceptions.FalhaNaRedeException;
import com.nicolas.botTelegram.botOrchestrator.BotOrchestrator;
import com.nicolas.botTelegram.config.AppConfig;

public class CicloAgendado {

    public void rodarCiclo(BotOrchestrator botOrchestrator) throws FalhaNaRedeException {
        try{
            botOrchestrator.executarCiclo(AppConfig.QUERY_GEOPOLITICA_CONFLITO, "Confilto");
            botOrchestrator.executarCiclo(AppConfig.QUERY_GEOPOLITICA_ECONOMIA, "Economia");

        } catch (FalhaNaRedeException e) {
            throw new FalhaNaRedeException("Falha no processo de automação do envio de mensagens");

        }
    }
}
