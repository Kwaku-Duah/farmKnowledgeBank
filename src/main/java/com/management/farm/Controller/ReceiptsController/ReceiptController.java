
package com.management.farm.Controller.ReceiptsController;

import com.management.farm.DTO.ReceiptDTos.ReceiptRequest;
import com.management.farm.Model.ReceiptsModel.Receipt;
import com.management.farm.Service.ReceiptsService.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/new")
    @Secured({ "ROLE_ADMIN" })
    public Receipt createReceipt(@RequestBody ReceiptRequest request) {
        return receiptService.createReceipt(request.getName(), request.getQuantity(), request.getUnitPrice());
    }

    @GetMapping("/all")
    @Secured({ "ROLE_ADMIN" })
    public List<Receipt> getAllReceipts() {
        return receiptService.getAllReceipts();
    }
    

    @GetMapping("/{id}/pdf")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<byte[]> getReceiptPdf(@PathVariable Long id) throws IOException {
        Receipt receipt = receiptService.getReceiptById(id);
        byte[] pdfBytes = receiptService.generatePdfReceipt(receipt);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "receipt.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }



}
