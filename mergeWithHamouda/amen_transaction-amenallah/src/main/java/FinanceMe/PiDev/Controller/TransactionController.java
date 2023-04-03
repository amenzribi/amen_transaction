
package FinanceMe.PiDev.Controller;

import FinanceMe.PiDev.DTO.DepositRequest;
import FinanceMe.PiDev.DTO.TransferRequest;
import FinanceMe.PiDev.DTO.ValidationRequest;
import FinanceMe.PiDev.DTO.WithdrawRequest;
import FinanceMe.PiDev.Enteties.Compte;
import FinanceMe.PiDev.Enteties.Transaction;
import FinanceMe.PiDev.Repository.CompteRepository;
import FinanceMe.PiDev.Repository.TransactionRepository;
import FinanceMe.PiDev.Repository.UserRepository;
import FinanceMe.PiDev.Services.TransactionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

//import net.sf.jasperreports.engine.JRDataSource;
//import net.sf.jasperreports.engine.JREmptyDataSource;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
////import net.sf.jasperreports.engine.data.JdbcDataSource;

@AllArgsConstructor
@NoArgsConstructor

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    private UserRepository userRepository;
    @Autowired
    TransactionRepository transactionRepository;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;

    }

    /*
    @PostMapping("/depot")
    public ResponseEntity<?> depot(@RequestParam Long compteDestinataire, @RequestParam float montant , @RequestParam String type_transaction) {
        try {
            transactionService.depot(compteDestinataire, montant,type_transaction);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/validation")
    public ResponseEntity<String> validerTransaction(@RequestParam Long transactionId, @RequestParam String codeValidation) {
        try {
            transactionService.validerTransaction(transactionId, codeValidation);
            return ResponseEntity.ok("Transaction validée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    origin code
     */
