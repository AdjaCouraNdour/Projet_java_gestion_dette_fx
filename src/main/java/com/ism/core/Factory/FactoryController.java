// package com.ism.core.Factory;

// import com.ism.controllers.ArticleController;
// import com.ism.controllers.ClientController;
// import com.ism.controllers.DetailsController;
// import com.ism.controllers.DetteController;
// import com.ism.controllers.PaiementController;
// import com.ism.controllers.UserController;

// public class FactoryController {

//     private static ClientController clientController ;
//     private static UserController userController;
//     private static ArticleController articleController;
//     private static DetteController detteController;
//     private static PaiementController paiementController;
//     private static DetailsController detaiController ;
 
//     public ClientController getInstanceClientController(){
//         if (clientController==null) {
//             return clientController=new ClientController();
//         }
//         return clientController;
//     }

//     public UserController getInstanceUserController(){
//         if (userController==null) {
//             return userController=new UserController();
//         }
//         return userController;
//     }

//     public ArticleController getInstanceArticleController(){
//         if (articleController==null) {
//             return articleController=new ArticleController();
//         }
//         return articleController;
//     }
//     public DetteController getInstanceDetteController(){
//         if (detteController==null) {
//             return detteController=new DetteController();
//         }
//         return detteController;
//     }
//     public PaiementController getInstancePaiementController(){
//         if (paiementController==null) {
//             return paiementController=new PaiementController();
//         }
//         return paiementController;
//     }
//     public DetailsController getInstanceDetailsController(){
//         if (detaiController==null) {
//             return detaiController=new DetailsController();
//         }
//         return detaiController;
//     }
   
   
// }
