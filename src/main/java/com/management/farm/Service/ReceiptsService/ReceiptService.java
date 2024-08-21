package com.management.farm.Service.ReceiptsService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.farm.Model.ReceiptsModel.Receipt;
import com.management.farm.Repository.ReceiptsRepository.ReceiptRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.ByteArrayOutputStream;
import java.io.IOException;



@Service
public class ReceiptService {
    @Autowired
    ReceiptRepository receiptRepository;



        public Receipt createReceipt(String name, int quantity, double unitPrice) {
        double totalPrice = quantity * unitPrice;
        Receipt receipt = new Receipt();
        receipt.setName(name);
        receipt.setDate(LocalDate.now());
        receipt.setQuantity(quantity);
        receipt.setUnitPrice(unitPrice);
        receipt.setTotalPrice(totalPrice);
        return receiptRepository.save(receipt);


    }

    public byte[] generatePdfReceipt(Receipt receipt) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 700);

                contentStream.showText("Receipt");
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("Name: " + receipt.getName());
                contentStream.newLine();
                contentStream.showText("Date: " + receipt.getDate());
                contentStream.newLine();
                contentStream.showText("Quantity: " + receipt.getQuantity());
                contentStream.newLine();
                contentStream.showText("Unit Price: " + receipt.getUnitPrice());
                contentStream.newLine();
                contentStream.showText("Total Price: " + receipt.getTotalPrice());
                contentStream.newLine();
                contentStream.newLine();
                contentStream.showText("____________________");
                contentStream.newLine();
                contentStream.showText("Signature");
                contentStream.endText();
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            return outputStream.toByteArray();
        }
    }

  

    public Receipt getReceiptById(Long id) {
        return receiptRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receipt not found with id: " + id));
    }
    

    public List<Receipt> getAllReceipts(){
        return receiptRepository.findAll();
        
    }
}

