import FinanceMe.PiDev.Enteties.Transaction;
import FinanceMe.PiDev.Repository.TransactionRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    private final TransactionRepository transactionRepository;

    public ReportService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public ByteArrayResource generateReport() throws Exception {
        // Load the report template from the classpath
        InputStream reportStream = getClass().getResourceAsStream("/reports/transactions.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Fetch data from the database
        List<Transaction> transactions = transactionRepository.findAll();

        // Convert the data to a JRBeanCollectionDataSource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transactions);

        // Generate the report
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export the report to a PDF file
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        // Create a ByteArrayResource from the byte array
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return resource;
    }

}
