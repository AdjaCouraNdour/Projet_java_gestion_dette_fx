package com.ism.core.Factory;

import com.ism.data.services.list.ArticleService;
import com.ism.data.services.list.ClientService;
import com.ism.data.services.list.DetailsService;
import com.ism.data.services.list.DetteService;
import com.ism.data.services.list.PaiementService;
import com.ism.data.services.list.UserService;

public class FactoryService  {

    private static ClientService clientService ;
    private static UserService userService;
    private static ArticleService articleService;
    private static DetteService detteService;
    private static PaiementService paiementService;
    private static DetailsService detailsService ;
 
    FactoryRepositoryJPA factory = new FactoryRepositoryJPA();

    public ClientService getInstanceClientService(){
        if (clientService==null) {
            return clientService=new ClientService(factory.getInstanceRepoClient());
        }
        return clientService;
    }

    public UserService getInstanceUserService(){
        if (userService==null) {
            return userService=new UserService(factory.getInstanceRepoUser());
        }
        return userService;
    }

    public ArticleService getInstanceArticleService(){
        if (articleService==null) {
            return articleService=new ArticleService(factory.getInstanceRepoArticle());
        }
        return articleService;
    }
    public DetteService getInstanceDetteService(){
        if (detteService==null) {
            return detteService=new DetteService(factory.getInstanceRepoDette());
        }
        return detteService;
    }
    public PaiementService getInstancePaiementService(){
        if (paiementService==null) {
            return paiementService=new PaiementService(factory.getInstanceRepoPaiement());
        }
        return paiementService;
    }
    public DetailsService getInstanceDetailsService(){
        if (detailsService==null) {
            return detailsService=new DetailsService(factory.getInstanceRepoDetails());
        }
        return detailsService;
    }
   
   
}
