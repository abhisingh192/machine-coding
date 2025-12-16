package org.digital_wallet;

import org.digital_wallet.service.OfferService;
import org.digital_wallet.service.StatementAndOverviewService;
import org.digital_wallet.service.TransferService;
import org.digital_wallet.service.WalletService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WalletService walletService = new WalletService();
        TransferService transferService = new TransferService(walletService);
        StatementAndOverviewService statementAndOverviewService = new StatementAndOverviewService(transferService , walletService);
        OfferService offerService = new OfferService(walletService, transferService);
        walletService.createWallet( "harry", 100);
        walletService.createWallet( "ron", 95.7);
        walletService.createWallet( "hermione", 104);
        walletService.createWallet( "albus", 200);
        walletService.createWallet( "draco", 500);

//        statementAndOverviewService.getOverview();

        transferService.transferMoney("albus", "draco", 30);
        transferService.transferMoney("hermione", "harry", 2);
        transferService.transferMoney("albus", "ron", 5);

//        statementAndOverviewService.getOverview();
//        statementAndOverviewService.getStatement("harry");
//        statementAndOverviewService.getStatement("albus");
        statementAndOverviewService.getOverview();
        offerService.applyOffer();
        statementAndOverviewService.getOverview();



    }
}
