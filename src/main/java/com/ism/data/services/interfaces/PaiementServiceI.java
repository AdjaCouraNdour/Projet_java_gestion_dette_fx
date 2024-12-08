package com.ism.data.services.interfaces;

import java.util.List;

import com.ism.core.Services.Service;
import com.ism.data.entities.Paiement;

public interface PaiementServiceI extends Service<Paiement>{
        public List<Paiement> getPaiementByClientId (int clientId) ;

}
