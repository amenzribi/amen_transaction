//package FinanceMe.PiDev.Services;
//
//import FinanceMe.PiDev.Enteties.Compte;
//import FinanceMe.PiDev.Enteties.Transaction;
//import FinanceMe.PiDev.Repository.TransactionRepository;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//@Component
//public class Excelgenerate {
//    @Autowired
//    private TransactionRepository transactionRepository;
////
////    public void generateExcelFile() throws IOException {
////        List<Transaction> transactions = transactionRepository.findAll();
////        Workbook workbook = new XSSFWorkbook();
////        Sheet sheet = workbook.createSheet("Transactions");
////        Row headerRow = sheet.createRow(0);
////        headerRow.createCell(0).setCellValue("ID");
////        headerRow.createCell(1).setCellValue("Date");
////        headerRow.createCell(2).setCellValue("Montant");
////        headerRow.createCell(3).setCellValue("Compte destinataire");
////        headerRow.createCell(4).setCellValue("Compte émetteur");
////        headerRow.createCell(5).setCellValue("Type de transaction");
////        headerRow.createCell(6).setCellValue("État");
////        int rowNum = 1;
////        for (Transaction transaction : transactions) {
////            Row row = sheet.createRow(rowNum++);
////            row.createCell(0).setCellValue(transaction.getId());
////            row.createCell(1).setCellValue(transaction.getDate().toString());
////            row.createCell(2).setCellValue(transaction.getMontant());
////            Compte compteDestinataire = transaction.getCompteDestinataire();
////            Compte compteEmetteur = transaction.getCompteEmetteur();
////            row.createCell(3).setCellValue(compteDestinataire.getCompte_id());
////            row.createCell(4).setCellValue(compteEmetteur.getCompte_id());
////            row.createCell(5).setCellValue(transaction.getType_transaction());
////            row.createCell(6).setCellValue(transaction.getEtat().toString());
////        }
////        FileOutputStream outputStream = new FileOutputStream("transactions.xlsx");
////        workbook.write(outputStream);
////        workbook.close();
////    }
//}