//@GetMapping("excel")
//public ResponseEntity<byte[]> generateExcelFile() throws IOException {
//    List<Transaction> transactions = transactionRepository.findAll();
//    Workbook workbook = new XSSFWorkbook();
//    Sheet sheet = workbook.createSheet("Transactions");
//    Row headerRow = sheet.createRow(0);
//    headerRow.createCell(0).setCellValue("ID");
//    headerRow.createCell(1).setCellValue("Date");
//    headerRow.createCell(2).setCellValue("Montant");
//    headerRow.createCell(3).setCellValue("Compte destinataire");
//    headerRow.createCell(4).setCellValue("Compte émetteur");
//    headerRow.createCell(5).setCellValue("Type de transaction");
//    headerRow.createCell(6).setCellValue("État");
//    int rowNum = 1;
//    for (Transaction transaction : transactions) {
//        Row row = sheet.createRow(rowNum++);
//        row.createCell(0).setCellValue(transaction.getId());
//        row.createCell(1).setCellValue(transaction.getDate().toString());
//        row.createCell(2).setCellValue(transaction.getMontant());
//        Compte compteDestinataire = transaction.getCompteDestinataire();
//        Compte compteEmetteur = transaction.getCompteEmetteur();
//        row.createCell(3).setCellValue(compteDestinataire.getCompte_id());
//        row.createCell(4).setCellValue(compteEmetteur.getCompte_id());
//        row.createCell(5).setCellValue(transaction.getType_transaction());
//        row.createCell(6).setCellValue(transaction.getEtat().toString());
//    }
//    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//    workbook.write(outputStream);
//    workbook.close();
//    byte[] bytes = outputStream.toByteArray();
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//    headers.setContentDispositionFormData("attachment", "transactions.xlsx");
//    headers.setContentLength(bytes.length);
//    return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
//}


    @PostMapping("/deposit")
    public ResponseEntity<?> depot(@RequestBody DepositRequest depositRequest) {
        try {
            transactionService.depot(depositRequest.getCompteDestinataire(), depositRequest.getMontant(), "Deposit");
            return ResponseEntity.ok("Transaction created successfully and waiting for validation.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validerTransaction(@RequestBody ValidationRequest validationRequest) {
        try {
            transactionService.validerTransaction( validationRequest.getValidationCode());
            return ResponseEntity.ok("Transaction validated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping("/withdraw")
    public ResponseEntity<?> retrait(@RequestBody WithdrawRequest withdrawRequest ) {
        try {
            transactionService.retrait(withdrawRequest.getCompteDestinataire(), withdrawRequest.getMontant(), "Withdraw");
            return ResponseEntity.ok("Transaction created successfully and waiting for validation.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping("/transfert")
    public ResponseEntity<?> transfert(@RequestBody TransferRequest transferRequest){//@RequestParam Long compteEmetteur, @RequestParam Long compteDestinataire, @RequestParam float montant , @RequestParam String type_transaction) {
        try {
            transactionService.transfert(transferRequest.getCompteEmetteur(), transferRequest.getCompteDestinataire(), transferRequest.getMontant() , "Transfer");
            return ResponseEntity.ok("Transaction created successfully and waiting for validation.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




    @GetMapping("/all_transactions")
    public ResponseEntity<List<Transaction>> findAllTransaction() {
        List<Transaction> transactions = transactionService.findAllTransaction();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Transaction> findTransactionById(@PathVariable("id") Long id) {
        Transaction transaction = transactionService.findTransactionById(id);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransactionById(@PathVariable("id") Long id) {
        transactionService.deleteTransactionById(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

//    @GetMapping("/transactionschart")
//    public String transactions(Model model) {
//        model.addAttribute("message", "Hello World!");
//        List<Transaction> transactions = transactionRepository.findAll();
//        List<String> dates = new ArrayList<>();
//        List<Float> montants = new ArrayList<>();
//        List<String> types = new ArrayList<>();
//        for (Transaction transaction : transactions) {
//            dates.add(transaction.getDate().toString());
//            montants.add(transaction.getMontant());
//            types.add(transaction.getType_transaction());
//        }
//        model.addAttribute("dates", dates);
//        model.addAttribute("montants", montants);
//        model.addAttribute("types", types);
//        return "transactions";
//    }


//    @Autowired
//    private DataSource dataSource;
//
//    @GetMapping("/transaction-report")
//    @ResponseBody
//    public byte[] generateTransactionReport() throws JRException {
//
//        // Récupération des transactions validées dans les 30 derniers jours
//        List<Transaction> transactions = getValidatedTransactionsLast30Days();
//
//        // Création d'une source de données pour les transactions
//        JRDataSource dataSource = new JREmptyDataSource();
//
//        if (!transactions.isEmpty()) {
//            JdbcDataSource jdbcDataSource = new JdbcDataSource(this.dataSource);
//            jdbcDataSource.executeQuery("SELECT * FROM Transaction WHERE etat = 'VALIDE' AND date >= DATE_SUB(NOW(), INTERVAL 30 DAY)");
//            dataSource = jdbcDataSource;
//        }
//
//        // Chargement du fichier JRXML depuis le classpath
//        Resource resource = new ClassPathResource("transaction-report.jrxml");
//        InputStream reportInputStream = resource.getInputStream();
//
//        // Compilation du fichier JRXML en JasperReport
//        JasperReport jasperReport = JasperCompileManager.compileReport(reportInputStream);
//
//        // Paramètres pour le rapport
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("dataSource", dataSource);
//
//        // Génération du rapport au format PDF
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
//
//        return pdfBytes;
//    }
//
//    private List<Transaction> getValidatedTransactionsLast30Days() {
//        // Code pour récupérer les transactions validées dans les 30 derniers jours depuis votre base de données
//    }


//    @GetMapping("/reports")
//    public ResponseEntity<byte[]> generateTransactionReport() throws Exception {
//        byte[] report = reportService.generateReport();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_PDF);
//        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.pdf");
//        headers.setContentLength(report.length);
//
//        return new ResponseEntity<>(report, headers, HttpStatus.OK);
//    }

//    @GetMapping("/reports")
//    public ResponseEntity<String> generateTransactionReport() throws Exception {
//        byte[] report = reportService.generateReport();
//        String fileName = "transactions.pdf";
//        String directoryPath = "C:\\Users\\zribi\\Desktop\\PiTrans\\amen_transaction-master"; // replace with the actual path to your directory
//
//        Path filePath = Paths.get(directoryPath, fileName);
//        Files.write(filePath, report);
//
//        String message = "Report downloaded to " + filePath.toString();
//        return ResponseEntity.ok(message);
//    }
//@GetMapping("/reports")
//public ResponseEntity<byte[]> generateTransactionReport() throws Exception {
//    ByteArrayResource resource = reportService.generateReport();
//
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_PDF);
//    headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.pdf");
//    headers.setContentLength(resource.contentLength());
//
//    return ResponseEntity.ok()
//            .headers(headers)
//            .contentLength(resource.contentLength())
//            .contentType(MediaType.APPLICATION_PDF)
//            .body(resource.getByteArray());
//}

//  @GetMapping("/reports")
//  public ResponseEntity<byte[]> generateTransactionReport() throws Exception {
//      byte[] report = reportService.generateReport();
//
//      HttpHeaders headers = new HttpHeaders();
//      headers.setContentType(MediaType.APPLICATION_PDF);
//      headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.pdf");
//      headers.setContentLength(report.length);
//
//      return new ResponseEntity<>(report, headers, HttpStatus.OK);
//  }

//    public ResponseEntity<Resource> generateTransactionsReport() throws Exception {
//        // Fetch all transactions from TransactionService
//        List<Transaction> transactions = transactionService.findAllTransaction();
//
//        // Generate report using ReportService
//        String pdfFile = reportService.generateReport(transactions);
//
//        // Read PDF file into a ByteArrayResource and return it as a ResponseEntity
//        // Read PDF file into a byte array and return it as a ResponseEntity
//        // Read PDF file into a byte array and return it as a ResponseEntity<Resource>
//        // Read PDF file into a byte array and return it as a ResponseEntity<Resource>
//        File file = new File(pdfFile);
//        byte[] bytes = Files.readAllBytes(file.toPath());
//        ByteArrayResource resource = new ByteArrayResource(bytes);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=transactions.pdf");
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(bytes.length)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(resource);
//
//    }




}
